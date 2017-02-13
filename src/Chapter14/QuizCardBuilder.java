package Chapter14;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.util.*;

public class QuizCardBuilder {

		private JTextArea question;
		private JTextArea answer;
		private ArrayList<QuizCard> cardList;
		private JFrame frame;
		
		public static void main(String[] args){
			QuizCardBuilder builder = new QuizCardBuilder();
			builder.go();
		}
		
	public void go(){					//build gui
		
		frame = new JFrame("Quiz Card Builder");
		JPanel mainPanel = new JPanel();
		
		Font bigFont = new Font("sanserif", Font.BOLD, 24); //set standard font
		
		question = new JTextArea(6,20);					//question box
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);
		
		JScrollPane qScroller = new JScrollPane(question); //scrollbar policy for question
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		answer = new JTextArea(6, 20);				//answer box
		answer.setLineWrap(true);
		answer.setFont(bigFont);
		
		JScrollPane aScroller = new JScrollPane(answer); //scrollbar policy for answer
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton nextButton = new JButton("Next Card");		//next button 
		
		cardList = new ArrayList<QuizCard>();
		
		JLabel qLabel = new JLabel("Question: "); //labels for the question and answer boxes
		JLabel aLabel = new JLabel("Answer: ");
		
		mainPanel.add(qLabel); //add it all to the panel
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		
		JMenuBar menuBar = new JMenuBar();			//make new menu bar
		JMenu fileMenu = new JMenu("File");			//make file menu and add new and save to it
		JMenuItem newMenuItem = new JMenuItem("New");  //add menu to the menu bar
		JMenuItem saveMenuItem = new JMenuItem("Save");	//ask frame to use this menu bar
		newMenuItem.addActionListener(new NewMenuListener());	//menu items can fire an actionevent
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}
	
	private class NextCardListener implements ActionListener{ //triggered when user hits NextCard button
		public void actionPerformed(ActionEvent ev){
			//add current card to the list and clear test areas
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}
	}
	
	private class SaveMenuListener implements ActionListener{	//triggered when user clicks save from filemenu
		public void actionPerformed(ActionEvent ev){
			//bring up a file dialog box
			//let the user name and save the set
			
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile()); //brings up a file dialog box and waits on this line until user chooses to save from the dialog box
		}
	}
	
	private class NewMenuListener implements ActionListener{ //triggered by choosing 'New' from file menu, user wants to start a brand new set
		public void actionPerformed(ActionEvent ev){
			//clear out the card list and clear out the text area
			
			cardList.clear();
			clearCard();
		}
	}
	
	private void clearCard(){
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	private void saveFile(File file){ //called by saveMenuListener, does the actual file writing
		//iterate through the list of cards, and write each 1 out to the text file
		//in a parseable way(in other words, with a clear separation between parts
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for(QuizCard card:cardList){
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
			}
			writer.close();
		} catch (IOException ex){
			System.out.println("couldn't write the cardList out");
			ex.printStackTrace();}
	}
}


