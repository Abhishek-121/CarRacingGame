

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Car extends Sprite implements GameConstant {

	

	
	
	public Car(){
		x=505;
		y=G_HEIGHT-150;
		w=50;
		h=70;
		
	}
	
	public void carImage(Graphics g) {
		BufferedImage subImage=image.getSubimage(409,949,475-409,1082-949);
		g.drawImage(subImage,x,y,w,h,null);
				
	}
	
	public void direction(int dir) {
		//System.out.println("direction");
		speed=8;
		speed=speed*dir;
		
	}
	public void move() {
		
//		if(y<=900 && y>50)
//		{
//		 moveup();	
//		}
		
		if(x<370) {
			x+=10;
		}
		if(x>640) {
			x-=10;
		}
		if(x>370 && x<640)
	   {
			x+=speed;
	   }
		
	}

	public void moveup()
	{
		int speed=8;
//		System.out.println("moveup");
		y-=speed;
	}
	public void drawCar(Graphics g) {
		carImage(g);
	
	}
	

}
