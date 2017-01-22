package ant;

import units.Vector2D;
import units.Direction;
import main.GC;

public class Ant {
	Vector2D pos;
	Direction facing;
	
	public Ant(int x, int y){
		pos=new Vector2D(x, y);
		facing=GC.DEFAULT_DIRECTION;
	}

	public void update(){
		GC.rules[GC.field[pos.x][pos.y]].action(this);
		colorLeavingTile();
		step();
	}

	public void step(){
		switch(facing){
		case DOWN:
			pos.y++;
			break;
		case LEFT:
			pos.x--;
			break;
		case RIGHT:
			pos.x++;
			break;
		case UP:
			pos.y--;
			break;
		default:
			System.out.println("Can not move in unknown direction");
			break;
		}
	}
	
	public void colorLeavingTile(){
		GC.increment(pos.x, pos.y);
	}
}
