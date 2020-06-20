package APP.Designers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import APP.Controllers.AddCustomer;
import APP.Controllers.CustomerMgr;
import APP.Controllers.MainMenu;
import APP.Controllers.RentalMgr;
import APP.Controllers.UserMgr;


public class RentalMgrDesigner extends DefaultDesigner implements ActionListener{
	public UtilDateModel model, model2;
	public JDatePanelImpl startDatePanel, endDatePanel;
	public static JDatePickerImpl startDatePicker, endDatePicker;
	public static JTextField txtSearchRent;
	public static JLabel lblCustId,lblCustName,lblCustAddr,lblLocId,lblAmount,lblCustPhone,lblCustFax,lblCustEmail;
	public static JLabel lblRentId,lblRentDate,lblStartRentDate,lblEndRentDate,lblRentStatus;
	public static JLabel lblSumTotal,lblVat2,lblDiscount2,lblTotal2,lblTotalsum,lblTotalsum2,lblTotalsum3,lblCancelRent;
	
	public static JButton btnAdd,btnEdit,btnSave,btnRentCancle, btnCancelRent,btnSearchCust,btnDelete;
	public static JTable tableRent,tableRentDetail;
	public static DefaultTableModel tableModel,tableModelDetail;
	protected Font fontHead = new Font("Tahoma", Font.BOLD, 15);
	public static JComboBox<String> cbhowsearch;
	public RentalMgrDesigner() {
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
		model = new UtilDateModel();  
		model2 = new UtilDateModel();  
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		startDatePanel = new JDatePanelImpl(model,p);  
		endDatePanel = new JDatePanelImpl(model2,p);  
		
		JPanel pnlcolor=new JPanel();
		pnlcolor.setBounds(10,10,10,30);
		pnlcolor.setBackground(Color.ORANGE);
		pnlContent.add(pnlcolor);
		
		JPanel pnlbar=new JPanel();
		pnlbar.setLayout(null);
		pnlbar.setBounds(10,10,800,30);
		pnlbar.setBackground(Color.GRAY);
		pnlContent.add(pnlbar);
		
		JLabel lblHead=new JLabel("สัญญาเช่า");
		lblHead.setBounds(20, 0, 300, 25);
		lblHead.setForeground(Color.ORANGE);
		lblHead.setFont(fontHead);
		pnlbar.add(lblHead);
		
		/*
		JLabel lblHead=new JLabel("สัญญาเช่า");
		lblHead.setBounds(10,10,500,25);
		lblHead.setFont(fontHead);
		pnlContent.add(lblHead);
		*/

		//รายละเอียดลูกค้า
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("ข้อมูลลูกค้า"));
		pnlDetail.setBounds(10, 50, 400, 180);
		
		JLabel lbl_CustId = new JLabel("รหัสลูกค้า:");
		lbl_CustId.setBounds(25, 20, 100, 25);
		pnlDetail.add(lbl_CustId);
		
		lblCustId=new JLabel();
		lblCustId.setBounds(125, 20, 260, 25);
		pnlDetail.add(lblCustId);
		
		JLabel lbl_CustName=new JLabel("ชื่อลูกค้า:");
		lbl_CustName.setBounds(25, 45, 100, 25);
		pnlDetail.add(lbl_CustName);
		
		lblCustName=new JLabel();
		lblCustName.setBounds(125, 45, 260, 25);
		pnlDetail.add(lblCustName);
		
		
		JLabel lbl_CustAddr=new JLabel("ที่อยู่ลูกค้า:");
		lbl_CustAddr.setBounds(25, 70, 100, 25);
		pnlDetail.add(lbl_CustAddr);
		
		lblCustAddr=new JLabel();
		lblCustAddr.setBounds(125, 70, 260, 25);
		pnlDetail.add(lblCustAddr);
		
		JLabel lbl_CustPhone=new JLabel("เบอร์โทรศัพท์:");
		lbl_CustPhone.setBounds(25, 95, 100, 25);
		pnlDetail.add(lbl_CustPhone);
		
		lblCustPhone=new JLabel();
		lblCustPhone.setBounds(125, 95, 260, 25);
		pnlDetail.add(lblCustPhone);		
		
		JLabel lbl_CustFax=new JLabel("โทรสาร:");
		lbl_CustFax.setBounds(25, 120, 100, 25);
		pnlDetail.add(lbl_CustFax);
		
		lblCustFax=new JLabel();
		lblCustFax.setBounds(125, 120, 260, 25);
		pnlDetail.add(lblCustFax);	
		
		JLabel lbl_CustEmail=new JLabel("อีเมล (Email):");
		lbl_CustEmail.setBounds(25, 145, 100, 25);
		pnlDetail.add(lbl_CustEmail);
		
