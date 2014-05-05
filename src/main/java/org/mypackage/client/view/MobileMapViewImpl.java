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

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import org.mypackage.client.map.TMSMap;

/**
 * Implementation of {@link org.mypackage.client.view.MobileMapView} .
 *
 * @author Dosi Bingov
 */
public class MobileMapViewImpl implements MobileMapView {
	protected SimplePanel panel;

	protected LayoutPanel main;
	protected HeaderPanel headerPanel;
	protected HeaderButton legendButton;
	protected HTML title;
	protected TMSMap tmsMap;

	public MobileMapViewImpl() {
		panel = new SimplePanel();
	    tmsMap = new TMSMap();
		main = new LayoutPanel();


		headerPanel = new HeaderPanel();
		title = new HTML();
		headerPanel.setCenterWidget(title);
		legendButton = new HeaderButton();

		headerPanel.setRightWidget(legendButton);



		main.add(headerPanel);
		main.add(tmsMap.asWidget());

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasText getHeader() {
		return title;
	}

	@Override
	public HasText getLegendButtonText() {
		return legendButton;
	}

	@Override
	public HasTapHandlers getLegendButton() {
		return legendButton;
	}

}
