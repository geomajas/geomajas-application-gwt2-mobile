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
package org.geomajas.quickstart.mobile.client.activity.mapper.phone;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import org.geomajas.quickstart.mobile.client.MobileAppFactory;
import org.geomajas.quickstart.mobile.client.activity.FeatureInfoActivity;
import org.geomajas.quickstart.mobile.client.activity.LegendActivity;
import org.geomajas.quickstart.mobile.client.activity.MobileMapActivity;
import org.geomajas.quickstart.mobile.client.place.FeatureInfoPlace;
import org.geomajas.quickstart.mobile.client.place.LegendPlace;
import org.geomajas.quickstart.mobile.client.place.MapPlace;
import org.geomajas.quickstart.mobile.client.view.tst.FormsActivity;
import org.geomajas.quickstart.mobile.client.view.tst.FormsPlace;

/**
 * @author Dosi Bingov
 * 
 */
public class PhoneActivityMapper implements ActivityMapper {

	private final MobileAppFactory mobileAppFactory;

	public PhoneActivityMapper(MobileAppFactory mobileAppFactory) {
		this.mobileAppFactory = mobileAppFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof MapPlace) {
			return new MobileMapActivity(mobileAppFactory);
		}

		if (place instanceof LegendPlace) {
			return new LegendActivity(mobileAppFactory);
		}

		if (place instanceof FeatureInfoPlace) {
			return new FeatureInfoActivity(mobileAppFactory);
		}

		if (place instanceof FormsPlace) {
			return new FormsActivity(mobileAppFactory);
		}

		return new MobileMapActivity(mobileAppFactory);
	}
}
