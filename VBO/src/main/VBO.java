package main;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.*;

import org.lwjgl.*;

public class VBO {
	

	private int bufferSize = 0;
	private int vboID = 0;
	private FloatBuffer buffer;
	
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
		 if(buffer != null){
			 int size = buffer.limit();
			 float[] previousBuffer = new float[size];
			 buffer.get(previousBuffer);
			 buffer.clear();
			 buffer = BufferUtils.createFloatBuffer(size + a.length);
			 buffer.put(previousBuffer).put(a);
			 buffer.flip();
		 }else{
			 buffer = BufferUtils.createFloatBuffer(a.length);
			 buffer.put(a);
			 buffer.flip();
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
		 if(buffer != null){
			 int size = buffer.limit();
			 float[] previousBuffer = new float[size];
			 buffer.get(previousBuffer);
			 buffer.clear();
			 buffer = BufferUtils.createFloatBuffer(size + a.length);
			 buffer.put(previousBuffer).put(a);
			 buffer.flip();
		 }else{
			 buffer = BufferUtils.createFloatBuffer(a.length);
			 buffer.put(a);
			 buffer.flip();
		 }
	}
	
	/**
	 * @param id
	 * @param data
	 * @Info Stocke le FloatBuffer dans le GPU
	 */
	public void bufferData(){
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		bufferSize = buffer.limit() / 7;// 7 = 3 vertex(x,y,z) + 4 color (r,g,b,a)
	}
	
	/**
	 * @Info fait le rendu du vbo
	 */
	public void renderVBO(){
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glVertexAttribPointer(0,3,GL_FLOAT,false,7 * 4,0);
		glVertexAttribPointer(1,4,GL_FLOAT,false,7 * 4,12);
		
		glDrawArrays(GL_QUADS, 0, bufferSize);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
	}
	
	/**
	 * @Info detruit le vbo
	 */
	public void destroyVBO(){
		glDeleteBuffers(vboID);
	}
}
