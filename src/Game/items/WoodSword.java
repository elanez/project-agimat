/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.items;

import Game.Handler;
import Game.items.interfaces.Weapon;
import Game.graphics.Assets;
import Game.ui.ClickListener;

/**
 *
 * @author Elan Fernandez
 */
public class WoodSword extends Item implements Weapon{
    
    public WoodSword(Handler handler,int id) {
        super(handler,Assets.woodSword, "Wood Sword", id);
        description = "+50 DAMAGE";
        price = 1000;
    }

    @Override
    public void damageBuff() {
        int dmgBuff = 25;
        handler.getGameState().getPlayer().setDamage(dmgBuff);
    }

    @Override
    public Item createNew(int count) {
        Item i = new WoodSword(handler,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    @Override
    public Item createNew(int x, int y) {
        Item i = new WoodSword(handler,id);
        i.setPosition(x, y);
        return i;
    }

    
    
}
