package Game.entity.creatures;

import Game.Handler;
import Game.graphics.Animation;
import Game.graphics.Assets;
import Game.items.Item;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pig extends Enemy{
    
    public Pig(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        
        health = 100;
        fullHealth = 100;
        damage = 8;
        moveAnim = new Animation(100,Assets.pigWalk_left,Assets.pigWalk_right);
        atkAnim = new Animation (100,Assets.pigAttack_left,Assets.pigAttack_right);
        
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
        graphics.setColor(Color.RED);
//        if(test != null)
//            graphics.fillRect((int)(test.x - handler.getCamera().getxOffset()), (int)(test.y -  handler.getCamera().getyOffset()), test.width, test.height);
        graphics.drawImage(this.getCurrentAnimationFrame(),(int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
        renderText(graphics);
 
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y +bounds.y -  handler.getCamera().getyOffset()), bounds.width, bounds.height);
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
                        return Assets.pigWalk_left[0];
                    case 1:
                        return Assets.pigWalk_right[0];
                }
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
       handler.getWorld().getPlayer().changeScore(50);
       handler.getWorld().getEntityManager().setRespawn(id, startX, startY);
       handler.getWorld().getPlayer().addGold(2);

       if(temp < 10){
       handler.getWorld().getItemManager().addItem(Item.baboy.createNew((int)(x + width/2),(int)(y + height/2)));
        }else if(temp < 25){
       handler.getWorld().getItemManager().addItem(Item.redPotion.createNew((int)(x + width/2),(int)(y + height/2)));
       }
       
       checkQuest();
    }
}
