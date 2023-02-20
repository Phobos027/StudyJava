package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextField implements ActionListener {
    JTextField field;
    JLabel label;

    public static void main(String[] args) {
        TextField gui = new TextField();
        gui.go();
    }
    public void go(){
        JFrame frame = new JFrame();
        JPanel panel= new JPanel();
        field = new JTextField(20);
        label = new JLabel("Your name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        field.addActionListener(this);
        field.selectAll();
        field.requestFocus();

        panel.add(label);
        panel.add(field);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(field.getText());
        //field.setText("Anything");
        field.setText("");
    }
}
