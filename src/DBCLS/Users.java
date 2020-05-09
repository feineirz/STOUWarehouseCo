package DBCLS;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Users {
	
	private int user_id;
	private String username, password, phone, email;
	private String relName = "users";
	
	/************************** Constructor ***************************/
	public Users() {}
	public Users(String username) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM " + relName
					+ " WHERE username=?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, username );
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				this.user_id = rs.getInt("user_id");
				this.username = rs.getString("username");
				this.password = rs.getString("password");
				this.phone = rs.getString("phone");
				this.email = rs.getString("email");
			}
			
			conn.close();
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	/************************** Properties ***************************/
	public int getUserID() {
		return this.user_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPasswordMD5() {
		return password;
	}
	
	public boolean setPassword(String password) {
		return Users.update(user_id, username, password, phone, email);		
	}
	
	public String getPhone() {
		return phone;
	}
	
	public boolean setPhone(String phone) {
		return Users.updateWithNoMD5(user_id, username, password, phone, email);
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean setEmail(String email) {
		return Users.updateWithNoMD5(user_id, username, password, phone, email);
	}
	
	/************************** Required Method ***************************/
	// List //
	public static ArrayList<Users> listAllUsers(String condition, String order) {
		
		ArrayList<Users> buff = new ArrayList<Users>();
		
		if(condition != "") condition = " WHERE " + condition;
		if(order != "") order = " ORDER BY " + order;
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM users"
					+ condition
					+ order;
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()) {
				buff.add(new Users(rs.getString("username")));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return buff;
		
	}
	
	// Add //
	public static boolean addNewUser(String username, String password, String phone, String email) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "INSERT INTO users"
					+ " (username,password,phone,email)"
					+ " VALUES(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, username);
			stmt.setString(2, getMD5(password));
			stmt.setString(3, phone);
			stmt.setString(4, email);
			
			stmt.execute();			
			conn.close();
			return true;
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		
	}
	
	// Update //
	public static boolean updateWithNoMD5(int user_id, String username, String password, String phone, String email) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "UPDATE users"
					+ " SET username=?, password = ?, phone = ?, email = ?"
					+ " WHERE user_id = ?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, phone);
			stmt.setString(4, email);
			stmt.setInt(5, user_id);
			
			stmt.execute();			
			conn.close();
			return true;
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		
	}
	
	// Update //
	public static boolean update(int user_id, String username, String password, String phone, String email) {
		
		return Users.updateWithNoMD5(user_id, username, getMD5(password), phone, email);
		
	}
	
	// Delete //
	public static boolean deleteUser(int user_id) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "DELETE FROMM users"
					+ " WHERE user_id=?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, user_id);
			
			stmt.execute();			
			conn.close();
			return true;
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		
	}
	
	// IsExist //
	public static boolean isExist(String username) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *" 
					+ " FROM users"
					+ " WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, username );			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				conn.close();
				return true;
			}			
			conn.close();
			return false;
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return true;
		}
		
	}
	
	/************************** Custom Method ***************************/
	public static String getMD5(String content){
		
		if(content == "") return "";
		
  	try { 
      MessageDigest md = MessageDigest.getInstance("MD5"); 
      byte[] messageDigest = md.digest(content.getBytes()); 
      BigInteger no = new BigInteger(1, messageDigest); 
      String hashtext = no.toString(16); 
      while (hashtext.length() < 32) { 
        hashtext = "0" + hashtext; 
      } 
      return hashtext; 
    }  
	
    catch (NoSuchAlgorithmException e) { 
    	throw new RuntimeException(e); 
	 	} 
	}
	
	public static Users performLogIn(String username, String password) {
		
		Connection conn = new DBConnector().getDBConnection();
		Users buff = null;
		
		try {
			String qry = "SELECT *"
					+ " FROM users"
					+ " WHERE username=? AND password=?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, username );
			stmt.setString(2, getMD5(password));
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				buff = new Users(rs.getString("username"));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		return buff;
		
	}

}















