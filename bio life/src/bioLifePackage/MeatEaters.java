package bioLifePackage;

import java.util.Iterator;
import java.util.Random;

public class MeatEaters {
	boolean adult = false;
	boolean targeted = false,exist = false,elseTarg = false;
	int runTime=0;
	int x=0,y=0,c=0,c1=0,index=0,points=0,id =0, speed =15;

	public MeatEaters(int x,int y,int id){
		
		this.x = x;
		this.y = y;
		this.id = id;
		changeWay();
		newChangeWay();
	}
    public void newChangeWay(){
    	c = new Random().nextInt(1100);
    	c1 = new Random().nextInt(550);
    }
	public void changeWay(){
		if(!targeted){
		Iterator<Vegetative>iter = LifeClass.list.iterator();
		while(iter.hasNext()){
			Vegetative curr = iter.next();
		   if(x>curr.x-100 && x<curr.x+100 && y>curr.y-100 && y<curr.y+100){
			   Iterator<MeatEaters>MCurr = LifeClass.list1.iterator();
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
	}
	public void move(){
		
		if(!targeted){
			
			runTime++;
			if(x>c)x-=speed;
			if(x<c)x+=speed;
			if(y>c1)y-=speed;
			if(y<c)y+=speed;
			
			changeWay();
		}
		
		if(targeted){
		Iterator<Vegetative>iter0 = LifeClass.list.iterator();
		while(iter0.hasNext()){
			Vegetative curr1 = iter0.next();
			
		  if(curr1.id == index){
			  exist = true;
			  
			  if(x>curr1.x)x-=speed;
			  if(x<curr1.x)x+=speed;
			  if(y>curr1.y)y-=speed;
			  if(y<curr1.y)y+=speed;
			  
			  
			  
			  if(x>curr1.x-speed && x<curr1.x+speed && y>curr1.y-speed && y<curr1.y+speed){
				  if(points>=curr1.points){
				      LifeClass.list.remove(curr1);
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
}
