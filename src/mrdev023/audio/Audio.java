package mrdev023.audio;

import java.io.*;

import org.lwjgl.util.*;
import static org.lwjgl.openal.AL10.*;

public class Audio {
	
	private String fileName;
	private WaveData soundData;
	private int buffer,source;
	
	public static final int INITIAL_STATE = 4113,PAUSED_STATE = 4115,STOPPED_STATE = 4116,PLAYING_STATE = 4114;

	public Audio(String fileName) throws FileNotFoundException{
		this.fileName = fileName;
		setSound();
	}
	
	private void setSound() throws FileNotFoundException{
		soundData = WaveData.create(new BufferedInputStream(new FileInputStream("res" + File.separatorChar +
                "sounds" + File.separatorChar + fileName)));
		buffer = alGenBuffers();
        alBufferData(buffer, soundData.format, soundData.data, soundData.samplerate);
        soundData.dispose();
        source = alGenSources();
        alSourcei(source, AL_BUFFER, buffer);
        
	}
	
	public void playSound(){
		alSourcePlay(source);
	}
	
	public int getStateSound(){
		return alGetSourcei(source, AL_SOURCE_STATE);
	}
	
	public boolean isStopped(){
		if(alGetSourcei(source, AL_SOURCE_STATE) == STOPPED_STATE)return true;
		else return false;
	}
	
	public boolean isPaused(){
		if(alGetSourcei(source, AL_SOURCE_STATE) == PAUSED_STATE)return true;
		else return false;
	}
	
	public boolean isPlaying(){
		if(alGetSourcei(source, AL_SOURCE_STATE) == PLAYING_STATE)return true;
		else return false;
	}
	
	public boolean isInitial(){
		if(alGetSourcei(source, AL_SOURCE_STATE) == INITIAL_STATE)return true;
		else return false;
	}
	
	public void stopSound(){
		alSourceStop(source);
	}

	public void pauseSound(){
		alSourcePause(source);
	}
	
	public void rewindSound(){
		alSourceRewind(source);
	}
	
	public void setGain(float gain){
		if(gain > 1.0f)gain = 1.0f;
		if(gain < 0.0f)gain = 0.0f;
		alSourcef(source, AL_GAIN, gain);
	}
	
	public void setPitch(float pitch){
		if(pitch < 0.0f)pitch = 0.0f;
		alSourcef(source, AL_PITCH, pitch);
	}
	
	
	public float getGain(){
		return alGetSourcef(source, AL_GAIN);
	}
	
	public float getPitch(){
		return alGetSourcef(source, AL_PITCH);
	}
	
	public void setLooping(boolean looping){
		if(looping){
			alSourcef(source, AL_LOOPING, AL_TRUE);
		}else{
			alSourcef(source, AL_LOOPING, AL_FALSE);
		}
	}
	
	public void destroySound(){
		alDeleteSources(source);
		alDeleteBuffers(buffer);
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		setSound();
	}

	public WaveData getSoundData() {
		return soundData;
	}

	public void setSoundData(WaveData soundData) {
		this.soundData = soundData;
	}

	public int getBuffer() {
		return buffer;
	}

	public void setBuffer(int buffer) {
		this.buffer = buffer;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}


	
	
	
}
