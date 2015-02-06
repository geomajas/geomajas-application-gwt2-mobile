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

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;

import com.googlecode.mgwt.mvp.client.history.HistoryHandler;
import com.googlecode.mgwt.mvp.client.history.HistoryObserver;
import com.googlecode.mgwt.ui.client.MGWT;
import org.geomajas.quickstart.mobile.client.animation.ActionNames;
import org.geomajas.quickstart.mobile.client.event.ActionEvent;
import org.geomajas.quickstart.mobile.client.event.ViewChangeEvent;
import org.geomajas.quickstart.mobile.client.place.FeatureInfoPlace;
import org.geomajas.quickstart.mobile.client.place.LegendPlace;
import org.geomajas.quickstart.mobile.client.place.MapPlace;
import org.geomajas.quickstart.mobile.client.view.tst.FormsPlace;

/**
 * History observer fot this mobile app.
 *
 * @author Dosi Bingov
 */
public class AppHistoryObserver implements HistoryObserver {

	@Override
	public void onPlaceChange(Place place, HistoryHandler handler) {

	}

	@Override
	public void onHistoryChanged(Place place, HistoryHandler handler) {

	}

	@Override
	public void onAppStarted(Place place, HistoryHandler historyHandler) {
		onPhoneNav(place, historyHandler);
	}

	@Override
	public HandlerRegistration bind(EventBus eventBus, final HistoryHandler historyHandler) {

		HandlerRegistration viewChangeHandler = eventBus.addHandler(ViewChangeEvent.getType(),
				new ViewChangeEvent.Handler() {

					@Override
					public void onViewChange(ViewChangeEvent event) {

						ViewChangeEvent.VIEW view = event.getView();

						Place place = null;

						switch (view) {
							case LEGEND:
								place = new LegendPlace();
								break;

							case FEATURE_INFO:
								place = new FeatureInfoPlace();
								break;

							case TEST_VIEW:
								place = new FormsPlace();
								break;

							case MOBILE_MAP:
							default:
								place = new MapPlace();
								break;
						}

						/*if (MGWT.getOsDetection().isTablet()) {

							historyHandler.replaceCurrentPlace(place);
							historyHandler.goTo(place, true);
						} else {*/
							historyHandler.goTo(place);
						//}
					}



		});

		HandlerRegistration actionHandler = ActionEvent.register(eventBus, ActionNames.BACK, new ActionEvent.Handler() {

			@Override
			public void onAction(ActionEvent event) {

				History.back();

			}
		});

		HandlerRegistration register = ActionEvent.register(eventBus,
				ActionNames.ANIMATION_END, new ActionEvent.Handler() {

			@Override
			public void onAction(ActionEvent event) {
				if (MGWT.getOsDetection().isPhone()) {
					History.back();
				} else {
					//If tablet goto tablet place
					//historyHandler.goTo(new TabletPlave(), true);
				}

			}
		});

		HandlerRegistrationCollection col = new HandlerRegistrationCollection();
		col.addHandlerRegistration(register);
		col.addHandlerRegistration(actionHandler);
		col.addHandlerRegistration(viewChangeHandler);
		return col;
	}

	private void onPhoneNav(Place place, HistoryHandler historyHandler) {
		if (!(place instanceof MapPlace)) {

			historyHandler.replaceCurrentPlace(new MapPlace());

			//historyHandler.pushPlace(new AnimationPlace());

		} else {

		}
	}


}
