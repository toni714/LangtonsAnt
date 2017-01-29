package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import ant.Ant;
import components.Field;
import drawing.EngineDrawingManager;

public class GameThread extends Thread {

	boolean running;
	boolean paused;
	EngineDrawingManager drawingManager;

	public GameThread(EngineDrawingManager drawingManager) {
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
				for (int i = 0; i < 5000000; i++) {
					update();
				}
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
		// initialize rules
		GC.rules = GC.DEFAULT_RULES;

		// initialize colors
		GC.colorMap = new Color[GC.rules.length+1];
		// Generate Grayscale Colors
		for (int i = 0; i < GC.rules.length+1; i++) {
			float percentile = 1 - (i / (float) (GC.rules.length));
			GC.colorMap[i] = new Color(percentile, percentile, percentile);
		}
		// Generate random Colors
		/*
		 * Random rnd = new Random(System.currentTimeMillis()); for (int i = 0;
		 * i < GC.colorMap.length; i++) { GC.colorMap[i] = new
		 * Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)); }
		 */

		// create field
		GC.fieldBuffer = 10;
		GC.field = new Field(GC.fieldBuffer, GC.fieldBuffer, GC.surfaceSize.x - (2 * GC.fieldBuffer),
				GC.surfaceSize.y - (4 * GC.fieldBuffer), 4);

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
