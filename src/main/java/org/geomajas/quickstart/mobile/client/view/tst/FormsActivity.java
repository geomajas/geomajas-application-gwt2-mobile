/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.geomajas.quickstart.mobile.client.view.tst;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import org.geomajas.quickstart.mobile.client.MobileAppFactory;

public class FormsActivity extends DetailActivity {

	private final MobileAppFactory clientFactory;

	public FormsActivity(MobileAppFactory clientFactory) {
		super(clientFactory.geFormsView(), "nav");
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		FormsView view = clientFactory.geFormsView();
		view.getMainButtonText().setText("Nav");
		view.getBackbuttonText().setText("UI");
		view.getHeader().setText("Forms");

		panel.setWidget(view);
	}

}
