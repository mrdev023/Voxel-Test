package mrdev023.game;

import mrdev023.world.*;

public abstract class Game {

	protected World world;
	protected int update = 0;
	
	public Game(World world){
		this.world = world;
	}
	
	public abstract void update();
	
	public abstract void render();
	
	public abstract void renderGUI();

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int getUpdate() {
		return update;
	}

	public void setUpdate(int update) {
		this.update = update;
	}
	
	public void destroyGame(){
		world.destroyWorld();
	}
}
