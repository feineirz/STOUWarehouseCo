package APP.Designers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import APP.Controllers.WHLocationPickup;


public class AddRentalDesigner extends DefaultDesigner implements ActionListener{
	public static JButton btnCustAdd,btnSelect,btnEdit,btnDelete,btnReset,btnPrint,btnRentSave;
	public static JTextField txtRentId,txtLocId,txtRentStart,txtRentEnd;
	public static JComboBox cbCustName;
	public static DefaultTableModel tableModel;
	public static JTable tableCust;
	public static UtilDateModel model, model2;
	public static JDatePanelImpl startDatePanel, endDatePanel;
	public static JDatePickerImpl startDatePicker, endDatePicker;
	public static JLabel lblSumTotal;
	
	public AddRentalDesigner() {
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
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
		
		//ค้นหา
		JLabel lblRentName=new JLabel("รายการการเช่าคลังสินค้า");
		lblRentName.setBounds(10, 10, 200, 25);
		pnlContent.add(lblRentName);
		
		JLabel lblRentId=new JLabel("เลขที่ใบแจ้งหนี้");
		lblRentId.setBounds(710, 10, 100, 25);
		pnlContent.add(lblRentId);
		
		txtRentId=new JTextField();
		txtRentId.setBounds(810, 10, 200, 25);
		pnlContent.add(txtRentId);
		
		//table
		JScrollPane scrollTable=new JScrollPane();
		scrollTable.setBounds(10, 50, 1000, 350);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableCust=new JTable();
		Object data[][]= {

				
		};
		String columns[]= {"รหัสตำแหน่ง","สถานะ","จำนวนเงิน"};
		tableModel=new DefaultTableModel(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCust.setModel(tableModel);
		tableCust.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new AddRental().mouseclick();
			}
		});

		scrollTable.setViewportView(tableCust);
		pnlContent.add(scrollTable);
		
		JLabel lblSum=new JLabel("รวมราคา:");
		lblSum.setBounds(700,420,120,25);
		pnlContent.add(lblSum);
		
		lblSumTotal=new JLabel("",SwingConstants.RIGHT);
		lblSumTotal.setBounds(800,420,140,25);
		lblSumTotal.setBackground(Color.GRAY);
		lblSumTotal.setOpaque(true);
		pnlContent.add(lblSumTotal);
		
		JLabel lblBath=new JLabel("บาท");
		lblBath.setBounds(950,420,120,25);
		pnlContent.add(lblBath);
		
		//รายละเอียด
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("ข้อมูลลูกค้า"));
		pnlDetail.setBounds(1030, 10, 330, 200);
		
		cbCustName = new JComboBox<String>();
		cbCustName.setBounds(10, 20, 230, 23);
		cbCustName.addItem("กรุษาเลือกชื่อลูกค้า");

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
		pnlBottom1.add(startDatePicker);	
		
		JLabel lblRentEnd = new JLabel("วันสิ้นสุด:");
		lblRentEnd.setBounds(10, 120, 100, 25);
		pnlBottom1.add(lblRentEnd);		
		
		endDatePicker = new JDatePickerImpl(endDatePanel, new endDateLabelFormatter2());
		endDatePicker.setBounds(90, 120, 230, 25);
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
		
		btnRentSave=new JButton("บันทึกข้อมูลการเช่า");
		btnRentSave.setBounds(1205, 470, 150, 25);
		btnRentSave.addActionListener(this);
		pnlContent.add(btnRentSave);
				

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCustAdd) {

			new AddCustomer().getaddcustomer();

		}else if(e.getSource()==btnSelect) {
			new WHLocationPickup().getWHLocationPickup();
			
		}else if(e.getSource()==btnEdit) {
			//new UserMgr().clickbtnedit();
			
		}else if(e.getSource()==btnDelete) {
			//new UserMgr().clickbtnsave();
			new AddRental().btnDelete();
			
		}else if(e.getSource()==btnReset) {
			new AddRental().btnReset();

			
		}else if(e.getSource()==btnPrint) {
			//new MainMenu().btnManegUser();
			
		}else if(e.getSource()==btnRentSave) {
			new AddRental().clickbtnsave();
			

			
		}
		
	}
}

class startDateLabelFormatter1 extends AbstractFormatter {

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

class endDateLabelFormatter2 extends AbstractFormatter {

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