
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements GameConstant {
	
	private int x;
	private int y;
	private boolean flage=false;
	
	
	private CameraEffect camera;
	private Car car;
	private Timer timer;
	private Enemy enemy;
	private Enemy[] enemies=new Enemy[MAXENEMY];
	private Enemy[] truckenemies=new Enemy[7];
	private ScoreBoard scoreboard;
	private int score=0;
	
	Board(){
		camera=new CameraEffect();
		setSize(G_WIDTH,G_HEIGHT);
		
		enemy=new Enemy();
		car=new Car();
		loadEnemy();
		loadTruckEnemy();
		scoreboard= new ScoreBoard();
		setFocusable(true);
		bindEvent();
		gameLoop();
		
	}
	
	private boolean collision()
	{
		int max_width=car.getW()>enemy.getW()?car.getW():enemy.getW();
		int max_height=car.getH()>enemy.getH()?car.getH():enemy.getH();
		
		int x_distance=Math.abs(car.getX()-enemy.getX());
		int y_distance=Math.abs(car.getY()-enemy.getY());;
		
		return (x_distance<max_width)&&(y_distance<max_height);
	}
	
	private boolean checkCollision(Enemy enemy)
	{
		
		int max_width=car.getW()>enemy.getW()?car.getW():enemy.getW();
		int max_height=car.getH()>enemy.getH()?car.getH():enemy.getH();
		
		int x_distance=Math.abs(car.getX()-enemy.getX());
		int y_distance=Math.abs(car.getY()-enemy.getY());;
		
		return (x_distance<max_width)&&(y_distance<max_height);
	}
	
	private boolean overTake(Enemy enemy)
	{	
		flage = true;
		return enemy.y == (car.getY()+car.getH());	
	}
	
	private void carOverTake()
	{
		for(Enemy enemy: enemies)
		{
			if(overTake(enemy)) {
				
				score+=50;
				
			}
			scoreboard.setScore(score);
		}
		
		
	}
	private void truckOverTake()
	{
		for(Enemy enemy: truckenemies)
		{
			if(overTake(enemy)) {
				
				score+=50;
				
			}
			scoreboard.setScore(score);
		}
	}
	
	private boolean overTake()
	{	
		flage = true;
		return enemy.y == (car.getY()+car.getH());	
	}
	
	private void loadEnemy()
	{
		int x=450;
		int y=G_WIDTH-400;
		int speed =6;
		for(int i=0;i<enemies.length;i++)
		{
			enemies[i]=new Enemy(x,y,speed);
			speed++;
			y-=900;
			if(x<600)
			{
			x+=30;
			}
			if(x>=600)
			{
			x-=80;	
			}
		}
	}
	
	int counter =0;
	int gameStartCounter = 0;
	
	
	private void startGame() {
		gameStartCounter++;
		
		if(gameStartCounter==17 || gameStartCounter==37 || gameStartCounter==57) {
			
			counter++;
		}
		if(gameStartCounter==60) {
		camera.setSpeed(28);
		}
	}
	
	private void drawCounter(Graphics g) {
		
		g.setFont(new Font("Arial",Font.BOLD,50));
		g.drawString(""+counter,G_WIDTH/2, G_HEIGHT/2);
	}
	
	private void drawStart(Graphics g)
	{
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString("START",G_WIDTH,G_HEIGHT);
	}
	private void loadTruckEnemy()
	{
		x=380;
		y=G_WIDTH-900;
		for(int i=0;i<truckenemies.length;i++)
		{
			truckenemies[i]=new Enemy(x,y);
			if(x<450)
			{
			x+=200;
			}
			if(x>=450)
			{
			x-=50;
			}
			y-=900;
		}
	}
	
	private void printtruckEnemy(Graphics g)
	{
		for(Enemy enemy:truckenemies)
		{
			overTake(enemy);
			if(checkCollision(enemy))
			{
				gameOver(g);
				timer.stop();
			}
			if(camera.getY()<19648) {
			enemy.truckEnemy(g);
			enemy.move();
			
			}
		}
	}
	
	private void printEnemy(Graphics g)
	{
		for(Enemy enemy:enemies)
		{
//			g.drawEnemy();
			if(checkCollision(enemy))
			{
				gameOver(g);
				timer.stop();
			}
			enemy.carEnemy(g);
			if(camera.getY()<19648) {
			enemy.move();
			}
		}
	}
	
	
	
	private void gameLoop() {
		
		ActionListener l=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				startGame();
				carOverTake();
				truckOverTake();
				if(camera.getY()>100)
				{
				camera.up();
				}
		
				if(camera.getY()<19648) {
					enemy.move();
				}
				
				car.move();
				if(camera.getY()<19648) {
				scoreboard.circleMove();
				}
				if(camera.getY()<100)
				{
					if(car.getY()>30) {
						car.moveup();
						
					}
				}
				
//				scoreboard.setScore(score);
			}
		};
	
	   timer=new Timer(DELAY,l);
	   timer.start();
	  
	}
	
	private void bindEvent() {
		KeyListener l=new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				car.direction(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				}
				
				if(e.getKeyCode()==KeyEvent.VK_UP) {
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					car.direction(-1);
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					car.direction(1);
				}
				
			}
		};
		this.addKeyListener(l);
	}
	
//	private void Count(Graphics g)
//	{
//		g.drawString("count"+count,G_WIDTH-200,200);
//	}
	private void drawScore(Graphics g) {
		if(overTake())
		{
			if(flage==true)
			{
				score+=50;
				flage=false;
			}
			
			scoreboard.setScore(score);
			
		}
		scoreboard.drawScore(g);
	}
	
	private void drawBackGround(Graphics g) {
        camera.drawCameraImage(g);
       
	}
	
	private void gameOver(Graphics g)
	{
		g.drawString("GAME OVER", G_WIDTH/2, G_HEIGHT/2);
	}
	private void drawScoreboard(Graphics g)
	{
		scoreboard.drawScoreBoard(g);
	}
	
	private void drawFlage(Graphics g)
	{
		scoreboard.drawFlage(g);
	}
	
	private void drawFinish(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,70));
		g.drawString("Finish",G_WIDTH/2, G_HEIGHT/2);
	}
	
	private void drawCircle(Graphics g)
	{
		scoreboard.drawCircle(g);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackGround(g);
		scoreboard.drawScoreBoard(g);
		scoreboard.drawFlage(g);
		scoreboard.drawCircle(g);
		drawScore(g);
		if(gameStartCounter<=58)
		{
		drawCounter(g);
		}
		
		printtruckEnemy(g);
		printEnemy(g);
		enemy.drawEnemy(g);
		
		car.drawCar(g);
		if(collision())
		{
			gameOver(g);
			timer.stop();
			
		}
		if(car.getY()<200)
		{
			
			drawFinish(g);
			
		}
	}
}
