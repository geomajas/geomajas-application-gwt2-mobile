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
package org.geomajas.quickstart.mobile.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.Button;
import org.geomajas.quickstart.mobile.client.map.GeomajasMap;
import org.geomajas.quickstart.mobile.client.resource.MobileAppResource;
import org.geomajas.quickstart.mobile.client.widget.zoom.MobileZoomView;
import org.geomajas.quickstart.mobile.client.widget.zoom.MobileZoomViewImpl;

/**
 * Implementation of {@link org.geomajas.quickstart.mobile.client.view.MobileMapView} .
 *
 * @author Dosi Bingov
 */
public class MobileMapViewImpl implements MobileMapView {
	protected Button legendButton;
	private Button gotoCurLocation;
	protected GeomajasMap geomajasMap;
	private MobileZoomView zoomControl;

	private FlowPanel layoutPanel;

	private FlowPanel slideUpContainer;


	public MobileMapViewImpl() {
		layoutPanel = new FlowPanel();
		slideUpContainer = new FlowPanel();
		layoutPanel.setSize("100%", "100%");
		slideUpContainer.setSize("100%", "70px");
		slideUpContainer.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
		slideUpContainer.getElement().getStyle().setBottom(0, Style.Unit.PX);
		gotoCurLocation = new Button();
		Image location = new Image(MobileAppResource.RESOURCE.location());
		location.setHeight("30px");
		location.setWidth("30px");
		gotoCurLocation.getElement().appendChild(location.getElement());

		geomajasMap = new GeomajasMap();
		legendButton = new Button();
		legendButton.getElement().getStyle().setOpacity(0.8);
		legendButton.getElement().getStyle().setRight(2, Style.Unit.PX);
		legendButton.getElement().getStyle().setFontSize(15, Style.Unit.PX);
		legendButton.getElement().getStyle().setProperty("padding", "5px 10px");
		legendButton.getElement().getStyle().setTop(2, Style.Unit.PX);
		zoomControl = new MobileZoomViewImpl();
		zoomControl.asWidget().getElement().getStyle().setLeft(2, Style.Unit.PX);
		zoomControl.asWidget().getElement().getStyle().setTop(2, Style.Unit.PX);

		gotoCurLocation.getElement().getStyle().setLeft(2, Style.Unit.PX);
		gotoCurLocation.asWidget().getElement().getStyle().setTop(90, Style.Unit.PX);
		gotoCurLocation.getElement().getStyle().setOpacity(0.8);
		gotoCurLocation.getElement().getStyle().setPadding(0, Style.Unit.PX);
		gotoCurLocation.getElement().getStyle().setHeight(30, Style.Unit.PX);
		gotoCurLocation.getElement().getStyle().setWidth(30, Style.Unit.PX);

		geomajasMap.getMapPresenter().getWidgetPane().add(zoomControl.asWidget());
		geomajasMap.getMapPresenter().getWidgetPane().add(legendButton);
		geomajasMap.getMapPresenter().getWidgetPane().add(gotoCurLocation);


		layoutPanel.add(geomajasMap.asWidget());
		layoutPanel.add(slideUpContainer);
	}

	@Override
	public Widget asWidget() {
		//return geomajasMap.asWidget();

		return layoutPanel;
	}

	@Override
	public HasText getLegendButtonText() {
		return legendButton;
	}

	@Override
	public HasTapHandlers getLegendButton() {
		return legendButton;
	}

	@Override
	public HasTapHandlers getLocationButton() {
		return gotoCurLocation;
	}

	@Override
	public GeomajasMap getMap() {
		return geomajasMap;
	}

	@Override
	public MobileZoomView getZoomControl() {
		return zoomControl;
	}



	@Override
	public FlowPanel getSlideUpContainer() {
		return slideUpContainer;
	}

}
