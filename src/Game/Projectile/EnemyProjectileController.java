/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Projectile;

import Game.Handler;
import Game.entity.Entity;
import java.awt.Rectangle;

/**
 *
 * @author Kevin
 */
public class EnemyProjectileController extends ProjectileController{
    
    public EnemyProjectileController(Handler handler) {
        super(handler);
    }
    
    @Override
    public void update() {
        for(int i = 0; i < projectileList.size(); i++){
            projectile = projectileList.get(i);
            attackRange = new Rectangle(projectile.getX(), projectile.getY(), 80, 80);
            
            for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                if(e.equals(this))
                    continue;
//                if(projectile.getX() > e.getX() + 400 || projectile.getX() < e.getX() - 400 ||
//                   projectile.getY() > e.getY() + 400 || projectile.getY() < e.getY() - 400)
//                        removeProjectile(projectile);
                if(handler.getWorld().getPlayer().getCollisionBounds(0f, 0f).contains(attackRange) || 
                   handler.getWorld().getPlayer().getCollisionBounds(0f, 0f).intersects(attackRange)){
                    handler.getWorld().getPlayer().hurt(0);
//                    handler.getWorld().getPlayer().knockback(projectile.getFace(), e);
//                      handler.getWorld().getPlayer().knockback(1, e);
//                    removeProjectile(projectile);
                }
               
            }
            projectile.update();
        }
    }
    
    
    public void addProjectile(EnemyProjectile enemyProjectile){
        projectileList.add(enemyProjectile);
    }
    
}
