import java.awt.Rectangle;


public class cloudClass {
int x=0,y=0;
game Game;

public Rectangle getRect(){
	return new Rectangle(x, y,100, 50 );
}
public cloudClass(int x, int y,game Game){
	this.x = x;
	this.y = y;
	this.Game=Game;
}
public void move(){
	x = x-5;	
}

}
