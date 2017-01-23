package main;

import java.awt.Color;

import drawing.DrawingEngine;
import drawing.DrawingManager;
import drawing.SwingDrawingManager;
import graphics.GraphicsManager;
import graphics.SwingGraphicsManager;

public class LangtonsAnt {
	public DrawingManager drawingManager;
	GraphicsManager graphicsManager;
	GameThread thread;

	public LangtonsAnt(DrawingEngine engine) {
		switch (engine) {
		case SWING:
			System.out.println("Seting drawing Mode to Swing");
			graphicsManager = new SwingGraphicsManager(this);
			graphicsManager.createWindow("Langtons Ant", 800, 600, Color.WHITE);
			drawingManager = new SwingDrawingManager(((SwingGraphicsManager) graphicsManager).getSurface());
			break;
		default:
			System.out.println("Unknown Graphics Engine");
			break;
		}
		thread = new GameThread(drawingManager);
	}

	public void sendCloseRequest() {
		thread.setRunning(false);
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
		System.out.println("Game Thread ended");
	}

	public void start() {
		System.out.println("Starting Game Tread");
		thread.start();
	}
}
