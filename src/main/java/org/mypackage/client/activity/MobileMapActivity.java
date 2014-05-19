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

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.mvp.client.MGWTAnimationEndEvent;
import com.googlecode.mgwt.mvp.client.MGWTAnimationEndHandler;
import org.geomajas.command.CommandResponse;
import org.geomajas.command.dto.TransformGeometryRequest;
import org.geomajas.command.dto.TransformGeometryResponse;
import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.Geometry;
import org.geomajas.geometry.service.GeometryService;
import org.geomajas.gwt.client.command.CommandCallback;
import org.geomajas.gwt.client.command.GwtCommand;
import org.geomajas.gwt.client.command.GwtCommandDispatcher;
import org.geomajas.gwt.client.map.RenderSpace;
import org.geomajas.gwt2.client.Geomajas;
import org.geomajas.gwt2.client.GeomajasImpl;
import org.geomajas.gwt2.client.GeomajasServerExtension;
import org.geomajas.gwt2.client.animation.NavigationAnimationFactory;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.gwt2.client.map.View;
import org.geomajas.gwt2.client.map.ViewPort;
import org.geomajas.gwt2.client.map.ZoomOption;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.geomajas.gwt2.client.map.feature.FeatureMapFunction;
import org.geomajas.gwt2.client.map.feature.ServerFeatureService;
import org.geomajas.gwt2.client.map.layer.FeaturesSupported;
import org.geomajas.gwt2.client.map.render.RenderMapEvent;
import org.geomajas.gwt2.client.service.GeometryOperationServiceImpl;
import org.geomajas.hammergwt.client.event.NativeHammerEvent;
import org.mypackage.client.MobileAppFactory;
import org.mypackage.client.event.ViewChangeEvent;
import org.mypackage.client.map.MapHammerController;
import org.mypackage.client.presenter.MobileMapPresenter;
import org.mypackage.client.view.MobileMapView;
import org.mypackage.client.widget.feature.FeatureInfoPresenter;

import java.util.List;
import java.util.Map;

/**
 * Mobile map activity and presenter
 */
public class MobileMapActivity extends MGWTAbstractActivity implements MobileMapPresenter {

  private final MobileMapView mapView;
  private final String eventId;
  private FeatureInfoPresenter featureInfoSlideUpPresenter;
   private MobileAppFactory mobileAppFactory;

	public MobileMapActivity(MobileAppFactory mobileAppFactory) {
		this.mapView = mobileAppFactory.getMapView();
		this.eventId = "nav";
		this.mobileAppFactory = mobileAppFactory;
		featureInfoSlideUpPresenter = mobileAppFactory.getFeaturePresenter();

	}

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {

    addHandlerRegistration(mapView.getLegendButton().addTapHandler(new TapHandler() {

		@Override
		public void onTap(TapEvent event) {
			ViewChangeEvent.fire(eventBus, ViewChangeEvent.VIEW.LEGEND);
		}

	}));

	  addHandlerRegistration(mapView.getLocationButton().addTapHandler(new TapHandler() {

		  @Override
		  public void onTap(TapEvent event) {
			 	getGeoLocation();
		  }

	  }));


	featureInfoSlideUpPresenter.setDragUpHandler(new FeatureInfoPresenter.DragUpHandler() {

		 @Override
		 public void onDragUp() {
			 ViewChangeEvent.fire(eventBus, ViewChangeEvent.VIEW.FEATURE_INFO);
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

						/*if (null == featureInfoSlideUpPresenter.getView()) {
							featureInfoSlideUpPresenter.setView(mobileAppFactory.getFeatureInfoSlideView());
						}*/

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



	private void getGeoLocation() {

		final Geolocation geo = Geolocation.getIfSupported();
		if (geo != null) {
			geo.getCurrentPosition(new Callback<Position, PositionError>() {

				@Override
				public void onSuccess(final Position result) {
					Position.Coordinates coord = result.getCoordinates();
					TransformGeometryRequest req = new TransformGeometryRequest();
					Geometry point = new Geometry(Geometry.POINT, 4326, -1);
					point.setCoordinates(new Coordinate[] { new Coordinate(coord.getLongitude(), coord.getLatitude()) });

					GWT.log("Browser coords " + new Coordinate(coord.getLongitude(), coord.getLatitude()));

					req.setGeometry(point);

					req.setSourceCrs("EPSG:4326");

					req.setTargetCrs(mapView.getMap().getMapPresenter().getViewPort().getCrs());

					GwtCommand command = new GwtCommand(TransformGeometryRequest.COMMAND);
					command.setCommandRequest(req);

					GwtCommandDispatcher.getInstance().execute(command, new CommandCallback<CommandResponse>() {

						@Override
						public void execute(CommandResponse response) {
							if (response.getErrors().isEmpty()) {
								org.geomajas.geometry.Geometry geom = ((TransformGeometryResponse) response)
										.getGeometry();
								double accuracy = result.getCoordinates().getAccuracy();

								GWT.log("After transform coords " + geom.getCoordinates()[0]);

								Bbox box = new Bbox(geom.getCoordinates()[0].getX() - (accuracy / 2), geom
										.getCoordinates()[0].getY() - (accuracy / 2), accuracy, accuracy);
								double res = mapView.getMap().getMapPresenter().getViewPort().getResolution();


								int co = mapView.getMap().getMapPresenter().getViewPort().getResolutionIndex(mapView.getMap().getMapPresenter().getViewPort().getResolution());
								double crr = mapView.getMap().getMapPresenter().getViewPort().getResolution(co + 3);


								mapView.getMap().getMapPresenter().getViewPort().applyView(new View(geom.getCoordinates()[0], crr));

								//mapView.getMap().getMapPresenter().getViewPort().applyBounds(box, ZoomOption.LEVEL_CLOSEST);
							}
						}
					});

				}

				@Override
				public void onFailure(PositionError reason) {

				}
			});
		}
	}




}
