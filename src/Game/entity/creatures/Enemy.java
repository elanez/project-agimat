package Game.entity.creatures;

import Game.Handler;
import Game.entity.Entity;
import Game.graphics.Animation;
import Game.items.Item;
import Game.util.Timer;
import java.awt.Rectangle;

public abstract class Enemy extends Creature{
    
//    public final Animation attack;
//    public final Animation move;
//    public final Animation fly;
    
   // public final Timer attackTimer, flyTimer;
    //************************************************
    protected int temp, stop,slow;
    protected int face;
    protected Timer atkTimer, moveTimer, atkTimer2;
    protected long timer, lastTime;
    protected Animation moveAnim, atkAnim;
    protected Rectangle test, atkRange, vision;
    protected boolean atk;
    protected int slowChance;
    
    protected float playerX, playerY, playerW, playerH;
    
    public long lastAttackTimer, attackCooldown = 320, attackTimer = attackCooldown;
    //******************************************************************************
    public Enemy(Handler handler, float x, float y, int id) {
        super(handler, x, y,110,110,id);
        
        bounds.x = 40;
        bounds.y = 50;
        bounds.width = 30;
        bounds.height = 30;
        
        atkRange = new Rectangle();
        vision  = new Rectangle();
        
        atkRange.width = 200;
        atkRange.height = 200;
        
        vision.width = 600;
        vision.height = 600;
        
        atkTimer =  new Timer(2000);
        atkTimer2 = new Timer(500);
        moveTimer = new Timer(1000);
        slowChance = 5;
        
        face = 0;
    }
    
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

                for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(0,0).intersects(ar)){
                    e.hurt(damage);
                    slow = (int)(Math.random() * 100);
                    if(slow < slowChance){
                        handler.getWorld().getPlayer().setSlow(true);
                    }
                    
                    return;
                }

     
                }
            }
        }
    }

    public void checkAttack(){
       if(atkTimer.start() && !atkAnim.isAnimating()){
            atkAnim.startAnimation();
            atkAnim.setIndex(0);
            atkTimer.restart();
            atkTimer2.restart();
            atk = true;
        }
    }
    
    @Override
    public void getInput(){
       
        xMove=0;
        yMove=0;

        playerW = handler.getWorld().getPlayer().getWidth();
        playerH = handler.getWorld().getPlayer().getHeight();
        playerX = handler.getWorld().getPlayer().getX();
        playerY = handler.getWorld().getPlayer().getY();
        
        Rectangle cb = getCollisionBounds(0,0);
        Rectangle playerBounds = handler.getWorld().getPlayer().getCollisionBounds(0, 0);
        atkRange.x = cb.x - (atkRange.width/2);
        atkRange.y = cb.y - (atkRange.height/2);
        vision.x = cb.x - (vision.width/2);
        vision.y = cb.y - (vision.height/2);
        
        test = atkRange;
        
        if(vision.contains(playerBounds)){
            if((playerX + (playerW/2))+5 < x)
                xMove = -speed;
            if((playerX)-15 > x)
                xMove = speed;
            if(playerY+5 < y)
                yMove = -speed;
            if(playerY-5 > y)
                yMove = speed;
                
            if(atkRange.contains(playerBounds))
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
    public void die(){
        handler.getWorld().getEntityManager().getMobs().remove(this);
        handler.getWorld().getPlayer().changeScore(100);
        handler.getWorld().getEntityManager().setRespawn(id, startX, startY);
        checkQuest();
    }
    
    public void checkQuest(){
       if(handler.getWorld().getQuest().isOngoing() && handler.getWorld().getQuest().getId() == id && handler.getWorld().getQuest().getType().equals("kill")){
           handler.getWorld().getQuest().setCurrentNo(handler.getWorld().getQuest().getCurrentNo() + 1);        
        }
    }
}

