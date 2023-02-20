package BeatBox;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class BeatBox {

    JPanel mainPanel;
    ArrayList<JCheckBox> checkBoxList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;
    String userName;
    ObjectOutputStream out;
    ObjectInputStream in;
    HashMap <String, boolean[]> otherSeqsMap = new HashMap<String, boolean[]>();
    JList incomingList;
    Vector <String> listVector = new Vector<String>();
    JTextField userMessage;
    int nextNum;

    String [] instrumentsNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
                                "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell",
                                "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int [] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {

        new BeatBox().startUp("cool");
    }

    private void startUp(String name) {
        userName = name;
        try {
            Socket sock = new Socket("127.0.0.1", 4242);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();
        } catch (Exception e){
            System.out.println("couldn't connect = you'll have to play alone");
        }
        setUpMidi();
        buildGui();
    }

    public void buildGui(){
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo UP");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

//        JButton serializeIt = new JButton("Serialize it");
//        serializeIt.addActionListener(new MySendListener());
//        buttonBox.add(serializeIt);
//
//        JButton restore = new JButton("Restore");
//        restore.addActionListener(new MyReadInListener());
//        buttonBox.add(restore);

        JButton sendIt = new JButton("Send");
        sendIt.addActionListener(new MySendMessageListener());
        sendIt.setMnemonic(KeyEvent.VK_ENTER);
        buttonBox.add(sendIt);


        JButton randomTrack = new JButton("Random track");
        randomTrack.addActionListener(new MyRandomListener());
        buttonBox.add(randomTrack);

        userMessage = new JTextField();
        buttonBox.add(userMessage);

        UIManager.put("OptionPane.yesButtonText"   , "Да");
        UIManager.put("OptionPane.noButtonText"    , "Нет"   );
        UIManager.put("OptionPane.cancelButtonText", "Отмена");


        incomingList = new JList();
        incomingList.addListSelectionListener(new MyListSelectionListener());
        incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane theList = new JScrollPane(incomingList);
        buttonBox.add(theList);
        incomingList.setListData(listVector);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentsNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }


        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setVisible(true);
    }

    public void setUpMidi(){
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception e){e.printStackTrace();}
    }

    public void buildTrackAndStart(){
        ArrayList <Integer> trackList = null;
        sequence.deleteTrack(track);
        track = sequence.createTrack();
        for (int i = 0; i < 16; i++) {
            trackList = new ArrayList<Integer>();
            for (int j = 0; j < 16; j++) {
                JCheckBox jc = (JCheckBox) checkBoxList.get(j + (16 * i));
                if (jc.isSelected()) {
                    int key = instruments[i];
                    trackList.add(key);
                } else trackList.add(null);
            }
            makeTracks(trackList);
        }
//        int [] trackList = null;
//
//        sequence.deleteTrack(track);
//        track = sequence.createTrack();
//
//        for (int i = 0; i < 16; i++) {
//            trackList = new int[16];
//
//            int key = instruments[i];
//
//            for (int j = 0; j < 16; j++) {
//                JCheckBox jc = (JCheckBox) checkBoxList.get(j + (16 * i));
//                if (jc.isSelected()){
//                    trackList[j] = key;
//                } else {
//                    trackList[j] = 0;
//                }
//            }
//
//            makeTracks(trackList);
//            track.add(makeEvent(176, 1, 127, 0, 16));
//        }

        track.add(makeEvent(192, 9, 1, 0, 15));
        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeTracks(ArrayList<Integer> list) {
        Iterator it = list.iterator();
        for (int i = 0; i < 16; i++) {
            Integer num = (Integer) it.next();
            if (num != null) {
                int numKey = num.intValue();
                track.add(makeEvent(144, 9, numKey, 100, i));
                track.add(makeEvent(128, 9, numKey, 100, i + 1));
            }
        }
    }


//    private void makeTracks(int[] list) {
//        for (int i = 0; i < 16; i++) {
//            int key = list[i];
//
//            if (key != 0){
//                track.add(makeEvent(144, 9, key, 100, i));
//                track.add(makeEvent(128, 9, key, 100, i + 1));
//            }
//        }
//    }

    private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e){e.printStackTrace();}
        return event;

    }

    public class MyStartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }

    public class MyStopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    public class MyUpTempoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }

    public class MyDownTempoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 0.97));
        }
    }

    private class MySendListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            boolean[] checkBoxState = new boolean[256];
