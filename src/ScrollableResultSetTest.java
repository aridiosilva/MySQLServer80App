
import java.sql.*;

public class ScrollableResultSetTest {  
	
   public static void main(String[] args) {
	   
      try (
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:8888/ebookshop", "myuser", "xxxx"); // MySQL
 
         //  TYPE_SCROLL_INSENSITIVE: ResultSet is scrollable and does not
         //    reflect changes by others while it is opened for processing.
         //  CONCUR_READ_ONLY: Resultset is not updatable
         Statement stmt = conn.createStatement(
               ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ) {
         ResultSet rset = stmt.executeQuery("select * from books");
 
         rset.last();
         System.out.println("-- LAST ROW --");
         System.out.println(
                 rset.getRow() + ": " + + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
 
         rset.beforeFirst();
         System.out.println("-- ALL ROWS --");
         while(rset.next()) {
            System.out.println(
                    rset.getRow() + ": " + rset.getInt("id") + ", "
                    + rset.getString("title") + ", " + rset.getString("author") + ", "
                    + rset.getDouble("price") + ", " + rset.getInt("qty"));
         }
 
         rset.absolute(3);  // Row number starts at 1
         System.out.println("-- ABSOLUTE ROW 3 --");
         System.out.println(
                 rset.getRow() + ": " + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
 
         rset.relative(-2);
         System.out.println("-- RELATIVE ROW -2 --");
         System.out.println(
                 rset.getRow() + ": " + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
 
      } catch(SQLException ex) {
         ex.printStackTrace();
      }
   }

}