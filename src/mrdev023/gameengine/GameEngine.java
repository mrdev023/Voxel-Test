package mrdev023.gameengine;

import java.util.concurrent.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import mrdev023.audio.*;
import mrdev023.game.*;
import mrdev023.game.gamestate.*;
import mrdev023.main.*;
import mrdev023.rendering.*;
import mrdev023.update.*;
import mrdev023.world.*;

public class GameEngine {

	private static boolean IsRunning = true;
	private static long current = System.currentTimeMillis(),current2, elapsedInfo = 0,
			elapsed = 0, previous = 0;
	private static long timeTicks = 0, timeFps = 0;
	private static int FPS = 0, TICKS = 0, LAST_TICKS = 60, LAST_FPS = 60;

	private static final String TITLE = "Test VBO";
	private static final int width = 1280, height = 720;
	
	public static GameState gameState = GameState.MAIN_MENU;
	
	public static void initWindow(){
		Main.mainPool = Executors.newWorkStealingPool(1);
		try {
			Display.setTitle(TITLE);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setResizable(true);
			Display.create();
			AudioManager.create();
			Camera.initCamera();
			Mouse.setGrabbed(true);
			changeGameState(GameState.MAIN_MENU);
			loop();
		} catch (Exception e) {

		}
	}
	
	/**
	 * @Info Boucle principal avec Timer
	 */
	public static long time = 0;
	public static void loop() {
		while (IsRunning) {
			previous = current;
			current = System.currentTimeMillis();
			elapsed += current - previous;
			elapsedInfo += current - previous;
			DisplayManager.setDelta(current - previous);

			current2 = System.nanoTime();
			if ((float)elapsed >= 1000.0f / 60.0f) {
				Display.processMessages();
				Update.updateMouse();
				Update.updateKeyboard();
				Update.update();
				TICKS++;
				elapsed -= (long)(1000.0f/60.0f);
				timeTicks = System.nanoTime() - current2;
			} else {
				DisplayManager.clearScreen();
				DisplayManager.preRender3D();
				DisplayManager.render3D();
				DisplayManager.preRender2D();
				DisplayManager.render2D();
				FPS++;
				timeFps = System.nanoTime() - current2;
				Display.update(false);
			}

			if (elapsedInfo >= 1000) {
				LAST_FPS = FPS;
				LAST_TICKS = TICKS;
				Display.setTitle(TITLE + " | FPS:" + (int)(1000000000.0f/timeFps) + " TICKS:"
						+ LAST_TICKS  + " timeFps:" + timeFps + "ns timeTicks:"
						+ timeTicks + "ns" + " | PX:"
						+ Camera.getPosition().getX() + " PY:"
						+ Camera.getPosition().getY() + " PZ:"
						+ Camera.getPosition().getZ() + " | "
						+ World.updateWorldTime + " " + DisplayManager.getDelta()
						+ " | "
						+ (int)((((float)Runtime.getRuntime().freeMemory()/1024.0)/((float)Runtime.getRuntime().totalMemory()/1024.0f))*100) 
						+ "% free | "
						+ Runtime.getRuntime().totalMemory()/1024);
				FPS = 0;
				TICKS = 0;
				elapsedInfo = 0;
			}
			
		}
		destroy();
	}
	
	public static void changeGameState(GameState newGameState){
		gameState.destroyGameState();
		newGameState.init();
		gameState = newGameState;	
	}
	
	public static void destroy(){
		gameState.destroyGameState();
		AudioManager.destroy();
		Display.destroy();
	}

	public static String getStringByNoString(Object... a){
		String b = "";
		for(Object c : a)b+= c + " ";
		return b;
	}
	
	public static boolean isRunning() {
		return IsRunning;
	}

	public static void setRunning(boolean isRunning) {
		IsRunning = isRunning;
	}

	public static long getCurrent() {
		return current;
	}

	public static long getElapsedInfo() {
		return elapsedInfo;
	}

	public static long getElapsed() {
		return elapsed;
	}

	public static long getPrevious() {
		return previous;
	}

	public static long getTimeTicks() {
		return timeTicks;
	}

	public static long getTimeFps() {
		return timeFps;
	}

	public static int getFPS() {
		return FPS;
	}

	public static int getTICKS() {
		return TICKS;
	}

	public static int getLAST_TICKS() {
		return LAST_TICKS;
	}

	public static int getLAST_FPS() {
		return LAST_FPS;
	}

	public static String getTitle() {
		return TITLE;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static GameState getGameState() {
		return gameState;
	}

	
}
