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
/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.geomajas.quickstart.mobile.client.widget;

import com.google.gwt.resources.client.CssResource;
import com.googlecode.mgwt.ui.client.theme.base.CarouselCss;

/**
 * TODO.
 *
 * @author Dosi Bingov
 */
public interface CarouselCssSlide extends CarouselCss {

	@CssResource.ClassName("mgwt-Carousel")
	String carousel();

	@CssResource.ClassName("mgwt-Carousel-Scroller")
	String carouselScroller();

	@CssResource.ClassName("mgwt-Carousel-Container")
	String carouselContainer();

	@CssResource.ClassName("Carousel-Indicator-Container")
	String indicatorContainer();

	@CssResource.ClassName("Carousel-Indicator")
	String indicator();

	@CssResource.ClassName("Carousel-Indicator-active")
	String indicatorActive();

	@CssResource.ClassName("mgwt-Carousel-Holder")
	String carouselHolder();

}
