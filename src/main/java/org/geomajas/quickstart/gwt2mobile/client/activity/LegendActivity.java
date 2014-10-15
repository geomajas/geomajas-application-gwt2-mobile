package org.geomajas.quickstart.gwt2mobile.client.activity;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.quickstart.gwt2mobile.client.MobileAppFactory;
import org.geomajas.quickstart.gwt2mobile.client.animation.ActionNames;
import org.geomajas.quickstart.gwt2mobile.client.event.ActionEvent;
import org.geomajas.quickstart.gwt2mobile.client.presenter.LegendPresenter;
import org.geomajas.quickstart.gwt2mobile.client.view.LegendView;
import org.geomajas.quickstart.gwt2mobile.client.widget.layerlist.LayerListPresenter;
import org.geomajas.quickstart.gwt2mobile.client.widget.layerlist.LayerListPresenterImpl;

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

	/*  addHandlerRegistration(legendView.getTestButton().addTapHandler(new TapHandler() {

		  @Override
		  public void onTap(TapEvent event) {
			  ViewChangeEvent.fire(eventBus, ViewChangeEvent.VIEW.TEST_VIEW);
			  // eventBus.fireEvent(new ShowMasterEvent(eventId));

		 }
	  }));*/

	  /*records = getLegendRecords();
	  legendView.renderRecords(records);*/
/*

	  addHandlerRegistration(legendView.getRecordsList().addCellSelectedHandler(new CellSelectedHandler() {

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
