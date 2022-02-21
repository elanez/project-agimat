package Game.entity.creatures;

import Game.Handler;
import Game.graphics.Animation;
import Game.graphics.Assets;
import Game.items.Item;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Aswang extends Pig{
    private final Animation aswangForm;
    
    private boolean isAttacked = false;
    
    public Aswang(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        health = 700;
        fullHealth = 700;
        damage = 20;
        
        moveAnim = new Animation(100,Assets.pigWalk_left,Assets.pigWalk_right);
        atkAnim = new Animation(100, Assets.aswangAttack_left,Assets.aswangAttack_right);
        aswangForm = new Animation(100,Assets.aswangWalk_left,Assets.aswangWalk_right);
        
        vision.width = 800;
        vision.height = 800;
        
        slowChance = 25;
    }
    
     @Override
    public void update() {
        if(isAttacked)
            aswangForm.update();
        else
            moveAnim.update();
        getInput();
        attack();
        move();
        checkDebuff();
        
    }
    
    @Override
    public void render(Graphics graphics) {
//        graphics.setColor(Color.RED);
//        if(test != null)
//            graphics.fillRect((int)(test.x - handler.getCamera().getxOffset()), (int)(test.y -  handler.getCamera().getyOffset()), test.width, test.height);
       graphics.drawImage(this.getCurrentAnimationFrame(),(int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
       renderText(graphics);
       infoBar(graphics);
    }
    @Override
    public BufferedImage getCurrentAnimationFrame(){
        
        if(xMove < 0){//left
            face = 0;
        }else if(xMove > 0){//right
            face = 1;
        }else if(xMove == 0 && yMove == 0){//idle
            if(atkAnim.isAnimating()){
               return atkAnim.getCurrentFrame(face);
            }else{
                switch(face){
                    case 0: 
                        return Assets.pigFace_left;
                    case 1:
                        return Assets.pigFace_right;
                }
            }
        }
        //moving
        if(atkAnim.isAnimating()){
            return atkAnim.getCurrentFrame(face);
        }else if(isAttacked)
            return aswangForm.getCurrentFrame(face);
        else{
            return moveAnim.getCurrentFrame(face);
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
        test = vision;
        if(vision.contains(playerBounds)&& isAttacked){
            
//                    if(!paused){
            if(playerX+5 < x)
                xMove = -7;
            if(playerX-5 > x)
                xMove = 7;
            if(playerY+5 < y)
                yMove = -7;
            if(playerY-5 > y)
                yMove = 7; 
//                    }
                        
            if(atkRange.contains(playerBounds)){
                checkAttack();
            }
        }else{
            isAttacked = false;
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
    public void hurt(double damage){
        health -= damage;
         isAttacked = true;
        if(health <= 0){
            active = false;
            die();
           
        }
    }
    @Override
    public void die() {
       temp = (int)(Math.random() * 100);
       handler.getWorld().getPlayer().changeScore(150);
       handler.getWorld().getEntityManager().setRespawn(id, startX, startY);
       handler.getWorld().getPlayer().addGold(10);
       if(temp < 10){
       handler.getWorld().getItemManager().addItem(Item.ulo.createNew((int)(x + width/2),(int)(y + height/2)));
        }
       if(temp < 25){
       handler.getWorld().getItemManager().addItem(Item.bluePotion.createNew((int)(x + width/2),(int)(y + height/2)));
       }
       
       checkQuest();
    } 
    
}
