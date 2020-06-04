package APP.Designers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import APP.Controllers.CustomerMgr;

@SuppressWarnings("serial")
public class AboutDesigner extends DefaultDesigner implements ActionListener {
	public static JButton btnSearchCust,btnAdd,btnEdit,btnSave;
	public static JTextField txtSearchCust,txtCustId,txtCustName,txtCustPhone,txtCustFax,txtCustEmail;
	public static JTextArea txtCustAddr;
	public static DefaultTableModel tableModelReport;
	public static JLabel lbl_CustName;
	public static JTable tableReport;
	
	public AboutDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		//ค้นหา
		JLabel lblCustName=new JLabel("รายชื่อนักศึกษากลุ่มที่ 2");
		lblCustName.setBounds(20, 10, 300, 25);
		pnlContent.add(lblCustName);
		
		JPanel pnlLeft=new JPanel();
		pnlLeft.setLayout(null);
		pnlLeft.setBounds(20, 50, 550, 450);
		pnlLeft.setBackground(Color.GRAY);
		pnlContent.add(pnlLeft);
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(570, 50, 800, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableReport=new JTable();
		Object data[][]= {
				{null,null,null,null,null},

				
		};
		String columns[]= {"ที่","รหัสนักศึกษา","คำนำหน้า","ชื่อ","นามสกุล"};
		tableModelReport=new DefaultTableModel(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableReport.setModel(tableModelReport);
		/*
		tableReport.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new CustomerMgr().mouseclick();
			}
		});
		*/
		scrollTable.setViewportView(tableReport);
		pnlContent.add(scrollTable);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
