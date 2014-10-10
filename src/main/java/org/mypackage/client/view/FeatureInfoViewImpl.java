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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.FormListEntry;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.WidgetList;
import org.geomajas.hammergwt.client.HammerGwt;
import org.geomajas.hammergwt.client.HammerTime;
import org.geomajas.hammergwt.client.event.EventType;
import org.geomajas.hammergwt.client.event.NativeHammerEvent;
import org.geomajas.hammergwt.client.handler.HammerHandler;
import org.geomajas.hammergwt.client.option.GestureOptions;

/**
 * default implementation of {@link FeatureInfoView}.
 *
 * @author Dosi Bingov
 *
 */
public class FeatureInfoViewImpl implements FeatureInfoView {
	protected LayoutPanel main;
	private ScrollPanel content;
	int pageIndex = 1;

	private DragLeftHandler dragLeftHandler;

	private DragRightHandler dragRightHandler;

	private DragDownHandler dragDownHandler;

	protected HeaderPanel headerPanel;
	protected HeaderButton goBack;
	protected HeaderButton goRight;

	private WidgetList recordsList;
	private HTML title;

	public FeatureInfoViewImpl() {
		main = new LayoutPanel();
		headerPanel = new HeaderPanel();


		//setScrollingEnabledX(false);
		title = new HTML();
		title.setHeight("100%");
		title.setWidth("100%");
		title.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		title.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
		title.getElement().getStyle().setColor("white");

		recordsList = new WidgetList();
		recordsList.setRound(true);
		content = new ScrollPanel();

		HammerTime hammerTime = HammerGwt.create(content);

		HammerGwt.on(hammerTime, new HammerHandler() {

			@Override
			public void onHammerEvent(NativeHammerEvent event) {
				event.stopDetect();
				event.preventDefault();
				event.preventNativeDefault();
				event.stopPropagation();

				if (event.getType() == EventType.DRAGRIGHT) {
					if (null != dragRightHandler) {
						dragRightHandler.onDragRight();
					}

				}

				if (event.getType() == EventType.DRAGLEFT) {
					if (null != dragLeftHandler) {
						dragLeftHandler.onDragLeft();
					}
				}

			}
		}, EventType.DRAGRIGHT, EventType.DRAGLEFT);

		hammerTime.setOption(GestureOptions.DRAG_MIN_DISTANCE, 10);

		HammerGwt.on(HammerGwt.create(headerPanel.asWidget()), new HammerHandler() {

			@Override
			public void onHammerEvent(NativeHammerEvent event) {
				event.stopDetect();
				event.preventDefault();
				event.preventNativeDefault();
				event.stopPropagation();


				if (event.getType() == EventType.DRAGDOWN) {
					if (null != dragDownHandler) {
						dragDownHandler.onDragDown();
					}
				}


			}
		}, EventType.DRAGDOWN);



		goBack = new HeaderButton();
		goBack.setText("Map");
		goBack.setBackButton(true);

		headerPanel.setLeftWidget(goBack);

		//headerPanel.setCenterWidget(headerLabel);

		content.setWidget(recordsList);


		main.add(headerPanel);
		main.add(content);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void clear() {
		recordsList.clear();
	}

	@Override
	public void setDragDownHandler(DragDownHandler dragDownHandler) {
		this.dragDownHandler = dragDownHandler;
	}

	@Override
	public HasTapHandlers getBackButton() {
		return goBack;
	}

	@Override
	public void addGridLine(String label, String data) {
		recordsList.add(new FormListEntry(label, new Label(data)));
	}

	@Override
	public void renderGrid() {
		//touchBoard.getElement().setInnerHTML(recordsList.asWidget().getElement().getString());
	}

	@Override
	public void setTitle(String title1) {
		title.setHTML(title1);
		recordsList.add(title);
		title.getElement().getParentElement().getStyle().setBackgroundColor("black");
	}

	@Override
	public void setDragLeftHandler(DragLeftHandler dragLeftHanfler) {
		this.dragLeftHandler = dragLeftHanfler;
	}

	@Override
	public void setDragRightHandler(DragRightHandler dragRightHandler) {
		this.dragRightHandler = dragRightHandler;
	}


}
