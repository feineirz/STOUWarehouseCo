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
					
					"jdbc:mysql://localhost/stouwarehouse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf-8",
					"root",
					""
					
					
					
					/*
		
					
					"jdbc:mysql://feinz.net:3306/feinznet_stou?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf-8",
					"feinznet_stou",
					"stouproj"
					*/
					);	
			//System.out.println("เชื่อมต่อแล้ว");

		}catch(Exception e){
			System.out.println(e);
		} 
		return conn;
		
	}
	
	/*
    public static Connection getDBConnection(){

    	
        String db_name = "db_stouwarehouseco?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String db_user = "root";
        String db_pass = "";
        String hostName = "localhost";
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
            String url = "jdbc:mysql://" + hostName + "/" + db_name;
            conn = DriverManager.getConnection(url, db_user, db_pass);
            System.out.println("เชื่อมต่อฐานข้อมุลสำเร็จ");
            return conn;
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        return conn;     
        
    }
    */

}