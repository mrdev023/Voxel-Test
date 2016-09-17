package mrdev023.world.chunk;

import mrdev023.rendering.*;
import mrdev023.world.*;

public class LoadChunk implements Runnable{
	
	private Chunk chunk;
	private World world;

	public LoadChunk(Chunk chunk, World world) {
		this.chunk = chunk;
		this.world = world;
	}

	public void run(){
		long current = System.currentTimeMillis();
		boolean IsError = true;
		while(IsError){
			IsError = false;
			try{
//				synchronized (world.chunks) {
					for (int i = 0; i < chunk.SIZE; i++) {
						for (int j = 0; j < chunk.SIZE; j++) {
							for (int k = 0; k < chunk.SIZE; k++) {
								int xa = (int)((Camera.getPosition().getX()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) - World.VIEW_CHUNK;
								int xb = (int)((Camera.getPosition().getX()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) + World.VIEW_CHUNK;
								int za = (int)((Camera.getPosition().getZ()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) - World.VIEW_CHUNK;
								int zb = (int)((Camera.getPosition().getZ()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) + World.VIEW_CHUNK;
								if(chunk.getX() < xa || chunk.getX() > xb || chunk.getZ() < za || chunk.getZ() > zb  ){
									System.out.println(Thread.currentThread().getName() + " stopped | " + (System.currentTimeMillis()-current));
									Thread.currentThread().stop();
									return;
								}
								int xx = chunk.getX() * chunk.SIZE + i;
								int zz = chunk.getZ() * chunk.SIZE + k;
								chunk.loopChunk(i, j, k);
							}
						}
					}
//				}
				chunk.setGenerated(true,false);
			}catch (Exception e){
				e.printStackTrace();
				IsError = true;
			}
		}
		System.out.println(Thread.currentThread().getName() + " terminated | loop1:" + (System.currentTimeMillis()-current) + "ms");
		Thread.currentThread().stop();
		
	}
	
}
