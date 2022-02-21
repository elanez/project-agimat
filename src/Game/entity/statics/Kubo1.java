/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.statics;

import Game.Handler;
import Game.entity.Entity;
import Game.graphics.Assets;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public class Kubo1 extends Kubo{

    public Kubo1(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        bounds.y = 100;
        bounds.width = 370;
        bounds.height = 290;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.kubo1, (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }
    
}

