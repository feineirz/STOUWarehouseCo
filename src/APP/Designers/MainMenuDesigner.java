package APP.Designers;
import javax.swing.*;
import java.awt.*;

public class MainMenuDesigner extends DefaultDesigner{
	
	public MainMenuDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		
		
		JPanel DashBoard=new JPanel();
		DashBoard.setBounds(10, 10, 1370, 300);
		DashBoard.setBackground(Color.GRAY);
		pnlContent.add(DashBoard);
		
		ImageIcon imgMoneyIcon = new ImageIcon("assets/cust3.png", BorderLayout.NORTH);
		JButton btnManegMoney=new JButton("<html>ระบบจัดการ<br />ลูกค้า</html>",imgMoneyIcon);
		btnManegMoney.setBackground(new Color(200,200,200));
		btnManegMoney.setBounds(50, 370, 150, 100);
		btnManegMoney.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegMoney.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlContent.add(btnManegMoney);
		
		ImageIcon imgLogIcon = new ImageIcon("assets/warehouse.png", BorderLayout.NORTH);
		JButton btnManegLog=new JButton("<html>ระบบจัดการ<br />คลังสินค้า</html>",imgLogIcon);
		btnManegLog.setBackground(new Color(200,200,200));
		btnManegLog.setBounds(250, 370, 150, 100);
		btnManegLog.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegLog.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlContent.add(btnManegLog);
		
		ImageIcon imgRentIcon = new ImageIcon("assets/cart2.png", BorderLayout.NORTH);
		JButton btnManegRent=new JButton("<html>ระบบจัดการ<br />การเช่าคลังสินค้า</html>",imgRentIcon);
		btnManegRent.setBackground(new Color(200,200,200));
		btnManegRent.setBounds(450, 370, 150, 100);
		btnManegRent.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegRent.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlContent.add(btnManegRent);
		
		ImageIcon imgReport = new ImageIcon("assets/report5.png", BorderLayout.NORTH);
		JButton btnManegReport=new JButton("ระบบรายงาน",imgReport);
		btnManegReport.setBackground(new Color(200,200,200));
		btnManegReport.setBounds(650, 370, 150, 100);
		btnManegReport.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlContent.add(btnManegReport);
		
		//footer
		JButton btnManegUser=new JButton("ระบบจัดการผู้ใช้");
		btnManegUser.setBackground(new Color(200,200,200));
		btnManegUser.setBounds(10, 20, 150, 25);
		pnlFooter.add(btnManegUser);
		
		JButton btnExit=new JButton("ออกจากโปรแกรม");
		btnExit.setBackground(new Color(200,200,200));
		btnExit.setBounds(1220, 20, 150, 25);
		pnlFooter.add(btnExit);		
	}
	
}
