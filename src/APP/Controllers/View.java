package APP.Controllers;

import javax.swing.JFrame;

import APP.Designers.DefaultDesigner;

public class View {
	
	// Preload Assets
	static 
	JFrame defaultDesiner = new DefaultDesigner();
	
	public static void main(String[] arg) {
		
		defaultDesiner.setVisible(true);
		
	}
	
}
