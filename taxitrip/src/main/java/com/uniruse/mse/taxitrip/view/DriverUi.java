/**
 * 
 */
package com.uniruse.mse.taxitrip.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * Driver info view.
 * 
 * @author gundev
 *
 */
public class DriverUi extends JFrame {

	private JButton showDrivers = new JButton("Show All Drivers");
	private JButton alfaDrivers = new JButton("Driver with Alfa Romeo");
	private JButton carsDrivedBy = new JButton("Cars drived by Milen");
	private JTable table = new JTable();
	private static final long serialVersionUID = 1L;

	/**
	 * Sets the size and the title of the window.
	 */
	public DriverUi() {
		setBounds(600, 50, 754, 495);
		setTitle("Driver information");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}

	/**
	 * Initialize ui component.
	 */
	private void init() {

		Container container = getContentPane();
		JPanel btnPanel = new JPanel();
		btnPanel.add(showDrivers);
		btnPanel.add(alfaDrivers);
		btnPanel.add(carsDrivedBy);
		JPanel infoPanel = new JPanel();

		infoPanel.add(table);
		container.add(btnPanel, BorderLayout.WEST);
		container.add(infoPanel, BorderLayout.SOUTH);

	}

	/**
	 * Adds event listener which manages the server events.
	 * 
	 * @param eventListener
	 *            is given listener
	 */
	public void addEventListener(ActionListener eventListener) {
		showDrivers.addActionListener(eventListener);
		alfaDrivers.addActionListener(eventListener);
	}

	/**
	 * @return the showDrivers
	 */
	public JButton getShowDrivers() {
		return showDrivers;
	}

	/**
	 * @param showDrivers
	 *            the showDrivers to set
	 */
	public void setShowDrivers(JButton showDrivers) {
		this.showDrivers = showDrivers;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the alfaDrivers
	 */
	public JButton getAlfaDrivers() {
		return alfaDrivers;
	}

	/**
	 * @param alfaDrivers
	 *            the alfaDrivers to set
	 */
	public void setAlfaDrivers(JButton alfaDrivers) {
		this.alfaDrivers = alfaDrivers;
	}

	/**
	 * @return the carsDrivedBy
	 */
	public JButton getCarsDrivedBy() {
		return carsDrivedBy;
	}

	/**
	 * @param carsDrivedBy
	 *            the carsDrivedBy to set
	 */
	public void setCarsDrivedBy(JButton carsDrivedBy) {
		this.carsDrivedBy = carsDrivedBy;
	}
}
