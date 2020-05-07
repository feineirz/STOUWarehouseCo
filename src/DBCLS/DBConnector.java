package DBCLS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	public Connection conn = null;
	
	public DBConnector() {}
	
	public Connection getDBConnection() {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			conn = DriverManager.getConnection(
					"jdbc:mysql://feinz.net:3306/feinznet_stou?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"feinznet_stou",
					"stouproj"
					);			
		}catch(Exception e){
			System.out.println(e);
		} 
		
		return conn;
	}

}