package main;

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
		//init();
		System.out.println("In Game Thread");
		while(running){
			//update();
			drawingManager.draw();
		}
	}
	
	public void setRunning(boolean running){
		this.running=running;
	}
	
	/*
	public void stop() {
		running=false;
	}*/

}
