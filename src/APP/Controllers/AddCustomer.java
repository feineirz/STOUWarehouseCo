package APP.Controllers;
import java.awt.Color;

import javax.swing.*;

import APP.Designers.*;
import DBCLS.Customers;

public class AddCustomer {
	// Preload Assets
	static JFrame addCust = new AddCustomerDesigner();
	public static void getaddcustomer() {
		
		//defaultFrame.setVisible(true);
		addCust.setVisible(true);
		cleartxt();
		
		
	}
	
	public static void btnAdd() {
		if(formValidation()) {
			Customers.addNewCustomer(AddCustomerDesigner.txtCustName.getText(), AddCustomerDesigner.txtCustAddr.getText(), AddCustomerDesigner.txtCustPhone.getText(), AddCustomerDesigner.txtCustFax.getText(), AddCustomerDesigner.txtCustEmail.getText());
			new AddRental().showdata();
			cleartxt();
			addCust.setVisible(false);
		}
		

	}
	
	public static boolean formValidation() {

		String txt="��سҡ�͡�����ŵ��仹�����ú\n";
		int check=0;
		if(AddCustomerDesigner.txtCustName.getText().isEmpty()) {
			

			txt +="-�����١���\n";
			check=1;
		}
		if(AddCustomerDesigner.txtCustAddr.getText().isEmpty()){
			txt +="-��������١���\n";
			check=1;
		}
		if(AddCustomerDesigner.txtCustPhone.getText().isEmpty()){
			txt +="-�������Ѿ���١���\n";
			check=1;
		}
		if(AddCustomerDesigner.txtCustFax.getText().isEmpty()){
			txt +="-����������١���\n";
			check=1;
		}
		if(AddCustomerDesigner.txtCustEmail.getText().isEmpty()){
			txt +="-������١���\n";
			check=1;
		}

		if(check==1) {
			JOptionPane.showMessageDialog(null, txt, "����͹",JOptionPane.ERROR_MESSAGE);
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
	}
	
	
}
