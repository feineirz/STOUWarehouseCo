package APP.Controllers;

import javax.swing.JDialog;
import javax.swing.JFrame;

import APP.Designers.AboutDesigner;
import APP.Designers.DefaultDesigner;
import APP.Designers.MainMenuDesigner;

public class View {
	
	// Preload Assets
	static MainMenu defaultFrame;
	static AboutDesigner frmAbout;
	
	public static void main(String[] arg) {
		
		defaultFrame = new MainMenu();
		frmAbout = new AboutDesigner();
		
		defaultFrame.setVisible(true);		
		
	}
	
	public static void showAbout() {
		
		frmAbout.setVisible(true);
		
	}
	
}
