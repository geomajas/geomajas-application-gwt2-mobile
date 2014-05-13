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
package org.mypackage.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.mypackage.client.map.MapHammerController;
import org.mypackage.client.view.LegendView;
import org.mypackage.client.view.LegendViewImpl;
import org.mypackage.client.view.MobileMapView;
import org.mypackage.client.view.MobileMapViewImpl;
import org.mypackage.client.widget.feature.FeatureInfoSlideUpView;
import org.mypackage.client.widget.feature.FeatureInfoSlideUpViewImpl;
import org.mypackage.client.widget.layerlist.LayerListView;
import org.mypackage.client.widget.layerlist.LayerListViewImpl;

/**
 * @author Dosi Bingov
 *
 */
public class MobileAppFactoryImpl implements MobileAppFactory {

	private EventBus eventBus;
	private PlaceController placeController;

	private MobileMapView mapView;

	private LegendView legendView;

	private LayerListView layerListView;

	private FeatureInfoSlideUpView featureInfoSlideUpView;


	public MobileAppFactoryImpl() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public MobileMapView getMapView() {
		if (mapView == null) {
			mapView = new MobileMapViewImpl();
		}

		return mapView;
	}

	@Override
	public LegendView getLegendView() {
		if (legendView == null) {
			legendView = new LegendViewImpl();
		}
		return legendView;
	}

	@Override
	public LayerListView getlayerListView() {
		if (layerListView == null) {
			layerListView = new LayerListViewImpl();
		}

		return layerListView;
	}

	@Override
	public FeatureInfoSlideUpView getFeatureInfoSlideView() {
		if (featureInfoSlideUpView == null) {
			featureInfoSlideUpView = new FeatureInfoSlideUpViewImpl();
		}

		return featureInfoSlideUpView;
	}

}