		lblCustEmail=new JLabel();
		lblCustEmail.setBounds(125, 145, 260, 25);
		pnlDetail.add(lblCustEmail);	
		pnlContent.add(pnlDetail);
		
		
		//รายละเอียด
		JPanel pnlRenttal = new JPanel();
		pnlRenttal.setLayout(null);
		pnlRenttal.setBorder(BorderFactory.createTitledBorder("ข้อมูลการเช่า"));
		pnlRenttal.setBounds(410, 50, 400, 180);
		
		JLabel lbl_RentId = new JLabel("เลขที่สัญญาเช่า:");
		lbl_RentId.setBounds(25, 20, 100, 25);
		pnlRenttal.add(lbl_RentId);
		
		lblRentId=new JLabel();
		lblRentId.setBounds(125, 20, 260, 25);
		pnlRenttal.add(lblRentId);
		
		JLabel lbl_RentDate=new JLabel("วันที่ทำสัญญา:");
		lbl_RentDate.setBounds(25, 45, 100, 25);
		pnlRenttal.add(lbl_RentDate);
		
		lblRentDate=new JLabel();
		lblRentDate.setBounds(125, 45, 260, 25);
		pnlRenttal.add(lblRentDate);
		
		JLabel lbl_StartRentDate=new JLabel("เริ่มเช่าวันที่:");
		lbl_StartRentDate.setBounds(25, 70, 100, 25);
		pnlRenttal.add(lbl_StartRentDate);
		
		lblStartRentDate=new JLabel();
		lblStartRentDate.setBounds(125, 70, 260, 25);
		pnlRenttal.add(lblStartRentDate);
		
		JLabel lbl_EndRentDate=new JLabel("สิ้นสุดวันที่:");
		lbl_EndRentDate.setBounds(25, 95, 100, 25);
		pnlRenttal.add(lbl_EndRentDate);
		
		lblEndRentDate=new JLabel();
		lblEndRentDate.setBounds(125, 95, 260, 25);
		pnlRenttal.add(lblEndRentDate);		
		
		JLabel lbl_RentStatus=new JLabel("สถานะการเช่า:");
		lbl_RentStatus.setBounds(25, 120, 100, 25);
		pnlRenttal.add(lbl_RentStatus);
		
		lblRentStatus=new JLabel();
		lblRentStatus.setBounds(125, 120, 260, 25);
		pnlRenttal.add(lblRentStatus);	

