package APP.Controllers;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;
import APP.Designers.UserMgrDesigner;
//import APP.Designers.CustomerMgrDesigner;
import APP.Designers.DefaultDesigner;
import DBCLS.*;


public class UserMgr {
	// Preload Assets
	static boolean btnAddClicked=false;
	static boolean btnEditClicked=false;
	static int maxId=0;
		
	static JFrame addUser = new UserMgrDesigner();
	/*
	public static void main(String[] arg) {
		


		
	}
	*/
	public static void getusermgr() {
		addUser.setVisible(true);
		showdata();
		textlock();
		
		
	}
	
	
	
	public static void showdata() {
		
		try {
			int totalRow=UserMgrDesigner.tableUser.getRowCount()-1;
			while(totalRow > -1) {
				UserMgrDesigner.tableModel.removeRow(totalRow);
				totalRow--;
				
			}
			int row=0;
			
		
			
			String search = UserMgrDesigner.txtSearchUser.getText().trim();
			ArrayList<Users> users = Users.listAllUsers("username LIKE'%"+search+"%'", "");
			if(!users.isEmpty()) {
				for (Users user : users) {

					UserMgrDesigner.tableModel.addRow(new Object[0]);
					UserMgrDesigner.tableModel.setValueAt(user.getUserID(),row,0);
					UserMgrDesigner.tableModel.setValueAt(user.getUsername(),row,1);
					UserMgrDesigner.tableModel.setValueAt(user.getPasswordMD5(),row,2);
					UserMgrDesigner.tableModel.setValueAt(user.getPhone(),row,3);
					UserMgrDesigner.tableModel.setValueAt(user.getEmail(),row,4);
					maxId=user.getUserID();
					row++;
				}
			}
			
			
			UserMgrDesigner.tableUser.setModel(UserMgrDesigner.tableModel);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void mouseclick() {
		int index=UserMgrDesigner.tableUser.getSelectedRow();
		
		UserMgrDesigner.txtUserId.setText(UserMgrDesigner.tableUser.getValueAt(index, 0).toString());
		
		UserMgrDesigner.txtUserName.setText(UserMgrDesigner.tableUser.getValueAt(index, 1).toString());
		UserMgrDesigner.txtPassword.setText(UserMgrDesigner.tableUser.getValueAt(index, 2).toString());
		UserMgrDesigner.txtUserPhone.setText(UserMgrDesigner.tableUser.getValueAt(index, 3).toString());
		UserMgrDesigner.txtUserEmail.setText(UserMgrDesigner.tableUser.getValueAt(index, 4).toString());
	
		UserMgrDesigner.btnEdit.setEnabled(true);
		

	}
	
	public static void clickbtnadd() {
		
		if(UserMgrDesigner.btnAdd.getText()=="เพิ่ม") {
			cleartxt();
			UserMgrDesigner.txtUserId.setText(String.valueOf(maxId+1));
			UserMgrDesigner.btnAdd.setText("ยกเลิก");
			textunlock();
			
			UserMgrDesigner.btnAdd.setEnabled(true);
			UserMgrDesigner.btnEdit.setEnabled(false);
			UserMgrDesigner.btnSave.setEnabled(true);
			btnAddClicked=true;
		}else if(UserMgrDesigner.btnAdd.getText()=="ยกเลิก") {
			UserMgrDesigner.btnAdd.setText("เพิ่ม");
			UserMgrDesigner.btnAdd.setEnabled(true);
			UserMgrDesigner.btnEdit.setEnabled(true);
			UserMgrDesigner.btnSave.setEnabled(false);
			cleartxt();
			textlock();
			btnEditClicked=false;	
			btnAddClicked=false;
			
		}
	}
	
	public static void clickbtnedit() {

		textunlock();
		UserMgrDesigner.btnAdd.setText("ยกเลิก");
		UserMgrDesigner.btnAdd.setEnabled(true);
		UserMgrDesigner.btnEdit.setEnabled(false);
		UserMgrDesigner.btnSave.setEnabled(true);
		btnEditClicked=true;	
		btnAddClicked=false;

	}
	
	public static void clickbtnsave() {
		
		if(formValidation()) {
			if(btnAddClicked) {
				Users.addNewUser(UserMgrDesigner.txtUserName.getText(), UserMgrDesigner.txtPassword.getText(),UserMgrDesigner.txtUserPhone.getText(), UserMgrDesigner.txtUserEmail.getText());
				
				btnAddClicked=false;
	
			}else if(btnEditClicked){
				Users.updateUserInfo(Integer.parseInt(UserMgrDesigner.txtUserId.getText()), UserMgrDesigner.txtUserName.getText(), UserMgrDesigner.txtPassword.getText(),UserMgrDesigner.txtUserPhone.getText(), UserMgrDesigner.txtUserEmail.getText());
				btnEditClicked=false;
				
				
			}
		
			UserMgrDesigner.btnAdd.setText("เพิ่ม");
			UserMgrDesigner.btnAdd.setEnabled(true);
			UserMgrDesigner.btnEdit.setEnabled(true);
			UserMgrDesigner.btnSave.setEnabled(false);
			cleartxt();
			textlock();
			showdata();
		}
		

	}
	
	public static void cleartxt() {
		UserMgrDesigner.txtUserId.setText("");
		UserMgrDesigner.txtUserName.setText("");
		UserMgrDesigner.txtPassword.setText("");
		UserMgrDesigner.txtUserPhone.setText("");
		UserMgrDesigner.txtUserEmail.setText("");
	}

	public static void textlock() {
		UserMgrDesigner.txtUserId.setEnabled(false);
		UserMgrDesigner.txtUserName.setEnabled(false);
		UserMgrDesigner.txtPassword.setEnabled(false);
		UserMgrDesigner.txtUserPhone.setEnabled(false);
		UserMgrDesigner.txtUserEmail.setEnabled(false);
		UserMgrDesigner.btnEdit.setEnabled(false);
		UserMgrDesigner.btnSave.setEnabled(false);
	}

	public static void textunlock() {
		UserMgrDesigner.txtUserId.setEnabled(false);
		UserMgrDesigner.txtUserName.setEnabled(true);
		UserMgrDesigner.txtPassword.setEnabled(true);
		UserMgrDesigner.txtUserPhone.setEnabled(true);
		UserMgrDesigner.txtUserEmail.setEnabled(true);
	}
	
	public static boolean formValidation() {

		String txt="กรุณากรอกข้อมูลต่อไปนี้ให้ครบ\n";
		int check=0;
		if(UserMgrDesigner.txtUserName.getText().isEmpty()) {
			
			//UserMgrDesigner.txtUserName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			//UserMgrDesigner.lbl_UserName.setForeground (Color.red);
			txt +="-Username\n";
			check=1;
		}
		if(UserMgrDesigner.txtPassword.getText().isEmpty()){
			txt +="-Password\n";
			check=1;
		}
		if(UserMgrDesigner.txtUserPhone.getText().isEmpty()){
			txt +="-เชอร์โทรศัพท์\n";
			check=1;
		}
		if(UserMgrDesigner.txtUserEmail.getText().isEmpty()){
			txt +="-อีเมล\n";
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
