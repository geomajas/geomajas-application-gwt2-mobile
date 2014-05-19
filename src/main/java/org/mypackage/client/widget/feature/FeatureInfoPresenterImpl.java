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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.geomajas.gwt2.client.map.layer.FeaturesSupported;
import org.geomajas.hammergwt.client.event.EventType;
import org.geomajas.hammergwt.client.event.NativeHammerEvent;
import org.geomajas.hammergwt.client.handler.HammerHandler;
import org.geomajas.hammergwt.client.impl.HammerGWT;
import org.geomajas.hammergwt.client.impl.HammerTime;
import org.mypackage.client.resource.MobileAppResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeatureInfoPresenterImpl implements FeatureInfoPresenter {

	private FeatureInfoSlideUpView view;

	private DragUpHandler dragUpHandler;

	private DragDownHandler dragDownHandler;

	private FlowPanel slideUpContainer;

	private HammerTime hammerTime;

	private int currentIndex;

	private List<Feature> featureList;

	public FeatureInfoPresenterImpl(FeatureInfoSlideUpView view, FlowPanel slideUpContainer) {
		this.view = view;
		this.slideUpContainer = slideUpContainer;
		this.view.getPane().setPanelToOverlay(slideUpContainer);
		//slideUpContainer.getElement().getStyle().setDisplay(Style.Display.NONE);
		slideUpContainer.getElement().getStyle().setHeight(0, Style.Unit.PX);
		featureList = new ArrayList<Feature>();

		currentIndex = 0;

		if(null == hammerTime) {

			hammerTime = HammerGWT.on(view.asWidget(), new HammerHandler() {

				@Override
				public void onHammerEvent(NativeHammerEvent event) {
					event.stopDetect();
					event.preventDefault();
					event.preventNativeDefault();
					event.stopPropagation();

					if (event.getType() == EventType.DRAGUP) {
						FeatureInfoPresenterImpl.this.view.hide();
						FeatureInfoPresenterImpl.this.slideUpContainer.getElement().getStyle()
								.setHeight(0, Style.Unit.PX);

						if (null != dragUpHandler) {
							Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

								@Override
								public void execute() {
									dragUpHandler.onDragUp();
								}
							});

						}
					}

					if (event.getType() == EventType.DRAGDOWN) {
						if (null != dragDownHandler) {
							dragDownHandler.onDragDown();
						}

						FeatureInfoPresenterImpl.this.view.hide();
						FeatureInfoPresenterImpl.this.slideUpContainer.getElement().getStyle()
								.setHeight(0, Style.Unit.PX);
					}

					if (null!= featureList && featureList.size() > 0) {

						if (event.getType() == EventType.DRAGRIGHT) {
							if (shiftToNextFeature()) {
								setFeatureLabelText();
							}

						}

						if (event.getType() == EventType.DRAGLEFT) {
							if (shiftToPrevFeature()) {
								setFeatureLabelText();
							}
						}
					}

				}
			}, EventType.DRAGDOWN, EventType.DRAGRIGHT, EventType.DRAGLEFT, EventType.DRAGUP);
		}


		  this.view.getLeftButton().addClickHandler(new ClickHandler() {

			  @Override
			  public void onClick(ClickEvent event) {
				  if (shiftToPrevFeature()) {
					  setFeatureLabelText();
				  }
			  }
		  });


		this.view.getRightButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (shiftToNextFeature()) {
					setFeatureLabelText();
				}
			}
		});

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

	@Override
	public void hideView() {
		if (null != view) {
			slideUpContainer.getElement().getStyle().setHeight(0, Style.Unit.PX);
			view.hide();
		}
	}

	private void setFeatureLabelText() {
		view.setLabel(getTitle());
	}

	@Override
	public Feature getCurrentFeature() {
		return featureList.get(currentIndex);
	}

	@Override
	public boolean shiftToPrevFeature() {
		if (currentIndex != featureList.size() - 1) {
			currentIndex++;
			return true;
		}

		return  false;
	}

	@Override
	public boolean shiftToNextFeature() {
		if (currentIndex != 0) {
			currentIndex--;
			return true;
		}

		return  false;
	}

	@Override
	public List<Feature> getAllFeatures() {
		return featureList;
	}

	@Override
	public String getTitle() {
		Feature f = getCurrentFeature();
		Element span = DOM.createSpan();
		span.setInnerHTML(f.getLayer().getTitle());
		span.getStyle().setFontSize(15, Style.Unit.PX);

		return f.getLabel() + " (" + (currentIndex + 1) + "/" + featureList.size() + ")" +"<br />"
				+ span.getString();
	}

	@Override
	public void fetchData(Map<FeaturesSupported, List<Feature>> featureMap) {
		featureList.clear();

		for (FeaturesSupported layer : featureMap.keySet()) {
			List<org.geomajas.gwt2.client.map.feature.Feature> features = featureMap.get(layer);

			for (org.geomajas.gwt2.client.map.feature.Feature f : features) {
				GWT.log("Feature label =" + f.getLabel());
				GWT.log("Feature id =" + f.getId());
				this.featureList.add(f);
			}
		}

		if (featureList.size() > 0) {
			currentIndex = featureList.size() - 1;
			show();
		}
	}

	@Override
	public void setView(final FeatureInfoSlideUpView view) {
		this.view = view;
		view.getPane().setPanelToOverlay(slideUpContainer);
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
/*
	private Map.Entry getEntry(int id){
		Iterator iterator = featureList.entrySet().iterator();
		int n = 0;
		while(iterator.hasNext()){
			Map.Entry entry = (Map.Entry) iterator.next();
			if(n == id){
				return entry;

			}
			n ++;
		}
		return null;
	}*/

	/**
	 * shows context menu if more than 1 feature in the buffered area or fires feature selected event if 1 feature
	 *//*
	private void showFeatureData() {
		// when there is more than one feature in the buffered area
		if (featureList.size() >= 2) {
			//featureSelectBoxView.clear();

			for (org.geomajas.gwt2.client.map.feature.Feature f : featureList.values()) {

			}

			//featureSelectBoxView.show(false);
		} else if (featureList.size() == 1) {
			Feature clickedFeuture = (Feature) featureList.values().toArray()[0];

			//mapPresenter.getEventBus().fireEvent(new FeatureClickedEvent(clickedFeuture));
		}
	}*/

}

