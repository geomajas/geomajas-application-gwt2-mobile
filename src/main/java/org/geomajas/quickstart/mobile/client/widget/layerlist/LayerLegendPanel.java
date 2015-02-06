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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.annotation.Api;
import org.geomajas.gwt2.client.event.LayerHideEvent;
import org.geomajas.gwt2.client.event.LayerShowEvent;
import org.geomajas.gwt2.client.event.LayerVisibilityHandler;
import org.geomajas.gwt2.client.event.LayerVisibilityMarkedEvent;
import org.geomajas.gwt2.client.map.MapEventBus;
import org.geomajas.gwt2.client.map.layer.Layer;
import org.geomajas.gwt2.client.map.layer.LegendUrlSupported;
import org.geomajas.gwt2.client.widget.map.MapWidgetResource;

/**
 * <p>
 * A com.googlecode.mgwt.ui.client.widget that displays the legend for a single layer.
 * It provides the possibility to toggle that layer's visibility
 * through a CheckBox.
 * </p>
 * <p>
 * If the layer should become invisible because the map has zoomed in or out beyond the allowed scale range (for that
 * layer), the CheckBox in this com.googlecode.mgwt.ui.client.widget will automatically become disabled.
 * It is no use to start marking a layer visible
 * when it won't appear anyway.
 * </p>
 * 
 * @author Pieter De Graef
 * @since 2.0.0
 */
@Api
public class LayerLegendPanel extends Composite {

	/**
	 * UI binder definition for the
	 * {@link org.geomajas.quickstart.mobile.client.widget.layerlist.LayerLegendPanel}
	 * com.googlecode.mgwt.ui.client.widget.
	 * 
	 * @author Pieter De Graef
	 */
	interface LayerLegendPanelUiBinder extends UiBinder<Widget, LayerLegendPanel> {
	}

	private static final LayerLegendPanelUiBinder UI_BINDER = GWT.create(LayerLegendPanelUiBinder.class);

	private final Layer layer;

	@UiField
	protected CheckBox visibilityToggle;

	@UiField
	protected Label title;

	@UiField
	protected FlexTable legendTable;

	/**
	 * Create a new legend panel for a single layer. This panel
	 * 
	 * @param eventBus
	 *            Map event bus. Must be the bus from the same map that holds the layer.
	 * @param layer
	 *            The layer to display in this legend com.googlecode.mgwt.ui.client.widget.
	 */
	public LayerLegendPanel(MapEventBus eventBus, final Layer layer) {
		((MapWidgetResource) GWT.create(MapWidgetResource.class)).css().ensureInjected();

		this.layer = layer;
		initWidget(UI_BINDER.createAndBindUi(this));

		// Apply the layer onto the GUI:
		title.setText(layer.getTitle());
		visibilityToggle.setValue(layer.isMarkedAsVisible());

		// React to layer visibility events:
		eventBus.addLayerVisibilityHandler(new LayerVisibilityHandler() {

			public void onShow(LayerShowEvent event) {
				visibilityToggle.setEnabled(true);
			}

			public void onHide(LayerHideEvent event) {
				// If a layer hides while it is marked as visible, it means it has gone beyond it's allowed scale range.
				// If so, disable the CheckBox. It's no use to try to mark the layer as visible anyway:
				if (layer.isMarkedAsVisible()) {
					visibilityToggle.setEnabled(false);
				}
			}

			public void onVisibilityMarked(LayerVisibilityMarkedEvent event) {
				visibilityToggle.setValue(layer.isMarkedAsVisible());
			}
		}, this.layer);

		visibilityToggle.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (visibilityToggle.isEnabled()) {
					LayerLegendPanel.this.layer.setMarkedAsVisible(!layer.isMarkedAsVisible());
					visibilityToggle.setEnabled(true); // Works because JavaScript is single threaded...
				}
			}
		});

		// Add the legend:
		if (layer instanceof LegendUrlSupported) {
			Image image = new Image(((LegendUrlSupported) layer).getLegendImageUrl());
			final int row = legendTable.insertRow(legendTable.getRowCount());
			legendTable.addCell(row);
			legendTable.setWidget(row, 0, image);
		}
	}

	/**
	 * Return the target layer for this legend panel.
	 * 
	 * @return The layer who's styles are displayed within this panel.
	 */
	public Layer getLayer() {
		return layer;
	}
}