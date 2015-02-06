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
package org.geomajas.quickstart.mobile.client.widget.layerlist;

import org.geomajas.gwt2.client.map.layer.LayersModel;

/**
 * Layer list presenter interface.
 *
 * @author Dosi Bingov
 */
public interface LayerListPresenter {
	void renderLayerRecords(LayersModel layerModel);

	LayerListView getLayerList();

}
