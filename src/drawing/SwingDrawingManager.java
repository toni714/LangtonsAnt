package drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import main.LangtonsAnt;

public class SwingDrawingManager implements EngineDrawingManager {
	LangtonsAnt controler;
	JComponent surface;
	Graphics currentGraphics;

	public SwingDrawingManager(LangtonsAnt controler, JComponent surface) {
		this.controler = controler;
		this.surface = surface;
	}

	@Override
	public void draw() {
		// Issue the Drawing
		// Indirect handover to Game via paint(g);
		surface.repaint();
	}

	public void paint(Graphics g) {
		currentGraphics = g;
		// Handover to Game
		controler.gameDrawingManager.draw();
	}

	
	@Override
	public void fillRect(int x, int y, int width, int height, Color color) {
		currentGraphics.setColor(color);
		currentGraphics.fillRect(x, y, width, height);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, Color color) {
		currentGraphics.setColor(color);
		currentGraphics.drawRect(x, y, width, height);
	}
}
