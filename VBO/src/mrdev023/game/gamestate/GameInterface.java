package mrdev023.game.gamestate;

import mrdev023.world.*;

public interface GameInterface {

	public void render();
	public void update();
	public void renderGUI();
	public void destroyGameState();
	public World getWorld();
	public void updateMouse();
	public void updateKeyboard();
	
}
