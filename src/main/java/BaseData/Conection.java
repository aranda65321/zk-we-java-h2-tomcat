package BaseData;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException; 



public class Conection {
	static final String JDBC_DRIVER = "org.h2.Driver";   
	static final String DB_URL = "jdbc:h2:file:C:/Users/Pc/eclipse-workspace/zk_test/src/main/java/BaseData/DataBase";  

	   //  Database credentials 
	static final String USER = "sa"; 
	static final String PASS = ""; 
    Connection conn = null; 
    
    
	public Connection Conectbasedata() {
	      try { 
	    	  
	          // STEP 1: Register JDBC driver 
	          Class.forName(JDBC_DRIVER); 
	          //STEP 2: Open a connection 
	          System.out.println("Connecting to database"); 
	          conn = DriverManager.getConnection(DB_URL,USER,PASS);
	          System.out.println("successful connection");
	          

	       } catch(SQLException se) { 
	          //Handle errors for JDBC 
	          se.printStackTrace(); 
	       } catch(Exception e) { 
	          //Handle errors for Class.forName 
	          e.printStackTrace(); 
	       } 

	      return conn;
	}

}
