/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.statics;

import Game.Handler;

/**
 *
 * @author Elan Fernandez
 */
public abstract class Kubo extends StaticEntity{

    public Kubo(Handler handler, float x, float y, int id) {
        super(handler, x, y, 400,400,id);
        
//        bounds.x = (int)x;
//        bounds.y = (int)y;
//        bounds.width = 300;
//        bounds.height = 225;
    }
    
    @Override
    public void update(){
        
    }

    @Override
    public void hurt(double damage){
        
    }
    
}
