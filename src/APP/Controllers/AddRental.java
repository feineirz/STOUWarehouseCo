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
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.*;

import APP.Designers.*;
import DBCLS.*;
import DBCLS.Warehouses.WHStatus;

public class AddRental {
	// Preload Assets
	static int custID;
	static int indexSelect;
	static JFrame addRental = new AddRentalDesigner();
	static String startDate,endDate;
	static Date selectedStartDate,selectedEndtDate;
	static DateFormat df;
	static pickupLoc click_m;
	static int totalDate=1;
	static Double sum,vat,discount,totalsum;
	static boolean addRent = false,updateWH = false,rentadd = false;
	
	/*
	public static void main(String[] arg) {
		getAddRental();



		
	}
	*/
	public static void getAddRental() {
		addRental.setVisible(true);
		cleartxt();
		//showdata();
		loadCust();

	}
	
    public static double getsum(){
        int rowscount = AddRentalDesigner.tableCust.getRowCount();
        double sum=0;
        for(int i =0; i< rowscount; i++){
            //sum=sum+Double.valueOf(AddRentalDesigner.tableCust.getValueAt(i,4).toString());
            
    		String str = AddRentalDesigner.tableCust.getValueAt(i,4).toString();
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
    
    public static void getsumtotal() {
    	

		/*
		AddRentalDesigner.lblSumTotal.setText(Double.toString(getsum()));
		AddRentalDesigner.lblVat2.setText(Double.toString(getsum()*7/100));
		AddRentalDesigner.lblTotal2.setText(Double.toString((getsum()*7/100)-Double.parseDouble(AddRentalDesigner.txtDiscount2.getText())));
		*/
		AddRentalDesigner.lblSumTotal.setText(new DecimalFormat("#,###.00").format(getsum()));
		AddRentalDesigner.lblVat2.setText(new DecimalFormat("#,###.00").format(getsum()*7/100));
		AddRentalDesigner.lblTotal2.setText(new DecimalFormat("#,###.00").format(getsum()+(getsum()*7/100)-Double.parseDouble(AddRentalDesigner.txtDiscount2.getText())));
		
		sum=getsum();
		vat=getsum()*7/100;
		discount=Double.parseDouble(AddRentalDesigner.txtDiscount2.getText());
		totalsum=(sum+vat)-discount;
    }
    

	
	public static void getMaxId() {
		int maxID=0;
		try {
			ArrayList<Rental> rentals2 = Rental.listAllRental("", "");
			if(!rentals2.isEmpty()) {
				for (Rental rental1 : rentals2) {
					maxID=rental1.getInvNo();
				}
				AddRentalDesigner.txtRentId.setText(String.valueOf(maxID+1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void showdata() {
		/*
		getMaxId();
		
		AddRentalDesigner.cbCustName.removeAllItems();
		AddRentalDesigner.cbCustName.addItem("กรุณาเลือกชื่อลูกค้า");
		
		
		ArrayList<Customers> cuts = Customers.listAllCustomers("", "cust_id ASC");
		if(!cuts.isEmpty()) {
			for (Customers cut : cuts) {
				AddRentalDesigner.cbCustName.addItem(cut.getCustomerName());
			}
		}
		*/
		

		AddRentalDesigner.txtDiscount2.setText("0");
		AddRentalDesigner.btnEdit.setEnabled(false);
		AddRentalDesigner.btnDelete.setEnabled(false);
		AddRentalDesigner.btnSelect.setEnabled(true);
	
	}
	
	public static void btnReset() {
		cleartxt();
		
		/*
		if(formValidation()) {
			Vector saleProd = new Vector();
			saleProd.add(AddRentalDesigner.txtLocId.getText());
			try {
				selectedStartDate = (Date) AddRentalDesigner.startDatePicker.getModel().getValue();
				df = new SimpleDateFormat("yyyy-MM-dd");
				startDate = df.format(selectedStartDate);
				//JOptionPane.showMessageDialog(null,reportDate);
				saleProd.add(startDate);
				//System.out.println(startDate);
			} catch (Exception ex) {
				System.out.println("Please select some Date!!");
			}
			

			try {
				selectedEndtDate = (Date) AddRentalDesigner.endDatePicker.getModel().getValue();
				df = new SimpleDateFormat("yyyy-MM-dd");
				endDate = df.format(selectedEndtDate);
				//JOptionPane.showMessageDialog(null,reportDate);
				saleProd.add(endDate);
			} catch (Exception ex) {
				System.out.println("Please select some Date!!");
			}
			
			
			AddRentalDesigner.tableModel.addRow(saleProd);
		}
		*/
	}
	
	public static void btnSelect() {
		WHLocationPickup.getWHLocationPickup();
		
	}
	
	public static void mouseclick() {
		indexSelect=AddRentalDesigner.tableCust.getSelectedRow();
		String locId=AddRentalDesigner.tableCust.getValueAt(indexSelect, 1).toString();
		String locStatus=AddRentalDesigner.tableCust.getValueAt(indexSelect, 2).toString();
		Double locPrice=(Double) AddRentalDesigner.tableCust.getValueAt(indexSelect, 3);
		//String locRemark=AddRentalDesigner.tableCust.getValueAt(indexSelect, 3).toString();
		int s_loc = WHLocationPickupDesigner.locSelected;

		click_m =new pickupLoc(locId,locPrice,s_loc);
		
		
		AddRentalDesigner.btnEdit.setEnabled(true);
		AddRentalDesigner.btnDelete.setEnabled(true);
	}
	
	public static void btnDelete() {
		
		WHLocationPickup.list.remove(click_m);
		AddRentalDesigner.tableModel.removeRow(indexSelect);
		//WHLocationPickup.list.remove(arg0);
		
		AddRentalDesigner.btnEdit.setEnabled(false);
		AddRentalDesigner.btnDelete.setEnabled(false);
		System.out.println(indexSelect);
		//new WHLocationPickup().getWHLocationPickup();
		//AddRentalDesigner.lblSumTotal.setText(Double.toString(getsum()));
		updateRow();
		getsumtotal();
		
	}
	
	public static void clickWH() {
		int totalRow=AddRentalDesigner.tableCust.getRowCount()-1;
		while(totalRow > -1) {
			AddRentalDesigner.tableModel.removeRow(totalRow);
			totalRow--;
		}
		int row=0;
		for(pickupLoc pickupLoc : WHLocationPickup.list) {
			AddRentalDesigner.tableModel.addRow(new Object[0]);
			AddRentalDesigner.tableModel.setValueAt(row+1,row,0);
			AddRentalDesigner.tableModel.setValueAt(pickupLoc.getLocId(),row,1);
			AddRentalDesigner.tableCust.setValueAt(totalDate, row, 2);
			AddRentalDesigner.tableCust.setValueAt(pickupLoc.getLocPrice(), row, 3);
			//AddRentalDesigner.tableModel.setValueAt(pickupLoc.getLocPrice()*totalDate,row,4);
			AddRentalDesigner.tableCust.setValueAt(new DecimalFormat("#,###.00").format(pickupLoc.getLocPrice()*totalDate),row,4);
			row++;
		}

		AddRentalDesigner.tableCust.setModel(AddRentalDesigner.tableModel);
		
		
		//System.out.println("Sum:"+getsum());
		getsumtotal();
		/*
		AddRentalDesigner.lblSumTotal.setText(Double.toString(getsum()));
		AddRentalDesigner.lblVat2.setText(Double.toString(getsum()*7/100));
		AddRentalDesigner.lblTotal2.setText(Double.toString((getsum()*7/100)-Double.parseDouble(AddRentalDesigner.txtDiscount2.getText())));
		*/
	}
	
	public static void updateRow() {

		int row=0;
		for(pickupLoc pickupLoc : WHLocationPickup.list) {
			AddRentalDesigner.tableModel.setValueAt(row+1,row,0);

			row++;
		}

		AddRentalDesigner.tableCust.setModel(AddRentalDesigner.tableModel);
	}
	
	static int rentMaxId() {
		int rentalMaxId = 0;
		ArrayList<Rental> rentals = Rental.listAllRental();
		if(!rentals.isEmpty()) {
			for (Rental rental : rentals) {

				rentalMaxId=rental.getInvNo();
				

			}
			
		}
		return rentalMaxId;

	}
	
	public static void totalDate() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
		df = new SimpleDateFormat("yyyy-MM-dd");
		int rowscount = AddRentalDesigner.tableCust.getRowCount();
		int totalRow=AddRentalDesigner.tableCust.getRowCount();
		if((Date) AddRentalDesigner.startDatePicker.getModel().getValue()!=null && (Date) AddRentalDesigner.endDatePicker.getModel().getValue()!=null) {
			try {
				selectedStartDate = (Date) AddRentalDesigner.startDatePicker.getModel().getValue();
				selectedEndtDate = (Date) AddRentalDesigner.endDatePicker.getModel().getValue();
				
				endDate = df.format(selectedEndtDate);
				startDate = df.format(selectedStartDate);
				String s1 = df.format(selectedStartDate);
				String s2 = df.format(selectedEndtDate);
				
				totalDate=(int)WarehouseMgr.getDayDiff(s1, s2,f);
				System.out.println(totalDate);
				if(rowscount>0) {
	            	for(int i=0; i<=rowscount-1; i++) {
	            		AddRentalDesigner.tableCust.setValueAt(WarehouseMgr.getDayDiff(s1, s2,f), i, 2);
	            		AddRentalDesigner.tableCust.setValueAt(new DecimalFormat("#,###.00").format(WHLocationPickup.list.get(i).getLocPrice()*totalDate), i, 4);
	            		System.out.println("=>"+WHLocationPickup.list.get(i).getLocPrice());
	            	}
				}

			} catch (Exception ex) {
				
			}
			getsumtotal();
		}

	}
	
	public static void clickbtnsave() {
	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
		Date d1 = new Date();
		/*
		Format formatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
	    Date d1 = ((DateFormat) formatter).parse(formatter.format(new Date()));
	    */
	    String d2 = f.format(new Date());
		System.out.println("========================>"+d1);
		
		String stime = new SimpleDateFormat("HH:mm:ss").format(d1.getTime());

		System.out.println(stime);
		if(formValidation()) {
			ArrayList<Customers> cuts2 = Customers.listAllCustomers("cust_name LIKE'%"+AddRentalDesigner.cbCustName.getItemAt(AddRentalDesigner.cbCustName.getSelectedIndex()).toString()+"%'", "");		
			if(!cuts2.isEmpty()) {
				for (Customers cut1 : cuts2) {
					custID=cut1.getCustomerID();
					if(Global.currentUser != null) {
						System.out.println(Global.currentUser.getUsername());
					}
				}
			}
			
			try {
				selectedStartDate = (Date) AddRentalDesigner.startDatePicker.getModel().getValue();
				df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
				startDate = df.format(selectedStartDate);
				selectedEndtDate = (Date) AddRentalDesigner.endDatePicker.getModel().getValue();

				endDate = df.format(selectedEndtDate);
				//JOptionPane.showMessageDialog(null,reportDate);
				
				System.out.println("=>"+startDate);
				System.out.println("=>"+endDate);

			} catch (Exception ex) {
				System.out.println("Please select some Date!!");
			}
			
			
			
    		try {

    			float vat = DecimalFormat.getNumberInstance().parse(AddRentalDesigner.lblVat2.getText().toString()).floatValue();
    			float discount = DecimalFormat.getNumberInstance().parse(AddRentalDesigner.txtDiscount2.getText()).floatValue();
    			float totalsum = DecimalFormat.getNumberInstance().parse(AddRentalDesigner.lblTotal2.getText()).floatValue();
    			//Rental.addNewRental(custID, d1,stime, selectedStartDate, selectedEndtDate, Float.parseFloat(AddRentalDesigner.lblVat2.getText()), Float.parseFloat(AddRentalDesigner.txtDiscount2.getText()), Float.parseFloat(AddRentalDesigner.lblTotal2.getText()), Global.currentUser.getUserID(), "1");
    			//Rental.addNewRental(custID, d1,stime, selectedStartDate, selectedEndtDate, vat.floatValue(), discount.floatValue(), totalsum.floatValue(), Global.currentUser.getUserID(), "1");
    			rentadd=Rental.addNewRental(custID, d2,stime, startDate, endDate, vat, discount, totalsum, Global.currentUser.getUserID(), "1");
    			
        		if(rentadd) {
        			saveData();
        			/*
        			for(pickupLoc pickupLoc : WHLocationPickup.list) {

        				//WHLocationPickupDesigner.lbl[pickupLoc.gets_loc()].setBackground(Color.GREEN);
        				addRent=RentDetail.addNewRentDetail(rentMaxId(), pickupLoc.getLocId(), totalDate, 0.0, pickupLoc.getLocPrice());
        				updateWH=Warehouses.updateWarehouseInfo(pickupLoc.getLocId(), WHStatus.FULL, pickupLoc.getLocPrice(),"");
        				
        			}
        			*/
        			
        			
        		}
        		/*
    			if(rentadd && addRent && updateWH) {
    				JOptionPane.showMessageDialog(null, "บันทึกข้อมูลเรียบร้อยแล้ว", "ผลการบันทึกรายการ", JOptionPane.INFORMATION_MESSAGE);
    			}else {
    				JOptionPane.showMessageDialog(null, "บันทึกข้อมูลไม่ได้", "แจ้งเตือน", JOptionPane.ERROR_MESSAGE);
    			}
    			//System.out.println(l); //111111.23
    			cleartxt();
    			*/
    
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}





		}

		
	}
	
	public static void cleartxt() {
		AddRentalDesigner.txtRentId.setText(String.valueOf(rentMaxId()+1));
		AddRentalDesigner.txtLocId.setText("");
		AddRentalDesigner.startDatePicker.getModel().setValue(null);
		AddRentalDesigner.endDatePicker.getModel().setValue(null);
		//AddRentalDesigner.endDatePicker.getJFormattedTextField().setText("");
		AddRentalDesigner.cbCustName.setSelectedIndex(0);
		AddRentalDesigner.lblSumTotal.setText("");

		AddRentalDesigner.lblVat2.setText("");
		AddRentalDesigner.txtDiscount2.setText("0");
		AddRentalDesigner.lblTotal2.setText("");
		int totalRow=AddRentalDesigner.tableCust.getRowCount()-1;
		while(totalRow > -1) {
			AddRentalDesigner.tableModel.removeRow(totalRow);
			totalRow--;
		}
		WHLocationPickup.list.clear();
		totalDate=1;
	}
	
	
	public static boolean formValidation() {
		int totalRow=AddRentalDesigner.tableCust.getRowCount();
		String txt="กรุณากรอกข้อมูลต่อไปนี้ให้ครบ\n";
		int check=0;
		if(AddRentalDesigner.cbCustName.getSelectedIndex() == 0) {

			txt +="-ชื่อลูกค้า\n";
			check=1;
		}
		/*
		if(AddRentalDesigner.txtLocId.getText().isEmpty()){
			txt +="-รหัสตำแหน่ง\n";
			check=1;
		
		}
		*/
		if(totalRow<=0) {
			txt +="-คลังสินค้า\n";
			check=1;
		}
		if((Date) AddRentalDesigner.startDatePicker.getModel().getValue()==null) {
			txt +="-วันเริ่มต้น\n";
			check=1;
		}

		if((Date) AddRentalDesigner.endDatePicker.getModel().getValue()==null) {
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
	
	public static void loadCust() {
		class loadCustBack extends SwingWorker<Void, Void> {

			private JProgressBar pb;
			private JDialog dialog;

			public loadCustBack() {
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
								dialog.add(new JLabel("โหลดข้อมูลลูกค้า..."), gbc);
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
				AddRentalDesigner.btnSelect.setEnabled(false);

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
				
				
				return null;
			}
		}
		new loadCustBack().execute();
	}
	
	
	
	public static void saveData() {
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
								dialog.add(new JLabel("กำลังบันทึกข้อมูล..."), gbc);
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
					//updatecount();
	    			if(rentadd && addRent && updateWH) {
	    				JOptionPane.showMessageDialog(null, "บันทึกข้อมูลเรียบร้อยแล้ว", "ผลการบันทึกรายการ", JOptionPane.INFORMATION_MESSAGE);
	    			}else {
	    				JOptionPane.showMessageDialog(null, "บันทึกข้อมูลไม่ได้", "แจ้งเตือน", JOptionPane.ERROR_MESSAGE);
	    			}
	    			cleartxt();
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

					if(WHLocationPickup.list.size() > 0) {
						Integer i = 0;
						Integer max = WHLocationPickup.list.size();
						//pb.setMaximum(max);
						for(pickupLoc pickupLoc : WHLocationPickup.list) {
							System.out.println(pickupLoc.getLocId());
							setProgress((int)((++i*100)/max));
		    				addRent=RentDetail.addNewRentDetail(rentMaxId(), pickupLoc.getLocId(), totalDate, 0.0, pickupLoc.getLocPrice());
		    				updateWH=Warehouses.updateWarehouseInfo(pickupLoc.getLocId(), WHStatus.FULL, pickupLoc.getLocPrice(),"");

							Thread.sleep(30);
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
				return null;
			}
		}
		new BackgroundWorker().execute();
	}
}
