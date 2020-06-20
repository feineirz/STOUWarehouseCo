package APP.Controllers;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import APP.Designers.AddRentalDesigner;
import APP.Designers.WHLocationPickupDesigner;

import DBCLS.Warehouses;
import DBCLS.pickupLoc;

public class WHLocationPickup {
	static ArrayList<pickupLoc> list = new ArrayList<pickupLoc>();
	static WHLocationPickupDesigner WHPicup = new WHLocationPickupDesigner();
	static int x=0;
	public static void getWHLocationPickup() {
		
		x=0;
		//pickupList=0;
		showdata();
		WHPicup.setModal(true);
		WHPicup.setVisible(true);
		
	
	}
	
	public static void showdata() {
		
		try {
			ArrayList<Warehouses> locs=Warehouses.listAllWarehouseLocation("", "");
			
			if(!locs.isEmpty()) {
				for (Warehouses loc : locs) {

					if(loc.getStatus().toString()=="FULL") {
						WHLocationPickupDesigner.lbl[x].setBackground(Color.RED);
					}else if(loc.getStatus().toString()=="MAINTENANCE") {
						WHLocationPickupDesigner.lbl[x].setBackground(Color.YELLOW);
					}else if(loc.getStatus().toString()=="EMPTY") {
						WHLocationPickupDesigner.lbl[x].setBackground(Color.WHITE);
					}
					x++;
				}
			}
			//WHLocationPickupDesigner.lbl[19].setBackground(Color.GREEN);
			
			for(pickupLoc pickupLoc : WHLocationPickup.list) {

				WHLocationPickupDesigner.lbl[pickupLoc.gets_loc()].setBackground(Color.GREEN);
				//System.out.println("++++++>"+pickupLoc.gets_loc());

				//System.out.println("++++++>"+pickupLoc.gets_loc());
			}
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void mouseclick() {
		
		try {
			ArrayList<Warehouses> locs=Warehouses.listAllWarehouseLocation("loc_id='"+WHLocationPickupDesigner.selectedLoc+"'", "");
			
			if(!locs.isEmpty()) {
				for (Warehouses loc : locs) {
					String locId=loc.getLocID().toString();
					String locStatus=loc.getStatus().toString();
					Double locPrice=loc.getPrice();
					String locRemark=loc.getRemark().toString();

					if(WHLocationPickupDesigner.pickupAdd) {
						list.add(new pickupLoc(locId,locPrice,WHLocationPickupDesigner.locSelected));
						AddRental.clickWH();
					}
					if(WHLocationPickupDesigner.pickupDel) {
						//list.remove(locId,locStatus,locPrice,locRemark,WHLocationPickupDesigner.locSelected);
						//pickupLoc m =new pickupLoc("A01","EMPTY",1100.0,"t",0);
						pickupLoc m =new pickupLoc(locId,locPrice,WHLocationPickupDesigner.locSelected);
						list.remove(m);
						AddRental.clickWH();
						//System.out.println(list.contains(m));
						//list.clear();
					}
					

				}
				
				
			}

			System.out.println(list.toString());
			
			//System.out.println(list.get(2));

			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		//WarehouseMgrDesigner.btnEdit.setEnabled(true);	
		//WarehouseMgrDesigner.btnSave.setEnabled(false);	


	}
	
	public static void selected(String e) {
		//new AddRentalDesigner().txtLocId.setText(e);
		//System.out.println(new WHLocationPickupDesigner().selected);
		System.out.println(e);
	}
}
