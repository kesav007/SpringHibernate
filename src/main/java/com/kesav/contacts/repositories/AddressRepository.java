package com.kesav.contacts.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kesav.contacts.entities.Address;

public class AddressRepository {

	private final DataSource dataSource;
	
	public AddressRepository() {
		Context context = null;
		try {
			try {
				context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/trainingdb");
			} finally {
				context.close();
			}
		} catch (NamingException e) {
			throw new RuntimeException();
		}
	}

	public void init() throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			try {
				statement.execute("create table address (id integer generated by default as identity primary key, street varchar(255), city varchar(255), state varchar(255), zip varchar(255))");
			} 
			catch (Exception e) {
				System.out.println("Unable to create address table");
			}
			finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public Address find(Long id) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			try {
				ResultSet resultSet = statement.executeQuery("SELECT * FROM ADDRESS WHERE ID = " + id);
				try {
					if(!resultSet.next()) {
						return null;
					}
					else {
						return unmarshal(resultSet);
					}
				} finally {
					resultSet.close();
				}
			}
			finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}
	public List<Address> findAll() throws SQLException {
		List<Address> address = new ArrayList<Address>(); 
		Connection connection = dataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			try {
				ResultSet resultSet = statement.executeQuery("SELECT * FROM ADDRESS");
				try {
					while(resultSet.next()) {						
						address.add(unmarshal(resultSet));
					}
					return address;
				} finally {
					resultSet.close();
				}
			}
			finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	private static Address unmarshal(ResultSet resultSet) throws SQLException {
		Address address = new Address();
		address.setId(resultSet.getLong("ID"));
		address.setStreet(resultSet.getString("STREET"));
		address.setCity(resultSet.getString("CITY"));
		address.setState(resultSet.getString("STATE"));
		address.setZip(resultSet.getString("ZIP"));		
		return address;
	}

	public void create(Address address) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			try {
				String insertQuery = "INSERT INTO ADDRESS (STREET, CITY, STATE, ZIP) VALUES ('"
						+ address.getStreet() + "', '"
						+ address.getCity() + "', '"
						+ address.getState()+ "', '"
						+ address.getZip()
						+ "')";
				statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
				ResultSet generatedKeys = statement.getGeneratedKeys();
				try {
					if(generatedKeys.next()) {
						address.setId(generatedKeys.getLong("ID"));
					}
				} finally {
					generatedKeys.close();
				}
			}
			finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}
	
	public void update(Address address) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			try {
				statement.executeUpdate("UPDATE ADDRESS SET "
						+ " STREET = '" + address.getStreet() + "',"
						+ " CITY = '" + address.getCity() + "',"
						+ " STATE= '" + address.getState() + "',"
						+ " ZIP= '" + address.getZip()
						+"' WHERE ID = " + address.getId()
						);
			}
			finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public void delete(Address address) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			try {
				statement.executeUpdate("DELETE FROM ADDRESS WHERE ID = " + address.getId());
			}
			finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}
}