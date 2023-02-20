package Gui;

import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {
    JFrame frame;
    int x = 20;
    int y = 20;

    public static void main(String[] args) {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setSize(350, 350);
        frame.setVisible(true);

        for (int i = 0; i <= 200; i++) {
            x++;
            y++;
            drawPanel.repaint();
            try {
                Thread.sleep(30);
            } catch (Exception ex){}
        }
    }

    class MyDrawPanel extends JPanel{

        public void paintComponent(Graphics graphics){
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

            graphics.setColor(Color.red);
            graphics.fillOval(x, y, 70, 70);
        }
    }
}
