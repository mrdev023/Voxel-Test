package world;

import java.util.*;

import main.*;
import math.*;
import blocks.*;

public class World {

	public long seed;
	public final int SIZE = 1,HEIGHT = 1;
	public static final float GRAVITY = 1;
	public static final int VIEW_CHUNK = 8;
	public static WorldNoise worldNoise;
	
	public ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	
	public World(long seed){
		this.seed= seed;
		this.worldNoise = new WorldNoise(seed);
		for(int x = 0;x < SIZE;x++){
			for(int y = 0;y < HEIGHT;y++){
				for(int z = 0;z < SIZE;z++){
					Chunk ch = new Chunk(x,y,z,this,worldNoise);
					chunks.add(ch);
				}
			}
		}
		for(Chunk ch : chunks)ch.createChunk(this);
	}
	
	public void update(){
		int xa = (int)((Camera.getPosition().getX()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) - VIEW_CHUNK;
		int xb = (int)((Camera.getPosition().getX()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) + VIEW_CHUNK;
		int za = (int)((Camera.getPosition().getZ()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) - VIEW_CHUNK;
		int zb = (int)((Camera.getPosition().getZ()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) + VIEW_CHUNK;
		
				
		int delta_x = xb - xa;
		int delta_z = zb - za;
		for(int i = 0; i <= delta_x;i++){
			for(int k = 0;k <= delta_z;k++){
				for(int j = 0; j < HEIGHT; j++){
					if(getChunk((xa + i), 0, (za + k)) == null){
						Chunk ch = new Chunk((xa + i),j,(za + k),this,worldNoise);
						chunks.add(ch);
					}
				}
			}
		}
		ArrayList<Chunk> removeList = new ArrayList<Chunk>();
		for(int i = 0; i < chunks.size();i++){
			Chunk c = chunks.get(i);
			if(c.getPosition().getX() < xa || c.getPosition().getX() > xb || c.getPosition().getZ() < za || c.getPosition().getZ() > zb){
				c.destroyChunk();
				removeList.add(c);
			}
		}
		for(Chunk c: removeList){
			removeByChunk(c);
		}
		removeList.clear();		
		for(Chunk c : chunks){
			if(!c.isLoaded() && !c.isGenerated() && !c.isCurrentGenerate() && !c.isDestroy())c.createChunk(this);
			if(!c.isLoaded() && c.isGenerated() && !c.isDestroy())c.loadBufferData();
			if(!c.isDestroy())c.update();
		}
		System.gc();
	}
	
	public void render(){
		for(Chunk c : chunks){
			if(c.isLoaded())
			c.render();
		}
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

	public Block getBlock(int x, int y, int z) {
		int xc = (x / Chunk.SIZE);
		int zc = (z / Chunk.SIZE);
		int yc = (y / Chunk.SIZE);

		Chunk chunk = getChunk(xc, yc, zc);
		if(chunk == null)return null;
		
		int xb = x % Chunk.SIZE;
		int yb = y % Chunk.SIZE;
		int zb = z % Chunk.SIZE;
		
//		if(xb < 0){
//			xb = -xb;
//		}
//		if(yb < 0){
//			yb = -yb;
//		}
//		if(zb < 0){
//			zb = -zb;
//		}

		return chunk.getBlock(xb, yb, zb);
	}
	
	
	
}
