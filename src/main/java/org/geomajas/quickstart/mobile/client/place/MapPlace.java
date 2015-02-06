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
package org.geomajas.quickstart.mobile.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Dosi Bingov
 *
 */
public class MapPlace extends Place {

	/**
	 * TODO.
	 */
	public static class MapPlaceTokenizer implements PlaceTokenizer<MapPlace> {

		@Override
		public MapPlace getPlace(String token) {
			return new MapPlace();
		}

		@Override
		public String getToken(MapPlace place) {
			return "";
		}

	}

	@Override
	public int hashCode() {
		return 3;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other == null) {
			return false;
		}

		if (other instanceof MapPlace) {
			return true;
		}
		return false;
	}
}
