/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.creatures;

import Game.Handler;
import Game.entity.Entity;
import static Game.entity.Entity.DEFAULT_HEALTH;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.tiles.Tile;
import Game.util.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elan Fernandez
 */
public abstract class Creature extends Entity{

    public static int DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT;
    public static final float DEFAULT_SPEED = 5;

    private int dmg;
    private final Timer txtTimer, slowTimer, burnTimer, stunTimer;
    private double knockBackDistance;
    private boolean slowed, burning, stunned;
    
    protected int damage;
    protected float speed;
    protected float xMove, yMove;
    
    public Creature(Handler handler, float x, float y, int width, int height, int id){
        super(handler, x, y, width, height, id);
        speed = 5;
        xMove = 0;
        yMove = 0;
        slowed = false;
        burning = false;
        stunned = false;
        
        DEFAULT_CREATURE_WIDTH = ((Tile.TILE_WIDTH * 2)*3)/5;
        DEFAULT_CREATURE_HEIGHT = ((Tile.TILE_HEIGHT * 2)*3)/5;
    
        txtTimer = new Timer(1000);
        slowTimer = new Timer(5000);
        burnTimer = new Timer(5000);
        stunTimer = new Timer (5000);
        health = DEFAULT_HEALTH ;
        fullHealth = health;
        damage = 10;
        dmg = 0;
        
    }
    
    public abstract void getInput();
    public abstract BufferedImage getCurrentAnimationFrame();
    
    public void knockback(int face, Entity currentEntity){
        knockBackDistance = Math.random() * 4;
        switch(face){
            
            case 0:
                currentEntity.setX(currentEntity.getX()-(20*(int)knockBackDistance));
                break;
            case 1:
                currentEntity.setX(currentEntity.getX()+(20*(int)knockBackDistance));
                break;
                
            case 2:
                currentEntity.setY(currentEntity.getY()-(20*(int)knockBackDistance));
                break;
            default:
                currentEntity.setY(currentEntity.getY()+(20*(int)knockBackDistance));
                break;
        }
        for(Entity otherEntity : handler.getWorld().getEntityManager().getEntities()){
            if(currentEntity.getCollisionBounds(0f, 0f).intersects(otherEntity.getCollisionBounds(0f, 0f)))
                currentEntity.setY(currentEntity.getY()- 100);
        }
    }
    public void spinKnockback(Entity currentEntity){
        if(currentEntity.getX()< handler.getWorld().getPlayer().getX())
            currentEntity.setX(currentEntity.getX() - 100);
        else
            currentEntity.setX(currentEntity.getX() + 100);
        if(currentEntity.getY()< handler.getWorld().getPlayer().getY())
            currentEntity.setY(currentEntity.getY() - 100);
        else
            currentEntity.setY(currentEntity.getY() + 100);
            
    }
    
    public void move(){
        if(!checkEntityCollisions(xMove,0f))
            moveX();
        if(!checkEntityCollisions(0f,yMove))
            moveY();
    }
    
    public void moveX(){
        if((x + xMove + bounds.x) < 0 || (x + xMove + bounds.x + bounds.width) >= (handler.getWorld().getWidth() * Tile.TILE_WIDTH) )
            return;
        
	if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
		!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
		x += xMove;
            }else{
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
	}else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
		!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            }else{
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
	}
    }
    
    public void moveY(){
        if((y + yMove + bounds.y) < 0 || (y + yMove + bounds.y + bounds.height) >= (handler.getWorld().getHeight() * Tile.TILE_HEIGHT))
            return;
        
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
		!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
		y += yMove;
            }else{
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            
            }
	}else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
		!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height -1;
            }
        }
    }
   
    
    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
    protected void renderText(Graphics graphics){
        if(txtTimer.start())
            return;
        
        Text.drawString(graphics, "-" + Integer.toString(dmg),(int)((x + width/2) - handler.getCamera().getxOffset()), (int)((y - 10) -  handler.getCamera().getyOffset()), true, Color.red, Assets.font16);
        
    }
    public void checkDebuff(){
        if(slowed){
            speed = 2;
            slowTimer.restart();
            slowed = false;
        }
        else if(slowTimer.start())
            setSpeed(5);
        
        if(burning){
            burnTimer.restart();
            burning = false;
        }
        if(!burnTimer.start()){
            health -= 1;
        }
        if(stunned){
            stunTimer.restart();
            stunned = false;
        }
        if(!stunTimer.start()){
            speed = 0;
        }
    }
    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
   
    public float getFullHealth() {
        return fullHealth;
    }

    public void setFullHealth(int fullHealth) {
        this.fullHealth = fullHealth;
    }
 
    
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getYMove() {
        return yMove;
    }

    public void setSlowed(boolean slowed) {
        this.slowed = slowed;
    }

    public void setYMove(float YMove) {
        this.yMove = YMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void damaged(int dmg) {
        this.dmg = dmg;
        txtTimer.restart();
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }
    
    
    
}
