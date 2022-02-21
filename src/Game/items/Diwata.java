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
 * @author Lance
 */
public class Diwata extends Item implements Potion {

    public Diwata(Handler handler,int id) {
        super(handler, Assets.diwata, "Diwata", id);
        
        price = 200;
        
    }

     @Override
    public Item createNew(int count) {
        Item i = new Diwata(handler, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    @Override
    public Item createNew(int x, int y) {
        Item i = new Diwata(handler,id);
        i.setPosition(x, y);
        return i;
    }

    public ClickListener equip() {
        Item i = this;
         return ((new ClickListener() {
            @Override
            public void onClick(){  
                handler.getWorld().getPlayer().getInventory().equipActivate();
                handler.getWorld().getPlayer().setHealerActive(1);
                handler.getWorld().getPlayer().getInventory().setDisplayImage(i.image);
                handler.getWorld().getPlayer().setDiwataActive(true);
            }
        }));
    }

    @Override
    public void buff() {
       int hBuff = 5;
        if(handler.getWorld().getPlayer().getHealth() + hBuff >= 100)
            handler.getWorld().getPlayer().setHealth(100);
        else
            handler.getWorld().getPlayer().setHealth(handler.getWorld().getPlayer().getHealth()+ hBuff);
    }
       
    }

    

