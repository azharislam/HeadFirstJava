package Chapter11;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMiniMusicApp {

	public static void main (String[] args){
		
		MiniMiniMusicApp mini = new MiniMiniMusicApp();
		mini.play();
	}
	
	public void play(){
		
		try {
			Sequencer player = MidiSystem.getSequencer();  //get sequencer and then open it, sequencer doesnt come open
			
			player.open(); //open sequencer
			
			Sequence seq = new Sequence(Sequence.PPQ, 4); 
			
			Track track = seq.createTrack(); //ask sequence for a track, track lives in sequence, midi data lives on track
			
			ShortMessage a = new ShortMessage(); //make message
			a.setMessage(144, 1, 127, 100); //start playing note 44
			MidiEvent noteOn = new MidiEvent(a, 1); //trigger message a on the first beat, beat 1
			track.add(noteOn); //add midievent to the track
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, 44, 100); //128 (noteOff, 144 note on), 
										  //channel 1, channel 9 is the drummer
										 //0 to 127, low to high notes
										//0 to 100, velocity, how fast and hard is the key pressed
			MidiEvent noteOff = new MidiEvent(b, 16);
			track.add(noteOff);
			
			player.setSequence(seq); //give sequence to sequencer, like putting cd in a cd player
			player.start(); //press play 
		} 
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
