package mrdev023.rendering.gui;

import mrdev023.math.*;
import mrdev023.rendering.gui.action.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.*;

public abstract class GUI {

	protected float x,y,width,height;
	protected Color4f color;
	protected String value;
	
	public static final int NULL = 0,CLICKED = 1;
	
	public Action action;
	
	protected TrueTypeFont  font = null;
	
	public GUI(String value,float x,float y,float width,float height,int size){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = new Color4f(1f,1f,1f,1f);
		this.value = value;
		font = new TrueTypeFont (new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, size),false);
		this.action = new Action() {
			public void manageAction(int action) {}
			public void hover(Vector2f position) {}
		};
	}
	
	public abstract void render();
	public abstract void update();
	
	
	public void updateAction(){
		while(Mouse.next()){
			if(Mouse.getEventButtonState()){
				if(Mouse.getEventButton() == 0 && mouseIsCollide()){
					action.manageAction(CLICKED);
				}
			}else{
				
			}
		}
		if(mouseIsCollide())action.hover(new Vector2f(Mouse.getX(),(Display.getDisplayMode().getHeight() - Mouse.getY())));
	}
	
	public void setAction(Action a){
		this.action = a;
	}
	
	public boolean mouseIsCollide(){
		if(Mouse.getX() > x && Mouse.getX() < (x+width)
		&& (Display.getDisplayMode().getHeight() - Mouse.getY()) > y && (Display.getDisplayMode().getHeight() - Mouse.getY()) < (y+height))return true;
		
		return false;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Color4f getColor() {
		return color;
	}

	public void setColor(Color4f color) {
		this.color = color;
	}

	public Action getAction() {
		return action;
	}
	
	
}
