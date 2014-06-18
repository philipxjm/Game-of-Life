package gameOfLife.graphics;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by Philip Xu on 6/15/14.
 */
public class GridPanel extends JPanel implements MouseMotionListener, MouseListener {
    protected Dimension size;
    private int mouseX, mouseY;
    private int cellPix;
    boolean magicTouchOn;

    public GridPanel(Dimension d) {
        super();
        this.size = d;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        cellPix = 10;
        mouseX = 0;
        mouseY = 0;
        magicTouchOn = false;

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

        this.setCursor(blankCursor);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gridBackgroundColorSetup(g2d);
        gridSetup(g2d);
        paintCells(g2d);
        paintCursor(g2d);
    }

    public void reset(){
        cellPix = 800 / Client.core.grid.getWidth();
        repaint();
    }

    public void paintCursor(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.fillRect(
                mouseX * cellPix,
                600 - (mouseY + 1) * cellPix,
                cellPix,
                cellPix);
    }

    public void paintCells(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        for(int i = 0; i < Client.core.grid.getWidth(); i++) {
            for(int j = 0; j < Client.core.grid.getHeight(); j++) {
                if(Client.core.grid.getState(i, j))
                    g2d.fillRect(
                            i * cellPix,
                            600 - (j + 1) * cellPix,
                            cellPix,
                            cellPix);
            }
        }
    }

    public void gridSetup(Graphics2D g2d) {//draws the x,y axis
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        for(int i = 0; i <= this.getPreferredSize().width; i += cellPix){
            g2d.drawLine(
                    i,
                    (this.getPreferredSize().height),
                    i,
                    0
            );
        }

        for(int i = 0; i <= this.getPreferredSize().height; i += cellPix){
            g2d.drawLine(
                    0,
                    i,
                    (this.getPreferredSize().width),
                    i
            );
        }
    }

    public void gridBackgroundColorSetup(Graphics2D g2d) {//paints the background of a color.
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,this.getPreferredSize().width,this.getPreferredSize().height);
    }

    public void mouseMoved(MouseEvent e) { // this is called every tick the mouse is moved.
        mouseX = (e.getPoint().x - 1)/cellPix;
        mouseY = Client.core.grid.getHeight() - (((e.getPoint().y - 3)/cellPix)) - 1;
        if(magicTouchOn)
            Client.core.grid.setState(mouseX, mouseY, true);
        Client.gridPanel.repaint();
        Client.gamePanel.repaint();
    }

    public void mouseDragged(MouseEvent evt) {
        //Useless
    }

    public Dimension getPreferredSize() {
        return size;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(magicTouchOn){
            magicTouchOn = false;
            Client.gamePanel.magicTouchButton.setSelected(false);
        }
        else
            Client.core.grid.flipState(mouseX, mouseY);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
