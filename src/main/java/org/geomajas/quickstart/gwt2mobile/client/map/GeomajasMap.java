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

package org.geomajas.quickstart.gwt2mobile.client.map;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.gwt2.client.GeomajasImpl;
import org.geomajas.gwt2.client.GeomajasServerExtension;
import org.geomajas.gwt2.client.event.MapInitializationEvent;
import org.geomajas.gwt2.client.event.MapInitializationHandler;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.gwt2.client.widget.DefaultMapWidget;
import org.geomajas.gwt2.client.widget.MapLayoutPanel;

import java.util.logging.Logger;

/**
 * Full screen map with touch devices support.
 *
 * @author Dosi Bingov
 */
public class GeomajasMap implements IsWidget {
    private Logger remoteLogger = Logger.getLogger("");

	private static final String TMS_BASE_URL = "http://apps.geomajas.org/geoserver/gwc/service/tms/1.0.0/demo_world%3Asimplified_country_borders@EPSG%3A900913@png";

	private static final String WMS_BASE_URL = "http://apps.geomajas.org/geoserver/demo_world/ows";

	//private ResizeLayoutPanel resizeLayoutPanel;

	private MapLayoutPanel layout;

	private static final String EPSG = "EPSG:4326";

	private MapPresenter mapPresenter;

	private final MapHammerController mapHammerController;

	public GeomajasMap() {
		layout = new MapLayoutPanel();

		// Create the MapPresenter and add to the layout:
		mapPresenter = GeomajasImpl.getInstance().createMapPresenter();


		mapPresenter.setSize(300,300);
		//mapPresenter = GeomajasImpl.getInstance().createMapPresenter(configuration, 480, 480);

		mapPresenter.getEventBus().addMapInitializationHandler(new MyMapInitializationHandler());

		layout.setPresenter(mapPresenter);

		mapHammerController = new MapHammerController();

		mapPresenter.setMapController(mapHammerController);


		// Initialize the map server side
		GeomajasServerExtension.getInstance().initializeMap(mapPresenter, "app", "mapLayerVisibility", new DefaultMapWidget[] {});


		initializeMap();

	}

	public void setMapTapHandler(MapHammerController.HammerTapLocationHandler tapHandler) {
		mapHammerController.setTapHandler(tapHandler);
	}

	private void initializeMap() {




	}

	public MapPresenter getMapPresenter() {
		return mapPresenter;
	}

	@Override
	public Widget asWidget() {
		ResizeLayoutPanel resizeLayoutPanel = new ResizeLayoutPanel();
		resizeLayoutPanel.setSize("100%", "100%");

		resizeLayoutPanel.setWidget(layout);
		return resizeLayoutPanel;
	}

	public void setMapController() {

	}


	public void setSize(int width, int height) {
		mapPresenter.setSize(width, height);
	}

	/**
	 * Map initialization handler.
	 */
	private class MyMapInitializationHandler implements MapInitializationHandler {

		public void onMapInitialized(MapInitializationEvent event) {
			//MapPresenter mapPres = event.getMapPresenter();

			// Create the configuration objects:
//			TileConfiguration tileConfig = new TileConfiguration(256, 256, new Coordinate(-180, -90), mapPresenter.getViewPort());
//			TmsLayerConfiguration layerConfig = new TmsLayerConfiguration();
//			layerConfig.setBaseUrl(TMS_BASE_URL);
//			layerConfig.setFileExtension(".png");
//
//			// Now create the layer and add it to the map:
//			final TmsLayer tmsLayer = TmsClient.getInstance().createLayer("Countries", tileConfig, layerConfig);
//			mapPresenter.getLayersModel().addLayer(tmsLayer);

			/*TmsClient.getInstance().getTileMap("d/proxy?url=" + TMS_BASE_URL, new Callback<TileMapInfo, String>() {

				@Override
				public void onSuccess(TileMapInfo result) {
					*//*TmsLayer layer = TmsClient.getInstance().createLayer(result);
					layer.getTileConfiguration().setLimitXYByTileLevel(true);
					mapPresenter.getLayersModel().addLayer(layer);
					//move layer immedeately down so openstreet map stays top layer
					mapPresenter.getLayersModel().moveLayerDown(layer);
					mapPresenter.getLayersModelRenderer().setAnimated(layer, false);*//*
				}

				@Override
				public void onFailure(String reason) {
					Window.alert("We're very sorry, but something went wrong: " + reason);
				}
			});
*/
		}
	}
}