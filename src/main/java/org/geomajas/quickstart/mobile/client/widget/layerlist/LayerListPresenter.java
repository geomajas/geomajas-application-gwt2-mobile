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