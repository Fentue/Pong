// A PONG GAME --- BY VINCENT KY NGUYEN --- 2017 --- BLOCK D --- MS.ZHENG ---

//imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

//creates a class that has methods of JComponent and has ActionListener and KeyListener
public class shapesAndActions extends JComponent implements ActionListener, KeyListener{
	
	//this block of code was suggested by Eclipse and it would give a warning without it here
	private static final long serialVersionUID = 1L;

	//creates a timer of 5 milliseconds
	Timer timer = new Timer(5, this);
	
	//creates variables of the 2 paddles
	double paddleWidth = 20, paddleHeight = 225;
	double player1x = (100-paddleWidth), player1y = (500 - (paddleHeight/2));
	double player2x = (1220-paddleWidth), player2y = (500 - (paddleHeight/2));
	double paddleSpeed = 4, velocity1 = 0, velocity2 = 0;
	
	//creates variables of the ball (chooses a random speed from the array)
	Random random = new Random();
	double ballSize = 50, ballX = (650 - (ballSize/2)), ballY = (500 - (ballSize/2));
	double[] ballocityXarray = {-2.5, 2.5};
	double ballocityX = ballocityXarray[random.nextInt(ballocityXarray.length)];
	double[] ballocityYarray = {-3.5, -2.5, -1.5, -0.5, 0.0, 0.5, 1.5, 2.5, 3.5};
	double ballocityY = ballocityYarray[random.nextInt(ballocityXarray.length)];
	
	//creates the point integers to keep score
	int p1score = 0, p2score = 0;
	
	//picks a random colour set
	int colourSet = random.nextInt(8);
	
	//creates a method that runs in the main class and adds a KeyListener
	public void listen(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	//this method draws graphics using awt.Graphics
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//uses more 2D graphics
		Graphics2D g2 = (Graphics2D) g;
		
		Color bgColour = new Color (0, 0, 0);
		Color mainColour = new Color(255, 255, 255);
		Color secondColour = new Color(255, 255, 255);
		
		switch (colourSet) {
			//black and white
			case 0:
				bgColour = new Color (0, 0, 0);
				mainColour = new Color(255, 255, 255);
				secondColour = new Color(255, 255, 255);
				break;
			//white orange and blue
			case 1:
				bgColour = new Color (255, 255, 255);
				mainColour = new Color(130, 214, 204);
				secondColour = new Color(247, 199, 86);
				break;
			//black and green aka matrix
			case 2:
				bgColour = new Color (0, 0, 0);
				mainColour = new Color(35, 124, 19);
				secondColour = new Color(35, 124, 19);
				break;
			//yellow and purple
			case 3:
				bgColour = new Color (229, 237, 139);
				mainColour = new Color(170, 66, 188);
				secondColour = new Color(222, 139, 237);
				break;
			//orange
			case 4:
				bgColour = new Color (175, 238, 238);
				mainColour = new Color(255, 140, 0);
				secondColour = new Color(255, 69, 0);
				break;
			//red
			case 5:
				bgColour = new Color (250, 128, 114);
				mainColour = new Color (255, 99, 71);
				secondColour = new Color (220, 20, 60);
			//yellow and black aka bumblebee
			case 6:
				bgColour = new Color (232, 237, 87);
				mainColour = new Color(0, 0, 0);
				secondColour = new Color(0, 0, 0);
				break;
			//white and black aka inverse
			case 7:
				bgColour = new Color (255, 255, 255);
				mainColour = new Color(0, 0, 0);
				secondColour = new Color(0, 0, 0);
				break;
			//default colour = black and white
			default:
				bgColour = new Color (255, 255, 255);
				mainColour = new Color(130, 214, 204);
				secondColour = new Color(247, 199, 86);
				break;
		}

		
		//sets the background colour
		g2.setColor(bgColour);
		
		//creates the background
		Rectangle.Double background = new Rectangle.Double(0, 0, 1300, 1000);
		g2.fill(background);
		
		//sets the main colour
		g2.setColor(mainColour);
		//creates 2 paddles with their sizes and locations and fills them with colour
		Rectangle.Double player1 = new Rectangle.Double(player1x, player1y, paddleWidth, paddleHeight);
		g2.fill(player1);
		Rectangle.Double player2 = new Rectangle.Double(player2x, player2y, paddleWidth, paddleHeight);
		g2.fill(player2);
		
		//makes a line to be a net
		Line2D.Double net = new Line2D.Double(650, 0, 650, 1000);
		g2.draw(net);
		
		//makes a ball in the second colour with the sizes and location abd fills it with colour
		g2.setColor(secondColour);
		Ellipse2D.Double ball = new Ellipse2D.Double(ballX, ballY, ballSize, ballSize);
		g2.fill(ball);
		
		
		//creates the scores to be displayed using texts
		g2.setColor(secondColour);
		g2.setFont(new Font("impact", Font.PLAIN, 100));
		g2.drawString(Integer.toString(p1score), 325, 150);
		g2.drawString(Integer.toString(p2score), 975, 150);
		
		//my name
		g2.setFont(new Font("impact", Font.PLAIN, 25));
		g2.drawString("Vincent Ky Nguyen 2017", 1025, 950);
		
		
		//starts the timer
		timer.start();
		
		//changes the font
		g2.setFont(new Font("Impact", Font.PLAIN, 100));
		
	/*	if ((p1score == 0) && (p2score == 0)){
			for (int i = 0; i<=3; i++){
				g2.drawString(Integer.toString(i), 500,650);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}	
			}
		}*/
		
		//says FINAL ROUND when the scores are both 6
		if ((p1score == 6) && (p2score == 6)){
			g2.drawString("FINAL ROUND", 425, 150);
		}
		//says MATCH POINT if one of the players has a score of 6
		else if ((p1score == 6)||(p2score == 6)){
			g2.drawString("MATCH POINT", 425, 150);
		}
		//changes the font
		g2.setFont(new Font("Impact", Font.PLAIN, 85));
		//says who wins the game when the score gets to 7
		if (p1score == 7){
			g2.drawString("PLAYER 1 WINS!", 425, 150);
		}
		if (p2score == 7){
			g2.drawString("PLAYER 2 WINS!", 425, 150);
		}
		
	}
	
