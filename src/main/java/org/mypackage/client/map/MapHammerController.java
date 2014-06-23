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
package org.mypackage.client.map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.GestureChangeEvent;
import com.google.gwt.event.dom.client.GestureEndEvent;
import com.google.gwt.event.dom.client.GestureStartEvent;
import com.google.gwt.event.dom.client.HumanInputEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import org.geomajas.geometry.Coordinate;
import org.geomajas.gwt.client.map.RenderSpace;
import org.geomajas.gwt2.client.controller.MapController;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.gwt2.client.map.View;
import org.geomajas.gwt2.client.map.ViewPort;
import org.geomajas.gwt2.client.map.feature.Feature;
import org.geomajas.gwt2.client.map.layer.FeaturesSupported;
import org.geomajas.hammergwt.client.event.EventType;
import org.geomajas.hammergwt.client.event.NativeHammerEvent;
import org.geomajas.hammergwt.client.event.PointerType;
import org.geomajas.hammergwt.client.handler.HammerHandler;
import org.geomajas.hammergwt.client.handler.HammerTapHandler;
import org.geomajas.hammergwt.client.impl.HammerGWT;
import org.geomajas.hammergwt.client.impl.HammerTime;
import org.geomajas.hammergwt.client.impl.option.GestureOption;
import org.geomajas.hammergwt.client.impl.option.GestureOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Map controller responsible for touch and pan (uses hammer gwt for its events).
 *
 * @author Dosi Bingov
 */
public class MapHammerController implements MapController, HammerHandler {
	private Logger remoteLogger = Logger.getLogger("");

	private double beginResolution;

	private Coordinate dragOrigin;

	private int currentResIndex;

	private MapPresenter mapPresenter;

	private HammerTapLocationHandler tapHandler;

	private int pixelBuffer;


	public MapHammerController() {
		pixelBuffer = 7; //7px buffer
	}

	@Override
	public void onActivate(MapPresenter presenter) {
		mapPresenter = presenter;

		HammerTime hammerTime = HammerGWT.createInstance(presenter.asWidget());

		HammerGWT.on(hammerTime, this,
				EventType.PINCH, EventType.TOUCH, EventType.DRAG, EventType.DRAGSTART, EventType.TAP, EventType.HOLD);

		hammerTime.setOption(GestureOptions.PREVENT_DEFAULT, true);

		hammerTime.setOption(GestureOptions.HOLD_TIMEOUT, 2);
	}

	@Override
	public void onDeactivate(MapPresenter presenter) {
		//TODO remove hammer events

	}

	public void  setTapHandler(HammerTapLocationHandler tapHandler) {
		this.tapHandler = tapHandler;
	}

	@Override
	public boolean isRightMouseButton(HumanInputEvent<?> event) {
		return false;
	}

	@Override
	public void onDoubleClick(DoubleClickEvent event) {
		//do nothing
	}

	@Override
	public void onGestureChange(GestureChangeEvent event) {
		//do nothing
	}

	@Override
	public void onGestureEnd(GestureEndEvent event) {
		//do nothing
	}

	@Override
	public void onGestureStart(GestureStartEvent event) {
		//do nothing
	}

	@Override
	public Coordinate getLocation(HumanInputEvent<?> event, RenderSpace renderSpace) {
		return null;
	}

	@Override
	public Element getTarget(HumanInputEvent<?> event) {
		return null;
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		//do nothing
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		//do nothing
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		//do nothing
	}

	@Override
	public void onMouseOver(MouseOverEvent event) {
		//do nothing
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		//do nothing
	}

	@Override
	public void onMouseWheel(MouseWheelEvent event) {
		//do nothing
	}

	@Override
	public void onTouchCancel(TouchCancelEvent event) {
		//do nothing
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		//do nothing
	}

	@Override
	public void onTouchMove(TouchMoveEvent event) {
		//do nothing
	}

	@Override
	public void onTouchStart(TouchStartEvent event) {
		//do nothing
	}

