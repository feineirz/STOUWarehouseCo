package APP.Designers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
import APP.Controllers.ReportGen;
import APP.Controllers.WHLocationPickup;

import java.awt.*;

public class ReportGenDesigner extends DefaultDesigner implements ActionListener {
	public static JButton btnShowReport,btnPrint,btnReset;
	public static JTextField txtSearchCust,txtCustId,txtCustName,txtCustPhone,txtCustFax,txtCustEmail;
	public static JTextArea txtCustAddr;
	public static DefaultTableModel tableModelReport;
	public static JLabel lbl_CustName,lblTotalsum,lblTotalsum2,lblTotalsum3,lblCancelRent;
	public static JTable tableReport;
	
	public static UtilDateModel beginModel, toModel;
	public static JDatePanelImpl beginDatePanel, toDatePanel;
	public static JDatePickerImpl beginDatePicker, toDatePicker;
	public static JLabel lblSumTotal;
	protected Font fontHead = new Font("Tahoma", Font.BOLD, 15);
	public ReportGenDesigner() {
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
		beginModel = new UtilDateModel();  
		toModel = new UtilDateModel();  
		
		Properties r = new Properties();
		r.put("text.today", "Today");
		r.put("text.month", "Month");
		r.put("text.year", "Year");
		
		beginDatePanel = new JDatePanelImpl(beginModel,r);  
		toDatePanel = new JDatePanelImpl(toModel,r);  
		
		//ค้นหา
		JPanel pnlcolor=new JPanel();
		pnlcolor.setBounds(10,10,10,30);
		pnlcolor.setBackground(Color.ORANGE);
		pnlContent.add(pnlcolor);
		
		JPanel pnlbar=new JPanel();
		pnlbar.setLayout(null);
		pnlbar.setBounds(10,10,1000,30);
		pnlbar.setBackground(Color.GRAY);
		pnlContent.add(pnlbar);
		
		JLabel lblHead=new JLabel("รายงานการเช่าคลังสินค้า");
		lblHead.setBounds(20, 0, 300, 25);
		lblHead.setForeground(Color.ORANGE);
		lblHead.setFont(fontHead);
		pnlbar.add(lblHead);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 380);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableReport=new JTable();
		tableReport.setRowHeight(30);
		Object data[][]= {
				
		};
		String columns[]= {"เลขที่สัญญา","ลูกค้า","เริ่มวันที่","สินสุดวันที่","เวลา","เจ้าหน้าที่","วันคงเหลือ","จำนวนเงิน"};
		tableModelReport=new DefaultTableModel(data, columns) {
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
					return String.class;
				case 5:
					return String.class;
				case 6:
					return String.class;	
				case 7:
					return Integer.class;	
				default:
					return String.class;
				}
			}
		};
		tableReport.setModel(tableModelReport);
		tableReport.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableReport.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableReport.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableReport.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableReport.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableReport.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableReport.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableReport.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableReport.getColumnModel().getColumn(7).setPreferredWidth(150);
		
		/*
		tableReport.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new CustomerMgr().mouseclick();
			}
		});
		*/
		scrollTable.setViewportView(tableReport);
		pnlContent.add(scrollTable);
		
		JLabel lblSum=new JLabel("รายรับทั้งหมด:");
		lblSum.setBounds(700,470,120,25);
		pnlContent.add(lblSum);
		
		lblSumTotal=new JLabel("",SwingConstants.RIGHT);
		lblSumTotal.setBounds(800,470,140,25);
		lblSumTotal.setBackground(Color.GRAY);
		lblSumTotal.setOpaque(true);
		lblSumTotal.setForeground(Color.ORANGE);
		lblSumTotal.setFont(fontHead);
		pnlContent.add(lblSumTotal);
		
		JLabel lblBath=new JLabel("บาท");
		lblBath.setBounds(950,470,120,25);
		pnlContent.add(lblBath);
		
		//รายละเอียด
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("รายละเอียด"));
		pnlDetail.setBounds(1030, 10, 330, 350);
		
		
		JLabel lblRentStart = new JLabel("เริ่มวันที่:");
		lblRentStart.setBounds(10, 20, 100, 25);
		pnlDetail.add(lblRentStart);		
		
		beginDatePicker = new JDatePickerImpl(beginDatePanel, new beginDateLabelFormatter());
		beginDatePicker.setBounds(90, 20, 230, 25);
		pnlDetail.add(beginDatePicker);	
		
		JLabel lblRentEnd = new JLabel("ถึงวันที่:");
		lblRentEnd.setBounds(10, 50, 100, 25);
		pnlDetail.add(lblRentEnd);		
		
		toDatePicker = new JDatePickerImpl(toDatePanel, new toDateLabelFormatter());
		toDatePicker.setBounds(90, 50, 230, 25);
		pnlDetail.add(toDatePicker);	
		
		btnShowReport=new JButton("แสดงรายงาน");
		btnShowReport.setBounds(90, 100, 150, 25);
		btnShowReport.addActionListener(this);
		pnlDetail.add(btnShowReport);			
		
		btnPrint=new JButton("พิมพ์");
		btnPrint.setBounds(250, 100, 70, 25);
		btnPrint.addActionListener(this);
		pnlDetail.add(btnPrint);				
		
		btnReset=new JButton("แสดงทั้งหมด");
		btnReset.setBounds(90, 130, 150, 25);
		btnReset.addActionListener(this);
		pnlDetail.add(btnReset);	
		pnlContent.add(pnlDetail);
		
		//รายละเอียด
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder(""));
		pnlBottom1.setBounds(1030, 370, 330, 130);
		
		
		pnlContent.add(pnlBottom1);
		
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
		if(e.getSource()==btnShowReport) {

			new ReportGen().btnShowReport();

		}else if(e.getSource()==btnPrint) {
			//new ReportGen().btnPrint();
			
		}else if(e.getSource()==btnReset) {
			new ReportGen().btnReset();
		}
		
	}

}

class beginDateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, new Locale("en", "EN"));
    //private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));

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

class toDateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, new Locale("en", "EN"));

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

