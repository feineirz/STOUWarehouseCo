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
		//ค้นหา
		JPanel pnlcolor=new JPanel();
		pnlcolor.setBounds(10,10,10,30);
		pnlcolor.setBackground(Color.ORANGE);
		pnlContent.add(pnlcolor);
		
		JPanel pnlbar=new JPanel();
		pnlbar.setLayout(null);
		pnlbar.setBounds(10,10,1360,30);
		pnlbar.setBackground(Color.GRAY);
		pnlContent.add(pnlbar);
		
		JLabel lblHead=new JLabel("รายชื่อนักศึกษากลุ่มที่ 2");
		lblHead.setBounds(20, 0, 300, 25);
		lblHead.setForeground(Color.ORANGE);
		lblHead.setFont(fontHead);
		pnlbar.add(lblHead);
		/*
		JLabel lblCustName=new JLabel("รายชื่อนักศึกษากลุ่มที่ 2");
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
				{1,"6096009284","จ.อ.","อนุสรณ์	","พิชิตการค้า"},
				{2,"6196001686","นางสาว","ธนพร","ทองดี"},
				{3,"5996011770","นางสาว","พรสุดา","นาเลิง"},
				{4,"6096008419","นางสาว","ภาวินี","มุงคุณคำ"},
				{5,"-","นาย","กิตติ	","ธนะสาร"},
				{6,"5996001318","นาย","คัมภีร์","บุญโพโรจน์"},
				{7,"5496030411","นาย","คาราวาน","พรหมจอม"},
				{8,"6196003492","นาย","คุณากร","ทองวิจิตร"},
				{9,"6096002545","นาย","ชาตรี","อาษานอก"},
				{10,"5896017984","นาย","ตีรภัสร์","นาแล"},
				{11,"6096000721","นาย","ธนากร","ม่วงเดช"},
				{12,"5896002341","นาย","ภูริจักษ์","พลกำจัด"},
				{13,"-","นาย","ภูสิฏฐ์","สุรเบญจพล"},
				{14,"5996004676","นาย","มงคล","พูลสุข"},
				{15,"5796013117","นาย","มารุทร","นุชบ้านป่า"},
				{16,"6096005787","นาย","วันณัฐ","บุญช่วย"},
				{17,"6196001900","นาย","อนุวัฒน์","ป้องที"},
				{18,"6296001149","นาย","อรรถกร","มงคลประเสริฐ"},
				{19,"5896004370","นาย","เทพชัย","ภูนิคม"},
				{20,"6196006560","นาย","เรวัตร","บุญคง"},
				{21,"5996004486","พระ","สุรชัย","ปิ่นวงษ์เพชร"},
				

				
		};
		

		
		String columns[]= {"ที่","รหัสนักศึกษา","คำนำหน้า","ชื่อ","นามสกุล"};
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
