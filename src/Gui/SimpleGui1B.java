package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui1B implements ActionListener {

    JButton button;

    public static void main(String[] args) {
        SimpleGui1B gui = new SimpleGui1B();

        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        button = new JButton("Click me");
        MyDrawPanel panel = new MyDrawPanel();

        button.addActionListener(this);
        button.setSize(300, 30);
        button.setLocation(0,270);

        frame.getContentPane().add(button);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setText("I've been clicked");
    }
}
