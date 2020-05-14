package APP.Designers;

import java.awt.FlowLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AboutDesigner extends DefaultDesigner {
	
	public AboutDesigner() {
		this.setSize(400,600);
		reAdjustPanel();
		pnlContent.setLayout(new FlowLayout());
		
		for(int idx = 0; idx < 40; idx++) {
			JButton btn = new JButton("ปุ่ม " + (idx + 1));
			pnlContent.add(btn);
		}
		
	}

}
