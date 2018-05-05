package ua.at.mamdouh.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	
	private static DbHelper dbHelper;
	
	private File dbFile = new File("db/data.db");
	private File dbDir = new File(dbFile.getParentFile().getAbsolutePath());
	
	 // db parameters
   private  String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
	
	public DbHelper() {
		
		createDbFile();
	}
	
	private void createDbFile() {
		
		if(!dbDir.exists()) {
			
			dbDir.mkdirs();
		}
		
		if(!dbFile.exists())
			try {
				
				dbFile.createNewFile();
				
				createMainTable();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static DbHelper getInstance() {
		
		if(dbHelper == null) {
			
			dbHelper = new DbHelper();
		}
		
		return dbHelper;
	}
	
	
	 public  Connection  getConnect() {
	        Connection conn = null;
	        try {
	           
	            // create a connection to the database
	            conn = DriverManager.getConnection(url);
	            
	            System.out.println("Connection to SQLite has been established.");
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            
	        } /*finally {
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException ex) {
	                System.out.println(ex.getMessage());
	            }
	        }*/
	        
	        return conn;
	    }
	 
	 
	 //----------------  create main table ----------------------
	 
	 public  void createMainTable() {
	        
	        // SQL statement for creating a new table
	        String sql = "CREATE TABLE IF NOT EXISTS main (\n"
	                + "	id integer PRIMARY KEY,\n"
	                + "	sender text NOT NULL,\n"
	                + "	read integer\n"
	                + ");";
	        
	        try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 ///-----  insert Record ------------
	 
	  public void insert(String sender, int isRead) {
		  
	        String sql = "INSERT INTO main(sender,read) VALUES(?,?)";
	 
	        try (Connection conn = getConnect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	
	            pstmt.setString(1, sender);
	            pstmt.setInt(2, isRead);
	            
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

}
