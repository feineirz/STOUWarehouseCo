package DBCLS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Rental {

	/************************** Class Header ***************************/
	private int inv_no, cust_id, user_id;
	private Date inv_date, start_date, expire_date;
	private float amount;
	private String loc_id, remark;
	
	public final String relName = "rents";
	public final String columnNames = "inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark";
	
	/************************** Class Structure ***************************/
	public static class RentalInfo {
		int inv_no, cust_id, user_id;
		Date inv_date, start_date, expire_date;
		float amount;
		String loc_id, remark;
	}
	
	/************************** Constructor ***************************/
	public Rental() {}
	
	// Create a Rental object from the given inv_no.
	public Rental(int inv_no) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM " + relName
					+ " WHERE inv_no=?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, inv_no);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				this.inv_no = rs.getInt("inv_no");
				this.cust_id = rs.getInt("cust_id");
				this.loc_id = rs.getString("loc_id");
				this.inv_date = rs.getDate("inv_date");
				this.start_date = rs.getDate("start_date");
				this.expire_date = rs.getDate("expire_date");
				this.amount = rs.getFloat("amount");
				this.user_id = rs.getInt("user_id");
				this.remark = rs.getString("remark");
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
	public int getInvNo() {
		return inv_no;
	}
	
	public int getCustomerID() {
		return cust_id;
	}
	
	public boolean setCustomerID(int cust_id) {
		return updateRentalInfo(inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark);
	}
	
	public String getWarehouseLocationID() {
		return loc_id;
	}
	
	public boolean setWarehouseLocationID(String loc_id) {
		return updateRentalInfo(inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark);
	}
	
	public Date getInvDate() {
		return inv_date;
	}
	
	public boolean setInvDate(Date inv_date) {
		return updateRentalInfo(inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark);
	}
	
	public Date getStartDate() {
		return start_date;
	}
	
	public boolean setStartDate(Date start_date) {
		return updateRentalInfo(inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark);
	}
	
	public Date getExpireDate() {
		return expire_date;
	}
	
	public boolean setExpireDate(Date expire_date) {
		return updateRentalInfo(inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark);
	}
	
	public float getAmount() {
		return amount;
	}
	
	public boolean setAmount(float amount) {
		return updateRentalInfo(inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark);
	}
	
	public int getUserID() {
		return user_id;
	}
	
	public boolean setUserID(int user_id) {
		return updateRentalInfo(inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark);
	}
	
	public String getRemark() {
		return remark;
	}
	
	public boolean setRemark(String remark) {
		return updateRentalInfo(inv_no, cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark);
	}
	
	/************************** Required Method ***************************/
	
	// List //
	// List all rentals in the database as a Rental objects.
	/// Overload ///
	public static ArrayList<Rental> listAllRental(){
		return listAllRental("","");
	}
	public static ArrayList<Rental> listAllRental(String condition){
		return listAllRental(condition,"");
	}	
	/// Main ///
	public static ArrayList<Rental> listAllRental(String condition, String order) {
		
		ArrayList<Rental> buff = new ArrayList<Rental>();
		
		if(condition != "") condition = " WHERE " + condition;
		if(order != "") order = " ORDER BY " + order;
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM rents"
					+ condition
					+ order;
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()) {
				buff.add(new Rental(rs.getInt("inv_no")));
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
	// Add rental to the database by giving a raw information.
	public static boolean addNewRental(int cust_id, String loc_id, Date inv_date, Date start_date, Date expire_date, float amount, int user_id, String remark) {
		
		RentalInfo rentalInfo = new RentalInfo();
		rentalInfo.inv_no = 0;
		rentalInfo.cust_id = cust_id;
		rentalInfo.loc_id = loc_id;
		rentalInfo.inv_date = inv_date;
		rentalInfo.start_date = start_date;
		rentalInfo.expire_date = expire_date;
		rentalInfo.amount = amount;
		rentalInfo.user_id = user_id;
		rentalInfo.remark = remark;
		
		return addNewRental(rentalInfo);
		
	}
	
	// Add rental to the database by giving a structured information.
	public static boolean addNewRental(RentalInfo rentalInfo) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "INSERT INTO rents"
					+ " (cust_id, loc_id, inv_date, start_date, expire_date, amount, user_id, remark)"
					+ " VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, rentalInfo.cust_id);
			stmt.setString(2, rentalInfo.loc_id);
			stmt.setDate(3, rentalInfo.inv_date);
			stmt.setDate(4, rentalInfo.start_date);
			stmt.setDate(5, rentalInfo.expire_date);
			stmt.setFloat(6, rentalInfo.amount);
			stmt.setInt(7, rentalInfo.user_id);
			stmt.setString(8, rentalInfo.remark);
			
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
	// Update rental information to a database.
	public static boolean updateRentalInfo(int inv_no, int cust_id, String loc_id, Date inv_date, Date start_date, Date expire_date, float amount, int user_id, String remark) {
		
		RentalInfo rentalInfo = new RentalInfo();
		rentalInfo.inv_no = inv_no;
		rentalInfo.cust_id = cust_id;
		rentalInfo.loc_id = loc_id;
		rentalInfo.inv_date = inv_date;
		rentalInfo.start_date = start_date;
		rentalInfo.expire_date = expire_date;
		rentalInfo.amount = amount;
		rentalInfo.user_id = user_id;
		rentalInfo.remark = remark;
		
		return updateRentalInfo(rentalInfo);
		
	}
	
	// Update rental information to the database by giving a structured information.
	public static boolean updateRentalInfo(RentalInfo rentalInfo) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "UPDATE rents"
					+ " SET cust_id=?, loc_id=?, inv_date=?, start_date=?, expire_date=?, amount=?, user_id=?, remark=?"
					+ " WHERE inv_no = ?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, rentalInfo.cust_id);
			stmt.setString(2, rentalInfo.loc_id);
			stmt.setDate(3, rentalInfo.inv_date);
			stmt.setDate(4, rentalInfo.start_date);
			stmt.setDate(5, rentalInfo.expire_date);
			stmt.setFloat(6, rentalInfo.amount);
			stmt.setInt(7, rentalInfo.user_id);
			stmt.setString(8, rentalInfo.remark);
			
			stmt.setInt(9, rentalInfo.inv_no);
			
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
	// Rental information is required for tracking and cannot be delete.
	
	// IsExist //
	// Check if record(s) from the given condition is exist in a database.
	public static boolean isExist(String condition) {
		
		Connection conn = new DBConnector().getDBConnection();
		
		if(condition != "") condition = " WHERE " + condition;
		try {
			String qry = "SELECT *" 
					+ " FROM rents"
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