//            for (int i = 0; i < 256; i++) {
//                JCheckBox check = (JCheckBox) checkBoxList.get(i);
//                if (check.isSelected()){
//                    checkBoxState[i] = true;
//                }
//            }
//
//            try{
//                FileOutputStream fileStream = new FileOutputStream(new File("CheckBox.ser"));
//                ObjectOutputStream os = new ObjectOutputStream(fileStream);
//                os.writeObject(checkBoxState);
//            } catch (Exception exception){
//                exception.printStackTrace();
//            }

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(theFrame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    public void saveFile(File file){
        boolean[] checkBoxState = new boolean[256];
        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkBoxList.get(i);
            if (check.isSelected()){
                checkBoxState[i] = true;
            }
        }

        try{
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(checkBoxState);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private class MyReadInListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(theFrame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

    private void loadFile(File file) {
        boolean[] checkBoxState = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream is = new ObjectInputStream(fileIn);
            checkBoxState = (boolean[]) is.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkBoxList.get(i);
            if (checkBoxState[i]){
                check.setSelected(true);
            }else check.setSelected(false);
        }
        sequencer.stop();
        buildTrackAndStart();
    }

    private class RemoteReader implements Runnable {
        boolean [] checkboxState  = null;
        String nameToShow = null;
        Object obj = null;

        @Override
        public void run() {
            try {
                while ((obj = in.readObject()) != null) {
                    System.out.println("got an object from server");
                    System.out.println(obj.getClass());
                    String nameToShow = (String) obj;
                    checkboxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkboxState);
                    listVector.add(nameToShow);
                    incomingList.setListData(listVector);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class MyListSelectionListener implements javax.swing.event.ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selected = (String) incomingList.getSelectedValue();
                if (selected != null) {
                    boolean [] selectedState = (boolean[]) otherSeqsMap.get(selected);
                    changeSequence(selectedState);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }
        }
    }

    public void changeSequence(boolean[] checkBoxState) {
        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkBoxList.get(i);
            if (checkBoxState[i]){
                check.setSelected(true);
            }else {
                check.setSelected(false);
            }
        }
    }

    private class MySendMessageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] checkboxState = new boolean[256];
            for (int i = 0; i < 256; i++) {
                JCheckBox check = (JCheckBox) checkBoxList.get(i);
                if (check.isSelected()) {
                    checkboxState[i] = true;
                }
            }
            String messageToSend = null;
            try {
                out.writeObject(userName + nextNum++ + ": " + userMessage.getText());
                out.writeObject(checkboxState);
            } catch (Exception exception){
                System.out.println("Sorry dude. Could not to send it to the server.");
            }
            userMessage.setText("");
        }
    }

    private class MyRandomListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           // boolean [] checkBoxState = null;
            int result = JOptionPane.showConfirmDialog(mainPanel, "Save?", "window", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == 0){
                JFileChooser fileSave = new JFileChooser();
                fileSave.showSaveDialog(theFrame);
                saveFile(fileSave.getSelectedFile());
            }
            sequencer.stop();
            for (int i = 0; i < 256; i++) {
                int random = (int) (Math.random() * 2);
                JCheckBox check = (JCheckBox) checkBoxList.get(i);
                if (random == 1){
                    check.setSelected(true);
                } else check.setSelected(false);
            }
            sequencer.stop();
            buildTrackAndStart();
        }
    }
}
