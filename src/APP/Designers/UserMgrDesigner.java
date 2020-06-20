package APP.Designers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import APP.Controllers.*;
import DBCLS.*;

public class UserMgrDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnSearchCust,btnAdd,btnDelete,btnEdit,btnSave,btnSearchUser;
	public static JTextField txtSearchUser,txtPass,txtUserId,txtUserName,txtUserPhone,txtUserEmail;
	public static DefaultTableModel tableModel;
	public static JTable tableUser;
	public static JLabel lblTotalsum,lblTime;
	public static JPasswordField txtPassword;
	
	public UserMgrDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainMenu.getmain();
			}
		});
		//ค้นหา
		JPanel pnlcolor=new JPanel();
		pnlcolor.setBounds(10,10,10,30);
		pnlcolor.setBackground(Color.ORANGE);
		pnlContent.add(pnlcolor);
		
		JPanel pnlbar=new JPanel();
		pnlbar.setLayout(null);
		pnlbar.setBounds(10,10,1000,30);
		pnlbar.setBackground(Color.GRAY);
		pnlContent.add(pnlbar);
		
		JLabel lblHead=new JLabel("รายชื่อผู้ใช้ระบบ");
		lblHead.setBounds(20, 0, 300, 25);
		lblHead.setForeground(Color.ORANGE);
		lblHead.setFont(fontHead);
		pnlbar.add(lblHead);
		/*
		JLabel lblUserName=new JLabel("รายชื่อผู้ใช้ระบบ");
		lblUserName.setBounds(10, 10, 100, 25);
		pnlContent.add(lblUserName);
		*/
		txtSearchUser=new JTextField();
		txtSearchUser.setBounds(700, 2, 200, 25);
		txtSearchUser.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent keyevent) {
				UserMgr.showdata();
			}
		});
		pnlbar.add(txtSearchUser);
		
		btnSearchUser=new JButton("ค้นหา");
		btnSearchUser.setBounds(910, 2, 90, 25);
		btnSearchUser.addActionListener(this);
		pnlbar.add(btnSearchUser);

		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableUser=new JTable();
		/*
		JTableHeader tableHeader = tableUser.getTableHeader();
		tableHeader.setBackground(Color.black);
		

		tableUser.setOpaque(true);
		*/
		tableUser.setRowHeight(30);
		Object data[][]= {
				{null,null,null,null,null,null},

				
		};
		String columns[]= {"ลำดับ","UserID","Username","หมายเลขโทรศัพท์","อีเมล"};
		tableModel=new DefaultTableModel(data, columns) {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0: 
					//return ImageIcon.class;
					return Integer.class;
				case 1:
					return Integer.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;

					
				default:
					return String.class;
				}
			}
		};
		tableUser.setModel(tableModel);
		tableUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new UserMgr().mouseclick();
			}
		});
		tableUser.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableUser.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableUser.getColumnModel().getColumn(1).setPreferredWidth(350);
		tableUser.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableUser.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableUser.getColumnModel().getColumn(4).setPreferredWidth(250);

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
		txtUserName.setEnabled(false);
		pnlDetail.add(txtUserName);
		
		JLabel lbl_Password=new JLabel("Password:");
		lbl_Password.setBounds(10, 80, 100, 25);
		pnlDetail.add(lbl_Password);
		
		/*
		txtPassword=new JTextField();
		txtPassword.setBounds(110, 80, 210, 25);
		pnlDetail.add(txtPassword);	
		*/	
		txtPassword = new JPasswordField();
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
		btnAdd.setBounds(10, 240, 100, 25);
		btnAdd.addActionListener(this);
		pnlDetail.add(btnAdd);		
				
		btnEdit=new JButton("แก้ไข");
		btnEdit.setBounds(115, 240, 100, 25);
		btnEdit.addActionListener(this);
		pnlDetail.add(btnEdit);				
		
		btnSave=new JButton("บันทึก");
		btnSave.setBounds(220, 240, 100, 25);
		btnSave.addActionListener(this);
		pnlDetail.add(btnSave);	
		
		btnDelete=new JButton("ลบ");
		btnDelete.setBounds(10, 310, 100, 25);
		btnDelete.addActionListener(this);
		pnlDetail.add(btnDelete);
		
		pnlContent.add(pnlDetail);
		
		//รายละเอียด
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder(""));
		pnlBottom1.setBounds(1030, 370, 330, 130);
		
		
		pnlContent.add(pnlBottom1);
		
		
		lblTotalsum=new JLabel("จำนวนทั้งหมด: ");
		lblTotalsum.setBounds(10, 20, 200, 25);
		pnlFooter.add(lblTotalsum);


	}
	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAdd) {

			UserMgr.clickbtnadd();
		}else if(e.getSource()==btnDelete) {
			UserMgr.clickbtndelete();
			
		}else if(e.getSource()==btnEdit) {
			UserMgr.clickbtnedit();
			
		}else if(e.getSource()==btnSave) {
			UserMgr.clickbtnsave();
			
		}else if(e.getSource()==btnSearchUser) {
			UserMgr.showdata();
		}

	}
}
