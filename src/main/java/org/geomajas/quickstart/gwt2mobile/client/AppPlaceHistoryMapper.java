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
package org.geomajas.quickstart.gwt2mobile.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import org.geomajas.quickstart.gwt2mobile.client.place.FeatureInfoPlace;
import org.geomajas.quickstart.gwt2mobile.client.place.LegendPlace;
import org.geomajas.quickstart.gwt2mobile.client.place.MapPlace;
import org.geomajas.quickstart.gwt2mobile.client.view.tst.FormsPlace;

/**
 * History mapper.
 *
 * @author Dosi Bingov
 * 
 */
@WithTokenizers({ MapPlace.MapPlaceTokenizer.class, LegendPlace.Tokenizer.class, FeatureInfoPlace.Tokenizer.class,
		FormsPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
