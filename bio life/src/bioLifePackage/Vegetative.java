package bioLifePackage;

import java.util.Iterator;
import java.util.Random;

public class Vegetative {
boolean adult = false,targeted = false,elseTarg = false;;
int x=0,y=0,c=0,c1=0,runTime=0,points=0,id=0,index=0,speed = 15;

public Vegetative(boolean adult,int x,int y){
	this.adult = adult;
	this.x = x;
	this.y = y;
	changeWay();
	id = new Random().nextInt(100000);
}
public void findFood(){
	if(!targeted){
	elseTarg = false;
	Iterator<food>iter = LifeClass.foodList.iterator();
	while(iter.hasNext()){
		food curr = iter.next();
	   if(x>curr.x-100 && x<curr.x+100 && y>curr.y-50 && y<curr.y+100 ){
		   
			  
		   Iterator<Vegetative>MCurr = LifeClass.list.iterator();
		   while(MCurr.hasNext()){
			   if(MCurr.next().index == curr.id){
			   elseTarg = true;
				   break;
			   }
		   }
		   if(!elseTarg){
			   targeted = true;
			   index = curr.id;
		   }
	   }
	}
	
	}
	if(targeted){
			Iterator<food>iter0 = LifeClass.foodList.iterator();
			while(iter0.hasNext()){
				food curr1 = iter0.next();
				
			  if(curr1.id == index){
				  
				  if(x>curr1.x)x-=speed;
				  if(x<curr1.x)x+=speed;
				  if(y>curr1.y)y-=speed;
				  if(y<curr1.y)y+=speed;
				  
				  
				  
				  if(x>curr1.x-speed && x<curr1.x+speed && y>curr1.y-speed && y<curr1.y+speed){
					  if(points>=curr1.points){
				      LifeClass.foodList.remove(curr1);
				      points+=10;
				      if(speed>-1)speed--;
				      targeted = false;
					  changeWay();
					  break;
					  }else{
						  x-=50;
						  y-=50;
						  speed--;
						  points+=5;
						  curr1.points-=10;
					  }
					  
			     }
			  
				}
			  
			   }
			
			
	}
}
public void changeWay(){
	c=new Random().nextInt(1050);
	c1=new Random().nextInt(550);	
	
}
public void move(){
	if(!targeted){
	runTime++;
	if(x>c)x-=speed;
	if(x<c)x+=speed;
	if(y>c1)y-=speed;
	if(y<c)y+=speed;}
}



}
