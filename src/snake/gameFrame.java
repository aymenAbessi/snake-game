package snake;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class gameFrame extends JFrame{
	gameFrame(){
	ImageIcon snake=new ImageIcon("C:\\Users\\Aymen\\eclipse-workspace\\snake\\src\\snake\\snake.jpg");
	this.setIconImage(snake.getImage());
	this.add(new gamePanel());
	this.setVisible(true);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setTitle("Snake Game");
	this.setLocationRelativeTo(null);
		
	}
	

}
