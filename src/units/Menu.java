package units;

public abstract class Menu {
	Vector2D pos;
	int width;
	int height;
	public boolean isVisible;
	public abstract void draw();
	public void setVisible(boolean state) {
		isVisible=state;
	}
}
