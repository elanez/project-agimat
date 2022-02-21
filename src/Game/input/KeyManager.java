/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Elan Fernandez
 */
public class KeyManager implements KeyListener{

    private boolean[] keys, justPressed, cantPress;    
    public boolean up, down, left, rigth;
    public boolean atkBtn, escBtn, ctrlBtn, saveBtn, interactBtn, shftBtn, spinBtn, rangedBtn,healthBtn,manaBtn,debuffBtn,switchBtn;
    
    public KeyManager(){
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }
    
    public void update(){
        for(int i = 0; i < keys.length; i++){
            if(cantPress[i] && !keys[i]){
                cantPress[i] = false;
            }else if(justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }
        
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        rigth = keys[KeyEvent.VK_D];

        atkBtn = keys[KeyEvent.VK_J];
        rangedBtn = keys[KeyEvent.VK_K];
        spinBtn = keys[KeyEvent.VK_L];
        escBtn = keys[KeyEvent.VK_ESCAPE];
        saveBtn = keys[KeyEvent.VK_S];
        ctrlBtn = keys[KeyEvent.VK_CONTROL];
        shftBtn = keys[KeyEvent.VK_SHIFT];
        interactBtn = keys[KeyEvent.VK_K];
        healthBtn = keys[KeyEvent.VK_1];
        manaBtn = keys[KeyEvent.VK_2];
        debuffBtn = keys[KeyEvent.VK_3];
        switchBtn = keys[KeyEvent.VK_R];
    }
    
    public boolean keyJustPressed(int keyCode){
            if(keyCode < 0 || keyCode >= keys.length)
		return false;
            return justPressed[keyCode];
	}
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) { 
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }
    
}
