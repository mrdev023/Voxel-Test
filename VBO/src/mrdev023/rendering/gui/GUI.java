package mrdev023.rendering.gui;

import mrdev023.math.*;
import mrdev023.rendering.gui.action.*;
import mrdev023.update.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.*;

public abstract class GUI {

	protected float x,y,width,height;
	protected Color4f color;
	protected String value;
	
	public static final int NULL = 0,CLICKED = 1;
	
	protected Action action = null;
	
	protected boolean IsHover = true;
	
	protected TrueTypeFont  font = null;
	
	public GUI(String value,float x,float y,float width,float height,int size){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = new Color4f(1f,1f,1f,1f);
		this.value = value;
		font = new TrueTypeFont (new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, size),false);
	}
	
	public abstract void render();
	public abstract void update();
	
	
	public void updateFrame(float x,float y,float width,float height,int size){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		font = null;
		font = new TrueTypeFont (new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, size),false);
	}
	
	public abstract void updateGUI();
	
	public void updateAction(){
		if(this.action != null){
			if(Update.mouseButtonPressed(0) && mouseIsCollide())action.manageAction(CLICKED);
			if(mouseIsCollide())action.hover(new Vector2f(Mouse.getX(),(Display.getDisplayMode().getHeight() - Mouse.getY())));
		}
	}
	
	public GUI setAction(Action a){
		this.action = a;
		return this;
	}
	
	public GUI setHoverAnimation(boolean a){
		this.IsHover = a;
		return this;		
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isIsHover() {
		return IsHover;
	}

	public void setIsHover(boolean isHover) {
		IsHover = isHover;
	}

	public TrueTypeFont getFont() {
		return font;
	}

	public void setFont(TrueTypeFont font) {
		this.font = font;
	}

	public static int getNull() {
		return NULL;
	}

	public static int getClicked() {
		return CLICKED;
	}
	
	
}
