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
public class ManaPotion extends Item implements Potion{
    
    public ManaPotion(Handler handler,int id) {
        super(handler,Assets.bBluePotion, "Big blue Potion", id);
        
        description = "+10 Mana";
        price = 30;
    }

    public String getName() {
        return name;
    }

    @Override
    public Item createNew(int count) {
        Item i = new ManaPotion(handler,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    @Override
    public Item createNew(int x, int y) {
        Item i = new ManaPotion(handler,id);
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
        int manaBuff = 10;
        if(handler.getWorld().getPlayer().getStamina() + manaBuff >= 100)
            handler.getWorld().getPlayer().setStamina(100);
        else
            handler.getWorld().getPlayer().setStamina(handler.getWorld().getPlayer().getStamina()+ manaBuff);
    }
    
}