	//runs these actions when something is done
	public void actionPerformed(ActionEvent e){
		
		//if a player wins, it shows a victory message and then exits the program
		if (p1score == 7){
		    JOptionPane.showMessageDialog(null, "PLAYER 1 WINS!", "VICTORY", JOptionPane.INFORMATION_MESSAGE);
		    System.exit(0);
		}
		if (p2score == 7){
		    JOptionPane.showMessageDialog(null, "PLAYER 2 WINS!", "VICTORY", JOptionPane.INFORMATION_MESSAGE);
		    System.exit(0);
		}
		//if the ball hits a paddle, it will go the opposite direction (reverses the velocity of the ball)
		if (((ballX == (player1x+paddleWidth) && (((player1y + paddleHeight) > (ballY+(ballSize/2))) && ((ballY+(ballSize/2)) > player1y)))||
		((ballX == (player2x-ballSize)) && (((player2y + paddleHeight) > (ballY+(ballSize/2))) && ((ballY+(ballSize/2)) > player2y))))){
			ballocityX = -ballocityX;
		}
		//if the ball reaches the left side of the window
		if (ballX < (player1x-ballSize)){
			//player 2 scores a point
			p2score++;
			//centres the ball back to its original position
			ballX = 625;
			ballY = 475;
			//waits 2 seconds and ignores an error
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			//centres the paddles back to the original position
			player1y = (500 - (paddleHeight/2));
			player2y = (500 - (paddleHeight/2));
			
		}
		//if the ball reaches the right side of the window
		if (ballX > player2x+paddleWidth){
			//player 1 scores a point
			p1score++;
			//centres the ball back to its original position
			ballX = 625;
			ballY = 475;
			//waits 2 seconds and ignores an error
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			//centres the paddles back to the original position
			player1y = (500 - (paddleHeight/2));
			player2y = (500 - (paddleHeight/2));
		}
		//makes the ball bounce if it hits the sides of the screen
		if (ballY < 0 || ballY > 920){
			ballocityY = -ballocityY;
		}
		//makes the ball's location move by its velocity
		ballX += ballocityX;
		ballY += ballocityY;
		//makes the paddles' locations move by their velocities
		player1y += velocity1;
		player2y += velocity2;
		//draws the shapes again after they have changed
		repaint();
	}

	//detects if a key is pressed and does an action
	public void keyPressed(KeyEvent e) {
		//gets the number code of which key they pressed
		int keyCode = e.getKeyCode();
		//moves the paddle up or down according to which key they press using the velocity
		//if they go off screen it stops changing the velocity
		if (keyCode == KeyEvent.VK_W){
			if (player1y < 50){
				velocity1 = 0;
			}
			else{
				velocity1 = -paddleSpeed;				
			}
		}
		if (keyCode == KeyEvent.VK_S){
			if (player1y > (950 - paddleHeight)){
				velocity1 = 0;
			}
			else{
			velocity1 = paddleSpeed;
			}
		}
		if (keyCode == KeyEvent.VK_UP){
			if (player2y < 50){
				velocity2 = 0;
			}
			else{
			velocity2 = -paddleSpeed;
			}
		}
		if (keyCode == KeyEvent.VK_DOWN){
			if (player2y > (950 - paddleHeight)){
				velocity2 = 0;
			}
			else{
			velocity2 = paddleSpeed;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		//gets the number code of which key they pressed
		int keyCode = e.getKeyCode();
		//stops the paddles if they release the key
		if ((keyCode == KeyEvent.VK_W) || (keyCode == KeyEvent.VK_S)){
			velocity1 = 0;
		}
		if ((keyCode == KeyEvent.VK_UP) || (keyCode == KeyEvent.VK_DOWN)){
			velocity2 = 0;
		}

	}
	
	//does something when a key is typed
	//this is unused, but it will pop up an error if I don't put it here
	public void keyTyped(KeyEvent e) {}

}
