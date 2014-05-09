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
package org.mypackage.client.widget.zoom;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;


/**
 * Mobile zoom  impl.
 *
 * @author Dosi Bingov.
 */
public class MobileZoomViewImpl implements  MobileZoomView {
	private Button zoomIn;

	private AbsolutePanel control;
	private Button zoomOut;

	public MobileZoomViewImpl() {
		zoomIn = new Button();
		zoomIn.setSmall(true);
		zoomOut = new Button();
		zoomOut.setText("-");
		zoomOut.setSmall(true);
		zoomIn.setText("+");
		zoomIn.getElement().getStyle().setOpacity(0.8);
		zoomOut.getElement().getStyle().setOpacity(0.8);

		control = new AbsolutePanel();
		control.add(zoomIn);
		control.add(zoomOut);
	}

	@Override
	public HasTapHandlers getZoomInButton() {
		return zoomIn;
	}

	@Override
	public HasTapHandlers getZoomOutButton() {
		return zoomOut;
	}

	@Override
	public Widget asWidget() {
		return control;
	}
}
