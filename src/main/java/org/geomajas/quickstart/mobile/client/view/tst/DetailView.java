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
package org.geomajas.quickstart.mobile.client.view.tst;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

/**
 * TODO.
 *
 * @author Dosi Bingov
 */
public interface DetailView extends IsWidget {
	HasText getHeader();
	
	HasText getBackbuttonText();
	
	HasTapHandlers getBackbutton();
	
	HasText getMainButtonText();
	
	HasTapHandlers getMainButton();
}
