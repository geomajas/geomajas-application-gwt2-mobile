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
package org.mypackage.client.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;
import org.mypackage.client.MobileAppFactory;
import org.mypackage.client.event.ActionEvent;
import org.mypackage.client.animation.ActionNames;
import org.mypackage.client.event.ViewChangeEvent;
import org.mypackage.client.presenter.MobileMapPresenter;
import org.mypackage.client.view.MobileMapView;

/**
 * Mobile map activity and presenter
 */
public class MobileMapActivity extends MGWTAbstractActivity implements MobileMapPresenter {

  private final MobileMapView mapView;
  private final String eventId;

	public MobileMapActivity(MobileAppFactory mobileAppFactory) {
		this.mapView = mobileAppFactory.getMapView();
		this.eventId = "nav";

	}

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {

    addHandlerRegistration(mapView.getLegendButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
		  ViewChangeEvent.fire(eventBus, ViewChangeEvent.VIEW.LEGEND);
      }

    }));

	  mapView.getHeader().setText("Map view");
	  mapView.getLegendButtonText().setText("Legend");

	  panel.setWidget(mapView);
  }
}
