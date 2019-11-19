package button_Functions;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dproj_bt.DB_Queries;

public class AddUser extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -704163710887471834L;
	JLabel jl = new JLabel("log:");
	JLabel jl1 = new JLabel("pass:");
	JLabel jl2 = new JLabel("money:");
	
	JTextField uLog = new JTextField(10);
	JTextField uPass = new JTextField(10);
	JRadioButton isAd = new JRadioButton("admin");
	JTextField uMon = new JTextField(5);
	
 	JButton sub = new JButton("add");
 	JButton canc = new JButton("cancel");
 	
 	Font b1 = new Font("Verdana",15,15);
 	
	public AddUser() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500,110);
		setTitle("adding user");
		setLocation(300,200);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jl.setFont(b1);
		jl1.setFont(b1);
		jl2.setFont(b1);
		uLog.setFont(b1);
		uPass.setFont(b1);
		uMon.setFont(b1);
		isAd.setFont(b1);
		sub.setFont(b1);
		canc.setFont(b1);
		sub.setBorder(BorderFactory.createRaisedBevelBorder());
		canc.setBorder(BorderFactory.createRaisedBevelBorder());
		canc.setBackground(Color.yellow);
		
		uLog.setBorder(BorderFactory.createEtchedBorder());
		uPass.setBorder(BorderFactory.createEtchedBorder());
		uMon.setBorder(BorderFactory.createEtchedBorder());
		
		sub.setBackground(Color.green);
		
		canc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!uLog.getText().equals("") && !uPass.getText().equals("") && !uMon.getText().equals("")) {
					try {
						int money = Integer.parseInt(uMon.getText());
						if(DB_Queries.Registrate(uLog.getText(),uPass.getText(),isAd.isSelected(),money)) {
							JOptionPane.showMessageDialog(null, "success");
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "something went wrong");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "something went wrong with SQL");
					}catch(NumberFormatException | InputMismatchException e2 ) {
						JOptionPane.showMessageDialog(null, "incorrect input in fields");
					}
				}else {
					JOptionPane.showMessageDialog(null, "please fill all fields");
				}
				
			}
		});
		add(jl);
		add(uLog);
		add(jl1);
		add(uPass);
		add(isAd);
		add(jl2);
		add(uMon);
		add(sub);
		add(canc);
		setVisible(true);
	}
}
