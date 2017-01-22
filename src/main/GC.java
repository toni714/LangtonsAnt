package main;

import units.Direction;
import units.Rule;

public class GC {

	public static final Direction DEFAULT_DIRECTION = Direction.UP;
	public static Rule[] rules;
	public static int[][] field;
	
	public static void increment(int x, int y) {
		field[x][y]++;
		if(field[x][y]>=rules.length){
			field[x][y]=0;
		}
		
	}

}
