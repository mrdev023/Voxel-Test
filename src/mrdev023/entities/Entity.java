package mrdev023.entities;

import mrdev023.entities.ia.*;
import mrdev023.entities.physics.*;
import mrdev023.world.*;

public abstract class Entity {
	
	private World world;
	private Physics physics;
	private PathFinding pathFinding;

	public Entity(World world,Physics physics,PathFinding pathFinding){
		this.pathFinding = pathFinding;
		this.world = world;
		this.physics = physics;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Physics getPhysics() {
		return physics;
	}

	public void setPhysics(Physics physics) {
		this.physics = physics;
	}

	public PathFinding getPathFinding() {
		return pathFinding;
	}

	public void setPathFinding(PathFinding pathFinding) {
		this.pathFinding = pathFinding;
	}
	
	
	
}
