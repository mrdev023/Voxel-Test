package mrdev023.rendering.gui;

import static org.lwjgl.opengl.GL11.*;
import mrdev023.rendering.*;

import org.newdawn.slick.*;


public class ButtonGUI extends GUI{
	
	public boolean IsLoaded = false;
	
	public float px,py,pwidth,pheight;

	public ButtonGUI(String value,float x,float y,float width,float height){
		super(value,x * DisplayManager.getWidth(), y * DisplayManager.getHeight(), width * DisplayManager.getWidth(), height * DisplayManager.getHeight(),24);
		if(!IsLoaded){
			int size = (int)((height * DisplayManager.getHeight())/2);
			setFont(new TrueTypeFont (new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, size),false));
			IsLoaded = true;
		}
		this.px = x;
		this.py = y;
		this.pwidth = width;
		this.pheight = height;
	}
	
	public void render() 
	{
		glPushMatrix();
		glLoadIdentity();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		if(mouseIsCollide() && IsHover){
			font.drawString((int)(x + (width/2 - font.getWidth(value)/2)),(int)(y + font.getHeight()/2 - height/16),value,Color.green);
		}else{
			font.drawString((int)(x + (width/2 - font.getWidth(value)/2)),(int)(y + font.getHeight()/2 - height/16),value,Color.red);
		}
		glDisable(GL_BLEND);
		if(mouseIsCollide() && IsHover)glColor4f(0,1,0,1);
		else glColor4f(1,0,0,1);
		glLineWidth(height/(height/2));
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glBegin(GL_QUADS);	
			glVertex2f(x,y);
			glVertex2f(x + width,y);
			glVertex2f(x + width,y + height);
			glVertex2f(x,y + height);
		glEnd();
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		glPopMatrix();
	}
	
	public void update() {
		
	}

	public void updateGUI() {
		updateFrame(px * DisplayManager.getWidth(), py * DisplayManager.getHeight(), pwidth * DisplayManager.getWidth(), pheight * DisplayManager.getHeight(),(int)((pheight * DisplayManager.getHeight())/2));
	}

}
