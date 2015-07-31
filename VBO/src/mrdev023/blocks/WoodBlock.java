package mrdev023.blocks;

import mrdev023.math.*;

public class WoodBlock extends Block{

	public WoodBlock(Color4f c) {
		super(c);
	}
	
	public String toString() {
		return this.getClass().getSimpleName();  
	}
	
}
