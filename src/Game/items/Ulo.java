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
public class Ulo extends Item {

    public Ulo(Handler handler,int id) {
        super(handler, Assets.ulo, "ULO", id);
        
        price = 800;
        
    }

     @Override
    public Item createNew(int count) {
        Item i = new Ulo(handler, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    @Override
    public Item createNew(int x, int y) {
        Item i = new Ulo(handler,id);
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
            }
        }));  
    }
    
}
