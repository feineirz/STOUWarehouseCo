package APP.Controllers;

import javax.swing.*;

import APP.Designers.*;

public class RentalMgr {
	// Preload Assets
	static JFrame defaultFrame = new AboutDesigner();
	static JFrame rental = new RentalMgrDesigner();
	public static void main(String[] arg) {
		
		//defaultFrame.setVisible(true);
		rental.setVisible(true);
		
		
	}
}
