package gameOfLife.core;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import gameOfLife.graphics.Client;

/**
 * Created by Philip Xu and Daniel Boerlage on 6/15/14.
 * Credit to James Liu and Daniel Boerlage for concept
 *
 */
public class Core {
    
    public Grid grid;
    private Timer animator;
    private boolean wrapAround;

    public Core(Grid grid){
        this.grid = grid;
        wrapAround = true;
    }

    public void tick() {
        ArrayList<Point> cellsToFlip = new ArrayList<Point>();
        for(int i = 0; i < grid.getWidth(); i++) {
            for(int j = 0; j < grid.getHeight(); j++) {
                if(flipCalculate(i, j))
                    cellsToFlip.add(new Point(i, j));
            }
        }
        for(int i = 0; i < cellsToFlip.size(); i++)
            grid.flipState(cellsToFlip.get(i).x, cellsToFlip.get(i).y);
    }

    private boolean flipCalculate(int i, int j) {
        int right = i+1, left = i-1, top = j+1, bottom = j-1;
        if(wrapAround) {
            if(i == 0)
                left = grid.getWidth() - 1;
            else if(i == grid.getWidth() - 1)
                right = 0;
            if(j == 0)
                bottom = grid.getHeight() - 1;
            else if(j == grid.getHeight() - 1)
                top = 0;
        }
        int nNeighbors = 0;
        if(grid.getState(right, top))
            nNeighbors++;
        if(grid.getState(right, j))
            nNeighbors++;
        if(grid.getState(right, bottom))
            nNeighbors++;
        if(grid.getState(i, top))
            nNeighbors++;
        if(grid.getState(i, bottom))
            nNeighbors++;
        if(grid.getState(left, top))
            nNeighbors++;
        if(grid.getState(left, j))
            nNeighbors++;
        if(grid.getState(left, bottom))
            nNeighbors++;
        return (grid.getState(i, j) && (nNeighbors < 2 || nNeighbors > 3)) || (!grid.getState(i, j) && nNeighbors == 3);
    }

    public void animate(int ms){
        animator = new Timer(ms,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
                Client.gridPanel.repaint();
            }
        });
        animator.start();
    }

    public void stopAnimation(){
        if(animator != null){
            animator.stop();
        }
    }
}
