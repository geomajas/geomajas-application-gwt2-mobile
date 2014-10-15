package org.geomajas.quickstart.gwt2mobile.client.event;

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

	public enum VIEW {
		MOBILE_MAP, LEGEND, FEATURE_INFO, TEST_VIEW
	}

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
