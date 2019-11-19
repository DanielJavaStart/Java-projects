package dproj_bt;

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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import button_Functions.AddUser;
import button_Functions.DeleteUser;
import button_Functions.PassChanger;
import button_Functions.TransferMon;

public class UI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5562361306577819775L;
	private static String login;
	private static String password;
	
	static Font B = new Font("MICRA",15,15);
	static Font b = new Font("MICRA",10,10);
	
	static JLabel currLog = new JLabel("");
	static JLabel currPas = new JLabel("");
	static JLabel currMon = new JLabel("");
	public static JButton logOut = new JButton("Re-Login");
	static JButton chPass= new JButton("change password");
	static JButton MoneyTransfer = new JButton("send money");
	//administrator's items
	static JTextArea textA = new JTextArea(14,25);
	static JScrollPane bx = new JScrollPane(textA);
	static JButton getAll = new JButton("get users");
	static JButton DelSmbd= new JButton("delete user");
	static JButton addSmbd= new JButton("add user");
	//
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	
public UI() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	setSize(500,500);
	setLocation(400,200);
	setLayout(new FlowLayout(FlowLayout.CENTER));
	
	
	chPass.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(Main_Terminal.InSystem) {
				PassChanger pc = new PassChanger();	
			}
		}
	});
	
	logOut.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main_Terminal.InSystem=false;
			 currLog.setText("Please login into the system!");
			 currPas.setText("");
			 currMon.setText("");
			 textA.setVisible(false);
			 getAll.setVisible(false);
			 DelSmbd.setVisible(false);
			 addSmbd.setVisible(false);
			 textA.setText("");
			LogInUI();
		}
	});
	
	getAll.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			textA.setText(DB_Queries.getListUsers());
		}
	});
	
	DelSmbd.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		DeleteUser du = new DeleteUser();	
			
		}
	});
	addSmbd.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			AddUser au = new AddUser();
			
		}
	});
	MoneyTransfer.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			TransferMon tm = new TransferMon();
			
		}
	});
	
	currLog.setFont(B);
	currPas.setFont(B);
	currMon.setFont(B);
	
	currLog.setBorder(BorderFactory.createBevelBorder(1));
	currLog.setForeground(Color.blue);
	currPas.setBorder(BorderFactory.createBevelBorder(1));
	currPas.setForeground(Color.green);
	currMon.setBorder(BorderFactory.createBevelBorder(1));
	currMon.setForeground(Color.red);
	
	logOut.setFont(B);
	chPass.setFont(B);
	DelSmbd.setFont(b);
	addSmbd.setFont(b);
	getAll.setFont(b);
	MoneyTransfer.setFont(b);
	
	add(currLog);
	add(currPas);
	add(currMon);
	
	add(logOut);
	add(chPass);
	
	add(bx);
	add(getAll);
	add(DelSmbd);
	add(addSmbd);
	add(MoneyTransfer);
	
	textA.setFont(new Font("Arial",20,20));
	textA.setVisible(false);
	textA.setBackground(Color.gray);
	textA.setEditable(false);
	textA.setForeground(Color.cyan);
	bx.setVisible(false);
	getAll.setVisible(false);
	DelSmbd.setVisible(false);
	DelSmbd.setBackground(Color.red);
	addSmbd.setBackground(Color.green);
	addSmbd.setVisible(false);
	
	setVisible(false);
	LogInUI();
}

public static void LogInUI() {
	JFrame f = new JFrame("Login");
	JTextField log = new JTextField(10);
	JTextField pass = new JTextField(10);
	JRadioButton reg = new JRadioButton("Register");
	
	Font b1 = new Font("Verdana",15,15);
	
	JLabel l = new JLabel("Login");
	JLabel p = new JLabel("Pass");
	
	JButton sub = new JButton("Submit");
	sub.setBackground(Color.green);
	sub.setBorder(BorderFactory.createEtchedBorder());
	
	log.setBackground(Color.gray);
	pass.setBackground(Color.gray);
	log.setForeground(Color.yellow);
	pass.setForeground(Color.yellow);
	log.setBorder(BorderFactory.createSoftBevelBorder(1));
	pass.setBorder(BorderFactory.createSoftBevelBorder(1));
	log.setFont(b1);
	pass.setFont(b1);
	reg.setFont(b1);
	l.setFont(b1);
	p.setFont(b1);
	sub.setFont(b1);
	
	sub.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 if(log.getText().equals("") || pass.getText().equals("")) {
				 JOptionPane.showMessageDialog(f,"Input login and password");
			 }else {
				 
				 if(reg.isSelected()) {
					 login = log.getText();
					 password = pass.getText();
					 
					 try {
						 if(DB_Queries.Registrate(login, password,false,-1)) {
							 JOptionPane.showMessageDialog(f,"Succesfully registered!");
							 Main_Terminal.InSystem=true;
							 currLog.setText("Hello, "+Main_Terminal.currLogin);
							 currPas.setText("password: "+Main_Terminal.currPass);
							 currMon.setText("Your Money: "+Main_Terminal.currMoney+"");
							 Main_Terminal.main_ui.setVisible(true);
							 f.dispose();
						 }else {
							 JOptionPane.showMessageDialog(f,"This user already exist");
						 }
					 }catch(SQLException ex) {
						 ex.printStackTrace();
					 }
					 }
					 else {
				 login = log.getText();
				 password = pass.getText();
				 try {
					if(DB_Queries.checkLogin(login, password)) {
						 Main_Terminal.InSystem=true;
						 currLog.setText("Hello, "+Main_Terminal.currLogin);
						 currPas.setText("password: "+Main_Terminal.currPass);
						 currMon.setText("Your Money: "+Main_Terminal.currMoney+"");
						 Main_Terminal.main_ui.setVisible(true);
						 if(Main_Terminal.IsAdmin) {
								textA.setVisible(true);
								bx.setVisible(true);
								getAll.setVisible(true);
								DelSmbd.setVisible(true);
								addSmbd.setVisible(true);
							}
						 f.dispose();
					 }else {
						 JOptionPane.showMessageDialog(f,"Incorrect login or password");
					 }
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				 }
			 }
		}
	});
	f.setLocation(300, 300);
	f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	f.setSize(450,100);
	f.setResizable(false);
	f.setLocation(400,300);
	f.setLayout(new FlowLayout(FlowLayout.CENTER));
	f.add(l);
	f.add(log);
	f.add(p);
	f.add(pass);
	f.add(sub);
	f.add(reg);
	
	f.setVisible(true);
}
}