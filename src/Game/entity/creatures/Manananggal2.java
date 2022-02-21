package Game.entity.creatures;

import Game.Handler;
import Game.Projectile.EnemyProjectile;
import Game.Projectile.EnemyProjectileController;
import Game.entity.Entity;
import Game.graphics.Animation;
import Game.graphics.Assets;
import Game.items.Item;
import Game.util.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Manananggal2 extends Manananggal{
    private final EnemyProjectileController enemyProjectileController;
    private final Animation rangedAnimation;
    private final Animation skillAnimationLeft;
    private final Animation skillAnimationRight;
    private final Animation skillAnimationUp;
    private final Animation skillAnimationDown;
    private final int attackPattern;
    private final Timer scatterTimer;
    private Timer scatterTimer2;
    private final boolean isScatter;
    public Manananggal2(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        
        rangedAnimation = new Animation(50, Assets.homePortal);
        skillAnimationLeft = new Animation(150, Assets.enemySkillLeft);
        skillAnimationRight = new Animation(150, Assets.enemySkillRight);
        skillAnimationUp = new Animation(150, Assets.enemySkillUp);
        skillAnimationDown = new Animation(150, Assets.enemySkillDown);
        damage = 30;
        health = 2000;
        fullHealth = 2000;
        enemyProjectileController = new EnemyProjectileController(handler);
        atkRange = 300;
        vision = 1000;
        attackPattern = 0;
        moveAnim = new Animation(200,Assets.bossLeft, Assets.bossRight);
        atkAnim = new Animation(200, Assets.bossAttackLeft, Assets.bossAttackRight); 
        
        scatterTimer = new Timer (5000);
        isScatter = false;
    }
//    public void scatter(){
//        if(scatterTimer2.start()&& atkAnim.isAnimating()){
//            atkAnim.singleUpdate();
//            if(isScatter){
//                isScatter = false;
//                for(int i = 0; i < 8; i++){
//                    enemyProjectileController.addProjectile(new Projectile(handler, (int)x, (int)y, i, rangedAnimation));
//                }
//                
//            }
//        }
//    }
    
    @Override
    public void attack(){
            
            if(atkTimer2.start()  && atkAnim.isAnimating()){
                atkAnim.singleUpdate();
                skillAnimationLeft.startAnimation();
                skillAnimationRight.startAnimation();
                skillAnimationUp.startAnimation();
                skillAnimationDown.startAnimation();
                
                if(atk){
                            
                            Rectangle cb = getCollisionBounds(0,0);
                            Rectangle arLeft = new Rectangle();
                            Rectangle arRight = new Rectangle();
                            Rectangle arUp = new Rectangle();
                            Rectangle arDown = new Rectangle();

                            int arSize = 80;

                            arLeft.width = 500;
                            arLeft.height = arSize*3;
                            arRight.width = 500;
                            arRight.height = arSize*3;

                            arUp.width = arSize*3;
                            arUp.height = 500;
                            arDown.width = arSize*3;
                            arDown.height = 500;

                            arLeft.x = cb.x - arLeft.width;
                            arLeft.y = cb.y - arSize;

                            arRight.x = cb.x + cb.width;
                            arRight.y = cb.y - arSize;

                            arUp.x = cb.x - arSize;
                            arUp.y = cb.y - 500;

                            arDown.x = cb.x - arSize;
                            arDown.y = cb.y + cb.height;
                            test = arDown;
                            atk = false;

                            for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                                if(e.equals(this))
                                    continue;
                                if(e.equals(handler.getWorld().getEntityManager().getPlayer())){
                                    if(e.getCollisionBounds(0,0).intersects(arLeft)|| e.getCollisionBounds(0,0).intersects(arRight)||
                                        e.getCollisionBounds(0,0).intersects(arUp)||e.getCollisionBounds(0,0).intersects(arDown)){
                                            e.hurt(damage);
                                            chance = Math.random() * 10;
                                            if(chance < 5)
                                                handler.getWorld().getPlayer().setSlow(true);
                                           
                                            return;
                                     }
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
    
    public void checkScatter() {
        if(scatterTimer.start()){
            scatterTimer.restart();
            for(int i = 0; i < 8; i++){
                    enemyProjectileController.addProjectile(new EnemyProjectile(handler, (int)x, (int)y, i, rangedAnimation));
            }
        }
    }
    
    
    
    @Override
    public void update() {
        
        moveAnim.update();
        skillAnimationLeft.singleUpdate();
        skillAnimationRight.singleUpdate();
        skillAnimationUp.singleUpdate();
        skillAnimationDown.singleUpdate();
//        atkAnim.singleUpdate();
        attack();
        getInput();
        move();
        enemyProjectileController.update();
        
      
        
    }
    
    @Override
    public void render(Graphics graphics) {
       graphics.drawImage(this.getCurrentAnimationFrame(),(int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
//      graphics.setColor(Color.RED);
//        if(test != null){
//            graphics.fillRect((int)(test.x - handler.getCamera().getxOffset()), (int)(test.y -  handler.getCamera().getyOffset()), test.width, test.height);
//            System.out.println("Right: " + test.x + " " + test.y);
//        }
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y +bounds.y -  handler.getCamera().getyOffset()), bounds.width, bounds.height);
//        
        if(skillAnimationLeft.isAnimating())
                graphics.drawImage(skillAnimationLeft.getCurrentFrame(), (int)(x - handler.getCamera().getxOffset() - 400), (int)(y -  handler.getCamera().getyOffset()), 500, 200, null);
        if(skillAnimationRight.isAnimating())   
                graphics.drawImage(skillAnimationRight.getCurrentFrame(), (int)(x - handler.getCamera().getxOffset()+100), (int)(y -  handler.getCamera().getyOffset()), 500, 200, null);
        if(skillAnimationDown.isAnimating())
                graphics.drawImage(skillAnimationDown.getCurrentFrame(), (int)(x - handler.getCamera().getxOffset()-20), (int)(y -  handler.getCamera().getyOffset()), 200, 500, null);
        if(skillAnimationUp.isAnimating())
                graphics.drawImage(skillAnimationUp.getCurrentFrame(), (int)(x - handler.getCamera().getxOffset()-20), (int)(y -  handler.getCamera().getyOffset()-400), 200, 500, null);
     
    infoBar(graphics);
    enemyProjectileController.render(graphics);
    }
    
    @Override
      public void getInput(){
        xMove=0;
        yMove=0;

        playerX = handler.getWorld().getEntityManager().getPlayer().getX();
        playerY = handler.getWorld().getEntityManager().getPlayer().getY();

        if((playerX > (x-vision) && playerX < (x+vision))&&(playerY > (y-vision) && playerY < (y+vision))){
                if(((playerX + atkRange) > x && (playerX - atkRange) < x )&&((playerY + 80) > y && (playerY - 80) < y)){
                        if(scatterTimer.start())
                            checkScatter();
                        else
                            checkAttack();
                }else if(((playerX + 80) > x && (playerX - 80) < x )&&((playerY + atkRange) > y && (playerY - atkRange) < y)){
                        if(scatterTimer.start())
                            checkScatter();
                        else
                            checkAttack();
                    
                }
//                if(flyingLeft){
//                    x -= SPEED*3;   
//                }else if(flyingRight){
//                    x += SPEED*3;
//                }
                else{
                    if(playerX+2 < x)
                        xMove = -speed;
                    if(playerX-2 > x)
                        xMove = speed;
                    if(playerY+2 < y)
                        yMove = -speed;
                    if(playerY-2 > y)
                        yMove = speed;
                }
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
    public void die() {
       temp = (int)(Math.random() * 100);
       handler.getWorld().getPlayer().changeScore(500);
       handler.getWorld().getEntityManager().setRespawn(id, startX, startY);
       handler.getWorld().getPlayer().addGold(1000);

       if(temp < 5){
       handler.getWorld().getItemManager().addItem(Item.diwata.createNew((int)(x + width/2),(int)(y + height/2)));
        }
       if(temp < 50){
       handler.getWorld().getItemManager().addItem(Item.woodSword.createNew((int)(x + width/2),(int)(y + height/2)));
       
       }
       
       checkQuest();
    } 
   
    
}
