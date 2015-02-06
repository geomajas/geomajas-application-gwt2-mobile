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
package org.geomajas.quickstart.mobile.client.view.tst;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;

/**
 * TODO.
 *
 * @author Dosi Bingov
 */
public class DetailActivity extends MGWTAbstractActivity {

  private final DetailView detailView;
  private final String eventId;

  public DetailActivity(DetailView detailView, String eventId) {
	  this.detailView = detailView;
	  this.eventId = eventId;
  }

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {
	  addHandlerRegistration(detailView.getMainButton().addTapHandler(new TapHandler() {
		  @Override
		  public void onTap(TapEvent tapEvent) {
			  eventBus.fireEvent(new ShowMasterEvent(eventId));
		  }
	  }));
	  addHandlerRegistration(detailView.getBackbutton().addTapHandler(new TapHandler() {
		  @Override
		  public void onTap(TapEvent tapEvent) {

		  }
	  }));
  }

}
