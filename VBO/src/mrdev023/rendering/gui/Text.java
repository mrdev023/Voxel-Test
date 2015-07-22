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
	
}
