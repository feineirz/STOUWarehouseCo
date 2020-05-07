package APP.Designers;

import java.awt.FlowLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AboutDesigner extends DefaultDesigner {
	
	public AboutDesigner() {
		this.setSize(800,600);
		this.reAdjustPanel();
		pnlContent.setLayout(new FlowLayout());
		
		for(int idx = 0; idx < 100; idx++) {
			JButton btn = new JButton("Button " + (idx + 1));
			pnlContent.add(btn);
		}
		
		
	}

}
