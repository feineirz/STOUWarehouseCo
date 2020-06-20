package APP.Controllers;
import javax.swing.JOptionPane;

import APP.Designers.*;
import DBCLS.Users;
public class Login{
	// Preload Assets

	static LoginDesigner frmLogin = new LoginDesigner();
	/*
	public static void main(String[] arg) {
		
		frmLogin.setVisible(true);
		
		
	}
	*/
	
	public static void getmain() {
		
		frmLogin.setVisible(true);
		
		
	}
	
	public static void getSetting() {
		Setting.getmain();
	}
	
	public static void clickbtnlogin() {
		
		try {
			Users login=Users.performLogIn(frmLogin.txtUser.getText(), String.valueOf(frmLogin.txtPass.getPassword()));
			if(login != null) {
				Global.currentUser=login;
				frmLogin.setVisible(false);
				//MainMenu.getmain();
				Logo.getmain();
			}else{

				JOptionPane.showMessageDialog(null, "Username หรือ Password ไม่ถูกต้อง", "แจ้งเตือน",JOptionPane.ERROR_MESSAGE);
			}


		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	
	public static void clickbtnexit() {
		System.exit(0);
	}

}
