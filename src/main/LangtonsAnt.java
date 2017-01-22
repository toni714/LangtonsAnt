package main;

import javax.swing.JComponent;

import drawing.DrawingEngine;
import drawing.DrawingManager;
import drawing.SwingDrawingManager;

public class LangtonsAnt {
	DrawingManager drawingManager;
	GameThread thread;
	
	public LangtonsAnt(DrawingEngine engine){
		switch(engine){
		case SWING:
			System.out.println("Seting drawing Mode to Swing");
			break;
		default:
			System.out.println("Unknown Graphics Engine");
			break;
		}
	}
	
	public void init(JComponent surface){
		drawingManager=new SwingDrawingManager(surface);
		thread=new GameThread(drawingManager);
	}
	
	public DrawingManager getDrawingManager(){
		return drawingManager;
	}

	public void sendCloseRequest() {
		thread.setRunning(false);
		try {
			thread.join();
		} catch (InterruptedException e) {}
		System.out.println("Game Thread ended");
	}

	public void start() {
		System.out.println("Starting Game Tread");
		thread.start();
	}
}
