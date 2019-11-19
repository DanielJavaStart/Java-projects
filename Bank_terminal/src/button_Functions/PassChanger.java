package button_Functions;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dproj_bt.DB_Queries;
import dproj_bt.UI;

public class PassChanger extends JFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = -8696498095096043638L;
JLabel nP =  new JLabel("new password:");
JLabel nP1 =  new JLabel("confirm password:");

JTextField tF = new JTextField(10);
JTextField tF1 = new JTextField(10);

JButton sub = new JButton("submit");
JButton canc = new JButton("cancel");
Font b1 = new Font("Verdana",15,15);

	public PassChanger() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400,100);
		setResizable(false);
		setTitle("Changing password");
		setLocation(300,300);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		nP.setFont(b1);
		nP1.setFont(b1);
		tF.setFont(b1);
		tF1.setFont(b1);
		tF.setBorder(BorderFactory.createEtchedBorder());
		tF1.setBorder(BorderFactory.createEtchedBorder());
		sub.setFont(b1);
		canc.setFont(b1);
		sub.setBorder(BorderFactory.createRaisedBevelBorder());
		sub.setBackground(Color.green);
		canc.setBorder(BorderFactory.createRaisedBevelBorder());
		canc.setBackground(Color.yellow);
		
		canc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tF.getText().contentEquals("") && !tF1.getText().contentEquals("")) {
					if(tF.getText().equals(tF1.getText())) {
						try {
							if(DB_Queries.UpdatePass(tF.getText())) {
								JOptionPane.showMessageDialog(null, "success!");
								UI.logOut.doClick();
							}
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "error with db: "+e1);
						}finally {
							dispose();
						}
					}else {
						JOptionPane.showMessageDialog(null,"fields not equal");
					}
				}else {
					JOptionPane.showMessageDialog(null,"fill both fields");
				}
			}
		});
		
		add(nP);
		add(tF);
		add(canc);
		add(nP1);
		add(tF1);
		add(sub);
		
		setVisible(true);
	}
}
