package alchemyDproj;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	static JFrame f;

	public static void framePreset() {
		f = new JFrame("Alchemy");
		
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocation(440, 0);
		f.setSize(580,720);
		
		GraphicPanel gp = new GraphicPanel();
		
		f.add(gp);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		Logic.startInitialize();
		framePreset();
	}

}
