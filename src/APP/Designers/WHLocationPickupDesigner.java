package APP.Designers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import APP.Controllers.AddCustomer;
import APP.Controllers.AddRental;
import APP.Controllers.MainMenu;
import APP.Controllers.WHLocationPickup;
import APP.Controllers.WarehouseMgr;
import APP.Designers.AddRentalDesigner;

public class WHLocationPickupDesigner extends JDialog implements ActionListener{
	public static String selected;
	public static JButton btnSelect;
	
	public static String selectedLoc;
	public static JLabel[] lbl=new JLabel[168];
	static int a =0;
	public static int locSelected;
	static int lastSelectedLoc;
	public static Color lastBgColor;
	public static Color currentBgColor;
	public static boolean pickupAdd=false;
	public static boolean pickupDel=false;
	
	public WHLocationPickupDesigner() {
		setSize(830, 530);
		setTitle("เลือกคลังสินค้า");
		this.setLocationRelativeTo(null);
		Container c =getContentPane();
		//c.setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setLayout(null);
		
		Font font = new Font("Tahoma", Font.PLAIN, 10);
		Border bdr = BorderFactory.createLineBorder(Color.black);
		Border border = BorderFactory.createLineBorder(Color.GREEN, 5);
		Dimension btnSize = new Dimension(35,35);

		String[] rowID1 = {"A","B","","C","D","","E","F"};
		String slotID = "";
		
		for(int i = 1; i < 9; i++){
			for(int j = 1; j <= 12; j++) {
				if(i != 3 && i != 6) {
					slotID = rowID1[i-1] + String.format("%02d", j);
					
					//System.out.println(a);
					int b=a;
					lbl[a] = new JLabel(slotID,SwingConstants.CENTER);
					lbl[a].setBorder(bdr);
					lbl[a].setFont(font);
					lbl[a].setOpaque(true);
					lbl[a].setBackground(Color.WHITE);
					lbl[a].setBounds(i*btnSize.width, j*btnSize.height, btnSize.width, btnSize.height);
					lbl[a].addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {

							selectedLoc=lbl[b].getText();
							locSelected=b;

							currentBgColor=lbl[b].getBackground();
							if(lbl[b].getBackground()==Color.WHITE) {
								pickupAdd=true;
								pickupDel=false;
								WHLocationPickup.mouseclick();
								lbl[b].setBackground(Color.GREEN);

								//AddRental.list.add(lbl[b].getText());
							}else if(lbl[b].getBackground()==Color.GREEN){
								pickupAdd=false;
								pickupDel=true;
								WHLocationPickup.mouseclick();
								lbl[b].setBackground(Color.WHITE);
								//AddRental.list.remove(lbl[b].getText());

							}else if(lbl[b].getBackground()==Color.RED){
								pickupAdd=false;
								pickupDel=false;
								JOptionPane.showMessageDialog(null, "ชองนี้ถูกเช่าแล้ว", "แจ้งเตือน",JOptionPane.ERROR_MESSAGE);
								lbl[b].setBackground(Color.RED);


							}else if(lbl[b].getBackground()==Color.YELLOW){
								pickupAdd=false;
								pickupDel=false;
								JOptionPane.showMessageDialog(null, "ชองนี้กำลังอยู่ระหว่างซ่อมบำรุง", "แจ้งเตือน",JOptionPane.ERROR_MESSAGE);
								lbl[b].setBackground(Color.YELLOW);


							}
							//System.out.println("=>"+AddRental.list);
							
							lastSelectedLoc=b;
							lastBgColor=currentBgColor;



							
						}
					});
					pnlContainer.add(lbl[a]);
					a++;
				}
			}
		}
		
		
		
		String[] rowID2 = {"G","H","","I","J","","K","L","","M","N"};
		for(int i = 1; i < 12; i++) {
			for(int j = 10; j < 22; j++) {
				
				if(i != 3 && i != 6 && i != 9) {
					int b=a;
					
					slotID = rowID2[i-1] + String.format("%02d", j - 9);
					
					//System.out.println(a);
					
					lbl[a] = new JLabel(slotID,SwingConstants.CENTER);
					
					lbl[a].setBorder(bdr);
					lbl[a].setFont(font);
					lbl[a].setOpaque(true);
					lbl[a].setBackground(Color.WHITE);
					lbl[a].setBounds(j*btnSize.width, i*btnSize.height, btnSize.width, btnSize.height);
					lbl[a].addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {

							selectedLoc=lbl[b].getText();
							locSelected=b;

							currentBgColor=lbl[b].getBackground();
							if(lbl[b].getBackground()==Color.WHITE) {
								pickupAdd=true;
								pickupDel=false;
								WHLocationPickup.mouseclick();
								lbl[b].setBackground(Color.GREEN);

								//AddRental.list.add(lbl[b].getText());
							}else if(lbl[b].getBackground()==Color.GREEN){
								pickupAdd=false;
								pickupDel=true;
								WHLocationPickup.mouseclick();
								lbl[b].setBackground(Color.WHITE);
								//AddRental.list.remove(lbl[b].getText());

							}else if(lbl[b].getBackground()==Color.RED){
								pickupAdd=false;
								pickupDel=false;
								JOptionPane.showMessageDialog(null, "ชองนี้ถูกเช่าแล้ว", "แจ้งเตือน",JOptionPane.ERROR_MESSAGE);
								lbl[b].setBackground(Color.RED);


							}else if(lbl[b].getBackground()==Color.YELLOW){
								pickupAdd=false;
								pickupDel=false;
								JOptionPane.showMessageDialog(null, "ชองนี้กำลังอยู่ระหว่างซ่อมบำรุง", "แจ้งเตือน",JOptionPane.ERROR_MESSAGE);
								lbl[b].setBackground(Color.YELLOW);


							}


							lastSelectedLoc=b;
							lastBgColor=currentBgColor;



							
						}
					});
					
					pnlContainer.add(lbl[a]);
					
					a++;
				}    
				
				
			}
		}
		

		JLabel lblStatusFree = new JLabel();
		lblStatusFree.setBounds(350, 440, 20, 20);
		lblStatusFree.setBorder(bdr);
		lblStatusFree.setBackground(Color.WHITE);
		lblStatusFree.setOpaque(true);
		pnlContainer.add(lblStatusFree);
		
		JLabel lbl_Free = new JLabel("ว่าง");
		lbl_Free.setBounds(370, 440, 100, 20);
		pnlContainer.add(lbl_Free);
		
		JLabel lblNotFree = new JLabel();
		lblNotFree.setBounds(410, 440, 20, 20);
		lblNotFree.setBorder(bdr);
		lblNotFree.setBackground(Color.RED);
		lblNotFree.setOpaque(true);
		pnlContainer.add(lblNotFree);
		
		JLabel lbl_NotFree = new JLabel("ไม่ว่าง");
		lbl_NotFree.setBounds(430, 440, 100, 20);
		pnlContainer.add(lbl_NotFree);
		
		JLabel lbl_Broken = new JLabel();
		lbl_Broken.setBounds(470, 440, 20, 20);
		lbl_Broken.setBorder(bdr);
		lbl_Broken.setBackground(Color.YELLOW);
		lbl_Broken.setOpaque(true);
		pnlContainer.add(lbl_Broken);
		
		JLabel lblBroken = new JLabel("ซ่อมบำรุง");
		lblBroken.setBounds(490, 440, 100, 20);
		pnlContainer.add(lblBroken);
		
		JLabel lbl_Rented = new JLabel();
		lbl_Rented.setBounds(550, 440, 20, 20);
		lbl_Rented.setBorder(bdr);
		lbl_Rented.setBackground(Color.GREEN);
		lbl_Rented.setOpaque(true);
		pnlContainer.add(lbl_Rented);
		
		JLabel lblRented = new JLabel("เลือก");
		lblRented.setBounds(570, 440, 100, 20);
		pnlContainer.add(lblRented);
		
		btnSelect=new JButton("เลือกตำแหน่งที่ระบุ");
		btnSelect.setBounds(620,440, 150, 25);
		btnSelect.addActionListener(this);
		pnlContainer.add(btnSelect);

		
		c.add(pnlContainer);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSelect) {
			AddRental.clickWH();
			this.setVisible(false);
			//new AddRental().clickWH(selected);

			//new WHLocationPickup().selected(selected);

		}	// TODO Auto-generated method stub
		
	}

}
