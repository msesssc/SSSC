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
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Driver info view.
 * 
 * @author gundev
 *
 */
public class DriverUi extends JFrame {

	private JButton showDrivers = new JButton("Show All Drivers");
	private JButton showClients = new JButton("Show All Clients");
	private JButton showCars = new JButton("Show All Cars");
	private JTable table = new JTable();
	private static final long serialVersionUID = 1L;

	/**
	 * Sets the size and the title of the window.
	 */
	public DriverUi() {
		setBounds(600, 50, 500, 500);
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
		btnPanel.add(showClients);
		btnPanel.add(showCars);
		JPanel infoPanel = new JPanel();

		// infoPanel.add(table, BorderLayout.CENTER);
		// infoPanel.add(table.getTableHeader(), BorderLayout.NORTH);
		// infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		infoPanel.add(scrollPane);
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
		showClients.addActionListener(eventListener);
		showCars.addActionListener(eventListener);
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
	 * @return the showClients
	 */
	public JButton getShowClients() {
		return showClients;
	}

	/**
	 * @return the showCars
	 */
	public JButton getShowCars() {
		return showCars;
	}
}
