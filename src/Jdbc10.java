/*

The purpose of this program is to test the
ability to use JDBC to access a MySQL database
server on localhost.

The MySQL server must be running on localhost
before this program is started. In addition, a
database named JunkDB must have been created and
a user named auser must have been registered on
that database with a password of drowssap before
this program is started.

It is necessary to manually start the MySQL
database server running on localhost. See the
documentation sections 2.2.1.5,Starting the
Server for the First Time and 2.2.1.4 Selecting a
Windows Server. This is accomplished by executing
the following command at the command prompt:

C:\mysql\bin\mysqld-opt --console

Similarly, it is necessary to manually stop the
MySQL database server. See the documentation
Section 2.2.1.6 Starting MySQL from the Windows
Command Line. This is accomplished by executing
the following command at the command prompt:

C:\mysql\bin\mysqladmin -u root shutdown

You can prepare MySQL for use in three
alternative ways:
1. Using the control program named mysql located
in folder C:\mysql\bin in interactive mode.
2. Using a batch file to start the mysql program
along with a text file to provide the input.
3. By running the Java programs named Jdbc11 and
Jdbc12.

See the MySQL documentation Section 3.5, entitled
Using mysql in Batch Mode, for an explanation of
how to access the MySQL database server using
batch files and text files.

There is one bat file and one txt file used in
each case. The bat file logs into the mysql
monitor program as root having administrator
privileges and points to the txt file, which
provides the commands that are executed by the
monitor program.

The following two files create a new database
named JunkDB. See the documentation Section 3.3,
entitled Creating and Using a Database.

MySqCreateDatabase01.bat
MySqlCreateDatabase01.txt

The following two files make a new user named
auser registered on the database named JunkDB
with a password of drowssap. This user can
access the JunkDB database, but only from
localhost. See the documentation Section 5.6.2,
entitled Adding New User Accounts to MySQL.

MySqlMakeUser01.bat
MySqlMakeUser01.txt

Another way to create the database and register
a user named auser on that database is to run
the Java program named Jdbc11.

The following two files delete the database named
JunkDB. See documentation Section 14.2.8, DROP
DATABASE Syntax.

MySqDropDatabase01.bat
MySqlDropDatabase01.txt

The following two files remove the user named
auser. See the documentation Section 5.6.3,
Removing User Accounts from MySQL.

MySqlRemoveUser01.bat
MySqlRemoveUser01.txt

Another way to delete a database named JunkDB
and remove the user named auser is to run the
program named Jdbc12.

This program:
Accesses the database named JunkDB,
Creates a table named myTable,
Puts five rows of data into the table,
Displays the data,
Deletes the table.

Two different approaches are used to display the
contents of the table. The first approach
displays all of the data in the table. The
second approach displays only the data in a
specific row in the table.

As a precaution, before attempting to create the
new table, the program attempts to delete a table
having the same name. If a table having the same
name already exists as residue from a previous
run, it is deleted. If it doesn't already exist
when the attempt is made to delete it, an
exception is thrown. This exception is simply
caught and ignored.

To install the JDBC interface classes, I copied
the jar file named
mysql-connector-java-3.0.15-ga-bin.jar into the
jre\lib\ext folder of my Java installation. I
did this to avoid having to make changes to the
classpath.

I am currently running SDK v1.4.2. When I
upgrade to a newer version of the SDK, it will be
necessary for me to copy the JDBC jar file into
the jre\lib\ext folder for the new version of the
SDK.

This program produces the following output under
normal conditions where the table named myTable
does not exist when the program is started (the
specifics regarding the Connection object may
vary from one run to the next):

Copyright 2004, R.G.Baldwin
URL: jdbc:mysql://localhost:3306/JunkDB
Connection: com.mysql.jdbc.Connection@1430b5c
java.sql.SQLException: Base table or view not
found message from server: "Unknown table
'mytable'"No existing table to delete
Display all results:
test_id= 1 str = One
test_id= 2 str = Two
test_id= 3 str = Three
test_id= 4 str = Four
test_id= 5 str = Five
Display row number 2:
test_id= 2 str = Two

Tested using SDK 1.4.2 under WinXP, MySQL
version 4.0.21-win, and JDBC connector
version mysql-connector-java-3.0.15-ga.
 */
import java.sql.*;

public class Jdbc10 {

	public static void main(String args[]){

		System.out.println(
				"JDBC10 - Use of MySQL 8.0");
		try {
			Statement stmt;
			ResultSet rs;

			//Register the JDBC driver for MySQL.
			Class.forName("com.mysql.jdbc.Driver");

			//Define URL of database server for
			// database named JunkDB on the localhost
			// with the default port number 3306.
			String url =
					"jdbc:mysql://localhost:3306/JunkDB";

			//Get a connection to the database for a
			// user named auser with the password
			// drowssap, which is password spelled
			// backwards.
			Connection con =
					DriverManager.getConnection(
							url,"auser", "drowssap");

			//Display URL and connection information
			System.out.println("URL: " + url);
			System.out.println("Connection: " + con);

			//Get a Statement object
			stmt = con.createStatement();

			//As a precaution, delete myTable if it
			// already exists as residue from a
			// previous run. Otherwise, if the table
			// already exists and an attempt is made
			// to create it, an exception will be
			// thrown.
			try{
				stmt.executeUpdate("DROP TABLE myTable");
			}catch(Exception e){
				System.out.print(e);
				System.out.println(
						"No existing table to delete");
			}//end catch

			//Create a table in the database named
			// myTable.
			stmt.executeUpdate(
					"CREATE TABLE myTable(test_id int," +
					"test_val char(15) not null)");

			//Insert some values into the table
			stmt.executeUpdate(
					"INSERT INTO myTable(test_id, " +
					"test_val) VALUES(1,'One')");
			stmt.executeUpdate(
					"INSERT INTO myTable(test_id, " +
					"test_val) VALUES(2,'Two')");
			stmt.executeUpdate(
					"INSERT INTO myTable(test_id, " +
					"test_val) VALUES(3,'Three')");
			stmt.executeUpdate(
					"INSERT INTO myTable(test_id, " +
					"test_val) VALUES(4,'Four')");
			stmt.executeUpdate(
					"INSERT INTO myTable(test_id, " +
					"test_val) VALUES(5,'Five')");

			//Get another statement object initialized
			// as shown.
			stmt = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			//Query the database, storing the result
			// in an object of type ResultSet
			rs = stmt.executeQuery("SELECT * " +
					"from myTable ORDER BY test_id");

			//Use the methods of class ResultSet in a
			// loop to display all of the data in the
			// database.
			System.out.println("Display all results:");
			while(rs.next()){
				int theInt= rs.getInt("test_id");
				String str = rs.getString("test_val");
				System.out.println("\ttest_id= " + theInt
						+ "\tstr = " + str);
			}//end while loop

			//Display the data in a specific row using
			// the rs.absolute method.
			System.out.println(
					"Display row number 2:");
			if( rs.absolute(2) ){
				int theInt= rs.getInt("test_id");
				String str = rs.getString("test_val");
				System.out.println("\ttest_id= " + theInt
						+ "\tstr = " + str);
			}//end if

			//Delete the table and close the connection
			// to the database
			stmt.executeUpdate("DROP TABLE myTable");
			con.close();
		}catch( Exception e ) {
			e.printStackTrace();
		}//end catch
	}//end main
}//end class Jdbc10