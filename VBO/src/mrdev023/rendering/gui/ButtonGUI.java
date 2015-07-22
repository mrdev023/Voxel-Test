package mrdev023.rendering.gui;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.*;


public class ButtonGUI extends GUI{

	public ButtonGUI(String value,float x,float y,float width,float height){
		super(value,x, y, width, height,(int)(height/2));
	}
	
	public void render() 
	{
		glPushMatrix();
		glLoadIdentity();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		if(mouseIsCollide()){
			font.drawString((int)(x + (width/2 - font.getWidth(value)/2)),(int)(y + font.getHeight()/2 - height/16),value,Color.green);
		}else{
			font.drawString((int)(x + (width/2 - font.getWidth(value)/2)),(int)(y + font.getHeight()/2 - height/16),value,Color.red);
		}
		glDisable(GL_BLEND);
		if(mouseIsCollide())glColor4f(0,1,0,1);
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

}
