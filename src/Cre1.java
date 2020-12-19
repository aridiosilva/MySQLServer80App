
import java.sql.*;

//https://edux.pjwstk.edu.pl/mat/268/lec/lect7/lecture7.html


public class Cre1 {

	static public void main(String[] args) {
		new Cre1();
	}

	Statement stmt; 

	Cre1()  {
		Connection con = null;
		try {
			// connect to the database and create a Statement object
		} catch (Exception exc)  {
			System.out.println(exc);
			System.exit(1);
		}

		// The method dropTable simplifies the program - it removes the given table.
		// If we want to create a table PUBLISHER 
		// we must remove it first (if it exists).

		dropTable("PUBLICATIONS"); // remove the depending table
		dropTable("PUBLISHER");    // remove the PUBLISHER

		String crestmt;  // ...

				try  {
					  // execute the statement stored in the crestmt

				} catch (SQLException exc)  {      // catch the exception
					System.out.println("SQL except.: " + exc.getMessage());
					System.out.println("SQL state  : " + exc.getSQLState());
					System.out.println("Vendor errc: " + exc.getErrorCode());
					System.exit(1);
				} finally {
					try {
						stmt.close();
						con.close();
					} catch(SQLException exc) {
						System.out.println(exc);
						System.exit(1);
					}
				}
	}

	private void dropTable(String tname)  {
		// ....

	}

}

