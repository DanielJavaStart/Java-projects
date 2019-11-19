package button_Functions;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dproj_bt.DB_Queries;

public class TransferMon extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6194167788825620419L;
	JLabel l1= new JLabel("username:");
	JLabel l2 = new JLabel("money to send:");
	
	JTextField uName = new JTextField(10);
	JTextField uMon = new JTextField(10);
	
 	JButton sub = new JButton("send");
 	JButton canc = new JButton("cancel");
 	Font b1 = new Font("Verdana",15,15);
 	
	public TransferMon() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(200,200);
		setTitle("money transfer");
		setResizable(false);
		setLocation(300,200);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
	 	sub.setBackground(Color.blue);
	 	sub.setForeground(Color.yellow);
	 	l1.setFont(b1);
	 	l2.setFont(b1);
	 	uName.setFont(b1);
	 	uMon.setFont(b1);
	 	sub.setFont(b1);
	 	canc.setFont(b1);
	 	
	canc.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	});
	sub.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!uName.getText().equals("")) {
				try {
					if(DB_Queries.transferMoney(uName.getText(),Integer.parseInt(uMon.getText()))) {
						JOptionPane.showMessageDialog(null, "success");
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "user not found or you havenot got so much $");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error in sql");
				} catch(InputMismatchException | NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "incorrect input");
				}
			}
			
		}
	});
	
	add(l1);
	add(uName);
	add(l2);
	add(uMon);
	add(sub);
	add(canc);
	setVisible(true);
	}
}
