package gameOfLife.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel implements MouseMotionListener{
	public double mouseX = 0;
    public double mouseY = 0;

	public GridPanel(){
		super();
		addMouseMotionListener(this);
	}

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        gridSetup(g2d);
    }

    public void gridSetup(Graphics2D g2d){
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));

        for(int x = 0; x <= 800; x += 10){
        	g2d.drawLine(x,0,x,600);
        }

        for(int y = 0; y <= 600; y += 10){
        	g2d.drawLine(0,y,800,y);
        }
    }

    public void mouseMoved(MouseEvent e) { // this is called every tick the mouse is moved.
        mouseX = e.getPoint().x;
        mouseY = 600 - e.getPoint().y;
        this.repaint();
        System.out.println(((int)(mouseX/10) + 1) + ", " + ((int)(mouseY/10) + 1));
    }

    public void mouseDragged(MouseEvent evt) {
        //Useless
    }
}