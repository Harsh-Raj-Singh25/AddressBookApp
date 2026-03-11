package com.addressBook.service;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookDBService {

	// JDBC Connection Details
	private Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/address_book_service";
		return DriverManager.getConnection(url, "root", "Harsh@SQL12");
	}

	// UC 16: Retrieve all entries from DB
	public List<Contact> readData() {
		String sql = "SELECT * FROM contact";
		List<Contact> contactList = new ArrayList<>();

		try (Connection connection = this.getConnection(); Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				contactList.add(new Contact(resultSet.getString("first_name"), resultSet.getString("last_name"),
						resultSet.getString("address"), resultSet.getString("city"), resultSet.getString("state"),
						resultSet.getString("zip"), resultSet.getString("phone_number"), resultSet.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	// UC17 - update logic
	public int updateContactCity(String firstName, String newCity) {
		String sql = "UPDATE contact SET city = ? WHERE first_name = ?";

		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Use PreparedStatement to safely set values
			preparedStatement.setString(1, newCity);
			preparedStatement.setString(2, firstName);

			int result = preparedStatement.executeUpdate();
			return result; // Returns number of rows affected
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// UC18
	public List<Contact> getContactsByDateRange(String startDate, String endDate) {
		List<Contact> contactList = new ArrayList<>();
		// Use JDBC for CRUD operation with DB
		String sql = "SELECT * FROM contact WHERE date_added BETWEEN ? AND ?";

		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, startDate);
			preparedStatement.setString(2, endDate);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				contactList.add(new Contact(resultSet.getString("first_name"), resultSet.getString("last_name"),
						resultSet.getString("address"), resultSet.getString("city"), resultSet.getString("state"),
						resultSet.getString("zip"), resultSet.getString("phone_number"), resultSet.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	// UC 19
	public Map<String, Integer> getContactCountByCityOrState(String type) {
		// Determine column based on input (City or State)
		String column = type.equalsIgnoreCase("city") ? "city" : "state";
		String sql = "SELECT " + column + ", COUNT(*) as count FROM contact GROUP BY " + column;

		Map<String, Integer> countMap = new HashMap<>();

		// Use JDBC for CRUD operation with DB
		try (Connection connection = this.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				countMap.put(resultSet.getString(column), resultSet.getInt("count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countMap;
	}

	// Uc20
	public boolean addNewContact(Contact contact) {
		String sql = "INSERT INTO contact (first_name, last_name, address, city, state, zip, phone_number, email, date_added) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;

		try {
			connection = this.getConnection();
			// UC 20: Ensure DB Transaction is implemented
			connection.setAutoCommit(false);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, contact.getFirstName());
				preparedStatement.setString(2, contact.getLastName());
				preparedStatement.setString(3, contact.getAddress());
				preparedStatement.setString(4, contact.getCity());
				preparedStatement.setString(5, contact.getState());
				preparedStatement.setString(6, contact.getZip());
				preparedStatement.setString(7, contact.getPhoneNumber());
				preparedStatement.setString(8, contact.getEmail());
				preparedStatement.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));

				preparedStatement.executeUpdate();

				// Commit transaction if all steps succeed
				connection.commit();
				return true;
			} catch (SQLException e) {
				// Rollback if any part of the process fails
				if (connection != null)
					connection.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}