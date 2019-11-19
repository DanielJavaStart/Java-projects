package bioLifePackage;

import java.util.Iterator;
import java.util.Random;

public class food {
	boolean targeted = false,elseTarg= false;
	int x=0,y=0,c=0,c1=0,runTime=0,points=0,id=0,index=0, speed = 15;

	public food(int x,int y,int id){
		this.x = x;
		this.y = y;
		this.id = id;
		changeWay();
	}
	public void findFood(){
		if(!targeted){
			if( LifeClass.list1.size()>=1){
		Iterator<MeatEaters>iter = LifeClass.list1.iterator();
		while(iter.hasNext()){
			MeatEaters curr = iter.next();
		   if(x>curr.x-100 && x<curr.x+100 && y>curr.y-100 && y<curr.y+100 ){
			   Iterator<food>MCurr = LifeClass.foodList.iterator();
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
		if(targeted){
			Iterator<MeatEaters>iter0 = LifeClass.list1.iterator();
			while(iter0.hasNext()){
				MeatEaters curr1 = iter0.next();
				
			  if(curr1.id == index){
				  
				  if(x>curr1.x)x-=speed;
				  if(x<curr1.x)x+=speed;
				  if(y>curr1.y)y-=speed;
				  if(y<curr1.y)y+=speed;
				  
				  
				  
				  if(x>curr1.x-speed && x<curr1.x+speed && y>curr1.y-speed && y<curr1.y+speed){
					  if(points>=curr1.points){
					      LifeClass.list1.remove(curr1);
					      points+=10;
					      if(speed>-1)speed--;
					      targeted = false;
						  changeWay();
						  break;
						  }else{
							  speed--;
							  x-=50;
							  y-=50;
							  points+=5;
							  curr1.points-=10;
						  }
			     }
			  
				}
			  
			   }
			
			
	}
	}
	public void changeWay(){
		c=new Random().nextInt(1100);
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
