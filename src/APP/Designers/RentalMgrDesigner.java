package APP.Designers;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class RentalMgrDesigner extends DefaultDesigner{
	
	public RentalMgrDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		
		//ค้นหา
		JLabel lblRentName=new JLabel("รายการการเช่า");
		lblRentName.setBounds(10, 10, 100, 25);
		pnlContent.add(lblRentName);
		
		JLabel lblRentId=new JLabel("เลขที่ใบแจ้งหนี้");
		lblRentId.setBounds(710, 10, 100, 25);
		pnlContent.add(lblRentId);
		
		JTextField txtRentId=new JTextField();
		txtRentId.setBounds(810, 10, 200, 25);
		pnlContent.add(txtRentId);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		JTable tableCust=new JTable();
		Object data[][]= {
				{null,null,null},
				{null,null,null},
				{null,null,null},
				
		};
		String columns[]= {"รหัสตำแหน่ง","ระยะเวลา","จำนวนเงิน"};
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
		pnlDetail.setBorder(BorderFactory.createTitledBorder("ข้อมูลลูกค้า"));
		pnlDetail.setBounds(1030, 10, 330, 350);
		
		JLabel lblCustId = new JLabel("เลขที่ใบแจ้งหนี้:");
		lblCustId.setBounds(10, 20, 100, 25);
		pnlDetail.add(lblCustId);
		
		JTextField txtCustId=new JTextField();
		txtCustId.setBounds(110, 20, 210, 25);
		pnlDetail.add(txtCustId);
		
		JLabel lbl_CustName=new JLabel("ชื่อลูกก้า:");
		lbl_CustName.setBounds(10, 50, 100, 25);
		pnlDetail.add(lbl_CustName);
		
		JTextField txtCustName=new JTextField();
		txtCustName.setBounds(110, 50, 210, 25);
		pnlDetail.add(txtCustName);
		
		JLabel lbl_CustAddr=new JLabel("รหัสตำแหน่ง:");
		lbl_CustAddr.setBounds(10, 80, 100, 25);
		pnlDetail.add(lbl_CustAddr);
		
		JTextField txtLocId=new JTextField();
		txtLocId.setBounds(110, 80, 210, 25);
		pnlDetail.add(txtLocId);
		
		
		JLabel lblRentStart = new JLabel("วันเริ่มต้น:");
		lblRentStart.setBounds(10, 110, 100, 25);
		pnlDetail.add(lblRentStart);		
		
		JTextField txtRentStart=new JTextField();
		txtRentStart.setBounds(110, 110, 160, 25);
		pnlDetail.add(txtRentStart);	
		
		JButton btnRentStart=new JButton("...");
		btnRentStart.setBounds(270, 110, 50, 25);
		pnlDetail.add(btnRentStart);	
		
		JLabel lblRentEnd = new JLabel("วันสิ้นสุด:");
		lblRentEnd.setBounds(10, 140, 100, 25);
		pnlDetail.add(lblRentEnd);		
		
		JTextField txtRentEnd=new JTextField();
		txtRentEnd.setBounds(110, 140, 160, 25);
		pnlDetail.add(txtRentEnd);	
		
		JButton btnRentEnd=new JButton("...");
		btnRentEnd.setBounds(270, 140, 50, 25);
		pnlDetail.add(btnRentEnd);	
		
		JLabel lbl_Amount=new JLabel("จำนวนเงิน:");
		lbl_Amount.setBounds(10, 170, 100, 25);
		pnlDetail.add(lbl_Amount);
		
		JTextField txtAmount=new JTextField();
		txtAmount.setBounds(110, 170, 210, 25);
		pnlDetail.add(txtAmount);
		
		JButton btnAdd=new JButton("เพิ่ม");
		btnAdd.setBounds(10, 270, 80, 25);
		pnlDetail.add(btnAdd);			

		
		JButton btnEdit=new JButton("แก้ไข");
		btnEdit.setBounds(120, 270, 80, 25);
		pnlDetail.add(btnEdit);			
		
		JButton btnSave=new JButton("บันทึก");
		btnSave.setBounds(230, 270, 80, 25);
		pnlDetail.add(btnSave);				
		
		JButton btnRentCancle=new JButton("ยกเลิกการเช่า");
		btnRentCancle.setBounds(10, 310, 120, 25);
		btnRentCancle.setBackground(Color.RED);
		pnlDetail.add(btnRentCancle);	
		
		pnlContent.add(pnlDetail);
		
		//รายละเอียด
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder(""));
		pnlBottom1.setBounds(1030, 370, 330, 130);
		
		pnlContent.add(pnlBottom1);
		


				

	}
}
