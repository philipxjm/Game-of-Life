package gameOfLife.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game{

    static JFrame frame;
    static GridPanel gridPanel;
    static Dimension screenSize;

    public static void run() { //The client frame runs the frame that hosts the panels
        frame = new JFrame("Game of Life");
        frameSetup();
        panelSetup();
        addPanels();
    }

    public static void frameSetup() { //set up the frame
        frame.setLayout(null);
        frame.setVisible(true);
        //frame.setResizable(false);
        //ImageIcon icon = new ImageIcon("res/images/icon/icon.gif");
        //frame.setIconImage(icon.getImage());
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds( (int)(screenSize.getWidth()/2 - 400),
                        (int)(screenSize.getHeight()/2 - 350), 
                         816, 
                         638 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void panelSetup() { //set up the various panels
    	gridPanel = new GridPanel();
    	gridPanel.setBounds(0,0,800,600);
    } 

    public static void addPanels() { //add the panels to frame
    	frame.add(gridPanel);
    }
} 