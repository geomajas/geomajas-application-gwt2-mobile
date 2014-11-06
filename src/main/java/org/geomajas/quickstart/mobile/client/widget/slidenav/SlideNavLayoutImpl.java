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

package org.geomajas.quickstart.mobile.client.widget.slidenav;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import org.geomajas.quickstart.mobile.client.widget.slidenav.resource.SlideNavCss;
import org.geomajas.quickstart.mobile.client.widget.slidenav.resource.SlideNavResource;

/**
 * Sliding navigation Layout for the map.
 * 
 * @author Dosi Bingov
 */
public class SlideNavLayoutImpl implements SlideNavLayout, IsWidget {

	private boolean isMenuContentLoaded;

	private HTMLPanel layout;

	@UiField
	protected ResizeLayoutPanel content;

/*	@UiField
	protected Button navButton;

	@UiField
	protected Object navMenu;

	@UiField
	protected HTMLPanel slideNavBody;

	@UiField
	protected ResizeLayoutPanel content;

	@UiField
	protected SimplePanel navContent;

	@UiField
	protected SimplePanel button;

	@UiField
	com.google.gwt.dom.client.Element menuTitle;*/

	@Override
	public void setMenuTitle(String title) {
		//menuTitle.setInnerText(title);
	}

	@Override
	public void setMainContent(IsWidget contentWidget) {
		this.content.setWidget(contentWidget);
	}

	@Override
	public void setNavContent(IsWidget navContentWidget) {
		//navContent.setWidget(navContentWidget);

	}

	@Override
	public void setNavbarContent(IsWidget navbarContent) {

	}

	@Override
	public Widget asWidget() {
		layout.getElement().setId("htmlPanel");

		return layout;
	}

	/**
	 * UI binder interface this com.googlecode.mgwt.ui.client.widget.
	 *
	 * @author Dosi Bingov
	 */
	interface MyUiBinder extends UiBinder<HTMLPanel, SlideNavLayoutImpl> {
	}

	private static final MyUiBinder UIBINDER = GWT.create(MyUiBinder.class);


	public SlideNavLayoutImpl(HeaderButton legendButton) {
		this(legendButton, SlideNavResource.RESOURCE.css());
	}


	public SlideNavLayoutImpl(HeaderButton legendButton, final SlideNavCss css) {
		layout = UIBINDER.createAndBindUi(this);

		css.ensureInjected();
		/*navButton.setStyleName(css.navButton());
		navButton.addStyleName(css.navMenuSpriteIcon());*/
		//slideNavBody.setStyleName(css.pushmenuPush());
		// RootPanel.get().setStyleName(css.pushmenuPush());

		//button.setWidget(legendButton);

		//content.setWidget(map.asWidget());

		/*navButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Element menuel = (Element) navMenu;
				toggleCssClass(css.active(), navButton.getElement());
				//toggleCssClass(css.pushmenuPushToright(), slideNavBody.getElement());

				toggleCssClass(css.pushmenuPushToright(), RootPanel.get().getElement());
				toggleCssClass(css.pushmenuOpen(), menuel);

				if(!isMenuContentLoaded) {
					MapLegendPanel legendPanel = new MapLegendPanel(map.getMapPresenter());

					navContent.setWidget(legendPanel);
					isMenuContentLoaded = true;
				}

			}
		});*/
	}

	private void toggleCssClass(String cssClassName, Element element) {
		String cssClassAttr = element.getAttribute("class");

		if (cssClassAttr.indexOf(cssClassName) != -1) {
			element.removeClassName(cssClassName);
		} else {
			element.addClassName(cssClassName);
		}
	}
}