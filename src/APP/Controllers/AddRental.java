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
	public static void getAddRental() {
		addRental.setVisible(true);
		cleartxt();
		showdata();

	}
	
    public static double getsum(){
        int rowscount = AddRentalDesigner.tableCust.getRowCount();
        double sum=0;
        for(int i =0; i< rowscount; i++){
            sum=sum+Double.valueOf(AddRentalDesigner.tableCust.getValueAt(i,2).toString());
        }
        return sum;
        
    }
    
    public static void getsumtotal() {
		AddRentalDesigner.lblSumTotal.setText(Double.toString(getsum()));
		AddRentalDesigner.lblVat2.setText(Double.toString(getsum()*7/100));
		AddRentalDesigner.lblTotal2.setText(Double.toString(getsum()+(getsum()*7/100)-Double.parseDouble(AddRentalDesigner.txtDiscount2.getText())));
    }
    

	
	public static void getMaxId() {
		int maxID=0;
		try {
			ArrayList<Rental> rentals2 = Rental.listAllRental("", "");
			if(!rentals2.isEmpty()) {
				for (Rental rental1 : rentals2) {
					maxID=rental1.getInvNo();
				}
				AddRentalDesigner.txtRentId.setText(String.valueOf(maxID+1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void showdata() {
		getMaxId();

		AddRentalDesigner.cbCustName.removeAllItems();
		AddRentalDesigner.cbCustName.addItem("กรุษาเลือกชื่อลูกค้า");
		ArrayList<Customers> cuts = Customers.listAllCustomers("", "cust_id ASC");
		if(!cuts.isEmpty()) {
			for (Customers cut : cuts) {
				AddRentalDesigner.cbCustName.addItem(cut.getCustomerName());
			}
		}

		AddRentalDesigner.txtDiscount2.setText("0");
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
		//AddRentalDesigner.lblSumTotal.setText(Double.toString(getsum()));
		getsumtotal();
		
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
		//System.out.println("Sum:"+getsum());
		getsumtotal();
		/*
		AddRentalDesigner.lblSumTotal.setText(Double.toString(getsum()));
		AddRentalDesigner.lblVat2.setText(Double.toString(getsum()*7/100));
		AddRentalDesigner.lblTotal2.setText(Double.toString((getsum()*7/100)-Double.parseDouble(AddRentalDesigner.txtDiscount2.getText())));
		*/
	}
	
	static int rentMaxId() {
		int rentalMaxId = 0;
		ArrayList<Rental> rentals = Rental.listAllRental();
		if(!rentals.isEmpty()) {
			for (Rental rental : rentals) {

				rentalMaxId=rental.getInvNo();
				

			}
			
		}
		return rentalMaxId;

	}
	
	public static void clickbtnsave() {
		Date d1 = new Date();

		String stime = new SimpleDateFormat("HH:mm:ss").format(d1.getTime());

		System.out.println(stime);
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

			
			Rental.addNewRental(custID, d1,stime, selectedStartDate, selectedEndtDate, Float.parseFloat(AddRentalDesigner.lblVat2.getText()), Float.parseFloat(AddRentalDesigner.txtDiscount2.getText()), Float.parseFloat(AddRentalDesigner.lblTotal2.getText()), Global.currentUser.getUserID(), "1");
			

			for(pickupLoc pickupLoc : WHLocationPickup.list) {

				//WHLocationPickupDesigner.lbl[pickupLoc.gets_loc()].setBackground(Color.GREEN);
				RentDetail.addNewRentDetail(rentMaxId(), pickupLoc.getLocId(), 1, 0.0, pickupLoc.getLocPrice());
				Warehouses.updateWarehouseInfo(pickupLoc.getLocId(), WHStatus.FULL, pickupLoc.getLocPrice(), pickupLoc.getLocRemark());
				
			}
			
			cleartxt();

		}

		
	}
	
	public static void cleartxt() {
		AddRentalDesigner.txtRentId.setText(String.valueOf(rentMaxId()+1));
		AddRentalDesigner.txtLocId.setText("");
		AddRentalDesigner.startDatePicker.getModel().setValue(null);
		AddRentalDesigner.endDatePicker.getModel().setValue(null);
		//AddRentalDesigner.endDatePicker.getJFormattedTextField().setText("");
		AddRentalDesigner.cbCustName.setSelectedIndex(0);
		AddRentalDesigner.lblSumTotal.setText("");

		AddRentalDesigner.lblVat2.setText("");
		AddRentalDesigner.txtDiscount2.setText("0");
		AddRentalDesigner.lblTotal2.setText("");
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
