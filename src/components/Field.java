package components;

import main.GC;
import units.Vector2D;

public class Field {
	public Vector2D pos;
	int width;
	int height;
	public int sqSize;
	int xSquares;
	int ySquares;
	public int[][] colors;
	
	public Field(int x, int y, int width, int height, int sqSize) {
		pos=new Vector2D(x, y);
		this.width=width;
		this.height=height;
		this.sqSize=sqSize;
		xSquares=(int)(width/(float)sqSize);
		ySquares=(int)(height/(float)sqSize);
		colors=new int[xSquares][ySquares];
	}
	
	public void increment(int x, int y) {
		colors[x][y]++;
		if(colors[x][y]>=GC.rules.length){
			colors[x][y]=0;
		}
		
	}
	
}
