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
public class DebuffPotion extends Item implements Potion{
    
    public DebuffPotion(Handler handler,int id) {
        super(handler,Assets.bGreenPotion, "Big green Potion", id);
        
        description = "Defuff";
        price = 10;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public Item createNew(int count) {
        Item i = new DebuffPotion(handler,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    @Override
    public Item createNew(int x, int y) {
        Item i = new DebuffPotion(handler,id);
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
        handler.getWorld().getPlayer().setSpeed(5);
    }
    
}
