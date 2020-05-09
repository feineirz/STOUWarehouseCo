package APP.Designers;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AddCustomerDesigner extends JFrame{
	
	public AddCustomerDesigner() {

		setSize(600, 350);
		setTitle("�����١���");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int x,y;
		x=(screenWidth-getWidth())/2;
		y=(screenHeight-getHeight())/2;
		setLocation(x,y);
		
		Container c =getContentPane();
		c.setLayout(null);
		
		//left panel
		JPanel pnlLeft=new JPanel();
		pnlLeft.setLayout(null);
		pnlLeft.setBounds(0, 0, 200, 350);
		pnlLeft.setBackground(Color.GRAY);
		
		ImageIcon loginIcon = new ImageIcon("assets/addUser4.png");
        JLabel loginLogo = new JLabel();
        loginLogo.setBounds(35,70,150,150);
        //loginLogo.setLocation(10, 10);
        loginLogo.setIcon(loginIcon);
        pnlLeft.add(loginLogo);
		c.add(pnlLeft);
		
		//right panel
		
		JPanel pnlRight=new JPanel();
		pnlRight.setLayout(null);
		pnlRight.setBounds(200, 0, 400, 350);
		pnlRight.setBackground(new Color(230,230,230));
		
		JLabel lblCustId = new JLabel("�����١���:");
		lblCustId.setBounds(10, 20, 100, 25);
		pnlRight.add(lblCustId);
		
		JTextField txtCustId=new JTextField();
		txtCustId.setBounds(110, 20, 260, 25);
		pnlRight.add(txtCustId);
		
		JLabel lbl_CustName=new JLabel("�����١���:");
		lbl_CustName.setBounds(10, 50, 100, 25);
		pnlRight.add(lbl_CustName);
		
		JTextField txtCustName=new JTextField();
		txtCustName.setBounds(110, 50, 260, 25);
		pnlRight.add(txtCustName);
		
		
		JLabel lbl_CustAddr=new JLabel("��������١���:");
		lbl_CustAddr.setBounds(10, 80, 100, 25);
		pnlRight.add(lbl_CustAddr);
		
		JTextArea txtCustAddr=new JTextArea();
		txtCustAddr.setBounds(110, 80, 260, 70);
		pnlRight.add(txtCustAddr);
		
		JLabel lbl_CustPhone=new JLabel("�������Ѿ��:");
		lbl_CustPhone.setBounds(10, 160, 100, 25);
		pnlRight.add(lbl_CustPhone);
		
		JTextField txtCustPhone=new JTextField();
		txtCustPhone.setBounds(110, 160, 260, 25);
		pnlRight.add(txtCustPhone);		
		
		JLabel lbl_CustFax=new JLabel("�����:");
		lbl_CustFax.setBounds(10, 190, 100, 25);
		pnlRight.add(lbl_CustFax);
		
		JTextField txtCustFax=new JTextField();
		txtCustFax.setBounds(110, 190, 260, 25);
		pnlRight.add(txtCustFax);	
		
		JLabel lbl_CustEmail=new JLabel("����� (Email):");
		lbl_CustEmail.setBounds(10, 220, 100, 25);
		pnlRight.add(lbl_CustEmail);
		
		JTextField txtCustEmail=new JTextField();
		txtCustEmail.setBounds(110, 220, 260, 25);
		pnlRight.add(txtCustEmail);	
		
		JButton btnLogin=new JButton("Add");
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setBounds(10, 260, 120, 30);
		pnlRight.add(btnLogin);
		
		JButton btnExit=new JButton("Cancle");
		btnExit.setBackground(Color.GRAY);
		btnExit.setBounds(250, 260, 120, 30);
		pnlRight.add(btnExit);		

		c.add(pnlRight);
		
	}
}
