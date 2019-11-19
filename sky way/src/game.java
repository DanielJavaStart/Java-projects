import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class game extends JPanel implements ActionListener, Runnable{
	Timer T = new Timer(20,this);
	Thread CloudThread = new Thread(this);
	boolean isShot = false;
	static int life = 5;
	Main m;
	static boolean bossFight = false;
	static double metres=0;
	Image Plife = new ImageIcon("pictures/life.png").getImage();
	Image blue = new ImageIcon("pictures/blue.png").getImage();
	Image red = new ImageIcon("pictures/red.png").getImage();
	static Image bg0= new ImageIcon("pictures/bg.png").getImage();
	static Image bg1= new ImageIcon("pictures/bg1.png").getImage();
	static Image bg= bg0;
	Image bullet= new ImageIcon("pictures/bullet.png").getImage();
	Image cloud= new ImageIcon("pictures/cloud.png").getImage();
	int x=0;
	static int y=200;
	int x1=550;
	int y1=200;
	int a=-2;
	int n=0,n1 = 0;
	static int cloudShooted=0;
	bossClass BC;
	static boolean end=false;
	int z = 0,z1=0;
	
	List<cloudClass>ClList = new ArrayList<cloudClass>();
    public game(){
    	CloudThread.start();
    	T.start();
    	addKeyListener(new MKA());
      
    	setFocusable(true);
    }
    public Rectangle getRect(){
    	return new Rectangle(0, y,100, 50 );
    }
    public boolean checkLevelTask(int a){
    	boolean answ= false;
    	if(a==0){
    		if(metres>=2000){
    			answ = true;
    		}
    	}
    	if(a==1){
    		if(cloudShooted>=20){
    			answ = true;
    		}
    	}
    	if(a==2){
    		if(metres>=3500){
    			answ = true;
    		}
    	}
    	if(a==3){
    		if( metres>=2000 && !bossFight){
        		bossFight = true;
        		
        	}
    		if(bossClass.bossHP<=0){
    			answ = true;
    		}
    	}
    	if(a==4){
    		if(metres>=5000){
    			answ = true;
    		}
    	}
    	if(a==5){
    		if(cloudShooted>=35){
    			answ = true;
    		}
    	}
    	if(a==6){
    		if(metres>=6500){
    			answ = true;
    		}
    	}
    	if(a==7){
    		if( metres>=4000 && !bossFight){
        		bossFight = true;	
        	}
    		if(bossClass.bossHP<=0){
    			answ = true;
    		}
    	}
		return answ;
    }
   
	private class MKA extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_W ){
				if(y>15)y-=15;
				if(!bossFight)if(a>-50)a-=3;
			}
			if(key == KeyEvent.VK_S){
				if(y<325)y+=15;
				if(!bossFight)if(a>-50)a-=3;
			}
			
			if(key == KeyEvent.VK_D && !bossFight){
				if(a>-50)a-=3;
			}
			if(key == KeyEvent.VK_A && !bossFight){
				if(a<-1)a+=2;
			}
			if(key == KeyEvent.VK_SPACE){
				if(!isShot){
					z = 70;
					z1 = y+25;
					isShot=true;
					n=0;
				}
				
			}
		}
		public void keyReleased(KeyEvent e){
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_D){
				if(a<-40){
					a+=30;
				}else if(a<-15){
					a+=10;
				}else{
					a+=1;
				}
			}	
		}
	}
	private void testCollisionWithEnemies() {
		Iterator<cloudClass> i = ClList.iterator();
		while(i.hasNext()){
			cloudClass e =i.next();
			
			if(getRect().intersects(e.getRect())){
				i.remove();
				life--;
				
			}
		}
		
	}
	public void paint(Graphics g){
		x = a+x;
		if(a<-1)metres = metres+1;
		g.drawImage(bg, x,1,null);
		g.drawImage(bg, x+740, 1,null);
		if(x<=-740)x=0;
		g.drawImage(blue,50,y,null);
		int cf=0,cf1=25;
		if(bossFight){
			for(int i=0;i<bossClass.bossHP;i++){
				g.drawImage(bossClass.blife, bossClass.x-100+cf, bossClass.y-cf1, null);
				cf+=25;
				
			}
			if(Main.LevelChoose.getSelectedIndex()>3){
				bossClass.boss = bossClass.boss2;
			}
			g.drawImage(bossClass.boss,bossClass.x, bossClass.y,null);
		}
		
		if(isShot){
			g.drawImage(bullet, z+100+n, z1,null);
			n+=15;
			if(!bossFight){
			Iterator<cloudClass> i = ClList.iterator();
			while(i.hasNext()){
				cloudClass e =i.next();
				Rectangle bRect = new Rectangle(z+100+n, z1,20, 10 );
				if(bRect.intersects(e.getRect())){
					i.remove();
					cloudShooted++;
					isShot=false;
				}
			}
			}
			if(bossFight){
				if(z+100+n>bossClass.x &&z+100+n<bossClass.x+150 && z1>bossClass.y &&y+25< bossClass.y+100){
					bossClass.bossHP--;
					
					isShot = false;
				}
			}
			if(z+100+n>=700){
				n=0;
				isShot=false;
				z=0;
			}
		}
		
		if(bossClass.shoot){
			if( Main.LevelChoose.getSelectedIndex()>3){
				g.drawImage(bullet, bossClass.z-100-n1, bossClass.z1-23,null);
				g.drawImage(bullet, bossClass.z-100-n1, bossClass.z1+23,null);
			}else{
				g.drawImage(bullet, bossClass.z-100-n1, bossClass.z1,null);
			}
			n1+=15;
			
			if(bossClass.z-100-n1<=0){
				n1=0;
				bossClass.shoot=false;
			}
		if(Main.LevelChoose.getSelectedIndex()<3){
			if(bossClass.z-100-n1<150 &&bossClass.z-100-n1>	50 && bossClass.z1>y && bossClass.z1<y+50){
				life--;
				bossClass.shoot=false;
				bossClass.z=0;
				bossClass.z1=0;
				n1=0;
			}
		}else{
			if(bossClass.z-100-n1<150 &&bossClass.z-100-n1>	50)
				if(bossClass.z1-23>y && bossClass.z1-23<y+50 || bossClass.z1+23>y && bossClass.z1+23<y+50){
				life--;
				bossClass.shoot=false;
				bossClass.z=0;
				bossClass.z1=0;
				n1=0;
			}
		}
		}
		
		if(!bossFight){
		Iterator<cloudClass> i = ClList.iterator();
		while(i.hasNext()){
			cloudClass e1 = i.next();
			if(e1.x<=-400){
				i.remove();
				}else{
						g.drawImage(cloud,e1.x,e1.y,null);
						e1.move();
			
			}
		}}
		Font B = new Font("Arial",2,30);
		g.setFont(B);
		
		int lx=10,ly=460;
		g.setColor(Color.white);
		g.fillRect(0, 460, 800, 45);
		
		g.setColor(Color.black);
		for(int j=0;j<life;j++){
			g.drawImage(Plife, lx,ly,null);
			lx+=40;
		}
		g.drawString(metres/1000+" km", 220, 490);
		if(end){
			if(life<=0){
				g.drawString("Level Failed", 500, 500);
				//end = false;
			}else{
				g.drawString("Level passed", 500, 500);
				//end=false;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!end){
			if(bossFight){
				bossClass.fight();
			}
		repaint();
		//System.out.print(Main.LevelChoose.getSelectedIndex()+" ");
		if(checkLevelTask(Main.LevelChoose.getSelectedIndex())){
			try {
				FileWriter fr = new FileWriter("LPass");
				int y4 = Main.LevelChoose.getSelectedIndex();
				Main.passedLevels[y4]=1;
				for(int i=0;i<Main.passedLevels.length;i++){
					fr.write(Main.passedLevels[i]+"");
				}
				fr.flush();
				fr.close();
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
		end = true;
		}
		testCollisionWithEnemies();
		if(life<=0){
			end = true;
		}
		}
	}
	@Override
	public void run() {
		while(true){
			
			if(!bossFight){
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				if(ClList.size()<6){ClList.add(new cloudClass(900,rand.nextInt(300),this));}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}
	}

}
