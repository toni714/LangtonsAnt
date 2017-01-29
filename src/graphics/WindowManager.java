package graphics;

import java.awt.Color;

import drawing.EngineDrawingManager;
import events.MyActionListener;
import main.LangtonsAnt;

public interface WindowManager {
	
	//Initialization---------------------------------------------------------------------------------------------------
	void createWindow(LangtonsAnt controler, String title, int width, int height);
	void createSurface(int x, int y, int width, int height, Color bgColor);
	
	//Window Functions-------------------------------------------------------------------------------------------------
	public boolean createYNDialogue(String question, String title);
	public void closeWindow();
	
	public EngineDrawingManager getDrawingManager();
	
	//Menu Stuff-------------------------------------------------------------------------------------------------------
	int addMenuBar(LangtonsAnt controler) throws IllegalArgumentException;
	void addMenuToBar(String menuName) throws IllegalArgumentException;
	
	void addItemToMenu(String itemName, MyActionListener listener, String menuName) throws IllegalArgumentException;
	
	void addCheckboxToMenu(String itemName, MyActionListener listener, boolean defaultVal, String menuName)
			throws IllegalArgumentException;
	public boolean getCheckboxValue(String string);
	
}
