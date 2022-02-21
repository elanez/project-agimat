/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.audio;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 *
 * @author Kevin
 */
public class AudioFile implements LineListener{
    private File soundFile;
    private AudioInputStream ais;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip clip;;
    private FloatControl gainControl;
    private volatile boolean playing;
    
    public AudioFile(String fileName){
        soundFile = new File(fileName);
        try{
            ais = AudioSystem.getAudioInputStream(soundFile);
            format = ais.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN) ;
            gainControl.setValue(6);
        }catch(Exception e){
            e.printStackTrace();
            
        }
        
    }
    public void play(){
        gainControl.setValue(-10);
        clip.start();
        playing = true;
    }
    public void play(int volume){
        gainControl.setValue(volume);
        clip.start();
        playing = true;
    }
    public boolean isPlaying(){
        return playing;
    }
    @Override
    public void update(LineEvent event) {
        if(event.getType() == LineEvent.Type.START)
            playing = true;
        else if(event.getType() == LineEvent.Type.STOP){
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
            playing = false;
        }
    }
}
