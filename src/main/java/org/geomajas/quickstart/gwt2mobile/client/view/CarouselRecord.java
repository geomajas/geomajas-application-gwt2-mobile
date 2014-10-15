package org.geomajas.quickstart.gwt2mobile.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.FormListEntry;
import com.googlecode.mgwt.ui.client.widget.WidgetList;



public class CarouselRecord  implements IsWidget{
		private WidgetList recordsList;
		private HTML title;

		public CarouselRecord(String titleText) {
			//setScrollingEnabledX(false);
			title = new HTML();
			title.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
			title.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
			title.getElement().getStyle().setColor("white");

			title.setText(titleText);

			recordsList = new WidgetList();
			recordsList.setRound(true);
			//setWidget(recordsList);
			recordsList.add(title);
			title.getElement().getParentElement().getStyle().setBackgroundColor("black");

		}

		public void addGridLine(String label, String data) {
			recordsList.add(new FormListEntry(label, new Label(data)));
		}

	@Override
	public Widget asWidget() {
		return recordsList;
	}
}