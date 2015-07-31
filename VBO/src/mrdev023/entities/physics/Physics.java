package mrdev023.entities.physics;

import mrdev023.entities.*;
import mrdev023.world.*;

public class Physics {

	private World world;
	private Entity entity;
	
	public Physics(World world,Entity entity){
		this.entity = entity;
		this.world = world;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
	
}
