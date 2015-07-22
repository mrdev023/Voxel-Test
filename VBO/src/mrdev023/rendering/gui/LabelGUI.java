package mrdev023.rendering.gui;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.*;

public class LabelGUI extends GUI{

	public LabelGUI(String value,float x, float y, float width, float height) {
		super(value,x, y, width, height,(int)(height/2));
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

}
