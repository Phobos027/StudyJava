package MiniMusicCmdLine;

import javax.sound.midi.*;
import java.util.Scanner;

public class MiniMusicCmdLine {
    public static void main(String[] args) {
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        int note = 0;
        int instrument = 0;
        Scanner scanner = new Scanner(System.in);
        try {
        System.out.print("Введите номер инструмента: ");
        instrument = scanner.nextInt();
        System.out.println();
        System.out.print("Введите номер ноты: ");
        note = scanner.nextInt();
        scanner.close();
        } catch (Exception e){
            System.out.println("Что-то пошло не так");
        } finally {
            mini.play(instrument, note);
        }

        }

    public void play (int instrument, int note){
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

            ShortMessage on = new ShortMessage();
            on.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(on, 1);
            track.add(noteOn);

            ShortMessage off = new ShortMessage();
            off.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(off, 20);
            track.add(noteOff);

            player.setSequence(sequence);
            player.start();

        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
