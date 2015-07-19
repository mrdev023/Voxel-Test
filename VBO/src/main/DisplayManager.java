package main;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;


public class DisplayManager {

	private static long delta = 0;
	private static float fov = 45;
	
	/**
	 * @Info Nettoie l'ecran
	 */
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	/**
	 * @Info Definie le mode d'affichage pour le rendu en 3d
	 */
	public static void preRender3D(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, (float)Display.getDisplayMode().getWidth()/(float)Display.getDisplayMode().getHeight(), 0.1f, 1000f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_FRONT);
		glEnable(GL_TEXTURE_2D);
	}
	
	/**
	 * @Info Fait le rendu 3d
	 */
	public static void render3D(){
		Main.getGame().render();
		if(Update.getSelectedBlock() != null && Update.getSelectedVector() !=null){
			renderBlock((int)Update.getSelectedVector().x,(int)Update.getSelectedVector().y,(int)Update.getSelectedVector().z);
		}
	}
	
	/**
	 * @Info Definie le mode d'affichage pour le rendu en 2d
	 */
	public static void preRender2D(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0, Display.getDisplayMode().getWidth(), Display
				.getDisplayMode().getHeight(), 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	/**
	 * @Info Fait le rendu 2d
	 */
	public static void render2D(){
		Main.getGame().renderGUI();
	}

	/**
	 * @Info mets a jour la resolution de l'ecran
	 */
	public static void updateDisplay() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}

	public static long getDelta() {
		return delta;
	}

	public static void setDelta(long delta) {
		DisplayManager.delta = delta;
	}

	public static float getFov() {
		return fov;
	}

	public static void setFov(float fov) {
		DisplayManager.fov = fov;
	}
	
	private static void renderBlock(int x,int y ,int z){
		float s = 1;
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glDisable(GL_CULL_FACE);
		glLineWidth(2);
		glBegin(GL_QUADS);
			glVertex3f(x,y,z);
			glVertex3f(x + s,y,z);
			glVertex3f(x + s, y + s, z);
			glVertex3f(x,y + s,z);
			
			glVertex3f(x + s,y,z + s);
			glVertex3f(x,y,z + s);
			glVertex3f(x,y + s,z + s);
			glVertex3f(x + s, y + s, z + s);

			glVertex3f(x + s,y,z);
			glVertex3f(x,y,z);
			glVertex3f(x,y,z + s);
			glVertex3f(x + s,y,z + s);
			
			glVertex3f(x,y + s,z);
			glVertex3f(x + s,y + s,z);
			glVertex3f(x + s,y + s,z + s);
			glVertex3f(x,y + s,z + s);
			
			glVertex3f(x,y,z);
			glVertex3f(x,y + s,z);
			glVertex3f(x,y + s,z + s);
			glVertex3f(x,y,z + s);
			
			glVertex3f(x + s,y + s,z);
			glVertex3f(x + s,y,z);
			glVertex3f(x + s,y,z + s);
			glVertex3f(x + s,y + s,z + s);
		glEnd();
		glEnable(GL_CULL_FACE);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}
}
