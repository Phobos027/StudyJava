package MiniMiniMusicApp;

import javax.sound.midi.*;

public class MiniMiniMusicApp {
    public static void main(String[] args) {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();
    }

    public void play(){
        try{
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, 102, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 4);
            track.add(changeInstrument);

            ShortMessage q = new ShortMessage();
            q.setMessage(144, 2, 35, 100);
            MidiEvent noteOn2 = new MidiEvent(q, 5);
            track.add(noteOn2);

            ShortMessage w = new ShortMessage();
            w.setMessage(128, 2, 35, 100);
            MidiEvent noteOff2 = new MidiEvent(w, 20);
            track.add(noteOff2);

            player.setSequence(seq);
            player.start();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
