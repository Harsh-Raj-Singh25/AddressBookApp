package com.addressBook.service;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}