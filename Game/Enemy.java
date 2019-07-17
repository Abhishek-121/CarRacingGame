
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Sprite implements GameConstant{

	
	
	
	public Enemy() {
		x=G_WIDTH-600;
		y=G_HEIGHT-1200;
	    w=50;
	    h=80;
	    speed=8;
	        
	}
	public Enemy(int x,int y,int speed)
	{
		this.x=x;
		this.y=y;
		w=50;
		h=80;
		this.speed=speed;
		
	}
	
	public Enemy(int x,int y) {
		speed=10;
		this.x=x;
		this.y=y;
		w=50;
		h=80;
	}
	
//	public void direction() {
//		speed=5;
//	}
//	
	public void move() {
//		speed=5;
		y+=speed;
	}
	

	public void truckEnemy(Graphics g)
	{
		BufferedImage subimage=image.getSubimage(602,675,689-602,844-675);
		g.drawImage(subimage,x,y, w, h,null);
	}
	
	public void carEnemy(Graphics g)
	{
		BufferedImage subimage=image.getSubimage(349,949,411-349,1084-946);
		g.drawImage(subimage, x, y, w, h,null);
	}
	
	public void enemyImage(Graphics g) {
		BufferedImage subimage=image.getSubimage(607,944,680-607,1077-944);
		g.drawImage(subimage,x,y,w,h,null);
	}
  	
   	public void drawEnemy(Graphics g) {
             enemyImage(g);  		
  	}
}
