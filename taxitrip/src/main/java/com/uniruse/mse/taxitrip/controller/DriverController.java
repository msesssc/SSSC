/**
 * 
 */
package com.uniruse.mse.taxitrip.controller;

import com.uniruse.mse.taxitrip.model.Driver;
import com.uniruse.mse.taxitrip.view.DriverUi;

/**
 * Driver info controller.
 * 
 * @author gundev
 *
 */
public class DriverController {
	private Driver driver;
	private DriverUi driverUi;

	public DriverController(Driver driver, DriverUi driverUi) {
		this.driver = driver;
		this.driverUi = driverUi;
		UserJButtonListener eventListener = new UserJButtonListener(this);
		driverUi.addEventListener(eventListener);
	}

	/**
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * @param driver
	 *            the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	/**
	 * @return the driverUi
	 */
	public DriverUi getDriverUi() {
		return driverUi;
	}

	/**
	 * @param driverUi
	 *            the driverUi to set
	 */
	public void setDriverUi(DriverUi driverUi) {
		this.driverUi = driverUi;
	}
}
