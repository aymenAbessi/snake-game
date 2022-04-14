package snake;

import java.awt.*;


import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

import snake2.Snake;

public class gamePanel extends JPanel implements ActionListener{
	JLabel l=new JLabel(new ImageIcon("C:\\Users\\Aymen\\eclipse-workspace\\snake\\src\\snake\\start.jpg"));
	static final int SCREEN_WIDHT =600;
	static final int SCREEN_HIGHT =600;
	static final int UNIT_SIZE =25;
	static final int GAME_UNIT =(SCREEN_WIDHT*SCREEN_HIGHT)/UNIT_SIZE;
	static final int DELAY=75;
	static int []X;
	static int []Y;
	int bodyParts;
	int applesEaten;
	int appleX;
	int appleY;
	char direction ='R';
	int x=0;
	boolean running =false;
	Timer timer;
	Random random;
	JButton b;
	gamePanel(){
		random=new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDHT,SCREEN_HIGHT));
		this.setBackground(Color.black);
		 this.setFocusable(true);
		 this.addKeyListener(new myKeyAdapter ());
		//startGame();
	}
	public void startGame() {
		x=1;
		applesEaten=0;  
		 bodyParts=6;
		direction='R';
		running=true;
		X=new int[GAME_UNIT];
		Y=new int[GAME_UNIT];
		newApple();
		
		timer=new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		draw(g);
		
	}
	public void draw(Graphics g) {
		if(x==0) {
			this.setBackground(new Color(120,170,35));
			this.add(l);
			g.setColor(Color.black);
			g.setFont(new Font("ink free",Font.BOLD,30));
			FontMetrics matx=getFontMetrics(g.getFont());
			g.drawString("<<WELCOME TO THE SNAKE GAME>>", (SCREEN_WIDHT-matx.stringWidth("<<WELCOME TO THE SNAKE GAME>>") )/2, 390);
			g.drawString("<<press space to start game>>", (SCREEN_WIDHT-matx.stringWidth("<<press space to start game>>") )/2, 500);
			
		}else if (x==1) {
			this.remove(l);
			this.setBackground(Color.black);
		if(running) {
			
			g.setColor(Color.red);
			g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);
			for(int i=0;i<bodyParts;i++) {
				if(i==0) {
					g.setColor(Color.green);
					g.fillRect(X[i], Y[i], UNIT_SIZE, UNIT_SIZE);	
				}
				else {

					g.setColor(new Color(random.nextInt(255) ,random.nextInt(255) ,random.nextInt(255)));
						
					g.fillRect(X[i], Y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
			}
		}
	
	
		else {
			gameOver(g);
		}
		g.setColor(Color.green);
		for(int i=0;i<bodyParts;i++) {
		if(Y[i]<=49 || running==false) {
			g.setColor(Color.blue);
		break;
		}	
		}
		g.setFont(new Font("ink free",Font.BOLD,30));
		FontMetrics matrix=getFontMetrics(g.getFont());
		g.drawString("SCORE :"+applesEaten, (SCREEN_WIDHT-matrix.stringWidth("SCORE :"+applesEaten) )/2, 30);
	}
	public void newApple() {
		appleX=random.nextInt((int)(SCREEN_WIDHT/UNIT_SIZE))*UNIT_SIZE;
		appleY=random.nextInt((int)(SCREEN_HIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	public void  moving () {
		for(int i =bodyParts;i>0;i--) {
			X[i]=X[i-1];
			Y[i]=Y[i-1];
		}
		switch(direction) {
		case 'U':
			Y[0]=Y[0]-UNIT_SIZE;
			break;
		case 'D':
			Y[0]=Y[0]+UNIT_SIZE;
			break;
		case 'R':
			X[0]=X[0]+UNIT_SIZE;
			break;
		case 'L':
			X[0]=X[0]-UNIT_SIZE;
			break;
		}
	}
	public void checkApple() {
		if(X[0]==appleX&&Y[0]==appleY) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	public void checkCollusion() {
		for(int i=bodyParts;i>0;i--) {
			if(X[0]==X[i]&&Y[0]==Y[i]) {
				running=false;
			}
		}
		if(X[0]<0) {
			running=false;
		}
		if(X[0]>SCREEN_HIGHT-1) {
			running=false;
		}
		if(X[0]<0) {
			running=false;
		}
		if(Y[0]>SCREEN_WIDHT-1) {
			running=false;
		}
		if(Y[0]<0) {
			running=false;
		}
		
		if(running==false) {
			x=2;
			timer.stop();
		}
	
	}
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("ink free",Font.BOLD,75));
		FontMetrics matx= getFontMetrics(g.getFont());
		g.drawString("GAME OVER", (SCREEN_WIDHT-matx.stringWidth("GAME OVER"))/2, SCREEN_HIGHT/2);
		
		g.setColor(Color.white);
		g.setFont(new Font("ink free",Font.BOLD,30));
		 matx=getFontMetrics(g.getFont());
		g.drawString("<<press space to play again>>", (SCREEN_WIDHT-matx.stringWidth("<<press space to play again>>") )/2, 575);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();

		if(running) {
			moving();
			checkApple();
			checkCollusion();
		}
		
		
	}
	
	public class myKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction !='R') {
					direction='L';
				}break;
			case KeyEvent.VK_Q:
				if (direction !='R') {
					direction='L';
				}break;
			case KeyEvent.VK_RIGHT:
				if (direction !='L') {
					direction='R';
				}break;
			case KeyEvent.VK_D:
				if (direction !='L') {
					direction='R';
				}break;
			case KeyEvent.VK_UP:
				if (direction !='D') {
					direction='U';
				}break;
			case KeyEvent.VK_Z:
				if (direction !='D') {
					direction='U';
				}break;
			case KeyEvent.VK_DOWN:
					if (direction !='U') {
						direction='D';
					}break;
			case KeyEvent.VK_S:
				if (direction !='U') {
					direction='D';
					
				}break;
			case KeyEvent.VK_SPACE:
				startGame();
				break;
				
			}

	}
	}

}
