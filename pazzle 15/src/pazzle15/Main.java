package pazzle15;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame{

	public static void main(String[] args) {
		JFrame f = new JFrame("Pazzle15");
		
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setSize(415, 555);
		f.setResizable(false);
		
		
		f.add(new Game());
		
		f.setVisible(true);
	}
}
