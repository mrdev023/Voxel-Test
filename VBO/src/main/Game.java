package main;

import world.*;

public class Game {

	private World world;
	
	public Game(){
		world= new World(0);
	}
	
	public void update(){
		world.update();
	}
	
	public void render(){
		world.render();
	}
	
	public void renderGUI(){
		
	}
	
}
