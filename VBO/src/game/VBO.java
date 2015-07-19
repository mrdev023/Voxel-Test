package game;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.*;
import java.util.*;

import org.lwjgl.*;

public class VBO {
	

	private int bufferSize = 0;
	private int vboID = 0;
	private FloatBuffer buffer;
	private ArrayList<Float> floatlist = new ArrayList<Float>();
	
	public VBO(){
		this.vboID = createVBO();
	}
	
	/**
	 * @Info creer le vbo
	 */
	public int createVBO(){
		return glGenBuffers();
	}
	
	/**
	 * @param a
	 * @Info () doit contenir par ligne au moins l'axe (x,y,z) + les couleurs (r,g,b,a)
	 * 			Ex: getFloatBufferByFloatArray(new float[]{x,y,z,r,g,b,a})
	 * 			
	 * 			
	 */
	
	public void clearBuffer(){
		buffer.clear();
	}
	
	public void addDataByFloatArray(float[] a){
		 if(a == null)return;
		 for(float c :a){
			 floatlist.add(c);
		 }
	}
	
	/**
	 * @param a
	 * @Info () doit contenir par ligne au moins l'axe (x,y,z) + les couleurs (r,g,b,a)
	 * 			Ex: getFloatBufferbyFloatList(x,y,z,r,g,b,a)
	 * 			
	 * 			
	 */

	public void addDataByFloatList(float... a){
		if(a == null)return;
		 for(float c :a){
			 floatlist.add(c);
		 }
	}
	
	/**
	 * @param id
	 * @param data
	 * @Info Stocke le FloatBuffer dans le GPU
	 */
	public void bufferData(){
		buffer = BufferUtils.createFloatBuffer(floatlist.size());
		for(Float f : floatlist){
			buffer.put(f);
		}
		buffer.flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
//		glBufferSubData(vboID, 0, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		bufferSize = buffer.limit() / 7;// 7 = 3 vertex(x,y,z) + 4 color (r,g,b,a)
	}
	
	/**
	 * @Info fait le rendu du vbo
	 */
	public void renderVBO(){
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);
		
		glVertexPointer(3, GL_FLOAT, 7 * 4, 0);
		glColorPointer(4, GL_FLOAT, 7 * 4, 12);
		
//		glVertexAttribPointer(0,3,GL_FLOAT,false,7 * 4,0);
//		glVertexAttribPointer(1,4,GL_FLOAT,false,7 * 4,12);
		
		glDrawArrays(GL_QUADS, 0, bufferSize);
		
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		
	}
	
	/**
	 * @Info detruit le vbo
	 */
	public void destroyVBO(){
		glDeleteBuffers(vboID);
	}
}
