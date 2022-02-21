/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.statics;

import Game.Handler;
import Game.entity.creatures.Creature;
import Game.graphics.Assets;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public class Ship extends StaticEntity{

    public Ship(Handler handler, float x, float y, int id) {
        super(handler, x, y, 400, 400, id);
        
        bounds.x = 10;
        bounds.y = 100;
        bounds.width = 387;
        bounds.height = 290;
    }
    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.ship,(int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
    }
    
}
