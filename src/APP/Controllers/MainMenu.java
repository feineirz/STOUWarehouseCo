package APP.Controllers;
import javax.swing.*;
import APP.Designers.*;

public class MainMenu{
	// Preload Assets
	static JFrame mainMenu = new MainMenuDesigner();
	public static void getMainMenu() {
		
		mainMenu.setVisible(true);

		
	}
	
	
	
	public static void getmain() {
		mainMenu.setVisible(true);

	}
	
	
	public static void btnManegMoney() {
		new CustomerMgr().getcustomerMgr();
	}
	
	public static void btnManegLog() {
		new WarehouseMgr();
		
		//WarehouseMgr.wareHouse.setVisible(true);
		WarehouseMgr.getWarehouseMgr();
	}
	public static void btnManegRent() {
		new AddRental().getAddRental();
	}
	public static void btnManegUser() {
		new UserMgr().getusermgr();
	}
	
	public static void btnRental() {
		new RentalMgr().getRental();
	}
	
	public static void btnManegReport() {
		new ReportGen().getReport();
	}
	
	public static void btnAbout() {
		new About().getAbout();
	}
}
