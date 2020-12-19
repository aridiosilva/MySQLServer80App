
import java.sql.*;

//https://edux.pjwstk.edu.pl/mat/268/lec/lect7/lecture7.html


public class Ins1 {

	static public void main(String[] args) throws Exception {
		new Ins1();
	}

	Statement stmt;

	Ins1()  {
		Connection con = null;
		try {
			//...
		} catch (Exception exc)  {
			System.out.println(exc);
			System.exit(1);
		}

		String[] ins =  { "INSERT INTO PUBLISHER VALUES (1, \'Publ 1\')",
				"INSERT INTO PUBLISHER VALUES (2, \'Publ 2\')",
				"INSERT INTO PUBLISHER VALUES (3, \'Publ 3\')",
		};
		int insCount = 0;   // the number of rows entered
		try  {
			for (int i=0; i < ins.length; i++) { // entering rows
				
				// ...
			}
		} catch (Exception exc) {
			
			System.out.println(exc);
			System.exit(1);
		}
		
		//...
	}
} 