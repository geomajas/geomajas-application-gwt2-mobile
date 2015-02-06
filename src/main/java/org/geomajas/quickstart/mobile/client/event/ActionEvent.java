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
 * Action event.
 *
 * @author Dosi Bingov
 */
public class ActionEvent extends Event<ActionEvent.Handler> {

	/**
	 * TODO.
	 *
	 * @author Dosi Bingov
	 */
	public interface Handler {
		void onAction(ActionEvent event);
	}

	private static final Type<Handler> TYPE = new Type<Handler>();

	public static void fire(EventBus eventBus, String sourceName) {
		eventBus.fireEventFromSource(new ActionEvent(), sourceName);
	}

	public static HandlerRegistration register(EventBus eventBus, String sourceName, Handler handler) {
		return eventBus.addHandlerToSource(TYPE, sourceName, handler);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected ActionEvent() {

	}

	@Override
	protected void dispatch(ActionEvent.Handler handler) {
		handler.onAction(this);
	}

}
