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
package org.mypackage.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MobileAppResource extends ClientBundle {

	MobileAppResource RESOURCE = GWT.create(MobileAppResource.class);

	@Source("mobile_app.css")
	MobileAppCss css();

	@Source("image/location.png")
	ImageResource location();

	@Source("image/split.png")
	ImageResource split();
}
