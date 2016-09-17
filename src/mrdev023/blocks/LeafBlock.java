package mrdev023.blocks;

import mrdev023.math.*;

public class LeafBlock extends Block{

	public LeafBlock() {
		super(new Color4f(0,0.6f,0,1));
	}
	
	public String toString() {
		return this.getClass().getSimpleName();  
	}
	
}
