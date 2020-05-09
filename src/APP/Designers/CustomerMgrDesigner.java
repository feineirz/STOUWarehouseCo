package APP.Designers;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class CustomerMgrDesigner extends DefaultDesigner{
	public CustomerMgrDesigner() {
		
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		
		//����
		JLabel lblCustName=new JLabel("��ª����١���");
		lblCustName.setBounds(10, 10, 100, 25);
		pnlContent.add(lblCustName);
		
		JTextField txtSearchCust=new JTextField();
		txtSearchCust.setBounds(700, 10, 200, 25);
		pnlContent.add(txtSearchCust);
		
		JButton btnSearchCust=new JButton("����");
		btnSearchCust.setBounds(910, 10, 100, 25);
		pnlContent.add(btnSearchCust);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		JTable tableCust=new JTable();
		Object data[][]= {
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				
		};
		String columns[]= {"�Ţ����١���","�����١���","��������١���","����ઢ���Ѿ���١���","������١���"};
		DefaultTableModel tableModel=new DefaultTableModel(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCust.setModel(tableModel);
		scrollTable.setViewportView(tableCust);
		pnlContent.add(scrollTable);
		
		
		//��������´
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("��������´"));
		pnlDetail.setBounds(1030, 10, 330, 350);
		
		JLabel lblCustId = new JLabel("�����١���:");
		lblCustId.setBounds(10, 20, 100, 25);
		pnlDetail.add(lblCustId);
		
		JTextField txtCustId=new JTextField();
		txtCustId.setBounds(110, 20, 210, 25);
		pnlDetail.add(txtCustId);
		
		JLabel lbl_CustName=new JLabel("�����١���:");
		lbl_CustName.setBounds(10, 50, 100, 25);
		pnlDetail.add(lbl_CustName);
		
		JTextField txtCustName=new JTextField();
		txtCustName.setBounds(110, 50, 210, 25);
		pnlDetail.add(txtCustName);
		
		
		JLabel lbl_CustAddr=new JLabel("��������١���:");
		lbl_CustAddr.setBounds(10, 80, 100, 25);
		pnlDetail.add(lbl_CustAddr);
		
		JTextArea txtCustAddr=new JTextArea();
		txtCustAddr.setBounds(110, 80, 210, 100);
		pnlDetail.add(txtCustAddr);
		
		JLabel lbl_CustPhone=new JLabel("�������Ѿ��:");
		lbl_CustPhone.setBounds(10, 190, 100, 25);
		pnlDetail.add(lbl_CustPhone);
		
		JTextField txtCustPhone=new JTextField();
		txtCustPhone.setBounds(110, 190, 210, 25);
		pnlDetail.add(txtCustPhone);		
		
		JLabel lbl_CustFax=new JLabel("�����:");
		lbl_CustFax.setBounds(10, 220, 100, 25);
		pnlDetail.add(lbl_CustFax);
		
		JTextField txtCustFax=new JTextField();
		txtCustFax.setBounds(110, 220, 210, 25);
		pnlDetail.add(txtCustFax);	
		
		JLabel lbl_CustEmail=new JLabel("����� (Email):");
		lbl_CustEmail.setBounds(10, 250, 100, 25);
		pnlDetail.add(lbl_CustEmail);
		
		JTextField txtCustEmail=new JTextField();
		txtCustEmail.setBounds(110, 250, 210, 25);
		pnlDetail.add(txtCustEmail);	
		
		JButton btnAdd=new JButton("����");
		btnAdd.setBounds(110, 290, 70, 25);
		pnlDetail.add(btnAdd);			
		
		JButton btnEdit=new JButton("���");
		btnEdit.setBounds(180, 290, 70, 25);
		pnlDetail.add(btnEdit);				
		
		JButton btnSave=new JButton("�ѹ�֡");
		btnSave.setBounds(250, 290, 70, 25);
		pnlDetail.add(btnSave);	
		
		pnlContent.add(pnlDetail);
		
		//��������´
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder(""));
		pnlBottom1.setBounds(1030, 370, 330, 130);
		
		
		pnlContent.add(pnlBottom1);
	}

}
