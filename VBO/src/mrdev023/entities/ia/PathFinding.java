package mrdev023.entities.ia;

import mrdev023.entities.*;
import mrdev023.world.*;

public class PathFinding {

	private World world;
	private Entity entity;
	
	public PathFinding(World world,Entity entity){
		this.world = world;
		this.entity = entity;
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
