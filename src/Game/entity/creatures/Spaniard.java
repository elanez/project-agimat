/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.creatures;

import Game.Handler;
import Game.entity.Entity;
import Game.graphics.Animation;
import Game.graphics.Assets;
import Game.items.Item;
import Game.tiles.Tile;
import Game.util.Timer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Elan Fernandez
 */
public class Spaniard extends Enemy{

    private float enemyX, enemyY;
    private int enemyW, enemyH, enemyIndex;
    private boolean enemyLocked, searching;
    
    public Spaniard(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        
        bounds.x = 40;
        bounds.y = 50;
        bounds.width = 30;
        bounds.height = 30;
        
        moveAnim = new Animation(200,Assets.spaniardLeft,Assets.spaniardRight,Assets.spaniardUp,Assets.spaniardDown);
        atkAnim = new Animation(200,Assets.spaniardLeftAtk,Assets.spaniardRightAtk,Assets.spaniardUp,Assets.spaniardDownAtk);
        
        atkRange = new Rectangle();
        vision  = new Rectangle();
        
        atkRange.width = 200;
        atkRange.height = 200;
        
        vision.width = 300;
        vision.height = 300;
        
        atkTimer =  new Timer(2000);
        atkTimer2 = new Timer(500);
        moveTimer = new Timer(1000);
        
        health = 300;
        fullHealth = 300;
        damage = 25;
        face = 3;
        enemyLocked = false;
        searching = true;
    }
    
    private int findEnemy(){
        ArrayList<Entity> e = handler.getWorld().getEntityManager().getMobs();
        int c=0;
        int index = 0;
        searching = true;
        while(searching){
            index = (int) (Math.random() * e.size());
            if(!(e.get(index) instanceof Spaniard)){
                enemyLocked = true;
                break;
            }
            c++;
            if(c > 2)
                break;
        }
        if(c >= 2){
            enemyLocked = false;
        }
        return index;
    }
    
    private void checkEnemyIndex(){
        if(!(handler.getWorld().getEntityManager().getMobs().get(enemyIndex) instanceof Normie)){
            enemyLocked = false;
        }
    }

    @Override
    public void getInput(){
        
        xMove=0;
        yMove=0;
        
        if(!enemyLocked)
            enemyIndex = findEnemy();
        
        if(enemyIndex >= handler.getWorld().getEntityManager().getMobs().size() && !handler.getWorld().getEntityManager().getMobs().isEmpty()){
            enemyLocked = false;
            enemyIndex = findEnemy();
        }
        
        checkEnemyIndex();
            
        Rectangle playerBounds = handler.getWorld().getPlayer().getCollisionBounds(0, 0);
        if(vision.contains(playerBounds)){
            enemyW = handler.getWorld().getPlayer().getWidth();
            enemyH = handler.getWorld().getPlayer().getHeight();
            enemyX = handler.getWorld().getPlayer().getX();
            enemyY = handler.getWorld().getPlayer().getY();
            
            Rectangle cb = getCollisionBounds(0,0);
            atkRange.x = cb.x - (atkRange.width/2);
            atkRange.y = cb.y - (atkRange.height/2);
            vision.x = cb.x - (vision.width/2);
            vision.y = cb.y - (vision.height/2);

            test = atkRange;

            if((enemyX + (enemyW/2))+5 < x)
                xMove = -speed;
            if((enemyX)-15 > x)
                xMove = speed;
            if(enemyY+5 < y)
                yMove = -speed;
            if(enemyY-5 > y)
                yMove = speed;

            if(atkRange.contains(playerBounds))
                checkAttack();
        }else if(enemyLocked){
            enemyW = handler.getWorld().getEntityManager().getMobs().get(enemyIndex).getWidth();
            enemyH = handler.getWorld().getEntityManager().getMobs().get(enemyIndex).getHeight();
            enemyX = handler.getWorld().getEntityManager().getMobs().get(enemyIndex).getX();
            enemyY = handler.getWorld().getEntityManager().getMobs().get(enemyIndex).getY();
            
            Rectangle cb = getCollisionBounds(0,0);
            Rectangle enemyBounds = handler.getWorld().getEntityManager().getMobs().get(enemyIndex).getCollisionBounds(0, 0);
            atkRange.x = cb.x - (atkRange.width/2);
            atkRange.y = cb.y - (atkRange.height/2);
            vision.x = cb.x - (vision.width/2);
            vision.y = cb.y - (vision.height/2);

            test = atkRange;

            if((enemyX + (enemyW/2))+5 < x)
                xMove = -speed;
            if((enemyX)-15 > x)
                xMove = speed;
            if(enemyY+5 < y)
                yMove = -speed;
            if(enemyY-5 > y)
                yMove = speed;

            if(atkRange.contains(enemyBounds))
                checkAttack();
        }else{
            if(moveTimer.start()){
                stop++;
                if(stop == 1){
                     temp = (int)(Math.random() * 4);
                }else{
                    stop = 0;
                    temp = 5;
                }
               moveTimer.restart();
            }
            switch (temp) {
                case 0:
                    yMove = speed;
                    break;
                case 1:
                    xMove = -speed;
                    break;
                case 2:
                    yMove = -speed;
                    break;
                case 3:
                    xMove = speed;
                    break;
                default:
                    break;
               }
        }
    }

