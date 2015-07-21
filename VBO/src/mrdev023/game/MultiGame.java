package mrdev023.game;

import mrdev023.world.*;

public class MultiGame extends Game{

	public MultiGame(long seed) {
		super(new MultiWorld(seed,120,50));
	}

	public void render(){
		this.world.render();
	}
	
	public void update(){
		if(update >= 2){
			world.update();
			update = 0;
		}
		update++;
	}

	public void renderGUI() {
	
	}

}
