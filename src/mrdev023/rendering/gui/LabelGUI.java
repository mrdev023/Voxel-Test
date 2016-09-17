package mrdev023.rendering.gui;

import static org.lwjgl.opengl.GL11.*;
import mrdev023.rendering.*;

import org.newdawn.slick.*;

public class LabelGUI extends GUI{
	
	public boolean IsLoaded = false;
	
	public float px,py,pwidth,pheight;

	public LabelGUI(String value,float x, float y, float width, float height) {
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

	public void render() {
		glPushMatrix();
		glLoadIdentity();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		font.drawString((int)(x + (width/2 - font.getWidth(value)/2)),(int)(y + font.getHeight()/2 - height/16),value,Color.red);
		glDisable(GL_BLEND);
		glPopMatrix();
	}

	public void update() {
		
	}

	public void updateGUI() {
		updateFrame(px * DisplayManager.getWidth(), py * DisplayManager.getHeight(), pwidth * DisplayManager.getWidth(), pheight * DisplayManager.getHeight(),(int)((pheight * DisplayManager.getHeight())/2));
	}

}
