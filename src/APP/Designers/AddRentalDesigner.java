package APP.Designers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import APP.Controllers.AddCustomer;
import APP.Controllers.AddRental;
import APP.Controllers.CustomerMgr;
import APP.Controllers.MainMenu;
import APP.Controllers.UserMgr;
import APP.Controllers.WHLocationPickup;
import APP.Controllers.WarehouseMgr;


public class AddRentalDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnCustAdd,btnSelect,btnEdit,btnDelete,btnReset,btnPrint,btnRentSave;
	public static JTextField txtRentId,txtLocId,txtRentStart,txtRentEnd,txtDiscount2;
	public static JComboBox<String> cbCustName;
	public static DefaultTableModel tableModel;
	public static JTable tableCust;
	public static UtilDateModel model, model2;
	public static JDatePanelImpl startDatePanel, endDatePanel;
	public static JDatePickerImpl startDatePicker, endDatePicker;
	public static JLabel lblSumTotal,lblVat2,lblTotal2;
	protected Font fontHead = new Font("Tahoma", Font.BOLD, 15);
	public AddRentalDesigner() {
		
		model = new UtilDateModel();  
		model2 = new UtilDateModel();  
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		startDatePanel = new JDatePanelImpl(model,p);  
		endDatePanel = new JDatePanelImpl(model2,p);  
		
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
		pnlbar.setBounds(10,10,1000,30);
		pnlbar.setBackground(Color.GRAY);
		pnlContent.add(pnlbar);
		
		JLabel lblHead=new JLabel("รายการการเช่าคลังสินค้า");
		lblHead.setBounds(20, 0, 300, 25);
		lblHead.setForeground(Color.ORANGE);
		lblHead.setFont(fontHead);
		pnlbar.add(lblHead);
		/*
		JLabel lblRentName=new JLabel("รายการการเช่าคลังสินค้า");
		lblRentName.setBounds(10, 10, 200, 25);
		pnlContent.add(lblRentName);
		*/
		JLabel lblRentId=new JLabel("เลขที่สัญญาเช่า");
		lblRentId.setBounds(710, 2, 100, 25);
		lblRentId.setForeground(Color.WHITE);
		pnlbar.add(lblRentId);
		
		txtRentId=new JTextField();
		txtRentId.setBounds(810, 2, 185, 25);
		txtRentId.setEditable(false);
		pnlbar.add(txtRentId);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 320);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableCust=new JTable();
		tableCust.setRowHeight(30);
		Object data[][]= {

				
		};
		String columns[]= {"ที่","รหัสตำแหน่ง","จำนวน/วัน","ราคา/หน่วย","รวมราคา"};
		tableModel=new DefaultTableModel(data, columns) {
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
		tableCust.setModel(tableModel);
		
		tableCust.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new AddRental().mouseclick();
			}
		});
		
		tableCust.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCust.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableCust.getColumnModel().getColumn(1).setPreferredWidth(350);
		tableCust.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableCust.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableCust.getColumnModel().getColumn(4).setPreferredWidth(250);

		
		scrollTable.setViewportView(tableCust);
		pnlContent.add(scrollTable);
		
		JLabel lblSum=new JLabel("ยอดรวม:");
		lblSum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSum.setBounds(660,380,120,25);
		pnlContent.add(lblSum);
		
		lblSumTotal=new JLabel("",SwingConstants.RIGHT);
		lblSumTotal.setBounds(800,380,140,25);
		lblSumTotal.setBackground(Color.GRAY);
		lblSumTotal.setForeground(Color.WHITE);
		lblSumTotal.setOpaque(true);
		pnlContent.add(lblSumTotal);
		
		JLabel lblBath=new JLabel("บาท");
		lblBath.setBounds(970,380,100,25);
		pnlContent.add(lblBath);
		
		
		JLabel lblVat=new JLabel("ภาษีมูลค่าเพิ่ม 7%:");
		lblVat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVat.setBounds(660,410,120,25);
		pnlContent.add(lblVat);
		
		lblVat2=new JLabel("",SwingConstants.RIGHT);
		lblVat2.setBounds(800,410,140,25);
		lblVat2.setBackground(Color.GRAY);
		lblVat2.setForeground(Color.WHITE);
		lblVat2.setOpaque(true);
		pnlContent.add(lblVat2);
		
		JLabel lblBath2=new JLabel("บาท");
		lblBath2.setBounds(970,410,100,25);
		pnlContent.add(lblBath2);
		
		JLabel lblDiscount=new JLabel("ส่วนลด:");
		lblDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscount.setBounds(660,440,120,25);
		pnlContent.add(lblDiscount);
		
		txtDiscount2=new JTextField("");
		txtDiscount2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDiscount2.setBounds(800,440,140,25);
		txtDiscount2.setBackground(Color.GRAY);
		txtDiscount2.setForeground(Color.WHITE);
		txtDiscount2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent keyevent) {
				new AddRental().getsumtotal();
			}
		});
		txtDiscount2.setOpaque(true);
		pnlContent.add(txtDiscount2);
		
		JLabel lblBath3=new JLabel("บาท");
		lblBath3.setBounds(970,440,100,25);
		pnlContent.add(lblBath3);
		
		JLabel lblTotal=new JLabel("รวมทั้งสิ้น:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(660,470,120,25);
		pnlContent.add(lblTotal);
		
		lblTotal2=new JLabel("",SwingConstants.RIGHT);
		lblTotal2.setBounds(800,470,140,25);
		lblTotal2.setBackground(Color.GRAY);
		lblTotal2.setForeground(Color.YELLOW);
		lblTotal2.setOpaque(true);
		pnlContent.add(lblTotal2);
		
		JLabel lblBath4=new JLabel("บาท");
		lblBath4.setBounds(970,470,100,25);
		pnlContent.add(lblBath4);
		
		//รายละเอียด
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("ข้อมูลลูกค้า"));
		pnlDetail.setBounds(1030, 10, 330, 200);
		
		cbCustName = new JComboBox<String>();
		cbCustName.setBounds(10, 20, 230, 23);
		cbCustName.addItem("กรุณาเลือกชื่อลูกค้า");

		pnlDetail.add(cbCustName);
		
		btnCustAdd=new JButton("เพิ่ม");
		btnCustAdd.setBounds(250, 20, 70, 25);
		btnCustAdd.addActionListener(this);
		pnlDetail.add(btnCustAdd);			
		

		
		pnlContent.add(pnlDetail);
		
		//ตำแหน่ง
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder("ตำแหน่ง"));
		pnlBottom1.setBounds(1030, 220, 330, 220);
		
		JLabel lblLocId = new JLabel("รหัสตำแหน่ง:");
		lblLocId.setBounds(10, 20, 100, 25);
		pnlBottom1.add(lblLocId);
		
		txtLocId=new JTextField();
		txtLocId.setBounds(90, 20, 160, 25);
		pnlBottom1.add(txtLocId);
		
		btnSelect=new JButton("เลือก");
		btnSelect.setBounds(250, 20, 70, 25);
		btnSelect.addActionListener(this);
		pnlBottom1.add(btnSelect);	
		
		
		JLabel lblRentTime = new JLabel("ระยะเวลาเช่า");
		lblRentTime.setBounds(10, 60, 100, 25);
		pnlBottom1.add(lblRentTime);
		
		JLabel lblRentStart = new JLabel("วันเริ่มต้น:");
		lblRentStart.setBounds(10, 90, 100, 25);
		pnlBottom1.add(lblRentStart);		
		
		startDatePicker = new JDatePickerImpl(startDatePanel, new startDateLabelFormatter1());
		startDatePicker.setBounds(90, 90, 230, 25);
		startDatePicker.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	AddRental.totalDate();
		    }
		});
		pnlBottom1.add(startDatePicker);	
		
		JLabel lblRentEnd = new JLabel("วันสิ้นสุด:");
		lblRentEnd.setBounds(10, 120, 100, 25);
		pnlBottom1.add(lblRentEnd);		
		
		endDatePicker = new JDatePickerImpl(endDatePanel, new endDateLabelFormatter2());
		endDatePicker.setBounds(90, 120, 230, 25);
		endDatePicker.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	AddRental.totalDate();
		    }
		});
		pnlBottom1.add(endDatePicker);			
		
		//button
		
		btnEdit=new JButton("แก้ไข");
		btnEdit.setBounds(30, 180, 80, 25);
		btnEdit.addActionListener(this);
		pnlBottom1.add(btnEdit);			
		
		btnDelete=new JButton("ลบ");
		btnDelete.setBounds(130, 180, 80, 25);
		btnDelete.addActionListener(this);
		pnlBottom1.add(btnDelete);				
		
		btnReset=new JButton("เคลียร์");
		btnReset.setBounds(230, 180, 80, 25);
		btnReset.addActionListener(this);
		pnlBottom1.add(btnReset);			
		
		pnlContent.add(pnlBottom1);
		

		btnPrint=new JButton("พิมพ์");
		btnPrint.setBounds(1030, 470, 80, 25);
		btnPrint.addActionListener(this);
		pnlContent.add(btnPrint);			
		
		btnRentSave=new JButton("บันทึกข้อมูล");
		btnRentSave.setBounds(1205, 470, 150, 25);
		btnRentSave.addActionListener(this);
		pnlContent.add(btnRentSave);
				

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCustAdd) {

			AddCustomer.getaddcustomer();

		}else if(e.getSource()==btnSelect) {
			WHLocationPickup.getWHLocationPickup();
			
		}else if(e.getSource()==btnEdit) {
			WHLocationPickup.getWHLocationPickup();
			
		}else if(e.getSource()==btnDelete) {
			//new UserMgr().clickbtnsave();
			AddRental.btnDelete();
			
		}else if(e.getSource()==btnReset) {
			AddRental.btnReset();

			
		}else if(e.getSource()==btnPrint) {
			//new MainMenu().btnManegUser();
			
		}else if(e.getSource()==btnRentSave) {
			AddRental.clickbtnsave();

		}
		
	}
}

class startDateLabelFormatter1 extends AbstractFormatter {

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

class endDateLabelFormatter2 extends AbstractFormatter {
	
	
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