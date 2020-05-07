package com;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Appointment {

	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readAppointments() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// prepare the html table to be displayed
			output = "<table border='1'><tr><th>Appointment Number</th><th>Appointment Type</th><th>Doctor ID</th><th>Doctor Name</th><th>Hospital Name</th><th>Hospital Name</th>"
					+ "<th>Description</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from appointments";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);

			// iterate through the row in the result set
			while (rs.next()) {
				String appointmentID = Integer.toString(rs.getInt("appointmentID"));
				String appointmentNumber = rs.getString("appointmentNumber");
				String appointmentType = rs.getString("appointmentType");
				String docID = rs.getString("docID");
				String docName = rs.getString("docName");
				String hospitalName = rs.getString("hospitalName");
				String Desc = rs.getString("Desc");

				// Add into the html table
				output += "<tr><td><input id='hidAppointmentIDUpdate' name='hidAppointmentIDUpdate' type='hidden' value='"
						+ appointmentID + "'>" + appointmentNumber + "</td>";
				output += "<td>" + appointmentType + "</td>";
				output += "<td>" + docID + "</td>";
				output += "<td>" + docName + "</td>";
				output += "<td>" + hospitalName + "</td>";
				output += "<td>" + Desc + "</td>";

				// Buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-appointmentid='"
						+ appointmentID + "'>" + "</td></tr>";
			}
			// jquery data parameters to store the ID ="data-item-id"

			con.close();

			// complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the appointments.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String insertAppointment(String appName, String appType, String docId, String dName, String hosName,
			String desc) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into appointments(`appointmentID`,`appointmentNumber`,`appointmentType`,`docID`,`docName`,'hospitalName','Desc')"
					+ " values (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, appName);
			preparedStmt.setString(3, appType);
			preparedStmt.setString(4, docId);
			preparedStmt.setString(5, dName);
			preparedStmt.setString(6, hosName);
			preparedStmt.setString(7, desc);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newAppointments = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the appointment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateAppointment(String appName, String appType, String docId, String dName, String hosName,
			String desc) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE appointments SETappointmentNumber=?,appointmentType=?,docID=?,docName=?,hospitalName=?,Desc=? WHERE appointmentID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, appName);
			preparedStmt.setString(3, appType);
			preparedStmt.setString(4, docId);
			preparedStmt.setString(5, dName);
			preparedStmt.setString(6, hosName);
			preparedStmt.setString(7, desc);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newAppointments = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the appointment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteAppointment(String appointmentID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from appointments where appointmentID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(appointmentID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newAppointments = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the appointment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateAppointment(String string, String string2, String string3, String string4, String string5,
			Object object, String string6) {
		
		return null;
	}

}
