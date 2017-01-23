package units;

import ant.Ant;

public enum Rule {
	L {
		@Override
		public void action(Ant ant) {
			ant.rotateLeft();
		}
	},
	R{
		public void action(Ant ant){
			ant.rotateRight();
		}
	};
	public abstract void action(Ant ant);
	//public abstract Color getColor();
}
