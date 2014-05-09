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

package org.mypackage.client.widget.slidenav.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Slide nav resources bundle.
 * 
 * @author Dosi Bingov
 */
public interface SlideNavResource extends ClientBundle {
	SlideNavResource RESOURCE = GWT.create(SlideNavResource.class);

	@Source("slidenav.css")
	SlideNavCss css();

	@Source("nav_menu.png")
	ImageResource navButtonSprite();
}