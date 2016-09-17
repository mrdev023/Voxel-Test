package mrdev023.blocks;

import mrdev023.math.*;

public class GrassBlock extends Block{
	public GrassBlock() {
		super(new Color4f(0.2f,0.6f,0f));
	}

	public String toString() {
		return this.getClass().getSimpleName();  
	}
	
}
