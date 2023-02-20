package MusicPlayer;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer3 {
    static JFrame frame = new JFrame("Мой первый музыкальный клип");
    static MyDrawPanel m1;

    public static void main(String[] args) {
        MiniMusicPlayer3 mMP3 = new MiniMusicPlayer3();
        mMP3.go();
    }

    public void setUpGui(){
        m1 = new MyDrawPanel();
        frame.setContentPane(m1);
        frame.setBounds(30, 30 , 300, 300);
        frame.setVisible(true);
    }

    public void go(){
        setUpGui();

        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int [] eventsIWant = {127};
            sequencer.addControllerEventListener(m1, new int [] {127});


            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for (int i = 0; i < 120; i+=8) {
                track.add(makeEvent(144, 1, i, 100, i));
                track.add(makeEvent(176, 1, 127, 0, i));
                track.add(makeEvent(128, 1, i, 100, i + 2));

            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        } catch (Exception ex){ex.printStackTrace();}
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(comd, chan, one, two);
            event = new MidiEvent(message, tick);
        } catch (Exception exception) {
        }
        return event;
    }

    class MyDrawPanel extends JPanel implements ControllerEventListener{
        boolean msg = false;

        @Override
        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }

        public void paintComponent (Graphics g){
            if (msg){
                Graphics2D g2 = (Graphics2D) g;

                int red = (int) (Math.random() * 250);
                int green = (int) (Math.random() * 250);
                int blue = (int) (Math.random() * 250);

                g.setColor(new Color(red, green, blue));

                int height = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);

                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);

                g.fillRect(x, y, width, height);
                msg = false;
            }
        }
    }
}
