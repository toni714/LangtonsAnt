package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import ant.Ant;
import components.Field;
import drawing.DrawingManager;

public class GameThread extends Thread {

	boolean running;
	boolean paused;
	DrawingManager drawingManager;

	public GameThread(DrawingManager drawingManager) {
		this.drawingManager = drawingManager;
	}

	@Override
	public void run() {
		running = true;
		paused = false;
		System.out.println("Initializing Game");
		init();
		GC.initDone = true;
		System.out.println("Entering Gameloop");
		while (running) {
			if (!paused) {
				update();
				drawingManager.draw();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}

		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	void update() {
		for (Ant ant : GC.ants) {
			ant.update();
		}
	}

	void init() {
		GC.rules = GC.DEFAULT_RULES;
		GC.colorMap = new Color[GC.rules.length];
		Random rnd = new Random(System.currentTimeMillis());
		for (int i = 0; i < GC.colorMap.length; i++) {
			GC.colorMap[i] = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
		}
		// create field
		GC.field = new Field(10, 10, 580, 580, 4);

		// create ants
		GC.ants = new ArrayList<Ant>();
		GC.ants.add(new Ant(GC.field.xSquares / 2, GC.field.ySquares / 2, Color.CYAN));
	}

	public void pauseSim() {
		paused = true;
	}

	public void resumeSim() {
		paused = false;
	}

}
