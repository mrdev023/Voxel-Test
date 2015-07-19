package main;

import rendering.*;

import java.util.concurrent.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import rendering.*;
import vanilla.java.affinity.*;
import vanilla.java.affinity.impl.*;
import world.*;

public class Main {

	private static boolean IsRunning = true;
	private static long current = System.currentTimeMillis(),current2, elapsedInfo = 0,
			elapsed = 0, previous = 0;
	private static long timeTicks = 0, timeFps = 0;
	private static int FPS = 0, TICKS = 0, LAST_TICKS = 60, LAST_FPS = 60;

	private static final String TITLE = "Test VBO";
	private static final int width = 1280, height = 720;
	

//	private static AffinityLock al;
	public static ExecutorService mainPool;
	
	private static Game game;
	private static SkyBox skybox;

	/**
	 * @param args
	 * @Info Fonction principal
	 */
	public static void main(String[] args) {
//		mainPool = ForkJoinPool.commonPool();
		mainPool = Executors.newWorkStealingPool();
//		AffinityLock.cpuLayout(new NoCpuLayout(Runtime.getRuntime().availableProcessors() + 8));
//		al = AffinityLock.acquireLock();
//		System.out.println(AffinityLock.cpuLayout().coresPerSocket())
		try {
			Display.setTitle(TITLE);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setResizable(true);
			Display.create();
			String back = "/tex/cubemap/back.jpg";
			String bottom = "/tex/cubemap/bottom.jpg";
			String front = "/tex/cubemap/front.jpg";
			String top = "/tex/cubemap/top.jpg";
			String left = "/tex/cubemap/left.jpg";
			String right = "/tex/cubemap/right.jpg";
			
			skybox = new SkyBox(new String[] { right, left, top, bottom, back,
					front });
			Camera.initCamera();
			game = new Game();
			Mouse.setGrabbed(true);
			loop();
		} catch (Exception e) {

		}
	}
	
	public static Runnable addThread(Runnable t,String name){
		mainPool.submit(t);
//		mainPool.execute(t);
//		new Thread(t, name).start();
//		System.out.println("Details" + AffinityLock.dumpLocks());
		return t;
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

			if (Display.wasResized()) {
				DisplayManager.updateDisplay();
			}

			current2 = System.nanoTime();
			if (elapsed >= 1000 / 60) {
				Update.updateMouse();
				Update.updateKeyboard();
				Update.update();
				TICKS++;
				elapsed = 0;
				timeTicks = System.nanoTime() - current2;
			} else {
				DisplayManager.clearScreen();
				DisplayManager.preRender3D();
				Camera.renderCamera();
				Shader.SKYBOX.bind();
				skybox.render(Camera.getPosition());
				Shader.MAIN.bind();
				DisplayManager.render3D();
				DisplayManager.preRender2D();
				DisplayManager.render2D();
				FPS++;
				timeFps = System.nanoTime() - current2;
			}

			if (elapsedInfo >= 1000) {
				LAST_FPS = FPS;
				LAST_TICKS = TICKS;
				boolean nx = false, ny = false, nz = false;
				if(Camera.getPosition().getX() < 0)nx = true;
				if(Camera.getPosition().getY() < 0)ny = true;
				if(Camera.getPosition().getZ() < 0)nz = true;
				Display.setTitle(TITLE + " | FPS:" + (int)(1000000000.0f/timeFps) + " TICKS:"
						+ LAST_TICKS  + " timeFps:" + timeFps + "ns timeTicks:"
						+ timeTicks + "ns" + " | PX:"
						+ Camera.getPosition().getX() + " PY:"
						+ Camera.getPosition().getY() + " PZ:"
						+ Camera.getPosition().getZ() + " | "
						+ World.updateWorldTime + " " + DisplayManager.getDelta()
						+ " " + Update.getSelectedBlock() + " | "
						+ getGame().getWorld().getBlock((int)Camera.getPosition().getX(), (int) Camera.getPosition().getY(), (int) Camera.getPosition().getZ(),nx,ny,nz) + " | "
						+ getGame().getWorld().getLocalChunk((int)Camera.getPosition().getX(), (int) Camera.getPosition().getY(), (int) Camera.getPosition().getZ(),nx,ny,nz).toString());
				FPS = 0;
				TICKS = 0;
				elapsedInfo = 0;
			}
			
			Display.update();
		}
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

	public static Game getGame() {
		return game;
	}

}
