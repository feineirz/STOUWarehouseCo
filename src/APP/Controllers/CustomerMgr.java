package APP.Controllers;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import APP.Designers.CustomerMgrDesigner;
import DBCLS.*;

import java.awt.Color;
import java.util.*; 

public class CustomerMgr{
	// Preload Assets

	static JFrame cust = new CustomerMgrDesigner();
	
	static boolean btnAddClicked=false;
	static boolean btnEditClicked=false;
	static int maxId=0;
	
	//public static void main(String[] arg){
		
		//defaultFrame.setVisible(true);

		
		//System.out.println(dbcls.listAllCustomers("cust_id",""));
		//dbcls.listAllCustomers("","");
		//System.out.println(dbcls.listAllCustomers("cust_id='11'","").size());

		//cus.txtSearchCust.setText(dbcls.getCustomerName());
		//cus.txtSearchCust.setText("xxxxxxx");
		/*
		for(int i=0; i<dbcls.listAllCustomers("cust_id='11'","").size(); i++) {
			System.out.println("=>"+dbcls.getCustomerName());
			for(int x=0; x<5; x++) {
				//System.out.println("===>"+x+dbcls.getCustomerName());
			}
			
			
		}
		*/
		/*
		ArrayList<Users> users = Users.listAllUsers("", "");
		  if(!users.isEmpty()) {
		   for (Users user : users) {
		    System.out.printf("%S %S %S %S\n", user.getUserID(), user.getUsername(), user.getPhone(), user.getEmail());
		   }
		  }
		  */
		/*
		ArrayList<Customers> cuts = Customers.listAllCustomers("", "");
		if(!cuts.isEmpty()) {
			for (Customers cut : cuts) {
				System.out.printf("%S %S %S %S\n", cut.getCustomerID(), cut.getCustomerName(), cut.getPhone(), cut.getEmail());
			}
		}
		*/


	//}
	public static void getcustomerMgr() {
		cust.setVisible(true);
		textlock();
		showdata();
	}
	
