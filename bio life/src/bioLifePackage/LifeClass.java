package bioLifePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class LifeClass extends JPanel implements MouseListener, ActionListener{
	
	
	
    Timer T = new Timer(60,this);
    static ArrayList<Vegetative>list = new ArrayList<Vegetative>();
    static ArrayList<MeatEaters>list1 = new ArrayList<MeatEaters>();
    static List<food> foodList = new ArrayList<food>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LifeClass(){
		T.start();
		addMouseListener(this);
	}
	
	
	
	public void paint(Graphics g){
		Font B = new Font("Arial",20,20);
		g.setFont(B);
		
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 1100, 600);
		Iterator<food>iter0 = foodList.iterator();
		while(iter0.hasNext()){
			food curr = iter0.next();
			g.setColor(Color.blue);
			g.fillOval(curr.x, curr.y,20+curr.points/2, 20+curr.points/2);
		}
		
		Iterator<Vegetative>iter = list.iterator();
		while(iter.hasNext()){
			Vegetative curr = iter.next();
			g.setColor(Color.green);
			g.fillOval(curr.x, curr.y,20+curr.points/2, 20+curr.points/2);
			/*g.drawString(curr.c+","+curr.c1, curr.x-5, curr.y-10);
			g.drawString(curr.x+","+curr.y, curr.x-5, curr.y+40);*/
		}
		
		Iterator<MeatEaters>iter1 = list1.iterator();
		while(iter1.hasNext()){
			MeatEaters curr = iter1.next();
			g.setColor(Color.red);
			g.fillOval(curr.x, curr.y,20+curr.points/2, 20+curr.points/2);
			/*g.drawString(curr.c+","+curr.c1, curr.x-5, curr.y-10);
			g.drawString(curr.x+","+curr.y, curr.x-5, curr.y+40);*/
		}
		g.setColor(Color.black);
		g.drawString("ЛКМ - зеленые", 10, 30);
		g.drawString("ПКМ - красные", 10, 50);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){
		list.add(new Vegetative(true,e.getX(),e.getY()));
		}
		if(SwingUtilities.isRightMouseButton(e)){
			list1.add(new MeatEaters(e.getX(),e.getY(),new Random().nextInt(10000)));
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
int b=0;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	b++;
	if(b>=50){
		foodList.add(new food(new Random().nextInt(450)+450,new Random().nextInt(200)+50,new Random().nextInt(90000)));
		list.add(new Vegetative(true,new Random().nextInt(450)+100,new Random().nextInt(200)+50));
		list1.add(new MeatEaters(new Random().nextInt(600)+200,new Random().nextInt(200)+200,new Random().nextInt(90000)));
		foodList.add(new food(new Random().nextInt(1100),new Random().nextInt(550)+50,new Random().nextInt(90000)));
		list.add(new Vegetative(true,new Random().nextInt(1000)+100,new Random().nextInt(500)+50));
		list1.add(new MeatEaters(new Random().nextInt(900)+200,new Random().nextInt(200)+200,new Random().nextInt(90000)));
		b=0;
	}
		
		Iterator<MeatEaters>iter1 = list1.iterator();
		while(iter1.hasNext()){
			MeatEaters curr1 = iter1.next();
			curr1.move();
			if(curr1.runTime==30){
				curr1.newChangeWay();
				curr1.runTime=0;
			}
		}
		Iterator<Vegetative>iter = list.iterator();
		while(iter.hasNext()){
			Vegetative curr = iter.next();
			curr.move();
			curr.findFood();
			if(curr.runTime==30){
				curr.changeWay();
				curr.targeted = false;
				curr.runTime=0;
			}
		}
		Iterator<food>iter0 = foodList.iterator();
		while(iter0.hasNext()){
			food curr = iter0.next();
			curr.move();
			curr.findFood();
			if(curr.runTime==30){
				curr.changeWay();
				curr.targeted = false;
				curr.runTime=0;
			}
		}
		
		repaint();
	}

}
