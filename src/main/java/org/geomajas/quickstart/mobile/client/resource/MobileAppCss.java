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

package org.geomajas.quickstart.mobile.client.resource;

import com.google.gwt.resources.client.CssResource;

/**
 * CSS resource bundle for slide nav layout.
 *
 * @author Dosi Bingov
 */
public interface MobileAppCss extends CssResource {

	@ClassName("buttonFeatureInfo")
	String featureInfoButton();

	String splitImage();

	String slideUpcenter();
}