	public static void showdata() {
		
		try {
			int totalRow=CustomerMgrDesigner.tableCust.getRowCount()-1;
			while(totalRow > -1) {
				CustomerMgrDesigner.tableModel.removeRow(totalRow);
				totalRow--;
			}
			int row=0;
			
			/*
			String search = cus.txtSearchCust.getText().trim();
			String sql="SELECT * FROM customers";
			ResultSet rs=conn.createStatement().executeQuery(sql);

			while(rs.next()) {
				cus.tableModel.addRow(new Object[0]);
				cus.tableModel.setValueAt(rs.getString("cust_id"),row,0);
				cus.tableModel.setValueAt(rs.getString("cust_name"),row,1);
				cus.tableModel.setValueAt(rs.getString("address"),row,2);
				cus.tableModel.setValueAt(rs.getString("phone"),row,3);
				cus.tableModel.setValueAt(rs.getString("email"),row,4);
				row++;
			}
			*/
			
			
			String search = CustomerMgrDesigner.txtSearchCust.getText().trim();
			ArrayList<Customers> cuts = Customers.listAllCustomers("cust_name LIKE'%"+search+"%'", "");
			if(!cuts.isEmpty()) {
				for (Customers cut : cuts) {
					//System.out.printf("%S %S %S %S\n", cut.getCustomerID(), cut.getCustomerName(), cut.getPhone(), cut.getEmail());
					CustomerMgrDesigner.tableModel.addRow(new Object[0]);
					CustomerMgrDesigner.tableModel.setValueAt(cut.getCustomerID(),row,0);
					CustomerMgrDesigner.tableModel.setValueAt(cut.getCustomerName(),row,1);
					CustomerMgrDesigner.tableModel.setValueAt(cut.getAddress(),row,2);
					CustomerMgrDesigner.tableModel.setValueAt(cut.getPhone(),row,3);
					CustomerMgrDesigner.tableModel.setValueAt(cut.getFax(),row,4);
					CustomerMgrDesigner.tableModel.setValueAt(cut.getEmail(),row,5);
					maxId=cut.getCustomerID();
					row++;
				}
			}
			
			
			CustomerMgrDesigner.tableCust.setModel(CustomerMgrDesigner.tableModel);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void mouseclick() {
		int index=CustomerMgrDesigner.tableCust.getSelectedRow();
		CustomerMgrDesigner.txtCustId.setText(CustomerMgrDesigner.tableCust.getValueAt(index, 0).toString());
		CustomerMgrDesigner.txtCustName.setText(CustomerMgrDesigner.tableCust.getValueAt(index, 1).toString());
		CustomerMgrDesigner.txtCustAddr.setText(CustomerMgrDesigner.tableCust.getValueAt(index, 2).toString());
		CustomerMgrDesigner.txtCustPhone.setText(CustomerMgrDesigner.tableCust.getValueAt(index, 3).toString());
		CustomerMgrDesigner.txtCustFax.setText(CustomerMgrDesigner.tableCust.getValueAt(index, 4).toString());
		CustomerMgrDesigner.txtCustEmail.setText(CustomerMgrDesigner.tableCust.getValueAt(index, 5).toString());
	}
	
	public static void clickbtnadd() {
		
		if(CustomerMgrDesigner.btnAdd.getText()=="เพิ่ม") {
			cleartxt();
			CustomerMgrDesigner.txtCustId.setText(String.valueOf(maxId+1));
			CustomerMgrDesigner.btnAdd.setText("ยกเลิก");
			textunlock();
			
			CustomerMgrDesigner.btnAdd.setEnabled(true);
			CustomerMgrDesigner.btnEdit.setEnabled(false);
			CustomerMgrDesigner.btnSave.setEnabled(true);
			btnAddClicked=true;
		}else if(CustomerMgrDesigner.btnAdd.getText()=="ยกเลิก") {
			CustomerMgrDesigner.btnAdd.setText("เพิ่ม");
			CustomerMgrDesigner.btnAdd.setEnabled(true);
			CustomerMgrDesigner.btnEdit.setEnabled(true);
			CustomerMgrDesigner.btnSave.setEnabled(false);
			cleartxt();
			textlock();
			btnEditClicked=false;	
			btnAddClicked=false;
			
		}
	}
	
	public static void clickbtnedit() {

		textunlock();
		CustomerMgrDesigner.btnAdd.setText("ยกเลิก");
		CustomerMgrDesigner.btnAdd.setEnabled(true);
		CustomerMgrDesigner.btnEdit.setEnabled(false);
		CustomerMgrDesigner.btnSave.setEnabled(true);
		btnEditClicked=true;	
		btnAddClicked=false;

	}
	
	public static void clickbtnsave() {
		
		if(formValidation()) {
			if(btnAddClicked) {
				Customers.addNewCustomer(CustomerMgrDesigner.txtCustName.getText(), CustomerMgrDesigner.txtCustAddr.getText(), CustomerMgrDesigner.txtCustPhone.getText(), CustomerMgrDesigner.txtCustFax.getText(), CustomerMgrDesigner.txtCustEmail.getText());
				btnAddClicked=false;
	
			}else if(btnEditClicked){
				Customers.updateCustomerInfo(Integer.parseInt(CustomerMgrDesigner.txtCustId.getText()), CustomerMgrDesigner.txtCustName.getText(), CustomerMgrDesigner.txtCustAddr.getText(),CustomerMgrDesigner.txtCustPhone.getText(), CustomerMgrDesigner.txtCustFax.getText(), CustomerMgrDesigner.txtCustEmail.getText());
				btnEditClicked=false;
				
				
			}
		
			CustomerMgrDesigner.btnAdd.setText("เพิ่ม");
			CustomerMgrDesigner.btnAdd.setEnabled(true);
			CustomerMgrDesigner.btnEdit.setEnabled(true);
			CustomerMgrDesigner.btnSave.setEnabled(false);
			cleartxt();
			textlock();
			showdata();
		}
		

	}
	
	public static void cleartxt() {
		CustomerMgrDesigner.txtCustId.setText("");
		CustomerMgrDesigner.txtCustName.setText("");
		CustomerMgrDesigner.txtCustAddr.setText("");
		CustomerMgrDesigner.txtCustPhone.setText("");
		CustomerMgrDesigner.txtCustFax.setText("");
		CustomerMgrDesigner.txtCustEmail.setText("");
	}

	public static void textlock() {
		CustomerMgrDesigner.txtCustId.setEnabled(false);
		CustomerMgrDesigner.txtCustName.setEnabled(false);
		CustomerMgrDesigner.txtCustAddr.setEnabled(false);
		CustomerMgrDesigner.txtCustPhone.setEnabled(false);
		CustomerMgrDesigner.txtCustFax.setEnabled(false);
		CustomerMgrDesigner.txtCustEmail.setEnabled(false);
		CustomerMgrDesigner.btnSave.setEnabled(false);
	}

	public static void textunlock() {
		//CustomerMgrDesigner.txtCustId.setEnabled(true);
		CustomerMgrDesigner.txtCustName.setEnabled(true);
		CustomerMgrDesigner.txtCustAddr.setEnabled(true);
		CustomerMgrDesigner.txtCustPhone.setEnabled(true);
		CustomerMgrDesigner.txtCustFax.setEnabled(true);
		CustomerMgrDesigner.txtCustEmail.setEnabled(true);
	}
	
	public static boolean formValidation() {
		boolean checkfrm=CustomerMgrDesigner.txtCustName.getText().isEmpty();
		if(checkfrm) {
			System.out.println("ว่างงงงงงงงงงงงงงง");
		}
		String txt="กรุณากรอกข้อมูลต่อไปนี้ให้ครบ\n";
		int check=0;
		if(CustomerMgrDesigner.txtCustName.getText().isEmpty()) {
			
			CustomerMgrDesigner.txtCustName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			CustomerMgrDesigner.lbl_CustName.setForeground (Color.red);
			txt +="-ชื่อลูกค้า\n";
			check=1;
		}
		if(CustomerMgrDesigner.txtCustAddr.getText().isEmpty()){
			txt +="-ที่อยู่ลูกค้า\n";
			check=1;
		}
		if(CustomerMgrDesigner.txtCustPhone.getText().isEmpty()){
			txt +="-เชอร์โทรศัพท์ลูกค้า\n";
			check=1;
		}
		if(CustomerMgrDesigner.txtCustFax.getText().isEmpty()){
			txt +="-เบอร์โทรศารลูกค้า\n";
			check=1;
		}
		if(CustomerMgrDesigner.txtCustEmail.getText().isEmpty()){
			txt +="-ฮีเมลลูกค้า\n";
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
