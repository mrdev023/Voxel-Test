package world;

import vanilla.java.affinity.*;
import blocks.*;
import main.*;
import math.*;

public class Chunk {

	public final static int SIZE = 8;
	private int x, y, z;
	private VBO vbo;
	private World world;
	Block[][][] blocks;
	private boolean IsLoad = false;
	private boolean IsGenerated = false;
	private boolean IsCurrentGenerate = false;
	private boolean IsDestroy = false;
	private WorldNoise worldNoise;

	public Chunk(int x, int y, int z, World world,WorldNoise worldNoise) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
		this.worldNoise = worldNoise;
		this.blocks = new Block[SIZE][SIZE][SIZE];
		vbo = new VBO();
	}

	public void render() {
		vbo.renderVBO();
	}

	public void update() {

	}

	public void createChunk(World world) {
		this.world = world;

		Main.addThread(new Generate(this, world),"Create Chunk");
		IsCurrentGenerate = true;
	}

	public void loadBufferData() {
		vbo.bufferData();
		IsLoad = true;
	}

	public void updateChunk() {
		vbo.clearBuffer();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < SIZE; k++) {
					loopChunk(i, j, k);
				}
			}
		}
		vbo.bufferData();
	}

	public int getSIZE() {
		return SIZE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Vector3f getPosition() {
		return new Vector3f(x, y, z);
	}

	public Block getBlock(int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0 || x >= SIZE || y >= SIZE || z >= SIZE)
			return null;
		return blocks[x][y][z];
	}

	public void destroyChunk() {
		vbo.destroyVBO();
		IsDestroy = false;
	}

	public void loopChunk(int x, int y, int z) {
		int xx = this.x * SIZE + x;
		int yy = this.y * SIZE + y;
		int zz = this.z * SIZE + z;
		boolean up = world.getBlock(xx, yy + 1, zz) == null;
		boolean down = world.getBlock(xx, yy - 1, zz) == null;
		boolean left = world.getBlock(xx - 1, yy, zz) == null;
		boolean right = world.getBlock(xx + 1, yy, zz) == null;
		boolean front = world.getBlock(xx, yy, zz - 1) == null;
		boolean back = world.getBlock(xx, yy, zz + 1) == null;
		if (!up && !down && !left && !right && !front && !back)
			return;
		if (blocks[x][y][z] == null)
			return;
		Block b = blocks[x][y][z];

		float ao = 0.7f;

		// up + 1 = down - 1 = y
		// left - 1 = right + 1 = x
		// front - 1 = back + 1 = z
//		up = true;
//		down = true;
//		left = true;
//		right = true;
//		back = true;
//		front = true;
		
		if (up) {
			// aa ab bb ba
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx - 1, yy + 1, zz) != null
					|| world.getBlock(xx - 1, yy + 1, zz - 1) != null
					|| world.getBlock(xx, yy + 1, zz - 1) != null)
				a[0] = ao;
			if (world.getBlock(xx + 1, yy + 1, zz) != null
					|| world.getBlock(xx + 1, yy + 1, zz - 1) != null
					|| world.getBlock(xx, yy + 1, zz - 1) != null)
				a[1] = ao;
			if (world.getBlock(xx + 1, yy + 1, zz) != null
					|| world.getBlock(xx + 1, yy + 1, zz + 1) != null
					|| world.getBlock(xx, yy + 1, zz + 1) != null)
				a[2] = ao;
			if (world.getBlock(xx - 1, yy + 1, zz) != null
					|| world.getBlock(xx - 1, yy + 1, zz + 1) != null
					|| world.getBlock(xx, yy + 1, zz + 1) != null)
				a[3] = ao;

			vbo.addDataByFloatArray(b.getDataUp(xx, yy, zz, a));
		}
		if (down) {
			float[] a = new float[] { 1, 1, 1, 1 };
			// ambient occlusion

			if (world.getBlock(xx - 1, yy - 1, zz) != null
					|| world.getBlock(xx - 1, yy - 1, zz - 1) != null
					|| world.getBlock(xx, yy - 1, zz - 1) != null)
				a[1] = ao;
			if (world.getBlock(xx + 1, yy - 1, zz) != null
					|| world.getBlock(xx + 1, yy - 1, zz - 1) != null
					|| world.getBlock(xx, yy - 1, zz - 1) != null)
				a[0] = ao;
			if (world.getBlock(xx + 1, yy - 1, zz) != null
					|| world.getBlock(xx + 1, yy - 1, zz + 1) != null
					|| world.getBlock(xx, yy - 1, zz + 1) != null)
				a[3] = ao;
			if (world.getBlock(xx - 1, yy - 1, zz) != null
					|| world.getBlock(xx - 1, yy - 1, zz + 1) != null
					|| world.getBlock(xx, yy - 1, zz + 1) != null)
				a[2] = ao;

			// affiche la face si il n'y a rien en dessous
			vbo.addDataByFloatArray(b.getDataDown(xx, yy, zz, a));
		}
		if (left) {
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx - 1, yy - 1, zz) != null
					|| world.getBlock(xx - 1, yy - 1, zz - 1) != null
					|| world.getBlock(xx - 1, yy, zz - 1) != null)
				a[0] = ao;
			if (world.getBlock(xx - 1, yy + 1, zz) != null
					|| world.getBlock(xx - 1, yy + 1, zz - 1) != null
					|| world.getBlock(xx - 1, yy, zz - 1) != null)
				a[1] = ao;
			if (world.getBlock(xx - 1, yy + 1, zz) != null
					|| world.getBlock(xx - 1, yy + 1, zz + 1) != null
					|| world.getBlock(xx - 1, yy, zz + 1) != null)
				a[2] = ao;
			if (world.getBlock(xx - 1, yy - 1, zz) != null
					|| world.getBlock(xx - 1, yy - 1, zz + 1) != null
					|| world.getBlock(xx - 1, yy, zz + 1) != null)
				a[3] = ao;

			vbo.addDataByFloatArray(b.getDataLeft(xx, yy, zz, a));
		}
		if (right) {
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx + 1, yy - 1, zz) != null
					|| world.getBlock(xx + 1, yy - 1, zz - 1) != null
					|| world.getBlock(xx + 1, yy, zz - 1) != null)
				a[1] = ao;
			if (world.getBlock(xx + 1, yy + 1, zz) != null
					|| world.getBlock(xx + 1, yy + 1, zz - 1) != null
					|| world.getBlock(xx + 1, yy, zz - 1) != null)
				a[0] = ao;
			if (world.getBlock(xx + 1, yy + 1, zz) != null
					|| world.getBlock(xx + 1, yy + 1, zz + 1) != null
					|| world.getBlock(xx + 1, yy, zz + 1) != null)
				a[3] = ao;
			if (world.getBlock(xx + 1, yy - 1, zz) != null
					|| world.getBlock(xx + 1, yy - 1, zz + 1) != null
					|| world.getBlock(xx + 1, yy, zz + 1) != null)
				a[2] = ao;

			vbo.addDataByFloatArray(b.getDataRight(xx, yy, zz, a));
		}
		if (front) {
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx, yy - 1, zz - 1) != null
					|| world.getBlock(xx - 1, yy - 1, zz - 1) != null
					|| world.getBlock(xx - 1, yy, zz - 1) != null)
				a[0] = ao;
			if (world.getBlock(xx, yy + 1, zz - 1) != null
					|| world.getBlock(xx - 1, yy + 1, zz - 1) != null
					|| world.getBlock(xx - 1, yy, zz - 1) != null)
				a[3] = ao;
			if (world.getBlock(xx, yy + 1, zz - 1) != null
					|| world.getBlock(xx + 1, yy + 1, zz - 1) != null
					|| world.getBlock(xx + 1, yy, zz - 1) != null)
				a[2] = ao;
			if (world.getBlock(xx, yy - 1, zz - 1) != null
					|| world.getBlock(xx + 1, yy - 1, zz - 1) != null
					|| world.getBlock(xx + 1, yy, zz - 1) != null)
				a[1] = ao;

			vbo.addDataByFloatArray(b.getDataFront(xx, yy, zz, a));
		}
		if (back) {
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx, yy - 1, zz + 1) != null
					|| world.getBlock(xx - 1, yy - 1, zz + 1) != null
					|| world.getBlock(xx - 1, yy, zz + 1) != null)
				a[1] = ao;
			if (world.getBlock(xx, yy + 1, zz + 1) != null
					|| world.getBlock(xx - 1, yy + 1, zz + 1) != null
					|| world.getBlock(xx - 1, yy, zz + 1) != null)
				a[2] = ao;
			if (world.getBlock(xx, yy + 1, zz + 1) != null
					|| world.getBlock(xx + 1, yy + 1, zz + 1) != null
					|| world.getBlock(xx + 1, yy, zz + 1) != null)
				a[3] = ao;
			if (world.getBlock(xx, yy - 1, zz + 1) != null
					|| world.getBlock(xx + 1, yy - 1, zz + 1) != null
					|| world.getBlock(xx + 1, yy, zz + 1) != null)
				a[0] = ao;

			vbo.addDataByFloatArray(b.getDataBack(xx, yy, zz, a));
		}
	}

	public boolean isLoaded() {
		return IsLoad;
	}

	public boolean isGenerated() {
		return IsGenerated;
	}

	public void setGenerated(boolean g) {
		IsGenerated = g;
	}

	public boolean isCurrentGenerate(){
		return IsCurrentGenerate;
	}

	public boolean isDestroy() {
		return IsDestroy;
	}

	public WorldNoise getWorldNoise() {
		return worldNoise;
	}

	public void setWorldNoise(WorldNoise worldNoise) {
		this.worldNoise = worldNoise;
	}
	
	
}

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
		AffinityLock al = null;
		int cpuId = 0;
		try{
			al = AffinityLock.acquireCore();
		}catch(Exception e){}
		long current = System.currentTimeMillis();
		boolean IsError = true;
		Noise noise = new Noise(world.seed, 20, 5);
		for (int x = 0; x < chunk.SIZE; x++) {
			for (int z = 0; z < chunk.SIZE; z++) {
				for (int y = 0; y < chunk.SIZE; y++) {
					int xx = chunk.getX() * chunk.SIZE + x;
					int yy = chunk.getY() * chunk.SIZE + y;
					int zz = chunk.getZ() * chunk.SIZE + z;
					if(noise.getNoise(xx, zz) > yy){
						chunk.blocks[x][y][z] = Block.GRASS;
					}else{
						continue;
					}
				}
			}
		}
		while(IsError){
			IsError = false;
			try{
				synchronized (world.chunks) {
					for (int i = 0; i < chunk.SIZE; i++) {
						for (int j = 0; j < chunk.SIZE; j++) {
							for (int k = 0; k < chunk.SIZE; k++) {
								chunk.loopChunk(i, j, k);
							}
						}
					}
				}
				chunk.setGenerated(true);
			}catch (Exception e){
				IsError = true;
			}
		}
		System.out.println(Thread.currentThread().getName() + " | " + (System.currentTimeMillis()-current) + " | " + cpuId);
		Thread.currentThread().stop();
		al.release();
	}

}
