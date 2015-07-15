package world;

import java.util.*;

import main.*;
import math.*;
import blocks.*;

public class World {

	public long seed;
	public final int SIZE = 1,HEIGHT = 1;
	public static final float GRAVITY = 1;
	public static final int VIEW_CHUNK = 4;
	
	public ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	
	public World(long seed){
		this.seed= seed;
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
						Chunk ch = new Chunk((xa + i),j,(za + k),this);
						chunks.add(ch);
					}
				}
			}
		}
		for(int i = 0; i < chunks.size();i++){
			Chunk c = chunks.get(i);
			if(c.getPosition().getX() < xa || c.getPosition().getX() > xb || c.getPosition().getZ() < za || c.getPosition().getZ() > zb){
				c.destroyChunk();
				chunks.remove(i);
				break;
			}
		}
		for(Chunk c : chunks){
			if(!c.isLoaded() && !c.isGenerated() && !c.isCurrentGenerate())c.createChunk(this);
			if(!c.isLoaded() && c.isGenerated())c.loadBufferData();
			c.update();
		}
		System.gc();
	}
	
	public void render(){
		for(Chunk c : chunks){
			if(c.isLoaded())
			c.render();
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

	public Block getBlock(int x, int y, int z) {
		int xc = (x / Chunk.SIZE);
		int zc = (z / Chunk.SIZE);
		int yc = (y / Chunk.SIZE);

		Chunk chunk = getChunk(xc, yc, zc);
		if(chunk == null)return null;
		
		int xb = x % Chunk.SIZE;
		int yb = y % Chunk.SIZE;
		int zb = z % Chunk.SIZE;

		return chunk.getBlock(xb, yb, zb);
	}
	
	
	
}
