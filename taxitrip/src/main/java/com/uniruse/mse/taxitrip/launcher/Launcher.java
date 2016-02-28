/**
 * 
 */
package com.uniruse.mse.taxitrip.launcher;

import com.uniruse.mse.taxitrip.controller.DriverController;
import com.uniruse.mse.taxitrip.model.Driver;
import com.uniruse.mse.taxitrip.view.DriverUi;

/**
 * Launcher class.
 * 
 * @author gundev
 *
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DriverUi view = new DriverUi();
		Driver model = new Driver();
		DriverController controller = new DriverController(model, view);
		view.setVisible(true);
	}

}
