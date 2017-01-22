package drawing;

import java.awt.Graphics;

import javax.swing.JComponent;

import ant.Ant;
import main.GC;

public class SwingDrawingManager implements DrawingManager{
	JComponent surface;
	public SwingDrawingManager(JComponent surface){
		this.surface=surface;
	}
	
	public void paint(Graphics g){
		System.out.println("Painting");
		g.drawRect(0, 10, 50, 100);
		for(Ant ant:GC.ants){
			drawAnt(g, ant);
		}
	}
	
	@Override
	public void draw() {
		surface.repaint();
	}
	public void drawAnt(Graphics g, Ant ant){
		g.drawRect(GC.fieldPos.x+(ant.pos.x*GC.antSize), GC.fieldPos.y+(ant.pos.y*GC.antSize), GC.antSize, GC.antSize);
	}

}
