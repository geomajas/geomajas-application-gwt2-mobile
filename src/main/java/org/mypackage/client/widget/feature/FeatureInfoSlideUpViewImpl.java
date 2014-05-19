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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.dialog.SlideUpPanel;
import com.googlecode.mgwt.ui.client.widget.*;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import org.mypackage.client.resource.MobileAppResource;

public class FeatureInfoSlideUpViewImpl implements FeatureInfoSlideUpView  {
	private com.googlecode.mgwt.ui.client.widget.HeaderPanel widget;
	private FlowPanel label;
	private FlowPanel centerWidget;
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
		centerWidget = new FlowPanel();
		widget = new HeaderPanel();
		Image slideUpButton = new Image(MobileAppResource.RESOURCE.split());
		slideUpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				event.getSource();
			}
		});
		slideUpButton.getElement().getStyle().setHeight(20, Style.Unit.PX);
		slideUpButton.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
		centerWidget.add(slideUpButton);
		centerWidget.add(label);
		centerWidget.getElement().getStyle().setMarginTop(0, Style.Unit.PX);
		centerWidget.getElement().getStyle().setPaddingTop(5, Style.Unit.PX);

		panel.setCenterContent(true);
		widget.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
		widget.getElement().getStyle().setBottom(0, Style.Unit.PX); //align to bottom
		widget.getElement().getStyle().setBackgroundColor("gray");
		widget.setCenterWidget(centerWidget);
		centerWidget.setHeight("60px");

		label.getElement().getStyle().setFontSize(22, Style.Unit.PX);
		label.getElement().getStyle().setMarginTop(12, Style.Unit.PX);
		centerWidget.addStyleName(MobileAppResource.RESOURCE.css().slideUpcenter());
		//label.addStyleName(MobileAppResource.RESOURCE.css().splitImage());

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

	class FeatureInfoButton extends ButtonBase {
		public FeatureInfoButton() {
			super(Document.get().createPushButtonElement());
			getElement().getStyle().setProperty("top", "15px");
			getElement().getStyle().setWidth(25, Style.Unit.PX);


		}
	}
}
