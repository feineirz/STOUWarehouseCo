package APP.Designers;
import javax.swing.*;

import APP.Controllers.CustomerMgr;
import APP.Controllers.Login;
import APP.Controllers.WarehouseMgr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class LoginDesigner extends JFrame implements ActionListener{
	
	public static JTextField txtUser;
	public static JPasswordField txtPass;
	public static JButton btnLogin, btnExit;
	public static JLabel lblSetting;
	
	public LoginDesigner() {
		setSize(600, 350);
		setTitle("Login");
		/*
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int x,y;
		x=(screenWidth-getWidth())/2;
		y=(screenHeight-getHeight())/2;
		setLocation(x,y);
		*/
		this.setLocationRelativeTo(null);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setIconImage(new ImageIcon("assets/appicon.png").getImage());
		Container c =getContentPane();
		c.setLayout(null);
		
		JPanel pnlLeft=new JPanel();
		pnlLeft.setLayout(null);
		pnlLeft.setBounds(0, 0, 200, 350);
		pnlLeft.setBackground(Color.GRAY);
		
		ImageIcon loginIcon = new ImageIcon("assets/key.png");
        JLabel loginLogo = new JLabel();
        loginLogo.setBounds(35,70,150,150);
        loginLogo.setIcon(loginIcon);
        pnlLeft.add(loginLogo);
		c.add(pnlLeft);
		
		
		
		JPanel pnlRight=new JPanel();
		pnlRight.setLayout(null);
		pnlRight.setBounds(200, 0, 400, 350);
		pnlRight.setBackground(new Color(230,230,230));

		JLabel lblUser=new JLabel ( new ImageIcon ( "assets/user5.png" ), SwingConstants.CENTER );
		//JLabel lblUser=new JLabel("USER:");
		lblUser.setBackground(Color.ORANGE);
		lblUser.setOpaque(true);
		lblUser.setBounds(80, 80, 30, 30);
		pnlRight.add(lblUser);
		
		txtUser=new JTextField();
		txtUser.setBorder(BorderFactory.createEmptyBorder());
		txtUser.setBounds(110, 80, 220, 30);
		txtUser.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	               Login.clickbtnlogin();
	            }
	        }

	    });
		pnlRight.add(txtUser);
		
		ImageIcon lockIcon = new ImageIcon("assets/lock2.png");
		//JLabel lblPass=new JLabel ( new ImageIcon ( "assets/lock2.png" ), SwingConstants.CENTER );
		JLabel lblPass = new JLabel(lockIcon, JLabel.CENTER);
		//lblPass.setIcon(lockIcon);
		lblPass.setBackground(Color.ORANGE);
		//lblPass.setVerticalTextPosition ( SwingConstants.BOTTOM );
		lblPass.setOpaque(true);
		lblPass.setBounds(80, 120, 30, 30);
		pnlRight.add(lblPass);
		
		txtPass = new JPasswordField();
		txtPass.setBorder(BorderFactory.createEmptyBorder());
		txtPass.setBounds(110, 120, 220, 30);
		txtPass.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	               Login.clickbtnlogin();
	            }
	        }

	    });
		pnlRight.add(txtPass);	
		
		btnLogin=new JButton("Login");
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setBounds(80, 200, 120, 30);

		btnLogin.setVisible(true);
		btnLogin.setPreferredSize(new Dimension(400, 100));
		btnLogin.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBackground(Color.ORANGE);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setOpaque(true);
		
		btnLogin.addActionListener(this);
		pnlRight.add(btnLogin);
		
		btnExit=new JButton("Exit");
		btnExit.setBackground(Color.GRAY);
		btnExit.setBounds(210, 200, 120, 30);
		btnExit.setVisible(true);
		btnExit.setPreferredSize(new Dimension(400, 100));
		btnExit.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
		btnExit.setContentAreaFilled(false);
		btnExit.setBackground(Color.ORANGE);
		btnExit.setForeground(Color.BLACK);
		btnExit.setOpaque(true);
		btnExit.addActionListener(this);
		pnlRight.add(btnExit);		
		

		ImageIcon settingIcon = new ImageIcon("assets/setting_2.png");
		//JLabel lblPass=new JLabel ( new ImageIcon ( "assets/lock2.png" ), SwingConstants.CENTER );
		JLabel lblSetting = new JLabel(settingIcon, JLabel.CENTER);
		//lblPass.setIcon(lockIcon);
		//lblSetting.setBackground(Color.WHITE);
		//lblPass.setVerticalTextPosition ( SwingConstants.BOTTOM );
		lblSetting.setOpaque(false);
		lblSetting.setBounds(330, 260, 50, 50);
		
		lblSetting.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Login.getSetting();;

				
			}
		});
		pnlRight.add(lblSetting);

		c.add(pnlRight);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnLogin) {
			Login.clickbtnlogin();
		}else if(arg0.getSource()==btnExit) {
			Login.clickbtnexit();
			
		}
		
	}

}
