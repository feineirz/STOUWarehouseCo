package APP.Controllers;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import APP.Designers.*;
import DBCLS.DBConnector;
import DBCLS.Warehouses;
import DBCLS.Warehouses.WHStatus;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WarehouseMgr {
	static JFrame wareHouse = new WarehouseMgrDesigner();
	
	static int x=0;
	
	// Preload Assets
	static boolean btnAddClicked=false;
	static boolean btnEditClicked=false;
	
	static boolean btnRemakeClicked=true;
	
	static int maxId=0;
	static String locStatClick;
	static Connection conn = DBConnector.getDBConnection();
	/*
	public static void main(String[] arg) {

		getWarehouseMgr();
		
	}
	*/
	
	public static void getWarehouseMgr() {

		wareHouse.setVisible(true);

		/*
		WarehouseMgrDesigner.pnlLeft.setVisible(false);
		WarehouseMgrDesigner.pnlDetail.setVisible(false);
		WarehouseMgrDesigner.pnlBottom1.setVisible(false);
		
		WarehouseMgrDesigner.pgbPregress.setVisible(true);
		*/
		clearLocStatus();
		textlock();
		x=0;
		listData();
		
		//showdata();
		

		
		
		WarehouseMgrDesigner.btnEdit.setEnabled(false);	
		WarehouseMgrDesigner.btnSave.setEnabled(false);	
		WarehouseMgrDesigner.btnRemake.setEnabled(false);	
		



	}
	
	
	public static void showdata() {
		
		try {
			
			ArrayList<Warehouses> locs=Warehouses.listAllWarehouseLocation("", "loc_id ASC");
			
			if(!locs.isEmpty()) {
				for (Warehouses loc : locs) {
					//Warehouses.updateWarehouseInfo(loc.getLocID(), WHStatus.EMPTY,200,"");
					if(loc.getStatus().toString()=="FULL") {
						
						WarehouseMgrDesigner.lbl[x].setBackground(Color.RED);
					}else if(loc.getStatus().toString()=="MAINTENANCE") {
						WarehouseMgrDesigner.lbl[x].setBackground(Color.YELLOW);
					}else if(loc.getStatus().toString()=="EMPTY") {
						WarehouseMgrDesigner.lbl[x].setBackground(Color.WHITE);
					}
					x++;
					System.out.println("=>"+loc.getLocID());
					
				}
			}
			showstate();
		
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void showstate() {
		try {
			String qry1="SELECT COUNT(*) FROM warehouses WHERE status='E'";
			Statement stmt1 = conn.createStatement();			
			ResultSet rs1 = stmt1.executeQuery(qry1);
			rs1.next();
			int count1 = rs1.getInt(1);
			WarehouseMgrDesigner.lbl_sFree.setText(String.valueOf(count1));
			
			String qry2="SELECT COUNT(*) FROM warehouses WHERE status='F'";
			Statement stmt2 = conn.createStatement();			
			ResultSet rs2 = stmt2.executeQuery(qry2);
			rs2.next();
			int count2 = rs2.getInt(1);
			WarehouseMgrDesigner.lbl_sNotFree.setText(String.valueOf(count2));
			
			String qry3="SELECT COUNT(*) FROM warehouses WHERE status='M'";
			Statement stmt3 = conn.createStatement();			
			ResultSet rs3 = stmt3.executeQuery(qry3);
			rs3.next();
			int count3 = rs3.getInt(1);
			WarehouseMgrDesigner.lbl_sBroken.setText(String.valueOf(count3));
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	private static void listData() {
		class BackgroundWorker extends SwingWorker<Void, Void> {

			public BackgroundWorker() {
				addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						WarehouseMgrDesigner.pgbPregress.setValue(getProgress());
					}

				});
			}

			@Override
			protected void done() {
				/*
				WarehouseMgrDesigner.pnlLeft.setVisible(true);
				WarehouseMgrDesigner.pnlDetail.setVisible(true);
				WarehouseMgrDesigner.pnlBottom1.setVisible(true);
				WarehouseMgrDesigner.pgbPregress.setVisible(false);
				*/
			}

			protected Void doInBackground() throws Exception {
				int ProgressType=0;
				ArrayList<Warehouses> whs = Warehouses.listAllWarehouseLocation("", "");
				if(whs.size() > 0) {
					Integer i = 0;
					Integer max = whs.size();
					WarehouseMgrDesigner.pgbPregress.setMaximum(max);
					for(Warehouses wh:whs) {
						System.out.println(wh.getLocID());
						WarehouseMgrDesigner.pgbPregress.setValue(++i);

						if(wh.getStatus().toString()=="FULL") {
							WarehouseMgrDesigner.lbl[x].setBackground(Color.RED);
						}else if(wh.getStatus().toString()=="MAINTENANCE") {
							WarehouseMgrDesigner.lbl[x].setBackground(Color.YELLOW);
						}else if(wh.getStatus().toString()=="EMPTY") {
							WarehouseMgrDesigner.lbl[x].setBackground(Color.WHITE);
						}
						x++;
						
						if(ProgressType == 0) {
							// Records
							WarehouseMgrDesigner.pgbPregress.setString("โหลดข้อมุล  "+i.toString() + " / " + max.toString() + " รายการ สำเร็จ.");     
						} else {
							// Percentage
							Float cur =(i / (float)max) * 100;
							WarehouseMgrDesigner.pgbPregress.setString(new DecimalFormat("0.00").format(cur) + "% สำเร็จ.");
						}
						
						try {
							//delay(10);
							Thread.sleep(20);
						} catch (Exception e) {
							// whatever
						}
					}
					showstate();
				}
				
				return null;
			}
			
		};
		new BackgroundWorker().execute();
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
						Date d1 = new Date();
						SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
						try {

							String qry = "SELECT cus.cust_id, cus.cust_name, rents.inv_no, rents.start_date, rents.expire_date, rentdetail.rentdetail_id, rentdetail.loc_id, warehouses.status, warehouses.price, warehouses.remark FROM customers AS cus INNER JOIN rents ON cus.cust_id = rents.cust_id INNER JOIN rentdetail on rents.inv_no = rentdetail.inv_no INNER JOIN warehouses on rentdetail.loc_id = warehouses.loc_id WHERE rentdetail.loc_id = '"+WarehouseMgrDesigner.selectedLoc+"'";
							PreparedStatement stmt = conn.prepareStatement(qry);
							ResultSet rs = stmt.executeQuery();
							while(rs.next()) {
								WarehouseMgrDesigner.txtRentId.setText(rs.getString("inv_no"));
								WarehouseMgrDesigner.txtCustName.setText(rs.getString("cust_name"));
								WarehouseMgrDesigner.txtRentTotal.setText(Long.toString(getDayDiff(rs.getString("start_date"), rs.getString("expire_date"),f)));
								WarehouseMgrDesigner.txtStartDate.setText(rs.getString("start_date"));
								WarehouseMgrDesigner.txtEndDate.setText(rs.getString("expire_date"));
								WarehouseMgrDesigner.txtDadeLine.setText(Long.toString(getDayDiff(d1, rs.getString("expire_date"),f)+1));
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
					
					locSta= WHStatus.FULL;
				}else if(locStatClick=="MAINTENANCE"){
					locSta= WHStatus.MAINTENANCE;
				}
				
				
				
			Warehouses.updateWarehouseInfo(WarehouseMgrDesigner.txtLocId.getText(), locSta,Double.parseDouble(WarehouseMgrDesigner.txtLocPrice.getText()),WarehouseMgrDesigner.txtLocRemark.getText());
	
		
			WarehouseMgrDesigner.btnEdit.setText("แก้ไข");
			WarehouseMgrDesigner.btnEdit.setEnabled(true);
			WarehouseMgrDesigner.btnSave.setEnabled(false);
			//cleartxt();
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
			showstate();
			
			btnRemakeClicked=true;
		}else if(WarehouseMgrDesigner.btnRemake.getText()=="ยกเลิกสถานะซ่อมบำรุง") {
			Warehouses.updateWarehouseInfo(WarehouseMgrDesigner.txtLocId.getText(), Warehouses.WHStatus.EMPTY,Double.parseDouble(WarehouseMgrDesigner.txtLocPrice.getText()),WarehouseMgrDesigner.txtLocRemark.getText());
			WarehouseMgrDesigner.btnRemake.setText("ตั้งเป็นสถานะซ่อมบำรุง");
			WarehouseMgrDesigner.lastBgColor=Color.WHITE;
			x=0;
			showdataClicked();
			showstate();
		}
	}
	
	public static void clearLocStatus() {

		for (int i=0; i<168; i++) {
			
			WarehouseMgrDesigner.lbl[i].setBackground(Color.WHITE);

		}
		WarehouseMgrDesigner.lbl_sFree.setText("");
		WarehouseMgrDesigner.lbl_sNotFree.setText("");
		WarehouseMgrDesigner.lbl_sBroken.setText("");

	}
	
	public static long getDayDiff(Date dateStart, Date dateEnd) {
		long diff = dateEnd.getTime() - dateStart.getTime();
		long diffDays = diff / (24*60*60*1000);
		
		return diffDays;
	}
		 
	public static long getDayDiff(String dateStart, String dateEnd, SimpleDateFormat f) {
		try {
			Date d1 = f.parse(dateStart);
			Date d2 = f.parse(dateEnd);
			   
			return getDayDiff(d1, d2);
		   
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  
		return -1;
	}
	
	public static long getDayDiff(Date dateStart, String dateEnd, SimpleDateFormat f) {
		try {
			Date d1 = dateStart;
			Date d2 = f.parse(dateEnd);
			   
			return getDayDiff(d1, d2);
		   
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  
		return -1;
	}
	public static void cleartxt() {
		WarehouseMgrDesigner.txtLocId.setText("");
		WarehouseMgrDesigner.txtLocPrice.setText("");
		WarehouseMgrDesigner.txtLocStatus.setText("");
		WarehouseMgrDesigner.txtLocRemark.setText("");

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
