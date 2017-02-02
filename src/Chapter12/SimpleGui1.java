package Chapter12;
import javax.swing.*; //import swing

public class SimpleGui1 {

	public static void main (String[] args){
		
		JFrame frame = new JFrame(); //make a frame
		JButton button = new JButton("click me"); //make a button
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this makes program quit when you close window
		
		frame.getContentPane().add(button); //add button to frames content pane
		
		frame.setSize(300, 300); //set size of frame
		
		frame.setVisible(true); //make it visible, has to be true to see frame
	}
}
