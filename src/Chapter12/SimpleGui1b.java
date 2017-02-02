package Chapter12;
import javax.swing.*;
import java.awt.event.*; //import action stuff

public class SimpleGui1b implements ActionListener { //implement interface
	
	JButton button;
	
	public static void main(String[] args){
		
		SimpleGui1b gui = new SimpleGui1b();
		gui.go();
	}
	
	public void go(){
		
		JFrame frame = new JFrame();
		button = new JButton("click me");
		
		button.addActionListener(this); //add button to the list of listeners, argument must be object from class that implements actionlistener
		
		frame.getContentPane().add(button); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event){ //implement action listeners performed method, this is actual event handling method 
		button.setText("I've been clicked!"); //button calls this method to let me know an event has happened
	}
}
