/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.statics;

import Game.Handler;
import Game.entity.Entity;
import Game.entity.creatures.Creature;
import Game.graphics.Assets;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public class Tree extends StaticEntity{

    public Tree(Handler handler, float x, float y, int id) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH * 3, Creature.DEFAULT_CREATURE_HEIGHT * 3, id);

        
        bounds.x = (width*3)/8 + ((width/8)/2);
        bounds.y = (height*4)/5;
        bounds.width = width/7;
        bounds.height = height/5;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.tree, (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
        
//        graphics.setColor(Color.red);
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), 
//                (int)(y - handler.getCamera().getyOffset() + bounds.y),
//        bounds.width,bounds.height);
    }

    
    
}
