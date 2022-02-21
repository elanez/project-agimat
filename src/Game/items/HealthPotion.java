/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.items;

import Game.Handler;
import Game.graphics.Assets;
import Game.items.interfaces.Potion;
import Game.ui.ClickListener;

/**
 *
 * @author Elan Fernandez
 */
public class HealthPotion extends Item implements Potion{
    
    public HealthPotion(Handler handler,int id) {
        super(handler,Assets.bRedPotion, "Big Red Potion", id);
        
        description = "+25 Health";
        price = 20;
    }

    @Override
    public Item createNew(int count) {
        Item i = new HealthPotion(handler,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    @Override
    public Item createNew(int x, int y) {
        Item i = new HealthPotion(handler,id);
        i.setPosition(x, y);
        return i;
    }

    @Override
    public ClickListener equip() {
        Item i = this;
       return ((new ClickListener() {
            @Override
            public void onClick(){
                handler.getWorld().getPlayer().getInventory().setDisplayImage(i.image);
                handler.getWorld().getPlayer().getInventory().equipDeactivate();
                buff();
            }
        }));  
    }

    @Override
    public void buff() {
        int hBuff = 25;
        if(handler.getWorld().getPlayer().getHealth() + hBuff >= 100)
            handler.getWorld().getPlayer().setHealth(100);
        else
            handler.getWorld().getPlayer().setHealth(handler.getWorld().getPlayer().getHealth()+ hBuff);
    }
    
}
