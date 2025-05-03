package com.example.TaskTracker;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Soundalarm {

	public static void playalarm() {
		// TODO Auto-generated method stub
		try
		{
			File soundfile = new File("alarm.wav");
			AudioInputStream audioin = AudioSystem.getAudioInputStream(soundfile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioin);
			clip.start();
			
		}
		catch(UnsupportedAudioFileException | IOException | LineUnavailableExcception e)
		{
			e.printStackTrace();
		}
	}

}
