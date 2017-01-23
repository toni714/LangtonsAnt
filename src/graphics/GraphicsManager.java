package graphics;

import java.awt.Color;

public interface GraphicsManager {
	//public void createNewSlider(String name, float min, float max, float step, int x, int y, int width, int height);

	void createWindow(String title, int width, int height, Color bgColor);
	
	boolean createYNDialogue(String question, String title);

}
