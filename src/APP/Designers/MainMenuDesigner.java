package APP.Designers;
import javax.swing.*;

import APP.Controllers.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainMenuDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnManegMoney,btnManegLog,btnManegRent,btnManegReport,btnRental,btnManegUser,btnAbout,btnExit;
	protected Font fontTitle = new Font("Tahoma", Font.BOLD, 13);
	public MainMenuDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		JPanel DashBoard=new JPanel();
		DashBoard.setBounds(10, 10, 1370, 330);
		DashBoard.setBackground(Color.GRAY);
		
		JLabel imgDash=new JLabel ( new ImageIcon ("assets/dash4.jpeg") , SwingConstants.CENTER );
		//JLabel lblUser=new JLabel("USER:");
		imgDash.setBackground(Color.WHITE);
		imgDash.setOpaque(true);
		imgDash.setBounds(0, 0, 1370, 170);
		DashBoard.add(imgDash);
		
		pnlContent.add(DashBoard);
		

		
		
		JPanel pnlMenu=new JPanel();
		pnlMenu.setBounds(10, 350, 1370, 150);
		
		ImageIcon imgUserIcon = new ImageIcon("assets/addUser1.png", BorderLayout.NORTH);
		btnManegUser=new JButton("ระบบจัดการผู้ใช้",imgUserIcon);
		btnManegUser.setBackground(new Color(200,200,200));
		btnManegUser.setPreferredSize(new Dimension(200, 120));
		btnManegUser.addActionListener(this);
		btnManegUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegUser);
		
		ImageIcon imgMoneyIcon = new ImageIcon("assets/cust3.png", BorderLayout.NORTH);
		btnManegMoney=new JButton("ระบบจัดการลูกค้า",imgMoneyIcon);
		btnManegMoney.setBackground(new Color(200,200,200));
		btnManegMoney.setPreferredSize(new Dimension(200, 120));
		btnManegMoney.addActionListener(this);
		btnManegMoney.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegMoney.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegMoney);
		
		ImageIcon imgLogIcon = new ImageIcon("assets/warehouse.png", BorderLayout.NORTH);
		btnManegLog=new JButton("ระบบจัดการคลังสินค้า",imgLogIcon);
		btnManegLog.setBackground(new Color(200,200,200));
		btnManegLog.setPreferredSize(new Dimension(200, 120));
		btnManegLog.addActionListener(this);
		btnManegLog.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegLog.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegLog);
		
		ImageIcon imgRentIcon = new ImageIcon("assets/cart2.png", BorderLayout.NORTH);
		btnManegRent=new JButton("เช่าคลังสินค้า",imgRentIcon);
		btnManegRent.setBackground(new Color(200,200,200));
		btnManegRent.setPreferredSize(new Dimension(200, 120));
		btnManegRent.addActionListener(this);
		btnManegRent.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegRent.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegRent);
		
		ImageIcon imgRetal = new ImageIcon("assets/rental2.png", BorderLayout.NORTH);
		btnRental=new JButton("สัญญาเช่า",imgRetal);
		btnRental.setBackground(new Color(200,200,200));
		btnRental.setPreferredSize(new Dimension(200, 120));
		btnRental.addActionListener(this);
		btnRental.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRental.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnRental);
		
		ImageIcon imgReport = new ImageIcon("assets/report3.png", BorderLayout.NORTH);
		btnManegReport=new JButton("รายงานการขาย",imgReport);
		btnManegReport.setBackground(new Color(200,200,200));
		btnManegReport.setPreferredSize(new Dimension(200, 120));
		btnManegReport.addActionListener(this);
		btnManegReport.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManegReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlMenu.add(btnManegReport);
		
		
		pnlContent.add(pnlMenu);
		
		//footer
		
		btnAbout=new JButton("คณะผู้จัดทำ");
		btnAbout.setBackground(new Color(200,200,200));
		btnAbout.setBounds(10, 20, 150, 25);
		btnAbout.addActionListener(this);
		pnlFooter.add(btnAbout);
		
		
		btnExit=new JButton("ออกจากโปรแกรม");
		btnExit.setBackground(new Color(200,200,200));
		btnExit.setBounds(1210, 20, 160, 25);
		btnExit.addActionListener(this);
		pnlFooter.add(btnExit);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnManegMoney) {

			MainMenu.btnManegMoney();
		}else if(e.getSource()==btnManegLog) {
			MainMenu.btnManegLog();
			
			
		}else if(e.getSource()==btnManegRent) {
			MainMenu.btnManegRent();
		}else if(e.getSource()==btnRental) {
			MainMenu.btnRental();
		}else if(e.getSource()==btnManegReport) {
			MainMenu.btnManegReport();
			
		}else if(e.getSource()==btnManegUser) {
			MainMenu.btnManegUser();
			
		}else if(e.getSource()==btnAbout) {
			MainMenu.btnAbout();
			
		}else if(e.getSource()==btnExit) {
			//this.dispose();
			System.exit(0);
			
		}
		
	}
	
}
