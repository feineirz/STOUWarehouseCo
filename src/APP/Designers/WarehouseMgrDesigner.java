package APP.Designers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import APP.Controllers.*;
import DBCLS.*;

public class WarehouseMgrDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnSearchCust,btnAdd,btnEdit,btnSave;
	public static JTextField txtSearchLoc,txtLocId,txtLocStatus,txtLocRemark,txtRentId,txtCustName,txtRentTotal,txtStartDate,txtEndDate,txtDadeLine;
	public static DefaultTableModel tableModel;
	public static JTable tableWh;
	static String btnA[];
	static String btnB[];
	public WarehouseMgrDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		JPanel pnlLeft=new JPanel();
		pnlLeft.setLayout(null);
		pnlLeft.setBounds(10, 10, 1000, 490);
		pnlLeft.setBackground(new Color(200,200,200));
		

		//JPanel pnlContainer = new JPanel();
		//pnlContainer.setLayout(null);

		Font font = new Font("Tahoma", Font.PLAIN, 10);
		Border bdr = BorderFactory.createLineBorder(Color.black);
		Dimension btnSize = new Dimension(35,35);

		String[] rowID1 = {"A","B","","C","D","","E","F"};
		String slotID = "";

		for(int i = 1; i < 9; i++){
			for(int j = 1; j <= 12; j++) {
				if(i != 3 && i != 6) {
					slotID = rowID1[i-1] + String.format("%02d", j);
					JLabel lbl = new JLabel(slotID,SwingConstants.CENTER);
					lbl.setBorder(bdr);
					lbl.setFont(font);
					lbl.setOpaque(true);
					lbl.setBackground(Color.WHITE);
					lbl.setBounds(i*btnSize.width, j*btnSize.height, btnSize.width, btnSize.height);
					pnlLeft.add(lbl);
				}
			}
		}

		String[] rowID2 = {"G","H","","I","J","","K","L","","M","N"};
		for(int i = 10; i < 22; i++) {
			for(int j = 1; j < 12; j++) {
				if(j != 3 && j != 6 && j != 9) {
					slotID = rowID2[j-1] + String.format("%02d", i - 9);
					JLabel lbl = new JLabel(slotID,SwingConstants.CENTER);
					lbl.setBorder(bdr);
					lbl.setFont(font);
					lbl.setOpaque(true);
					lbl.setBackground(Color.WHITE);
					lbl.setBounds(i*btnSize.width, j*btnSize.height, btnSize.width, btnSize.height);
					pnlLeft.add(lbl);
				}    
			}
		}

		//this.add(pnlContainer);
		
		JLabel lblStatusFree = new JLabel();
		lblStatusFree.setBounds(830, 150, 20, 20);
		lblStatusFree.setBorder(bdr);
		lblStatusFree.setBackground(Color.WHITE);
		lblStatusFree.setOpaque(true);
		pnlLeft.add(lblStatusFree);
		
		JLabel lbl_Free = new JLabel("ว่าง");
		lbl_Free.setBounds(860, 150, 100, 20);
		pnlLeft.add(lbl_Free);
		
		JLabel lblNotFree = new JLabel();
		lblNotFree.setBounds(830, 190, 20, 20);
		lblNotFree.setBorder(bdr);
		lblNotFree.setBackground(Color.RED);
		lblNotFree.setOpaque(true);
		pnlLeft.add(lblNotFree);
		
		JLabel lbl_NotFree = new JLabel("ไม่ว่าง");
		lbl_NotFree.setBounds(860, 190, 100, 20);
		pnlLeft.add(lbl_NotFree);
		
		JLabel lbl_Broken = new JLabel();
		lbl_Broken.setBounds(830, 230, 20, 20);
		lbl_Broken.setBorder(bdr);
		lbl_Broken.setBackground(Color.YELLOW);
		lbl_Broken.setOpaque(true);
		pnlLeft.add(lbl_Broken);
		
		JLabel lblBroken = new JLabel("ซ่อมบำรุง");
		lblBroken.setBounds(860, 230, 100, 20);
		pnlLeft.add(lblBroken);
		
		JLabel lbl_Rented = new JLabel();
		lbl_Rented.setBounds(830, 270, 20, 20);
		lbl_Rented.setBorder(bdr);
		lbl_Rented.setBackground(Color.GREEN);
		lbl_Rented.setOpaque(true);
		pnlLeft.add(lbl_Rented);
		
		JLabel lblRented = new JLabel("เลือก");
		lblRented.setBounds(860, 270, 100, 20);
		pnlLeft.add(lblRented);
		
		pnlContent.add(pnlLeft);
		
		
		
		
		//รายละเอียด
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("สถานะคลังสินค้า"));
		pnlDetail.setBounds(1030, 10, 330, 100);
		
		JLabel lblLocStatus = new JLabel("สถานะ:");
		lblLocStatus.setBounds(10, 30, 100, 25);
		pnlDetail.add(lblLocStatus);
		
		txtLocStatus=new JTextField();
		txtLocStatus.setBounds(110, 30, 210, 25);
		pnlDetail.add(txtLocStatus);
		pnlContent.add(pnlDetail);
		
		
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder("รายละเอียดผู้เช่า"));
		pnlBottom1.setBounds(1030, 150, 330, 350);
		
		JLabel lbl_RentId=new JLabel("เลขที่ใบแจ้างหนี้:");
		lbl_RentId.setBounds(10, 30, 100, 25);
		pnlBottom1.add(lbl_RentId);
		
		txtRentId=new JTextField();
		txtRentId.setBounds(110, 30, 210, 25);
		pnlBottom1.add(txtRentId);	
								
		JLabel lbl_CustName=new JLabel("ชื่อลูกค้า:");
		lbl_CustName.setBounds(10, 60, 100, 25);
		pnlBottom1.add(lbl_CustName);
		
		txtCustName=new JTextField();
		txtCustName.setBounds(110, 60, 210, 25);
		pnlBottom1.add(txtCustName);	
		
		JLabel lbl_RentTotal=new JLabel("ระยะเวลาเช่า:");
		lbl_RentTotal.setBounds(10, 90, 100, 25);
		pnlBottom1.add(lbl_RentTotal);
		
		txtRentTotal=new JTextField();
		txtRentTotal.setBounds(110, 90, 210, 25);
		pnlBottom1.add(txtRentTotal);
		
		JLabel lbl_StartDate=new JLabel("วันเริ่มต้น:");
		lbl_StartDate.setBounds(10, 120, 100, 25);
		pnlBottom1.add(lbl_StartDate);
		
		txtStartDate=new JTextField();
		txtStartDate.setBounds(110, 120, 210, 25);
		pnlBottom1.add(txtStartDate);
		
		JLabel lbl_EndDate=new JLabel("วันสิ้นสุด:");
		lbl_EndDate.setBounds(10, 150, 100, 25);
		pnlBottom1.add(lbl_EndDate);
		
		txtEndDate=new JTextField();
		txtEndDate.setBounds(110, 150, 210, 25);
		pnlBottom1.add(txtEndDate);
		
		JLabel lbl_DadeLine=new JLabel("เวลาคงเหลือ:");
		lbl_DadeLine.setBounds(10, 180, 100, 25);
		pnlBottom1.add(lbl_DadeLine);
		
		txtDadeLine=new JTextField();
		txtDadeLine.setBounds(110, 180, 210, 25);
		pnlBottom1.add(txtDadeLine);
		
		btnAdd=new JButton("ตั้งเป็นสถานะซ่อมบำรุง");
		btnAdd.setBounds(110, 300, 210, 25);
		btnAdd.addActionListener(this);
		pnlBottom1.add(btnAdd);			
		
		
		
		pnlContent.add(pnlBottom1);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
