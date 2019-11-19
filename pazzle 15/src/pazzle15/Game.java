package pazzle15;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, MouseListener{
Timer T = new Timer(550,this);
boolean isWin = false;
Image butt = new ImageIcon("src/button.png").getImage();
int u1=0;
 int[][]nums =new int[4][4];
int minutes=0;
int seconds=0;
public Game(){
	T.start();
	addMouseListener(this);
	
	int k=0;
	for(int i=1;i<5;i++){
		for(int j=1;j<5;j++){
			nums[i-1][j-1] = k;
			k++;
		}
	}
	mixer();
}

public void mixer(){
	minutes = 0;
	seconds = 0;
	for(int i=0;i<16;i++){
		int a1= new Random().nextInt(4);
		int a2= new Random().nextInt(4);
		int b1= new Random().nextInt(4);
		int b2= new Random().nextInt(4);
		
		int mid = nums[a1][a2];
		nums[a1][a2]=nums[b1][b2];
		nums[b1][b2] = mid;
	}
}

static int k1 =0;

public void winChecker(){
	k1=0;
	for(int i=0;i<4;i++){
	for(int j=0;j<4;j++){
		if(nums[i][j]!=k1){
			isWin = false;
			break;
		}
		k1++;
		isWin = true;
	}
	}
}

public void paint(Graphics g){
	g.setColor(Color.white);
	g.fillRect(0, 0, 600, 600);
	g.draw3DRect (25, 10, 50, 75, true);
    g.draw3DRect (25, 110, 50, 75, false);
    g.fill3DRect (100, 10, 50, 75, true);
    g.fill3DRect (100, 110, 50, 75, false);
	g.drawImage(butt,10,0,null);
	
	Font B = new Font("Arial",30,30);
	g.setFont(B);
	int x=10,y=100;
	
	if(isWin){
		g.setColor(Color.BLACK);
		g.drawString("VICTORY",150, 50);
	}
	
	g.setColor(Color.BLACK);
	g.drawString(minutes+":"+seconds,300, 50);
	
	
	
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
		if(nums[i][j]!=15){
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 95, 95);
		g.setColor(Color.white);
		g.drawString(nums[i][j]+"", x+30, y+50);
		x+=100;
		}
		if(nums[i][j]==15){
			x+=100;
		}
		
		}
		y+=100;
		x=10;
	}
}
@Override
public void mouseClicked(MouseEvent e) {
	
}
@Override
public void actionPerformed(ActionEvent arg0) {
	if(!isWin){
	u1++;
	if(u1==2){
		seconds++;
		u1=0;
		if(seconds>=60){
			seconds=0;
			minutes++;
		}
	}
	}
	winChecker();
	repaint();
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
public void move(int n,int h){
	if(n<3){
	if(nums[n+1][h]==15){
	  nums[n+1][h]=nums[n][h];
	  nums[n][h]=15;
	}}

	if(n>0){
		if(nums[n-1][h]==15){
		  nums[n-1][h]=nums[n][h];
		  nums[n][h]=15;
	}}
	
	if(h<3){
		if(nums[n][h+1]==15){
		  nums[n][h+1]=nums[n][h];
		  nums[n][h]=15;
	}}
	if(h>0){
		if(nums[n][h-1]==15){
		  nums[n][h-1]=nums[n][h];
		  nums[n][h]=15;
		}}
}

@Override
public void mousePressed(MouseEvent arg0) {
	int x1=10,y1=100;
	float touchX = arg0.getX();
	float touchY = arg0.getY();
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){	
		if(touchX>x1 && touchX<x1+95 && touchY>y1 && touchY<y1+95){
			 move(i,j);
			 repaint();
			break;
		}
		x1+=100;
	
		}
		y1+=100;
		x1=10;
}
	
if(arg0.getX()>10 && arg0.getX()<100 && arg0.getY()>0 && arg0.getY()<100){
	mixer();
}
}
	
		

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
