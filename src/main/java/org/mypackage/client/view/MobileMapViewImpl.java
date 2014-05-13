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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapRecognizer;
import com.googlecode.mgwt.ui.client.dialog.SlideUpPanel;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.Geometry;
import org.geomajas.gwt.client.map.RenderSpace;
import org.geomajas.gwt2.client.GeomajasServerExtension;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.geomajas.gwt2.client.map.feature.FeatureMapFunction;
import org.geomajas.gwt2.client.map.feature.ServerFeatureService;
import org.geomajas.gwt2.client.map.layer.FeaturesSupported;
import org.geomajas.gwt2.widget.client.featureselectbox.event.FeatureClickedEvent;
import org.geomajas.hammergwt.client.event.EventType;
import org.geomajas.hammergwt.client.event.NativeHammerEvent;
import org.geomajas.hammergwt.client.handler.HammerHandler;
import org.geomajas.hammergwt.client.impl.HammerGWT;
import org.mypackage.client.map.GeomajasMap;
import org.mypackage.client.map.MapHammerController;
import org.mypackage.client.widget.feature.FeatureInfoSlideUpView;
import org.mypackage.client.widget.zoom.MobileZoomView;
import org.mypackage.client.widget.zoom.MobileZoomViewImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link org.mypackage.client.view.MobileMapView} .
 *
 * @author Dosi Bingov
 */
public class MobileMapViewImpl implements MobileMapView {
	protected Button legendButton;
	protected HTML title;
	protected GeomajasMap geomajasMap;
	private MobileZoomView zoomControl;

	public MobileMapViewImpl() {
		geomajasMap = new GeomajasMap();
		title = new HTML();
		legendButton = new Button();
		legendButton.setSmall(true);
		legendButton.getElement().getStyle().setOpacity(0.6);
		legendButton.getElement().getStyle().setRight(2, Style.Unit.PX);
		legendButton.getElement().getStyle().setTop(2, Style.Unit.PX);
		zoomControl = new MobileZoomViewImpl();
		zoomControl.asWidget().getElement().getStyle().setLeft(2, Style.Unit.PX);
		zoomControl.asWidget().getElement().getStyle().setTop(2, Style.Unit.PX);
		geomajasMap.getMapPresenter().getWidgetPane().add(zoomControl.asWidget());
		geomajasMap.getMapPresenter().getWidgetPane().add(legendButton);
	}

	@Override
	public Widget asWidget() {
		return geomajasMap.asWidget();
	}

	@Override
	public HasText getLegendButtonText() {
		return legendButton;
	}

	@Override
	public HasTapHandlers getLegendButton() {
		return legendButton;
	}

	@Override
	public GeomajasMap getMap() {
		return geomajasMap;
	}

	@Override
	public MobileZoomView getZoomControl() {
		return zoomControl;
	}

}
