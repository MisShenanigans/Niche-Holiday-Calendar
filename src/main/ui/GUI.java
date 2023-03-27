package ui;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class GUI {

    ImageIcon icon = new ImageIcon("Icon.jpg");

    public GUI() {

        JLabel label = new JLabel();
        label.setText("Check out the Niche Calendar");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0x00FF00));
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));


        JFrame frame = new JFrame(); //create the frame
        frame.setTitle("Niche Calendar"); //rename it
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(840, 600);
        frame.setVisible(true);


        frame.getContentPane().setBackground(Color.pink);

        frame.add(label);



    }



}
