package main;

import java.awt.Color;
import java.util.ArrayList;

import ant.Ant;
import components.Field;
import drawing.DrawingManager;

public class GameThread extends Thread{
	
	boolean running;
	DrawingManager drawingManager;
	
	public GameThread(DrawingManager drawingManager) {
		this.drawingManager=drawingManager;
	}
	
	@Override
	public void run() {
		running=true;
		System.out.println("Initializing Game");
		init();
		GC.initDone=true;
		System.out.println("Entering Gameloop");
		while(running){
			update();
			drawingManager.draw();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {}
		}
	}
	
	public void setRunning(boolean running){
		this.running=running;
	}
	
	void update(){
		for(Ant ant:GC.ants){
			ant.update();
		}
	}
	
	void init(){
		GC.rules=GC.DEFAULT_RULES;

		//create field
		GC.field=new Field(10, 10, 580, 580, 4);
		
		//create ants
		GC.ants=new ArrayList<Ant>();
		GC.ants.add(new Ant(GC.field.xSquares/2, GC.field.ySquares/2, Color.CYAN));
	}

}
