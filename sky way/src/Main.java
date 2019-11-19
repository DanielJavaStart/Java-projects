
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Main extends JFrame{
static ImageIcon Blogo = new ImageIcon("pictures/Blogo.png");
static ImageIcon B1logo = new ImageIcon("pictures/B1logo.png");
static ImageIcon B2logo = new ImageIcon("pictures/B2logo.png");
static ImageIcon logo = new ImageIcon("pictures/logo.png");
static float version = 1.0f;  

static String[]LvLTask = new String[]{"You should rich 2 km ","You should shoot 20 clouds ","You should rich 3,5 km ",
	"you should rich 2 km and beat the boss 1","you should rich 5km","you should shoot 35 clouds","you should rich 6,5 km","you should rich 4km and beat the boss"};
static String[]levels= new String[]{"level 1","level 2","level 3","level 4(boss)","level 5","level 6","level 7","level 8(boss)"};
static int[] passedLevels = new int[levels.length];
static JComboBox<String> LevelChoose = new JComboBox<String>(levels);
	public static void main(String[] args) {
		
		
		JFrame f = new JFrame();
		JButton B = new JButton("  START  ");
		JButton B1 = new JButton("  HighScore  ");
		
		JLabel LDesc = new JLabel("Level Description:  ");
		
		LevelChoose.setSelectedIndex(-1);
		
		
		JLabel l = new JLabel(logo);
		JLabel l1 = new JLabel(Blogo);
		JLabel l2 = new JLabel(B1logo);
		
		
		B.add(l1);
		B1.add(l2);
		
		
		f.setLocation(300, 200);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		f.add(l);
		f.add(B);
		f.add(B1);
		f.add(LDesc);
		f.add(LevelChoose);
		f.setResizable(false);
		f.setSize(500, 500);
		f.setVisible(true);
		B.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			if(LevelChoose.getSelectedIndex()==0 || LevelChoose.getSelectedIndex()>=0 && passedLevels[LevelChoose.getSelectedIndex()-1]==1 ){
			 
				JFrame f1 = new JFrame();
			if(LevelChoose.getSelectedIndex()>3){
				game.bg = game.bg1;
			}
			f1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			System.gc();
			f1.setLocation(200, 0);
			f1.setSize(800, 550);	
			f1.setResizable(false);
			f1.add(new game());
			
			game.end = false;
			game.life = 5;
			game.metres = 0;
			game.bossFight = false;
			bossClass.bossHP=10;
			game.cloudShooted=0;
			
			f1.setVisible(true);
				}else{
					LDesc.setText("choose any level");
				}
			}
	         });
		
		B1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			String uu="";
			JFrame f2 = new JFrame();
			/*try {
				FileReader fr = new FileReader("record.txt");
				int check;
				
				while((check = fr.read())!=-1){
					uu +=(char)check;
				}
				fr.close();
				}catch(IOException ex){
					
				}*/
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
		LevelChoose.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileReader fr = new FileReader("LPass");
					
					for(int i=0;i<passedLevels.length;i++){
						passedLevels[i]=Integer.parseInt((char)fr.read()+"");
						
					}
					
					fr.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=0;i<passedLevels.length;i++){
					if(LevelChoose.getSelectedIndex()==i){
						
						if(passedLevels[i]==0){
							LDesc.setText(LvLTask[i]+":"+"closed");
							}
						if(i>=1){
						if(passedLevels[i-1]==1){
							LDesc.setText(LvLTask[i]+":"+"opened");
							}
						}
						if(i==0){
								LDesc.setText(LvLTask[i]+":"+"opened");
							}
						break;
					}
				}
			}
			
		});
		
	}

}

