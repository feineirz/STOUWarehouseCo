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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import APP.Designers.AboutDesigner;
import APP.Designers.AddCustomerDesigner;
import APP.Designers.AddRentalDesigner;
import APP.Designers.CustomerMgrDesigner;
import APP.Designers.RentalMgrDesigner;
import APP.Designers.ReportGenDesigner;
import APP.Designers.WarehouseMgrDesigner;
import DBCLS.Customers;
import DBCLS.DBConnector;
import DBCLS.Rental;
import DBCLS.Warehouses;

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
		loadData();
		//showdata();
	}
	
	public static void showdata() {
		Connection conn = new DBConnector().getDBConnection();
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
				ReportGenDesigner.tableModelReport.setValueAt(rs.getString("amount"),row,5);
				
				row++;

			}
			ReportGenDesigner.tableReport.setModel(ReportGenDesigner.tableModelReport);
			conn.close();
			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		ReportGenDesigner.lblSumTotal.setText(Double.toString(getsum()));
	}
	
	public static void btnShowReport() {
		if(formValidation()) {
			secBeginDate = (Date) ReportGenDesigner.beginDatePicker.getModel().getValue();
			beginDf = new SimpleDateFormat("yyyy-MM-dd");
			beginDate = beginDf.format(secBeginDate);

			secTotDate = (Date) ReportGenDesigner.toDatePicker.getModel().getValue();
			toDf = new SimpleDateFormat("yyyy-MM-dd");
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
            sum=sum+Double.valueOf(ReportGenDesigner.tableReport.getValueAt(i,5).toString());
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
					showdata();
					dialog.dispose();
				}
			}

			@Override
			protected Void doInBackground() throws Exception {
				
				for (int index = 0; index < 100; index++) {
					setProgress(index);
					Thread.sleep(10);

				}
				
				
				/*
				int ProgressType=0;
				ArrayList<Rental> rents = Rental.listAllRental("", "");
				if(rents.size() > 0) {
					Integer i = 0;
					Integer max = rents.size();
					//pb.setMaximum(max);
					for(Rental rent:rents) {
						System.out.println(rent.getInvNo());
						setProgress(++i);

						Thread.sleep(30);
					}
				}
				*/
				
				return null;
			}
		}
		new BackgroundWorker().execute();
	}
}
