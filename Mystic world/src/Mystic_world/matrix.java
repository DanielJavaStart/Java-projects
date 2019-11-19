package Mystic_world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class matrix extends JPanel implements ActionListener, Runnable{
int[][]WorldMatrix = new int[50][50];
Thread Enemy = new Thread(this);
Timer t =new Timer(10,this);
int PlayerX=30,PlayerY=30,gold=0,wood=0, level=1;
int z=0,z1=0,z2=0,z3=0,xMini=0,yMini=0,h=0;
int life=30,energy=30,zombieCount=0,mHealth=20;
int[]ZombieX = new int[50];
int CurrentZX=0,CurrentZY=0,CurrentZ=0;
int[]ZombieY = new int[50];
int[]crystX = new int[50];
int[]crystY = new int[50];
boolean nearMonolith=false,isWin=false;
Image personB = new ImageIcon("person.png").getImage();
Image personR = new ImageIcon("personR.png").getImage();
Image personL = new ImageIcon("personL.png").getImage();
Image personA = new ImageIcon("personAttack.png").getImage();
Image personA1 = new ImageIcon("personA1.png").getImage();
Image personA2 = new ImageIcon("personA2.png").getImage();
Image personA3 = new ImageIcon("personA3.png").getImage();
Image personA4 = new ImageIcon("personA4.png").getImage();
Image personF = new ImageIcon("personF.png").getImage();
Image zombieB = new ImageIcon("zombie.png").getImage();
Image zombieA = new ImageIcon("zombieA.png").getImage();
Image healCrystal = new ImageIcon("crystall.png").getImage();
Image person = personB;
Image zombie = zombieB;
int mX,mY;
Image tree = new ImageIcon("tree.png").getImage();
Image monolith = new ImageIcon("monolith.png").getImage();
Image goldImage = new ImageIcon("gold.png").getImage();
Image woodImage = new ImageIcon("wood.png").getImage();
Image grass = new ImageIcon("grass.png").getImage();
Image chest = new ImageIcon("chest.png").getImage();

public void create(){
	mHealth = 20;
	nearMonolith = false;
	isWin = false;
	for(int i =0;i<50;i++){
		for(int j =0;j<50;j++){
			WorldMatrix[i][j] = new Random().nextInt(10);
			if(WorldMatrix[i][j]==6){
				WorldMatrix[i][j]=new Random().nextInt(20);
				if(WorldMatrix[i][j]==17){
					WorldMatrix[i][j]=6;
				}else{
					WorldMatrix[i][j]=7;
				}
			}
			if(WorldMatrix[i][j]==1 && j>=7){
				for(int u =0;u<new Random().nextInt(5)+1;u++){
					WorldMatrix[i][j-u]=1;
				}
			}
			if(WorldMatrix[i][j]==1 && i>=7){
				for(int u =0;u<new Random().nextInt(5)+1;u++){
					WorldMatrix[i-u][j]=1;
				}
			}
		}
	}
	int n=0;
	for(int i=0;i<50;i++){
		for(int j=0;j<50;j++){
			if(WorldMatrix[i][j]==6 && j<49 && j>0){
				
				if(WorldMatrix[i][j+1]!=1){
					WorldMatrix[i][j+1]=11;
					ZombieX[n] = i;
					ZombieY[n]= j+1;
				
					n++;
					zombieCount++;
				}else if(WorldMatrix[i][j-1]!=1){
					WorldMatrix[i][j-1]=11;
					ZombieX[n] = i;
					ZombieY[n]= j-1;
					
					n++;
					zombieCount++;
				}else if(WorldMatrix[i+1][j]!=1){
					WorldMatrix[i+1][j]=11;
					ZombieX[n] = i+1;
					ZombieY[n]= j;
				
					n++;
					zombieCount++;
				}else if(WorldMatrix[i-1][j]!=1){
					WorldMatrix[i-1][j]=11;
					ZombieX[n] = i-1;
					ZombieY[n]= j;
					
					n++;
					zombieCount++;
				}
			}
		}
	}
	WorldMatrix[PlayerX][PlayerY]=10;
	mX=new Random().nextInt(48);
	mY=new Random().nextInt(48);
	WorldMatrix[mX][mY]=666;
}
int x=1,y=1;
public void ZombieMove(){
	for(int i=0;i<zombieCount;i++){
		int Choose = new Random().nextInt(4);
		if(Choose==0 && ZombieY[i]<=46){
			if(WorldMatrix[ZombieX[i]][ZombieY[i]+1]!=1 && WorldMatrix[ZombieX[i]][ZombieY[i]+1]!=6){
			WorldMatrix[ZombieX[i]][ZombieY[i]+1]=11;
			WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
			ZombieY[i]++;
		}
			}
		if(Choose==1 && ZombieY[i]>=2){
		 if(WorldMatrix[ZombieX[i]][ZombieY[i]-1]!=1 && WorldMatrix[ZombieX[i]][ZombieY[i]-1]!=6){
			WorldMatrix[ZombieX[i]][ZombieY[i]-1]=11;
			WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
			ZombieY[i]--;
		}
		}
		if(Choose==2 && ZombieX[i]<=46){
			if(WorldMatrix[ZombieX[i]+1][ZombieY[i]]!=1 && WorldMatrix[ZombieX[i]+1][ZombieY[i]]!=6){
			WorldMatrix[ZombieX[i]+1][ZombieY[i]]=11;
			WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
			ZombieX[i]++;
		}
		}
		   if(Choose==3 && ZombieX[i]>=2){
			   if(WorldMatrix[ZombieX[i]-1][ZombieY[i]]!=1 && WorldMatrix[ZombieX[i]-1][ZombieY[i]]!=6){
			WorldMatrix[ZombieX[i]-1][ZombieY[i]]=11;
			WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
			ZombieX[i]--;
		}
		   }
	}
}
public void rec(){
String Scheck="";
		
		try{FileReader Input = new FileReader("record.txt");//////проверяем рекорд с файла, если больше - записываем
		int check;
		
		while((check = Input.read())!=-1){
			Scheck +=(char)check;
		}
		Input.close();
		}catch(IOException ex){
			
		}
		int check = Integer.parseInt(Scheck);
		if(level>check){
			try{
			FileWriter Output = new FileWriter("record.txt",false);
			Output.append(level+"");
			Output.flush();
			Output.close();
			}
			catch(IOException ex){
				System.out.print(ex+"\n");
			}
		}
}
public matrix(){
	t.start();
	Enemy.start();
	addKeyListener(new MKA());
	create();
	setFocusable(true);
}
private class MKA extends KeyAdapter{
	public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();
	
	if(key == KeyEvent.VK_RIGHT && PlayerY<=48){
		person = personR;
		if(WorldMatrix[PlayerX][PlayerY+1]==6){
			gold+=150;
		}
		if(WorldMatrix[PlayerX][PlayerY+1]!=1 && WorldMatrix[PlayerX][PlayerY+1]!=666 && WorldMatrix[PlayerX][PlayerY+1]!=11 && WorldMatrix[PlayerX][PlayerY+1]!=15){
			WorldMatrix[PlayerX][PlayerY]=100;
			WorldMatrix[PlayerX][PlayerY+1]=10;
			PlayerY+=1;
		}
	}		
	if(key == KeyEvent.VK_LEFT && PlayerY>=1){
		person = personL;
		if(WorldMatrix[PlayerX][PlayerY-1]==6 ){
			gold+=150;
		}
		if(WorldMatrix[PlayerX][PlayerY-1]!=1 && WorldMatrix[PlayerX][PlayerY-1]!=666 && WorldMatrix[PlayerX][PlayerY-1]!=11&& WorldMatrix[PlayerX][PlayerY-1]!=15){
			WorldMatrix[PlayerX][PlayerY]=100;
			WorldMatrix[PlayerX][PlayerY-1]=10;
			PlayerY-=1;
		}	
	}
	if(key == KeyEvent.VK_UP && PlayerX>=1){
		person = personB;
		if(WorldMatrix[PlayerX-1][PlayerY]==6){
			gold+=150;
		}
		if(WorldMatrix[PlayerX-1][PlayerY]!=1 && WorldMatrix[PlayerX-1][PlayerY]!=666  && WorldMatrix[PlayerX-1][PlayerY]!=11&& WorldMatrix[PlayerX-1][PlayerY]!=15){
			
			WorldMatrix[PlayerX][PlayerY]=100;
			WorldMatrix[PlayerX-1][PlayerY]=10;
			PlayerX-=1;
		}
	}
	if(key == KeyEvent.VK_DOWN && PlayerX<=47){
		person = personF;
		if(WorldMatrix[PlayerX+1][PlayerY]==6){
			gold+=150;
		}
		if(WorldMatrix[PlayerX+1][PlayerY]!=1  && WorldMatrix[PlayerX+1][PlayerY]!=666 && WorldMatrix[PlayerX+1][PlayerY]!=11 && WorldMatrix[PlayerX+1][PlayerY]!=15){
			
			WorldMatrix[PlayerX][PlayerY]=100;
			WorldMatrix[PlayerX+1][PlayerY]=10;
			PlayerX+=1;
		}
	}
	if(key == KeyEvent.VK_SPACE && energy>=5){
		if(person == personR && PlayerX<=48){
			person=personA4;
			if(WorldMatrix[PlayerX][PlayerY+1]==1){
				wood++;
			}
			if(WorldMatrix[PlayerX][PlayerY+1]==666){
				mHealth--;
			}
			if(WorldMatrix[PlayerX][PlayerY+1]==11){
				for(int y=0;y<ZombieX.length;y++){
					if(ZombieX[y]==PlayerX){
						ZombieX[y]=0;
					}
				}
				for(int y=0;y<ZombieY.length;y++){
					if(ZombieY[y]==PlayerY+1){
						ZombieY[y]=0;
					}
				}
				WorldMatrix[PlayerX-1][PlayerY]=0;
			}
			if(WorldMatrix[PlayerX][PlayerY+1]!=666 || mHealth<=0){
				WorldMatrix[PlayerX][PlayerY+1]=0;
			}
			energy-=5;
		}
		if(person == personL && PlayerX>=2){
			person=personA1;
			if(WorldMatrix[PlayerX][PlayerY-1]==1){
				wood++;
			}
			if(WorldMatrix[PlayerX][PlayerY-1]==11){
				for(int y=0;y<ZombieX.length;y++){
					if(ZombieX[y]==PlayerX){
						ZombieX[y]=0;
					}
				}
				for(int y=0;y<ZombieY.length;y++){
					if(ZombieY[y]==PlayerY-1){
						ZombieY[y]=0;
					}
				}
				WorldMatrix[PlayerX-1][PlayerY]=0;
			}
			if(WorldMatrix[PlayerX][PlayerY-1]==666){
				mHealth--;
			}
			if(WorldMatrix[PlayerX][PlayerY-1]!=666 || mHealth<=0){
				WorldMatrix[PlayerX][PlayerY-1]=0;
				
			}
			energy-=5;
		}
		if(person == personF && PlayerY<=48 ){
			person=personA3;
			if(WorldMatrix[PlayerX+1][PlayerY]==1){
				wood++;
			}
			if(WorldMatrix[PlayerX+1][PlayerY]==11){
				for(int y=0;y<ZombieX.length;y++){
					if(ZombieX[y]==PlayerX+1){
						ZombieX[y]=0;
					}
				}
				for(int y=0;y<ZombieY.length;y++){
					if(ZombieY[y]==PlayerY){
						ZombieY[y]=0;
					}
				}
				WorldMatrix[PlayerX-1][PlayerY]=0;
			}
			if(WorldMatrix[PlayerX+1][PlayerY]==666){
				mHealth--;
			}
			if(WorldMatrix[PlayerX+1][PlayerY]!=666 || mHealth<=0){
				WorldMatrix[PlayerX+1][PlayerY]=0;
				
			}
			energy-=5;
		}
		if(person == personB && PlayerY>=2){
			person=personA2;
			if(WorldMatrix[PlayerX-1][PlayerY]==1){
				wood++;
			}
			if(WorldMatrix[PlayerX-1][PlayerY]==666){
				mHealth--;
			}
			if(WorldMatrix[PlayerX-1][PlayerY]==11){
				for(int y=0;y<ZombieX.length;y++){
					if(ZombieX[y]==PlayerX-1){
						ZombieX[y]=0;
					}
				}
				for(int y=0;y<ZombieY.length;y++){
					if(ZombieY[y]==PlayerY){
						ZombieY[y]=0;
					}
				}
				WorldMatrix[PlayerX-1][PlayerY]=0;
			}
			if(WorldMatrix[PlayerX-1][PlayerY]!=666 || mHealth<=0){
				WorldMatrix[PlayerX-1][PlayerY]=0;
				
			}
			energy-=5;
		}
		if(mHealth<=0){
			isWin = true;
			level++;
			rec();
		}
	}
	if(key == KeyEvent.VK_B && gold>=300 && wood>=30){
		
		
			
			gold-=300;
			wood-=30;
			
			WorldMatrix[PlayerX][PlayerY]=15;
			crystX[h]=PlayerX;
			crystY[h]=PlayerY;
			h++;
			PlayerX--;
			PlayerY--;
			WorldMatrix[PlayerX][PlayerY]=10;
		
	}
	if(key == KeyEvent.VK_R && energy>=15){
		
		person = personA;
		if(PlayerX<=48){
		if(WorldMatrix[PlayerX+1][PlayerY]==1 || WorldMatrix[PlayerX+1][PlayerY]==11){
			WorldMatrix[PlayerX+1][PlayerY]=4;
			wood++;
		}
		    if(PlayerY<=48){
		       if(WorldMatrix[PlayerX+1][PlayerY+1]==1 || WorldMatrix[PlayerX+1][PlayerY+1]==11){
			   WorldMatrix[PlayerX+1][PlayerY+1]=4;
		  	   wood++;
		         }
		     }
		    if(PlayerY>=2){
		    if(WorldMatrix[PlayerX+1][PlayerY-1]==1 || WorldMatrix[PlayerX+1][PlayerY-1]==11){
			WorldMatrix[PlayerX+1][PlayerY-1]=4;
			wood++;
		     }
		     }
		}
		
		if(PlayerX>=2)
		{
			if(WorldMatrix[PlayerX-1][PlayerY]==1 || WorldMatrix[PlayerX-1][PlayerY]==11){
				WorldMatrix[PlayerX-1][PlayerY]=4;
				wood++;
			}
			  if(PlayerY>=2){
			  if(WorldMatrix[PlayerX-1][PlayerY-1]==1 || WorldMatrix[PlayerX-1][PlayerY-1]==11){
				WorldMatrix[PlayerX-1][PlayerY-1]=4;
				wood++;
			  }
			  }
			  if(PlayerY<=48){
			if(WorldMatrix[PlayerX-1][PlayerY+1]==1 || WorldMatrix[PlayerX-1][PlayerY+1]==11){
				WorldMatrix[PlayerX-1][PlayerY+1]=4;
				wood++;
			}
			  }
		}
		if(PlayerY<=48){
			if(WorldMatrix[PlayerX][PlayerY+1]==1 ){
				WorldMatrix[PlayerX][PlayerY+1]=4;
				wood++;
			}
		}
		if(PlayerY>=2){
		if(WorldMatrix[PlayerX][PlayerY-1]==1 ){
			WorldMatrix[PlayerX][PlayerY-1]=4;
			wood++;
		}
		}	
		energy-=15;
	}
	}
public void keyReleased(KeyEvent e){
	
	}
}
public void Work(){
	if(PlayerX-5<0){
		z1=10;
		z = 0;
		
	}else{
		
			z = PlayerX-4;
	}
	if(PlayerX+5>50){
		z1 = 50;
		z = 40;
	}else if(PlayerX+5<=50 && PlayerX-5>0){
		
			z1 = PlayerX+5;
		
	}
	
	if(PlayerY-5<0){
		z3=10;
		z2 = 0;
		
	}else{
		
		z2 = PlayerY-5;
		
	}
	if(PlayerY+5>50){
		z3 = 50;
		z2=41;		
		
	}else if(PlayerY+5<=50 && PlayerY-5>0){
		
			z3 = PlayerY+4;
		
	}
}
public void paint(Graphics g){
	g = (Graphics2D)g;
	y=1;
	x=1;
	Work();
	for(int i=z;i<z1;i++){
		for(int j = z2;j<z3;j++){
			if(WorldMatrix[i][j]!=1 && WorldMatrix[i][j]!=10 && WorldMatrix[i][j]!=4  && WorldMatrix[i][j]!=16 && WorldMatrix[i][j]!=15){
				g.setColor(Color.green);
				g.fillRect(x, y, 60, 60);
				g.drawRect(x, y, 60, 60);
				
			}
			if(WorldMatrix[i][j]==1){
				g.drawImage(tree, x, y, null);
				
			}
			if(WorldMatrix[i][j]==10){
				g.drawImage(person, x, y, null);
				
			}
			if(WorldMatrix[i][j]==4){
				g.drawImage(grass, x, y, null);
				
			}
			if(WorldMatrix[i][j]==6){
				g.drawImage(chest, x, y, null);
			}
			if(WorldMatrix[i][j]==11){
				g.drawImage(zombie, x, y, null);
			}
			if(WorldMatrix[i][j]==15){
				g.drawImage(healCrystal, x, y, null);
			}
			if(WorldMatrix[i][j]==666){
				g.drawImage(monolith, x, y, null);
			}
			x+=60;
		}
		y+=60;
		x=1;
		
	}
	///////////mini map
	xMini=590;
	yMini=1;
	for(int i=0;i<50;i++){
		for(int j = 0;j<50;j++){
			if(WorldMatrix[i][j]!=1 && WorldMatrix[i][j]!=10){
				g.setColor(Color.green);
				g.fillRect(xMini, yMini, 6, 6);
				g.drawRect(xMini, yMini, 6, 6);
				
			}
			if(WorldMatrix[i][j]==1){
				g.setColor(Color.red);
				g.fillRect(xMini, yMini, 6, 6);
				g.drawRect(xMini, yMini, 6, 6);
				
			}
			if(WorldMatrix[i][j]==10){
				g.setColor(Color.blue);
				g.fillRect(xMini, yMini, 6, 6);
				g.drawRect(xMini, yMini, 6, 6);
				
			}
			/*if(WorldMatrix[i][j]==6){
				g.setColor(Color.yellow);
				g.fillRect(xMini, yMini, 6, 6);
				g.drawRect(xMini, yMini, 6, 6);
			}
			if(WorldMatrix[i][j]==11){
				g.setColor(Color.black);
				g.fillRect(xMini, yMini, 6, 6);
				g.drawRect(xMini, yMini, 6, 6);
			}*/
			if(WorldMatrix[i][j]==15){
				g.setColor(Color.magenta);
				g.fillRect(xMini, yMini, 6, 6);
				g.drawRect(xMini, yMini, 6, 6);
			}
			if(WorldMatrix[i][j]==100){
				g.setColor(Color.PINK);
				g.fillRect(xMini , yMini, 6, 6);
				g.drawRect(xMini, yMini, 6, 6);
			}
			/*if(WorldMatrix[i][j]==666){
				g.setColor(Color.white );
				g.fillRect(xMini , yMini, 6, 6);
				g.drawRect(xMini, yMini, 6, 6);
			}*/
			xMini+=6;
		}
		yMini+=6;
		xMini=590;
	}
	g.setColor(Color.WHITE);
	g.fillRect(640,320,60,30);
	g.fillRect(640,360,60,30);
	g.fillRect(720,430,80,30);
	g.fillRect(720,400,100,30);
	g.fillRect(720,360,150,30);
	g.fillRect(720,320,150,30);
    g.fillRect(0 ,550, 970, 50);
	g.fillRect(550, 0, 50, 650);
	g.setColor(Color.black);
	Font B = new Font("Arial",2,20);
	g.setFont(B);
	g.drawImage(goldImage,600,320,null);
	g.drawString(gold+"",640, 340);
    g.drawImage(woodImage,600,360,null);
	g.drawString(wood+"",640, 380);
	g.drawString("level:"+level,720, 450);

	int xL=720,yL=320;
	g.setColor(Color.red);
	for(int i=0;i<life;i++){
		g.fillRect(xL, yL, 4, 30);
		xL+=5;
	}
	
	 xL=720;
	 yL=360;
	g.setColor(Color.blue);
	for(int i=0;i<energy;i++){
		g.fillRect(xL, yL, 4, 30);
		xL+=5;
	}
	 xL=720;
	 yL=400;
	g.setColor(Color.black);
	for(int i=0;i<mHealth;i++){
		g.fillRect(xL, yL, 4, 30);
		xL+=5;
	}

	
}
@Override
public void actionPerformed(ActionEvent e) {
	repaint();
	if(life<=0){
		System.exit(1);
	}
	if(isWin){
		create();
	}
	
}
@Override
public void run() {
	while(true){
	try {
		if(PlayerX>mX-2 && PlayerX<mX+2 && PlayerY>mY-2 && PlayerY<mY+2 ){
			nearMonolith = true;
 		}
		for(int i=0;i<zombieCount;i++){
			if(PlayerX>ZombieX[i]-5 && PlayerX<ZombieX[i]+5 && PlayerY>ZombieY[i]-5 && PlayerY<ZombieY[i]+5 && WorldMatrix[ZombieX[i]][ZombieY[i]]!=4 && WorldMatrix[ZombieX[i]][ZombieY[i]]!=0  || nearMonolith){
				if(ZombieX[i]>=2 && ZombieX[i]<=48 && ZombieY[i]>=2 && ZombieY[i]<=48){
				if(WorldMatrix[ZombieX[i]+1][ZombieY[i]]==10){
					zombie = zombieA;
					life-=3;
				}else 
				if(PlayerX>ZombieX[i] && WorldMatrix[ZombieX[i]+1][ZombieY[i]]!=1 && WorldMatrix[ZombieX[i]+1][ZombieY[i]]!=666){
					zombie = zombieB;
					WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
					WorldMatrix[ZombieX[i]+1][ZombieY[i]]=11;
					ZombieX[i]++;
				}else if(PlayerX>ZombieX[i] && WorldMatrix[ZombieX[i]+1][ZombieY[i]]==1){
					if(WorldMatrix[ZombieX[i]-1][ZombieY[i]]!=1){
						zombie = zombieB;
						WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
						WorldMatrix[ZombieX[i]-1][ZombieY[i]]=11;
						ZombieX[i]--;
					}
				}
				
				if(WorldMatrix[ZombieX[i]-1][ZombieY[i]]==10){
					zombie = zombieA;
					life-=3;
				}else 
				if(PlayerX<ZombieX[i]&& WorldMatrix[ZombieX[i]-1][ZombieY[i]]!=1 && WorldMatrix[ZombieX[i]-1][ZombieY[i]]!=666){
					zombie = zombieB;
					WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
					WorldMatrix[ZombieX[i]-1][ZombieY[i]]=11;
					ZombieX[i]--;
				}else if(PlayerX>ZombieX[i] && WorldMatrix[ZombieX[i]-1][ZombieY[i]]==1){
					if(WorldMatrix[ZombieX[i]+1][ZombieY[i]]!=1){
						zombie = zombieB;
						WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
						WorldMatrix[ZombieX[i]+1][ZombieY[i]]=11;
						ZombieX[i]++;
					}
				}
				
				if(WorldMatrix[ZombieX[i]][ZombieY[i]+1]==10){
					zombie = zombieA;
					life-=3;
				}else 
				if(PlayerY>ZombieY[i]&& WorldMatrix[ZombieX[i]][ZombieY[i]+1]!=1 && WorldMatrix[ZombieX[i]][ZombieY[i]+1]!=666){
					zombie = zombieB;
					WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
					WorldMatrix[ZombieX[i]][ZombieY[i]+1]=11;
					ZombieY[i]++;
				}else if(PlayerX>ZombieX[i] && WorldMatrix[ZombieX[i]][ZombieY[i]+1]==1){
					if(WorldMatrix[ZombieX[i]][ZombieY[i]-1]!=1){
						zombie = zombieB;
						WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
						WorldMatrix[ZombieX[i]][ZombieY[i]-1]=11;
						ZombieY[i]--;
					}
				}
				
				if(WorldMatrix[ZombieX[i]][ZombieY[i]-1]==10){
					zombie = zombieA;
					life-=3;
				}else 
				if(PlayerY<ZombieY[i]&& WorldMatrix[ZombieX[i]][ZombieY[i]-1]!=1 && WorldMatrix[ZombieX[i]][ZombieY[i]-1]!=666){
					zombie = zombieB;
					WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
					WorldMatrix[ZombieX[i]][ZombieY[i]-1]=11;
					ZombieY[i]--;
				}else if(PlayerX>ZombieX[i] && WorldMatrix[ZombieX[i]][ZombieY[i]-1]==1){
					if(WorldMatrix[ZombieX[i]][ZombieY[i]+1]!=1){
						zombie = zombieB;
						WorldMatrix[ZombieX[i]][ZombieY[i]]=0;
						WorldMatrix[ZombieX[i]][ZombieY[i]+1]=11;
						ZombieY[i]++;
					}
				}
			}
			}
			}
			
		
		
		Thread.sleep(400);
		for(int i=0;i<h;i++){
		if(PlayerX>crystX[i]-2 && PlayerX<crystX[i]+2 && PlayerY>crystY[i]-2 && PlayerY<crystY[i]+2  ){
			if(life<30)life++;
		}
		}
		if(energy<30){
			energy++;
		}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}
}
