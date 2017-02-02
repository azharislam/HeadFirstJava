package Chapter12;
import javax.sound.midi.*;

public class MiniMusicPlayer1 implements ControllerEventListener{
	
	public static void main (String[] args){
		
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			int[] eventsIWant = {127};
			//sequencer.addControllerEventListener(this, eventsIWant); //register for events with sequencer
																	//event registration method takes listener and int array representing the list of controllerE vents you want
			
			Sequence seq = new Sequence(Sequence.PPQ, 4); //make sequence and track
			Track track = seq.createTrack();
			
			for (int i = 5; i < 61; i+=4){ //piano note 5 to 61, make notes keep going up
				
				track.add(makeEvent(144,1,i,100,i));
				
				track.add(makeEvent(176,1,127,0,i)); //176 says that event type is ControllerEvent
													//sole purpose is so something fires that we can listen for
													//cant listen for note on/off events
													//made it happen at same time as note on
				
				track.add(makeEvent(128,1,i,100,i + 2)); //call makeEvent method to make message and event
			} //end loop
			
			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
		}
		catch (Exception ex) {ex.printStackTrace();}
		} //close main
	
		public void controlChange(ShortMessage event){
			System.out.println("la");   				//event handler method, from controllerEvent listener interface
		}												//print 'la' to command line each time an event occurs
	
		public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
			MidiEvent event = null;
			try{
				ShortMessage a = new ShortMessage();
				a.setMessage(comd, chan, one, two);
				event = new MidiEvent(a, tick);
			} catch(Exception e){}
			return event;
	}

}
