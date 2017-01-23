package main;

import java.util.ArrayList;

import ant.Ant;
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
		System.out.println("Entering Gameloop");
		while(running){
			//update();
			drawingManager.draw();
		}
	}
	
	public void setRunning(boolean running){
		this.running=running;
	}
	
	void init(){
		GC.rules=GC.DEFAULT_RULES;

		//create field
		//GC.field=
		
		//create ants
		GC.ants=new ArrayList<Ant>();
		
	}

}
