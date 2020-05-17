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
		new CustomerMgr().getcustomerMgr();
	}
	
	public static void btnManegLog() {
		//new CustomerMgr().getshow()
	}
	
	public static void btnManegUser() {
		new UserMgr().getusermgr();
	}
}
