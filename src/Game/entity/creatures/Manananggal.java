package Game.entity.creatures;

import Game.Handler;
import Game.graphics.Animation;
import Game.graphics.Assets;
import Game.items.Item;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Manananggal extends Enemy {
    private final long flyCooldown = 1000;
    private long lastFlyTimer, flyTimer = flyCooldown;
    private int flyDamage = 0, flyRange = 200;
    private boolean flyingLeft = true, flyingRight = true, flyingUp = true, flyingDown = true;
    protected int vision, atkRange;
    protected double chance;
      
    public Manananggal(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        
        moveAnim = new Animation(200,Assets.enemy_left,Assets.enemy_right);
        atkAnim = new Animation(200, Assets.enemyAttack_left, Assets.enemyAttack_right); 

        bounds.x = 40;
        bounds.y = 50;
        bounds.width = 30;
        bounds.height = 30;
        
        vision = 300;
        atkRange = 32;
        health = 300;
        fullHealth = 300;
        damage = 15;
        
        slowChance = 15;
        
        atk = false;
    }
    
    public void checkFly(int direction){
        
        flyTimer += System.currentTimeMillis() - lastFlyTimer;
        lastFlyTimer = System.currentTimeMillis();
        if(flyTimer < flyCooldown){
            return;
        }
        flyingLeft = false;
        flyingRight = false;
        flyingUp = false;
        flyingDown = false;
        switch (direction) {
            case 0:
                if(flyTimer > 5250)
                {
                    flyingLeft = true;
                    face=0;
                    flyTimer = 0;
                }   break; 
            case 1:
                if(flyTimer > 5250)
                {
                    flyingRight = true;
                    face=1;
                    flyTimer = 0;
                }   break;
            case 2:
                if(flyTimer > 5250)
                {
                    flyingUp = true;
                    
                    flyTimer = 0;
                }   break;
            case 3:
                if(flyTimer > 5250)
                {
                    flyingDown = true;
                    
                    flyTimer = 0;
                }   break;
            default:
                break;
        }
    }

    @Override
    public void update() {
        
        moveAnim.update();
//        atkAnim.singleUpdate();
        attack();
        getInput();
        
        move();
        checkDebuff();
        
    }
    
    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
//        if(test != null)
//            graphics.fillRect((int)(test.x - handler.getCamera().getxOffset()), (int)(test.y -  handler.getCamera().getyOffset()), test.width, test.height);
       graphics.drawImage(this.getCurrentAnimationFrame(),(int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
       renderText(graphics);
       
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y +bounds.y -  handler.getCamera().getyOffset()), bounds.width, bounds.height);
       infoBar(graphics);
    }
    
//      @Override
//      public void getInput(){
//        xMove=0;
//        yMove=0;
//
//        playerX = handler.getWorld().getEntityManager().getPlayer().getX();
//        playerY = handler.getWorld().getEntityManager().getPlayer().getY();
//
//        if(playerX > (x-vision) && playerX < (x+vision)){
//            if(playerY > (y-vision) && playerY < (y+vision)){
////                if(playerX+5 < x)
////                    checkFly(0);
////                else if(playerX-5 > x)
////                    checkFly(1);
////                if(playerY-5 > y)
////                    checkFly(2);
////                else if(playerY+5 < y)
////                    checkFly(3);
//                
//                if((playerX + atkRange) > x && (playerX - atkRange) < x ){
//                    if((playerY + atkRange) > y && (playerY - atkRange) < y){
//                        checkAttack();
//                    }
//                }
////                if(flyingLeft){
////                    x -= SPEED*3;   
////                }else if(flyingRight){
////                    x += SPEED*3;
////                }
//                else{
//                    if(playerX+5 < x)
//                        xMove = -SPEED;
//                    if(playerX-5 > x)
//                        xMove = SPEED;
//                    if(playerY+5 < y)
//                        yMove = -SPEED;
//                    if(playerY-5 > y)
//                        yMove = SPEED;
//                }
//            }
//        }else{
//            if(moveTimer.start()){
//                stop++;
//                if(stop == 1){
//                    temp = (int)(Math.random() * 4);
//                }else{
//                    stop = 0;
//                    temp = 5;
//                }
//
//                moveTimer.restart();
//            }
//
//            switch (temp) {
//                case 0:
//                    yMove = SPEED;
//                    break;
//                case 1:
//                    xMove = -SPEED;
//                    break;
//                case 2:
//                    yMove = -SPEED;
//                    break;
//                case 3:
//                    xMove = SPEED;
//                    break;
//                default:
//                    break;
//           }
//        }
//        
//    }
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
                return moveAnim.getCurrentFrame(face);
            }
        }
        //moving
        if(atkAnim.isAnimating()){
            return atkAnim.getCurrentFrame(face);
        }else{
            return moveAnim.getCurrentFrame(face);
        }
    }      
    
    @Override
    public void die() {
       temp = (int)(Math.random() * 100);
       handler.getWorld().getPlayer().changeScore(100);
       handler.getWorld().getEntityManager().setRespawn(id, startX, startY);
       handler.getWorld().getPlayer().addGold(5);
       
       if(temp < 10){
       handler.getWorld().getItemManager().addItem(Item.dila.createNew((int)(x + width/2),(int)(y + height/2)));
        }
       if(temp < 25){
       handler.getWorld().getItemManager().addItem(Item.greenPotion.createNew((int)(x + width/2),(int)(y + height/2)));
       
       }
       
       checkQuest();
    } 
}
