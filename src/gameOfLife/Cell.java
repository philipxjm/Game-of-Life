package gameOfLife;

import java.util.ArrayList;
import java.awt.Point;

/**
 * @author Daniel Boelage
 */
public class Cell {

	public static Cell[][] cells;
	public boolean state;

	private Cell(boolean state) {
		this.state = state;
	}

	public static void initArray(int width, int height) {
		cells = new Cell[width + 2][height + 2];
		for(int i = 0; i < width + 2; i++) {
			for(int j = 0; j < height + 2; j++) {
				cells[i][j] = new Cell(false);
			}
		}
	}

	public static void tick() {
		ArrayList<Point> cellsToFlip = new ArrayList<Point>();
		for(int i = 1; i < cells.length - 1; i++) {
			for(int j = 1; j < cells[0].length - 1; j++) {
				if(cells[i][j].flipCalculate(i, j))
					cellsToFlip.add(new Point(i, j));
			}
		}
		for(int i = 0; i < cellsToFlip.size(); i++)
			cells[cellsToFlip.get(i).x][cellsToFlip.get(i).y].state ^= true;	
	}

	public static void setState(int i, int j, boolean state) {
		cells[i][j].state = state;
	}

	public static boolean getState(int i, int j) {
		return cells[i][j].state;
	}

	public static int getWidth() {
		return cells.length - 2;
	}

	public static int getHeight() {
		return cells[0].length - 2;
	}

	private boolean flipCalculate(int i, int j) {
		int nNeighbors = 0;
		if(cells[i+1][j+1].state)
			nNeighbors++;
		if(cells[i+1][j].state)
			nNeighbors++;
		if(cells[i+1][j-1].state)
			nNeighbors++;
		if(cells[i][j+1].state)
			nNeighbors++;
		if(cells[i][j-1].state)
			nNeighbors++;
		if(cells[i-1][j+1].state)
			nNeighbors++;
		if(cells[i-1][j].state)
			nNeighbors++;
		if(cells[i-1][j-1].state)
			nNeighbors++;
		return (state && (nNeighbors < 2 || nNeighbors > 3)) || (!state && nNeighbors == 3);
	}
}