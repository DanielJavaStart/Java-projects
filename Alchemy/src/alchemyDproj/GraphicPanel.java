package alchemyDproj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GraphicPanel extends JPanel implements ActionListener {
	
Image leftButt = new ImageIcon("pict/buttLeft.png").getImage();
Image rightButt = new ImageIcon("pict/buttRight.png").getImage();
Image garbage = new ImageIcon("pict/garbage.png").getImage();
Image search = new ImageIcon("pict/loop.png").getImage();
Image bg = new ImageIcon("pict/bg.png").getImage();

static List<Element>ElementsInUse = new LinkedList<Element>();
static boolean creatingNew=false;
Timer t = new Timer(50,this);
MouseMove mv = new MouseMove();
static String currMessage=" ";
static int pos=0;

	public GraphicPanel() {
		setLayout(null);		
		setFocusable(true);
		addMouseListener(mv);
		addMouseMotionListener(mv);
		t.start();	
		
		
	}

	public void paint(Graphics g) {
		g.drawImage(bg,0,0,null);
		g.drawImage(leftButt,0,0,null);
		g.drawImage(search,415,0,null);
		g.drawImage(rightButt,490,0,null);
		
		int xEl=100,yEl=0;
		for(int y=pos;y<pos+4 && y<Logic.OpenedElements.size();y++){
			g.drawImage(Logic.OpenedElements.get(y).getImage(),xEl,yEl,null);
			g.drawString(Logic.OpenedElements.get(y).getName(), xEl+20, yEl+90);
			xEl+=80;
		}
		
		for(int i=0;i<ElementsInUse.size();i++) {
			g.drawImage(ElementsInUse.get(i).getImage(),ElementsInUse.get(i).x,ElementsInUse.get(i).y,null);
			g.drawString(ElementsInUse.get(i).getName(),ElementsInUse.get(i).x+20,ElementsInUse.get(i).y+90);
		}
		Font b = new Font("Algerian",25,25);
		g.setFont(b);
		g.drawString(currMessage,100,630);
		
		g.drawImage(garbage, 0,600,null);
		g.drawString(ElementsInUse.size()+"", 500, 630);
		
		if(creatingNew) {
			g.setColor(Color.CYAN);
			g.drawLine(90, 150, 500, 520);
			g.drawLine(500, 150, 90, 520);
			g.setColor(Color.BLACK);
		}
	}
	
	int counter=0,counter1=0;
	
	@Override
	public void actionPerformed(ActionEvent e) {
	repaint();
	
	
	if(currMessage!="") {
		creatingNew=true;
	}
	if(counter>=10) {
	Logic.garbageCheck();
	counter=0;
	}
	if(counter1>=30) {
		currMessage="";
		creatingNew = false;
		counter1=0;
		}
	counter++;
	counter1++;
	}
}
