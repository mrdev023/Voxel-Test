package game;

import world.*;

public class Game {

	private World world;
	private int update = 0;
	
	public Game(){
		world= new World(0,120,50);
	}
	
	public void update(){
		if(update >= 2){
			world.update();
			update = 0;
		}
		update++;
	}
	
	public void render(){
		world.render();
	}
	
	public void renderGUI(){
		
	}

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
	
	
}
