
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame implements GameConstant,ActionListener{

	JFrame f;
	JButton b1,b2;
	
	 GameFrame()
	{
		 f=new JFrame();
		
		 f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
		 JLabel l=new JLabel("YOU WANNA PLAY THEN GO!!!");
		 l.setBounds(400, 50, 800, 150);
		 f.add(l);
		 b1=new JButton("start");
		 b2=new JButton("exit");
		 b1.setBounds(400, 150, 150, 80);
		 b1.setBackground(Color.pink);
		 b2.setBounds(400, 300, 150, 80);
		 b2.setBackground(Color.pink);
		 f.add(b1);
		 f.add(b2);
		 b1.addActionListener(this);
		 b2.addActionListener(this);
		 f.setSize(G_WIDTH,G_HEIGHT);
		 f.setLocation(30,20);
		 
		 f.setLayout(null);
		
		 f.setVisible(true);
		 
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==b1)
		{
		
		setSize(G_WIDTH,G_HEIGHT);
		setLocation(30,20);
		setTitle(TITLE);
		setResizable(false);
		Board board=new Board();
		add(board);
		setVisible(true);
		}
		if(e.getSource()==b2)
			System.exit(0);
		
	}
}
