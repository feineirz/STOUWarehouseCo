package APP.Designers;

import java.awt.FlowLayout;

import javax.swing.JButton;

import APP.Controllers.About;

public class MainMenuDesigner extends DefaultDesigner {
	
	/* Initial Variables */
	private int frameWidth = 1200, frameHeight = 900;
	
	/* Initial Components */
	protected JButton btnAbout;
	
	public MainMenuDesigner() {
		
		this.setSize(W,H);
		this.reAdjustPanel();
		pnlContent.setLayout(new FlowLayout());
		
		btnAbout = new JButton("ABOUT");
		pnlContent.add(btnAbout);
		
	}

}
