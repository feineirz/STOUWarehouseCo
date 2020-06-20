package APP.Controllers;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

import APP.Designers.*;
import DBCLS.Customers;
import DBCLS.Users;

public class AddCustomer {
	// Preload Assets
	static AddCustomerDesigner addCust = new AddCustomerDesigner();
	static int maxuserId;
	public static void getaddcustomer() {
		
		//defaultFrame.setVisible(true);
		//addCust.setVisible(true);

		cleartxt();

		addCust.setModal(true);
		addCust.setVisible(true);
		
	}
	
	public static void btnAdd() {
		if(formValidation()) {
			Customers.addNewCustomer(AddCustomerDesigner.txtCustName.getText(), AddCustomerDesigner.txtCustAddr.getText(), AddCustomerDesigner.txtCustPhone.getText(), AddCustomerDesigner.txtCustFax.getText(), AddCustomerDesigner.txtCustEmail.getText());
			new AddRental().showdata();
			cleartxt();
			addCust.setVisible(false);
		}
		

	}
	
	static int maxuserId() {

		ArrayList<Customers> cuts = Customers.listAllCustomers("", "cust_id ASC");
		if(!cuts.isEmpty()) {
			for (Customers cut : cuts) {

				maxuserId=cut.getCustomerID()+1;
				

			}
		}
		return maxuserId;
	}
	
	public static boolean formValidation() {

		String txt="กรุณากรอกข้อมูลต่อไปนี้ให้ครบ\n";
		int check=0;
		if(AddCustomerDesigner.txtCustName.getText().isEmpty()) {
			

			txt +="-ชื่อลูกค้า\n";
			check=1;
		}
		if(AddCustomerDesigner.txtCustAddr.getText().isEmpty()){
			txt +="-ที่อยู่ลูกค้า\n";
			check=1;
		}
		if(AddCustomerDesigner.txtCustPhone.getText().isEmpty()){
			txt +="-เบอร์โทรศัพท์ลูกค้า\n";
			check=1;
		}
		if(AddCustomerDesigner.txtCustFax.getText().isEmpty()){
			txt +="-เบอร์โทรสารลูกค้า\n";
			check=1;
		}
		if(AddCustomerDesigner.txtCustEmail.getText().isEmpty()){
			txt +="-อีเมลลูกค้า\n";
			check=1;
		}

		if(check==1) {
			JOptionPane.showMessageDialog(null, txt, "แจ้งเตือน",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			return true;
		}

	}
	
	public static void cleartxt() {
		AddCustomerDesigner.txtCustId.setText("");
		AddCustomerDesigner.txtCustName.setText("");
		AddCustomerDesigner.txtCustAddr.setText("");
		AddCustomerDesigner.txtCustPhone.setText("");
		AddCustomerDesigner.txtCustFax.setText("");
		AddCustomerDesigner.txtCustEmail.setText("");
		AddCustomerDesigner.txtCustId.setText(String.valueOf(maxuserId()));
	}
	
	
}
