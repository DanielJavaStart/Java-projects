package alchemyDproj;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Searcher extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField tx = new JTextField(15);
	JButton sub = new JButton("submit");
	
	public Searcher(int x,int y) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,100);
		
		tx.setFocusable(true);
		tx.addKeyListener(this);
		
		setTitle("search the element");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setLocation(x,y);
		add(tx);
		
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.f.setFocusable(true);
				if(findElement(tx.getText())) {
					dispose();
					}
			}
		});
		
		add(sub);
		setVisible(true);
	}

	protected boolean findElement(String text) {
		if(text.equals("")) {
			JOptionPane.showMessageDialog(this,"enter the name of element!");
			return false;
		}else {
			Iterator<Element>iter = Logic.OpenedElements.iterator();
			while(iter.hasNext()) {
				Element e = iter.next();
				if(e.getName().contentEquals(text)) {
					Element n = new Element(e.getName(),e.getImageName()); 
					n.x=(250+new Random().nextInt(100)-50);
					n.y=(250+new Random().nextInt(100)-50);
					GraphicPanel.ElementsInUse.add(n);
					return true;
				}
			}
		}
		JOptionPane.showMessageDialog(this,"not found!");
		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	if(e.getKeyCode()==10) {
		if(findElement(tx.getText())) {
			dispose();
			}
	}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
