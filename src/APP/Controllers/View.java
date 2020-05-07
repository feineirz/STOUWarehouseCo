package APP.Controllers;

import javax.swing.JFrame;

import APP.Designers.AboutDesigner;

public class View {
	
	// Preload Assets
	static 
	JFrame defaultFrame = new AboutDesigner();
	
	public static void main(String[] arg) {
		
		defaultFrame.setVisible(true);
		
	}
	
}
