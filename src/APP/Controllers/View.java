package APP.Controllers;

import javax.swing.JFrame;

import APP.Designers.*;

public class View {
	
	// Preload Assets
	static JFrame defaultFrame = new AboutDesigner();
	static JFrame addCust = new AddCustomerDesigner();
	public static void main(String[] arg) {
		
		//defaultFrame.setVisible(true);
		addCust.setVisible(true);
		
		
	}
	

	
}