	@Override
	public void onHammerEvent(NativeHammerEvent event) {
		event.preventDefault();
		event.preventNativeDefault();
		//log(event);

		switch (event.getType()) {

			case TAP:
				if (null != tapHandler) {
					tapHandler.onLocationTap(event, getLocation(event,  RenderSpace.WORLD));
				}

				break;

			case TOUCH:
				currentResIndex =
						mapPresenter.getViewPort().getResolutionIndex(mapPresenter.getViewPort().getResolution());
				beginResolution = mapPresenter.getViewPort().getResolution();
				break ;

			case PINCH:
				double resolution = beginResolution / event.getScale();


				if (mapPresenter.getViewPort().getResolutionIndex(resolution) == 0) {
					//do nothing because max or min resolution is reached
					break;
				}

				int midX = 0;
				int midY = 0;

				if (event.getNativeEvent().getTouches().length() == 2) {
					int x1 = event.getNativeEvent().getTouches().get(0).getClientX();
					int x2 = event.getNativeEvent().getTouches().get(1).getClientX();

					int y1 = event.getNativeEvent().getTouches().get(0).getClientY();
					int y2 = event.getNativeEvent().getTouches().get(1).getClientY();

					midX = (x1 + x2) / 2;

					midY = (y1 + y2) / 2;

					remoteLogger.log(Level.SEVERE, "touch1 x=" + x1 +
							" touch1 y=" + y1 + " :::: touch2 x=" + x2 + " touch2 y=" + y2);
				}


				Coordinate worldCoordinate = mapPresenter.getViewPort().getTransformationService()
						.transform(new Coordinate(midX, midY), RenderSpace.SCREEN, RenderSpace.WORLD);

				View view = new View(calculatePosition(resolution, worldCoordinate), resolution);
				mapPresenter.getViewPort().applyView(view);

				break;

			case DRAGSTART:
				dragOrigin = getLocation(event, RenderSpace.SCREEN);
				break;

			case DRAG:
				updateView(event);
				break;

			case HOLD:
				event.getDirection();
				break;

			default:
				break;

		}
	}

	private Coordinate getLocation(NativeHammerEvent event, RenderSpace renderSpace) {
		switch (renderSpace) {
			case WORLD:
				Coordinate screen = getLocation(event, RenderSpace.SCREEN);
				return mapPresenter.getViewPort().getTransformationService()
						.transform(screen, RenderSpace.SCREEN, RenderSpace.WORLD);
			case SCREEN:
			default:

				if (event.getPointerType() == PointerType.MOUSE) {
					double offsetX = event.getRelativeX();
					double offsetY = event.getRelativeY();

					return new Coordinate(offsetX, offsetY);
					//center between two fingers
				} else if (event.getPointerType() == PointerType.TOUCH) {
					return new Coordinate(event.getPageX(), event.getPageY());
				} else {
					return new Coordinate(event.getPageX(), event.getPageY());
				}
		}
	}

	private void updateView(NativeHammerEvent event) {
		Coordinate end = getLocation(event, RenderSpace.SCREEN);
		Coordinate beginWorld = mapPresenter.getViewPort().getTransformationService()
				.transform(dragOrigin, RenderSpace.SCREEN, RenderSpace.WORLD);
		Coordinate endWorld = mapPresenter.getViewPort().getTransformationService()
				.transform(end, RenderSpace.SCREEN, RenderSpace.WORLD);

		double x = mapPresenter.getViewPort().getPosition().getX() + beginWorld.getX() - endWorld.getX();
		double y = mapPresenter.getViewPort().getPosition().getY() + beginWorld.getY() - endWorld.getY();
		mapPresenter.getViewPort().applyPosition(new Coordinate(x, y));
		dragOrigin = end;
	}

	/**
	 * Calculate the target position should there be a rescale point. The idea is that after zooming in or out, the
	 * mouse cursor would still lie at the same position in world space.
	 */
	protected Coordinate calculatePosition(double resolution, Coordinate rescalePoint) {
		ViewPort viewPort = mapPresenter.getViewPort();
		Coordinate position = viewPort.getPosition();
		double factor = viewPort.getResolution() / resolution;
		double dX = (rescalePoint.getX() - position.getX()) * (1 - 1 / factor);
		double dY = (rescalePoint.getY() - position.getY()) * (1 - 1 / factor);
		return new Coordinate(position.getX() + dX, position.getY() + dY);
	}

	public interface HammerTapLocationHandler {
		void onLocationTap(NativeHammerEvent event, Coordinate tappedLocation);
	}
}
