/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.npc;

import Game.Handler;
import Game.entity.Entity;
import Game.entity.creatures.Creature;

/**
 *
 * @author Elan Fernandez
 */
public abstract class NPC extends Entity{
    
    protected String name, message;
    protected boolean complete = false;
    protected int currentQuest;
    
    public NPC(Handler handler, float x, float y, int id) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT,id);
        
        currentQuest = 0;
    }
    
    public abstract void interact();

    @Override
    public void hurt(double damage){
        
    }
    
    @Override
    public void die() {
        
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getCurrentQuest() {
        return currentQuest;
    }

    public void setCurrentQuest(int currentQuest) {
        this.currentQuest = currentQuest;
    }
       
}
