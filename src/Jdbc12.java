
import java.sql.*;

public class Jdbc12 {
	
	public static void main(String args[]){
		
		System.out.println(
				"JDBC12 - Use of MySQL 8.0");
		try {
			Statement stmt;

			//Register the JDBC driver for MySQL.
			Class.forName("com.mysql.jdbc.Driver");

			//Define URL of database server for
			// database named mysql on the localhost
			// with the default port number 3306.
			String url =
					"jdbc:mysql://localhost:3306/mysql";

			//Get a connection to the database for a
			// user named root with a blank password.
			// This user is the default administrator
			// having full privileges to do anything.
			Connection con =
					DriverManager.getConnection(
							url,"root", "");

			//Display URL and connection information
			System.out.println("URL: " + url);
			System.out.println("Connection: " + con);

			//Get a Statement object
			stmt = con.createStatement();

			//Remove the user named auser
			stmt.executeUpdate(
					"REVOKE ALL PRIVILEGES ON *.* " +
					"FROM 'auser'@'localhost'");
			stmt.executeUpdate(
					"REVOKE GRANT OPTION ON *.* " +
					"FROM 'auser'@'localhost'");
			stmt.executeUpdate(
					"DELETE FROM mysql.user WHERE " +
					"User='auser' and Host='localhost'");
			stmt.executeUpdate("FLUSH PRIVILEGES");

			//Delete the database
			stmt.executeUpdate(
					"DROP DATABASE JunkDB");

			con.close();
		}catch( Exception e ) {
			e.printStackTrace();
		}
	}

}