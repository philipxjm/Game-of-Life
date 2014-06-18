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

    public Core(Grid grid){
        this.grid = grid;
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
        int nNeighbors = 0;
        if(grid.getState(i+1, j+1))
            nNeighbors++;
        if(grid.getState(i+1, j))
            nNeighbors++;
        if(grid.getState(i+1, j-1))
            nNeighbors++;
        if(grid.getState(i, j+1))
            nNeighbors++;
        if(grid.getState(i, j-1))
            nNeighbors++;
        if(grid.getState(i-1, j+1))
            nNeighbors++;
        if(grid.getState(i-1, j))
            nNeighbors++;
        if(grid.getState(i-1, j-1))
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
