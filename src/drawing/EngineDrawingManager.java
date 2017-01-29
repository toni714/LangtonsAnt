package drawing;

import java.awt.Color;

public interface EngineDrawingManager {
	//this needs to call drawingManager.draw();
	public void draw();

	//Functions used by the Game to draw-------------------------------------------------------------------------------
	public void fillRect(int x, int y, int width, int height, Color color);
	public void drawRect(int x, int y, int width, int height, Color color);
}
