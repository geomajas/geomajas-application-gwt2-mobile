package org.mypackage.client.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEvent;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import org.geomajas.configuration.AttributeInfo;
import org.geomajas.gwt2.client.map.attribute.Attribute;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.geomajas.gwt2.client.map.layer.VectorServerLayer;
import org.geomajas.hammergwt.client.event.EventType;
import org.geomajas.hammergwt.client.event.NativeHammerEvent;
import org.geomajas.hammergwt.client.handler.HammerHandler;
import org.geomajas.hammergwt.client.impl.HammerGWT;
import org.mypackage.client.MobileAppFactory;
import org.mypackage.client.animation.ActionNames;
import org.mypackage.client.event.ActionEvent;
import org.mypackage.client.view.CarouselRecord;
import org.mypackage.client.view.FeatureInfoView;
import org.mypackage.client.widget.feature.FeatureInfoPresenter;

import java.util.Map;

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
	  panel.setWidget(featureInfoView);

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
  }


	private void renderFeatureRecord(Feature f) {
		featureInfoView.clear();

		featureInfoView.setTitle(featureInfoSlideUpPresenter.getTitle());

		if (f.getLayer() instanceof VectorServerLayer) {

			for (AttributeInfo attributeInfo :((VectorServerLayer) f.getLayer()).getLayerInfo().getFeatureInfo().getAttributes()) {
				if (!attributeInfo.isHidden()) {
					String key = attributeInfo.getName();
					Attribute<?> value = f.getAttributes().get(key);
					featureInfoView.addGridLine(attributeInfo.getLabel(), value.getValue().toString());
				}
			}
		}

	}
}
