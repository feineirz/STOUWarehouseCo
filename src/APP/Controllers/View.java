package APP.Controllers;

import javax.swing.JFrame;

import APP.Designers.*;

public class View {
	
	// Preload Assets

	
	public static void main(String[] arg) {
		
		//defaultFrame.setVisible(true);
		
		
		if(Global.currentUser == null) {
			Login login = new Login();
			login.getmain();
		}else {
			MainMenu mainmenu = new MainMenu();
			mainmenu.getmain();
		}
		
		
	}
	

	
}
