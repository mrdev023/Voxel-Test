package mrdev023.update;
import mrdev023.gameEngine.*;


public class Update {
	
	/**
	 * @Info Fonction permettant de gerer les action de la souris
	 */
	public static void updateMouse(){
		GameEngine.getGameState().updateMouse();
	}
	
	/**
	 * @Info Fonction permettant de gerer les action du clavier
	 */
	public static void updateKeyboard(){
		GameEngine.getGameState().updateKeyboard();
	}
	
	/**
	 * @Info Fonction de mettre a jour le display et d'autre choses predefinie
	 */
	public static void update(){
		GameEngine.getGameState().update();
	}	
	
	
}
