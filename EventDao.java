package com.cybage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybage.bean.Event;
import com.cybage.bean.User;

public class EventDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/db?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USERS_SQL = "INSERT INTO event" + "  (name, venue, price, category) VALUES "
			+ " (?, ?, ?,?);";

	private static final String SELECT_USER_BY_ID = "select event_id,name,venue,price,organizer_id,category from event where event_id =?";
	private static final String SELECT_ALL_USERS = "select * from event";
	private static final String DELETE_USERS_SQL = "delete from event where event_id = ?;";
	private static final String UPDATE_USERS_SQL = "update event set name = ?,venue= ?, price =?, category = ? where event_id = ?;";

	public EventDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertEvent(Event event) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, event.getName());
			preparedStatement.setString(2, event.getVenue());
			preparedStatement.setInt(3, event.getPrice());
			preparedStatement.setString(4, event.getCategory());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Event selectUser(int event_id) {
		Event event = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, event_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String venue = rs.getString("venue");
				String category = rs.getString("category");
				int price = rs.getInt("price");
				event = new Event(event_id, name, venue, price , category);
//				System.out.println(user);
			}
//			System.out.println(rs);
		} catch (SQLException e) {
			printSQLException(e);
		}
		return event;
	}

	public List<Event> selectAllEvents() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Event> events = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int event_id = rs.getInt("event_id");
				String name = rs.getString("name");
				String venue = rs.getString("venue");
				String category = rs.getString("category");
				int price = rs.getInt("price");
				events.add(new Event(event_id, name, venue, price, category));
			}
//			System.out.println(Arrays.toString(users));
//			for(User u : users) {
//				System.out.println(u);
//			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return events;
	}

	public boolean deleteEvent(int event_id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, event_id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateEvent(Event event) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, event.getName());
			statement.setString(2, event.getVenue());
			statement.setString(3, event.getCategory());
			statement.setInt(4, event.getPrice());
			statement.setInt(5, event.getEvent_id());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}