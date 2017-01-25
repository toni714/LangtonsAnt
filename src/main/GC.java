package main;

import java.awt.Color;
import java.util.ArrayList;

import ant.Ant;
import components.Field;
import units.Direction;
import units.Rule;

public class GC {

	public static final Direction DEFAULT_DIRECTION = Direction.UP;
	public static final Rule[] DEFAULT_RULES = {Rule.L, Rule.R, Rule.L, Rule.R,Rule.L, Rule.R, Rule.L, Rule.R};
	public static Rule[] rules;
	public static Color[] colorMap;
	public static Field field;
	public static ArrayList<Ant> ants;
	public static int antSize;
	public static boolean initDone=false;
	
	

}
