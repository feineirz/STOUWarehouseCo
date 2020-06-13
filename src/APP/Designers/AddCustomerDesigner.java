package APP.Designers;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import APP.Controllers.AddCustomer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerDesigner extends JDialog implements ActionListener{
	public static JTextField txtCustId,txtCustName,txtCustPhone,txtCustFax,txtCustEmail;
	public static JTextArea txtCustAddr;
	public static JButton btnAdd,btnExit;
	public AddCustomerDesigner() {

		setSize(600, 350);
		setTitle("เพิ่มลูกค้า");
		
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
		
		JLabel lblCustId = new JLabel("รหัสลูกค้า:");
		lblCustId.setBounds(10, 20, 100, 25);
		pnlRight.add(lblCustId);
		
		txtCustId=new JTextField();
		txtCustId.setEditable(false);
		txtCustId.setBounds(110, 20, 260, 25);
		pnlRight.add(txtCustId);
		
		JLabel lbl_CustName=new JLabel("ชื่อลูกก้า:");
		lbl_CustName.setBounds(10, 50, 100, 25);
		pnlRight.add(lbl_CustName);
		
		txtCustName=new JTextField();
		txtCustName.setBounds(110, 50, 260, 25);
		pnlRight.add(txtCustName);
		
		
		JLabel lbl_CustAddr=new JLabel("ที่อยู่ลูกก้า:");
		lbl_CustAddr.setBounds(10, 80, 100, 25);
		pnlRight.add(lbl_CustAddr);
		
		txtCustAddr=new JTextArea();
		txtCustAddr.setBounds(110, 80, 260, 70);
		pnlRight.add(txtCustAddr);
		
		JLabel lbl_CustPhone=new JLabel("เบอร์โทรศัพท์:");
		lbl_CustPhone.setBounds(10, 160, 100, 25);
		pnlRight.add(lbl_CustPhone);
		
		txtCustPhone=new JTextField();
		txtCustPhone.setBounds(110, 160, 260, 25);
		pnlRight.add(txtCustPhone);		
		
		JLabel lbl_CustFax=new JLabel("โทรสาร:");
		lbl_CustFax.setBounds(10, 190, 100, 25);
		pnlRight.add(lbl_CustFax);
		
		txtCustFax=new JTextField();
		txtCustFax.setBounds(110, 190, 260, 25);
		pnlRight.add(txtCustFax);	
		
		JLabel lbl_CustEmail=new JLabel("อีเมล (Email):");
		lbl_CustEmail.setBounds(10, 220, 100, 25);
		pnlRight.add(lbl_CustEmail);
		
		txtCustEmail=new JTextField();
		txtCustEmail.setBounds(110, 220, 260, 25);
		pnlRight.add(txtCustEmail);	
		
		btnAdd=new JButton("เพิ่ม");
		btnAdd.setBackground(Color.GRAY);
		btnAdd.setBounds(10, 260, 120, 30);
		btnAdd.addActionListener(this);
		pnlRight.add(btnAdd);
		
		btnExit=new JButton("ยกเลิก");
		btnExit.setBackground(Color.GRAY);
		btnExit.setBounds(250, 260, 120, 30);
		btnExit.addActionListener(this);
		pnlRight.add(btnExit);		

		c.add(pnlRight);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAdd) {
			new AddCustomer().btnAdd();

		}else if(e.getSource()==btnExit) {
			this.setVisible(false);
			
		}
		
	}
}
