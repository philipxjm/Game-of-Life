package gameOfLife.core;

import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileHandler {

	public static Grid read(File file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String[] dims = in.readLine().split(" ");
			Grid grid = new Grid(new Integer(dims[0]), new Integer(dims[1]));
			String line;
			for(int j = grid.getHeight()-1; (line = in.readLine()) != null; j--) {
				for(int i = 0; i < line.length(); i++) {
					if(line.charAt(i) == '*')
						grid.setState(i, j, true);
					else
						grid.setState(i, j, false);
				}
			}
			return grid;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void write(Grid grid, File file) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			out.println(grid.getWidth() + " " + grid.getHeight());
			for(int j = grid.getWidth()-1; j >= 0; j--) {
				for(int i = 0; i < grid.getHeight(); i++) {
					if(grid.getState(i, j))
						out.print('*');
					else
						out.print('`');
				}
				out.print("\n");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}