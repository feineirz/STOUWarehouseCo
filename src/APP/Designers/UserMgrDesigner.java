package APP.Designers;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class UserMgrDesigner extends DefaultDesigner{
	
	public UserMgrDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		
		//ค้นหา
		JLabel lblUserName=new JLabel("รายชื่อผู้ใช้ระบบ");
		lblUserName.setBounds(10, 10, 100, 25);
		pnlContent.add(lblUserName);
		
		JTextField txtSearchCust=new JTextField();
		txtSearchCust.setBounds(700, 10, 200, 25);
		pnlContent.add(txtSearchCust);
		
		JButton btnSearchUser=new JButton("ค้นหา");
		btnSearchUser.setBounds(910, 10, 100, 25);
		pnlContent.add(btnSearchUser);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		JTable tableCust=new JTable();
		Object data[][]= {
				{null,null,null,null},
				{null,null,null,null},
				{null,null,null,null},
				
		};
		String columns[]= {"รหัสผู้ใช้","ชื่อผู้ใช้","หมายเชขโทรศัพท์ลูกค้า","อีเมลลูกค้า"};
		DefaultTableModel tableModel=new DefaultTableModel(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCust.setModel(tableModel);
		scrollTable.setViewportView(tableCust);
		pnlContent.add(scrollTable);
		
		
		//รายละเอียด
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("รายละเอียด"));
		pnlDetail.setBounds(1030, 10, 330, 350);
		
		JLabel lblUserId = new JLabel("รหัสผู้ใช้:");
		lblUserId.setBounds(10, 20, 100, 25);
		pnlDetail.add(lblUserId);
		
		JTextField txtUserId=new JTextField();
		txtUserId.setBounds(110, 20, 210, 25);
		pnlDetail.add(txtUserId);
		
		JLabel lbl_UserName=new JLabel("ชื่อผู้ใช้:");
		lbl_UserName.setBounds(10, 50, 100, 25);
		pnlDetail.add(lbl_UserName);
		
		JTextField txtUserName=new JTextField();
		txtUserName.setBounds(110, 50, 210, 25);
		pnlDetail.add(txtUserName);
		
				
		JLabel lbl_UserPhone=new JLabel("เบอร์โทรศัพท์:");
		lbl_UserPhone.setBounds(10, 80, 100, 25);
		pnlDetail.add(lbl_UserPhone);
		
		JTextField txtUserPhone=new JTextField();
		txtUserPhone.setBounds(110, 80, 210, 25);
		pnlDetail.add(txtUserPhone);		
		
		
		JLabel lbl_UserEmail=new JLabel("อีเมล (Email):");
		lbl_UserEmail.setBounds(10, 110, 100, 25);
		pnlDetail.add(lbl_UserEmail);
		
		JTextField txtUserEmail=new JTextField();
		txtUserEmail.setBounds(110, 110, 210, 25);
		pnlDetail.add(txtUserEmail);	
		
		JButton btnAdd=new JButton("เพิ่ม");
		btnAdd.setBounds(110, 290, 70, 25);
		pnlDetail.add(btnAdd);			
		
		JButton btnEdit=new JButton("แก้ไข");
		btnEdit.setBounds(180, 290, 70, 25);
		pnlDetail.add(btnEdit);				
		
		JButton btnSave=new JButton("บันทึก");
		btnSave.setBounds(250, 290, 70, 25);
		pnlDetail.add(btnSave);	
		
		pnlContent.add(pnlDetail);
		
		//รายละเอียด
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder(""));
		pnlBottom1.setBounds(1030, 370, 330, 130);
		
		
		pnlContent.add(pnlBottom1);

	}
}
