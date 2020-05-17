package APP.Controllers;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import APP.Designers.*;
import DBCLS.*;

public class AddRental {
	// Preload Assets
	static int indexSelect;
	static JFrame addRental = new AddRentalDesigner();
	public static void main(String[] arg) {
		
		//defaultFrame.setVisible(true);
		addRental.setVisible(true);
		showdata();
		
		
	}
	
	public static void showdata() {
		ArrayList<Customers> cuts = Customers.listAllCustomers("", "");
		if(!cuts.isEmpty()) {
			for (Customers cut : cuts) {
				//System.out.printf("%S %S %S %S\n", cut.getCustomerID(), cut.getCustomerName(), cut.getPhone(), cut.getEmail());
				AddRentalDesigner.cbCustName.addItem(cut.getCustomerName());
			}
		}
		AddRentalDesigner.btnEdit.setEnabled(false);
		AddRentalDesigner.btnDelete.setEnabled(false);
	
	}
	
	public static void btnAdd() {

		Vector saleProd = new Vector();
		saleProd.add(AddRentalDesigner.txtLocId.getText());
		saleProd.add("2");
		saleProd.add("3");
		AddRentalDesigner.tableModel.addRow(saleProd);
		
	}
	
	public static void btnSelect() {
		new WHLocationPickup().getWHLocationPickup();
		
	}
	
	public static void mouseclick() {
		indexSelect=AddRentalDesigner.tableCust.getSelectedRow();
		System.out.println(indexSelect);
		AddRentalDesigner.btnEdit.setEnabled(true);
		AddRentalDesigner.btnDelete.setEnabled(true);
	}
	
	public static void btnDelete() {
		
		AddRentalDesigner.tableModel.removeRow(indexSelect);
		AddRentalDesigner.btnEdit.setEnabled(false);
		AddRentalDesigner.btnDelete.setEnabled(false);
		System.out.println(indexSelect);
		//new WHLocationPickup().getWHLocationPickup();
		
	}
}
