package ui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyFrame() {

        this.setTitle("Niche Calendar"); //rename it
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(840,600);
        this.setVisible(true);

        //set up basic stuff
        ImageIcon image = new ImageIcon("Icon.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.pink);
    }



}
