package ant;

import units.Vector2D;
import units.Direction;

import java.awt.Color;

import main.GC;

public class Ant {
	public Vector2D pos;
	Direction facing;
	public Color color;
	
	public Ant(int x, int y, Color color){
		pos=new Vector2D(x, y);
		facing=GC.DEFAULT_DIRECTION;
		this.color=color;
	}

	public void update(){
		GC.rules[GC.field.colors[pos.x][pos.y]].action(this);
		colorLeavingTile();
		step();
	}

	public void step(){
		switch(facing){
		case DOWN:
			pos.y++;
			if(pos.y>=GC.field.colors[0].length){
				pos.y=0;
			}
			break;
		case LEFT:
			pos.x--;
			if(pos.x<0){
				pos.x=GC.field.colors.length-1;				
			}
			break;
		case RIGHT:
			pos.x++;
			if(pos.x>=GC.field.colors.length){
				pos.x=0;
			}
			break;
		case UP:
			pos.y--;
			if(pos.y<0){
				pos.y=GC.field.colors[0].length-1;				
			}
			break;
		default:
			System.out.println("Can not move in unknown direction");
			break;
		}
	}
	
	public void colorLeavingTile(){
		GC.field.increment(pos.x, pos.y);
	}

	public void rotateLeft() {
		switch(facing){
		case DOWN:
			facing=Direction.RIGHT;
			break;
		case LEFT:
			facing=Direction.DOWN;
			break;
		case RIGHT:
			facing=Direction.UP;
			break;
		case UP:
			facing=Direction.LEFT;
			break;
		}
	}

	public void rotateRight() {
		switch(facing){
		case DOWN:
			facing=Direction.LEFT;
			break;
		case LEFT:
			facing=Direction.UP;
			break;
		case RIGHT:
			facing=Direction.DOWN;
			break;
		case UP:
			facing=Direction.RIGHT;
			break;
		}
	}
}
