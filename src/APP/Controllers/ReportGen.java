package APP.Controllers;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;


import APP.Designers.ReportGenDesigner;

import DBCLS.DBConnector;
import DBCLS.Rental;
import DBCLS.Warehouses;
import DBCLS.Warehouses.WHStatus;

public class ReportGen {
	// Preload Assets
	static JFrame report = new ReportGenDesigner();
	static String beginDate;
	static String toDate;
	static DateFormat beginDf,toDf;
	static Date secBeginDate,secTotDate;
	/*
	public static void main(String[] arg) {
		report.setVisible(true);
		
		showdata();
	
	}
	*/
	
	public static void getReport() {
		report.setVisible(true);
		int totalRow=ReportGenDesigner.tableModelReport.getRowCount()-1;
		while(totalRow > -1) {
			ReportGenDesigner.tableModelReport.removeRow(totalRow);
			totalRow--;
		}
		
		loadData();
		//showdata();
	}
	
	public static void showdata() {
		/*
		Connection conn = DBConnector.getDBConnection();
		try {
			
			int totalRow=ReportGenDesigner.tableReport.getRowCount()-1;
			while(totalRow > -1) {
				ReportGenDesigner.tableModelReport.removeRow(totalRow);
				totalRow--;
				
			}
			int row=0;
			String order="";
			if((Date) ReportGenDesigner.beginDatePicker.getModel().getValue()!=null && (Date) ReportGenDesigner.toDatePicker.getModel().getValue()!=null) {
				order="WHERE rents.inv_date BETWEEN '"+beginDate+"' AND '"+toDate+"' ORDER BY inv_no ASC";
			}else {
				order="ORDER BY inv_no ASC";
			}
			
			
			//if(condition != "") condition = " WHERE " + condition;
			//if(order != "") order = " ORDER BY " + order;
			String qry = "SELECT rents.inv_no, cus.cust_name, rents.start_date, rents.expire_date, rents.amount, users.username FROM rents INNER JOIN customers as cus ON rents.cust_id = cus.cust_id INNER JOIN users ON rents.user_id = users.user_id "+order+"";
			PreparedStatement stmt = conn.prepareStatement(qry);
			//stmt.setInt(1, inv_no);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString("loc_id"));
				
				ReportGenDesigner.tableModelReport.addRow(new Object[0]);
				ReportGenDesigner.tableModelReport.setValueAt(rs.getString("inv_no"),row,0);
				ReportGenDesigner.tableModelReport.setValueAt(rs.getString("cust_name"),row,1);
				ReportGenDesigner.tableModelReport.setValueAt(rs.getString("start_date"),row,2);
				ReportGenDesigner.tableModelReport.setValueAt(rs.getString("expire_date"),row,3);
				ReportGenDesigner.tableModelReport.setValueAt(rs.getString("username"),row,4);
				ReportGenDesigner.tableModelReport.setValueAt(new DecimalFormat("#,###.00").format(Float.parseFloat(rs.getString("amount"))),row,5);
				
				row++;

			}
			ReportGenDesigner.tableReport.setModel(ReportGenDesigner.tableModelReport);
			
			//conn.close();

			ReportGenDesigner.lblTotalsum.setText("จำนวนทั้งหมด: "+getRentTotal());

			ReportGenDesigner.lblTotalsum2.setText("อยู่ในสัญญาจำนวน: "+getRentNoFree());

			ReportGenDesigner.lblTotalsum3.setText("หมดสัญญาจำนวน: "+getRentFree());
			
			ReportGenDesigner.lblSumTotal.setText(new DecimalFormat("#,###.00").format(getsum()));
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		*/

		ReportGenDesigner.lblTotalsum.setText("จำนวนทั้งหมด: "+getRentTotal());

		ReportGenDesigner.lblTotalsum2.setText("อยู่ในสัญญาจำนวน: "+getRentNoFree());

		ReportGenDesigner.lblTotalsum3.setText("หมดสัญญาจำนวน: "+getRentFree());
		ReportGenDesigner.lblCancelRent.setText("ยกเลิกสัญญาจำนวน: "+getRentCancel());
		
		ReportGenDesigner.lblSumTotal.setText(new DecimalFormat("#,###.00").format(getsum()));
		

	}
	
