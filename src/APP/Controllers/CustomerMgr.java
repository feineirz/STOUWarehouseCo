package APP.Controllers;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import APP.Designers.CustomerMgrDesigner;
import APP.Designers.UserMgrDesigner;
//import APP.Designers.UserMgrDesigner;
import DBCLS.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*; 

public class CustomerMgr{
	// Preload Assets

	static JFrame cust = new CustomerMgrDesigner();
	
	static boolean btnAddClicked=false;
	static boolean btnEditClicked=false;
	static int maxId=0;
	

	public static void getcustomerMgr() {
		cust.setVisible(true);
		textlock();
		loadCusts();
		//showdata();
	}
	
	public static void showdata() {
		
		
		btnEditClicked=false;	
		btnAddClicked=false;
		CustomerMgrDesigner.btnAdd.setEnabled(true);
		CustomerMgrDesigner.btnEdit.setEnabled(false);
		CustomerMgrDesigner.btnSave.setEnabled(false);
		CustomerMgrDesigner.btnAdd.setText("เพิ่ม");
		cleartxt();
		try {
			int totalRow=CustomerMgrDesigner.tableCust.getRowCount()-1;
			while(totalRow > -1) {
				CustomerMgrDesigner.tableModel.removeRow(totalRow);
				totalRow--;
			}
			int row=0;
			
			String search = CustomerMgrDesigner.txtSearchCust.getText().trim();
			ArrayList<Customers> cuts = Customers.listAllCustomers("cust_id LIKE'%"+search+"%'OR address LIKE'%"+search+"%' OR cust_name LIKE'%"+search+"%' OR phone LIKE '%"+search+"%' OR fax LIKE '%"+search+"%' OR email LIKE '%"+search+"%'", "");
			if(!cuts.isEmpty()) {
				for (Customers cut : cuts) {
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
			CustomerMgrDesigner.lblTotalsum.setText("จำนวนทั้งหมด: "+CustomerMgrDesigner.tableCust.getRowCount()+" รายการ");
			
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
		CustomerMgrDesigner.btnEdit.setEnabled(true);
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
		CustomerMgrDesigner.btnEdit.setEnabled(false);
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
			/*
			CustomerMgrDesigner.txtCustName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			CustomerMgrDesigner.lbl_CustName.setForeground (Color.red);
			*/
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
	
	
	public static void loadCusts() {
		class loadCustsBack extends SwingWorker<Void, Void> {

			private JProgressBar pb;
			private JDialog dialog;

			public loadCustsBack() {
				addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if ("progress".equalsIgnoreCase(evt.getPropertyName())) {
							if (dialog == null) {
								dialog = new JDialog();
								dialog.setTitle("Processing");
								dialog.setLayout(new GridBagLayout());
								dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
								GridBagConstraints gbc = new GridBagConstraints();
								gbc.insets = new Insets(2, 2, 2, 2);
								gbc.weightx = 1;
								gbc.gridy = 0;
								dialog.add(new JLabel("โหลดข้อมูลลูกค้า..."), gbc);
								pb = new JProgressBar();
								pb.setStringPainted(true);
								gbc.gridy = 1;
								dialog.add(pb, gbc);
								dialog.pack();
								dialog.setLocationRelativeTo(null);
								dialog.setModal(true);
								JDialog.setDefaultLookAndFeelDecorated(true); 
								dialog.setVisible(true);
							}
							pb.setValue(getProgress());
						}
					}

				});
			}

			@Override
			protected void done() {
				if (dialog != null) {
					//showdata();
					dialog.dispose();
				}
			}

			@Override
			protected Void doInBackground() throws Exception {
				/*
				for (int index = 0; index < 100; index++) {
					setProgress(index);
					Thread.sleep(30);

				}
				*/
				
				/*
				int ProgressType=0;
				ArrayList<Rental> rents = Rental.listAllRental("", "");
				if(rents.size() > 0) {
					Integer i = 0;
					Integer max = rents.size();
					//pb.setMaximum(max);
					for(Rental rent:rents) {
						System.out.println(rent.getInvNo());
						setProgress((int)((++i*100)/max));

						Thread.sleep(50);
					}
				}
				*/

				btnEditClicked=false;	
				btnAddClicked=false;
				CustomerMgrDesigner.btnAdd.setEnabled(true);
				CustomerMgrDesigner.btnEdit.setEnabled(false);
				CustomerMgrDesigner.btnSave.setEnabled(false);
				CustomerMgrDesigner.btnAdd.setText("เพิ่ม");
				cleartxt();
				try {
					int totalRow=CustomerMgrDesigner.tableCust.getRowCount()-1;
					while(totalRow > -1) {
						CustomerMgrDesigner.tableModel.removeRow(totalRow);
						totalRow--;
					}
					int row=0;
					
					String search = CustomerMgrDesigner.txtSearchCust.getText().trim();
					ArrayList<Customers> cuts = Customers.listAllCustomers("cust_id LIKE'%"+search+"%'OR address LIKE'%"+search+"%' OR cust_name LIKE'%"+search+"%' OR phone LIKE '%"+search+"%' OR fax LIKE '%"+search+"%' OR email LIKE '%"+search+"%'", "");
					Integer i = 0;
					Integer max = cuts.size();
					if(!cuts.isEmpty()) {
						for (Customers cut : cuts) {
							setProgress((int)((row*100)/max));
							
							CustomerMgrDesigner.tableModel.addRow(new Object[0]);
							CustomerMgrDesigner.tableModel.setValueAt(cut.getCustomerID(),row,0);
							CustomerMgrDesigner.tableModel.setValueAt(cut.getCustomerName(),row,1);
							CustomerMgrDesigner.tableModel.setValueAt(cut.getAddress(),row,2);
							CustomerMgrDesigner.tableModel.setValueAt(cut.getPhone(),row,3);
							CustomerMgrDesigner.tableModel.setValueAt(cut.getFax(),row,4);
							CustomerMgrDesigner.tableModel.setValueAt(cut.getEmail(),row,5);
							maxId=cut.getCustomerID();
							row++;
							Thread.sleep(30);
						}
					}
					CustomerMgrDesigner.lblTotalsum.setText("จำนวนทั้งหมด: "+CustomerMgrDesigner.tableCust.getRowCount()+" รายการ");
					
					CustomerMgrDesigner.tableCust.setModel(CustomerMgrDesigner.tableModel);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		}
		new loadCustsBack().execute();
	}
	
	
}
