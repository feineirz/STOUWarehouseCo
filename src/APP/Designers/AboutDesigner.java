package APP.Designers;

import java.awt.FlowLayout;
import java.util.ArrayList;
import DBCLS.Users;

@SuppressWarnings("serial")
public class AboutDesigner extends DefaultDesigner {
	
	public AboutDesigner() {
		this.setSize(1200,900);
		reAdjustPanel();
		pnlContent.setLayout(new FlowLayout());
		
		ArrayList<Users> users = Users.listAllUsers("", "");
		if(!users.isEmpty()) {
			for (Users user : users) {
				System.out.printf("%s %s %s %s\n", user.getUserID(), user.getUsername(), user.getPhone(), user.getEmail());
			}
		}
		
		
	}

}
