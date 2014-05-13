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

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import org.geomajas.hammergwt.client.handler.HammerTapHandler;
import org.mypackage.client.map.GeomajasMap;
import org.mypackage.client.map.MapHammerController;
import org.mypackage.client.widget.zoom.MobileZoomView;

/**
 * Mobile map view interface.
 */
public interface MobileMapView extends IsWidget{
	public HasText getLegendButtonText();
	
	public HasTapHandlers getLegendButton();

	public GeomajasMap getMap();

	public MobileZoomView getZoomControl();
}
