package mrdev023.game.gamestate;

import mrdev023.game.*;
import mrdev023.world.*;

public enum GameState {

	MAIN_MENU(new MainMenu()),OPTION_MENU(new OptionMenu()),ABOUT_MENU(new AboutMenu()),SOLO_GAME(new SoloGame()),MULTI_GAME(new MultiGame());
	
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
	
	public void updateGUI() {
		this.gameInterface.updateGUI();
	}
	
	public void init() {
		this.gameInterface.init();
	}
}
