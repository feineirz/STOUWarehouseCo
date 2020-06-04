package APP.Controllers;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import APP.Designers.*;
import DBCLS.DBConnector;
import DBCLS.Warehouses;
import DBCLS.Warehouses.WHStatus;

public class WarehouseMgr {
	static JFrame wareHouse = new WarehouseMgrDesigner();
	
	static int x=0;
	
	// Preload Assets
	static boolean btnAddClicked=false;
	static boolean btnEditClicked=false;
	
	static boolean btnRemakeClicked=true;
	
	static int maxId=0;
	static String locStatClick;
	
	public static void main(String[] arg) {
		wareHouse.setVisible(true);
		textlock();
		x=0;
		showdata();
		WarehouseMgrDesigner.btnEdit.setEnabled(false);	
		WarehouseMgrDesigner.btnSave.setEnabled(false);	
		WarehouseMgrDesigner.btnRemake.setEnabled(false);	

		
	}
	
	public static void getWarehouseMgr() {
		wareHouse.setVisible(true);
		
		textlock();
		x=0;
		showdata();
		WarehouseMgrDesigner.btnEdit.setEnabled(false);	
		WarehouseMgrDesigner.btnSave.setEnabled(false);	
		WarehouseMgrDesigner.btnRemake.setEnabled(false);	
		

	}

	
	
