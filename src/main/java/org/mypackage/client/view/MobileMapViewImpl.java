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
package org.mypackage.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import org.mypackage.client.map.GeomajasMap;
import org.mypackage.client.widget.zoom.MobileZoomView;
import org.mypackage.client.widget.zoom.MobileZoomViewImpl;

/**
 * Implementation of {@link org.mypackage.client.view.MobileMapView} .
 *
 * @author Dosi Bingov
 */
public class MobileMapViewImpl implements MobileMapView {
	protected Button legendButton;
	protected HTML title;
	protected GeomajasMap geomajasMap;
	private MobileZoomView zoomControl;


	public MobileMapViewImpl() {
		geomajasMap = new GeomajasMap();
		title = new HTML();
		legendButton = new Button();
		legendButton.setSmall(true);
		legendButton.getElement().getStyle().setOpacity(0.6);
		legendButton.getElement().getStyle().setRight(2, Style.Unit.PX);
		legendButton.getElement().getStyle().setTop(2, Style.Unit.PX);

		zoomControl = new MobileZoomViewImpl();
		zoomControl.asWidget().getElement().getStyle().setLeft(2, Style.Unit.PX);
		zoomControl.asWidget().getElement().getStyle().setTop(2, Style.Unit.PX);
		geomajasMap.getMapPresenter().getWidgetPane().add(zoomControl.asWidget());
		geomajasMap.getMapPresenter().getWidgetPane().add(legendButton);
	}

	@Override
	public Widget asWidget() {
		return geomajasMap.asWidget();
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
	public GeomajasMap getMap() {
		return geomajasMap;
	}

	@Override
	public MobileZoomView getZoomControl() {
		return zoomControl;
	}
}
