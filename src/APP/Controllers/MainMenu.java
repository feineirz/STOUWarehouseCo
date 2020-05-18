package APP.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import APP.Designers.MainMenuDesigner;

@SuppressWarnings("serial")
public class MainMenu extends MainMenuDesigner implements ActionListener {
	
	public MainMenu() {
		
		btnAbout.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmdString = e.getActionCommand();
		
		switch (cmdString) {
		case "ABOUT":
			View.showAbout();
			break;

		default:
			break;
		}
		
	}

}
