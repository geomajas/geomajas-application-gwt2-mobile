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
package org.mypackage.client.widget.feature;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.geomajas.gwt2.client.map.layer.FeaturesSupported;
import org.geomajas.gwt2.widget.client.featureselectbox.event.FeatureClickedEvent;
import org.geomajas.hammergwt.client.event.EventType;
import org.geomajas.hammergwt.client.event.NativeHammerEvent;
import org.geomajas.hammergwt.client.handler.HammerHandler;
import org.geomajas.hammergwt.client.impl.HammerGWT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureInfoSlideUpPresenterImpl implements FeatureInfoSlideUpPresenter {

	private FeatureInfoSlideUpView view;

	private DragUpHandler dragUpHandler;

	private DragDownHandler dragDownHandler;

	private FlowPanel slideUpContainer;

	private int currentIndex;

	private Map<String, org.geomajas.gwt2.client.map.feature.Feature> clickedFeatures;

	public FeatureInfoSlideUpPresenterImpl(FlowPanel slideUpContainer) {
		this.slideUpContainer = slideUpContainer;
		//slideUpContainer.getElement().getStyle().setDisplay(Style.Display.NONE);
		slideUpContainer.getElement().getStyle().setHeight(0, Style.Unit.PX);
		clickedFeatures = new HashMap<String, Feature>();
		currentIndex = 0;
	}

	@Override
	public boolean show() {
		if (null != view) {
			setFeatureLabelText();
			//slideUpContainer.getElement().getStyle().setProperty("display", "");
			slideUpContainer.getElement().getStyle().setHeight(70, Style.Unit.PX);
			view.show();

			return true;
		}

		return false;
	}

	private void setFeatureLabelText() {
		Feature clickedFeuture = (Feature) clickedFeatures.values().toArray()[currentIndex];
		view.setText(clickedFeuture.getLabel() + " ID=" + clickedFeuture.getId() +
				" (" + (currentIndex + 1) + "/" + clickedFeatures.size() + ")");
	}

	@Override
	public void fetchData(Map<FeaturesSupported, List<Feature>> featureMap) {
		clickedFeatures.clear(); // clear all stored features

		for (FeaturesSupported layer : featureMap.keySet()) {
			List<org.geomajas.gwt2.client.map.feature.Feature> features = featureMap.get(layer);

			if (features != null) {
				for (org.geomajas.gwt2.client.map.feature.Feature f : features) {
					GWT.log("Feature label =" + f.getLabel());
					GWT.log("Feature id =" + f.getId());

					// store features in a hashmap
					clickedFeatures.put(f.getLabel(), f);
				}

			}
		}

		if (clickedFeatures.size() > 0) {
			currentIndex = clickedFeatures.size() - 1;
			show();
		}

		// showFeatureData();
	}

	@Override
	public void setView(final FeatureInfoSlideUpView view) {
		this.view = view;
		view.getPane().setPanelToOverlay(slideUpContainer);



		HammerGWT.on(view.asWidget(), new HammerHandler() {

			@Override
			public void onHammerEvent(NativeHammerEvent event) {
				event.stopDetect();
				event.preventDefault();
				event.preventNativeDefault();

				if (event.getType() == EventType.DRAGUP) {
					if ( null != dragUpHandler) {
						dragUpHandler.onDragUp();
					}

					slideUpContainer.getElement().getStyle().setHeight(0, Style.Unit.PX);
				}

				if (event.getType() == EventType.DRAGDOWN) {
					if ( null != dragDownHandler) {
						dragDownHandler.onDragDown();
					}

					view.hide();
					slideUpContainer.getElement().getStyle().setHeight(0, Style.Unit.PX);
				}

				if (clickedFeatures.size() > 0) {

					if (event.getType() == EventType.DRAGRIGHT) {

						if (currentIndex != 0) {
							currentIndex--;
							setFeatureLabelText();
						}

					}

					if (event.getType() == EventType.DRAGLEFT) {
						if (currentIndex != clickedFeatures.size() - 1) {
							currentIndex++;
							setFeatureLabelText();
						}

					}
				}

			}
		}, EventType.DRAGDOWN, EventType.DRAGRIGHT, EventType.DRAGLEFT, EventType.DRAGUP);
	}

	@Override
	public FeatureInfoSlideUpView getView() {
		return view;
	}

	@Override
	public void setDragUpHandler(DragUpHandler dragUpHandler) {
		this.dragUpHandler = dragUpHandler;
	}

	@Override
	public void setDragDownHandler(DragDownHandler dragDownHandler) {
		this.dragDownHandler = dragDownHandler;
	}

	/**
	 * shows context menu if more than 1 feature in the buffered area or fires feature selected event if 1 feature
	 */
	private void showFeatureData() {
		// when there is more than one feature in the buffered area
		if (clickedFeatures.size() >= 2) {
			//featureSelectBoxView.clear();

			for (org.geomajas.gwt2.client.map.feature.Feature f : clickedFeatures.values()) {

			}

			//featureSelectBoxView.show(false);
		} else if (clickedFeatures.size() == 1) {
			Feature clickedFeuture = (Feature) clickedFeatures.values().toArray()[0];

			//mapPresenter.getEventBus().fireEvent(new FeatureClickedEvent(clickedFeuture));
		}
	}

}

