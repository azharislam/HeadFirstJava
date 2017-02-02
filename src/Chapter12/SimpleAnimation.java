package Chapter12;
import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {

	int x = 70;
	int y = 70;				//x and y co-ordinates for the circle
	
	public static void main (String[] args){
		
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}
	
	public void go(){
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyDrawPanel drawPanel = new MyDrawPanel();
		
		frame.getContentPane().add(drawPanel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		for(int i = 0; i < 130; i++){	//repeat 130 times
			x++;
			y++;
			
			drawPanel.repaint(); //panel will repaint itself so circle will be in new location
			
			try{
				Thread.sleep(50);	//slow it down so we can see it move
			} catch(Exception ex){}
		}
	}
	
	class MyDrawPanel extends JPanel { //an inner class
		
		public void paintComponent(Graphics g){
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight()  );
			
			g.setColor(Color.green);
			g.fillOval(x, y, 40, 40);
		}
	}
	
}

