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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import APP.Designers.*;
import DBCLS.Rental;
import DBCLS.Rents;
import DBCLS.Users;
import DBCLS.Warehouses.WHStatus;
import DBCLS.DBConnector;
import DBCLS.RentDetail;
import DBCLS.Warehouses;

public class RentalMgr {
	// Preload Assets
	static JFrame defaultFrame = new AboutDesigner();
	static JFrame rental = new RentalMgrDesigner();
	static Connection conn = DBConnector.getDBConnection();
	/*
	public static void main(String[] arg) {
		rental.setVisible(true);
		showdata();


		
	}
	*/
	public static void getRental() {
		
		rental.setVisible(true);
		//loadData();
		showdata();
		
	}
	
	public static void showdata() {
		Date d1 = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
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
			/*
			ArrayList<Rental> rentals = Rental.listAllRental("inv_no LIKE'%"+search+"%' OR cust_id LIKE '%"+search+"%' OR cust_name LIKE '%"+search+"%'", "");
			if(!rentals.isEmpty()) {
				for (Rental rental : rentals) {
					RentalMgrDesigner.tableModel.addRow(new Object[0]);
					RentalMgrDesigner.tableModel.setValueAt(rental.getInvNo(),row,0);
					RentalMgrDesigner.tableModel.setValueAt(rental.getCustName(),row,1);
					RentalMgrDesigner.tableModel.setValueAt(rental.getInvDate(),row,2);
					RentalMgrDesigner.tableModel.setValueAt(rental.getTime(),row,3);
			
					if(WarehouseMgr.getDayDiff(d1, rental.getExpireDate().toString(),f)>0) {
						RentalMgrDesigner.tableModel.setValueAt(WarehouseMgr.getDayDiff(d1, rental.getExpireDate().toString(),f)+1, row, 4);
					}else {
						if(Integer.parseInt(rental.getRemark().toString())==1) {
							//RentalMgrDesigner.lblRentStatus.setText("อยู่ในสัญญา");
							RentalMgrDesigner.tableModel.setValueAt("อยู่ในสัญญา",row,4);
						}else if(Integer.parseInt(rental.getRemark().toString())==0) {
							//RentalMgrDesigner.lblRentStatus.setText("หมดสัญญา");
							RentalMgrDesigner.tableModel.setValueAt("หมดสัญญา",row,4);
						}
					}
					

					row++;
									
					
				}
			}
			*/
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
				while(rs.next()) {
					//System.out.println(rs.getString("loc_id"));
					
					RentalMgrDesigner.tableModel.addRow(new Object[0]);
					RentalMgrDesigner.tableModel.setValueAt(rs.getString("inv_no"),row,0);
					RentalMgrDesigner.tableModel.setValueAt(rs.getString("cust_name"),row,1);
					RentalMgrDesigner.tableModel.setValueAt(rs.getString("inv_date"),row,2);
					RentalMgrDesigner.tableModel.setValueAt(rs.getString("time"),row,3);
		
					if(WarehouseMgr.getDayDiff(d1, rs.getString("expire_date"),f)>0) {
						RentalMgrDesigner.tableModel.setValueAt(WarehouseMgr.getDayDiff(d1, rs.getString("expire_date").toString(),f)+1, row, 4);
					}else {
						if(Integer.parseInt(rs.getString("remark").toString())==1) {
							//RentalMgrDesigner.lblRentStatus.setText("อยู่ในสัญญา");
							RentalMgrDesigner.tableModel.setValueAt("อยู่ในสัญญา",row,4);
							
						}else if(Integer.parseInt(rs.getString("remark").toString())==0) {
							//RentalMgrDesigner.lblRentStatus.setText("หมดสัญญา");
							RentalMgrDesigner.tableModel.setValueAt("หมดสัญญา",row,4);
							
						}
					}
					

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
					
					RentalMgrDesigner.lblVat2.setText(String.valueOf(rent.getVat()));
					RentalMgrDesigner.lblDiscount2.setText(String.valueOf(rent.getDiscount()));
					RentalMgrDesigner.lblTotal2.setText(String.valueOf(rent.getAmount()));
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
					}else if(Integer.parseInt(rent.getRemark().toString())==0) {
						RentalMgrDesigner.lblRentStatus.setText("หมดสัญญา");
						RentalMgrDesigner.lblRentStatus.setForeground(Color.RED);
					}
					
					
					
				}

			}



		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
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
					+ " WHERE inv_no="+RentalMgrDesigner.tableRent.getValueAt(index, 0).toString()+" ORDER BY rentdetail_id";
			PreparedStatement stmt = conn.prepareStatement(qry);
			//stmt.setInt(1, inv_no);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString("loc_id"));
				RentalMgrDesigner.tableModelDetail.addRow(new Object[0]);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("loc_id"),row,0);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("saleprice"),row,1);
				RentalMgrDesigner.tableModelDetail.setValueAt(rs.getString("quantity"),row,2);
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
		
		RentalMgrDesigner.lblSumTotal.setText(Double.toString(getsum()));
		
	}
	
	public static  void btnCancelRent(){
		int index=RentalMgrDesigner.tableRent.getSelectedRow();
		Connection conn = DBConnector.getDBConnection();
		try {

			String sql="UPDATE rents SET remark=? WHERE inv_no=?";
					
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, "0");
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
	}
	
    public static double getsum(){
        int rowscount = RentalMgrDesigner.tableRentDetail.getRowCount();
        double sum=0;
        for(int i =0; i< rowscount; i++){
            sum=sum+Double.valueOf(RentalMgrDesigner.tableRentDetail.getValueAt(i,1).toString());
        }
        return sum;
        
    }
    
	private static void loadData() {
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
								dialog.add(new JLabel("Processing..."), gbc);
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
					dialog.dispose();
				}
			}

			@Override
			protected Void doInBackground() throws Exception {
				for (int index = 0; index < 100; index++) {
					setProgress(index);
					Thread.sleep(100);
					/**
					 *  Do work
					 *  Do work
					 *  Do work
					 */
				}
				return null;
			}
		}
		new BackgroundWorker().execute();
	}
	
}
