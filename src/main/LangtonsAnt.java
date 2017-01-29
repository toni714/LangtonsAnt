package main;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.JCheckBoxMenuItem;

import drawing.DrawingEngine;
import drawing.GameDrawingManager;
import events.MyActionListener;
import events.MyEvent;
import graphics.WindowManager;
import units.Menu;
import graphics.SwingWindowManager;

public class LangtonsAnt {
	WindowManager windowManager;
	GameThread gameThread;
	public GameDrawingManager gameDrawingManager;

	public LangtonsAnt(DrawingEngine engine) {
		GC.menus = new HashMap<String, Menu>();
		switch (engine) {
		case SWING:
			System.out.println("Setting drawing Mode to Swing");
			windowManager = new SwingWindowManager(this, "Langtons Ant", 800, 600, Color.WHITE);
			addMenusToBar();

			break;
		default:
			System.out.println("Unknown Graphics Engine");
			break;
		}
		gameDrawingManager = new GameDrawingManager(windowManager.getDrawingManager());
		gameThread = new GameThread(windowManager.getDrawingManager());
	}

	public void handleQuitRequest() {
		gameThread.pauseSim();
		if (windowManager.createYNDialogue("Do You really want to Quit?", "Quit?") == true) {
			sendCloseRequest();
			windowManager.closeWindow();
		} else {
			gameThread.resumeSim();
		}
	}

	void addMenusToBar() {
		windowManager.addMenuToBar(Constants.OPTIONS_MENU);
		// TODO make independent
		windowManager.addCheckboxToMenu(Constants.SETTINGS_ITEM, new MyActionListener() {
			@Override
			public void action(MyEvent event) {
				Menu selectedMenu = GC.menus.get(Constants.SETTINGS_ITEM);
				if (selectedMenu != null) {
					selectedMenu.setVisible(((JCheckBoxMenuItem) event.getSource()).getState());
				}
			}
		}, false, Constants.OPTIONS_MENU);

	}

	private void sendCloseRequest() {
		gameThread.setRunning(false);
		try {
			gameThread.join();
		} catch (InterruptedException e) {
		}
		System.out.println("Game Thread ended");
	}

	public void start() {
		System.out.println("Starting Game Tread");
		gameThread.start();
	}
}
