package blocks;




import math.Color4f;
import math.Vector3f;


public abstract class Block {

	public static final Block OAK_WOOD = new OakWoodBlock(),
						GRASS = new GrassBlock(),
						LEAF = new LeafBlock(),
						FIR_LEAF = new FirLeafBlock(),
						FIR_WOOD = new FirWoodBlock();
	
	private float r,g,b,a;
	
	private static final float size = 1f;
	
	public Block(float r,float g,float b,float a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}


	public Block(Color4f c) {
		this.r = c.getR();
		this.g = c.getG();
		this.b = c.getB();
		this.a = c.getA();
	}


	public Block setColor(float r,float g,float b){
		
		this.r = r;
		this.g = g;
		this.b = b;
		
		return this;
	}
	
	public Block setColor(Color4f c){
		
		this.r = c.getR();
		this.g = c.getG();
		this.b = c.getB();
		
		return this;
	}

	public float[] getDataFront(float x,float y,float z,float[] shading) {
		return new float[]{
				x,y,z,								r * 0.9f * shading[0],g * 0.9f * shading[0],b * 0.9f * shading[0],a,
				x+size,y,z,							r * 0.9f * shading[1],g * 0.9f * shading[1],b * 0.9f * shading[1],a,
				x+size,y+size,z,					r * 0.9f * shading[2],g * 0.9f * shading[2],b * 0.9f * shading[2],a,
				x,y+size,z,							r * 0.9f * shading[3],g * 0.9f * shading[3],b * 0.9f * shading[3],a 
			};                                                                                                          
	}
	
	public float[] getDataBack(float x,float y,float z,float[] shading) {
		return new float[]{
				x+size,y,z+size,					r * 0.9f * shading[0],g * 0.9f * shading[0],b * 0.9f * shading[0],a,
				x,y,z+size,							r * 0.9f * shading[1],g * 0.9f * shading[1],b * 0.9f * shading[1],a,
				x,y+size,z+size,					r * 0.9f * shading[2],g * 0.9f * shading[2],b * 0.9f * shading[2],a,
				x+size,y+size,z+size,				r * 0.9f * shading[3],g * 0.9f * shading[3],b * 0.9f * shading[3],a 
		};                                                                                                              
	}

	public float[] getDataLeft(float x,float y,float z,float[] shading) {
		return new float[]{
				x,y,z,								r * 0.8f * shading[0],g * 0.8f * shading[0],b * 0.8f * shading[0],a,
				x,y+size,z,							r * 0.8f * shading[1],g * 0.8f * shading[1],b * 0.8f * shading[1],a,
				x,y+size,z+size,					r * 0.8f * shading[2],g * 0.8f * shading[2],b * 0.8f * shading[2],a,
				x,y,z+size,							r * 0.8f * shading[3],g * 0.8f * shading[3],b * 0.8f * shading[3],a 
		};                                                                                                              
	}
	
	public float[] getDataRight(float x,float y,float z,float[] shading) {
		return new float[]{
				x+size,y+size,z,					r * 0.8f * shading[0],g * 0.8f * shading[0],b * 0.8f * shading[0],a,
				x+size,y,z,							r * 0.8f * shading[1],g * 0.8f * shading[1],b * 0.8f * shading[1],a,
				x+size,y,z+size,					r * 0.8f * shading[2],g * 0.8f * shading[2],b * 0.8f * shading[2],a,
				x+size,y+size,z+size,				r * 0.8f * shading[3],g * 0.8f * shading[3],b * 0.8f * shading[3],a 
		};                                                                                                              
	}


	public float[] getDataDown(float x,float y,float z,float[] shading) {
		return new float[]{
				x+size,y,z,							r * 0.7f * shading[0],g * 0.7f * shading[0],b * 0.7f * shading[0],a,
				x,y,z,								r * 0.7f * shading[1],g * 0.7f * shading[1],b * 0.7f * shading[1],a,
				x,y,z+size,							r * 0.7f * shading[2],g * 0.7f * shading[2],b * 0.7f * shading[2],a,
				x+size,y,z+size,					r * 0.7f * shading[3],g * 0.7f * shading[3],b * 0.7f * shading[3],a 
                                                                                                                        
		};
	}

	public float[] getDataUp(float x,float y,float z,float[] shading) {
		return new float[]{
				x,y+size,z,							r * 1.0f * shading[0],g * 1.0f * shading[0],b * 1.0f * shading[0],a, 
				x+size,y+size,z,					r * 1.0f * shading[1],g * 1.0f * shading[1],b * 1.0f * shading[1],a, 
				x+size,y+size,z+size,				r * 1.0f * shading[2],g * 1.0f * shading[2],b * 1.0f * shading[2],a, 
				x,y+size,z+size,					r * 1.0f * shading[3],g * 1.0f * shading[3],b * 1.0f * shading[3],a  
		};                                                                                                               
	}

	public static float getSize() {
		return size;
	}

		
	
	
}
