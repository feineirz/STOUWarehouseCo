package APP.Designers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import APP.Controllers.*;
import DBCLS.*;

public class WarehouseMgrDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnSearchCust,btnRemake,btnEdit,btnSave;
	public static JTextField txtSearchLoc,txtLocId,txtLocPrice,txtLocStatus,txtLocRemark,txtRentId,txtCustName,txtRentTotal,txtStartDate,txtEndDate,txtDadeLine;
	//public static DefaultTableModel tableModel;
	//public static JTable tableWh;
	
	static String btnA[];
	static String btnB[];
	public static String selectedLoc;
	public static JLabel[] lbl=new JLabel[168];
	static int a =0;
	public static int locSelected;
	static int lastSelectedLoc;
	public static Color lastBgColor;
	public static Color currentBgColor;
	public static JPanel pnlLeft,pnlDetail,pnlBottom1;
	public static JProgressBar pgbPregress;
	
	public WarehouseMgrDesigner() {
		
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
		//**progressbar
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension(1400, 700);
		Dimension pgbSize = new Dimension(frameSize.width / 2, 20);
		 
		
		//**progressbar
		
		pnlLeft=new JPanel();
		pnlLeft.setLayout(null);
		pnlLeft.setBounds(10, 10, 1000, 490);
		pnlLeft.setBackground(new Color(200,200,200));
		

		Font font = new Font("Tahoma", Font.PLAIN, 10);
		Border bdr = BorderFactory.createLineBorder(Color.black);
		Dimension btnSize = new Dimension(35,35);
		
		
		String[] rowID1 = {"A","B","","C","D","","E","F"};
		String slotID = "";
		Border border = BorderFactory.createLineBorder(Color.GREEN, 5);

		for(int i = 1; i < 9; i++){
			for(int j = 1; j <= 12; j++) {
				if(i != 3 && i != 6) {
					slotID = rowID1[i-1] + String.format("%02d", j);
					
					//System.out.println(a);
					int b=a;
					lbl[a] = new JLabel(slotID,SwingConstants.CENTER);
					lbl[a].setBorder(bdr);
					lbl[a].setFont(font);
					lbl[a].setOpaque(true);
					lbl[a].setBackground(Color.WHITE);
					lbl[a].setBounds(i*btnSize.width, j*btnSize.height, btnSize.width, btnSize.height);
					lbl[a].addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {

							selectedLoc=lbl[b].getText();
							locSelected=b;
							System.out.println("=>"+b);
							txtLocId.setText(lbl[b].getText().toString());
							//System.out.println("++>"+lastBgColor);
							//System.out.println(lbl[b].getBackground());
							currentBgColor=lbl[b].getBackground();
							
							if(b != lastSelectedLoc) {
								if(lbl[b].getBackground()==Color.WHITE || lbl[b].getBackground()==Color.RED || lbl[b].getBackground()==Color.YELLOW) {
									lbl[b].setBorder(border);
								}

								lbl[lastSelectedLoc].setBorder(bdr);
							}


							WarehouseMgr.mouseclick();
							lastSelectedLoc=b;
							lastBgColor=currentBgColor;



							
						}
					});
					pnlLeft.add(lbl[a]);
					a++;
				}
			}
		}
		
		
		
		String[] rowID2 = {"G","H","","I","J","","K","L","","M","N"};
		for(int i = 1; i < 12; i++) {
			for(int j = 10; j < 22; j++) {
				
				if(i != 3 && i != 6 && i != 9) {
					int b=a;
					
					slotID = rowID2[i-1] + String.format("%02d", j - 9);
					
					//System.out.println(a);
					
					lbl[a] = new JLabel(slotID,SwingConstants.CENTER);
					
					lbl[a].setBorder(bdr);
					lbl[a].setFont(font);
					lbl[a].setOpaque(true);
					lbl[a].setBackground(Color.WHITE);
					lbl[a].setBounds(j*btnSize.width, i*btnSize.height, btnSize.width, btnSize.height);
					lbl[a].addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {

							selectedLoc=lbl[b].getText();
							locSelected=b;
							System.out.println("=>"+b);
							txtLocId.setText(lbl[b].getText().toString());
							//System.out.println("++>"+lastBgColor);
							//System.out.println(lbl[b].getBackground());
							currentBgColor=lbl[b].getBackground();
							
							if(b != lastSelectedLoc) {
								if(lbl[b].getBackground()==Color.WHITE || lbl[b].getBackground()==Color.RED || lbl[b].getBackground()==Color.YELLOW) {
									lbl[b].setBorder(border);
								}

								lbl[lastSelectedLoc].setBorder(bdr);
							}
			

							WarehouseMgr.mouseclick();
							lastSelectedLoc=b;
							lastBgColor=currentBgColor;



							
						}
					});
					
					pnlLeft.add(lbl[a]);
					
					a++;
				}    
				
				
			}
		}
		
		//******progressbar
		/*
		pgbPregress = new JProgressBar(0,100);
		pgbPregress.setBounds(
			(frameSize.width - pgbSize.width) / 2,
			(frameSize.height - pgbSize.height) / 2,
			pgbSize.width,
			pgbSize.height
		);
		
		pgbPregress.setValue(0);
		pgbPregress.setStringPainted(true);
		pgbPregress.setString("");
		this.add(pgbPregress);
		 
		this.revalidate();
		this.repaint();
		*/
		//System.out.println("==>"+lbl[167].getText());
		

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
		
		pgbPregress = new JProgressBar(0,100);
		pgbPregress.setBounds(35,460,930,20);
		
		pgbPregress.setValue(0);
		pgbPregress.setStringPainted(true);
		pgbPregress.setString("");
		pnlLeft.add(pgbPregress);

		
		pnlContent.add(pnlLeft);
		
		
		
		
		//สถานะคลังสินค้า
		pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("สถานะคลังสินค้า"));
		pnlDetail.setBounds(1030, 10, 330, 240);
		
		JLabel lblLocId = new JLabel("เลขที่คลังสินค้า:");
		lblLocId.setBounds(10, 30, 100, 25);
		pnlDetail.add(lblLocId);
		
		txtLocId=new JTextField();
		txtLocId.setBounds(110, 30, 210, 25);
		pnlDetail.add(txtLocId);
		pnlContent.add(pnlDetail);
		
		JLabel lblPrice = new JLabel("ราคาเช่า:");
		lblPrice.setBounds(10, 60, 100, 25);
		pnlDetail.add(lblPrice);
		
		txtLocPrice=new JTextField();
		txtLocPrice.setBounds(110, 60, 210, 25);
		pnlDetail.add(txtLocPrice);
		pnlContent.add(pnlDetail);
		
		JLabel lblLocStatus = new JLabel("สถานะ:");
		lblLocStatus.setBounds(10, 90, 100, 25);
		pnlDetail.add(lblLocStatus);
		
		txtLocStatus=new JTextField();
		txtLocStatus.setBounds(110, 90, 210, 25);
		pnlDetail.add(txtLocStatus);
		pnlContent.add(pnlDetail);
		
		JLabel lblLocRemark = new JLabel("หมายเหตุ:");
		lblLocRemark.setBounds(10, 120, 100, 25);
		pnlDetail.add(lblLocRemark);
		
		txtLocRemark=new JTextField();
		txtLocRemark.setBounds(110, 120, 210, 25);
		pnlDetail.add(txtLocRemark);
		pnlContent.add(pnlDetail);
		
		btnEdit=new JButton("แก้ไข");
		btnEdit.setBounds(110, 170, 100, 25);
		btnEdit.addActionListener(this);
		pnlDetail.add(btnEdit);	
		
		btnSave=new JButton("บันทึก");
		btnSave.setBounds(220, 170, 100, 25);
		btnSave.addActionListener(this);
		pnlDetail.add(btnSave);	
		
		btnRemake=new JButton("ตั้งเป็นสถานะซ่อมบำรุง");
		btnRemake.setBounds(110, 200, 210, 25);
		btnRemake.addActionListener(this);
		pnlDetail.add(btnRemake);	
		
		//รายละเอียดผู้เช่า
		pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder("รายละเอียดผู้เช่า"));
		pnlBottom1.setBounds(1030, 260, 330, 240);
		
		JLabel lbl_RentId=new JLabel("เลขที่สัญญาเช่า:");
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
		txtRentTotal.setBounds(110, 90, 180, 25);
		pnlBottom1.add(txtRentTotal);
		
		JLabel lblRentTotal=new JLabel("วัน");
		lblRentTotal.setBounds(300, 90, 30, 25);
		pnlBottom1.add(lblRentTotal);
		
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
		txtDadeLine.setBounds(110, 180, 180, 25);
		pnlBottom1.add(txtDadeLine);
		
		JLabel lblDadeLine=new JLabel("วัน");
		lblDadeLine.setBounds(300, 180, 30, 25);
		pnlBottom1.add(lblDadeLine);
		
		pnlContent.add(pnlBottom1);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnRemake) {
			
			
			if(currentBgColor==Color.YELLOW) {
				lastBgColor=Color.WHITE;
			}else if(currentBgColor==Color.WHITE) {
				lastBgColor=Color.YELLOW;
			}
			

			new WarehouseMgr().btnRemake();
			
			

		}else if(e.getSource()==btnEdit) {
			new WarehouseMgr().clickbtnedit();
			
		}else if(e.getSource()==btnSave) {
			if(currentBgColor==Color.YELLOW) {
				lastBgColor=Color.WHITE;
			}else if(currentBgColor==Color.WHITE) {
				lastBgColor=Color.YELLOW;
			}
			new WarehouseMgr().clickbtnsave();
			
		}
		
	}
	

}
