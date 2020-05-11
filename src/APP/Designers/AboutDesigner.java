package APP.Designers;

import java.awt.FlowLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AboutDesigner extends DefaultDesigner {
	
	public AboutDesigner() {
		/*************** Init Frame Properties ***************/
		this.setSize(1200,900);
		reAdjustPanel();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		pnlContent.setLayout(new FlowLayout());
		
		for(int idx = 0; idx < 400; idx++) {
			JButton btn = new JButton("B" + (idx + 1));
			pnlContent.add(btn);
		}
		
	}

}
