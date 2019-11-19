import java.awt.Image;

import javax.swing.ImageIcon;


public class bossClass {
	static game g;
static Image boss1 = new ImageIcon("pictures/1boss.png").getImage();
static Image boss = boss1;
static Image boss2 = new ImageIcon("pictures/2boss.png").getImage();
static Image blife = new ImageIcon("pictures/blife.png").getImage();
static int x=640,y=0;
static int bossHP=10,c=0,c1=25;
static int z=0,z1=0;
static boolean Ymove=false;
static boolean shoot;

public bossClass(){
	game.end = false;
	bossHP = 10;
	x=640;
	y=0;
}
 public static void fight(){
	if(game.bossFight){
			c++;
			if(Main.LevelChoose.getSelectedIndex()>3){
				c1=20;
				
			}
			if(c==c1){
				Ymove=true;
			}
			if(Ymove){
				if(game.y>y){
					y+=7;
				}
				if(game.y<y){
					y-=7;
				}
				if(y>game.y-5 && y<game.y+5){
					shoot = true;
					z = x;
					z1=y+25;
					c=0;
					Ymove = false;
				}
	 }}

 }

}
