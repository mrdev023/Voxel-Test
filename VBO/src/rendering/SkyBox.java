package rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import math.Vector3f;

import org.lwjgl.BufferUtils;

public class SkyBox {

	private int vbo;
	private FloatBuffer buffer;
	
	private int textureID;
	
	
	public SkyBox(String[] img){
		vbo = glGenBuffers();
		buffer = BufferUtils.createFloatBuffer(3 * 4 * 6);
		buffer.put(blockData());
		buffer.flip();
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		
		createCubeMap(img);
	}
	
	private void createCubeMap(String[] img){
		textureID = glGenTextures();
//		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_CUBE_MAP, textureID);
		for(int i = 0; i < img.length; i++){
			TextureData data = decode(img[i]);
			glTexImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0 , GL_RGBA,data.width, data.height,0,GL_RGBA,GL_UNSIGNED_BYTE,data.buffer);
		}
		glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		
		glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
		
	}
	
	private TextureData decode(String path){
		int[] pixels = null;
		int width = 0,height = 0;
		try{
			BufferedImage image = ImageIO.read(SkyBox.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		int[] data = new int[pixels.length];
		for(int i = 0; i < pixels.length; i++){
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b  << 16 | g << 8 | r;
		}
		
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data).flip();
		
		return new TextureData(glGenTextures(),width,height,buffer);
	}
	
	public void render(Vector3f pos){
		glPushMatrix();
//		glLoadIdentity();
		glTranslatef(pos.x, pos.y + 1.75f, pos.z);
		glBindTexture(GL_TEXTURE_CUBE_MAP, textureID);
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3,GL_FLOAT, false, 3*4, 0);
		glDrawArrays(GL_QUADS, 0, 4 * 6);
		glDisableVertexAttribArray(0);
		glBindTexture(GL_TEXTURE_CUBE_MAP, 0);
		glPopMatrix();
	}
	
	private float[] blockData(){
		int size = 500;
		
		return new float[] {
			+size,-size,-size,
			-size,-size,-size,	
			-size,+size,-size,
			+size,+size,-size,
			
			-size,-size,+size,	
			+size,-size,+size,
			+size,+size,+size,
			-size,+size,+size,
			
			-size,-size,-size,	
			+size,-size,-size,
			+size,-size,+size,
			-size,-size,+size,
			
			+size,+size,-size,
			-size,+size,-size,	
			-size,+size,+size,
			+size,+size,+size,
			
			-size,+size,-size,
			-size,-size,-size,	
			-size,-size,+size,
			-size,+size,+size,
			
			+size,-size,-size,	
			+size,+size,-size,
			+size,+size,+size,
			+size,-size,+size
		};
	}
	class TextureData{
		public TextureData(int id,int width, int height, IntBuffer buffer) {
			this.id = id;
			this.width = width;
			this.height = height;
			this.buffer = buffer;
		}
		public int id;
		public int width, height;
		public IntBuffer buffer;
	}
	
}
