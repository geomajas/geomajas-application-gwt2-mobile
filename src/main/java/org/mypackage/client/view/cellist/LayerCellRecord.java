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
package org.mypackage.client.view.cellist;


/**
 * @author Dosi Bingov
 * 
 */
public class LayerCellRecord {
	private String name;
	private boolean visible;

	public LayerCellRecord(String displayString, boolean visible) {
		this.name = displayString;
		this.visible = visible;

	}

	/**
	 * @return the layer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return if lmayer is visible
	 */
	public boolean isVisible() {
		return visible;
	}
}
