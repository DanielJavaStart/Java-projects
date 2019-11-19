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

public class DeleteUser extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5530446916025299190L;
	JLabel l = new JLabel("login:");
	JTextField uName = new JTextField(10);
 	JButton sub = new JButton("delete");
 	JButton canc = new JButton("cancel");
 	Font b1 = new Font("Verdana",15,15);

	public DeleteUser() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350,80);
		setTitle("deleting user");
		setLocation(300,200);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		l.setFont(b1);
		sub.setFont(b1);
		canc.setFont(b1);
		sub.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		canc.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		uName.setBorder(BorderFactory.createBevelBorder(1, Color.red, Color.blue));
		uName.setFont(b1);
		sub.setBackground(Color.red);
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
				if(!uName.getText().equals("")) {
					try {
						if(DB_Queries.DeleteUser(uName.getText())) {
							JOptionPane.showMessageDialog(null, "success");
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "something went wrong");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		add(l);
		add(uName);
		add(sub);
		add(canc);
		setVisible(true);
	}
}
