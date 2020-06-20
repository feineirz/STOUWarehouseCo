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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

import APP.Designers.*;
import DBCLS.Rental;
import DBCLS.Rents;
import DBCLS.Users;
import DBCLS.Warehouses.WHStatus;
import DBCLS.Customers;
import DBCLS.DBConnector;
import DBCLS.RentDetail;
import DBCLS.Warehouses;

public class RentalMgr {
	// Preload Assets
	static JFrame defaultFrame = new AboutDesigner();
	static JFrame rental = new RentalMgrDesigner();
	static Connection conn = DBConnector.getDBConnection();
	static int countDetial=0;
	/*
	public static void main(String[] arg) {
		rental.setVisible(true);
		showdata();


		
	}
	*/
	public static void getRental() {
		
		rental.setVisible(true);
		int totalRow=RentalMgrDesigner.tableModel.getRowCount()-1;
		while(totalRow > -1) {
			RentalMgrDesigner.tableModel.removeRow(totalRow);
			totalRow--;
		}
		loadData();
		//showdata();
		
	}
	
	public static void showdata() {

		
		RentalMgrDesigner.btnCancelRent.setEnabled(false);
		RentalMgrDesigner.btnEdit.setEnabled(false);
		RentalMgrDesigner.btnDelete.setEnabled(false);
		
		Date d1 = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
		
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
		
		
		try {
			

			
			int totalRow=RentalMgrDesigner.tableRent.getRowCount()-1;
			while(totalRow > -1) {
				RentalMgrDesigner.tableModel.removeRow(totalRow);
				totalRow--;
				
			}
			int row=0;
			
			String search = RentalMgrDesigner.txtSearchRent.getText().trim();

			String sqlwhere="";
			try {
				if(RentalMgrDesigner.cbhowsearch.getSelectedIndex()==1) {
					sqlwhere=" WHERE rents.remark=0";
				}else if(RentalMgrDesigner.cbhowsearch.getSelectedIndex()==2) {
					sqlwhere=" WHERE rents.remark=1";
				}else if(RentalMgrDesigner.cbhowsearch.getSelectedIndex()==0) {
					sqlwhere=" WHERE inv_no LIKE '%"+search+"%'"
							+ " OR customers.cust_id LIKE '%"+search+"%'"
							+ " OR customers.cust_name LIKE '%"+search+"%'"
							+ " OR customers.phone LIKE '%"+search+"%'"
							+ " OR customers.fax LIKE '%"+search+"%'"
							+ " OR customers.email LIKE '%"+search+"%'";
				}
				

				String sql = "SELECT rents.inv_no,rents.inv_date,rents.start_date,rents.expire_date,rents.time,rents.vat,rents.discount,rents.amount,rents.remark,customers.cust_id,customers.cust_name,customers.address,customers.phone,customers.fax,customers.email,users.user_id,users.username"
						+ " FROM customers"
						+ " INNER JOIN rents ON rents.cust_id = customers.cust_id"
						+ " INNER JOIN users ON users.user_id = rents.user_id"
						+ sqlwhere

						+ " ORDER BY inv_no ASC";
						
				PreparedStatement stmt = conn.prepareStatement(sql);
				//stmt.setInt(1, inv_no);
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					//System.out.println(rs.getString("loc_id"));
					
					RentalMgrDesigner.tableModel.addRow(new Object[0]);
					RentalMgrDesigner.tableModel.setValueAt(rs.getString("inv_no"),row,0);
					RentalMgrDesigner.tableModel.setValueAt(rs.getString("cust_name"),row,1);
					RentalMgrDesigner.tableModel.setValueAt(rs.getString("inv_date"),row,2);
					RentalMgrDesigner.tableModel.setValueAt(rs.getString("time"),row,3);
					RentalMgrDesigner.tableModel.setValueAt(WarehouseMgr.getDayDiff(rs.getString("start_date").toString(), rs.getString("expire_date").toString(),f),row,4);
					
		
					if((WarehouseMgr.getDayDiff(d1, rs.getString("expire_date"),f))>0 && Integer.parseInt(rs.getString("remark").toString())!=3) {
						RentalMgrDesigner.tableModel.setValueAt(WarehouseMgr.getDayDiff(d1, rs.getString("expire_date").toString(),f), row, 5);
					}else {
						if(Integer.parseInt(rs.getString("remark").toString())==3) {
							//RentalMgrDesigner.lblRentStatus.setText("อยู่ในสัญญา");
							RentalMgrDesigner.tableModel.setValueAt("ยกเลิกสัญญา",row,5);
							
						}else if(Integer.parseInt(rs.getString("remark").toString())==0) {
							//RentalMgrDesigner.lblRentStatus.setText("หมดสัญญา");
							RentalMgrDesigner.tableModel.setValueAt("หมดสัญญา",row,5);
							
						}
					}
					
					RentalMgrDesigner.tableModel.setValueAt(new DecimalFormat("#,###.00").format(Float.parseFloat(rs.getString("amount"))),row,6);

					row++;

				}
				
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			
			RentalMgrDesigner.tableRent.setModel(RentalMgrDesigner.tableModel);
		}catch(Exception e) {
			e.printStackTrace();
		}
		



	}
	
