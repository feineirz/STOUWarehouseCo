package APP.Designers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import APP.Controllers.CustomerMgr;
import APP.Controllers.RentalMgr;
import APP.Controllers.UserMgr;


public class RentalMgrDesigner extends DefaultDesigner implements ActionListener{
	public UtilDateModel model, model2;
	public JDatePanelImpl startDatePanel, endDatePanel;
	public static JDatePickerImpl startDatePicker, endDatePicker;
	public static JTextField txtSearchRent;
	public static JLabel lblCustId,lblCustName,lblCustAddr,lblLocId,lblAmount,lblCustPhone,lblCustFax,lblCustEmail;
	public static JLabel lblRentId,lblRentDate,lblStartRentDate,lblEndRentDate,lblRentStatus;
	public static JLabel lblSumTotal;
	
	public static JButton btnAdd,btnEdit,btnSave,btnRentCancle;
	public static JTable tableRent,tableRentDetail;
	public static DefaultTableModel tableModel,tableModelDetail;
	public RentalMgrDesigner() {
		this.setSize(1400,700);
		reAdjustPanel();
		pnlContent.setLayout(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		model = new UtilDateModel();  
		model2 = new UtilDateModel();  
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		startDatePanel = new JDatePanelImpl(model,p);  
		endDatePanel = new JDatePanelImpl(model2,p);  
			

		//รายละเอียดลูกค้า
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("ข้อมูลลูกค้า"));
		pnlDetail.setBounds(10, 10, 550, 180);
		
		JLabel lbl_CustId = new JLabel("รหัสลูกค้า:");
		lbl_CustId.setBounds(25, 20, 100, 25);
		pnlDetail.add(lbl_CustId);
		
		lblCustId=new JLabel();
		lblCustId.setBounds(125, 20, 260, 25);
		pnlDetail.add(lblCustId);
		
		JLabel lbl_CustName=new JLabel("ชื่อลูกก้า:");
		lbl_CustName.setBounds(25, 45, 100, 25);
		pnlDetail.add(lbl_CustName);
		
		lblCustName=new JLabel();
		lblCustName.setBounds(125, 45, 260, 25);
		pnlDetail.add(lblCustName);
		
		
		JLabel lbl_CustAddr=new JLabel("ที่อยู่ลูกก้า:");
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
		pnlRenttal.setBounds(560, 10, 450, 180);
		
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
		scrollTable.setBounds(10, 200, 1000, 250);
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableRentDetail=new JTable();
		Object data[][]= {


				
		};
		String columns[]= {"เลขที่คลัง","สถานะ","ราคา","หมายเหตุ"/*,"สินสุดวันที่","จำนวนเงิน","สถานะซ่อม"*/};
		tableModelDetail=new DefaultTableModel(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableRentDetail.setModel(tableModelDetail);

		scrollTable.setViewportView(tableRentDetail);
		pnlContent.add(scrollTable);
		
		JLabel lblSum=new JLabel("รวมราคา:");
		lblSum.setBounds(700,470,120,25);
		pnlContent.add(lblSum);
		
		lblSumTotal=new JLabel("",SwingConstants.RIGHT);
		lblSumTotal.setBounds(800,470,140,25);
		lblSumTotal.setBackground(Color.GRAY);
		lblSumTotal.setOpaque(true);
		pnlContent.add(lblSumTotal);
		
		JLabel lblBath=new JLabel("บาท");
		lblBath.setBounds(950,470,120,25);
		pnlContent.add(lblBath);
		

		//ค้นหา
		/*
		JLabel lblRentName=new JLabel("รายการการเช่า");
		lblRentName.setBounds(1030, 10, 100, 25);
		pnlContent.add(lblRentName);
		*/
		/*
		JLabel lblRentId=new JLabel("เลขที่ใบแจ้งหนี้");
		lblRentId.setBounds(710, 10, 100, 25);
		pnlContent.add(lblRentId);
		
		JTextField txtRentId=new JTextField();
		txtRentId.setBounds(810, 10, 200, 25);
		pnlContent.add(txtRentId);
		*/
		
		txtSearchRent=new JTextField();
		txtSearchRent.setBounds(1030, 10, 200, 25);
		txtSearchRent.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent keyevent) {
				new CustomerMgr().showdata();
			}
		});
		pnlContent.add(txtSearchRent);
		
		JButton btnSearchCust=new JButton("ค้นหา");
		btnSearchCust.setBounds(1280, 10, 100, 25);
		pnlContent.add(btnSearchCust);
		
		//table
		JScrollPane scrollTableR=new JScrollPane();
		scrollTableR.setBounds(1030, 45, 350, 400);
		scrollTableR.setPreferredSize(new Dimension(750,300));
		tableRent=new JTable();
		Object dataR[][]= {
				{null,null,null,null,null}

				
		};
		String columnsR[]= {"หมายเลขสัญญา","ผู้เช่า","เริ่มวันที่","สินสุดวันที่","วันคงเหลือ"};
		tableModel=new DefaultTableModel(dataR, columnsR) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableRent.setModel(tableModel);
		tableRent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				new RentalMgr().mouseclick();
			}
		});
		scrollTableR.setViewportView(tableRent);
		pnlContent.add(scrollTableR);
		
		/*
		JPanel pnlDetail = new JPanel();
		pnlDetail.setLayout(null);
		pnlDetail.setBorder(BorderFactory.createTitledBorder("ข้อมูลลูกค้า"));
		pnlDetail.setBounds(1030, 10, 330, 350);
		
		JLabel lblCustId = new JLabel("เลขที่ใบแจ้งหนี้:");
		lblCustId.setBounds(10, 20, 100, 25);
		pnlDetail.add(lblCustId);
		
		txtCustId=new JTextField();
		txtCustId.setBounds(110, 20, 210, 25);
		pnlDetail.add(txtCustId);
		
		JLabel lbl_CustName=new JLabel("ชื่อลูกก้า:");
		lbl_CustName.setBounds(10, 50, 100, 25);
		pnlDetail.add(lbl_CustName);
		
		txtCustName=new JTextField();
		txtCustName.setBounds(110, 50, 210, 25);
		pnlDetail.add(txtCustName);
		
		JLabel lbl_CustAddr=new JLabel("รหัสตำแหน่ง:");
		lbl_CustAddr.setBounds(10, 80, 100, 25);
		pnlDetail.add(lbl_CustAddr);
		
		txtLocId=new JTextField();
		txtLocId.setBounds(110, 80, 210, 25);
		pnlDetail.add(txtLocId);
		
		
		JLabel lblRentStart = new JLabel("วันเริ่มต้น:");
		lblRentStart.setBounds(10, 110, 100, 25);
		pnlDetail.add(lblRentStart);		

		startDatePicker = new JDatePickerImpl(startDatePanel, new startDateLabelFormatter());
		startDatePicker.setBounds(110, 110, 210, 25);
		pnlDetail.add(startDatePicker);	
		
		JLabel lblRentEnd = new JLabel("วันสิ้นสุด:");
		lblRentEnd.setBounds(10, 140, 100, 25);
		pnlDetail.add(lblRentEnd);		
		
		endDatePicker = new JDatePickerImpl(endDatePanel, new endDateLabelFormatter());
		endDatePicker.setBounds(110, 140, 210, 25);
		pnlDetail.add(endDatePicker);	
		
		JLabel lbl_Amount=new JLabel("จำนวนเงิน:");
		lbl_Amount.setBounds(10, 170, 100, 25);
		pnlDetail.add(lbl_Amount);
		
		txtAmount=new JTextField();
		txtAmount.setBounds(110, 170, 210, 25);
		pnlDetail.add(txtAmount);
		*/
		/*
		btnAdd=new JButton("เพิ่ม");
		btnAdd.setBounds(10, 270, 80, 25);
		pnlDetail.add(btnAdd);			

		
		btnEdit=new JButton("แก้ไข");
		btnEdit.setBounds(120, 270, 80, 25);
		pnlDetail.add(btnEdit);			
		
		btnSave=new JButton("บันทึก");
		btnSave.setBounds(230, 270, 80, 25);
		pnlDetail.add(btnSave);				
		
		btnRentCancle=new JButton("ยกเลิกการเช่า");
		btnRentCancle.setBounds(10, 310, 120, 25);
		btnRentCancle.setBackground(Color.RED);
		pnlDetail.add(btnRentCancle);	
		
		pnlContent.add(pnlDetail);
		*/
		//รายละเอียด
		/*
		JPanel pnlBottom1 = new JPanel();
		pnlBottom1.setLayout(null);
		pnlBottom1.setBorder(BorderFactory.createTitledBorder(""));
		pnlBottom1.setBounds(1030, 370, 330, 130);
		
		pnlContent.add(pnlBottom1);
		*/


				

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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