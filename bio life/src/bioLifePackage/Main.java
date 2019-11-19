package bioLifePackage;

import javax.swing.JFrame;

public class Main extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4700253093557836034L;

	public static void main(String[] args) {
		JFrame f = new JFrame("bio life");
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setSize(1100, 600);
		f.add(new LifeClass());
		f.setVisible(true);
		f.setResizable(false);
	}

}
