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
package org.geomajas.quickstart.mobile.client.widget.layerlist;

import com.googlecode.mgwt.ui.client.widget.WidgetList;

/**
 * Default implementation of {@link org.geomajas.quickstart.mobile.client.widget.layerlist.LayerListView }.
 *
 * @author Dosi Bingov.
 */
public class LayerListViewImpl extends WidgetList implements LayerListView {
	private boolean rendered;

	public LayerListViewImpl() {
		super();
	}

	@Override
	public WidgetList getList() {
		return LayerListViewImpl.this;
	}

	@Override
	public boolean isListRendered() {
		return rendered;
	}

	@Override
	public void markRendered() {
		rendered = true;
	}
}