	public static int getRentTotal() {
		Connection conn = DBConnector.getDBConnection();
		int count1=0;
		
		String order="";
		if((Date) ReportGenDesigner.beginDatePicker.getModel().getValue()!=null && (Date) ReportGenDesigner.toDatePicker.getModel().getValue()!=null) {
			order="WHERE rents.inv_date BETWEEN '"+beginDate+"' AND '"+toDate+"' ORDER BY inv_no ASC";
		}else {
			order="ORDER BY inv_no ASC";
		}
		

		
		try {
			//String qry1="SELECT COUNT(*) FROM rents";
			String qry1 = "SELECT COUNT(*) FROM rents "+order+"";
			Statement stmt1 = conn.createStatement();			
			ResultSet rs1 = stmt1.executeQuery(qry1);
			rs1.next();
			count1 = rs1.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count1;
	}
	
	public static int getRentNoFree() {
		Connection conn = DBConnector.getDBConnection();
		int count1=0;
		
		String order="";
		if((Date) ReportGenDesigner.beginDatePicker.getModel().getValue()!=null && (Date) ReportGenDesigner.toDatePicker.getModel().getValue()!=null) {
			//order="WHERE rents.inv_date BETWEEN '"+beginDate+"' AND '"+toDate+"' ORDER BY inv_no ASC";
		}else {
			order="ORDER BY inv_no ASC";
		}
		
		try {
			String qry1="SELECT COUNT(*) FROM rents WHERE remark=1 "+order+"";
			Statement stmt1 = conn.createStatement();			
			ResultSet rs1 = stmt1.executeQuery(qry1);
			rs1.next();
			count1 = rs1.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count1;
	}
	
	public static int getRentFree() {
		Connection conn = DBConnector.getDBConnection();
		int count1=0;
		
		String order="";
		if((Date) ReportGenDesigner.beginDatePicker.getModel().getValue()!=null && (Date) ReportGenDesigner.toDatePicker.getModel().getValue()!=null) {
			//order="WHERE rents.inv_date BETWEEN '"+beginDate+"' AND '"+toDate+"' ORDER BY inv_no ASC";
		}else {
			order="ORDER BY inv_no ASC";
		}
		
		try {
			String qry1="SELECT COUNT(*) FROM rents WHERE remark=0 "+order+"";
			Statement stmt1 = conn.createStatement();			
			ResultSet rs1 = stmt1.executeQuery(qry1);
			rs1.next();
			count1 = rs1.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count1;
	}
	
	public static int getRentCancel() {
		Connection conn = DBConnector.getDBConnection();
		int count1=0;
		
		String order=order="ORDER BY inv_no ASC";

		try {
			String qry1="SELECT COUNT(*) FROM rents WHERE remark=3 "+order+"";
			Statement stmt1 = conn.createStatement();			
			ResultSet rs1 = stmt1.executeQuery(qry1);
			rs1.next();
			count1 = rs1.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count1;
	}
	
	
	public static void btnShowReport() {
		if(formValidation()) {
			secBeginDate = (Date) ReportGenDesigner.beginDatePicker.getModel().getValue();
			beginDf = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
			beginDate = beginDf.format(secBeginDate);

			secTotDate = (Date) ReportGenDesigner.toDatePicker.getModel().getValue();
			toDf = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
			toDate = toDf.format(secTotDate);
			loadData();
			//showdata();
		}

	}
	
	public static void cleartxt() {

		ReportGenDesigner.beginDatePicker.getModel().setValue(null);
		ReportGenDesigner.toDatePicker.getModel().setValue(null);

	}
	
	public static void btnReset() {
		cleartxt();
		loadData();
		//showdata();
	}
	
    public static double getsum(){
        int rowscount = ReportGenDesigner.tableReport.getRowCount();
        double sum=0;
        for(int i =0; i< rowscount; i++){
            //sum=sum+Double.valueOf(ReportGenDesigner.tableReport.getValueAt(i,5).toString());
            
    		String str = ReportGenDesigner.tableReport.getValueAt(i,7).toString();
    		try {
    			double l = DecimalFormat.getNumberInstance().parse(str).doubleValue();
    			sum=sum+Double.valueOf(l);
    			//System.out.println(l); //111111.23
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		
        }
        
        
        return sum;
        
    }
		
	public static boolean formValidation() {
		String txt="กรุณากรอกข้อมูลต่อไปนี้ให้ครบ\n";
		int check=0;

		if((Date) ReportGenDesigner.beginDatePicker.getModel().getValue()==null) {
			txt +="-วันเริ่มต้น\n";
			check=1;
		}

		if((Date) ReportGenDesigner.toDatePicker.getModel().getValue()==null) {
			txt +="-วันสิ้นสุด\n";
			check=1;
		}

		if(check==1) {
			JOptionPane.showMessageDialog(null, txt, "แจ้งเตือน",JOptionPane.ERROR_MESSAGE);
			
			return false;
		}else {
			return true;
		}

	}
	
	private static void loadData() {
		class BackgroundWorker extends SwingWorker<Void, Void> {

			private JProgressBar pb;
			private JDialog dialog;
			Date d1 = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
			
			public BackgroundWorker() {
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
								dialog.add(new JLabel("โหลดข้อมูล..."), gbc);
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
					showdata();
					dialog.dispose();
				}
			}

			@Override
			protected Void doInBackground() throws Exception {
				Connection conn = DBConnector.getDBConnection();
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
				try {
					ArrayList<Rental> rentals2 = Rental.listAllRental("", "");
					if(!rentals2.isEmpty()) {
						for (Rental rental : rentals2) {
							
							if(WarehouseMgr.getDayDiff(d1, rental.getExpireDate().toString(),f) <= 0 && Integer.parseInt(rental.getRemark().toString()) == 1) {

								//btnCancelRent();
								
								try {

									String sql="UPDATE rents SET remark=? WHERE inv_no=?";
											
									PreparedStatement pre = conn.prepareStatement(sql);
									pre.setString(1, "0");
									pre.setInt(2, rental.getInvNo());
									
									if(pre.executeUpdate() != -1) {
										//JOptionPane.showMessageDialog(null, "แก้ไขข้อมูลลูกค้าเรียบร้อยแล้ว", "ผลการแก้ไขรายการ", JOptionPane.INFORMATION_MESSAGE);
										//showdata();
									}
									
									
								} catch (SQLException ex) {
									ex.printStackTrace();
								}
								
								
								try {

									
									String qry = "SELECT *"
											+ " FROM rentdetail,warehouses"
											+ " WHERE rentdetail.loc_id=warehouses.loc_id AND inv_no="+rental.getInvNo()+" ORDER BY inv_no";
									PreparedStatement stmt = conn.prepareStatement(qry);
									//stmt.setInt(1, inv_no);
									
									ResultSet rs = stmt.executeQuery();
									while(rs.next()) {
										System.out.println(rs.getString("loc_id"));
										Warehouses.updateWarehouseInfo(rs.getString("loc_id"), WHStatus.EMPTY,rs.getDouble("price"), rs.getString("remark"));

									}

									//conn.close();
									
								} catch (SQLException e) {

									e.printStackTrace();
								}
								//conn.close();
								
							}

						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
				int totalRow=ReportGenDesigner.tableReport.getRowCount()-1;
				while(totalRow > -1) {
					ReportGenDesigner.tableModelReport.removeRow(totalRow);
					totalRow--;
					
				}
				int row=0;
				String order="";
				if((Date) ReportGenDesigner.beginDatePicker.getModel().getValue()!=null && (Date) ReportGenDesigner.toDatePicker.getModel().getValue()!=null) {
					order="WHERE rents.inv_date BETWEEN '"+beginDate+"' AND '"+toDate+"' ORDER BY inv_no ASC";
				}else {
					order="ORDER BY inv_no ASC";
				}
				
				
				//if(condition != "") condition = " WHERE " + condition;
				//if(order != "") order = " ORDER BY " + order;
				String qry = "SELECT rents.inv_no, cus.cust_name, rents.start_date, rents.expire_date,rents.time, rents.amount, rents.remark, users.username FROM rents INNER JOIN customers as cus ON rents.cust_id = cus.cust_id INNER JOIN users ON rents.user_id = users.user_id "+order+"";
				PreparedStatement stmt = conn.prepareStatement(qry);
				//stmt.setInt(1, inv_no);
				
				ResultSet rs = stmt.executeQuery();
				
				if(getRentTotal() > 0) {
					Integer i = 0;
					Integer max = getRentTotal();
					//pb.setMaximum(max);
					while(rs.next()) {
						System.out.println(rs.getString("inv_no"));
						setProgress((int)((row*100)/max));
						
						ReportGenDesigner.tableModelReport.addRow(new Object[0]);
						ReportGenDesigner.tableModelReport.setValueAt(rs.getString("inv_no"),row,0);
						ReportGenDesigner.tableModelReport.setValueAt(rs.getString("cust_name"),row,1);
						ReportGenDesigner.tableModelReport.setValueAt(rs.getString("start_date"),row,2);
						ReportGenDesigner.tableModelReport.setValueAt(rs.getString("expire_date"),row,3);
						ReportGenDesigner.tableModelReport.setValueAt(rs.getString("time"),row,4);
						ReportGenDesigner.tableModelReport.setValueAt(rs.getString("username"),row,5);
						
						
						if((WarehouseMgr.getDayDiff(d1, rs.getString("expire_date"),f))>0 && Integer.parseInt(rs.getString("remark").toString())!=3) {
							ReportGenDesigner.tableModelReport.setValueAt(WarehouseMgr.getDayDiff(d1, rs.getString("expire_date").toString(),f), row, 6);
						}else {
							if(Integer.parseInt(rs.getString("remark").toString())==3) {
								//RentalMgrDesigner.lblRentStatus.setText("อยู่ในสัญญา");
								ReportGenDesigner.tableModelReport.setValueAt("ยกเลิกสัญญา",row,6);
								
							}else if(Integer.parseInt(rs.getString("remark").toString())==0) {
								//RentalMgrDesigner.lblRentStatus.setText("หมดสัญญา");
								ReportGenDesigner.tableModelReport.setValueAt("หมดสัญญา",row,6);
								
							}
						}
						
						//ReportGenDesigner.tableModelReport.setValueAt("remark",row,6);

						
						ReportGenDesigner.tableModelReport.setValueAt(new DecimalFormat("#,###.00").format(Float.parseFloat(rs.getString("amount"))),row,7);

						row++;
						Thread.sleep(50);
					}
				}

				ReportGenDesigner.tableReport.setModel(ReportGenDesigner.tableModelReport);
				
				
				
				return null;
			}
		}
		new BackgroundWorker().execute();
	}
}
