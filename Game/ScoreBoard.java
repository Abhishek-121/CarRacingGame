import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ScoreBoard extends Sprite implements GameConstant {
	private int score;
	int circle_y;
	public ScoreBoard() {
		circle_y=350;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void drawFlage(Graphics g)
	{
		BufferedImage  subimage = image.getSubimage(1247,593,1724-1247,1081-593);
		g.drawImage(subimage,G_WIDTH-150,150,50,50,null);
	}
	
	int circleDelay=0;
	public void circleMove()
	{
		if(circleDelay>13 && circle_y>180)
		{
		circleDelay=0;
		circle_y-=3;
		}circleDelay++;
	}
	
	public void drawCircle(Graphics g)
	{ 
		
		int x=G_WIDTH-145;
		int y=circle_y;
		
		g.setColor(Color.RED);
		g.fillOval(x,y,20,20);
		
	}
	
	public void drawScoreBoard(Graphics g)
	{

		BufferedImage  subimage = image.getSubimage(1744,709,1887-1744,1090-709);
		g.drawImage(subimage,G_WIDTH-250,0,250,G_HEIGHT,null);
	}
	public void drawScore(Graphics g) {
		
		g.setFont(new Font(SCORE,Font.BOLD,25));
		g.setColor(Color.BLUE);
		g.drawString(SCORE+" "+score,G_WIDTH-200,45);
	}

}
