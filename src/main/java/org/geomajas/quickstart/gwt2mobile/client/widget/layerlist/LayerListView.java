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
package org.geomajas.quickstart.gwt2mobile.client.widget.layerlist;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

/**
 * Layer List record view interface.
 *
 * @author Dosi Bingov.
 */
public interface LayerListView extends IsWidget {

	WidgetList getList();

	boolean isListRendered();

	void markRendered();
}
