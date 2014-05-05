package org.mypackage.client.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;
import org.mypackage.client.MobileAppFactory;
import org.mypackage.client.animation.ActionNames;
import org.mypackage.client.event.ActionEvent;
import org.mypackage.client.presenter.LegendPresenter;
import org.mypackage.client.view.LegendView;

/**
 * Legend activity and presenter.
 *
 * @author Dosi Bingov
 */
public class LegendActivity extends MGWTAbstractActivity implements LegendPresenter {

  private final LegendView legendView;
  private final String eventId;

	public LegendActivity(MobileAppFactory mobileAppFactory) {
		this.legendView = mobileAppFactory.getLegendView();
		this.eventId = "nav";

	}

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {

    addHandlerRegistration(legendView.getBackbutton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
		  ActionEvent.fire(eventBus, ActionNames.BACK);
		  // eventBus.fireEvent(new ShowMasterEvent(eventId));

      }
    }));

	  legendView.getHeader().setText("Legend view");
	  legendView.getBackbuttonText().setText("Map");

	  panel.setWidget(legendView);

  }
}
