package Core;

import Graphics.Client;

/**
 * Created by Philip Xu on 6/15/14.
 */
public class Grid {
    private Cell[][] grid;

    public Grid(int x, int y){
        grid = new Cell[x][y];
        initGrid(x, y);
    }

    public void initGrid(int width, int height) {
        grid = new Cell[width + 2][height + 2];
        for(int i = 0; i < width + 2; i++) {
            for(int j = 0; j < height + 2; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void loadTemplate(String str){
        initGrid(Client.gridX,Client.gridY);
        if(str.equals("Glider")){
            grid[10][10].setState(true);
            grid[11][11].setState(true);
            grid[12][9].setState(true);
            grid[12][10].setState(true);
            grid[12][11].setState(true);
        }
        else if(str.equals("Glider Gun") && Client.gamePanel.gridSize != 3){
            int s = 10;
            int d = 30;
            grid[s][d+2].setState(true);
            grid[s][d+3].setState(true);
            grid[s+1][d+2].setState(true);
            grid[s+1][d+3].setState(true);
            grid[s+8][d+3].setState(true);
            grid[s+8][d+4].setState(true);
            grid[s+9][d+2].setState(true);
            grid[s+9][d+4].setState(true);
            grid[s+10][d+2].setState(true);
            grid[s+10][d+3].setState(true);
            grid[s+16][d+4].setState(true);
            grid[s+16][d+5].setState(true);
            grid[s+16][d+6].setState(true);
            grid[s+17][d+4].setState(true);
            grid[s+18][d+5].setState(true);
            grid[s+22][d+1].setState(true);
            grid[s+22][d+2].setState(true);
            grid[s+23][d+0].setState(true);
            grid[s+23][d+2].setState(true);
            grid[s+24][d+0].setState(true);
            grid[s+24][d+1].setState(true);
            grid[s+24][d+12].setState(true);
            grid[s+24][d+13].setState(true);
            grid[s+25][d+12].setState(true);
            grid[s+25][d+14].setState(true);
            grid[s+26][d+12].setState(true);
            grid[s+34][d].setState(true);
            grid[s+34][d+1].setState(true);
            grid[s+35][d].setState(true);
            grid[s+35][d+1].setState(true);
            grid[s+35][d+7].setState(true);
            grid[s+35][d+8].setState(true);
            grid[s+35][d+9].setState(true);
            grid[s+36][d+7].setState(true);
            grid[s+37][d+8].setState(true);
        }
        else if(str.equals("Spaceship") && Client.gamePanel.gridSize != 3){
            int s = 74;
            int d = 30;
            grid[s][d].setState(true);
            grid[s][d + 1].setState(true);
            grid[s][d + 2].setState(true);
            grid[s + 1][d].setState(true);
            grid[s + 1][d + 3].setState(true);
            grid[s + 2][d].setState(true);
            grid[s + 3][d].setState(true);
            grid[s + 4][d + 1].setState(true);
            grid[s + 4][d + 3].setState(true);
        }
        else if(str.equals("Pulsar")){
            int s = 40;
            int d = 30;
            if(Client.gamePanel.gridSize == 3){
                s = 20;
                d = 15;
            }
            grid[s + 1][d+2].setState(true);
            grid[s + 1][d+3].setState(true);
            grid[s + 1][d+4].setState(true);
            grid[s + 1][d-2].setState(true);
            grid[s + 1][d-3].setState(true);
            grid[s + 1][d-4].setState(true);

            grid[s - 1][d+2].setState(true);
            grid[s - 1][d+3].setState(true);
            grid[s - 1][d+4].setState(true);
            grid[s - 1][d-2].setState(true);
            grid[s - 1][d-3].setState(true);
            grid[s - 1][d-4].setState(true);

            grid[s+2][d + 1].setState(true);
            grid[s+3][d + 1].setState(true);
            grid[s+4][d + 1].setState(true);
            grid[s-2][d + 1].setState(true);
            grid[s-3][d + 1].setState(true);
            grid[s-4][d + 1].setState(true);

            grid[s+2][d - 1].setState(true);
            grid[s+3][d - 1].setState(true);
            grid[s+4][d - 1].setState(true);
            grid[s-2][d - 1].setState(true);
            grid[s-3][d - 1].setState(true);
            grid[s-4][d - 1].setState(true);

            grid[s + 6][d+2].setState(true);
            grid[s + 6][d+3].setState(true);
            grid[s + 6][d+4].setState(true);
            grid[s + 6][d-2].setState(true);
            grid[s + 6][d-3].setState(true);
            grid[s + 6][d-4].setState(true);

            grid[s - 6][d+2].setState(true);
            grid[s - 6][d+3].setState(true);
            grid[s - 6][d+4].setState(true);
            grid[s - 6][d-2].setState(true);
            grid[s - 6][d-3].setState(true);
            grid[s - 6][d-4].setState(true);

            grid[s + 2][d + 6].setState(true);
            grid[s + 3][d + 6].setState(true);
            grid[s + 4][d + 6].setState(true);
            grid[s - 2][d + 6].setState(true);
            grid[s - 3][d + 6].setState(true);
            grid[s - 4][d + 6].setState(true);

            grid[s + 2][d - 6].setState(true);
            grid[s + 3][d - 6].setState(true);
            grid[s + 4][d - 6].setState(true);
            grid[s - 2][d - 6].setState(true);
            grid[s - 3][d - 6].setState(true);
            grid[s - 4][d - 6].setState(true);
        }
    }

    public boolean getState(int x, int y) {
        return grid[x][y].getState();
    }

    public Cell[][] getGrid(){
        return grid;
    }

}
