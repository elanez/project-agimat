/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Projectile;

import Game.Handler;
import Game.graphics.Animation;
import java.awt.Graphics;

/**
 *
 * @author Kevin
 */
public class EnemyProjectile extends Projectile{
    
    public EnemyProjectile(Handler handler, int x, int y, int face, Animation projectileAnimation) {
        super(handler, x, y, face, projectileAnimation);
        size = 50;
        this.x = x + 50;
        this.y = y + 50;
    }
    
    @Override
    public void update(){
        move();
       // System.out.println("nani");
        projectileAnimation.update();
    }
    @Override
    public void render(Graphics graphics){
        graphics.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getCamera().getxOffset()) , (int)(y - handler.getCamera().getyOffset()),size,size, null); 
    }
   
    
}
