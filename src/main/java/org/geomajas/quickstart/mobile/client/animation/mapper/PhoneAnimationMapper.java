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
package org.geomajas.quickstart.mobile.client.animation.mapper;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import org.geomajas.quickstart.mobile.client.place.FeatureInfoPlace;
import org.geomajas.quickstart.mobile.client.place.LegendPlace;
import org.geomajas.quickstart.mobile.client.place.MapPlace;

/**
 * @author Dosi Bingov
 * 
 */
public class PhoneAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {

		if (oldPlace instanceof MapPlace && newPlace instanceof LegendPlace) {
			return Animation.FLIP;
		}

		if (oldPlace instanceof LegendPlace && newPlace instanceof MapPlace) {
			return Animation.FLIP_REVERSE;
		}

		if (oldPlace instanceof MapPlace && newPlace instanceof FeatureInfoPlace) {
			return Animation.SLIDE_UP;
		}

		if (oldPlace instanceof FeatureInfoPlace && newPlace instanceof MapPlace) {
			return Animation.SLIDE_UP_REVERSE;
		}

		return Animation.SLIDE;

	}
}
