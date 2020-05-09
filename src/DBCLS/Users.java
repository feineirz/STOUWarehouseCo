package DBCLS;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.lang.*;

public class Users {	

	//==================== Header ====================

	private Connection conn;
	public boolean holdConnection = false;

	public boolean openConnection() {
		try {
			if(host != null || InitConnectionConfiguration()) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
				return true;
				
			}else{
				return false;
				
			}
			
		}catch(Exception e) {
			return false;
			
		}
	}
	
	public boolean isConnectionValid() {
		try {
			return conn.isValid(0);
			
		} catch (SQLException e) {
			return false;
			
		}
	}
	
	public boolean isConnectionClose() {
		try {
			return conn.isClosed();
			
		}catch(SQLException e) {
			return false;
			
		}
	}
	
	public boolean closeConnection() {
		try {
			conn.close();
			return true;
			
		}catch(Exception e) {
			return false;
			
		}		
	}	

	//@Connection info.
	private Properties props = new Properties();
	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	
	//InternalVariable
	private Integer _user_id;
	private String _username;
	private String _password;
	private String _email;
	private String _phone;
	private byte _is_admin;
	
	public final String relName = "users";
	public final String columns = "user_id, username, password, email, phone, is_admin";
	public final String columnsArr[] = {"user_id", "username", "password", "email", "phone", "is_admin"};

	//Initialize
	private boolean InitConnectionConfiguration() {
		
		String dbconf = "dbconf.conf";
		try (InputStream inputStream = new FileInputStream(dbconf)) {
			 
			// Loading the properties.
			props.load(inputStream);
 
			// Getting properties 
			host = props.getProperty("host");
			port = props.getProperty("port");
			database = props.getProperty("database");
			user = props.getProperty("user");
			password = props.getProperty("password");
			/*
			System.out.println("Host = " + host);
			System.out.println("Port = " + port);
			System.out.println("Database = " + database);
			System.out.println("User = " + user);
			System.out.println("Password = " + password);
			*/
			return true;
			
		} catch (IOException ex) {
			System.out.println("Problem occurs when reading file !");
			ex.printStackTrace();
			return false;
			
		}
	}

	//Constructor
	public Users() {
		if(host == null) InitConnectionConfiguration();
	}

	public Users(Integer user_id) {

		if(host != null || InitConnectionConfiguration()) {
			try{ 
				if(conn == null || conn.isClosed()){
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
				}

				Statement stmt = conn.createStatement();
				String SQL = "SELECT * FROM "+relName+" WHERE user_id = '"+user_id+"'";
				ResultSet rs = stmt.executeQuery(SQL);
				while(rs.next()) {
				this._user_id = rs.getInt("user_id");
				this._username = rs.getString("username");
				this._password = rs.getString("password");
				this._email = rs.getString("email");
				this._phone = rs.getString("phone");
				this._is_admin = rs.getByte("is_admin");
				}
			
			if(!holdConnection) conn.close();
			
			}catch(Exception e){ 
				System.out.println(e);
			
			}					
		}
	}

	//ClassInfo DataType
	public class UsersInfo {
		Integer user_id;
		String username;
		String password;
		String email;
		String phone;
		byte is_admin;
	}

	//==================== Properties ====================
	public Integer user_id() {
		return this._user_id;
	}

	public Integer user_id(Integer value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET user_id = '"+value+"' WHERE user_id = '"+this._user_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	public String username() {
		return this._username;
	}

	public Integer username(String value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET username = '"+value+"' WHERE user_id = '"+this._user_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	public String password() {
		return this._password;
	}

	public Integer password(String value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET password = '"+value+"' WHERE user_id = '"+this._user_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	public String email() {
		return this._email;
	}

	public Integer email(String value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET email = '"+value+"' WHERE user_id = '"+this._user_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	public String phone() {
		return this._phone;
	}

	public Integer phone(String value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET phone = '"+value+"' WHERE user_id = '"+this._user_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	public byte is_admin() {
		return this._is_admin;
	}

	public Integer is_admin(byte value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET is_admin = '"+value+"' WHERE user_id = '"+this._user_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	//==================== Basic Function ====================
		
	//List
	public Users[] List() {
		return List("","");
	}	
	public Users[] List(String Condition) {
		return List(Condition,"");
	}	
	public Users[] List(String Condition, String SortOrder) {
		
		//Init Parameter
		if(Condition != "") {Condition = " WHERE " +Condition;}
		if(SortOrder != "") {SortOrder = " ORDER BY " +SortOrder;}
		
		Users[] rt = null;
		String SQL;
		Integer user_id;
		int rc, i = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			Statement stm = conn.createStatement();
			SQL = "SELECT COUNT(*) FROM " +relName+Condition+SortOrder;
			ResultSet rs = stm.executeQuery(SQL);
			rs.next();
			rc = rs.getInt(1);
			
			SQL = "SELECT user_id FROM " +relName+Condition+SortOrder;
			stm = conn.createStatement();
			rs = stm.executeQuery(SQL);
			rt = new Users[rc];
			while(rs.next()){
				user_id = rs.getInt("user_id");
				rt[i] = new Users(user_id);
				i++;
			}			
			
			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rt;

	}

	//Add
	public int Add( UsersInfo[] Items) {
		int rt = 0;
		try{
			String SQL;
			String ItemList = "";
			if(Items.length > 0) {
				for(UsersInfo A : Items) {
					if(ItemList != "") {ItemList += ",";}
					
					ItemList += "('";
					ItemList += A.user_id +"','";
					ItemList += A.username +"','";
					ItemList += A.password +"','";
					ItemList += A.email +"','";
					ItemList += A.phone +"','";
					ItemList += A.is_admin;
					ItemList += "')";
					
				}
				ItemList += ";";

			}
			
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			Statement stm = conn.createStatement();
			SQL = "INSERT INTO " +relName+ "(user_id, username, password, email, phone, is_admin)";
			SQL += " VALUES"+ItemList;
			rt = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rt;
	}

	//Delete
	public int Remove() {
		int rt = 0;
		try{
			String SQL;
			
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			Statement stm = conn.createStatement();
			SQL = "DELETE FROM " +relName+ " WHERE user_id = '"+this._user_id+"'";
			rt = stm.executeUpdate(SQL);
			
			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);
		}
		
		return rt;
	}

		public int Remove(String Condition) {
		int rt = 0;
		try{
			String SQL;
			
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			Statement stm = conn.createStatement();
			SQL = "DELETE FROM " +relName+ " WHERE " +Condition;
			rt = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);
			
		}
		
		return rt;
	}

	public String dblLine(int length) {

		if(length < 1) length = 1;
		String buff = "";
		for(int i = 0; i < length; i++) {
			buff += "=";
		}
		
		return buff;
		
	}

	public String ReportTable() {
		return ReportTable("","");
	}
	
	public String ReportTable(String Condition) {
		return ReportTable(Condition,"");
	}

	public String ReportTable(String Condition, String SortOption) {

		Date d1, d2;		
		int dblLineLength = 200;
		int recordCount = 0;
		
		String buff = "", tmp = "";
		
		d1 = new Date();

		Users CLSs[] = List(Condition, SortOption);		
		
		tmp += String.format("%s\n", dblLine(dblLineLength));
		tmp += String.format("%s\n", columns.replace(",", "\t"));
		tmp += String.format("%s\n", dblLine(dblLineLength));
		buff += tmp;
		tmp = "";
		
		if(CLSs != null && CLSs.length > 0) {
			recordCount = CLSs.length;
			for(Users cls : CLSs) {
				try{
					tmp = String.format("Integer\t %s\t %s\t %s\t %s\t %d\n", cls.user_id(), cls.username(), cls.password(), cls.email(), cls.phone(), cls.is_admin());
					buff += tmp;

				}catch(Exception e){
					buff += "Error found : " +e.getMessage()+ "\n";

				}				
			}
		}
		
		d2 = new Date();
		
		tmp += String.format("%s\n",dblLine(dblLineLength));		
		tmp += String.format("%s\n",recordCount+ " item(s) found.");
		tmp += String.format("%s\n",dblLine(dblLineLength));
		tmp += String.format("%s\n","[Report started] "+d1);
		tmp += String.format("%s\n","[Report finished] "+d2);
		tmp += String.format("%s\n",dblLine(dblLineLength));
		buff += tmp;

		return buff;
	
	}
}

