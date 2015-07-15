package main;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import rendering.*;

public class Main {

	private static boolean IsRunning = true;
	private static long current = System.currentTimeMillis(), elapsedInfo = 0,
			elapsed = 0, previous = 0;
	private static long timeTicks = 0, timeFps = 0;
	private static int FPS = 0, TICKS = 0, LAST_TICKS = 60, LAST_FPS = 60;

	private static final String TITLE = "Test VBO";
	private static final int width = 1280, height = 720;
	
	private static Thread[] threadArray;

	private static Game game;

	/**
	 * @param args
	 * @Info Fonction principal
	 */
	public static void main(String[] args) {
		try {
			Display.setTitle(TITLE);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setResizable(true);
			Mouse.setGrabbed(true);
			Display.create();
			threadArray = new Thread[Runtime.getRuntime().availableProcessors()];
			game = new Game();
			loop();
		} catch (Exception e) {

		}
	}
	
	public static Thread addThread(Thread t){
		for(Thread c : threadArray){
			c = t;
			try {
				c.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}

	/**
	 * @Info Boucle principal avec Timer
	 */
	public static void loop() {
		while (IsRunning) {
			previous = current;
			current = System.currentTimeMillis();
			elapsed += current - previous;
			elapsedInfo += current - previous;
			DisplayManager.setDelta(current - previous);

			if (Display.wasResized()) {
				DisplayManager.updateDisplay();
			}

			if (elapsed >= 1000 / 60) {
				Update.updateMouse();
				Update.updateKeyboard();
				Update.update();
				TICKS++;
				elapsed = 0;
				timeTicks = System.currentTimeMillis() - current;
			} else {
				DisplayManager.clearScreen();
				Shader.MAIN.bind();
				DisplayManager.preRender3D();
				DisplayManager.render3D();
				DisplayManager.preRender2D();
				DisplayManager.render2D();
				FPS++;
				timeFps = System.currentTimeMillis() - current;
			}

			if (elapsedInfo >= 1000) {
				LAST_FPS = FPS;
				LAST_TICKS = TICKS;
				Display.setTitle(TITLE + " | FPS:" + LAST_FPS + " TICKS:"
						+ LAST_TICKS + " timeFps:" + timeFps + "ms timeTicks:"
						+ timeTicks + "ms" + " | PX:"
						+ Camera.getPosition().getX() + " PY:"
						+ Camera.getPosition().getY() + " PZ:"
						+ Camera.getPosition().getZ());
				FPS = 0;
				TICKS = 0;
				elapsedInfo = 0;
			}
		}
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

	public static Game getGame() {
		return game;
	}

}
