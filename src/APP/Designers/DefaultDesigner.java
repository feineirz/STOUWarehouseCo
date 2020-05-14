package APP.Designers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import APP.Controllers.Global;
import APP.Controllers.View;

@SuppressWarnings("serial")
public class DefaultDesigner extends JFrame {
	/* Initial Variables */
	protected Font fontComment = new Font("Verdana", Font.ITALIC, 10);
	protected Font font = new Font("Verdana", Font.PLAIN, 12);
	protected Font fontBold = new Font("Verdana", Font.BOLD, 12);
	protected Font fontSubTitle = new Font("Verdana", Font.PLAIN, 15);
	protected Font fontTitle = new Font("Verdana", Font.BOLD, 18);
	
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();	
	private Dimension frameSize = new Dimension(1000, 600);
	
	protected int padding = 20;
	protected int gap = 6;
	
	private int upiWidth = 220;
	private int btnWidth = 100;
	private int txfWidth = 150;
	private int lblWidth = 40;
	private int ctrlHeight = 25;
	
	protected int rowFactor = ctrlHeight + gap;
	protected Dimension rowSize = new Dimension(frameSize.width - (2*padding), rowFactor);
	protected int currentRow = 0;
	protected int currentX = padding;
	
	/* End Initial Variables */
	
	/* Initial Components */
	protected JPanel pnlHeader;
	protected JPanel pnlContent;
	protected JPanel pnlUserInfo;
	protected JLabel lblUsername;
	protected JLabel lblEmail;
	protected JPanel pnlFooter;
	
	public DefaultDesigner() {
		super("STOU Warehouse Co.");
		this.setIconImage(new ImageIcon("assets/appicon.png").getImage());
		
		/********** Main Frame **********/
		this.setBounds(
				(screen.width - frameSize.width) / 2,
				(screen.height - frameSize.height) / 2,
				frameSize.width,
				frameSize.height
				);
		this.setLayout(null);
		this.setBackground(Color.BLUE);
		this.setPreferredSize(frameSize);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/********** Container **********/
		Container container = this.getContentPane();
		container.setBackground(new Color(255,255,255));
		
		/********** Add Components **********/
		
		// pnlHeader Panel //
		pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(200,200,200));
		pnlHeader.setLayout(null);
		
		/* Add Components*/		
		// Label Icon
		JLabel lblIcon = new JLabel("");
		Icon icon = new ImageIcon("assets/appicon.png");
		lblIcon.setIcon(icon);
		lblIcon.setBounds(
				padding,
				padding + (currentRow * rowFactor),
				48,
				48
				);
		pnlHeader.add(lblIcon);
		
		currentX = padding + lblIcon.getWidth() + gap;
		
		// Title Label
		JLabel lblTitle = new JLabel("STOU WAREHOUSE CO.");
		lblTitle.setFont(fontTitle);
		lblTitle.setBounds(
				currentX,
				padding + (currentRow * rowFactor),
				frameSize.width - currentX - padding,
				ctrlHeight
				);
		pnlHeader.add(lblTitle);		
		currentRow++;
		
		// Subtitle Label
		JLabel lblSubTitle = new JLabel("Thinks Warehouse, Thinks STOU.");
		lblSubTitle.setFont(fontSubTitle);
		lblSubTitle.setBounds(
				currentX,
				padding + (currentRow * rowFactor) - (rowFactor/3),
				lblTitle.getWidth(),
				ctrlHeight
				);
		pnlHeader.add(lblSubTitle);		
		
		/* Adjust Panel Size */
		pnlHeader.setBounds(
				0,
				0,
				frameSize.width - upiWidth,
				lblIcon.getHeight() + (2*padding)
				);
		container.add(pnlHeader);
		// End Title Panel //
		
		// User Info Panel //
		currentRow = 0;
		pnlUserInfo = new JPanel();
		pnlUserInfo.setBackground(new Color(220,220,220));
		pnlUserInfo.setLayout(null);
		
		/* Add Components*/
		
		// UserIcon Label
		JLabel lblUserIcon = new JLabel("");
		Icon iconUser = new ImageIcon("assets/user.png");
		lblUserIcon.setIcon(iconUser);
		lblUserIcon.setSize(48, 48);
		lblUserIcon.setLocation(
				padding,
				padding
				);
		pnlUserInfo.add(lblUserIcon);
		
		// Usernamme Label
		lblUsername =  new JLabel("username");
		lblUsername.setFont(font);
		lblUsername.setBounds(
				padding + lblUserIcon.getWidth() + gap,
				padding,
				upiWidth - padding - lblUserIcon.getWidth() - gap,
				ctrlHeight
				);
		pnlUserInfo.add(lblUsername);
		
		// Email Label
		lblEmail =  new JLabel("email@host.domain");
		lblEmail.setFont(fontComment);
		lblEmail.setBounds(
				lblUsername.getX(),
				padding + ctrlHeight - 5,
				lblUsername.getWidth() - gap,
				ctrlHeight
				);
		pnlUserInfo.add(lblEmail);
		
		/* Adjust Panel Size */
		pnlUserInfo.setBounds(
				pnlHeader.getWidth(),
				0,
				frameSize.width - pnlHeader.getWidth(),
				pnlHeader.getHeight()
				);
		container.add(pnlUserInfo);
		// End User Info Panel //
		
		
		// Content Panel //
		currentRow = 0;
		currentX = padding;
		
		pnlContent = new JPanel();
		pnlContent.setBackground(new Color(230,230,230));
		pnlContent.setLayout(null);
		
		/* Add Components*/
		
		
		/* Adjust Panel Size */
		pnlContent.setBounds(
				0,
				pnlHeader.getHeight(),
				frameSize.width,
				(2*padding) + (currentRow*rowFactor)
				);
		container.add(pnlContent);
		// End Content Panel //
		
		// Footer Panel //
		currentRow = 0;
		
		pnlFooter = new JPanel();
		pnlFooter.setBackground(new Color(200,200,200));
		pnlFooter.setLayout(null);
		
		/* Add Components*/
		
		
		/* Adjust Panel Size */
		pnlFooter.setBounds(
				0,
				pnlContent.getY() + pnlContent.getHeight(),
				frameSize.width,
				(2*padding) + ctrlHeight
				);
		container.add(pnlFooter);
		// End Footer Panel //
		
		/* Get User Info */
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				if(Global.currentUser != null) {
					lblUsername.setText(Global.currentUser.getUsername());
					lblEmail.setText(Global.currentUser.getUsername());
				}
				
			}
		});
		
		/********** Finalize **********/
		this.setSize(this.getWidth(), pnlFooter.getY() + pnlFooter.getHeight() + 30);
		this.revalidate();
		this.repaint();
		
	}
	
	protected void setWidth(int width) {
		this.setSize(width, this.getHeight());
	}
	
	public void reAdjustPanel() {
		if(this.getWidth() < 600) this.setSize(600,this.getHeight());
		if(this.getHeight() < 300) this.setSize(this.getWidth(), 300);
		
		this.setLocation((screen.width - this.getWidth()) / 2, (screen.height - this.getHeight()) / 2);
		pnlHeader.setSize(this.getWidth() - upiWidth, pnlHeader.getHeight());
		pnlUserInfo.setLocation(pnlHeader.getWidth(), 0);
		pnlContent.setSize(this.getWidth(), this.getHeight() - pnlHeader.getHeight() - pnlFooter.getHeight() - 30);
		pnlFooter.setLocation(0, pnlContent.getY() + pnlContent.getHeight());
		pnlFooter.setSize(this.getWidth(),this.getHeight());
	}
	
}
