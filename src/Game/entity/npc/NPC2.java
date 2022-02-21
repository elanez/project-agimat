/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.npc;

import Game.Handler;
import Game.graphics.Assets;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public class NPC2 extends NPC{

    public NPC2(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        
        bounds.x = width/2 - width/6;
        bounds.y = height/2;
        bounds.width = width/3;
        bounds.height = height/2;
        
        name = "Arnold";
        message = ". . .";
    }

    @Override
    public void interact() {
        handler.getWorld().getPlayer().getDialog().setDialog(name, message);
        handler.getWorld().getPlayer().getDialog().infoSwitch();
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.npc2_down[0],  (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
    }
    
}
