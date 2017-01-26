package graphics;

import java.awt.Color;

public interface GraphicsManager {
	public void init(String title, int width, int height, Color bgColor);
	void createWindow(String title, int width, int height);
	boolean createYNDialogue(String question, String title);
	int createMenuBar() throws IllegalArgumentException ;
	void addMenuToBar(String text) throws IllegalArgumentException;
	void createSurface(int x, int y, int width, int height, Color bgColor);
	public void closeWindow();
}
