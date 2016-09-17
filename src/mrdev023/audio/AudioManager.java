package mrdev023.audio;

import java.io.*;
import java.util.*;

import org.lwjgl.*;
import org.lwjgl.openal.*;

public class AudioManager {
	
	public static ArrayList<Audio> music = new ArrayList<Audio>();
	public static ArrayList<Audio> sound = new ArrayList<Audio>();

	public static void create() throws LWJGLException{
		AL.create();
	}
	
	public static void addMusic(String name){
		try {
			music.add(new Audio(name));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addSound(String name){
		try {
			sound.add(new Audio(name));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void destroy(){
		AL.destroy();
	}
	
}
