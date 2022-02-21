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
public class sfxPlayer{
    private ArrayList<AudioFile> sfxFiles;
    private int currentSongIndex;
    private boolean running;
    
    public sfxPlayer()
    {
        currentSongIndex = 0;
        sfxFiles = new ArrayList<AudioFile>();
        sfxFiles.add(new AudioFile("src\\res\\audio\\attack1.wav"));
    }
    
    public void run() {
        running = true;
        AudioFile song = sfxFiles.get(currentSongIndex);
        song.play(0);
        
            
        
    }

    public void setCurrentSongIndex(int currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
    }
    
}
