package com.proquest.interview.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.proquest.interview.phonebook.Person;

/**
 * This class is just a utility class, you should not have to change anything
 * here
 * 
 * @author rconklin
 */
public class DatabaseUtil {

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
	}

	public static void initDB() {
		Connection cn = null;
		Statement stmt = null;

		try {
			cn = getConnection();
			stmt = cn.createStatement();
			// added DROP statement to make application and tests repeatable and stop 'object name already exists' exception. 
			stmt.execute("DROP SCHEMA PUBLIC CASCADE"); 
			stmt.execute("CREATE TABLE PHONEBOOK (NAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))");
			// These statements could be moved to createPeopleInDataBase or addPerson but then review running of application and tests!
			stmt.execute(
					"INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Chris Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')");
			stmt.execute(
					"INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Dave Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')");
			cn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(cn, stmt);
		}
	}

	public static void addPerson(Person newPerson) {
		Connection cn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) Values (?, ?, ?) ";

		try {
			cn = getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, newPerson.getName());
			pstmt.setString(2, newPerson.getPhoneNumber());
			pstmt.setString(3, newPerson.getAddress());
			pstmt.executeUpdate();
			cn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(cn, pstmt);
		}
	}

	public static Person findPerson(String firstName, String lastName) {
		Person personThatWasFound = null;
		
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String sql = "SELECT NAME, PHONENUMBER, ADDRESS FROM PHONEBOOK WHERE NAME = ? ";

		String searchName = firstName + " " + lastName;

		try {
			cn = getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, searchName);
			result = pstmt.executeQuery();

			if (result.next()) {
				System.out.println("*** " + searchName + " was found! ***");
				personThatWasFound = new Person(result.getString("NAME"), result.getString("PHONENUMBER"),
						result.getString("ADDRESS"));
				outputPersonToConsole(personThatWasFound);
			} else {
				System.out.println("*** " + searchName + " was NOT found! ***");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(cn, pstmt, result);
		}
		return personThatWasFound;
	}

	public static void findAllPeople() {
		Connection cn = null;
		Statement stmt = null;
		ResultSet result = null;

		try {
			cn = getConnection();
			stmt = cn.createStatement();
			result = stmt.executeQuery("SELECT Name, PHONENUMBER, ADDRESS FROM PHONEBOOK");

			while (result.next()) {
				outputPersonToConsole(new Person(result.getString("NAME"), result.getString("PHONENUMBER"),
						result.getString("ADDRESS")));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(cn, stmt);
		}
	}

	private static void outputPersonToConsole(Person person) {
		System.out.println(person);
	}

	private static void close(Connection cn, Statement stmt) {
		close(cn, stmt, null);
	}

	private static void close(Connection cn, Statement stmt, ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (cn != null) {
				cn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
