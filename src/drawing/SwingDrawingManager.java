package drawing;

import java.awt.Graphics;

public class SwingDrawingManager implements DrawingManager{

	Graphics g;
	
	public SwingDrawingManager(Object g) {
		this.g=(Graphics)g;
	}
	
	@Override
	public void draw() {
		g.drawRect(0, 10, 50, 100);
	}

}
