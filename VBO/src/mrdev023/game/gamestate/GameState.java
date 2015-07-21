package mrdev023.game.gamestate;

import mrdev023.game.*;
import mrdev023.world.*;

public enum GameState {

	MAIN_MENU(new MainMenu()),SOLO_GAME(new SoloGame()),MULTI_GAME(new MultiGame()),OPTION_MENU(new OptionMenu()),ABOUT_MENU(new AboutMenu());
	
	GameInterface gameInterface;
	
	GameState(GameInterface gameInterface){
		this.gameInterface = gameInterface;
	}

	public void render(){
		this.gameInterface.render();
	}
	
	public void update(){
		this.gameInterface.update();
	}
	
	public void renderGUI(){
		this.gameInterface.renderGUI();
	}
	
	public void destroyGameState(){
		this.gameInterface.destroyGameState();
	}
	
	public World getWorld(){
		return this.gameInterface.getWorld();
	}

	public void updateMouse() {
		this.gameInterface.updateMouse();
	}

	public void updateKeyboard() {
		this.gameInterface.updateKeyboard();
	}
}
