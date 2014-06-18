package gameOfLife.graphics;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import gameOfLife.core.*;

/**
 * Created by Philip Xu on 6/15/14.
 */
public class Client {

    public static Grid grid;
    public static Core core;
    public static int gridX;
    public static int gridY;

    public static JFrame frame;
    public static GridPanel gridPanel;
    public static GamePanel gamePanel;
    public static Dimension screenSize;

    public static void runClient() { //The client frame runs the frame that hosts the panels
        frame = new JFrame("Game of Life");
        gameSetup();
        frameSetup();
        panelSetup();
        addPanels();
        frame.repaint();
    }

    public static void gameSetup(){
        gridX = 80;
        gridY = 60;
        grid = new Grid(80,60);
        core = new Core(grid);
    }

    public static void frameSetup() { //set up the frame
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("res/images/icon.gif");
        frame.setIconImage(icon.getImage());
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds( (int)(screenSize.getWidth()/2 - 500),
                (int)(screenSize.getHeight()/2 - 325),
                1000,
                632 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void panelSetup() { //set up the various panels
        gridPanel = new GridPanel(new Dimension(801, 601));
        gridPanel.setBounds(5,
                            5,
                            gridPanel.getPreferredSize().width,
                            gridPanel.getPreferredSize().height);
        gamePanel = new GamePanel(new Dimension(801, 601));
        gamePanel.setBounds(815,
                            5,
                            gamePanel.getPreferredSize().width,
                            gamePanel.getPreferredSize().height);
    }

    public static void addPanels() { //add the panels to frame
        frame.add(gridPanel);
        frame.add(gamePanel);
    }

}
