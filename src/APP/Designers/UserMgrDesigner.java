package APP.Designers;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import APP.Controllers.*;
import DBCLS.*;

public class UserMgrDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnSearchCust,btnAdd,btnEdit,btnSave;
	public static JTextField txtSearchUser,txtPassword,txtUserId,txtUserName,txtUserPhone,txtUserEmail;
	public static DefaultTableModel tableModel;
	public static JTable tableUser;
	
	public UserMgrDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		//ค้นหา
		JLabel lblUserName=new JLabel("รายชื่อผู้ใช้ระบบ");
		lblUserName.setBounds(10, 10, 100, 25);
		pnlContent.add(lblUserName);
		
		txtSearchUser=new JTextField();
		txtSearchUser.setBounds(700, 10, 200, 25);
		txtSearchUser.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent keyevent) {
				new UserMgr().showdata();
			}
		});
		pnlContent.add(txtSearchUser);
		
		JButton btnSearchUser=new JButton("ค้นหา");
		btnSearchUser.setBounds(910, 10, 100, 25);
		pnlContent.add(btnSearchUser);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableUser=new JTable();
		Object data[][]= {
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				
		};
		String columns[]= {"รหัสผู้ใช้","Username","Password","หมายเชขโทรศัพท์ลูกค้า","อีเมลลูกค้า"};
		tableModel=new DefaultTableModel(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableUser.setModel(tableModel);
		tableUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new UserMgr().mouseclick();
			}
		});
		scrollTable.setViewportView(tableUser);
		pnlContent.add(scrollTable);
		
		
		//รายละเอียด
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("รายละเอียด"));
		pnlDetail.setBounds(1030, 10, 330, 350);
		
		JLabel lblUserId = new JLabel("รหัสผู้ใช้:");
		lblUserId.setBounds(10, 20, 100, 25);
		pnlDetail.add(lblUserId);
		
		txtUserId=new JTextField();
		txtUserId.setBounds(110, 20, 210, 25);
		pnlDetail.add(txtUserId);
		
		JLabel lbl_UserName=new JLabel("Username:");
		lbl_UserName.setBounds(10, 50, 100, 25);
		pnlDetail.add(lbl_UserName);
		
		txtUserName=new JTextField();
		txtUserName.setBounds(110, 50, 210, 25);
		pnlDetail.add(txtUserName);
		
		JLabel lbl_Password=new JLabel("Password:");
		lbl_Password.setBounds(10, 80, 100, 25);
		pnlDetail.add(lbl_Password);
		
		txtPassword=new JTextField();
		txtPassword.setBounds(110, 80, 210, 25);
		pnlDetail.add(txtPassword);	
				
		JLabel lbl_UserPhone=new JLabel("เบอร์โทรศัพท์:");
		lbl_UserPhone.setBounds(10, 110, 100, 25);
		pnlDetail.add(lbl_UserPhone);
		
		txtUserPhone=new JTextField();
		txtUserPhone.setBounds(110, 110, 210, 25);
		pnlDetail.add(txtUserPhone);		
		
		
		JLabel lbl_UserEmail=new JLabel("อีเมล (Email):");
		lbl_UserEmail.setBounds(10, 140, 100, 25);
		pnlDetail.add(lbl_UserEmail);
		
		txtUserEmail=new JTextField();
		txtUserEmail.setBounds(110, 140, 210, 25);
		pnlDetail.add(txtUserEmail);	
		
				
		
		btnAdd=new JButton("เพิ่ม");
		btnAdd.setBounds(110, 290, 70, 25);
		btnAdd.addActionListener(this);
		pnlDetail.add(btnAdd);			
		
		btnEdit=new JButton("แก้ไข");
		btnEdit.setBounds(180, 290, 70, 25);
		btnEdit.addActionListener(this);
		pnlDetail.add(btnEdit);				
		
		btnSave=new JButton("บันทึก");
		btnSave.setBounds(250, 290, 70, 25);
		btnSave.addActionListener(this);
		pnlDetail.add(btnSave);	
		
		pnlContent.add(pnlDetail);
		
		//รายละเอียด
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder(""));
		pnlBottom1.setBounds(1030, 370, 330, 130);
		
		
		pnlContent.add(pnlBottom1);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAdd) {

			new UserMgr().clickbtnadd();
		}else if(e.getSource()==btnEdit) {
			new UserMgr().clickbtnedit();
			
		}else if(e.getSource()==btnSave) {
			new UserMgr().clickbtnsave();
			
		}

	}
}
