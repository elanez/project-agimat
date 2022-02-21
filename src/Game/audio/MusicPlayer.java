/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.audio;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class MusicPlayer implements Runnable{
    private final ArrayList<AudioFile> musicFiles;
    private int currentSongIndex = 0;
    private volatile boolean running;
    
    public MusicPlayer(){
        musicFiles = new ArrayList<>();
        musicFiles.add(new AudioFile("src\\res\\audio\\newAudio.wav"));
    }
    
    @Override
    public void run() {
        running = true;
        AudioFile song = musicFiles.get(currentSongIndex);
        song.play();
        while(running){
            if(!song.isPlaying()){
                currentSongIndex++;
                if(currentSongIndex >= musicFiles.size())
                    currentSongIndex = 0;
                song = musicFiles.get(currentSongIndex);
                song.play();
            }
            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        
    }

    public void stop() {
        System.out.println("stopping music thread");
        running = false;
    }
    
    
    
}
