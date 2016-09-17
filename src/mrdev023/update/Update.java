package mrdev023.update;
import java.util.*;

import org.lwjgl.input.*;

import mrdev023.gameengine.*;


public class Update {
	
	private static ArrayList<Integer> mousePressed = new ArrayList<Integer>();
	private static ArrayList<Integer> mouseReleased = new ArrayList<Integer>();
	private static ArrayList<Integer> keyboardPressed = new ArrayList<Integer>();
	private static ArrayList<Integer> keyboardReleased = new ArrayList<Integer>();

	/**
	 * @Info Fonction permettant de gerer les action de la souris
	 */
	public static void updateMouse(){
		mousePressed.clear();
		mouseReleased.clear();
		while(Mouse.next()){
			if(Mouse.getEventButtonState()){
				mousePressed.add(Mouse.getEventButton());
			}else{
				mouseReleased.add(Mouse.getEventButton());
			}
		}
		GameEngine.getGameState().updateMouse();
	}
	
	public static boolean mouseButtonPressed(int button){
		for(Integer m : mousePressed){
			if(m == button)return true;
		}
		return false;
	}
	
	public static boolean mouseButtonReleased(int button){
		for(Integer m : mouseReleased){
			if(m == button)return true;
		}
		return false;
	}
	
	/**
	 * @Info Fonction permettant de gerer les action du clavier
	 */
	public static void updateKeyboard(){
		keyboardPressed.clear();
		keyboardReleased.clear();
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				keyboardPressed.add(Keyboard.getEventKey());
			}else{
				keyboardReleased.add(Keyboard.getEventKey());
			}
		}
		GameEngine.getGameState().updateKeyboard();
	}
	
	public static boolean keyboardButtonPressed(int button){
		for(Integer m : keyboardPressed){
			if(m == button)return true;
		}
		return false;
	}
	
	public static boolean keyboardButtonReleased(int button){
		for(Integer m : keyboardReleased){
			if(m == button)return true;
		}
		return false;
	}
	
	/**
	 * @Info Fonction de mettre a jour le display et d'autre choses predefinie
	 */
	public static void update(){
		GameEngine.getGameState().update();
	}	
	
	
}
