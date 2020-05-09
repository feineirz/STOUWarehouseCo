package DBCLS;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.lang.*;

public class Customers {	

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
	private Integer _cust_id;
	private String _cust_name;
	private String _address;
	private String _phone;
	private String _fax;
	private String _email;
	
	public final String relName = "customers";
	public final String columns = "cust_id, cust_name, address, phone, fax, email";
	public final String columnsArr[] = {"cust_id", "cust_name", "address", "phone", "fax", "email"};

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
	public Customers() {
		if(host == null) InitConnectionConfiguration();
	}

	public Customers(Integer cust_id) {

		if(host != null || InitConnectionConfiguration()) {
			try{ 
				if(conn == null || conn.isClosed()){
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
				}

				Statement stmt = conn.createStatement();
				String SQL = "SELECT * FROM "+relName+" WHERE cust_id = '"+cust_id+"'";
				ResultSet rs = stmt.executeQuery(SQL);
				while(rs.next()) {
				this._cust_id = rs.getInt("cust_id");
				this._cust_name = rs.getString("cust_name");
				this._address = rs.getString("address");
				this._phone = rs.getString("phone");
				this._fax = rs.getString("fax");
				this._email = rs.getString("email");
				}
			
			if(!holdConnection) conn.close();
			
			}catch(Exception e){ 
				System.out.println(e);
			
			}					
		}
	}

	//ClassInfo DataType
	public class CustomersInfo {
		Integer cust_id;
		String cust_name;
		String address;
		String phone;
		String fax;
		String email;
	}

	//==================== Properties ====================
	public Integer cust_id() {
		return this._cust_id;
	}

	public Integer cust_id(Integer value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET cust_id = '"+value+"' WHERE cust_id = '"+this._cust_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	public String cust_name() {
		return this._cust_name;
	}

	public Integer cust_name(String value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET cust_name = '"+value+"' WHERE cust_id = '"+this._cust_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	public String address() {
		return this._address;
	}

	public Integer address(String value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET address = '"+value+"' WHERE cust_id = '"+this._cust_id+"'";
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

			String SQL = "UPDATE "+relName+" SET phone = '"+value+"' WHERE cust_id = '"+this._cust_id+"'";
			Statement stm = conn.createStatement();
			rs = stm.executeUpdate(SQL);

			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rs;
		
	}

	public String fax() {
		return this._fax;
	}

	public Integer fax(String value) {

		int rs = 0;
		try{
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			String SQL = "UPDATE "+relName+" SET fax = '"+value+"' WHERE cust_id = '"+this._cust_id+"'";
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

			String SQL = "UPDATE "+relName+" SET email = '"+value+"' WHERE cust_id = '"+this._cust_id+"'";
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
	public Customers[] List() {
		return List("","");
	}	
	public Customers[] List(String Condition) {
		return List(Condition,"");
	}	
	public Customers[] List(String Condition, String SortOrder) {
		
		//Init Parameter
		if(Condition != "") {Condition = " WHERE " +Condition;}
		if(SortOrder != "") {SortOrder = " ORDER BY " +SortOrder;}
		
		Customers[] rt = null;
		String SQL;
		Integer cust_id;
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
			
			SQL = "SELECT cust_id FROM " +relName+Condition+SortOrder;
			stm = conn.createStatement();
			rs = stm.executeQuery(SQL);
			rt = new Customers[rc];
			while(rs.next()){
				cust_id = rs.getInt("cust_id");
				rt[i] = new Customers(cust_id);
				i++;
			}			
			
			if(!holdConnection) conn.close();
			
		}catch(Exception e){ 
			System.out.println(e);

		}
		
		return rt;

	}

	//Add
	public int Add( CustomersInfo[] Items) {
		int rt = 0;
		try{
			String SQL;
			String ItemList = "";
			if(Items.length > 0) {
				for(CustomersInfo A : Items) {
					if(ItemList != "") {ItemList += ",";}
					
					ItemList += "('";
					ItemList += A.cust_id +"','";
					ItemList += A.cust_name +"','";
					ItemList += A.address +"','";
					ItemList += A.phone +"','";
					ItemList += A.fax +"','";
					ItemList += A.email;
					ItemList += "')";
					
				}
				ItemList += ";";

			}
			
			if(conn == null || conn.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			}

			Statement stm = conn.createStatement();
			SQL = "INSERT INTO " +relName+ "(cust_id, cust_name, address, phone, fax, email)";
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
			SQL = "DELETE FROM " +relName+ " WHERE cust_id = '"+this._cust_id+"'";
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

		Customers CLSs[] = List(Condition, SortOption);		
		
		tmp += String.format("%s\n", dblLine(dblLineLength));
		tmp += String.format("%s\n", columns.replace(",", "\t"));
		tmp += String.format("%s\n", dblLine(dblLineLength));
		buff += tmp;
		tmp = "";
		
		if(CLSs != null && CLSs.length > 0) {
			recordCount = CLSs.length;
			for(Customers cls : CLSs) {
				try{
					tmp = String.format("Integer\t %s\t %s\t %s\t %s\t %s\n", cls.cust_id(), cls.cust_name(), cls.address(), cls.phone(), cls.fax(), cls.email());
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

