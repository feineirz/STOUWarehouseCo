package APP.Controllers;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

import APP.Designers.*;
import DBCLS.*;
import DBCLS.Warehouses.WHStatus;

public class AddRental {
	// Preload Assets
	static int custID;
	static int indexSelect;
	static JFrame addRental = new AddRentalDesigner();
	static String startDate,endDate;
	static Date selectedStartDate,selectedEndtDate;
	static DateFormat df;
	static pickupLoc click_m;
	/*
	public static void main(String[] arg) {
		getAddRental();



		
	}
	*/
    public static double getsum(){
        int rowscount = AddRentalDesigner.tableCust.getRowCount();
        double sum=0;
        for(int i =0; i< rowscount; i++){
            sum=sum+Double.valueOf(AddRentalDesigner.tableCust.getValueAt(i,2).toString());
        }
        return sum;
        
    }
    
	public static void getAddRental() {
		addRental.setVisible(true);
		showdata();
	}
	
	public static void showdata() {
		AddRentalDesigner.cbCustName.removeAllItems();
		AddRentalDesigner.cbCustName.addItem("กรุษาเลือกชื่อลูกค้า");
		ArrayList<Customers> cuts = Customers.listAllCustomers("", "");
		if(!cuts.isEmpty()) {
			for (Customers cut : cuts) {
				AddRentalDesigner.cbCustName.addItem(cut.getCustomerName());
			}
		}
		AddRentalDesigner.btnEdit.setEnabled(false);
		AddRentalDesigner.btnDelete.setEnabled(false);
	
	}
	
	public static void btnReset() {
		cleartxt();
		
		/*
		if(formValidation()) {
			Vector saleProd = new Vector();
			saleProd.add(AddRentalDesigner.txtLocId.getText());
			try {
				selectedStartDate = (Date) AddRentalDesigner.startDatePicker.getModel().getValue();
				df = new SimpleDateFormat("yyyy-MM-dd");
				startDate = df.format(selectedStartDate);
				//JOptionPane.showMessageDialog(null,reportDate);
				saleProd.add(startDate);
				//System.out.println(startDate);
			} catch (Exception ex) {
				System.out.println("Please select some Date!!");
			}
			

			try {
				selectedEndtDate = (Date) AddRentalDesigner.endDatePicker.getModel().getValue();
				df = new SimpleDateFormat("yyyy-MM-dd");
				endDate = df.format(selectedEndtDate);
				//JOptionPane.showMessageDialog(null,reportDate);
				saleProd.add(endDate);
			} catch (Exception ex) {
				System.out.println("Please select some Date!!");
			}
			
			
			AddRentalDesigner.tableModel.addRow(saleProd);
		}
		*/
	}
	
	public static void btnSelect() {
		new WHLocationPickup().getWHLocationPickup();
		
	}
	
	public static void mouseclick() {
		indexSelect=AddRentalDesigner.tableCust.getSelectedRow();
		String locId=AddRentalDesigner.tableCust.getValueAt(indexSelect, 0).toString();
		String locStatus=AddRentalDesigner.tableCust.getValueAt(indexSelect, 1).toString();
		Double locPrice=(Double) AddRentalDesigner.tableCust.getValueAt(indexSelect, 2);
		//String locRemark=AddRentalDesigner.tableCust.getValueAt(indexSelect, 3).toString();
		

		click_m =new pickupLoc(locId,locStatus,locPrice);
		
		
		AddRentalDesigner.btnEdit.setEnabled(true);
		AddRentalDesigner.btnDelete.setEnabled(true);
	}
	
	public static void btnDelete() {
		
		WHLocationPickup.list.remove(click_m);
		AddRentalDesigner.tableModel.removeRow(indexSelect);
		//WHLocationPickup.list.remove(arg0);
		
		AddRentalDesigner.btnEdit.setEnabled(false);
		AddRentalDesigner.btnDelete.setEnabled(false);
		System.out.println(indexSelect);
		//new WHLocationPickup().getWHLocationPickup();
		AddRentalDesigner.lblSumTotal.setText(Double.toString(getsum()));
		
	}
	
	public static void clickWH() {
		int totalRow=AddRentalDesigner.tableCust.getRowCount()-1;
		while(totalRow > -1) {
			AddRentalDesigner.tableModel.removeRow(totalRow);
			totalRow--;
		}
		int row=0;
		for(pickupLoc pickupLoc : WHLocationPickup.list) {
			AddRentalDesigner.tableModel.addRow(new Object[0]);
			AddRentalDesigner.tableModel.setValueAt(pickupLoc.getLocId(),row,0);
			AddRentalDesigner.tableModel.setValueAt(pickupLoc.getLocStatus(),row,1);
			AddRentalDesigner.tableModel.setValueAt(pickupLoc.getLocPrice(),row,2);
			row++;
		}

		AddRentalDesigner.tableCust.setModel(AddRentalDesigner.tableModel);
		System.out.println("Sum:"+getsum());
		AddRentalDesigner.lblSumTotal.setText(Double.toString(getsum()));
	}
	
