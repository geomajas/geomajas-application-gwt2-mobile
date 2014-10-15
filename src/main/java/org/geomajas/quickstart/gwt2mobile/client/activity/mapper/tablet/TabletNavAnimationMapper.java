package org.geomajas.quickstart.gwt2mobile.client.activity.mapper.tablet;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;

/**
 * Tablet navigation animation mapper.
 */
public class TabletNavAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {

		return Animation.SLIDE;
	}

}
