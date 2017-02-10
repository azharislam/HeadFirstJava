package Chapter13;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel1 {

	public static void main(String[] args){
		
		Panel1 gui = new Panel1();
		gui.go();
	}
	
	public void go(){
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton button = new JButton("Shock Me");
		JButton button2 = new JButton("Bliss");
		
		panel.add(button);
		panel.add(button2);
	
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}
