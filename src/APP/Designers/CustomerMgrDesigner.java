package APP.Designers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import DBCLS.*;
import APP.Controllers.*;

public class CustomerMgrDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnSearchCust,btnAdd,btnEdit,btnSave;
	public static JTextField txtSearchCust,txtCustId,txtCustName,txtCustPhone,txtCustFax,txtCustEmail;
	public static JTextArea txtCustAddr;
	public static DefaultTableModel tableModel;
	public static JLabel lbl_CustName;
	public static JTable tableCust;
	//static Customers c=new Customers();
	public CustomerMgrDesigner() {
		
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		//ค้นหา
		JLabel lblCustName=new JLabel("รายชื่อลูกค้า");
		lblCustName.setBounds(10, 10, 100, 25);
		pnlContent.add(lblCustName);
		
		txtSearchCust=new JTextField();
		txtSearchCust.setBounds(700, 10, 200, 25);
		txtSearchCust.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent keyevent) {
				new CustomerMgr().showdata();
			}
		});
		pnlContent.add(txtSearchCust);
		
		btnSearchCust=new JButton("ค้นหา");
		btnSearchCust.setBounds(910, 10, 100, 25);
		pnlContent.add(btnSearchCust);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableCust=new JTable();
		Object data[][]= {
				{null,null,null,null,null,null},
				{null,null,null,null,null,null},
				{null,null,null,null,null,null},
				
		};
		String columns[]= {"เลขที่ลูกค้า","ชื่อ-นามสกุล","ที่อยู่ลูกค้า","หมายเลขโทรศัพท์","โทรสาร","อีเมล"};
		tableModel=new DefaultTableModel(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCust.setModel(tableModel);
		tableCust.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new CustomerMgr().mouseclick();
			}
		});
		scrollTable.setViewportView(tableCust);
		pnlContent.add(scrollTable);
		
		
		//รายละเอียด
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("รายละเอียด"));
		pnlDetail.setBounds(1030, 10, 330, 350);
		
		JLabel lblCustId = new JLabel("รหัสลูกค้า:");
		lblCustId.setBounds(10, 20, 100, 25);
		pnlDetail.add(lblCustId);
		
		txtCustId=new JTextField();
		txtCustId.setBounds(110, 20, 210, 25);
		pnlDetail.add(txtCustId);
		
		lbl_CustName=new JLabel("ชื่อลูกก้า:");
		lbl_CustName.setBounds(10, 50, 100, 25);
		pnlDetail.add(lbl_CustName);
		
		txtCustName=new JTextField();
		txtCustName.setBounds(110, 50, 210, 25);
		pnlDetail.add(txtCustName);
		
		
		JLabel lbl_CustAddr=new JLabel("ที่อยู่ลูกก้า:");
		lbl_CustAddr.setBounds(10, 80, 100, 25);
		pnlDetail.add(lbl_CustAddr);
		
		txtCustAddr=new JTextArea();
		txtCustAddr.setBounds(110, 80, 210, 100);
		pnlDetail.add(txtCustAddr);
		
		JLabel lbl_CustPhone=new JLabel("เบอร์โทรศัพท์:");
		lbl_CustPhone.setBounds(10, 190, 100, 25);
		pnlDetail.add(lbl_CustPhone);
		
		txtCustPhone=new JTextField();
		txtCustPhone.setBounds(110, 190, 210, 25);
		pnlDetail.add(txtCustPhone);		
		
		JLabel lbl_CustFax=new JLabel("โทรสาร:");
		lbl_CustFax.setBounds(10, 220, 100, 25);
		pnlDetail.add(lbl_CustFax);
		
		txtCustFax=new JTextField();
		txtCustFax.setBounds(110, 220, 210, 25);
		pnlDetail.add(txtCustFax);	
		
		JLabel lbl_CustEmail=new JLabel("อีเมล (Email):");
		lbl_CustEmail.setBounds(10, 250, 100, 25);
		pnlDetail.add(lbl_CustEmail);
		
		txtCustEmail=new JTextField();
		txtCustEmail.setBounds(110, 250, 210, 25);
		pnlDetail.add(txtCustEmail);	
		
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
			//c.addNewCustomer(txtCustName.getText(), txtCustAddr.getText(), txtCustPhone.getText(), txtCustFax.getText(), txtCustEmail.getText());
			/*
			CustomerMgr CustMgr=new CustomerMgr();
			new CustomerMgr().addcust();
			new CustomerMgr().showdata();
			*/
			new CustomerMgr().clickbtnadd();
		}else if(e.getSource()==btnEdit) {
			new CustomerMgr().clickbtnedit();
			
		}else if(e.getSource()==btnSave) {
			new CustomerMgr().clickbtnsave();
			
		}

	}
	/*
	public static void showdata() {
		Connection conn = new DBConnector().getDBConnection();
		
		try {
			String sql="SELECT * FROM customers";
			ResultSet rs=conn.createStatement().executeQuery(sql);
			int row=0;
			
			
			while(rs.next()) {
				tableModel.addRow(new Object[0]);
				tableModel.setValueAt(rs.getString("cust_id"),row,0);
				tableModel.setValueAt(rs.getString("cust_name"),row,1);
				tableModel.setValueAt(rs.getString("address"),row,2);
				tableModel.setValueAt(rs.getString("phone"),row,3);
				tableModel.setValueAt(rs.getString("email"),row,4);
				row++;
			};
			tableCust.setModel(tableModel);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	


}
