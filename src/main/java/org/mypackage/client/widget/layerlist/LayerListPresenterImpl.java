package org.mypackage.client.widget.layerlist;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import org.geomajas.gwt2.client.event.LayerHideEvent;
import org.geomajas.gwt2.client.event.LayerShowEvent;
import org.geomajas.gwt2.client.event.LayerVisibilityHandler;
import org.geomajas.gwt2.client.event.LayerVisibilityMarkedEvent;
import org.geomajas.gwt2.client.map.MapEventBus;
import org.geomajas.gwt2.client.map.layer.Layer;
import org.geomajas.gwt2.client.map.layer.LayersModel;
import org.mypackage.client.MobileAppFactory;
import org.mypackage.client.view.cellist.LayerCellRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Layer List presenter.
 *
 * @author Dosi Bingov
 */
public class LayerListPresenterImpl implements LayerListPresenter {

	private LayerListView layerListView;

	private MapEventBus mapEventBus;

	public LayerListPresenterImpl(MapEventBus mapEventBus, MobileAppFactory mobileAppFactory) {
		this.layerListView = mobileAppFactory.getlayerListView();
		this.mapEventBus = mapEventBus;

	}

	@Override
	public void renderLayerRecords(LayersModel layerModel) {

		//TODO: we don't always want that.
		if(!layerListView.isListRendered()) {

			for (int i = layerModel.getLayerCount() - 1; i >= 0; i--) {
				addLayer(layerModel.getLayer(i));
			}

			layerListView.markRendered();
		}
	}

	@Override
	public LayerListView getLayerList() {
		return layerListView;
	}

	private void addLayer(Layer layer) {
		LayerListRecordView recordView = new LayerListRecordViewImpl(layer);
		layerListView.getList().add(recordView.asWidget());
		addLayerRecordEvents(recordView);
	}

	private void addLayerRecordEvents(final LayerListRecordView recordView) {
		// React to layer visibility events:
		//TODO:
		/*mapEventBus.addLayerVisibilityHandler(new LayerVisibilityHandler() {

			public void onShow(LayerShowEvent event) {
				recordView.getToggleButton().setValue(true);
			}

			public void onHide(LayerHideEvent event) {
				// If a layer hides while it is marked as visible, it means it has gone beyond it's allowed scale range.
				// If so, disable the CheckBox. It's no use to try to mark the layer as visible anyway:
				if (recordView.getLayer().isMarkedAsVisible()) {
					recordView.getToggleButton().setValue(false);
				}
			}

			public void onVisibilityMarked(LayerVisibilityMarkedEvent event) {
				recordView.getToggleButton().setValue(recordView.getLayer().isMarkedAsVisible());
			}
		}, recordView.getLayer());*/


		recordView.getToggleButton().addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				recordView.getLayer().setMarkedAsVisible(event.getValue());
			}
		});

	}

}
