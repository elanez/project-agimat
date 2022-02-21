/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.util;

/**
 *
 * @author Elan Fernandez
 */
public class Timer {

    private long time, lastTime;
    private final long duration;
    private boolean start;
    
    public Timer(long cooldown){
        this.duration = cooldown;
        time = cooldown;
        lastTime = 0;
        start = false;
    }
    
    public boolean start(){
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();        
        
        return time > duration;
    }
    
//    public boolean start(){
//        if(restart){
//            start += System.currentTimeMillis() - lastTime;
//            lastTime = System.currentTimeMillis();
//            if(start > duration){
//                restart = false;
//                return true;
//            }
//            return false;
//        }
//        return true;
//    }
    
    public void restart(){
        time = 0;
    }
    
    public void fullRestart(){
        time =0;
        lastTime = time;
    }

    public long getTime() {
        return time;
    }
    
    
}
