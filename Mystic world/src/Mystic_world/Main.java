package Mystic_world;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class Main extends JFrame{
static ImageIcon Blogo = new ImageIcon("Blogo.png");
static ImageIcon B1logo = new ImageIcon("B1logo.png");
static ImageIcon B2logo = new ImageIcon("B2logo.png");
static ImageIcon logo = new ImageIcon("logo.png");
static float version = 1.0f;    
	public static void main(String[] args) {
		
		
		JFrame f = new JFrame();
		JButton B = new JButton("  START  ");
		JButton B1 = new JButton("  HighScore  ");
		JButton B2 = new JButton("  Rules  ");
		
		JLabel l = new JLabel(logo);
		JLabel l1 = new JLabel(Blogo);
		JLabel l2 = new JLabel(B1logo);
		JLabel l3 = new JLabel(B2logo);
		
		B.add(l1);
		B1.add(l2);
		B2.add(l3);
		
		f.setLocation(300, 200);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		f.add(l);
		f.add(B);
		f.add(B1);
		f.add(B2);
		
		
		f.setSize(500, 500);
		f.setVisible(true);
		B.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			
			JFrame f1 = new JFrame();
			
			f1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f1.setLocation(200, 0);
			f1.setSize(900, 645);	
			f1.add(new matrix());
			f1.setVisible(true);
				}
	         });
		
		B1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			String uu="";
			JFrame f2 = new JFrame();
			try {
				FileReader fr = new FileReader("record.txt");
				int check;
				
				while((check = fr.read())!=-1){
					uu +=(char)check;
				}
				fr.close();
				}catch(IOException ex){
					
				}
			Font Fo = new Font("Arial",5,30);
			JLabel lbl = new JLabel(uu);
			lbl.setFont(Fo);
			f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f2.setSize(100, 100);	
			f2.add(lbl,BorderLayout.CENTER);
			f2.setLocation(500, 300);
			f2.setVisible(true);
				}
	         });
		
		B2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			String uu="1. Your Aim - to destroy monolith; \n 2. You can move on LEFT,RIGHT,UP,DOWN;\n"
					+ "3.You can kill/destroy on SPACE, power shot - R;\n 4. If you have a few HP and have 300 gold and 30 wood press B;\n"
					+ "5. Its my second full game, be condescending :)\n Author: ShDS Version 1.0";
			JFrame f2 = new JFrame();
			
			Font Fo = new Font("Arial",5,20);
			JTextPane lbl = new JTextPane();
			lbl.setText(uu);
			lbl.setFont(Fo);
			f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f2.setSize(400, 400);	
			f2.add(lbl);
			f2.setLocation(500, 300);
			f2.setVisible(true);
				}
	         });
		
	}

}
