package APP.Designers;
import javax.swing.*;

import APP.Controllers.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnManegMoney,btnManegLog,btnManegRent,btnManegReport,btnManegUser,btnExit;
	public MainMenuDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		JPanel DashBoard=new JPanel();
		DashBoard.setBounds(10, 10, 1370, 300);
		DashBoard.setBackground(Color.GRAY);
		pnlContent.add(DashBoard);
		
		JPanel pnlMenu=new JPanel();
		pnlMenu.setBounds(10, 320, 1370, 170);
		
		
		ImageIcon imgMoneyIcon = new ImageIcon("assets/cust3.png", BorderLayout.NORTH);
		btnManegMoney=new JButton("<html>ระบบจัดการ<br />ลูกค้า</html>",imgMoneyIcon);
		btnManegMoney.setBackground(new Color(200,200,200));
		btnManegMoney.setPreferredSize(new Dimension(120, 120));
		btnManegMoney.addActionListener(this);
		btnManegMoney.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegMoney.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegMoney);
		
		ImageIcon imgLogIcon = new ImageIcon("assets/warehouse.png", BorderLayout.NORTH);
		btnManegLog=new JButton("<html>ระบบจัดการ<br />คลังสินค้า</html>",imgLogIcon);
		btnManegLog.setBackground(new Color(200,200,200));
		btnManegLog.setPreferredSize(new Dimension(120, 120));
		btnManegLog.addActionListener(this);
		btnManegLog.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegLog.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegLog);
		
		ImageIcon imgRentIcon = new ImageIcon("assets/cart2.png", BorderLayout.NORTH);
		btnManegRent=new JButton("<html>ระบบจัดการ<br />การเช่าคลังสินค้า</html>",imgRentIcon);
		btnManegRent.setBackground(new Color(200,200,200));
		btnManegRent.setPreferredSize(new Dimension(120, 120));
		btnManegRent.addActionListener(this);
		btnManegRent.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegRent.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegRent);
		
		ImageIcon imgReport = new ImageIcon("assets/report5.png", BorderLayout.NORTH);
		btnManegReport=new JButton("ระบบรายงาน",imgReport);
		btnManegReport.setBackground(new Color(200,200,200));
		btnManegReport.setPreferredSize(new Dimension(120, 120));
		btnManegReport.addActionListener(this);
		btnManegReport.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegReport);
		
		
		pnlContent.add(pnlMenu);
		
		//footer
		btnManegUser=new JButton("ระบบจัดการผู้ใช้");
		btnManegUser.setBackground(new Color(200,200,200));
		btnManegUser.setBounds(10, 20, 150, 25);
		btnManegUser.addActionListener(this);
		pnlFooter.add(btnManegUser);
		
		btnExit=new JButton("ออกจากโปรแกรม");
		btnExit.setBackground(new Color(200,200,200));
		btnExit.setBounds(1220, 20, 150, 25);
		pnlFooter.add(btnExit);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnManegMoney) {

			new MainMenu().btnManegMoney();
		}else if(e.getSource()==btnManegLog) {
			//new UserMgr().clickbtnedit();
			
		}else if(e.getSource()==btnManegRent) {
			//new UserMgr().clickbtnsave();
			
		}else if(e.getSource()==btnManegReport) {
			//new UserMgr().clickbtnsave();
			
		}else if(e.getSource()==btnManegUser) {
			new MainMenu().btnManegUser();
			
		}else if(e.getSource()==btnExit) {
			//new UserMgr().clickbtnsave();
			
		}
		
	}
	
}