	public static void showdata() {
		
		try {
			ArrayList<Warehouses> locs=Warehouses.listAllWarehouseLocation("", "");
			
			if(!locs.isEmpty()) {
				for (Warehouses loc : locs) {

					if(loc.getStatus().toString()=="FULL") {
						WarehouseMgrDesigner.lbl[x].setBackground(Color.RED);
					}else if(loc.getStatus().toString()=="MAINTENANCE") {
						WarehouseMgrDesigner.lbl[x].setBackground(Color.YELLOW);
					}else if(loc.getStatus().toString()=="EMPTY") {
						WarehouseMgrDesigner.lbl[x].setBackground(Color.WHITE);
					}
					x++;
				}
			}

			

		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public static void showdataClicked() {
		try {
			ArrayList<Warehouses> locs=Warehouses.listAllWarehouseLocation("loc_id='"+WarehouseMgrDesigner.selectedLoc+"'", "");
			
			if(!locs.isEmpty()) {
				for (Warehouses loc : locs) {
					WarehouseMgrDesigner.txtLocStatus.setText(loc.getStatus().toString());
					
					if(loc.getStatus().toString()=="FULL") {
						WarehouseMgrDesigner.lbl[WarehouseMgrDesigner.locSelected].setBackground(Color.RED);
					}else if(loc.getStatus().toString()=="MAINTENANCE") {
						WarehouseMgrDesigner.lbl[WarehouseMgrDesigner.locSelected].setBackground(Color.YELLOW);
					}else if(loc.getStatus().toString()=="EMPTY") {
						WarehouseMgrDesigner.lbl[WarehouseMgrDesigner.locSelected].setBackground(Color.WHITE);
					}
				
				}
			}

			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void mouseclick() {
		
		try {
			ArrayList<Warehouses> locs=Warehouses.listAllWarehouseLocation("loc_id='"+WarehouseMgrDesigner.selectedLoc+"'", "");
			
			if(!locs.isEmpty()) {
				for (Warehouses loc : locs) {
					WarehouseMgrDesigner.txtLocStatus.setText(loc.getStatus().toString());
					WarehouseMgrDesigner.txtLocPrice.setText(loc.getPrice().toString());
					WarehouseMgrDesigner.txtLocRemark.setText(loc.getRemark().toString());
					locStatClick=loc.getStatus().toString();
					if(loc.getStatus().toString()=="MAINTENANCE") {
						WarehouseMgrDesigner.btnRemake.setText("ยกเลิกสถานะซ่อมบำรุง");
						WarehouseMgrDesigner.btnRemake.setEnabled(true);
						
						WarehouseMgrDesigner.txtRentId.setText("");
						WarehouseMgrDesigner.txtCustName.setText("");
						WarehouseMgrDesigner.txtRentTotal.setText("");
						WarehouseMgrDesigner.txtStartDate.setText("");
						WarehouseMgrDesigner.txtEndDate.setText("");
						WarehouseMgrDesigner.txtDadeLine.setText("");
					}else if(loc.getStatus().toString()=="EMPTY") {
						WarehouseMgrDesigner.btnRemake.setText("ตั้งเป็นสถานะซ่อมบำรุง");
						WarehouseMgrDesigner.btnRemake.setEnabled(true);
						
						WarehouseMgrDesigner.txtRentId.setText("");
						WarehouseMgrDesigner.txtCustName.setText("");
						WarehouseMgrDesigner.txtRentTotal.setText("");
						WarehouseMgrDesigner.txtStartDate.setText("");
						WarehouseMgrDesigner.txtEndDate.setText("");
						WarehouseMgrDesigner.txtDadeLine.setText("");
					}else if(loc.getStatus().toString()=="FULL") {
						WarehouseMgrDesigner.btnRemake.setEnabled(false);
						Connection conn = new DBConnector().getDBConnection();
						try {

							String qry = "SELECT cus.cust_id, cus.cust_name, rents.inv_no, rents.start_date, rents.expire_date, rentdetail.rentdetail_id, rentdetail.loc_id, warehouses.status, warehouses.price, warehouses.remark FROM customers AS cus INNER JOIN rents ON cus.cust_id = rents.cust_id INNER JOIN rentdetail on rents.inv_no = rentdetail.inv_no INNER JOIN warehouses on rentdetail.loc_id = warehouses.loc_id WHERE rentdetail.loc_id = '"+WarehouseMgrDesigner.selectedLoc+"'";
							PreparedStatement stmt = conn.prepareStatement(qry);
							ResultSet rs = stmt.executeQuery();
							while(rs.next()) {
								WarehouseMgrDesigner.txtRentId.setText(rs.getString("inv_no"));
								WarehouseMgrDesigner.txtCustName.setText(rs.getString("cust_name"));
								WarehouseMgrDesigner.txtRentTotal.setText("");
								WarehouseMgrDesigner.txtStartDate.setText(rs.getString("start_date"));
								WarehouseMgrDesigner.txtEndDate.setText(rs.getString("expire_date"));
								WarehouseMgrDesigner.txtDadeLine.setText("");
							}

							conn.close();
							
						} catch (SQLException e) {
							try {
								conn.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							e.printStackTrace();
						}
					}
				}
				
				
			}
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		
	
		
		WarehouseMgrDesigner.btnEdit.setEnabled(true);	
		WarehouseMgrDesigner.btnSave.setEnabled(false);	


	}
	
	public static void clickbtnedit() {
		
		if(WarehouseMgrDesigner.btnEdit.getText()=="แก้ไข") {
			cleartxt();

			textunlock();
			WarehouseMgrDesigner.btnEdit.setText("ยกเลิก");
			WarehouseMgrDesigner.btnEdit.setEnabled(true);	
			WarehouseMgrDesigner.btnSave.setEnabled(true);	
			WarehouseMgrDesigner.btnRemake.setEnabled(true);
			btnAddClicked=true;
		}else if(WarehouseMgrDesigner.btnEdit.getText()=="ยกเลิก") {
			WarehouseMgrDesigner.btnEdit.setText("แก้ไข");
			WarehouseMgrDesigner.btnEdit.setEnabled(true);
			WarehouseMgrDesigner.btnSave.setEnabled(false);
			cleartxt();
			textlock();
			btnEditClicked=false;	
			btnAddClicked=false;
			
		}
	}
	
	
	public static void clickbtnsave() {
		
		if(formValidation()) {
			//if(btnAddClicked) {
				WHStatus locSta = null;
				//System.out.println(WarehouseMgrDesigner.txtLocStatus.getText());
				String txt=WarehouseMgrDesigner.txtLocStatus.getText().toString();
				

				if(locStatClick=="EMPTY") {
					locSta= WHStatus.EMPTY;
				}else if(locStatClick=="FULL"){
					System.out.println("ffffffffffffffffff");
					locSta= WHStatus.FULL;
				}else if(locStatClick=="MAINTENANCE"){
					locSta= WHStatus.MAINTENANCE;
				}
				
				
				
			Warehouses.updateWarehouseInfo(WarehouseMgrDesigner.txtLocId.getText(), locSta,Double.parseDouble(WarehouseMgrDesigner.txtLocPrice.getText()),WarehouseMgrDesigner.txtLocRemark.getText());
	
		
			WarehouseMgrDesigner.btnEdit.setText("แก้ไข");
			WarehouseMgrDesigner.btnEdit.setEnabled(true);
			WarehouseMgrDesigner.btnSave.setEnabled(false);
			cleartxt();
			textlock();
			x=0;
			showdata();
		}
		

	}
	
	public static void btnRemake() {
		if(WarehouseMgrDesigner.btnRemake.getText()=="ตั้งเป็นสถานะซ่อมบำรุง") {
			
			Warehouses.updateWarehouseInfo(WarehouseMgrDesigner.txtLocId.getText(), Warehouses.WHStatus.MAINTENANCE,Double.parseDouble(WarehouseMgrDesigner.txtLocPrice.getText()),WarehouseMgrDesigner.txtLocRemark.getText());
			WarehouseMgrDesigner.btnRemake.setText("ยกเลิกสถานะซ่อมบำรุง");
			WarehouseMgrDesigner.lastBgColor=Color.YELLOW;
			x=0;
			showdataClicked();
			
			
			btnRemakeClicked=true;
		}else if(WarehouseMgrDesigner.btnRemake.getText()=="ยกเลิกสถานะซ่อมบำรุง") {
			Warehouses.updateWarehouseInfo(WarehouseMgrDesigner.txtLocId.getText(), Warehouses.WHStatus.EMPTY,Double.parseDouble(WarehouseMgrDesigner.txtLocPrice.getText()),WarehouseMgrDesigner.txtLocRemark.getText());
			WarehouseMgrDesigner.btnRemake.setText("ตั้งเป็นสถานะซ่อมบำรุง");
			WarehouseMgrDesigner.lastBgColor=Color.WHITE;
			x=0;
			showdataClicked();
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
		WarehouseMgrDesigner.txtLocId.setEnabled(false);
		WarehouseMgrDesigner.txtLocPrice.setEnabled(false);
		WarehouseMgrDesigner.txtLocStatus.setEnabled(false);
		WarehouseMgrDesigner.txtLocRemark.setEnabled(false);

	}

	public static void textunlock() {
		WarehouseMgrDesigner.txtLocId.setEnabled(false);
		WarehouseMgrDesigner.txtLocPrice.setEnabled(true);
		WarehouseMgrDesigner.txtLocStatus.setEnabled(true);
		WarehouseMgrDesigner.txtLocRemark.setEnabled(true);
	}
	
	public static boolean formValidation() {

		String txt="กรุณากรอกข้อมูลต่อไปนี้ให้ครบ\n";
		int check=0;
		if(WarehouseMgrDesigner.txtLocPrice.getText().isEmpty()) {
			
			//UserMgrDesigner.txtUserName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			//UserMgrDesigner.lbl_UserName.setForeground (Color.red);
			txt +="-ราคา\n";
			check=1;
		}
		if(WarehouseMgrDesigner.txtLocStatus.getText().isEmpty()){
			txt +="-สถานะ\n";
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
