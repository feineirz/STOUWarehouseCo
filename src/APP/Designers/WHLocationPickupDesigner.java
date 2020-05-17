package APP.Designers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class WHLocationPickupDesigner extends JFrame implements ActionListener{

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
		Dimension btnSize = new Dimension(35,35);

		String[] rowID1 = {"A","B","","C","D","","E","F"};
		String slotID = "";

		for(int i = 1; i < 9; i++){
			for(int j = 1; j <= 12; j++) {
				if(i != 3 && i != 6) {
					slotID = rowID1[i-1] + String.format("%02d", j);
					JLabel lbl = new JLabel(slotID,SwingConstants.CENTER);
					lbl.setBorder(bdr);
					lbl.setFont(font);
					lbl.setOpaque(true);
					lbl.setBackground(Color.WHITE);
					lbl.setBounds(i*btnSize.width, j*btnSize.height, btnSize.width, btnSize.height);
					lbl.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							System.out.println("=>");
							if(lbl.getBackground()==Color.WHITE) {
								lbl.setBackground(Color.GREEN);
							}else {
								lbl.setBackground(Color.WHITE);
							}
						}
					});
					pnlContainer.add(lbl);
				}
			}
		}

		String[] rowID2 = {"G","H","","I","J","","K","L","","M","N"};
		for(int i = 10; i < 22; i++) {
			for(int j = 1; j < 12; j++) {
				if(j != 3 && j != 6 && j != 9) {
					slotID = rowID2[j-1] + String.format("%02d", i - 9);
					JLabel lbl = new JLabel(slotID,SwingConstants.CENTER);
					lbl.setBorder(bdr);
					lbl.setFont(font);
					lbl.setOpaque(true);
					lbl.setBackground(Color.WHITE);
					lbl.setBounds(i*btnSize.width, j*btnSize.height, btnSize.width, btnSize.height);
					lbl.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							System.out.println("=>");
							if(lbl.getBackground()==Color.WHITE) {
								lbl.setBackground(Color.GREEN);
							}else {
								lbl.setBackground(Color.WHITE);
							}
						}
					});
					pnlContainer.add(lbl);
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
		
		JButton btnSelect=new JButton("เลือกตำแหน่งที่ระบุ");
		btnSelect.setBounds(620,440, 150, 25);
		pnlContainer.add(btnSelect);

		
		c.add(pnlContainer);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
