/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.statics;

import Game.Handler;
import Game.graphics.Assets;
import java.awt.Graphics;

/**
 *
 * @author Kevin
 */
public class Kubo2 extends Kubo{
    
    public Kubo2(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        
        bounds.x = 10;
        bounds.y = 100;
        bounds.width = 387;
        bounds.height = 290;
    }
    
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.kubo2, (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }
}

