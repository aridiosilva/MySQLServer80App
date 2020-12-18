<<<<<<< HEAD

import java.sql.*;
 
public class JdbcUpdatetableResultSetJDK7 {  // JDK 7 and above
   public static void main(String[] args) {
      try (
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:8888/ebookshop", "myuser", "xxxx"); // MySQL
 
         //  TYPE_SCROLL_INSENSITIVE: ResultSet is scrollable and does not
         //    reflect changes by others while it is opened for processing.
         //  CONCUR_UPDATABLE: Resultset is updatable
         Statement stmt = conn.createStatement(
               ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ) {
         conn.setAutoCommit(false);  // Disable auto-commit
 
         ResultSet rset = stmt.executeQuery("select * from books");
 
         // Update a row
         rset.last();
         System.out.println("-- Update a row --");
         System.out.println(
                 rset.getRow() + ": " + + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
         rset.updateDouble("price", 99.99);   // update cells via column name
         rset.updateInt("qty", 99);
         rset.updateRow();  // update the row in the data source
         System.out.println(
                 rset.getRow() + ": " + + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
 
         // Delete a row
         rset.first();
         System.out.println("-- Delete a row --");
         System.out.println(
                 rset.getRow() + ": " + + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
         rset.deleteRow();  // delete the current row
 
         // A updatable ResultSet has a special row that serves as a staging area
         // for building a row to be inserted.
         rset.moveToInsertRow();
         rset.updateInt(1, 8001);  // Update cells via column number (starts at 1)
         rset.updateString(2, "Even More Programming");
         rset.updateString(3, "Kumar");
         rset.updateDouble(4, 77.88);
         rset.updateInt(5, 77);
         rset.insertRow();
         // After inserting a row, you need to move the cursor to another row, to avoid
         //  unexpected error if another part of the program also accesses this ResultSet.
         rset.beforeFirst();
 
         conn.commit();  // commit changes
      } catch(SQLException ex) {
         ex.printStackTrace();
      }
   }
=======

import java.sql.*;
 
public class JdbcUpdatetableResultSetJDK7 {  // JDK 7 and above
   public static void main(String[] args) {
      try (
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:8888/ebookshop", "myuser", "xxxx"); // MySQL
 
         //  TYPE_SCROLL_INSENSITIVE: ResultSet is scrollable and does not
         //    reflect changes by others while it is opened for processing.
         //  CONCUR_UPDATABLE: Resultset is updatable
         Statement stmt = conn.createStatement(
               ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ) {
         conn.setAutoCommit(false);  // Disable auto-commit
 
         ResultSet rset = stmt.executeQuery("select * from books");
 
         // Update a row
         rset.last();
         System.out.println("-- Update a row --");
         System.out.println(
                 rset.getRow() + ": " + + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
         rset.updateDouble("price", 99.99);   // update cells via column name
         rset.updateInt("qty", 99);
         rset.updateRow();  // update the row in the data source
         System.out.println(
                 rset.getRow() + ": " + + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
 
         // Delete a row
         rset.first();
         System.out.println("-- Delete a row --");
         System.out.println(
                 rset.getRow() + ": " + + rset.getInt("id") + ", "
                 + rset.getString("title") + ", " + rset.getString("author") + ", "
                 + rset.getDouble("price") + ", " + rset.getInt("qty"));
         rset.deleteRow();  // delete the current row
 
         // A updatable ResultSet has a special row that serves as a staging area
         // for building a row to be inserted.
         rset.moveToInsertRow();
         rset.updateInt(1, 8001);  // Update cells via column number (starts at 1)
         rset.updateString(2, "Even More Programming");
         rset.updateString(3, "Kumar");
         rset.updateDouble(4, 77.88);
         rset.updateInt(5, 77);
         rset.insertRow();
         // After inserting a row, you need to move the cursor to another row, to avoid
         //  unexpected error if another part of the program also accesses this ResultSet.
         rset.beforeFirst();
 
         conn.commit();  // commit changes
      } catch(SQLException ex) {
         ex.printStackTrace();
      }
   }
>>>>>>> branch 'main' of https://github.com/aridiosilva/MySQLServer80App.git
}