package mrdev023.blocks;

import mrdev023.math.*;

public class TransparentBlock extends Block {
	
	public TransparentBlock(){
		super(new Color4f(1,1,1,0));
	}
	
	public String toString() {
		return this.getClass().getSimpleName();  
	}
	
}
