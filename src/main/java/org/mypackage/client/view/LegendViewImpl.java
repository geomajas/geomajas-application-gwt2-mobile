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

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.WidgetList;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;
import org.mypackage.client.view.cellist.LayerCell;
import org.mypackage.client.view.cellist.LayerCellRecord;
import org.mypackage.client.widget.layerlist.LayerListRecordViewImpl;
import org.mypackage.client.widget.layerlist.LayerListView;

import java.util.List;

public class LegendViewImpl implements LegendView {

	protected LayoutPanel main;
	protected ScrollPanel scrollPanel;
	protected HeaderPanel headerPanel;
	protected HeaderButton headerBackButton;
	protected HeaderButton headerMainButton;
/*	private CellList<LayerCellRecord> cellListWithHeader;*/
	protected HTML title;



	public LegendViewImpl() {
		main = new LayoutPanel();

		scrollPanel = new ScrollPanel();

		headerPanel = new HeaderPanel();
		title = new HTML();
		headerPanel.setCenterWidget(title);
		headerBackButton = new HeaderButton();
		headerBackButton.setBackButton(true);

		headerMainButton = new HeaderButton();
		headerMainButton.setRoundButton(true);

		headerPanel.setLeftWidget(headerBackButton);

		/*cellListWithHeader = new CellList<LayerCellRecord>(new LayerCell<LayerCellRecord>() {

			@Override
			public String getDisplayString(LayerCellRecord model) {
				return model.getName();
			}

			@Override
			public boolean isLayerVisible(LayerCellRecord model) {
				return model.isVisible();
			}

			@Override
			public boolean canBeSelected(LayerCellRecord model) {
				return true;
			}
		});

		cellListWithHeader.setRound(true);*/

		scrollPanel.setScrollingEnabledX(false);

		main.add(headerPanel);
		main.add(scrollPanel);
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasText getHeader() {
		return title;
	}

	@Override
	public HasText getBackbuttonText() {
		return headerBackButton;
	}

	@Override
	public HasTapHandlers getBackbutton() {
		return headerBackButton;
	}

	/*@Override
	public void renderRecords(List<LayerCellRecord> records) {
		cellListWithHeader.render(records);
	}

	@Override
	public void setSelectedIndex(int index, boolean selected) {
		cellListWithHeader.setSelectedIndex(index, selected);
	}

	@Override
	public HasCellSelectedHandler getList() {
		return cellListWithHeader;
	}
*/
	@Override
	public void setLayerListView(LayerListView layerListView) {
		scrollPanel.setWidget(layerListView);
	}

}
