/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.items;

import Game.Handler;
import Game.graphics.Assets;
import Game.ui.ClickListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lance
 */
public class WindAgimat extends Item {

    public WindAgimat(Handler handler,int id) {
        super(handler,Assets.windAgimat, "Fire Power", id);
        
        price = 100;
    }

    @Override
    public Item createNew(int count) {
        Item i = new WindAgimat(handler,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    @Override
    public Item createNew(int x, int y) {
        Item i = new WindAgimat(handler,id);
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
                handler.getWorld().getPlayer().getInventory().setItem(i);
                handler.getWorld().getPlayer().getInventory().equipActivate();
                handler.getWorld().getPlayer().getInventory().setEquipID(id);    
            }
        }));  
    }

}
