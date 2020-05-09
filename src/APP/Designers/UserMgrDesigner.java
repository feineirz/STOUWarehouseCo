package APP.Designers;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class UserMgrDesigner extends DefaultDesigner{
	
	public UserMgrDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		
		//����
		JLabel lblUserName=new JLabel("��ª��ͼ�����к�");
		lblUserName.setBounds(10, 10, 100, 25);
		pnlContent.add(lblUserName);
		
		JTextField txtSearchCust=new JTextField();
		txtSearchCust.setBounds(700, 10, 200, 25);
		pnlContent.add(txtSearchCust);
		
		JButton btnSearchUser=new JButton("����");
		btnSearchUser.setBounds(910, 10, 100, 25);
		pnlContent.add(btnSearchUser);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		JTable tableCust=new JTable();
		Object data[][]= {
				{null,null,null,null},
				{null,null,null,null},
				{null,null,null,null},
				
		};
		String columns[]= {"���ʼ����","���ͼ����","����ઢ���Ѿ���١���","������١���"};
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
		
		JLabel lblUserId = new JLabel("���ʼ����:");
		lblUserId.setBounds(10, 20, 100, 25);
		pnlDetail.add(lblUserId);
		
		JTextField txtUserId=new JTextField();
		txtUserId.setBounds(110, 20, 210, 25);
		pnlDetail.add(txtUserId);
		
		JLabel lbl_UserName=new JLabel("���ͼ����:");
		lbl_UserName.setBounds(10, 50, 100, 25);
		pnlDetail.add(lbl_UserName);
		
		JTextField txtUserName=new JTextField();
		txtUserName.setBounds(110, 50, 210, 25);
		pnlDetail.add(txtUserName);
		
				
		JLabel lbl_UserPhone=new JLabel("�������Ѿ��:");
		lbl_UserPhone.setBounds(10, 80, 100, 25);
		pnlDetail.add(lbl_UserPhone);
		
		JTextField txtUserPhone=new JTextField();
		txtUserPhone.setBounds(110, 80, 210, 25);
		pnlDetail.add(txtUserPhone);		
		
		
		JLabel lbl_UserEmail=new JLabel("����� (Email):");
		lbl_UserEmail.setBounds(10, 110, 100, 25);
		pnlDetail.add(lbl_UserEmail);
		
		JTextField txtUserEmail=new JTextField();
		txtUserEmail.setBounds(110, 110, 210, 25);
		pnlDetail.add(txtUserEmail);	
		
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
