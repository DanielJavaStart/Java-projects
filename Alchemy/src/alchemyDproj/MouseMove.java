package alchemyDproj;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

public class MouseMove implements MouseListener,MouseMotionListener{
boolean choosedSmth=false;
Element toMove;

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(choosedSmth) {
			if(x>0 && x<580 && y>0 && y<640) {
			toMove.x = x;
			toMove.y = y;
			
			Rectangle r = new Rectangle(toMove.x,toMove.y,75,75);
			Iterator<Element>iter = GraphicPanel.ElementsInUse.iterator();
			 while(iter.hasNext()) {
				 Element el = iter.next();
				Rectangle r1 = new Rectangle(el.x,el.y,75,75);
				
				if(r.intersects(r1) && !toMove.equals(el)) {
					
					if(Logic.mixStepOne(toMove,el)) {
					break;
					}
					}
				
			 }
			}
			
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x>0 && x<75 && y>0 && y<75) {
			Logic.outlineCorrect(1);
		}
		if(x>500 && x<575 && y>0 && y<75) {
			Logic.outlineCorrect(-1);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		addingElement(x,y);
		movingElement(x,y);
		
		if(x>415 && x<490 && y>0 && y<75) {
			findingElement(x,y);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(choosedSmth) {
			choosedSmth = false;
		}
		//GraphicPanel.currElementName  = "";
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void addingElement(int x,int y) {	
	for(int i=0;i<4;i++) {
		if(x>100+i*75 && x<175+i*75 && y>0 && y<75) {
			Element n = new Element(Logic.OpenedElements.get(GraphicPanel.pos+i).getName(),Logic.OpenedElements.get(GraphicPanel.pos+i).getImageName()); 
			n.x=(250+new Random().nextInt(100)-50);
			n.y=(250+new Random().nextInt(100)-50);
			GraphicPanel.ElementsInUse.add(n);
			break;
		}
	}
	}

	public void movingElement(int x,int y) {
		if(!choosedSmth) {
			for(int i=GraphicPanel.ElementsInUse.size()-1;i>=0;i--) {
				Element e = GraphicPanel.ElementsInUse.get(i);
					 
				if(x>=e.x && x<e.x+75 && y>=e.y && y<e.y+75) {
				choosedSmth = true;
				toMove = GraphicPanel.ElementsInUse.get(i);
					break;
				}
				
			}
		}
	}
	
	public void findingElement(int x, int y) {
		Searcher s = new Searcher(x,y);
	}
}
