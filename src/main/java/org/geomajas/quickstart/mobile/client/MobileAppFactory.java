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
package org.geomajas.quickstart.mobile.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import org.geomajas.quickstart.mobile.client.view.FeatureInfoView;
import org.geomajas.quickstart.mobile.client.view.LegendView;
import org.geomajas.quickstart.mobile.client.view.MobileMapView;
import org.geomajas.quickstart.mobile.client.view.tst.FormsView;
import org.geomajas.quickstart.mobile.client.widget.feature.FeatureInfoPresenter;
import org.geomajas.quickstart.mobile.client.widget.feature.FeatureInfoSlideUpView;
import org.geomajas.quickstart.mobile.client.widget.layerlist.LayerListView;

/**
 * Factory interface for this mobile application.
 *
 * if {@link MobileAppFactory} is created by GWT.create all view implementations can be changed easily
 * with GWT deffered binding (based on the user agent for example).
 *
 * @author Dosi Bingov
 * 
 */
public interface MobileAppFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	/**
	 * @return
	 */
	MobileMapView getMapView();

 	LegendView getLegendView();

	LayerListView getlayerListView();

	FeatureInfoSlideUpView getFeatureInfoSlideView();

	FeatureInfoView getFeatureInfoView();

	FeatureInfoPresenter getFeaturePresenter();

	FormsView geFormsView();

}
