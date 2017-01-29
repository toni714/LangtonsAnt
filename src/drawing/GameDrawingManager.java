package drawing;

import ant.Ant;
import main.GC;
import units.Menu;

public class GameDrawingManager {
	
	EngineDrawingManager engine; 
	
	public GameDrawingManager(EngineDrawingManager engine) {
		this.engine=engine;
	}
	
	public void draw(){
		if (GC.initDone) {
			drawField();
			drawAnts();
			//drawMenus();
		}
	}
	
	public void drawMenus(){
		for(Menu menu:GC.menus.values()){
			if(menu.isVisible){
				menu.draw();
			}
		}
	}

	public void drawField() {
		for (int x = 0; x < GC.field.colors.length; x++) {
			for (int y = 0; y < GC.field.colors[x].length; y++) {
				engine.fillRect(GC.field.pos.x + (x * GC.field.sqSize), GC.field.pos.y + (y * GC.field.sqSize),
						GC.field.sqSize, GC.field.sqSize, GC.colorMap[GC.field.colors[x][y]]);
			}
		}
	}

	public void drawAnts() {
		for (Ant ant : GC.ants) {
			engine.drawRect(GC.field.pos.x + (ant.pos.x * GC.field.sqSize), GC.field.pos.y + (ant.pos.y * GC.field.sqSize),
					GC.field.sqSize, GC.field.sqSize, ant.color);
		}
	}
}
