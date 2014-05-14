/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2014 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.mypackage.client.activity;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.mvp.client.MGWTAnimationEndEvent;
import com.googlecode.mgwt.mvp.client.MGWTAnimationEndHandler;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.Geometry;
import org.geomajas.gwt.client.map.RenderSpace;
import org.geomajas.gwt2.client.GeomajasServerExtension;
import org.geomajas.gwt2.client.animation.NavigationAnimationFactory;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.gwt2.client.map.ViewPort;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.geomajas.gwt2.client.map.feature.FeatureMapFunction;
import org.geomajas.gwt2.client.map.feature.ServerFeatureService;
import org.geomajas.gwt2.client.map.layer.FeaturesSupported;
import org.geomajas.gwt2.client.map.render.RenderMapEvent;
import org.geomajas.hammergwt.client.event.NativeHammerEvent;
import org.geomajas.hammergwt.client.handler.HammerTapHandler;
import org.mypackage.client.MobileAppFactory;
import org.mypackage.client.event.ActionEvent;
import org.mypackage.client.animation.ActionNames;
import org.mypackage.client.event.ViewChangeEvent;
import org.mypackage.client.map.MapHammerController;
import org.mypackage.client.presenter.MobileMapPresenter;
import org.mypackage.client.view.MobileMapView;
import org.mypackage.client.widget.feature.FeatureInfoSlideUpPresenter;
import org.mypackage.client.widget.feature.FeatureInfoSlideUpPresenterImpl;
import org.mypackage.client.widget.feature.FeatureInfoSlideUpView;

import java.util.List;
import java.util.Map;

/**
 * Mobile map activity and presenter
 */
public class MobileMapActivity extends MGWTAbstractActivity implements MobileMapPresenter {

  private final MobileMapView mapView;
  private final String eventId;
  private FeatureInfoSlideUpPresenter featureInfoSlideUpPresenter;
   private MobileAppFactory mobileAppFactory;

	public MobileMapActivity(MobileAppFactory mobileAppFactory) {
		this.mapView = mobileAppFactory.getMapView();
		this.eventId = "nav";
		this.mobileAppFactory = mobileAppFactory;
		featureInfoSlideUpPresenter = new FeatureInfoSlideUpPresenterImpl(mapView.getSlideUpContainer());

	}

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {

    addHandlerRegistration(mapView.getLegendButton().addTapHandler(new TapHandler() {

		@Override
		public void onTap(TapEvent event) {
			ViewChangeEvent.fire(eventBus, ViewChangeEvent.VIEW.LEGEND);
		}

	}));


	featureInfoSlideUpPresenter.setDragUpHandler(new FeatureInfoSlideUpPresenter.DragUpHandler() {

		 @Override
		 public void onDragUp() {
			 //sfeatureInfoSlideUpPresenter.getView().hide();
			 ViewChangeEvent.fire(eventBus, ViewChangeEvent.VIEW.LEGEND);
		 }
	 });

	  mapView.getMap().setMapTapHandler(new MapHammerController.HammerTapLocationHandler() {

			@Override
			public void onLocationTap(NativeHammerEvent event, Coordinate tappedLocation) {
				   //featureInfoSlideUpPresenter.getView().hide();
					searchAtLocation(tappedLocation);
				}
			});

			  mapView.getZoomControl().getZoomInButton().addTapHandler(new TapHandler() {

				  @Override
				  public void onTap(TapEvent event) {

					  ViewPort viewPort = mapView.getMap().getMapPresenter().getViewPort();
					  int index = viewPort.getResolutionIndex(viewPort.getResolution());
					  if (index < viewPort.getResolutionCount() - 1) {
						  viewPort.registerAnimation(
								  NavigationAnimationFactory.createZoomIn(mapView.getMap().getMapPresenter()));
					  }
				  }
			  });

	  mapView.getZoomControl().getZoomOutButton().addTapHandler(new TapHandler() {

		  @Override
		  public void onTap(TapEvent event) {
			  ViewPort viewPort = mapView.getMap().getMapPresenter().getViewPort();

			  int index = viewPort.getResolutionIndex(viewPort.getResolution());
			  if (index > 0) {
				  viewPort.registerAnimation(NavigationAnimationFactory.createZoomOut(mapView.getMap().getMapPresenter()));
			  }
		  }
	  });

	  mapView.getLegendButtonText().setText("Layers");

	  eventBus.addHandler(MGWTAnimationEndEvent.getType(), new MapActivityAnimationEndHandler());

	  panel.setWidget(mapView);
  }

	@Override
	public void refreshMap() {
		mapView.getMap().getMapPresenter().getEventBus().fireEvent(new RenderMapEvent());
	}

	//-------------------
	// Private methods
	//-------------------

	private void searchAtLocation(Coordinate location) {
		Geometry point = new Geometry(Geometry.POINT, 0, -1);
		point.setCoordinates(new Coordinate[] { location });
		MapPresenter mapPresenter = mapView.getMap().getMapPresenter();

		GeomajasServerExtension.getInstance().getServerFeatureService().search(mapPresenter, point,
				calculateBufferFromPixelTolerance(7), ServerFeatureService.QueryType.INTERSECTS, ServerFeatureService.SearchLayerType.SEARCH_ALL_LAYERS, -1,
				new FeatureMapFunction() {

					@Override
					public void execute(Map<FeaturesSupported, List<Feature>> featureMap) {

						if (null == featureInfoSlideUpPresenter.getView()) {
							featureInfoSlideUpPresenter.setView(mobileAppFactory.getFeatureInfoSlideView());
						}


						//featureInfoSlideUpPresenter.show();

						featureInfoSlideUpPresenter.fetchData(featureMap);

					}
				});
	}


	private double calculateBufferFromPixelTolerance(int pixelBuffer) {
		MapPresenter mapPresenter = mapView.getMap().getMapPresenter();

		Coordinate c1 = mapPresenter.getViewPort().getTransformationService()
				.transform(new Coordinate(0, 0), RenderSpace.SCREEN, RenderSpace.WORLD);
		Coordinate c2 = mapPresenter.getViewPort().getTransformationService()
				.transform(new Coordinate(pixelBuffer, 0), RenderSpace.SCREEN, RenderSpace.WORLD);
		return c1.distance(c2);
	}


	private class MapActivityAnimationEndHandler implements MGWTAnimationEndHandler {

		@Override
		public void onAnimationEnd(MGWTAnimationEndEvent event) {
			refreshMap();
		}
	}

}
