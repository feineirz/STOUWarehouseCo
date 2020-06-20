package DBCLS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DBConnector {
	
	public static Connection conn = null;
	
	public DBConnector() {}
	
	public static Connection getDBConnection() {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  

			
			if(Integer.parseInt(getServer())==0){
				conn = DriverManager.getConnection(
						"jdbc:mysql://feinz.net:3306/feinznet_stou?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf-8",
						"feinznet_stou",
						"stouproj"
						
						);	
			} else if(Integer.parseInt(getServer())==1){

				String dbpass1 = "";
				if(getPass()==null) {
					dbpass1="";
				}else {
					dbpass1=getPass();
				}
				conn = DriverManager.getConnection(
						"jdbc:mysql://"+getHost()+"/"+getDatabase()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf-8",
						""+getUser()+"",
						""+dbpass1+""
						);	
			}
			

		}catch (ClassNotFoundException ex) {
            System.out.println("Could not find database driver class");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
		
		if(conn == null) {
			JOptionPane.showMessageDialog(null, "เชื่อมต่อฐานข้อมูลไม่ได้", "แจ้งเตือน", JOptionPane.ERROR_MESSAGE);
			return null;
		}else {
			return conn;
		}
		
		//return conn;
		
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
	  
	public static void write(String txt) {
		  
		try {
			FileWriter myWriter = new FileWriter("assets/dbSetting.txt");
			myWriter.write(txt);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
  
	public static void write(String server, String host, String user, String pass) {
		  
		try {
			FileWriter myWriter = new FileWriter("assets/dbSetting.txt");
			myWriter.write(server+":"+host+":"+user+":"+pass);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}
	
	public static String read() {
		String data="";
		try {
			File myObj = new File("assets/dbSetting.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
			}
			myReader.close();
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		return data;
	}
	

	
	
	public static String getServer() {
		
		String filePath = "assets/dbSetting.txt";
		String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths.get(filePath)));
			String[] result = contents.split(":");
			return result[0];
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
				
	}
	
	public static String getDatabase() {
		
		String filePath = "assets/dbSetting.txt";
		String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths.get(filePath)));
			String[] result = contents.split(":");
			return result[1];
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
				
	}
	
	public static String getHost() {
		
		String filePath = "assets/dbSetting.txt";
		String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths.get(filePath)));
			String[] result = contents.split(":");
			return result[2];
			

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
				
	}
	
	public static String getUser() {
		
		String filePath = "assets/dbSetting.txt";
		String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths.get(filePath)));
			String[] result = contents.split(":");
			
			return result[3];
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
				
	}

	public static String getPass() {
		
		String filePath = "assets/dbSetting.txt";
		String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths.get(filePath)));
			String[] result = contents.split(":");
			
			if(result.length == 5) {
				System.out.println(result.length);
				return result[4];
			}else {
				return null;
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
				
	}
	

}