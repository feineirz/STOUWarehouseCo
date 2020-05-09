package DBCLS;

import java.sql.*;
import java.util.*;

public class Customers {	

	/************************** Class Header ***************************/
	private int cust_id;
	private String cust_name, address, phone, fax, email;
	public final String relName = "customers";
	public final String columnNames = "cust_id, cust_name, address, phone, fax, email";
	
	/************************** Class Structure ***************************/
	public static class CustomerInfo {
		int cust_id;
		String cust_name, address, phone, fax, email;
	}
	
	/************************** Constructor ***************************/
	public Customers() {}
	public Customers(int cust_id) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM " + relName
					+ " WHERE cust_id=?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, cust_id);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				this.cust_id = rs.getInt("cust_id");
				this.cust_name = rs.getString("cust_name");
				this.address = rs.getString("address");
				this.phone = rs.getString("phone");
				this.fax = rs.getString("fax");
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
	public int getCustomerID() {
		return this.cust_id;
	}
	
	public String getCustomerName() {
		return cust_name;
	}
	
	public boolean setCustomerName(String cust_name) {
		return Customers.updateCustomerInfo(cust_id, cust_name, address, phone, fax, email);
	}
	
	public String getAddress() {
		return address;
	}
	
	public boolean setAddress(String address) {
		return Customers.updateCustomerInfo(cust_id, cust_name, address, phone, fax, email);
	}
	
	public String getPhone() {
		return phone;
	}
	
	public boolean setPhone(String phone) {
		return Customers.updateCustomerInfo(cust_id, cust_name, address, phone, fax, email);
	}
	
	public String getFax() {
		return fax;
	}
	
	public boolean setFax(String fax) {
		return Customers.updateCustomerInfo(cust_id, cust_name, address, phone, fax, email);
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean setEmail(String email) {
		return Customers.updateCustomerInfo(cust_id, cust_name, address, phone, fax, email);
	}
	
	/************************** Required Method ***************************/
	// List //
	public static ArrayList<Customers> listAllCustomers(String condition, String order) {
		
		ArrayList<Customers> buff = new ArrayList<Customers>();
		
		if(condition != "") condition = " WHERE " + condition;
		if(order != "") order = " ORDER BY " + order;
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM customers"
					+ condition
					+ order;
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()) {
				buff.add(new Customers(rs.getInt("cust_id")));
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
	public static boolean addNewCustomer(String cust_name, String address, String phone, String fax, String email) {
		
		CustomerInfo custtomerInfo = new CustomerInfo();
		custtomerInfo.cust_name = cust_name;
		custtomerInfo.address = address;
		custtomerInfo.phone = phone;
		custtomerInfo.fax = fax;
		custtomerInfo.email = email;
		
		return addNewCustomer(custtomerInfo);
		
	}
	
	public static boolean addNewCustomer(CustomerInfo customerInfo) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "INSERT INTO customers"
					+ " (cust_name,address,phone,fax,email)"
					+ " VALUES(?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, customerInfo.cust_name);
			stmt.setString(2, customerInfo.address);
			stmt.setString(3, customerInfo.phone);
			stmt.setString(4, customerInfo.fax);
			stmt.setString(5, customerInfo.email);
			
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
	public static boolean updateCustomerInfo(int cust_id, String cust_name, String address, String phone, String fax, String email) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "UPDATE customers"
					+ " SET cust_name=?, address = ?, phone = ?, fax=?, email = ?"
					+ " WHERE cust_id = ?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, cust_name);
			stmt.setString(2, address);
			stmt.setString(3, phone);
			stmt.setString(4, fax);
			stmt.setString(5, email);
			stmt.setInt(6, cust_id);
			
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
	
	// Delete //
	public static boolean deleteCustomer(int cust_id) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "DELETE FROM customers"
					+ " WHERE cust_id=?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, cust_id);
			
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
	public static boolean isExist(String condition) {
		
		Connection conn = new DBConnector().getDBConnection();
		
		if(condition != "") condition = " WHERE " + condition;
		try {
			String qry = "SELECT *" 
					+ " FROM customers"
					+ condition;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
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
		
}

