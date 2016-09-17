package mrdev023.blocks;

import mrdev023.math.*;

public class FirLeafBlock extends Block{
	
	public FirLeafBlock() {
		super(new Color4f(0.0f,0.4f,0f));
	}

	public String toString() {
		return this.getClass().getSimpleName();  
	}

}
