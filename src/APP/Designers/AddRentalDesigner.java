package APP.Designers;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddRentalDesigner extends DefaultDesigner{
	public AddRentalDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		
		//����
		JLabel lblRentName=new JLabel("��¡�á�����");
		lblRentName.setBounds(10, 10, 100, 25);
		pnlContent.add(lblRentName);
		
		JLabel lblRentId=new JLabel("�Ţ������˹��");
		lblRentId.setBounds(710, 10, 100, 25);
		pnlContent.add(lblRentId);
		
		JTextField txtRentId=new JTextField();
		txtRentId.setBounds(810, 10, 200, 25);
		pnlContent.add(txtRentId);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		JTable tableCust=new JTable();
		Object data[][]= {
				{null,null,null},
				{null,null,null},
				{null,null,null},
				
		};
		String columns[]= {"���ʵ��˹�","��������","�ӹǹ�Թ"};
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
		pnlDetail.setBorder(BorderFactory.createTitledBorder("�������١���"));
		pnlDetail.setBounds(1030, 10, 330, 200);
		
		JComboBox cbCustName = new JComboBox<String>();
		cbCustName.setBounds(10, 20, 230, 23);
		cbCustName.addItem("��������͡�����١���");
		cbCustName.addItem("a");
		cbCustName.addItem("b");
		cbCustName.addItem("c");	
		pnlDetail.add(cbCustName);
		
		JButton btnCustAdd=new JButton("����");
		btnCustAdd.setBounds(250, 20, 70, 25);
		pnlDetail.add(btnCustAdd);			
		

		
		pnlContent.add(pnlDetail);
		
		//���˹�
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder("���˹�"));
		pnlBottom1.setBounds(1030, 220, 330, 220);
		
		JLabel lblLocId = new JLabel("���ʵ��˹�:");
		lblLocId.setBounds(10, 20, 100, 25);
		pnlBottom1.add(lblLocId);
		
		JTextField txtLocId=new JTextField();
		txtLocId.setBounds(90, 20, 160, 25);
		pnlBottom1.add(txtLocId);
		
		JButton btnSelect=new JButton("���͡");
		btnSelect.setBounds(250, 20, 70, 25);
		pnlBottom1.add(btnSelect);	
		
		
		JLabel lblRentTime = new JLabel("�����������");
		lblRentTime.setBounds(10, 60, 100, 25);
		pnlBottom1.add(lblRentTime);
		
		JLabel lblRentStart = new JLabel("�ѹ�������:");
		lblRentStart.setBounds(20, 90, 100, 25);
		pnlBottom1.add(lblRentStart);		
		
		JTextField txtRentStart=new JTextField();
		txtRentStart.setBounds(90, 90, 160, 25);
		pnlBottom1.add(txtRentStart);	
		
		JButton btnRentStart=new JButton("...");
		btnRentStart.setBounds(250, 90, 70, 25);
		pnlBottom1.add(btnRentStart);	
		
		JLabel lblRentEnd = new JLabel("�ѹ����ش:");
		lblRentEnd.setBounds(20, 120, 100, 25);
		pnlBottom1.add(lblRentEnd);		
		
		JTextField txtRentEnd=new JTextField();
		txtRentEnd.setBounds(90, 120, 160, 25);
		pnlBottom1.add(txtRentEnd);	
		
		JButton btnRentEnd=new JButton("...");
		btnRentEnd.setBounds(250, 120, 70, 25);
		pnlBottom1.add(btnRentEnd);			
		
		//button
		
		JButton btnEdit=new JButton("���");
		btnEdit.setBounds(30, 180, 80, 25);
		pnlBottom1.add(btnEdit);			
		
		JButton btnDelete=new JButton("ź");
		btnDelete.setBounds(130, 180, 80, 25);
		pnlBottom1.add(btnDelete);				
		
		JButton btnAdd=new JButton("����");
		btnAdd.setBounds(230, 180, 80, 25);
		pnlBottom1.add(btnAdd);			
		
		pnlContent.add(pnlBottom1);
		

		JButton btnPrint=new JButton("�����");
		btnPrint.setBounds(1030, 470, 80, 25);
		pnlContent.add(btnPrint);			
		
		JButton btnRentSave=new JButton("�ѹ�֡�����š�����");
		btnRentSave.setBounds(1205, 470, 150, 25);
		pnlContent.add(btnRentSave);
				

	}
}
