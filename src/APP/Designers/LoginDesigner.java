package APP.Designers;
import javax.swing.*;
import java.awt.*;

public class LoginDesigner extends JFrame{
	public LoginDesigner() {
		setSize(600, 350);
		setTitle("Login");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int x,y;
		x=(screenWidth-getWidth())/2;
		y=(screenHeight-getHeight())/2;
		setLocation(x,y);
		
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
		lblUser.setBackground(Color.WHITE);
		lblUser.setOpaque(true);
		lblUser.setBounds(80, 80, 50, 30);
		pnlRight.add(lblUser);
		
		JTextField txtUser=new JTextField();
		txtUser.setBorder(BorderFactory.createEmptyBorder());
		txtUser.setBounds(130, 80, 200, 30);
		pnlRight.add(txtUser);
		
		ImageIcon lockIcon = new ImageIcon("assets/lock2.png");
		//JLabel lblPass=new JLabel ( new ImageIcon ( "assets/lock2.png" ), SwingConstants.CENTER );
		JLabel lblPass = new JLabel(lockIcon, JLabel.CENTER);
		//lblPass.setIcon(lockIcon);
		lblPass.setBackground(Color.WHITE);
		//lblPass.setVerticalTextPosition ( SwingConstants.BOTTOM );
		lblPass.setOpaque(true);
		lblPass.setBounds(80, 120, 50, 30);
		pnlRight.add(lblPass);
		
		JPasswordField txtPass = new JPasswordField();
		txtPass.setBorder(BorderFactory.createEmptyBorder());
		txtPass.setBounds(130, 120, 200, 30);
		pnlRight.add(txtPass);	
		
		JButton btnLogin=new JButton("Login");
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setBounds(80, 200, 120, 30);
		pnlRight.add(btnLogin);
		
		JButton btnExit=new JButton("Exit");
		btnExit.setBackground(Color.GRAY);
		btnExit.setBounds(210, 200, 120, 30);
		pnlRight.add(btnExit);		

		c.add(pnlRight);
	}

}
