/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.state;

import Game.Handler;
import Game.ui.UIManager;
import Game.world.WorldManager;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public abstract class State {
    
    private static State currentState = null;
    
    protected Handler handler;
    protected WorldManager worldManager;
    protected UIManager uiManager;
    
    public State(Handler handler){
        this.handler = handler;
    }
    
    public abstract void update();
    public abstract void render(Graphics graphics);
    
    public void activate(){
        handler.getMouseManager().setUIManager(uiManager);
        setState(this);
    }
    
    public static void setState(State state){
        currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }
    
    public WorldManager getWorldManager(){
        return worldManager;
    }
}