		pnlContent.add(pnlRenttal);

		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 250, 800, 120);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableRentDetail=new JTable();
		tableRentDetail.setRowHeight(30);
		Object data[][]= {


				
		};
		String columns[]= {"ที่","รหัสตำแหน่ง","จำนวน/วัน","ราคา/หน่วย","รวมราคา"};
		tableModelDetail=new DefaultTableModel(data, columns) {
			/*
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			*/
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0: 
					//return ImageIcon.class;
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return Integer.class;
				case 4:
					return Integer.class;

					
				default:
					return String.class;
				}
			}
		};
		tableRentDetail.setModel(tableModelDetail);

		tableRentDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableRentDetail.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableRentDetail.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableRentDetail.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableRentDetail.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableRentDetail.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		scrollTable.setViewportView(tableRentDetail);
		pnlContent.add(scrollTable);
		
		JLabel lblSum=new JLabel("ยอดรวม:");
		lblSum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSum.setBounds(450,380,120,25);
		pnlContent.add(lblSum);
		
		lblSumTotal=new JLabel("",SwingConstants.RIGHT);
		lblSumTotal.setBounds(620,380,140,25);
		lblSumTotal.setBackground(Color.GRAY);
		lblSumTotal.setForeground(Color.WHITE);
		lblSumTotal.setOpaque(true);
		pnlContent.add(lblSumTotal);
		
		JLabel lblBath=new JLabel("บาท");
		lblBath.setBounds(780,380,100,25);
		pnlContent.add(lblBath);
		
		
		JLabel lblVat=new JLabel("ภาษีมูลค่าเพิ่ม 7%:");
		lblVat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVat.setBounds(450,410,120,25);
		pnlContent.add(lblVat);
		
		lblVat2=new JLabel("",SwingConstants.RIGHT);
		lblVat2.setBounds(620,410,140,25);
		lblVat2.setBackground(Color.GRAY);
		lblVat2.setForeground(Color.WHITE);
		lblVat2.setOpaque(true);
		pnlContent.add(lblVat2);
		
		JLabel lblBath2=new JLabel("บาท");
		lblBath2.setBounds(780,410,100,25);
		pnlContent.add(lblBath2);
		
		JLabel lblDiscount=new JLabel("ส่วนลด:");
		lblDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscount.setBounds(450,440,120,25);
		pnlContent.add(lblDiscount);
		
		lblDiscount2=new JLabel("",SwingConstants.RIGHT);
		lblDiscount2.setBounds(620,440,140,25);
		lblDiscount2.setBackground(Color.GRAY);
		lblDiscount2.setForeground(Color.WHITE);
		lblDiscount2.setOpaque(true);
		pnlContent.add(lblDiscount2);
		
		JLabel lblBath3=new JLabel("บาท");
		lblBath3.setBounds(780,440,100,25);
		pnlContent.add(lblBath3);
		
		JLabel lblTotal=new JLabel("รวมทั้งสิ้น:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(450,470,120,25);
		pnlContent.add(lblTotal);
		
		lblTotal2=new JLabel("",SwingConstants.RIGHT);
		lblTotal2.setBounds(620,470,140,25);
		lblTotal2.setBackground(Color.GRAY);
		lblTotal2.setForeground(Color.YELLOW);
		lblTotal2.setOpaque(true);
		pnlContent.add(lblTotal2);
		
		JLabel lblBath4=new JLabel("บาท");
		lblBath4.setBounds(780,470,100,25);
		pnlContent.add(lblBath4);
		

		cbhowsearch = new JComboBox<String>();
		cbhowsearch.setBounds(820, 10, 110, 23);
		cbhowsearch.addItem("แสดงทั้งหมด");
		cbhowsearch.addItem("หมดสัญญา");
		cbhowsearch.addItem("ยังอยู่ในสัญญา");
		cbhowsearch.addItem("ยกเลิกในสัญญา");
		cbhowsearch.addActionListener(this);
		pnlContent.add(cbhowsearch);
		
		
		txtSearchRent=new JTextField();
		txtSearchRent.setBounds(930, 10, 350, 25);
		txtSearchRent.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent keyevent) {
				cbhowsearch.setSelectedIndex(0);
				RentalMgr.showdata();
			}
		});
		pnlContent.add(txtSearchRent);
		
		btnSearchCust=new JButton("ค้นหา");
		btnSearchCust.setBounds(1300, 10, 80, 25);
		btnSearchCust.addActionListener(this);
		pnlContent.add(btnSearchCust);
		
		//table
		JScrollPane scrollTableR=new JScrollPane();
		scrollTableR.setBounds(820, 45, 560, 400);
		scrollTableR.setPreferredSize(new Dimension(750,300));
		tableRent=new JTable();
		tableRent.setRowHeight(30);
		Object dataR[][]= {
				{null,null,null,null,null}

				
		};
		String columnsR[]= {"หมายเลขสัญญา","ผู้เช่า","วันที่","เวลา","จำนวนวัน","วันคงเหลือ","ราคาสุทธิ"};
		tableModel=new DefaultTableModel(dataR, columnsR) {
			/*
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			*/
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0: 
					//return ImageIcon.class;
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return Integer.class;
				case 5:
					return String.class;
				case 6:
					return Integer.class;
					
				default:
					return String.class;
				}
			}
		};
		tableRent.setModel(tableModel);
		tableRent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new RentalMgr().mouseclick();
			}
		});
		scrollTableR.setViewportView(tableRent);
		tableRent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableRent.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableRent.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableRent.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableRent.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableRent.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableRent.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableRent.getColumnModel().getColumn(6).setPreferredWidth(100);
		pnlContent.add(scrollTableR);
		
		
		btnCancelRent=new JButton("ยกเลิกสัญญา");
		btnCancelRent.setBounds(820,470, 130, 25);
		btnCancelRent.setBackground(Color.RED);
		btnCancelRent.addActionListener(this);
		pnlContent.add(btnCancelRent);	
		
		
		btnDelete=new JButton("ลบ");
		btnDelete.setBounds(1170, 470, 100, 25);
		btnDelete.addActionListener(this);
		pnlContent.add(btnDelete);			
		
		btnEdit=new JButton("แก้ไข");
		btnEdit.setBounds(1280, 470, 100, 25);
		btnEdit.addActionListener(this);
		pnlContent.add(btnEdit);	
					
		lblTotalsum=new JLabel("จำนวนทั้งหมด: ");
		lblTotalsum.setBounds(10, 20, 200, 25);
		pnlFooter.add(lblTotalsum);
		
		lblTotalsum2=new JLabel("จำนวนทั้งหมด: ");
		lblTotalsum2.setBounds(220, 20, 200, 25);
		pnlFooter.add(lblTotalsum2);
		
		lblTotalsum3=new JLabel("จำนวนทั้งหมด: ");
		lblTotalsum3.setBounds(440, 20, 200, 25);
		pnlFooter.add(lblTotalsum3);
		
		lblCancelRent=new JLabel("จำนวนทั้งหมด: ");
		lblCancelRent.setBounds(680, 20, 200, 25);
		pnlFooter.add(lblCancelRent);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnCancelRent) {

			RentalMgr.btnCancelRent();

		}else if(e.getSource()==cbhowsearch) {
			RentalMgr.loadData();
		}else if(e.getSource()==btnSearchCust) {
			RentalMgr.loadData();
		}
	}
	
}

class startDateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}

class endDateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}