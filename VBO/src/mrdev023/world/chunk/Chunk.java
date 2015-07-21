package mrdev023.world.chunk;

import mrdev023.blocks.*;
import mrdev023.gameEngine.*;
import mrdev023.main.*;
import mrdev023.math.*;
import mrdev023.rendering.*;
import mrdev023.world.*;

public class Chunk {

	public final static int SIZE = 64;
	private int x, y, z;
	private VBO vbo;
	private World world;
	Block[][][] blocks;
	private boolean IsLoad = false;
	private boolean IsGenerated = false;
	private boolean IsCurrentGenerate = false;
	private boolean IsDestroy = false;

	public Chunk(int x, int y, int z, World world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
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

	public void addBlock(int x, int y, int z,Block b) {
		if (x < 0 || y < 0 || z < 0 || x >= SIZE || y >= SIZE || z >= SIZE)
			return;
		blocks[x][y][z] = b;
	}
	
	public void destroyChunk() {
		vbo.destroyVBO();
		IsDestroy = false;
	}

	public void loopChunk(int x, int y, int z) {
		world = GameEngine.getGame().getWorld();
		int xx = this.x * SIZE + x;
		int yy = this.y * SIZE + y;
		int zz = this.z * SIZE + z;
		boolean up = true;
		boolean down = true;
		boolean left = true;
		boolean right = true;
		boolean back = true;
		boolean front = true;
		boolean nx = false, ny = false, nz = false;
		if(this.x < 0)nx = true;
		if(this.y < 0)ny = true;
		if(this.z < 0)nz = true;
		up = world.getBlock(xx, yy + 1, zz,nx,ny,nz) == null;
		down = world.getBlock(xx, yy - 1, zz,nx,ny,nz) == null;
		left = world.getBlock(xx - 1, yy, zz,nx,ny,nz) == null;
		right = world.getBlock(xx + 1, yy, zz,nx,ny,nz) == null;
		front = world.getBlock(xx, yy, zz - 1,nx,ny,nz) == null;
		back = world.getBlock(xx, yy, zz + 1,nx,ny,nz) == null;
		if (!up && !down && !left && !right && !front && !back)
			return;
		if (blocks[x][y][z] == null)
			return;
		Block b = blocks[x][y][z];

		float ao = 0.7f;

		// up + 1 = down - 1 = y
		// left - 1 = right + 1 = x
		// front - 1 = back + 1 = z
		
		if (up) {
			// aa ab bb ba
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx - 1, yy + 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy + 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx, yy + 1, zz - 1,nx,ny,nz) != null)
				a[0] = ao;
			if (world.getBlock(xx + 1, yy + 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy + 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx, yy + 1, zz - 1,nx,ny,nz) != null)
				a[1] = ao;
			if (world.getBlock(xx + 1, yy + 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy + 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx, yy + 1, zz + 1,nx,ny,nz) != null)
				a[2] = ao;
			if (world.getBlock(xx - 1, yy + 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy + 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx, yy + 1, zz + 1,nx,ny,nz) != null)
				a[3] = ao;

			vbo.addDataByFloatArray(b.getDataUp(xx, yy, zz, a));
		}
		if (down) {
			float[] a = new float[] { 1, 1, 1, 1 };
			// ambient occlusion

			if (world.getBlock(xx - 1, yy - 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy - 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx, yy - 1, zz - 1,nx,ny,nz) != null)
				a[1] = ao;
			if (world.getBlock(xx + 1, yy - 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy - 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx, yy - 1, zz - 1,nx,ny,nz) != null)
				a[0] = ao;
			if (world.getBlock(xx + 1, yy - 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy - 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx, yy - 1, zz + 1,nx,ny,nz) != null)
				a[3] = ao;
			if (world.getBlock(xx - 1, yy - 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy - 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx, yy - 1, zz + 1,nx,ny,nz) != null)
				a[2] = ao;

			// affiche la face si il n'y a rien en dessous
			vbo.addDataByFloatArray(b.getDataDown(xx, yy, zz, a));
		}
		if (left) {
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx - 1, yy - 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy - 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy, zz - 1,nx,ny,nz) != null)
				a[0] = ao;
			if (world.getBlock(xx - 1, yy + 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy + 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy, zz - 1,nx,ny,nz) != null)
				a[1] = ao;
			if (world.getBlock(xx - 1, yy + 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy + 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy, zz + 1,nx,ny,nz) != null)
				a[2] = ao;
			if (world.getBlock(xx - 1, yy - 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy - 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy, zz + 1,nx,ny,nz) != null)
				a[3] = ao;

			vbo.addDataByFloatArray(b.getDataLeft(xx, yy, zz, a));
		}
		if (right) {
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx + 1, yy - 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy - 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy, zz - 1,nx,ny,nz) != null)
				a[1] = ao;
			if (world.getBlock(xx + 1, yy + 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy + 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy, zz - 1,nx,ny,nz) != null)
				a[0] = ao;
			if (world.getBlock(xx + 1, yy + 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy + 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy, zz + 1,nx,ny,nz) != null)
				a[3] = ao;
			if (world.getBlock(xx + 1, yy - 1, zz,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy - 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy, zz + 1,nx,ny,nz) != null)
				a[2] = ao;

			vbo.addDataByFloatArray(b.getDataRight(xx, yy, zz, a));
		}
		if (front) {
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx, yy - 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy - 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy, zz - 1,nx,ny,nz) != null)
				a[0] = ao;
			if (world.getBlock(xx, yy + 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy + 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy, zz - 1,nx,ny,nz) != null)
				a[3] = ao;
			if (world.getBlock(xx, yy + 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy + 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy, zz - 1,nx,ny,nz) != null)
				a[2] = ao;
			if (world.getBlock(xx, yy - 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy - 1, zz - 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy, zz - 1,nx,ny,nz) != null)
				a[1] = ao;

			vbo.addDataByFloatArray(b.getDataFront(xx, yy, zz, a));
		}
		if (back) {
			float[] a = new float[] { 1, 1, 1, 1 };

			if (world.getBlock(xx, yy - 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy - 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy, zz + 1,nx,ny,nz) != null)
				a[1] = ao;
			if (world.getBlock(xx, yy + 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy + 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx - 1, yy, zz + 1,nx,ny,nz) != null)
				a[2] = ao;
			if (world.getBlock(xx, yy + 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy + 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy, zz + 1,nx,ny,nz) != null)
				a[3] = ao;
			if (world.getBlock(xx, yy - 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy - 1, zz + 1,nx,ny,nz) != null
					|| world.getBlock(xx + 1, yy, zz + 1,nx,ny,nz) != null)
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

	public String toString(){
		return x + " " + y + " " + z;
	}
	

	public boolean isCurrentGenerate(){
		return IsCurrentGenerate;
	}

	public boolean isDestroy() {
		return IsDestroy;
	}
	
	
}