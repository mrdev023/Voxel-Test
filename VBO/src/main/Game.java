package main;

import world.*;

public class Game {

	private World world;
	private int update = 0;
	
	public Game(){
		world= new World(0);
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
	
}
