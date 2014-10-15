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
package org.geomajas.quickstart.gwt2mobile.client.widget.feature;

import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.dialog.SlideUpPanel;

public interface FeatureInfoSlideUpView extends IsWidget {
	void show();

	void hide();

	void setLabel(String label);

	SlideUpPanel getPane();

	ButtonBase getLeftButton();

	ButtonBase getRightButton();
}
