package units;

import ant.Ant;

public enum Rule {
	L {
		@Override
		public void action(Ant ant) {
			//TODO
		}
	},
	R{
		public void action(Ant ant){
			//TODO
		}
	};
	public abstract void action(Ant ant);
}
