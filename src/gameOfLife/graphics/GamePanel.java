package gameOfLife.graphics;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import gameOfLife.core.Grid;

/**
 * Created by Philip on 6/15/14.
 */
public class GamePanel extends JPanel{

    private Dimension size;
    private JLabel animatorLabel, animationStatusLabel, templateLabel;
    private JButton tickButton, clearButton, animatorButton, stopAnimationButton, gridSizeButton, about, loadTemplateButton;
    public JToggleButton magicTouchButton;
    private JSlider tpsSlider;
    private JList<String> templateList;
    public int gridSize = 1;

    public GamePanel(Dimension size) {
        super();
        this.size = size;
        this.setLayout(null);
        setTickButton();
        setClearButton();
        setTpsSlider();
        setAnimatorButton();
        setStopAnimationButton();
        setAnimationStatusLabel();
        setHelpAbout();
        setMagicTouchButton();
        setTemplateLabel();
        setTemplateList();
        setLoadTemplateButton();
        setGridSizeButton();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        positionDisplaySetup(g2d);
        paintSeparators(g2d, 53);
        paintSeparators(g2d, 105);
        paintSeparators(g2d, 325);
        paintSeparators(g2d, 385);
        paintSeparators(g2d, 545);
        paintVerticalSeparators(g2d, 72, 4, 53);
    }

