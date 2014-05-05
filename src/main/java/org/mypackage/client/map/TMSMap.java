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

package org.mypackage.client.map;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.Callback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.gwt2.client.GeomajasImpl;
import org.geomajas.gwt2.client.event.MapInitializationEvent;
import org.geomajas.gwt2.client.event.MapInitializationHandler;
import org.geomajas.gwt2.client.map.MapConfiguration;
import org.geomajas.gwt2.client.map.MapConfigurationImpl;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.gwt2.client.map.layer.tile.TileConfiguration;
import org.geomajas.gwt2.client.widget.DefaultMapWidget;
import org.geomajas.gwt2.client.widget.MapLayoutPanel;
import org.geomajas.plugin.tms.client.TmsClient;
import org.geomajas.plugin.tms.client.configuration.TileMapInfo;
import org.geomajas.plugin.tms.client.layer.TmsLayer;
import org.geomajas.plugin.tms.client.layer.TmsLayerConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Full screen map with touch devices support.
 *
 * @author Dosi Bingov
 */
public class TMSMap implements IsWidget {
	private MapLayoutPanel layout;

    private Logger remoteLogger = Logger.getLogger("");

	private static final String TMS_BASE_URL = "http://apps.geomajas.org/geoserver/gwc/service/tms/1.0.0/" +
			"demo_world%3Asimplified_country_borders@EPSG%3A4326@png";

	private ResizeLayoutPanel resizeLayoutPanel;

	private static final String EPSG = "EPSG:4326";

	private MapPresenter mapPresenter;

	public TMSMap() {
		// Define the layout:
		resizeLayoutPanel = new ResizeLayoutPanel();
	    /*layout = new MapLayoutPanel();
		resizeLayoutPanel.setWidget(layout);*/
		resizeLayoutPanel.setSize("500px", "500px");

		//<SRS>EPSG:4326</SRS><BoundingBox minx="2.580000" miny="50.685000" maxx="5.920000" maxy="51.500000"/>


		// Initialize the map, and return the layout:
		//GeomajasServerExtension.getInstance().initializeMap(mapPresenter, "gwt-app", "mapOsm");

		// Create the mapPresenter and add an InitializationHandler:
		//No server extension

		MapConfiguration configuration = new MapConfigurationImpl();
		configuration.setCrs(EPSG, MapConfiguration.CrsType.DEGREES);
		//configuration.setMaxBounds(new Bbox(17646.52218435664, 21958.60926379636, 250000.000, 300000.000));
		configuration.setMaxBounds(new Bbox(-180, -90, 360, 180));
		//<BoundingBox minx="17646.52218435664" miny="21958.60926379636" maxx="297198.78807110013" maxy="301510.87515053985"/>
		List<Double> resolutions = new ArrayList<Double>();
		resolutions.add(0.703125);
		resolutions.add(0.3515625);
		resolutions.add(0.17578125);
		resolutions.add(0.087890625);
		resolutions.add(0.0439453125);
		resolutions.add(0.703125 * 0.75);
		resolutions.add(0.3515625 * 0.75);
		resolutions.add(0.17578125 * 0.75);
		resolutions.add(0.087890625 * 0.75);
		resolutions.add(0.0439453125 * 0.75);


	/*	resolutions.add(1092.0010386200918);
		resolutions.add(546.0005193100459);
		resolutions.add(273.00025965502294);*/


		configuration.setResolutions(resolutions);
		configuration.setHintValue(MapConfiguration.RENDERER_TYPE,
				MapConfiguration.RendererType.CANVAS);

		//TODO: see what is wrong with canvas bigger sizes = make full size possible
		mapPresenter = GeomajasImpl
				.getInstance().createMapPresenter(configuration, 600, 300, new MyMapInitializationHandler(),
	DefaultMapWidget.ZOOM_CONTROL);
		//mapPresenter.getConfiguration().setHintValue(MapConfiguration.ANIMATION_TIME, 4000);


		mapPresenter.setMapController(new MapHammerController());



		//mapPresenter.getEventBus().addMapInitializationHandler(new MyMapInitializationHandler());
		initializeMap();

		//GeomajasServerExtension.getInstance().initializeMap(mapPresenter, "gwt-app", "mapEmpty");

	    resizeLayoutPanel.add(mapPresenter.asWidget());
	}


//	private void initializeHammer() {
//		HammerTime hammerTime = HammerGWT.on(mapPresenter.asWidget(), new HammerableHandler(),
//				EventType.PINCH, EventType.TOUCH, EventType.DRAG, EventType.DRAGSTART, EventType.DRAGEND);
//
//		hammerTime.setOption(GestureOptions.PREVENT_DEFAULT, true);
//	}

	private void initializeMap() {
		// First clear the panel and the map:
		//	mapPresenter.getLayersModel().clear();

		// Now create a WMS layer and add it to the map:

		// layerConfig.setMaximumResolution(Double.MAX_VALUE);
		//layerConfig.setMinimumResolution(2.1457672119140625E-5);

		//GeomajasServerExtension.getInstance().initializeMap(mapPresenter, "gwt-app", "mapOsm");

		String tmsURL = "http://apps.geomajas.org/geoserver/gwc/service/tms/1.0.0/osm@Belgiumlambert@png";

		String tmsURL2 = "http://apps.geomajas.org/geoserver/gwc/service/tms/1.0.0/demo_world%3Asimplified_country_borders@EPSG%3A4326@png";
		TmsClient.getInstance().getTileMap("d/proxy?url=" + tmsURL2, new Callback<TileMapInfo, String>() {

			@Override
			public void onSuccess(TileMapInfo result) {
				TmsLayer layer = TmsClient.getInstance().createLayer(result);
				mapPresenter.getLayersModel().addLayer(layer);
				mapPresenter.getLayersModelRenderer().setAnimated(layer, false);
			}

			@Override
			public void onFailure(String reason) {
				Window.alert("We're very sorry, but something went wrong: " + reason);
			}
		});

				// First clear the panel and the map:
				/*mapPresenter.getLayersModel().clear();

				// Create the configuration objects:
				TileConfiguration tileConfig = new TileConfiguration(256, 256, new Coordinate(-180, -90), mapPresenter.getViewPort());
				TmsLayerConfiguration layerConfig = new TmsLayerConfiguration();
				layerConfig.setBaseUrl(TMS_BASE_URL);
				layerConfig.setFileExtension(".png");

				// Now create the layer and add it to the map:
				final TmsLayer tmsLayer = TmsClient.getInstance().createLayer("Countries", tileConfig, layerConfig);
				mapPresenter.getLayersModel().addLayer(tmsLayer);*/

	}

	@Override
	public Widget asWidget() {
		return resizeLayoutPanel;
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
		}
	}
}