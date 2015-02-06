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
package org.geomajas.quickstart.mobile.client.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * View changed event Should be fired when the view is changed.
 *
 * @author Dosi Bingov
 */
public class ViewChangeEvent extends Event<ViewChangeEvent.Handler> {
	private VIEW view;

	/**
	 * TODO.
	 *
	 * @author Dosi Bingov
	 */
	public enum VIEW {
		MOBILE_MAP, LEGEND, FEATURE_INFO, TEST_VIEW
	}

	/**
	 * TODO.
	 *
	 * @author Dosi Bingov
	 */
	public interface Handler {
		void onViewChange(ViewChangeEvent event);
	}

	private static final Type<Handler> TYPE = new Type<Handler>();

	public static void fire(EventBus eventBus, VIEW view) {
		eventBus.fireEvent(new ViewChangeEvent(view));
	}

	public static HandlerRegistration register(EventBus eventBus, Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected ViewChangeEvent(VIEW view) {
		this.view = view;
	}

	@Override
	protected void dispatch(ViewChangeEvent.Handler handler) {
		handler.onViewChange(this);
	}

	public VIEW getView() {
		return view;
	}

	public static Type<Handler> getType() {
		return TYPE;
	}
}
