/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.graphics;

import Game.util.Timer;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elan Fernandez
 */
public class Animation {

    private final Timer timer;
    private int index;
    private boolean startAnim;
    
    private BufferedImage[] frames;
    private BufferedImage[] leftFrames, rightFrames, upFrames, downFrames;
    
    public Animation(int speed, BufferedImage[] frames){
        this.frames = frames;
        index = (frames.length-1) ;
        timer = new Timer(speed);
        startAnim = false;
    }
    
    public Animation(int speed, BufferedImage[] left, BufferedImage[] right){
        this.leftFrames = left;
        this.rightFrames = right;
        timer = new Timer(speed);
    }

    public Animation(int speed, BufferedImage[] left, BufferedImage[] right, BufferedImage[] up, BufferedImage[] down) {
        this.leftFrames = left;
        this.rightFrames = right;
        this.upFrames = up;
        this.downFrames = down;
        timer = new Timer(speed);
    }
    
    public void update(){
        if(timer.start()){
            index++;
            if(frames != null){
                if(index >= frames.length){
                    index = 0;
                }  
            }else{
                if(index >= leftFrames.length){
                    index = 0;
                } 
            }
            timer.restart();
        }
    }
    
    public void singleUpdate(){
        if(startAnim){
            if(timer.start()){
                index++;
                if(leftFrames != null){
                    if(index >= leftFrames.length){
                        index = 0;
                        startAnim = false;
                        return;
                    }
                }
                else {
                     if(index >= frames.length){
                        index = 0;
                        startAnim = false;
                        return;
                    }
                }
                
                timer.restart();
            }
        }
    }
    
    public BufferedImage getCurrentFrame(int face){
        if(upFrames != null && downFrames != null){
           switch(face){
            case 0://left
               return leftFrames[index];
            case 1://right
                return rightFrames[index];
            case 2://up
               return upFrames[index];
            default://down
               return downFrames[index];
           }
        }else if (leftFrames != null && rightFrames != null){
            switch(face){
                case 0://left
                    return leftFrames[index];
                default://right
                    return rightFrames[index];
            }
        }
        else
            return frames[index];
    }
    
    public void startAnimation(){
        startAnim = true;
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
    public int getIndex(){
        return index;
    }
    
    public void setIndex(int index){
        this.index = index;
    }

    public boolean isAnimating() {
        return startAnim;
    }
}
