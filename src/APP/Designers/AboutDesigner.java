package APP.Designers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import APP.Controllers.CustomerMgr;
import APP.Controllers.MainMenu;

@SuppressWarnings("serial")
public class AboutDesigner extends DefaultDesigner implements ActionListener {
	public static JButton btnSearchCust,btnAdd,btnEdit,btnSave;
	public static JTextField txtSearchCust,txtCustId,txtCustName,txtCustPhone,txtCustFax,txtCustEmail;
	public static JTextArea txtCustAddr;
	public static DefaultTableModel tableModelReport;
	public static JLabel lbl_CustName;
	public static JTable tableReport;
	protected Font fontHead = new Font("Tahoma", Font.BOLD, 15);
	
	public AboutDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainMenu.getmain();
			}
		});
		//����
		JPanel pnlcolor=new JPanel();
		pnlcolor.setBounds(10,10,10,30);
		pnlcolor.setBackground(Color.ORANGE);
		pnlContent.add(pnlcolor);
		
		JPanel pnlbar=new JPanel();
		pnlbar.setLayout(null);
		pnlbar.setBounds(10,10,1360,30);
		pnlbar.setBackground(Color.GRAY);
		pnlContent.add(pnlbar);
		
		JLabel lblHead=new JLabel("��ª��͹ѡ�֡�ҡ������� 2");
		lblHead.setBounds(20, 0, 300, 25);
		lblHead.setForeground(Color.ORANGE);
		lblHead.setFont(fontHead);
		pnlbar.add(lblHead);
		/*
		JLabel lblCustName=new JLabel("��ª��͹ѡ�֡�ҡ������� 2");
		lblCustName.setBounds(20, 10, 300, 25);
		pnlContent.add(lblCustName);
		*/
		JPanel pnlLeft=new JPanel();
		pnlLeft.setLayout(null);
		pnlLeft.setBounds(20, 50, 550, 450);
		pnlLeft.setBackground(Color.GRAY);
		
		ImageIcon loginIcon = new ImageIcon("assets/dash1.jpg");
        JLabel loginLogo = new JLabel();
        loginLogo.setBounds(0,0,550,450);
        loginLogo.setIcon(loginIcon);
        pnlLeft.add(loginLogo);
        
		pnlContent.add(pnlLeft);
		

        
        
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(570, 50, 800, 450);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableReport=new JTable();
		tableReport.setRowHeight(50);
 
	            		 
		ImageIcon imgLogIcon = new ImageIcon("assets/iconfinder_Personal_98961.png");
		Object data[][]= {
				{1,"6096009284","�.�.","͹��ó�	","�ԪԵ��ä��"},
				{2,"6196001686","�ҧ���","����","�ͧ��"},
				{3,"5996011770","�ҧ���","���ش�","����ԧ"},
				{4,"6096008419","�ҧ���","���Թ�","�ا�س��"},
				{5,"-","���","�Ե��	","������"},
				{6,"5996001318","���","�������","�ح��è��"},
				{7,"5496030411","���","�����ҹ","�������"},
				{8,"6196003492","���","�سҡ�","�ͧ�ԨԵ�"},
				{9,"6096002545","���","�ҵ��","���ҹ͡"},
				{10,"5896017984","���","��������","����"},
				{11,"6096000721","���","��ҡ�","��ǧപ"},
				{12,"5896002341","���","���Ԩѡ��","�šӨѴ"},
				{13,"-","���","���ԯ��","���ອ���"},
				{14,"5996004676","���","����","����آ"},
				{15,"5796013117","���","���ط�","�ت��ҹ���"},
				{16,"6096005787","���","�ѹ�Ѱ","�ح����"},
				{17,"6196001900","���","͹��Ѳ��","��ͧ��"},
				{18,"6296001149","���","��ö��","���Ż�����԰"},
				{19,"5896004370","���","෾���","�ٹԤ�"},
				{20,"6196006560","���","���ѵ�","�ح��"},
				{21,"5996004486","���","��ê��","���ǧ��ྪ�"},
				

				
		};
		

		
		String columns[]= {"���","���ʹѡ�֡��","�ӹ�˹��","����","���ʡ��"};
		tableModelReport=new DefaultTableModel(data, columns) {
			/*
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			*/
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					//return ImageIcon.class;
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
	
				default:
					return String.class;
				}
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
