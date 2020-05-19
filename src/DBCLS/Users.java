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
import java.util.Random;

public class Users {
	
	/************************** Class Header ***************************/
	private int user_id;
	private String username, password, phone, email;
	public final String relName = "users";
	public final String columnNames = "user_id, username, password, phone, email";
	
	/************************** Class Structure ***************************/
	public static class UserInfo {
		int user_id;
		String username, password, phone, email;		
	}
	
	/************************** Constructor ***************************/
	public Users() {}
	
	// Create a Users object from the given user_id.
	public Users(int user_id) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM " + relName
					+ " WHERE user_id=?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, user_id );
			
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
	
	// Create a Users object from the given username.
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
		return Users.updateUserInfo(user_id, username, password, phone, email);		
	}
	
	public String getPhone() {
		return phone;
	}
	
	public boolean setPhone(String phone) {
		return Users.updateUserInfoNonMD5(user_id, username, password, phone, email);
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean setEmail(String email) {
		return Users.updateUserInfoNonMD5(user_id, username, password, phone, email);
	}
	
	/************************** Required Method ***************************/
	// List //
	// List all users in the database as a Users object.
	/// Overload ///
	public static ArrayList<Users> listAllUsers(){
		return listAllUsers("","");
	}
	public static ArrayList<Users> listAllUsers(String condition){
		return listAllUsers(condition,"");
	}	
	/// Main ///
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
	// Add the user to the database by giving a raw information.
	public static boolean addNewUser(String username, String password, String phone, String email) {
		
		UserInfo userInfo = new UserInfo();
		userInfo.user_id = 0;
		userInfo.username = username;
		userInfo.password = password;
		userInfo.phone = phone;
		userInfo.email = email;
		
		return addNewUser(userInfo);
		
	}
	
	// Add the user to the database by giving a structured information.
	public static boolean addNewUser(UserInfo userInfo) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "INSERT INTO users"
					+ " (username,password,phone,email)"
					+ " VALUES(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, userInfo.username);
			stmt.setString(2, getMD5(userInfo.password));
			stmt.setString(3, userInfo.phone);
			stmt.setString(4, userInfo.email);
			
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
	// Update user information to the database but not Encrypt a password by giving a raw information.
	public static boolean updateUserInfoNonMD5(int user_id, String username, String password, String phone, String email) {
		
		UserInfo userInfo = new UserInfo();
		userInfo.user_id = user_id;
		userInfo.username = username;
		userInfo.password = password;
		userInfo.phone = phone;
		userInfo.email = email;
		
		return updateUserInfoNonMD5(userInfo);
		
	}
	
	// Update user information to the database but not Encrypt a password by giving a structured information.
	
	public static boolean updateUserInfoNonMD5(UserInfo userInfo) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "UPDATE users"
					+ " SET username=?, password = ?, phone = ?, email = ?"
					+ " WHERE user_id = ?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, userInfo.username);
			stmt.setString(2, userInfo.password);
			stmt.setString(3, userInfo.phone);
			stmt.setString(4, userInfo.email);
			stmt.setInt(5, userInfo.user_id);
			
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
	// Update user information to the database with Encrypted password.
	public static boolean updateUserInfo(int user_id, String username, String password, String phone, String email) {
		
		return Users.updateUserInfoNonMD5(user_id, username, getMD5(password), phone, email);
		
	}
	
	// Delete //
	// Delete user from a database.
	public static boolean deleteUser(int user_id) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "DELETE FROM users"
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
	// Check if the given username is exist in a database.
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
	
	// Generate String for use as a password or a temporary name.
	public String genString(int length) {
		
		if(length <= 0) return "";
		if(length > Integer.MAX_VALUE) length = Integer.MAX_VALUE;
		
		String src ="abcdfeghijklmnopqrstuvwxyz";
		src += src.toUpperCase();
		src += "0123456789";
		src += "!@#$%^&()_+{}[],.";
		char[] arrsrc = src.toCharArray();
		
		String buff ="";
		Random rndRandom = new Random();
		
		for(int i = 0; i < length; i++) {
			int pos =  rndRandom.nextInt(src.length()  -1);
			buff += arrsrc[pos];
		}
		
		return buff;		
		
	}
	
	// Generate MD5 Hash for encrypt a password.
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
	
	// Process login from the given username and password. 
	// Returns a Users object if successful or null if failed.
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
