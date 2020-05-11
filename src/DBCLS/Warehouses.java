package DBCLS;

import java.sql.*;
import java.util.*;

public class Warehouses {	

	/************************** Class Header ***************************/
	
	public static enum WHStatus{
		EMPTY, FULL, MAINTENANCE
	}
	private WHStatus status;
	private String loc_id, remark;
	public final String relName = "warehouses";
	public final String columnNames = "loc_id, status, remark";
	
	/************************** Class Structure ***************************/
	public static class WarehouseInfo {
		WHStatus status;
		String loc_id, remark;
	}
	
	/************************** Constructor ***************************/
	public Warehouses() {}
	
	// Create a Warehouse object from the given loc_id.
	public Warehouses(String loc_id) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM " + relName
					+ " WHERE loc_id=?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, loc_id);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				this.loc_id = rs.getString("loc_id");
				String status = rs.getString("status");
				switch (status) {
				case "E":
					this.status = WHStatus.EMPTY;
					break;
				case "F":
					this.status = WHStatus.FULL;
					break;
				case "M":
					this.status = WHStatus.MAINTENANCE;
					break;

				default:
					this.status = WHStatus.EMPTY;
					break;
				}
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
	public String getLocID() {
		return loc_id;
	}
	
	public WHStatus getStatus() {
		return status;
	}
	
	public boolean setStatus(WHStatus status) {
		return Warehouses.updateWarehouseInfo(loc_id, status, remark);
	}
	
	public String getRemark() {
		return remark;
	}
	
	public boolean setRemark(String remark) {
		return Warehouses.updateWarehouseInfo(loc_id, status, remark);
	}
	
	/************************** Required Method ***************************/
	
	// List //
	// List all warehouse slot in the database as a Warehouses objects.
	public static ArrayList<Warehouses> listAllWarehouseLocation(String condition, String order) {
		
		ArrayList<Warehouses> buff = new ArrayList<Warehouses>();
		
		if(condition != "") condition = " WHERE " + condition;
		if(order != "") order = " ORDER BY " + order;
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "SELECT *"
					+ " FROM warehouses"
					+ condition
					+ order;
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()) {
				buff.add(new Warehouses(rs.getString("loc_id")));
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
	// Warehouse slots is a fixed size and manually created from a SQL initial query.
	
	// Update //
	public static boolean updateWarehouseInfo(String loc_id, WHStatus whStatus, String remark) {
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			String qry = "UPDATE warehouses"
					+ " SET status=?, remark = ?"
					+ " WHERE loc_id = ?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			
			String status = "E";
			switch (whStatus) {
			case EMPTY:
				status = "E";
				break;
			case FULL:
				status = "E";
				break;
			case MAINTENANCE:
				status = "M";
				break;

			default:
				status = "E";
				break;
			}
			stmt.setString(1, status);
			
			stmt.setString(2, remark);
			stmt.setString(3, loc_id);
			
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
	// Warehouse slots is a fixed size and cannot be delete.
	
	// IsExist //
	// Check if record(s) from the given condition is exist in a database.
	public static boolean isExist(String condition) {
		
		Connection conn = new DBConnector().getDBConnection();
		
		if(condition != "") condition = " WHERE " + condition;
		try {
			String qry = "SELECT *" 
					+ " FROM warehouses3"
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

