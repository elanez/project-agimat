/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Projectile;

import Game.Handler;
import Game.entity.Entity;
import Game.entity.creatures.Creature;
import Game.entity.creatures.Normie;
import Game.entity.creatures.Player;
import Game.entity.statics.StaticEntity;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Kevin
 */
public class ProjectileController {
    public final LinkedList<Projectile> projectileList;
    public Handler handler;
    public Rectangle attackRange;
    public Projectile projectile;
    private int element;
    
    public ProjectileController(Handler handler){
        projectileList = new LinkedList<Projectile>();
        this.handler = handler;
        attackRange = new Rectangle();
       // addProjectile(new Projectile(handler, 300,300, 0));
       this.element = element;
    }
    public void update() {
        for(int i = 0; i < projectileList.size(); i++){
            projectile = projectileList.get(i);
            attackRange = new Rectangle(projectile.getX(), projectile.getY(), 80, 80);
            
            for(Entity e : handler.getWorld().getEntityManager().getMobs()){
                if(!(e instanceof Creature)  || e instanceof Normie || e instanceof Player)
                    continue;
                if(projectile.getX() > handler.getWorld().getPlayer().getX() + 400 || projectile.getX() < handler.getWorld().getPlayer().getX() - 400 ||
                        projectile.getY() > handler.getWorld().getPlayer().getY() + 400 || projectile.getY() < handler.getWorld().getPlayer().getY() - 400)
                        removeProjectile(projectile);
                if(e.getCollisionBounds(0, 0).contains(attackRange) || e.getCollisionBounds(0, 0).intersects(attackRange)){
                    
                    switch(handler.getWorld().getPlayer().getCount()){
                            case 0:
                                ((Creature)e).setSlowed(true);
                                break;
                            case 1:
                                ((Creature)e).setBurning(true);
                                break;
                            case 2:
                                ((Creature)e).spinKnockback(e);
                                break;
                            case 3:
                                ((Creature)e).setStunned(true);
                                break;
                    }
                    switch(handler.getWorld().getPlayer().getInventory().getEquipID()){
                        case 0: 
                            e.hurt(handler.getWorld().getPlayer().getDamage()/3);
                            break;
                        case 1:
                            e.hurt(handler.getWorld().getPlayer().getDamage()/2);
                            break;
                        case 2:
                            e.hurt(handler.getWorld().getPlayer().getDamage()/2);
                            break;
                        case 3:
                            e.hurt(handler.getWorld().getPlayer().getDamage()/2);
                            break;
                    }
//                    ((Creature)e).knockback(projectile.getFace(), e);
//                    removeProjectile(projectile);
                }
               
            }
            projectile.update();
        }
    }
    public void render(Graphics graphics){
        for(int i = 0; i < projectileList.size(); i++){
            projectileList.get(i).render(graphics);
//            Rectangle test = projectileList.get(i).getCollisionBounds(0,0);
//            graphics.setColor(Color.yellow);
//            graphics.fillRect(test.x, test.y, test.width, test.height);
        }
    }
    public void addProjectile(Projectile projectile){
        projectileList.add(projectile);
    }
    public void removeProjectile (Projectile projectile){
        projectileList.remove(projectile);
    }
}
