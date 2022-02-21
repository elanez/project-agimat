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
public class Kubo3 extends Kubo{
    
    public Kubo3(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
    }
    
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.kubo3, (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }
    
}
