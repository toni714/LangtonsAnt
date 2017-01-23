package main;

import java.util.ArrayList;

import ant.Ant;
import units.Direction;
import units.Rule;
import units.Vector2D;

public class GC {

	public static final Direction DEFAULT_DIRECTION = Direction.UP;
	public static final Rule[] DEFAULT_RULES = {Rule.L, Rule.R};
	public static Rule[] rules;
	public static int[][] field;
	public static Vector2D fieldPos;
	public static ArrayList<Ant> ants;
	public static int antSize;
	
	public static void increment(int x, int y) {
		field[x][y]++;
		if(field[x][y]>=rules.length){
			field[x][y]=0;
		}
		
	}

}
