/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.items;

import Game.Handler;
import Game.graphics.Assets;
import Game.items.interfaces.Weapon;
import Game.ui.ClickListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elan Fernandez
 */
public class GoldSword extends Item implements Weapon{

    public GoldSword(Handler handler,int id) {
        super(handler,Assets.goldSword, "Gold Sword", id);
        description = "+200 DAMAGE";
        price = 10000;
    }

    @Override
    public Item createNew(int count) {
        Item i = new GoldSword(handler, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    @Override
    public Item createNew(int x, int y) {
        Item i = new GoldSword(handler,id);
        i.setPosition(x, y);
        return i;
    }

    @Override
    public void damageBuff() {
        int dmgBuff = 200;
         handler.getGameState().getPlayer().setDamage(dmgBuff);
    }

    
    
}
