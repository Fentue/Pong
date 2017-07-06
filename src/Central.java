// A PONG GAME --- BY VINCENT KY NGUYEN --- 2017 --- BLOCK D --- MS.ZHENG ---

//imports
import javax.swing.*;
import java.awt.*;

public class Central{
  
	
//displays instructions for the users
  public static void Intro(){
    JOptionPane.showMessageDialog(null, ("Welcome to Pong!" + "\nBy: Vincent Ky Nguyen - 2017"), "Hi", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null, "The first player to 7 points wins!", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null, ("Player 1 Controls:" + "\nUp = 'W' Key" + "\nDown = 'S' Key"), "Tutorial", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null, ("Player 2 Controls: " + "\nUp = UP ARROW Key  " + "\nDown = DOWN ARROW Key"), "Tutorial", JOptionPane.INFORMATION_MESSAGE);  
 
  }
  
  
  public static void Pong(){
	//creates a window
    JFrame window = new JFrame("PONG");
    
    //qualities of the window
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setTitle("PONG");
    window.setResizable(false);
    window.setSize(1300, 1000);
    window.setVisible(true);
    window.getContentPane().setBackground(Color.white);
    
    //calls the other class and draws shapes
    shapesAndActions shapesClass= new shapesAndActions();
    window.add(shapesClass);
    
    //calls the listen method in the other class (adds KeyListener)
    shapesClass.listen();
    
  }
  
  public static void main(String[] args) {
	//runs the intro and the window methods
    Intro();
    Pong();
  }
  

  
}