package APP.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import APP.Designers.*;
import DBCLS.Rental;
import DBCLS.Rents;
import DBCLS.Users;
import DBCLS.DBConnector;
import DBCLS.RentDetail;

public class RentalMgr {
	// Preload Assets
	static JFrame defaultFrame = new AboutDesigner();
	static JFrame rental = new RentalMgrDesigner();
	/*
	public static void main(String[] arg) {
		rental.setVisible(true);
		showdata();


		
	}
	*/
	public static void getRental() {
		
		rental.setVisible(true);
		showdata();
		
	}
	
	public static void showdata() {
		try {
			int totalRow=RentalMgrDesigner.tableRent.getRowCount()-1;
			while(totalRow > -1) {
				RentalMgrDesigner.tableModel.removeRow(totalRow);
				totalRow--;
				
			}
			int row=0;
			
			String search = RentalMgrDesigner.txtSearchRent.getText().trim();
			ArrayList<Rental> rentals = Rental.listAllRental("", "");
			if(!rentals.isEmpty()) {
				for (Rental rental : rentals) {
					RentalMgrDesigner.tableModel.addRow(new Object[0]);
					RentalMgrDesigner.tableModel.setValueAt(rental.getInvNo(),row,0);
					RentalMgrDesigner.tableModel.setValueAt(rental.getCustName(),row,1);
					RentalMgrDesigner.tableModel.setValueAt(rental.getStartDate(),row,2);
					RentalMgrDesigner.tableModel.setValueAt(rental.getExpireDate(),row,3);

					/*
					RentalMgrDesigner.tableModel.setValueAt(rental.getAmount(),row,4);
					RentalMgrDesigner.tableModel.setValueAt(rental.getRemark(),row,5);
					*/
					row++;
				}
			}
			RentalMgrDesigner.tableRent.setModel(RentalMgrDesigner.tableModel);
		}catch(Exception e) {
			e.printStackTrace();
		}
		


	}
	
	public static void mouseclick() {
		int index=RentalMgrDesigner.tableRent.getSelectedRow();
		/*
		RentalMgrDesigner.lblCustId.setText(RentalMgrDesigner.tableRent.getValueAt(index, 0).toString());
		RentalMgrDesigner.lblCustName.setText(RentalMgrDesigner.tableRent.getValueAt(index, 1).toString());
		*/
		/*
		RentalMgrDesigner.lblLocId.setText(RentalMgrDesigner.tableRent.getValueAt(index, 2).toString());
		RentalMgrDesigner.startDatePicker.getJFormattedTextField().setText(RentalMgrDesigner.tableRent.getValueAt(index, 3).toString());
		RentalMgrDesigner.endDatePicker.getJFormattedTextField().setText(RentalMgrDesigner.tableRent.getValueAt(index, 4).toString());
		RentalMgrDesigner.lblAmount.setText(RentalMgrDesigner.tableRent.getValueAt(index, 5).toString());
		*/
		//RentalMgrDesigner.lblCustName.setText();
		
		try {

			ArrayList<Rental> rentals = Rental.listAllRental("inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+"", "");
			if(!rentals.isEmpty()) {
				for (Rental rent : rentals) {
					RentalMgrDesigner.lblCustId.setText(String.valueOf(rent.getCustomerID()));
					System.out.println(rent.getCustomerID());
					RentalMgrDesigner.lblCustName.setText(rent.getCustName().toString());
					RentalMgrDesigner.lblCustAddr.setText(rent.getAddress().toString());
					RentalMgrDesigner.lblCustPhone.setText(rent.getPhone().toString());
					RentalMgrDesigner.lblCustFax.setText(rent.getFax().toString());
					RentalMgrDesigner.lblCustEmail.setText(rent.getEmail().toString());
					
					RentalMgrDesigner.lblRentId.setText(RentalMgrDesigner.tableRent.getValueAt(index, 0).toString());
					RentalMgrDesigner.lblRentDate.setText(rent.getInvDate().toString());
					RentalMgrDesigner.lblStartRentDate.setText(rent.getStartDate().toString());
					RentalMgrDesigner.lblEndRentDate.setText(rent.getExpireDate().toString());
					


				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		

		try {
			/*

			ArrayList<RentDetail> rentaldD = RentDetail.listAllRentDetail("inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+"", "");
			if(!rentaldD.isEmpty()) {
				for (RentDetail rentD : rentaldD) {
					System.out.println(rentD.getRentDetail_Id());

			

				}
			}
			*/
			/*
			
			int totalRow=RentalMgrDesigner.tableRentDetail.getRowCount()-1;
			while(totalRow > -1) {
				RentalMgrDesigner.tableModelDetail.removeRow(totalRow);
				totalRow--;
				
			}
			int row=0;

			ArrayList<RentDetail> rentaldD = RentDetail.listAllRentDetail("inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+"", "");
			if(!rentaldD.isEmpty()) {
				for (RentDetail rental1 : rentaldD) {
					RentalMgrDesigner.tableModelDetail.addRow(new Object[0]);
					RentalMgrDesigner.tableModelDetail.setValueAt(rental1.getPrice(),row,0);

					row++;
				}
			}
			RentalMgrDesigner.tableRentDetail.setModel(RentalMgrDesigner.tableModelDetail);
			
			*/
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		Connection conn = new DBConnector().getDBConnection();
		try {
			int totalRow=RentalMgrDesigner.tableRentDetail.getRowCount()-1;
			while(totalRow > -1) {
				RentalMgrDesigner.tableModelDetail.removeRow(totalRow);
				totalRow--;
				
			}
			int row=0;
			
			String qry = "SELECT *"
					+ " FROM rentdetail,warehouses"
					+ " WHERE rentdetail.loc_id=warehouses.loc_id AND inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+"";
			PreparedStatement stmt = conn.prepareStatement(qry);
			//stmt.setInt(1, inv_no);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString("loc_id"));
				RentalMgrDesigner.tableModelDetail.addRow(new Object[0]);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("loc_id"),row,0);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("status"),row,1);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("price"),row,2);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("remark"),row,3);
				row++;

			}
			RentalMgrDesigner.tableRentDetail.setModel(RentalMgrDesigner.tableModelDetail);
			conn.close();
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		RentalMgrDesigner.lblSumTotal.setText(Double.toString(getsum()));
		
	}
	
    public static double getsum(){
        int rowscount = RentalMgrDesigner.tableRentDetail.getRowCount();
        double sum=0;
        for(int i =0; i< rowscount; i++){
            sum=sum+Double.valueOf(RentalMgrDesigner.tableRentDetail.getValueAt(i,2).toString());
        }
        return sum;
        
    }
	
}
