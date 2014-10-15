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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.gwt2.client.event.LayerAddedEvent;
import org.geomajas.gwt2.client.event.LayerOrderChangedEvent;
import org.geomajas.gwt2.client.event.LayerOrderChangedHandler;
import org.geomajas.gwt2.client.event.LayerRemovedEvent;
import org.geomajas.gwt2.client.event.MapCompositionHandler;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.gwt2.client.map.layer.Layer;

/**
 * Legend panel that shows the legend for an entire map. It uses the {@link org.geomajas.gwt2.widget.client.map.LayerLegendPanel} to render the legends for
 * individual layers. This com.googlecode.mgwt.ui.client.widget will keep track of the layers in the map's
 * {@link org.geomajas.gwt2.client.map.layer.LayersModel}. If a new layer is added or layer is removed from the map,
 * this com.googlecode.mgwt.ui.client.widget will change the legend accordingly. Also if the order of layers change, this com.googlecode.mgwt.ui.client.widget will change
 * accordingly.
 *
 * @author Pieter De Graef
 *
 * @author Dosi Bingov
 *
 */
public class MobileLegendPanelView implements IsWidget {

	private final MapPresenter mapPresenter;

	private final VerticalPanel contentPanel;

	/**
	 * Create a legend that displays all layer legends of a map.
	 *
	 * @param mapPresenter
	 *            The map to display a legend com.googlecode.mgwt.ui.client.widget for.
	 */
	public MobileLegendPanelView(MapPresenter mapPresenter) {
		this.mapPresenter = mapPresenter;
		this.contentPanel = new VerticalPanel();

		// Add all layers (if there are any):
		for (int i = mapPresenter.getLayersModel().getLayerCount() - 1 ; i >= 0 ; i--) {
			addLayer(mapPresenter.getLayersModel().getLayer(i));
		}

		// Keep track of new layers being added or layers being removed. Change the legend accordingly:
		mapPresenter.getEventBus().addMapCompositionHandler(new MapCompositionHandler() {

			public void onLayerRemoved(LayerRemovedEvent event) {
				removeLayer(event.getLayer());
			}

			public void onLayerAdded(LayerAddedEvent event) {
				addLayer(event.getLayer());
			}
		});

		// Keep track of layer order within the LayersModel:
		mapPresenter.getEventBus().addLayerOrderChangedHandler(new LayerOrderChangedHandler() {

			public void onLayerOrderChanged(LayerOrderChangedEvent event) {
				int fromIndex = event.getFromIndex();
				Widget widget = contentPanel.getWidget(fromIndex);
				contentPanel.remove(fromIndex);
				contentPanel.insert(widget, event.getToIndex());
			}
		});
	}

	// ------------------------------------------------------------------------
	// IsWidget implementation:
	// ------------------------------------------------------------------------

	@Override
	public Widget asWidget() {
		return contentPanel;
	}

	// ------------------------------------------------------------------------
	// Protected methods:
	// ------------------------------------------------------------------------

	/**
	 * Add a layer to the legend drop down panel.
	 * 
	 * @param layer
	 *            The layer who's legend to add to the drop down panel.
	 * @return success or not.
	 */
	protected boolean addLayer(Layer layer) {
		int index = getLayerIndex(layer);
		if (index < 0) {
			contentPanel.add(new LayerLegendPanel(mapPresenter.getEventBus(), layer));
			return true;
		}
		return false;
	}
	
	/**
	 * Remove a layer from the drop down content panel again.
	 * 
	 * @param layer
	 *            The layer to remove.
	 * @return success or not.
	 */
	protected boolean removeLayer(Layer layer) {
		int index = getLayerIndex(layer);
		if (index >= 0) {
			contentPanel.remove(index);
			return true;
		}
		return false;
	}

	// ------------------------------------------------------------------------
	// Private methods:
	// ------------------------------------------------------------------------

	private int getLayerIndex(Layer layer) {
		for (int i = 0; i < contentPanel.getWidgetCount(); i++) {
			LayerLegendPanel layerPanel = (LayerLegendPanel) contentPanel.getWidget(i);
			if (layerPanel.getLayer() == layer) {
				return i;
			}
		}
		return -1;
	}
}