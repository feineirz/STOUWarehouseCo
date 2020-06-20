package APP.Controllers;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


import javax.swing.*;
import APP.Designers.UserMgrDesigner;
import APP.Designers.AddRentalDesigner;
//import APP.Designers.CustomerMgrDesigner;
import APP.Designers.DefaultDesigner;
import APP.Designers.MainMenuDesigner;
import DBCLS.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		//DefaultDesigner.setUIFont (new javax.swing.plaf.FontUIResource("Tahoma",Font.ITALIC,12));
		btnAddClicked=true;
		btnEditClicked=false;

		//showdata();
		loadUser();
		
		textlock();
		/*
		showDate();
		clock();
		*/
		UserMgrDesigner.btnAdd.setEnabled(true);
		UserMgrDesigner.btnDelete.setEnabled(false);
		UserMgrDesigner.btnEdit.setEnabled(false);
		UserMgrDesigner.btnSave.setEnabled(false);
		UserMgrDesigner.btnAdd.setText("����");
		
	}
	
	
	
	public static void showdata() {

		cleartxt();
		
		try {
			int totalRow=UserMgrDesigner.tableUser.getRowCount()-1;
			while(totalRow > -1) {
				UserMgrDesigner.tableModel.removeRow(totalRow);
				totalRow--;
				
			}
			int row=0;
			
		
			
			String search = UserMgrDesigner.txtSearchUser.getText().trim();
			ArrayList<Users> users = Users.listAllUsers("user_id LIKE'%"+search+"%' OR username LIKE '%"+search+"%' OR phone LIKE '%"+search+"%' OR email LIKE '%"+search+"%'", "user_id ASC");
			if(!users.isEmpty()) {
				for (Users user : users) {

					UserMgrDesigner.tableModel.addRow(new Object[0]);
					UserMgrDesigner.tableModel.setValueAt(row+1,row,0);
					UserMgrDesigner.tableModel.setValueAt(user.getUserID(),row,1);
					UserMgrDesigner.tableModel.setValueAt(user.getUsername(),row,2);
					UserMgrDesigner.tableModel.setValueAt(user.getPhone(),row,3);
					UserMgrDesigner.tableModel.setValueAt(user.getEmail(),row,4);
					maxId=user.getUserID();
					row++;
				}
			}
			UserMgrDesigner.lblTotalsum.setText("�ӹǹ������: "+UserMgrDesigner.tableUser.getRowCount()+" ��¡��");
			
			
			UserMgrDesigner.tableUser.setModel(UserMgrDesigner.tableModel);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
	public static void mouseclick() {
		int index=UserMgrDesigner.tableUser.getSelectedRow();
		
		UserMgrDesigner.txtUserId.setText(UserMgrDesigner.tableUser.getValueAt(index, 1).toString());
		UserMgrDesigner.txtUserName.setText(UserMgrDesigner.tableUser.getValueAt(index, 2).toString());
		//UserMgrDesigner.txtPassword.setText(UserMgrDesigner.tableUser.getValueAt(index, 2).toString());
		UserMgrDesigner.txtUserPhone.setText(UserMgrDesigner.tableUser.getValueAt(index, 3).toString());
		UserMgrDesigner.txtUserEmail.setText(UserMgrDesigner.tableUser.getValueAt(index, 4).toString());
	
		UserMgrDesigner.btnEdit.setEnabled(true);
		UserMgrDesigner.btnDelete.setEnabled(true);
		

	}
	
	public static void clickbtnadd() {
		
		if(UserMgrDesigner.btnAdd.getText()=="����") {
			cleartxt();
			UserMgrDesigner.txtUserName.setEnabled(true);
			UserMgrDesigner.txtUserId.setText(String.valueOf(maxId+1));
			UserMgrDesigner.btnAdd.setText("¡��ԡ");
			textunlock();
			
			UserMgrDesigner.btnAdd.setEnabled(true);
			UserMgrDesigner.btnEdit.setEnabled(false);
			UserMgrDesigner.btnSave.setEnabled(true);
			btnAddClicked=true;
		}else if(UserMgrDesigner.btnAdd.getText()=="¡��ԡ") {
			cleartxt();
			UserMgrDesigner.txtUserName.setEnabled(false);
			UserMgrDesigner.btnAdd.setText("����");
			UserMgrDesigner.btnAdd.setEnabled(true);
			UserMgrDesigner.btnEdit.setEnabled(true);
			UserMgrDesigner.btnSave.setEnabled(false);
			
			textlock();
			btnEditClicked=false;	
			btnAddClicked=false;
			
		}
	}
	
	public static void clickbtndelete() {
		int index=UserMgrDesigner.tableUser.getSelectedRow();
		
		int input = JOptionPane.showConfirmDialog(null, "�س��ͧ���ź User: "+UserMgrDesigner.tableUser.getValueAt(index, 2).toString()+" �� ���� ���","����͹",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if(input==0) {
			Users.deleteUser((int)UserMgrDesigner.tableUser.getValueAt(index, 1));
			UserMgrDesigner.btnDelete.setEnabled(false);
			showdata();
			UserMgrDesigner.btnEdit.setEnabled(false);
		}
	}
	
	static void showDate() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		UserMgrDesigner.lblTotalsum.setText("�ѹ���: "+s.format(d));

	}
	
	static void showTime() {
		new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date d = new Date();
				SimpleDateFormat s = new SimpleDateFormat("kk:mm:ss");
				UserMgrDesigner.lblTime.setText("����: "+s.format(d));
				
			}
			
		}).start();
	}
	
	public static void clock() {
		Thread clock=new Thread() {
			public void run() {
				try {
					for(;;) {
						Date d = new Date();
						SimpleDateFormat s = new SimpleDateFormat("kk:mm:ss");
						UserMgrDesigner.lblTime.setText("����: "+s.format(d));
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
	
	public static void clickbtnedit() {

		textunlock();
		UserMgrDesigner.btnAdd.setText("¡��ԡ");
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
				
				if(UserMgrDesigner.txtPassword.getText().isEmpty()) {
					
					Users.updateUserInfo(Integer.parseInt(UserMgrDesigner.txtUserId.getText()), UserMgrDesigner.txtUserName.getText(), UserMgrDesigner.txtUserPhone.getText(), UserMgrDesigner.txtUserEmail.getText());
					btnEditClicked=false;
				}else {
					
					Users.updateUserInfo(Integer.parseInt(UserMgrDesigner.txtUserId.getText()), UserMgrDesigner.txtUserName.getText(), UserMgrDesigner.txtPassword.getText(),UserMgrDesigner.txtUserPhone.getText(), UserMgrDesigner.txtUserEmail.getText());
					btnEditClicked=false;
				}

				
				
			}
		
			UserMgrDesigner.btnAdd.setText("����");
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
		//UserMgrDesigner.txtUserName.setEnabled(false);
		UserMgrDesigner.txtPassword.setEnabled(false);
		UserMgrDesigner.txtUserPhone.setEnabled(false);
		UserMgrDesigner.txtUserEmail.setEnabled(false);
		UserMgrDesigner.btnEdit.setEnabled(false);
		UserMgrDesigner.btnSave.setEnabled(false);
	}

	public static void textunlock() {
		UserMgrDesigner.txtUserId.setEnabled(false);
		//UserMgrDesigner.txtUserName.setEnabled(true);
		UserMgrDesigner.txtPassword.setEnabled(true);
		UserMgrDesigner.txtUserPhone.setEnabled(true);
		UserMgrDesigner.txtUserEmail.setEnabled(true);
	}
	
	public static boolean formValidation() {

		String txt="��سҡ�͡�����ŵ��仹�����ú\n";
		int check=0;
		if(UserMgrDesigner.txtUserName.getText().isEmpty()) {
			
			//UserMgrDesigner.txtUserName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			//UserMgrDesigner.lbl_UserName.setForeground (Color.red);
			txt +="-Username\n";
			check=1;
		}
		if(btnAddClicked==true) {
			if(UserMgrDesigner.txtPassword.getText().equals("")){
				txt +="-Password\n";
				check=1;
			}
		}
		/*
		if(UserMgrDesigner.txtPassword.getText().isEmpty()){
			txt +="-Password\n";
			check=1;
		}
		*/
		if(UserMgrDesigner.txtUserPhone.getText().isEmpty()){
			txt +="-�������Ѿ��\n";
			check=1;
		}
		if(UserMgrDesigner.txtUserEmail.getText().isEmpty()){
			txt +="-�����\n";
			check=1;
		}

		if(check==1) {
			JOptionPane.showMessageDialog(null, txt, "����͹",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			return true;
		}

	}
	
	public static void loadUser() {
		class loadUserBack extends SwingWorker<Void, Void> {

			private JProgressBar pb;
			private JDialog dialog;

			public loadUserBack() {
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
								dialog.add(new JLabel("��Ŵ�����ż����..."), gbc);
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

				cleartxt();
				try {
					int totalRow=UserMgrDesigner.tableUser.getRowCount()-1;
					while(totalRow > -1) {
						UserMgrDesigner.tableModel.removeRow(totalRow);
						totalRow--;
						
					}
					int row=0;
					
				
					
					String search = UserMgrDesigner.txtSearchUser.getText().trim();
					ArrayList<Users> users = Users.listAllUsers("user_id LIKE'%"+search+"%' OR username LIKE '%"+search+"%' OR phone LIKE '%"+search+"%' OR email LIKE '%"+search+"%'", "user_id ASC");
					Integer i = 0;
					Integer max = users.size();
					if(!users.isEmpty()) {
						for (Users user : users) {
							//System.out.println(users.getInt("inv_no"));
							setProgress((int)((row*100)/max));

							UserMgrDesigner.tableModel.addRow(new Object[0]);
							UserMgrDesigner.tableModel.setValueAt(row+1,row,0);
							UserMgrDesigner.tableModel.setValueAt(user.getUserID(),row,1);
							UserMgrDesigner.tableModel.setValueAt(user.getUsername(),row,2);
							UserMgrDesigner.tableModel.setValueAt(user.getPhone(),row,3);
							UserMgrDesigner.tableModel.setValueAt(user.getEmail(),row,4);
							maxId=user.getUserID();
							row++;
							Thread.sleep(30);
						}
					}
					UserMgrDesigner.lblTotalsum.setText("�ӹǹ������: "+UserMgrDesigner.tableUser.getRowCount()+" ��¡��");
					
					
					UserMgrDesigner.tableUser.setModel(UserMgrDesigner.tableModel);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		}
		new loadUserBack().execute();
	}
	
	
	
}
