package mrdev023.main;


import java.util.concurrent.*;

import mrdev023.game.*;
import mrdev023.gameEngine.*;
import mrdev023.rendering.*;
import mrdev023.server.*;
import mrdev023.update.*;
import mrdev023.world.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

public class Main {

	public static ExecutorService mainPool;
	
	/**
	 * @param args
	 * @Info Fonction principal
	 */
	public static void main(String[] args) {
		boolean IsServer = false;
		
		for(String arg : args){
			if(arg.equals("-server"))IsServer = true;
		}
		
		if(IsServer){
			Server.initServer();
		}else{
			GameEngine.initWindow();
		}
	}
	
	public static Runnable addThread(Runnable t,String name){
		mainPool.submit(t);
		return t;
	}

}
