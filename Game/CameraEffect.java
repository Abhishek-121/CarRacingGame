

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class CameraEffect implements GameConstant{

//	ScoreBoard scoreboard;
	int k;
	
	Car car;
	
	private BufferedImage image;
	private int speed;
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	private int x;
	private int y;
	private int h;
	private int w;
	
	CameraEffect(){
		y=MAX_HEIGHT-G_HEIGHT;
		h=G_HEIGHT;
		w=G_WIDTH;
		speed=0;
		car= new Car();
//		scoreboard= new ScoreBoard();
		loadCameraImage();
	}
	
	public void drawCameraImage(Graphics g) {
		BufferedImage subImage=image.getSubimage(x,y,G_WIDTH,G_HEIGHT);
		g.drawImage(subImage,0,0,w,h,null);
		
	}
	
	
	public void up() {
	
		k=1;
		y-=speed;
	
	}
	
	
//	public void drawflage()
//	{
//		BufferedImage  subimage = image.getSubimage();
//		g.drawImage(subimage, arg1, arg2, arg3, arg4, arg5,null)
//	}
//	public void down() {
//		if(y==20000) {
//			y-=20;
//		}
//		if(y<=20000) {
//			y+=speed;
//		}
//	}
	
	public void loadCameraImage() {
		try {
			image=ImageIO.read(CameraEffect.class.getResource(B_IMAGE));
		}
		catch (IOException e) {
           System.out.println(e);		
		}
		
	}
	
}
