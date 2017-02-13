package Chapter13;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.*;
import javax.sound.midi.*;

public class BeatBox {
	
	JPanel mainPanel; 
	ArrayList<JCheckBox> checkboxList; //store checkboxes in arraylist
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	//names of instruments and the drum keys stored in an array, each number corresponds to the instrument name in order
	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", 
			"Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", 
			"Whistle","Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", 
			"Open Hi Conga"};
	int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
	
	public static void main(String[] args){
		new BeatBox()  .buildGUI();
		
	}

	public void buildGUI(){
		theFrame = new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //margin between edges of the panel and where components are placed
		
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		JButton start = new JButton("Start"); //buttons for the beatbox
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);
		
		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for(int i = 0; i < 16; i++){
			nameBox.add(new Label(instrumentNames[i]));
		}
		
		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);
		
		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);
		
		for(int i = 0; i < 256; i++){	//make 256 checkboxes, leave them unticked, add them to ArrayList and GUI panel
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		} //end loop
		
		setUpMidi();
		
		theFrame.setBounds(50,50,300,300);
		theFrame.pack();
		theFrame.setVisible(true);
	}

		public void setUpMidi(){ //get the sequence, sequencer and track
			try{
				sequencer = MidiSystem.getSequencer();
				sequencer.open();
				sequence = new Sequence(Sequence.PPQ, 4);
				track = sequence.createTrack();
				sequencer.setTempoInBPM(120);
			} 
			catch (Exception e){e.printStackTrace();}
		}
		
	


	public void buildTrackAndStart(){
		int[] trackList = null;				//16 element array to hold values for one instrument across all 16 beats
		
		sequence.deleteTrack(track); //get rid of old track, make a new one
		track = sequence.createTrack();
		
		for(int i = 0; i < 16; i++){ //each of the 16 rows
			trackList = new int[16];
			
			int key = instruments[i]; //set the key, represents which instrument this is
			
			for(int j = 0; j < 16; j++){ //do this for each of the beats for this row
				
				JCheckBox jc = checkboxList.get(j + 16*i);
				if(jc.isSelected()){ //if checkbox at this beat is selected	
					trackList[j] = key; //put in key value in this slot in the array
				} else{
					trackList[j] = 0; //otherwise leave it empty so nothing plays
				}
			} //closer inner loop
			
			makeTracks(trackList);
			track.add(makeEvent(176, 1, 127, 0, 16)); //for this instrument and for all 16 beats, make events and add them to track
		}
			track.add(makeEvent(192, 9, 1, 0, 15)); //make sure theres an event on beat 16
			try{
				sequencer.setSequence(sequence);
				sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY); //loop iterations, continuous
				sequencer.start();
				sequencer.setTempoInBPM(120);
			} catch (Exception e) {e.printStackTrace();}
		} //close build trackandstart method
	
		public class MyStartListener implements ActionListener{ //listeners for the buttons inner classes
			public void actionPerformed(ActionEvent a){
				buildTrackAndStart();
			}
		}
	
		public class MyStopListener implements ActionListener{ //listeners for the buttons inner classes
			public void actionPerformed(ActionEvent a){
				sequencer.stop();
			}
		}
		
		public class MyUpTempoListener implements ActionListener{ //listeners for the buttons inner classes
			public void actionPerformed(ActionEvent a){
			 float tempoFactor = sequencer.getTempoFactor();
			 sequencer.setTempoFactor((float) (tempoFactor * 1.03)); //tempo factor scales sequencers tempo by factor provided +/- 3% per click
			}
		}
		
		public class MyDownTempoListener implements ActionListener{ //listeners for the buttons inner classes
			public void actionPerformed(ActionEvent a){
			 float tempoFactor = sequencer.getTempoFactor();
			 sequencer.setTempoFactor((float) (tempoFactor * 0.97));
			}
		}
		
		public void makeTracks(int[] list){	//this makes events for one instrument at a time for all 16 beats
											
			for (int i = 0; i < 16; i++){
				int key = list[i];
				
				if(key != 0){
					track.add(makeEvent(144, 9, key, 100, i));
					track.add(makeEvent(128, 9, key, 100, i+1)); //make note on note off events and add to track
				}
			}
	}
		
		public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
			MidiEvent event = null;
			try { 
				ShortMessage a = new ShortMessage();
				a.setMessage(comd, chan, one, two);
				event = new MidiEvent(a, tick);
				
			} catch (Exception e) {e.printStackTrace();}
			return event;
		}
		
		public class MySendListener implements ActionListener {
			public void actionPerformed(ActionEvent a){
				
				boolean[] checkboxState = new boolean[256]; //make boolean array to hold state of each checkbox
				
				for(int i = 0; i < 256; i++){
					
					JCheckBox check = (JCheckBox) checkboxList.get(i); //go through checkboxList
					if(check.isSelected()){								//get state of each one
						checkboxState[i] = true;						//add it to boolean array
					}
				}
				try{
					FileOutputStream fileStream = new FileOutputStream(new File("Checkbox.ser"));
					ObjectOutputStream os = new ObjectOutputStream(fileStream);
					os.writeObject(checkboxState);
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		}
		
		public class MyReadInListener implements ActionListener { //restores pattern
			
			public void actionPerformed(ActionEvent a){
				boolean[] checkboxState = null;
				try{
					FileInputStream fileIn = new FileInputStream(new File("Checkbox.ser"));
					ObjectInputStream is = new ObjectInputStream(fileIn);
					checkboxState = (boolean[]) is.readObject(); //read single object in file and cast it back to boolean array
					
				} catch (Exception ex) {ex.printStackTrace();}
				
				for (int i = 0; i < 256; i++){
					JCheckBox check = (JCheckBox) checkboxList.get(i);
					if(checkboxState[i]){
						check.setSelected(true);	//restore state of each checkboxes into the arraylist of actual JCheckBox objects (checkboxList)
					} else { 
						check.setSelected(false);
					}
				}
				
				sequencer.stop(); //stop whatever is playing, rebuild sequence using new state of checkboxes in arraylist
				buildTrackAndStart();
			}
		}
	}

	