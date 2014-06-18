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
    Grid grid;
    Timer animator;

    public Core(Grid grid){
        this.grid = grid;
    }

    public void tick() {
        ArrayList<Point> cellsToFlip = new ArrayList<Point>();
        for(int i = 1; i < grid.getGrid().length - 1; i++) {
            for(int j = 1; j < grid.getGrid()[0].length - 1; j++) {
                if(flipCalculate(i, j))
                    cellsToFlip.add(new Point(i, j));
            }
        }
        for(int i = 0; i < cellsToFlip.size(); i++) {
            grid.getGrid()[cellsToFlip.get(i).x][cellsToFlip.get(i).y].flipState();
        }
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

    public boolean flipCalculate(int i, int j) {
        int nNeighbors = 0;
        if(grid.getGrid()[i+1][j+1].getState())
            nNeighbors++;
        if(grid.getGrid()[i+1][j].getState())
            nNeighbors++;
        if(grid.getGrid()[i+1][j-1].getState())
            nNeighbors++;
        if(grid.getGrid()[i][j+1].getState())
            nNeighbors++;
        if(grid.getGrid()[i][j-1].getState())
            nNeighbors++;
        if(grid.getGrid()[i-1][j+1].getState())
            nNeighbors++;
        if(grid.getGrid()[i-1][j].getState())
            nNeighbors++;
        if(grid.getGrid()[i-1][j-1].getState())
            nNeighbors++;
        return (grid.getGrid()[i][j].getState() && (nNeighbors < 2 || nNeighbors > 3))
                || (!grid.getGrid()[i][j].getState() && nNeighbors == 3);
    }
}
