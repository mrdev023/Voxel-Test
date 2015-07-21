package mrdev023.world;

import java.util.*;

import mrdev023.rendering.*;
import mrdev023.world.chunk.*;

public class SoloWorld extends World{

	public SoloWorld(long seed, int octave, int amplitude) {
		super(seed, octave, amplitude);
	}

	public void update() {
		long current = System.currentTimeMillis();
		int xa = (int)((Camera.getPosition().getX()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) - VIEW_CHUNK;
		int xb = (int)((Camera.getPosition().getX()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) + VIEW_CHUNK;
		int za = (int)((Camera.getPosition().getZ()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) - VIEW_CHUNK;
		int zb = (int)((Camera.getPosition().getZ()-((float)Chunk.SIZE/2.0f))/(float)Chunk.SIZE) + VIEW_CHUNK;
		
		int delta_x = xb - xa;
		int delta_z = zb - za;
		for(int i = 0; i <= delta_x;i++){
			for(int k = 0;k <= delta_z;k++){
//				for(int j = 0; j < HEIGHT; j++){
					if(getChunk((xa + i), 0, (za + k)) != null)continue;
					Chunk ch = new Chunk((xa + i),0,(za + k),this);
					chunks.add(ch);
//				}
			}
		}
		ArrayList<Chunk> removeList = new ArrayList<Chunk>();
		for(int i = 0; i < chunks.size();i++){
			Chunk c = chunks.get(i);
			if(c.getPosition().getX() < xa || c.getPosition().getX() > xb || c.getPosition().getZ() < za || c.getPosition().getZ() > zb){
				c.destroyChunk();
				removeList.add(c);
			}else{
				if(!c.isLoaded() && !c.isGenerated() && !c.isCurrentGenerate() && !c.isDestroy())c.createChunk(this);
				if(!c.isLoaded() && c.isGenerated() && !c.isDestroy())c.loadBufferData();
				if(!c.isDestroy())c.update();
			}
		}
		for(Chunk c: removeList){
			removeByChunk(c);
		}
		removeList.clear();
		updateWorldTime = System.currentTimeMillis() - current;
	}

	public void render() {
		for(Chunk c : chunks){
			if(c.isLoaded()){
				c.render();
			}
		}		
	}

}