	public static void clickbtnsave() {
		Date d1 = new Date();
		//System.out.println(df.format(selectedDate));
		if(formValidation()) {
			ArrayList<Customers> cuts2 = Customers.listAllCustomers("cust_name LIKE'%"+AddRentalDesigner.cbCustName.getItemAt(AddRentalDesigner.cbCustName.getSelectedIndex()).toString()+"%'", "");		
			if(!cuts2.isEmpty()) {
				for (Customers cut1 : cuts2) {
					custID=cut1.getCustomerID();
					if(Global.currentUser != null) {
						System.out.println(Global.currentUser.getUsername());
					}
				}
			}
			
			try {
				selectedStartDate = (Date) AddRentalDesigner.startDatePicker.getModel().getValue();
				df = new SimpleDateFormat("yyyy-MM-dd");
				startDate = df.format(selectedStartDate);
				//JOptionPane.showMessageDialog(null,reportDate);

				//System.out.println(startDate);
			} catch (Exception ex) {
				System.out.println("Please select some Date!!");
			}
			

			try {
				selectedEndtDate = (Date) AddRentalDesigner.endDatePicker.getModel().getValue();
				df = new SimpleDateFormat("yyyy-MM-dd");
				endDate = df.format(selectedEndtDate);
				//JOptionPane.showMessageDialog(null,reportDate);

			} catch (Exception ex) {
				System.out.println("Please select some Date!!");
			}

			int rentalMaxId = 0;
			Rental.addNewRental(custID, d1, selectedStartDate, selectedEndtDate, Float.parseFloat(AddRentalDesigner.lblSumTotal.getText()), Global.currentUser.getUserID(), "t");
			
			ArrayList<Rental> rentals = Rental.listAllRental();
			if(!rentals.isEmpty()) {
				for (Rental rental : rentals) {

					rentalMaxId=rental.getInvNo();
					

				}
			}
			
			System.out.println("MaxRentId:"+rentalMaxId);
			for(pickupLoc pickupLoc : WHLocationPickup.list) {

				//WHLocationPickupDesigner.lbl[pickupLoc.gets_loc()].setBackground(Color.GREEN);
				RentDetail.addNewRentDetail(rentalMaxId, pickupLoc.getLocId(), 1, 0.0, pickupLoc.getLocPrice());
				Warehouses.updateWarehouseInfo(pickupLoc.getLocId(), WHStatus.FULL, pickupLoc.getLocPrice(), pickupLoc.getLocRemark());
				
			}
			
			cleartxt();

		}

		
	}
	
	public static void cleartxt() {
		AddRentalDesigner.txtRentId.setText("");
		AddRentalDesigner.txtLocId.setText("");
		AddRentalDesigner.startDatePicker.getModel().setValue(null);
		AddRentalDesigner.endDatePicker.getModel().setValue(null);
		//AddRentalDesigner.endDatePicker.getJFormattedTextField().setText("");
		AddRentalDesigner.cbCustName.setSelectedIndex(0);
		AddRentalDesigner.lblSumTotal.setText("");

		int totalRow=AddRentalDesigner.tableCust.getRowCount()-1;
		while(totalRow > -1) {
			AddRentalDesigner.tableModel.removeRow(totalRow);
			totalRow--;
		}
		WHLocationPickup.list.clear();
	}
	
	
	public static boolean formValidation() {
		int totalRow=AddRentalDesigner.tableCust.getRowCount();
		String txt="กรุณากรอกข้อมูลต่อไปนี้ให้ครบ\n";
		int check=0;
		if(AddRentalDesigner.cbCustName.getSelectedIndex() == 0) {

			txt +="-ชื่อลูกค้า\n";
			check=1;
		}
		/*
		if(AddRentalDesigner.txtLocId.getText().isEmpty()){
			txt +="-รหัสตำแหน่ง\n";
			check=1;
		
		}
		*/
		if(totalRow<=0) {
			txt +="-คลังสินค้า\n";
			check=1;
		}
		if((Date) AddRentalDesigner.startDatePicker.getModel().getValue()==null) {
			txt +="-วันเริ่มต้น\n";
			check=1;
		}

		if((Date) AddRentalDesigner.endDatePicker.getModel().getValue()==null) {
			txt +="-วันสิ้นสุด\n";
			check=1;
		}

		if(check==1) {
			JOptionPane.showMessageDialog(null, txt, "แจ้งเตือน",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			return true;
		}

	}
}
