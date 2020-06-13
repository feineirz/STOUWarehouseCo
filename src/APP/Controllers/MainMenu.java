package APP.Controllers;
import javax.swing.*;
import APP.Designers.*;

public class MainMenu{
	// Preload Assets
	static JFrame mainMenu = new MainMenuDesigner();

	
	public static void getmain() {
		mainMenu.setVisible(true);
	}
	
	public static void btnManegMoney() {
		CustomerMgr.getcustomerMgr();
		mainMenu.setVisible(false);
	}
	
	public static void btnManegLog() {
		WarehouseMgr.getWarehouseMgr();
		mainMenu.setVisible(false);
		
	}
	public static void btnManegRent() {
		AddRental.getAddRental();
		mainMenu.setVisible(false);
	}
	public static void btnManegUser() {
		UserMgr.getusermgr();
		mainMenu.setVisible(false);
	}
	
	public static void btnRental() {
		RentalMgr.getRental();
		mainMenu.setVisible(false);
	}
	
	public static void btnManegReport() {
		ReportGen.getReport();
		mainMenu.setVisible(false);
	}
	
	public static void btnAbout() {
		About.getAbout();
		mainMenu.setVisible(false);
	}
}
