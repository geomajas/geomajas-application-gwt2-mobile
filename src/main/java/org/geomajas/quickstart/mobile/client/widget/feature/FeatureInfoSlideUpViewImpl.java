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
package org.geomajas.quickstart.mobile.client.widget.feature;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.dialog.SlideUpPanel;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import org.geomajas.quickstart.mobile.client.resource.MobileAppResource;

/**
 * TODO.
 *
 * @author Dosi Bingov
 */
public class FeatureInfoSlideUpViewImpl implements FeatureInfoSlideUpView  {
	private com.googlecode.mgwt.ui.client.widget.HeaderPanel widget;
	private FlowPanel label;
	private FeatureInfoButton goLeft;
	private  FeatureInfoButton goRight;

	private SlideUpPanel panel;

	public FeatureInfoSlideUpViewImpl() {
		MobileAppResource.RESOURCE.css().ensureInjected();
		panel = new SlideUpPanel();
		panel.setShadow(false);
		goLeft = new FeatureInfoButton();
		goRight = new FeatureInfoButton();

		goRight.setText(">");
		goLeft.setText("<");

		goRight.setStyleName(MobileAppResource.RESOURCE.css().featureInfoButton());
		goLeft.setStyleName(MobileAppResource.RESOURCE.css().featureInfoButton());
		//panel.setHideOnBackgroundClick(true);
		//panel.setPanelToOverlay(haswidgets);
		label = new FlowPanel();
		widget = new HeaderPanel();
		panel.setCenterContent(true);
		widget.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
		widget.getElement().getStyle().setBottom(0, Style.Unit.PX); //align to bottom
		widget.getElement().getStyle().setBackgroundColor("gray");
		widget.setCenterWidget(label);
		label.setHeight("60px");

		label.getElement().getStyle().setFontSize(18, Style.Unit.PX);
		label.addStyleName(MobileAppResource.RESOURCE.css().slideUpcenter());
		label.addStyleName(MobileAppResource.RESOURCE.css().splitImage());

		widget.setLeftWidget(goLeft);
		widget.setRightWidget(goRight);

		widget.getElement().getStyle().setCursor(Style.Cursor.MOVE);

		widget.setSize("100%", "60px");

		panel.add(widget);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void show() {
		panel.show();
	}

	@Override
	public void hide() {
		panel.hide();
	}

	@Override
	public void setLabel(String label) {
		this.label.getElement().setInnerHTML(label);
	}

	@Override
	public SlideUpPanel getPane() {
		return panel;
	}

	@Override
	public ButtonBase getLeftButton() {
		return goLeft;
	}

	@Override
	public ButtonBase getRightButton() {
		return goRight;
	}

	/**
	 * TODO.
	 *
	 * @author Dosi Bingov
	 */
	class FeatureInfoButton extends ButtonBase {
		public FeatureInfoButton() {
			super(Document.get().createPushButtonElement());
			getElement().getStyle().setProperty("top", "15px");
			getElement().getStyle().setWidth(25, Style.Unit.PX);


		}
	}
}
