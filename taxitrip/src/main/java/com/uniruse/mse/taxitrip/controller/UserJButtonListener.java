/**
 * 
 */
package com.uniruse.mse.taxitrip.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.ibm.db2.jcc.DB2Driver;

/**
 * @author gundev
 *
 */
public class UserJButtonListener implements ActionListener {
	private DriverController controller;
	private static final String SELECT_ALL_DRIVERS_STMT = "SELECT * FROM driver";
	private static final String SELECT_ALL_CLIENTS_STMT = "SELECT * FROM client";
	private static final String SELECT_ALL_CARS_STMT = "SELECT * FROM car";

	/**
	 * Initialize object.
	 * 
	 * @param controller
	 */
	public UserJButtonListener(DriverController controller) {
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == controller.getDriverUi().getShowDrivers()) {

			try {
				ResultSet result = getResult(SELECT_ALL_DRIVERS_STMT);
				controller.getDriverUi().getTable().setModel(buildTableModel(result));
				controller.getDriverUi().repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == controller.getDriverUi().getShowClients()) {
			try {
				ResultSet result = getResult(SELECT_ALL_CLIENTS_STMT);
				controller.getDriverUi().getTable().setModel(buildTableModel(result));
				controller.getDriverUi().repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == controller.getDriverUi().getShowCars()) {
			try {
				ResultSet result = getResult(SELECT_ALL_CARS_STMT);
				controller.getDriverUi().getTable().setModel(buildTableModel(result));
				controller.getDriverUi().repaint();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Gets query result;
	 * 
	 * @param statement
	 * @return result set
	 * @throws SQLException
	 *             ex
	 */
	private ResultSet getResult(String statement) throws SQLException {
		DriverManager.registerDriver(new DB2Driver());

		Connection db2Conn = DriverManager.getConnection("jdbc:db2://localhost:50000/gundev", "db2admin", "123456");
		db2Conn.createStatement().execute("SET CURRENT SCHEMA gundev");
		PreparedStatement selectTestDataStatement = db2Conn.prepareStatement(statement);
		ResultSet result = selectTestDataStatement.executeQuery();
		return result;
	}

	/**
	 * Builds table model from query result.
	 * 
	 * @param rs
	 *            result
	 * @return model
	 * @throws SQLException
	 *             exception
	 */
	private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}

	/**
	 * @return the controller
	 */
	public DriverController getController() {
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	public void setController(DriverController controller) {
		this.controller = controller;
	}

}