    @Override
    public BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0){//left
            face = 0;
        }else if(xMove > 0){//right
            face = 1;
        }else if(yMove < 0){//up
            face = 2;
        }else if(yMove > 0){//down
            face = 3;
        }else{//idle
            if(atkAnim.isAnimating())
                return atkAnim.getCurrentFrame(face);
            else{
                switch (face) {
                 case 0:
                    return Assets.spaniardLeft[0];
                case 1:
                    return Assets.spaniardRight[0];
                case 2:
                    return Assets.spaniardUp[0];
                default:
                    return Assets.spaniardDown[0];
                }
            }
        }
        if(atkAnim.isAnimating()){
            return atkAnim.getCurrentFrame(face);
        }else
            return moveAnim.getCurrentFrame(face);
    }

    @Override
    public void update() {
        moveAnim.update();
        getInput();
        attack();
        move();
        checkDebuff();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(this.getCurrentAnimationFrame(),(int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y +bounds.y -  handler.getCamera().getyOffset()), bounds.width, bounds.height);
        renderText(graphics);
        infoBar(graphics);
    }

    @Override
    public void die() {
        temp = (int)(Math.random() * 100);
        handler.getWorld().getPlayer().changeScore(200);
        handler.getWorld().getEntityManager().setRespawn(id, startX, startY);
        if(temp < 5){
       handler.getWorld().getItemManager().addItem(Item.silverSword.createNew((int)(x + width/2),(int)(y + height/2)));
        }else if(temp < 15){
       handler.getWorld().getItemManager().addItem(Item.redPotion.createNew((int)(x + width/2),(int)(y + height/2)));
       }
        
        checkQuest();
    }
    
    @Override
    public void attack(){
        if(atkTimer2.start()  && atkAnim.isAnimating()){
            atkAnim.singleUpdate();
                
            if(atk){
                Rectangle cb = getCollisionBounds(0,0);
                Rectangle ar = new Rectangle();
                int arSize = 30;
                ar.width = arSize*3;
                ar.height = arSize*3;
                ar.x = cb.x - arSize;
                ar.y = cb.y - arSize;

                atk = false;

                for(Entity e : handler.getWorld().getEntityManager().getMobs()){
                if(e.equals(this) || e.getId() == id)
                    continue;
                if(e.getCollisionBounds(0,0).intersects(ar)){
                    e.hurt(damage);
                    ((Creature)e).damaged(damage);
                    return;
                }
                }
            }
        }
    }

    @Override
    public void checkAttack(){
       if(atkTimer.start() && !atkAnim.isAnimating()){
            atkAnim.startAnimation();
            atkAnim.setIndex(0);
            atkTimer.restart();
            atkTimer2.restart();
            atk = true;
        }
    }
    
}