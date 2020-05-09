package APP.Controllers;

import java.sql.Connection;

import javax.swing.JFrame;

import APP.Designers.*;
import DBCLS.*;

public class CustomerMgr {
	// Preload Assets
	//static Connection conn= DBConnector.getDBConnection();
	static Customers c=new Customers();
	static JFrame defaultFrame = new AboutDesigner();
	static JFrame cust = new CustomerMgrDesigner();
	public static void main(String[] arg) {
		
		//defaultFrame.setVisible(true);
		cust.setVisible(true);
		
		//c.openConnection();
		
		//System.out.println(conn);
	}
}
