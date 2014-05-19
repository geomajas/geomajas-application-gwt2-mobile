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
package org.mypackage.client.view;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndHandler;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;
import org.geomajas.gwt2.client.map.attribute.Attribute;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.mypackage.client.widget.layerlist.LayerListView;

import java.util.List;
import java.util.Map;

public interface FeatureInfoView extends IsWidget{

	void clear();

	void setDragDownHandler(DragDownHandler dragDownHandler);

	interface DragDownHandler {
		void onDragDown();
	}

	HasTapHandlers getBackButton();

	void addGridLine(String label, String data);

	void renderGrid();

	void setTitle(String title);


	void setDragLeftHandler(DragLeftHandler dragLeftHanfler);

	void setDragRightHandler(DragRightHandler dragRightHandler);

	interface DragLeftHandler {
		void onDragLeft();
	}


	interface DragRightHandler {
		void onDragRight();
	}


}
