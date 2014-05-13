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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.googlecode.mgwt.ui.client.dialog.SlideUpPanel;

public class FeatureInfoSlideUpViewImpl implements FeatureInfoSlideUpView {
	private FlowPanel widget;
	private Label label;

	private SlideUpPanel panel;

	public FeatureInfoSlideUpViewImpl() {
		panel = new SlideUpPanel();
		panel.setShadow(false);
		//panel.setHideOnBackgroundClick(true);
		//panel.setPanelToOverlay(haswidgets);
		label = new Label();
		widget = new FlowPanel();
		panel.setCenterContent(true);
		widget.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
		widget.getElement().getStyle().setBottom(0, Style.Unit.PX); //align to bottom
		widget.getElement().getStyle().setBackgroundColor("gray");

		widget.add(label);

		int clientWidth = Window.getClientWidth();
		widget.setSize(clientWidth + "px", "60px");

		panel.add(widget);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void show() {
	//	panel.hide();
		panel.show();
	}

	@Override
	public void hide() {
		panel.hide();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}
}
