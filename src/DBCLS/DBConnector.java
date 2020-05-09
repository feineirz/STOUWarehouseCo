package DBCLS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	public static Connection conn = null;
	
	public DBConnector() {}
	
	public static Connection getDBConnection() {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/db_stouwarehouseco",
					"root",
					""
					);		

		}catch(Exception e){
			System.out.println(e);
		} 
		return conn;
		
	}

}