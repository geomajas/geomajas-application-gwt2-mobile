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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.MCheckBox;
import org.geomajas.gwt2.client.map.layer.Layer;

/**
 * Default implementation of {@link LayerListRecordView }.
 *
 * @author Dosi Bingov.
 */
public class LayerListRecordViewImpl implements LayerListRecordView {
	private HorizontalPanel layout;
	private MCheckBox visibilityToggle;
	private Layer layer;

	public LayerListRecordViewImpl(Layer layer) {
		this.layer = layer;
		layout = new HorizontalPanel();
		visibilityToggle = new MCheckBox();
		visibilityToggle.setValue(layer.isMarkedAsVisible());
		visibilityToggle.setImportant(true);

		Label layerLabel = new Label(layer.getTitle());
		layerLabel.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
		layerLabel.getElement().getStyle().setLineHeight(25, Style.Unit.PX);

		layout.add(visibilityToggle);
		layout.add(layerLabel);

	}

	@Override
	public Widget asWidget() {
		return layout;
	}

	@Override
	public MCheckBox getToggleButton() {
		return visibilityToggle;
	}

	public Layer getLayer() {
		return layer;
	}
}
