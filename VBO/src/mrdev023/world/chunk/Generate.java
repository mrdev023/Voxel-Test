package mrdev023.world.chunk;

import java.util.*;

import mrdev023.blocks.*;
import mrdev023.math.*;
import mrdev023.rendering.*;
import mrdev023.world.*;
import mrdev023.world.chunk.*;

class Generate implements Runnable {

	private Chunk chunk;
	private World world;

	public Generate(Chunk chunk, World world) {
		this.chunk = chunk;
		this.world = world;
	}
	
	/*
	 * boolean grounded = noise.getNoise(xx, zz) > yy - 1 && noise.getNoise(xx, zz) < yy;
					
					float percentOfSpawnTree = 0.005f;
					if(random.nextFloat() < percentOfSpawnTree && grounded){
						if(random.nextInt(2) == 0)
							Tree.addOak(world, xx, yy, zz);
						else
							Tree.addFir(world, xx, yy, zz);
		}(non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */

	public void run() {
		long current = System.currentTimeMillis();
		long elapsed1 = 0;
		boolean IsError = true;
		Noise noise = new Noise(world.seed, world.octave, world.amplitude);;
		Random random = new Random(world.seed);
		Noise colorVariationNoise = new Noise(world.seed,world.octave,2);
		for (int x = 0; x < chunk.SIZE; x++) {
			for (int z = 0; z < chunk.SIZE; z++) {
					int xa = (int)((Camera.getPosition().getX()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) - World.VIEW_CHUNK;
					int xb = (int)((Camera.getPosition().getX()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) + World.VIEW_CHUNK;
					int za = (int)((Camera.getPosition().getZ()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) - World.VIEW_CHUNK;
					int zb = (int)((Camera.getPosition().getZ()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) + World.VIEW_CHUNK;
					if(chunk.getX() < xa || chunk.getX() > xb || chunk.getZ() < za || chunk.getZ() > zb  ){
						System.out.println(Thread.currentThread().getName() + " stopped | " + (System.currentTimeMillis()-current));
						Thread.currentThread().stop();
						return;
					}
					int xx = chunk.getX() * chunk.SIZE + x;
					int zz = chunk.getZ() * chunk.SIZE + z;

					
					Color4f green = new Color4f(0.2f,0.6f,0);
					Color4f yellow = new Color4f(0.36f,0.77f,0.17f);
					float v = colorVariationNoise.getNoise(xx, zz);
					
					Color4f finalColor = Color4f.interpolate(yellow, green, v);
					
					chunk.blocks[x][(int)noise.getNoise(xx, zz)][z] = new GrassBlock().setColor(finalColor);

			}
		}
		elapsed1 = System.currentTimeMillis() - current;
		while(IsError){
			IsError = false;
			try{
//				synchronized (world.chunks) {
					for (int i = 0; i < chunk.SIZE; i++) {
//						for (int j = 0; j < chunk.SIZE; j++) {
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
								chunk.loopChunk(i, (int)noise.getNoise(xx, zz), k);
							}
//						}
					}
//				}
				chunk.setGenerated(true);
			}catch (Exception e){
				e.printStackTrace();
				IsError = true;
			}
		}
		System.out.println(Thread.currentThread().getName() + " terminated | loop1:" + elapsed1 + "ms loop2:" + (System.currentTimeMillis()-current) + "ms");
		Thread.currentThread().stop();
	}

}
