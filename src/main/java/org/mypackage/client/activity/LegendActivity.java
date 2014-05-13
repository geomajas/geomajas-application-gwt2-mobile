package org.mypackage.client.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.mypackage.client.MobileAppFactory;
import org.mypackage.client.animation.ActionNames;
import org.mypackage.client.event.ActionEvent;
import org.mypackage.client.presenter.LegendPresenter;
import org.mypackage.client.view.cellist.LayerCellRecord;
import org.mypackage.client.view.LegendView;
import org.mypackage.client.widget.layerlist.LayerListPresenter;
import org.mypackage.client.widget.layerlist.LayerListPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Legend activity and presenter.
 *
 * @author Dosi Bingov
 */
public class LegendActivity extends MGWTAbstractActivity implements LegendPresenter {

  private final LegendView legendView;
  private final String eventId;
  private LayerListPresenter layerListPresenter;


	public LegendActivity(MobileAppFactory mobileAppFactory) {
		this.legendView = mobileAppFactory.getLegendView();
		MapPresenter mapPresenter = mobileAppFactory.getMapView().getMap().getMapPresenter();

		this.layerListPresenter =
				new LayerListPresenterImpl(mapPresenter.getEventBus(), mobileAppFactory.getlayerListView());
		this.eventId = "nav";

		layerListPresenter.renderLayerRecords(mapPresenter.getLayersModel());
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

	  /*records = getLegendRecords();
	  legendView.renderRecords(records);*/
/*

	  addHandlerRegistration(legendView.getList().addCellSelectedHandler(new CellSelectedHandler() {

		  @Override
		  public void onCellSelected(CellSelectedEvent event) {
			  int index = event.getIndex();

			  legendView.setSelectedIndex(oldIndex, false);
			  legendView.setSelectedIndex(index, true);
			  oldIndex = index;

			  //TODO: fire event to navigate to record details
			  // UIEntrySelectedEvent.fire(eventBus, items.get(index).getEntry());

		  }
	  }));
*/

	  legendView.getHeader().setText("Layers");
	  legendView.getBackbuttonText().setText("Map");
	  legendView.setLayerListView(layerListPresenter.getLayerList());

	  panel.setWidget(legendView);

  }
}
