/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.quickstart.mobile.client.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import org.geomajas.configuration.AttributeInfo;
import org.geomajas.gwt2.client.map.attribute.Attribute;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.geomajas.gwt2.client.map.layer.VectorServerLayer;
import org.geomajas.quickstart.mobile.client.MobileAppFactory;
import org.geomajas.quickstart.mobile.client.animation.ActionNames;
import org.geomajas.quickstart.mobile.client.event.ActionEvent;
import org.geomajas.quickstart.mobile.client.view.FeatureInfoView;
import org.geomajas.quickstart.mobile.client.widget.feature.FeatureInfoPresenter;

/**
 * Legend activity and presenter.
 *
 * @author Dosi Bingov
 */
public class FeatureInfoActivity extends MGWTAbstractActivity {

  private final FeatureInfoView featureInfoView;
  private final String eventId;
  private FeatureInfoPresenter featureInfoSlideUpPresenter;


	public FeatureInfoActivity(MobileAppFactory mobileAppFactory) {
		this.featureInfoView = mobileAppFactory.getFeatureInfoView();
		this.featureInfoSlideUpPresenter = mobileAppFactory.getFeaturePresenter();
		this.eventId = "nav";
	}

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {


	  addHandlerRegistration(featureInfoView.getBackButton().addTapHandler(new TapHandler() {

		  @Override
		  public void onTap(TapEvent event) {
			  ActionEvent.fire(eventBus, ActionNames.BACK);
		  }
	  }));

	  featureInfoView.setDragLeftHandler(new FeatureInfoView.DragLeftHandler() {

		  @Override
		  public void onDragLeft() {
			  if (featureInfoSlideUpPresenter.shiftToPrevFeature()) {
				  renderFeatureRecord(featureInfoSlideUpPresenter.getCurrentFeature());
			  }
		  }
	  });

	  featureInfoView.setDragRightHandler(new FeatureInfoView.DragRightHandler() {

		  @Override
		  public void onDragRight() {
			  if (featureInfoSlideUpPresenter.shiftToNextFeature()) {
				  renderFeatureRecord(featureInfoSlideUpPresenter.getCurrentFeature());
			  }
		  }
	  });


	  featureInfoView.setDragDownHandler(new FeatureInfoView.DragDownHandler() {

		  @Override
		  public void onDragDown() {
			  ActionEvent.fire(eventBus, ActionNames.BACK);
		  }
	  });

	  /*featureInfoView.addSwipeEndHandler(new SwipeEndHandler() {

		  @Override
		  public void onSwipeEnd(SwipeEndEvent event) {

			  if (event.getDirection() == SwipeEvent.DIRECTION.LEFT_TO_RIGHT ) {

				  if (featureInfoSlideUpPresenter.shiftToPrevFeature()) {
					  renderFeatureRecord(featureInfoSlideUpPresenter.getCurrentFeature());
				  }
			  }

			  if (event.getDirection() == SwipeEvent.DIRECTION.RIGHT_TO_LEFT ) {
				  if (featureInfoSlideUpPresenter.shiftToNextFeature()) {
					  renderFeatureRecord(featureInfoSlideUpPresenter.getCurrentFeature());
				  }
			  }

			  if (event.getDirection() == SwipeEvent.DIRECTION.TOP_TO_BOTTOM ) {
				  ActionEvent.fire(eventBus, ActionNames.BACK);
			  }

		  }
	  });*/


	  renderFeatureRecord(featureInfoSlideUpPresenter.getCurrentFeature());





/*	  for (org.geomajas.gwt2.client.map.feature.Feature f :featureInfoSlideUpPresenter.getAllFeatures()) {

		  String title = f.getLabel() + " (" + index + "/" + featureInfoSlideUpPresenter.getAllFeatures().size() +")";
		  index ++;

		  CarouselRecord carouselRecord = new CarouselRecord(title);

		 // for (Map.Entry<String, Attribute<?>> entry : f.getAttributes().entrySet()) {




		 // }

		  if(index == 1) {
			  break;
		  }

	  }*/

	  panel.setWidget(featureInfoView);
  }


	private void renderFeatureRecord(Feature f) {
		featureInfoView.clear();

		featureInfoView.setTitle(featureInfoSlideUpPresenter.getTitle());

		if (f.getLayer() instanceof VectorServerLayer) {

			for (AttributeInfo attributeInfo :
					((VectorServerLayer) f.getLayer()).getLayerInfo().getFeatureInfo().getAttributes()) {
				if (!attributeInfo.isHidden()) {
					String key = attributeInfo.getName();
					Attribute<?> value = f.getAttributes().get(key);
					featureInfoView.addGridLine(attributeInfo.getLabel(), value.getValue().toString());
				}
			}
		}

	}
}
