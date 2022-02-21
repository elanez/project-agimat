/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.statics;

import Game.Handler;
import Game.entity.Entity;

/**
 *
 * @author Elan Fernandez
 */
public abstract class StaticEntity extends Entity{
    
    public StaticEntity(Handler handler, float x, float y, int width, int height, int id) {
        super(handler, x, y, width, height, id);
    }
    
    @Override
    public void die() {

    }
    
}
