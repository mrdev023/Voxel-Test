package mrdev023.world;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import mrdev023.blocks.*;
import mrdev023.math.*;
import mrdev023.rendering.*;
import mrdev023.world.chunk.*;

public abstract class World {

	public long seed;
	public int octave,amplitude;
	public final int SIZE = 1,HEIGHT = 1;
	public static final float GRAVITY = 1;
	public static final int VIEW_CHUNK = 2;
	
	public ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	
	public World(long seed,int octave,int amplitude){
		this.seed= seed;
		this.octave = octave;
		this.amplitude = amplitude;
		for(int x = 0;x < SIZE;x++){
			for(int y = 0;y < HEIGHT;y++){
				for(int z = 0;z < SIZE;z++){
					Chunk ch = new Chunk(x,y,z,this);
					chunks.add(ch);
				}
			}
		}
		for(Chunk ch : chunks)ch.createChunk(this);
	}
	
	public static long updateWorldTime = 0;
	
	public abstract void update();
	public abstract void render();
	
	public void renderPoints(Vector2f... a){
		glPointSize(10);
		glBegin(GL_POINTS);
		for(Vector2f b : a){
			glVertex3f(b.getX(),Camera.getPosition().getY() + Camera.getHeight(),b.getY());
		}
		glEnd();
	}
	
	public void removeByChunk(Chunk ch){
		for(int i = 0;i < chunks.size();i++){
			if(chunks.get(i).equals(ch)){
				chunks.remove(i);
			}
		}
	}
	
	public Chunk getChunk(int xc, int yc, int zc) {
		Chunk c = null;
		Object[] chunk = chunks.toArray();
		for(int i = 0;i < chunk.length;i++){
			Chunk ch = (Chunk)chunk[i];
			if(ch.getX() == xc && ch.getY() == yc && ch.getZ() == zc){
				c = ch;
				break;
			}
		}
		return c;
	}
	
	public void addBlock(int x,int y,int z,Block b){
		int xc = (x / Chunk.SIZE);
		int zc = (z / Chunk.SIZE);
		int yc = (y / Chunk.SIZE);

		Chunk chunk = getChunk(xc, yc, zc);
		if(chunk == null)return;
		
		int xb = x % Chunk.SIZE;
		int yb = y % Chunk.SIZE;
		int zb = z % Chunk.SIZE;
		
		chunk.addBlock(xb, yb, zb, b);
	}
	
	public Vector3f getLocalChunk(int x, int y, int z,boolean nx,boolean ny,boolean nz){
		int xc = (x / Chunk.SIZE);
		int zc = (z / Chunk.SIZE);
		int yc = (y / Chunk.SIZE);

		if(nx)xc -= 1;
		if(ny)yc -= 1;
		if(nz)zc -= 1;
		return new Vector3f(xc,yc,zc);
	}

	public Block getBlock(int x, int y, int z,boolean nx,boolean ny,boolean nz) {
		int xc = (x / Chunk.SIZE);
		int zc = (z / Chunk.SIZE);
		int yc = (y / Chunk.SIZE);

		if(nx)xc -= 1;
		if(ny)yc -= 1;
		if(nz)zc -= 1;
		
		Chunk chunk = getChunk(xc, yc, zc);
		if(chunk == null)return null;
		int xb = 0;
		int yb = 0;
		int zb = 0;
		
		if( x <= 0 && nx){
			xb = (Chunk.SIZE-1)-((-x) % Chunk.SIZE) + 1;
		}else{
			xb = x % Chunk.SIZE;			
		}
		
		
		if( y <= 0 && ny){
			yb = (Chunk.SIZE-1)-((-y) % Chunk.SIZE) + 1;
		}else{
			yb = y % Chunk.SIZE;			
		}
		
		
		if( z <= 0 && nz){
			zb = (Chunk.SIZE-1)-((-z) % Chunk.SIZE) + 1;
		}else{
			zb = z % Chunk.SIZE;			
		}

		return chunk.getBlock(xb, yb, zb);
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public ArrayList<Chunk> getChunks() {
		return chunks;
	}

	public void setChunks(ArrayList<Chunk> chunks) {
		this.chunks = chunks;
	}

	public static long getUpdateWorldTime() {
		return updateWorldTime;
	}

	public static void setUpdateWorldTime(long updateWorldTime) {
		World.updateWorldTime = updateWorldTime;
	}

	public int getSIZE() {
		return SIZE;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public static float getGravity() {
		return GRAVITY;
	}

	public static int getViewChunk() {
		return VIEW_CHUNK;
	}
	
	public void destroyWorld(){
		for(Chunk c : chunks){
			c.destroyChunk();
			c = null;
		}
		chunks.clear();
	}
	
}
