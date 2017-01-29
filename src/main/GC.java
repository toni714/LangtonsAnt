package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import ant.Ant;
import components.Field;
import units.Direction;
import units.Menu;
import units.Rule;
import units.Vector2D;

public class GC {
	public static final Direction DEFAULT_DIRECTION = Direction.RIGHT;
	public static final Rule[] DEFAULT_RULES = { Rule.L, Rule.L, Rule.L, Rule.L, Rule.L, Rule.L, Rule.L, Rule.L, Rule.R, Rule.R, Rule.R, Rule.R};
	public static Rule[] rules;
	public static Color[] colorMap;
	public static Field field;
	public static ArrayList<Ant> ants;
	public static int antSize;
	public static boolean initDone = false;
	public static HashMap<String, Menu> menus;
	public static Vector2D surfaceSize;
	public static int fieldBuffer;

}
