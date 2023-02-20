package Gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TextAreal1 implements ActionListener, ItemListener, ListSelectionListener {

    JTextArea text;
    JCheckBox check;
    JList list;

    public static void main(String[] args) {
        TextAreal1 gui = new TextAreal1();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        check = new JCheckBox("Goes to 11");
        JButton button = new JButton("Just Click IT");
        String [] listEntries = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "theta"};
        list = new JList(listEntries);
        button.addActionListener(this);
        text = new JTextArea(10, 20);
        text.setLineWrap(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        check.addItemListener(this);

//        check.setSelected(true);
//        check.setSelected(false);

        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane scrollList = new JScrollPane(list);
        scrollList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        panel.add(scroller);
        panel.add(scrollList);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.NORTH, check);
        frame.setSize(350, 300);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        text.append("button clicked \n");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String onOrOff = "off";
        if (check.isSelected()) onOrOff = "on";
        System.out.println("Check box is " + onOrOff);
        text.append("Check box is " + onOrOff + "\n");

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selection = (String) list.getSelectedValue();
            text.append(selection + "\n");
            System.out.println(selection);
        }
    }
}
