package testing;

import drawing.DrawingEngine;
import main.LangtonsAnt;

public class Main {
	public static void main(String[] args) {
		LangtonsAnt controler=new LangtonsAnt(DrawingEngine.SWING);
		
		controler.start();
	}
}
