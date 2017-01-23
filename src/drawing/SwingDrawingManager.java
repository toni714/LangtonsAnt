package drawing;

import java.awt.Color;
import java.awt.Graphics;
//import java.util.Random;

import javax.swing.JComponent;

import ant.Ant;
import main.GC;

public class SwingDrawingManager implements DrawingManager {
	JComponent surface;

	public SwingDrawingManager(JComponent surface) {
		this.surface = surface;
	}

	@Override
	public void draw() {
		surface.repaint();
	}

	public void paint(Graphics g) {
		// g.drawRect(0, 10, 50, 100);
		if (GC.initDone) {
			drawField(g);
			drawAnts(g);
		}
	}

	public void drawField(Graphics g) {

		// Random rnd = new Random();
		for (int x = 0; x < GC.field.colors.length; x++) {
			for (int y = 0; y < GC.field.colors[x].length; y++) {
				// g.setColor(Color.getHSBColor(360 * (GC.field.colors[x][y]
				// / (float) GC.rules.length), 1, 1));
				float percent = 1 - (GC.field.colors[x][y] / (float) (GC.rules.length-1));
				g.setColor(new Color(percent, percent, percent));

				// g.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255),
				// rnd.nextInt(255)));
				g.fillRect(GC.field.pos.x + (x * GC.field.sqSize), GC.field.pos.y + (y * GC.field.sqSize),
						GC.field.sqSize, GC.field.sqSize);
			}
		}
	}

	public void drawAnts(Graphics g) {
		for (Ant ant : GC.ants) {
			g.setColor(ant.color);
			g.drawRect(GC.field.pos.x + (ant.pos.x * GC.field.sqSize), GC.field.pos.y + (ant.pos.y * GC.field.sqSize),
					GC.field.sqSize, GC.field.sqSize);
		}
	}

}
