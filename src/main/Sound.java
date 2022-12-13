package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        
        soundURL[0] = getClass().getResource("/main/res/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/main/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/main/res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/main/res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/main/res/sound/fanfare.wav");

    }
    
    public void setFile(int i){

        try {
            
            //Format to open audio files in Java
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            
        }

    }

    public void play(){

        //Called when we wanted to play a sound file
        clip.start();

    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){

        clip.stop();

    }
}