    public void paintVerticalSeparators(Graphics2D g2d, int x, int y1, int y2){
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x, y1, x, y2);
        g2d.fillOval(x - 4, y1 - 4, 8, 8);
    }

    public void paintSeparators(Graphics2D g2d, int y){
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(5, y, 170, y);
        g2d.fillOval(1, y - 4, 8, 8);
        g2d.fillOval(166, y - 4, 8, 8);
    }

    public Dimension getPreferredSize() {
        return size;
    }

    public void positionDisplaySetup(Graphics2D g2d) {  // this set up the x and y position display
        g2d.setColor(Color.BLACK);
        String posiX = "X: " + Client.gridPanel.getMouseX();
        String posiY = "Y: " + Client.gridPanel.getMouseY();
        Font font = new Font("Eras Bold ITC", Font.PLAIN, 20);
        g2d.setFont(font);
        // ^ those two lines make it look pretty but really really slow, to be fixed.
        g2d.drawString(posiX,10,20);
        g2d.drawString(posiY,10,40);
    }

    public void setAnimatorButton(){
        animatorButton = new JButton("Animate");
        animatorButton.setBounds(5, 200, 165, 40);
        animatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client.core.stopAnimation();
                Client.core.animate(tpsSlider.getValue());
                animationStatusLabel.setText("Animating at " + tpsSlider.getValue() + " ms/t");
                animationStatusLabel.setBounds(12, 280, 165, 40);
                repaint();
            }
        });
        this.add(animatorButton);

        animatorLabel = new JLabel("    Milliseconds Per Tick");
        animatorLabel.setBounds(5, 120, 165, 30);
        this.add(animatorLabel);
    }

    public void setStopAnimationButton(){
        stopAnimationButton = new JButton("Stop");
        stopAnimationButton.setBounds(5,240,165,40);
        stopAnimationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client.core.stopAnimation();
                animationStatusLabel.setText("Animation Stopped");
                animationStatusLabel.setBounds(25,280,165,40);
                repaint();
            }
        });
        this.add(stopAnimationButton);
    }

    public void setAnimationStatusLabel(){
        animationStatusLabel = new JLabel("Animation Stopped");
        animationStatusLabel.setBounds(25,280,165,40);
        this.add(animationStatusLabel);
    }

    public void setTpsSlider(){
        tpsSlider = new JSlider(25, 2000, 1000);
        tpsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                animatorButton.repaint();
            }
        });
        tpsSlider.setBounds(0, 140, 175, 60);
        tpsSlider.setMajorTickSpacing(1975);
        tpsSlider.setMinorTickSpacing(79);
        tpsSlider.setPaintTicks(true);
        tpsSlider.setPaintLabels(true);
        this.add(tpsSlider);
    }

    public void setTickButton(){
        tickButton = new JButton("Tick");
        tickButton.setBounds(5,60,80,40);
        tickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client.core.tick();
                Client.gridPanel.repaint();
            }
        });
        this.add(tickButton);
    }

    public void setClearButton(){
        clearButton = new JButton("Clear");
        clearButton.setBounds(90,60,80,40);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client.core.grid = new Grid(80, 60);
                Client.gridPanel.repaint();
            }
        });
        this.add(clearButton);
    }

    public void setHelpAbout(){
        about = new JButton("About & Help");
        about.setBounds(5,555,165,45);
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame about = new JFrame("About");
                about.setVisible(true);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                about.setBounds( (int)(screenSize.getWidth()/2 - 100),
                        (int)(screenSize.getHeight()/2 - 50),
                        200,
                        100 );
                JPanel aboutPanel = new JPanel();
                aboutPanel.setBounds(0,20,about.getPreferredSize().width,about.getPreferredSize().height);
                JLabel helpLabel = new JLabel("  Philip Xu 2014  ");
                helpLabel.setBounds(0,0,about.getPreferredSize().width,about.getPreferredSize().height);
                JLabel helpLabel2 = new JLabel("  James Liu 2014  ");
                helpLabel.setBounds(0,0,about.getPreferredSize().width,about.getPreferredSize().height);
                JLabel helpLabel3 = new JLabel("Daniel Boerlage 2014");
                helpLabel.setBounds(0,0,about.getPreferredSize().width,about.getPreferredSize().height);
                aboutPanel.add(helpLabel);
                aboutPanel.add(helpLabel2);
                aboutPanel.add(helpLabel3);
                about.add(aboutPanel);
                about.setResizable(false);
            }
        });

        this.add(about);
    }

    public void setMagicTouchButton(){
        magicTouchButton = new JToggleButton("Magic Touch");
        magicTouchButton.setBounds(5,335,165,43);
        magicTouchButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ev) {
                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    Client.gridPanel.magicTouchOn = true;
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    Client.gridPanel.magicTouchOn = false;
                }
            }
        });
        this.add(magicTouchButton);
    }

    public void setTemplateLabel(){
        templateLabel = new JLabel("     Templates");
        templateLabel.setBounds(35,385,165,40);
        this.add(templateLabel);
    }

    public void setTemplateList(){
        String[] data = {"Glider", "Glider Gun", "Spaceship", "Pulsar"};
        templateList = new JList<String>(data);
        templateList.setBounds(5, 420, 165, 68);
        this.add(templateList);
    }

    public void setLoadTemplateButton(){
        loadTemplateButton = new JButton("Load Template");
        loadTemplateButton.setBounds(5,495,165,43);
        loadTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Client.grid.loadTemplate(templateList.getSelectedValue());
                Client.gridPanel.repaint();
            }
        });
        this.add(loadTemplateButton);
    }

    public void setGridSizeButton(){
        gridSizeButton = new JButton("80 x 60");
        gridSizeButton.setBounds(80,3,90,45);
        gridSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gridSize == 1){
                    Client.core.grid = new Grid(160, 120);
                    Client.gridPanel.reset();
                    gridSizeButton.setText("160 x 120");
                    repaint();
                    gridSize++;
                }else if(gridSize == 2){
                    Client.core.grid = new Grid(40, 30);
                    Client.gridPanel.reset();
                    gridSizeButton.setText("40 x 30");
                    repaint();
                    gridSize++;
                }else {
                    Client.core.grid = new Grid(80, 60);
                    Client.gridPanel.reset();
                    gridSizeButton.setText("80 x 60");
                    repaint();
                    gridSize = 1;
                }
                Client.gridPanel.reset();
            }
        });
        this.add(gridSizeButton);
    }
}