	public static void updatecount() {
		try {

			RentalMgrDesigner.lblTotalsum.setText("จำนวนทั้งหมด: "+getRentTotal());
			RentalMgrDesigner.lblTotalsum2.setText("อยู่ในสัญญาจำนวน: "+getRentNoFree());
			RentalMgrDesigner.lblTotalsum3.setText("หมดสัญญาจำนวน: "+getRentFree());
			RentalMgrDesigner.lblCancelRent.setText("ยกเลิกสัญญาจำนวน: "+getRentCancel());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void mouseclick() {
		int index=RentalMgrDesigner.tableRent.getSelectedRow();

		try {

			ArrayList<Rental> rentals = Rental.listAllRental("inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+"", "inv_no ASC");
			if(!rentals.isEmpty()) {
				for (Rental rent : rentals) {
					
					RentalMgrDesigner.lblCustId.setText(String.valueOf(rent.getCustomerID()));
					RentalMgrDesigner.lblCustName.setText(rent.getCustName().toString());
					RentalMgrDesigner.lblCustAddr.setText(rent.getAddress().toString());
					RentalMgrDesigner.lblCustPhone.setText(rent.getPhone().toString());
					RentalMgrDesigner.lblCustFax.setText(rent.getFax().toString());
					RentalMgrDesigner.lblCustEmail.setText(rent.getEmail().toString());
					
					RentalMgrDesigner.lblRentId.setText(RentalMgrDesigner.tableRent.getValueAt(index, 0).toString());
					RentalMgrDesigner.lblRentDate.setText(rent.getInvDate().toString());
					RentalMgrDesigner.lblStartRentDate.setText(rent.getStartDate().toString());
					RentalMgrDesigner.lblEndRentDate.setText(rent.getExpireDate().toString());
					RentalMgrDesigner.lblRentStatus.setText(rent.getRemark().toString());
	
					
					RentalMgrDesigner.lblVat2.setText(new DecimalFormat("#,###.00").format(rent.getVat()));
					RentalMgrDesigner.lblDiscount2.setText(new DecimalFormat("#,###.00").format(rent.getDiscount()));
					RentalMgrDesigner.lblTotal2.setText(new DecimalFormat("#,###.00").format(rent.getAmount()));
					/*
					String datastring1 = "1";
					String datastring2 = rent.getRemark().toString();

					if (datastring1.contentEquals(datastring2)) {
						RentalMgrDesigner.lblRentStatus.setText("อยู่ในสัญญา");
					}else {
						RentalMgrDesigner.lblRentStatus.setText("หมดสัญญา");
					}
						
					*/
					
					
					if(Integer.parseInt(rent.getRemark().toString())==1) {
						RentalMgrDesigner.lblRentStatus.setText("อยู่ในสัญญา");
						RentalMgrDesigner.lblRentStatus.setForeground(Color.BLACK);
						RentalMgrDesigner.btnCancelRent.setEnabled(true);
					}else if(Integer.parseInt(rent.getRemark().toString())==0) {
						RentalMgrDesigner.lblRentStatus.setText("หมดสัญญา");
						RentalMgrDesigner.lblRentStatus.setForeground(Color.RED);
						RentalMgrDesigner.btnCancelRent.setEnabled(false);
					}else if(Integer.parseInt(rent.getRemark().toString())==3) {
						RentalMgrDesigner.lblRentStatus.setText("ยกเลิกสัญญา");
						RentalMgrDesigner.lblRentStatus.setForeground(Color.RED);
						RentalMgrDesigner.btnCancelRent.setEnabled(false);
					}
					
					
					
				}

			}



		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(".........>"+getRentDetailTotal());
		
		Connection conn = DBConnector.getDBConnection();


		
		
		try {
			int totalRow=RentalMgrDesigner.tableRentDetail.getRowCount()-1;
			while(totalRow > -1) {
				RentalMgrDesigner.tableModelDetail.removeRow(totalRow);
				totalRow--;
				
			}
			int row=0;
			
			String qry = "SELECT *"
					+ " FROM rentdetail"
					+ " WHERE inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+" ORDER BY loc_id ASC";
			PreparedStatement stmt = conn.prepareStatement(qry);
			//stmt.setInt(1, inv_no);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString("loc_id"));
				RentalMgrDesigner.tableModelDetail.addRow(new Object[0]);
				RentalMgrDesigner.tableModelDetail.setValueAt(row+1,row,0);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("loc_id"),row,1);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("quantity"),row,2);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("saleprice"),row,3);
				//RentalMgrDesigner.tableModelDetail.setValueAt(Float.parseFloat(rs.getString("saleprice"))*Float.parseFloat(rs.getString("quantity")),row,4);
				
				RentalMgrDesigner.tableModelDetail.setValueAt(new DecimalFormat("#,###.00").format(Float.parseFloat(rs.getString("saleprice"))*Float.parseFloat(rs.getString("quantity"))),row,4);

				//System.out.println(new DecimalFormat("#,###.00").format(100000000000000000000000000000.00));
				row++;

			}
			RentalMgrDesigner.tableRentDetail.setModel(RentalMgrDesigner.tableModelDetail);
			conn.close();
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		RentalMgrDesigner.lblSumTotal.setText(new DecimalFormat("#,###.00").format(getsum()));
	}
	
	public static  void btnCancelRent(){
		updateCancelRent();
		/*
		int index=RentalMgrDesigner.tableRent.getSelectedRow();
		Connection conn = DBConnector.getDBConnection();
		
		int input = JOptionPane.showConfirmDialog(null, "คุณต้องการยกเลิกสัญญาเลขที่: "+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+" ใช่ หรือ ไม่","แจ้งเตือน",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if(input==0) {
			
			
			try {

				String sql="UPDATE rents SET remark=? WHERE inv_no=?";
						
				PreparedStatement pre = conn.prepareStatement(sql);
				pre.setString(1, "3");
				pre.setString(2, RentalMgrDesigner.tableRent.getValueAt(index, 0).toString());
				
				if(pre.executeUpdate() != -1) {
					//JOptionPane.showMessageDialog(null, "แก้ไขข้อมูลลูกค้าเรียบร้อยแล้ว", "ผลการแก้ไขรายการ", JOptionPane.INFORMATION_MESSAGE);
					showdata();
				}
				
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			try {

				
				String qry = "SELECT *"
						+ " FROM rentdetail,warehouses"
						+ " WHERE rentdetail.loc_id=warehouses.loc_id AND inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+" ORDER BY inv_no";
				PreparedStatement stmt = conn.prepareStatement(qry);
				//stmt.setInt(1, inv_no);
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("loc_id"));
					Warehouses.updateWarehouseInfo(rs.getString("loc_id"), WHStatus.EMPTY,rs.getDouble("price"), rs.getString("remark"));

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
			showdata();
			updatecount();	
			
			

		}
		*/


	}
	
    public static double getsum(){
        int rowscount = RentalMgrDesigner.tableRentDetail.getRowCount();
        double sum=0;
        for(int i =0; i< rowscount; i++){
        	
    		String str = RentalMgrDesigner.tableRentDetail.getValueAt(i,4).toString();
    		try {
    			double l = DecimalFormat.getNumberInstance().parse(str).doubleValue();
    			sum=sum+Double.valueOf(l);
    			System.out.println(l); //111111.23
    		} catch (Exception e) {
    			e.printStackTrace();
    		}

            
        }
        return sum;
        
    }
    
	public static int getRentTotal() {
		Connection conn = DBConnector.getDBConnection();
		int count1=0;
		
		String order=order="ORDER BY inv_no ASC";

		

		
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
		
		String order="ORDER BY inv_no ASC";
	
		
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
		
		String order=order="ORDER BY inv_no ASC";

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
	
    //Progressbar
    

	public static int getRentDetailTotal() {
		
		Connection conn = DBConnector.getDBConnection();
		int count1=0;

		int index=RentalMgrDesigner.tableRent.getSelectedRow();
		

		
		try {

			String qry = "SELECT COUNT(*)"
					+ " FROM rentdetail"
					+ " WHERE inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+" ORDER BY rentdetail_id";
			//PreparedStatement stmt1 = conn.prepareStatement(qry);
			Statement stmt1 = conn.createStatement();			
			ResultSet rs1 = stmt1.executeQuery(qry);
			rs1.next();
			count1 = rs1.getInt(1);
			System.out.println("yyyyyyyyyyyyyyyyyyyyyy>"+count1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count1;
	}
	
	
	public static void updateCancelRent() {
		class updateCencelBack extends SwingWorker<Void, Void> {

			private JProgressBar pb;
			private JDialog dialog;

			public updateCencelBack() {
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
								dialog.add(new JLabel("กำลังยกเลิกสัญญา..."), gbc);
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
					updatecount();	
					dialog.dispose();
				}
			}

			@Override
			protected Void doInBackground() throws Exception {
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
				/*

				try {
					getMaxId();
					
					AddRentalDesigner.cbCustName.removeAllItems();
					AddRentalDesigner.cbCustName.addItem("กรุณาเลือกชื่อลูกค้า");

					ArrayList<Customers> cuts = Customers.listAllCustomers("", "cust_id ASC");
					if(!cuts.isEmpty()) {
						if(cuts.size() > 0) {
							Integer i = 0;
							Integer max = cuts.size();
							//pb.setMaximum(max);
							for (Customers cut : cuts) {
								System.out.println(cut.getCustomerID());
								setProgress((int)((++i*100)/max));
								AddRentalDesigner.cbCustName.addItem(cut.getCustomerName());
								Thread.sleep(30);
							}
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				*/
				
				
				int index=RentalMgrDesigner.tableRent.getSelectedRow();
				Connection conn = DBConnector.getDBConnection();
				countDetial=0;
				countDetial=getRentDetailTotal();
				int input = JOptionPane.showConfirmDialog(null, "คุณต้องการยกเลิกสัญญาเลขที่: "+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+" ใช่ หรือ ไม่","แจ้งเตือน",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if(input==0) {
					
					try {

						String sql="UPDATE rents SET remark=? WHERE inv_no=?";
								
						PreparedStatement pre = conn.prepareStatement(sql);
						pre.setString(1, "3");
						pre.setString(2, RentalMgrDesigner.tableRent.getValueAt(index, 0).toString());
						
						if(pre.executeUpdate() != -1) {
							//JOptionPane.showMessageDialog(null, "แก้ไขข้อมูลลูกค้าเรียบร้อยแล้ว", "ผลการแก้ไขรายการ", JOptionPane.INFORMATION_MESSAGE);
							showdata();
						}
						
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					
					try {

						
						String qry = "SELECT *"
								+ " FROM rentdetail,warehouses"
								+ " WHERE rentdetail.loc_id=warehouses.loc_id AND inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+" ORDER BY inv_no";
						PreparedStatement stmt = conn.prepareStatement(qry);
						//stmt.setInt(1, inv_no);
						
						ResultSet rs = stmt.executeQuery();
						
						Integer i = 0;
						Integer max = countDetial;
						while(rs.next()) {
							setProgress((int)((++i*100)/max));
							//System.out.println("************>"+rs.getString("loc_id"));
							Warehouses.updateWarehouseInfo(rs.getString("loc_id"), WHStatus.EMPTY,rs.getDouble("price"), rs.getString("remark"));
							Thread.sleep(30);
						}
						

						//conn.close();
						
					} catch (SQLException e) {

						e.printStackTrace();
					}
					
		

				}

				
				
				return null;
			}
		}
		new updateCencelBack().execute();
	}
	
	
	
	public static void loadData() {
		class BackgroundWorker extends SwingWorker<Void, Void> {

			private JProgressBar pb;
			private JDialog dialog;

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
					updatecount();
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
				
				//RentalMgrDesigner.btnCancelRent.setEnabled(false);
				RentalMgrDesigner.btnEdit.setEnabled(false);
				RentalMgrDesigner.btnDelete.setEnabled(false);
				
				Date d1 = new Date();
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
				
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
				

				try {
					

					
					int totalRow=RentalMgrDesigner.tableRent.getRowCount()-1;
					while(totalRow > -1) {
						RentalMgrDesigner.tableModel.removeRow(totalRow);
						totalRow--;
						
					}
					int row=0;
					
					String search = RentalMgrDesigner.txtSearchRent.getText().trim();

					String sqlwhere="";
					try {
						if(RentalMgrDesigner.cbhowsearch.getSelectedIndex()==1) {
							sqlwhere=" WHERE rents.remark=0";
						}else if(RentalMgrDesigner.cbhowsearch.getSelectedIndex()==2) {
							sqlwhere=" WHERE rents.remark=1";
						}else if(RentalMgrDesigner.cbhowsearch.getSelectedIndex()==3) {
							sqlwhere=" WHERE rents.remark=3";
						}else if(RentalMgrDesigner.cbhowsearch.getSelectedIndex()==0) {
							sqlwhere=" WHERE inv_no LIKE '%"+search+"%'"
									+ " OR customers.cust_id LIKE '%"+search+"%'"
									+ " OR customers.cust_name LIKE '%"+search+"%'"
									+ " OR customers.phone LIKE '%"+search+"%'"
									+ " OR customers.fax LIKE '%"+search+"%'"
									+ " OR customers.email LIKE '%"+search+"%'";
						}
						

						String sql = "SELECT rents.inv_no,rents.inv_date,rents.start_date,rents.expire_date,rents.time,rents.vat,rents.discount,rents.amount,rents.remark,customers.cust_id,customers.cust_name,customers.address,customers.phone,customers.fax,customers.email,users.user_id,users.username"
								+ " FROM customers"
								+ " INNER JOIN rents ON rents.cust_id = customers.cust_id"
								+ " INNER JOIN users ON users.user_id = rents.user_id"
								+ sqlwhere
								/*
								+ " WHERE inv_no LIKE '%"+search+"%'"
								+ " OR customers.cust_id LIKE '%"+search+"%'"
								+ " OR customers.cust_name LIKE '%"+search+"%'"
								+ " OR customers.phone LIKE '%"+search+"%'"
								+ " OR customers.fax LIKE '%"+search+"%'"
								+ " OR customers.email LIKE '%"+search+"%'"
								*/
								+ " ORDER BY inv_no ASC";
								
						PreparedStatement stmt = conn.prepareStatement(sql);
						//stmt.setInt(1, inv_no);
						
						ResultSet rs = stmt.executeQuery();
						
						if(getRentTotal() > 0) {
							Integer i = 0;
							Integer max = getRentTotal();
							//pb.setMaximum(max);
							while(rs.next()) {
								System.out.println(rs.getString("inv_no"));
								setProgress((int)((row*100)/max));
								
								RentalMgrDesigner.tableModel.addRow(new Object[0]);
								RentalMgrDesigner.tableModel.setValueAt(rs.getString("inv_no"),row,0);
								RentalMgrDesigner.tableModel.setValueAt(rs.getString("cust_name"),row,1);
								RentalMgrDesigner.tableModel.setValueAt(rs.getString("inv_date"),row,2);
								RentalMgrDesigner.tableModel.setValueAt(rs.getString("time"),row,3);
								RentalMgrDesigner.tableModel.setValueAt(WarehouseMgr.getDayDiff(rs.getString("start_date").toString(), rs.getString("expire_date").toString(),f),row,4);
								
					
								if((WarehouseMgr.getDayDiff(d1, rs.getString("expire_date"),f))>0 && Integer.parseInt(rs.getString("remark").toString())!=3) {
									RentalMgrDesigner.tableModel.setValueAt(WarehouseMgr.getDayDiff(d1, rs.getString("expire_date").toString(),f), row, 5);
								}else {
									if(Integer.parseInt(rs.getString("remark").toString())==3) {
										//RentalMgrDesigner.lblRentStatus.setText("อยู่ในสัญญา");
										RentalMgrDesigner.tableModel.setValueAt("ยกเลิกสัญญา",row,5);
										
									}else if(Integer.parseInt(rs.getString("remark").toString())==0) {
										//RentalMgrDesigner.lblRentStatus.setText("หมดสัญญา");
										RentalMgrDesigner.tableModel.setValueAt("หมดสัญญา",row,5);
										
									}
								}
								
								RentalMgrDesigner.tableModel.setValueAt(new DecimalFormat("#,###.00").format(Float.parseFloat(rs.getString("amount"))),row,6);

								row++;
								Thread.sleep(50);
							}
						}
						

						
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					RentalMgrDesigner.tableRent.setModel(RentalMgrDesigner.tableModel);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
				return null;
			}
		}
		new BackgroundWorker().execute();
	}
	
	
}
