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

package org.geomajas.quickstart.mobile.client.widget.slidenav.resource;

import com.google.gwt.resources.client.CssResource;

/**
 * CSS resource bundle for slide nav layout.
 *
 * @author Dosi Bingov
 */
public interface SlideNavCss extends CssResource {

	@ClassName("pushmenu")
	String pushMenu();

	String content();

	String navMenuSpriteIcon();

	@ClassName("pushmenu-push-toright")
	String pushmenuPushToright();

	@ClassName("buttonset")
	String buttonSet();

	@ClassName("pushmenu-open")
	String pushmenuOpen();

	String active();

	@ClassName("pushmenu-left")
	String pushmenuLeft();

	@ClassName("navButton")
	String navButton();

	@ClassName("pushmenu-push")
	String pushmenuPush();
}