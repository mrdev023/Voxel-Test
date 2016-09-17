package mrdev023.rendering.gui;

import java.awt.*;

import mrdev023.math.*;

import org.lwjgl.opengl.*;

public class Text {
	
	public static int getWidth(String a){
		return a.length() * 10;
	}
	
	public static int getHeight(){
		return 8;
	}

	public static void drawString(String s, int x, int y,int size,Color4f color) {
	      int startX = x;
	      int a = 10;
	      GL11.glPushMatrix();
	      GL11.glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
	      GL11.glPointSize(size);
	      GL11.glBegin(GL11.GL_POINTS);
	      char[] c = s.toCharArray();
	      for(char c1 : c){
	    	  switch(c1){
	    	  	case 'a':
	    	  		GL11.glVertex2f(x, y);
	    	  		break;
	    	  
	    	  }
	      }	      
	      GL11.glEnd();
	      GL11.glPopMatrix();
	   }
	
	private static String chars = "abcdefghijklmnopqrstuvwxyz 0123456789:!?.,()";

	public static void drawString(String msg, int x, int y, int size) {
		msg = msg.toLowerCase();
		// Texture.font.bind();
		GL11.glBegin(GL11.GL_QUADS);
		for (int i = 0; i < msg.length(); i++) {
			int xi = chars.indexOf(msg.charAt(i));
			int yi = 0;
			if (xi >= 27) {
				xi %= 27;
				yi++;
			}
			if ((yi >= 0) && (xi >= 0)) {
				quadData(x + i * size, y, size, size, xi, yi, 27.0F, 4.0F);
			}
		}
		GL11.glEnd();
		// Texture.font.unbind();
	}

	public static void quadData(int x, int y, int w, int h, int xo, int yo,
			float xSize, float ySize) {
		GL11.glTexCoord2f((0 + xo) / xSize, (0 + yo) / ySize);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f((1 + xo) / xSize, (0 + yo) / ySize);
		GL11.glVertex2f(x + w, y);
		GL11.glTexCoord2f((1 + xo) / xSize, (1 + yo) / ySize);
		GL11.glVertex2f(x + w, y + h);
		GL11.glTexCoord2f((0 + xo) / xSize, (1 + yo) / ySize);
		GL11.glVertex2f(x, y + h);
	}
	
}
