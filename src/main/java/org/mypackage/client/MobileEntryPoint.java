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


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import org.mypackage.client.activity.mapper.phone.PhoneActivityMapper;
import org.mypackage.client.animation.mapper.PhoneAnimationMapper;
import org.mypackage.client.place.MapPlace;

/**
 * @author Dosi Bingov
 */
public class MobileEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		//RootLayoutPanel.get().add(new GeomajasMap().asWidget());
		loadMobile();
	}


	private void loadMobile() {
		//SuperDevModeUtil.showDevMode();
		MGWTSettings.ViewPort viewPort = new MGWTSettings.ViewPort();
		viewPort.setTargetDensity(MGWTSettings.ViewPort.DENSITY.MEDIUM);
		viewPort.setUserScaleAble(false).setMinimumScale(1.0).setMinimumScale(1.0).setMaximumScale(1.0);

		MGWTSettings settings = new MGWTSettings();
		settings.setViewPort(viewPort);
		settings.setIconUrl("logo.png");
		settings.setAddGlosToIcon(true);
		settings.setFullscreen(true);
		settings.setStartUrl("iphone-full.png");
		settings.setPreventScrolling(true);

		MGWT.applySettings(settings);

		final MobileAppFactory mobileAppFactory = new MobileAppFactoryImpl();

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);

		// very nasty workaround because GWT does not corretly support
		// @media
		//StyleInjector.inject(AppBundle.INSTANCE.css().getText());

		//if (MGWT.getOsDetection().isTablet()) {

		//} else {
		createPhoneDisplay(mobileAppFactory);

		//}
		AppHistoryObserver historyObserver = new AppHistoryObserver();

		MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(historyMapper, historyObserver);

		historyHandler.register(mobileAppFactory.getPlaceController(), mobileAppFactory.getEventBus(), new MapPlace());
		historyHandler.handleCurrentHistory();
	}

	private void createPhoneDisplay(MobileAppFactory mobileAppFactory) {
		AnimatableDisplay display = GWT.create(AnimatableDisplay.class);

		PhoneActivityMapper appActivityMapper = new PhoneActivityMapper(mobileAppFactory);

		PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();

		AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, mobileAppFactory.getEventBus());
		activityManager.setFireAnimationEvents(true);



		activityManager.setDisplay(display);

		RootLayoutPanel.get().add(display);



	}


}
