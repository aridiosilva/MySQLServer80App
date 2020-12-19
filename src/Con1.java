import java.sql.*;
import java.lang.reflect.*;

//https://edux.pjwstk.edu.pl/mat/268/lec/lect7/lecture7.html


public class Con1  {

	// .... something is lacking here

	public Con1()  {

		// ... and here too

	}

	// The following method reports data stored in a DatabaseMetaData object.
	// When calling the method info() we pass the names of methods from this interface as arguments.
	// Inside this method we use reflection to call the specified methods.
	// We do not handle the SQLException here, but defer it to the try-catch block in the constructor.

	void reportInfo() throws SQLException {

		info("getDatabaseProductName");
		info("getDatabaseProductVersion");
		info("getDriverName");
		info("getURL");
		info("getUserName");

		info("supportsAlterTableWithAddColumn");
		info("supportsAlterTableWithDropColumn");
		info("supportsANSI92FullSQL");
		info("supportsBatchUpdates");
		info("supportsMixedCaseIdentifiers");
		info("supportsMultipleTransactions");
		info("supportsPositionedDelete");
		info("supportsPositionedUpdate");
		info("supportsSchemasInDataManipulation");
		info("supportsTransactions");

		DatabaseMetaData md;

		System.out.println("ResultSet  TYPE_SCROLL_INSENSITIVE :" +
				md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
		System.out.println("ResultSet  TYPE_SCROLL_SENSITIVE :" +
				md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
		System.out.println("insertsAreDetected :" +
				md.insertsAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
		System.out.println("updatesAreDetected :" +
				md.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
	}

	// The method info() usues reflection to invoke the specified methods 

	void info(String metName) {

		Class mdc  = DatabaseMetaData.class;
		Class[] paramTypes =  { };
		Object[] params =  { };
		String infoTyp;
		if (metName.startsWith("get"))
			infoTyp = metName.substring(3,metName.length());
		else infoTyp = metName;
		try  {
			Method m = mdc.getDeclaredMethod(metName, paramTypes);
			Object md;
			System.out.println(infoTyp + ": " + m.invoke(md, params));  // dynamic invocation
		} catch(Exception exc)  {   
			System.out.println(exc);
		}
	}

	public static void main(String[] args)  {
		new Con1();
	}

}