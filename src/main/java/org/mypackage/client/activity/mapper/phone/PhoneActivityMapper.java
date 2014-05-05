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
package org.mypackage.client.activity.mapper.phone;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import org.mypackage.client.MobileAppFactory;
import org.mypackage.client.activity.LegendActivity;
import org.mypackage.client.activity.MobileMapActivity;
import org.mypackage.client.place.LegendPlace;
import org.mypackage.client.place.MapPlace;

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

		return new MobileMapActivity(mobileAppFactory);
	}
}
