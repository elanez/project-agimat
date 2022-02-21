/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity;

import Game.Handler;
import Game.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Elan Fernandez
 */
public abstract class Entity {
   
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean active = true;
    protected int id;
    protected int startX, startY;

    public static final int DEFAULT_HEALTH = 100;
    
    protected float health, fullHealth;
    private float healthPercent;
    
    public Entity(Handler handler,float x, float y, int width, int height, int id){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        
        startX = (int)(x/Tile.TILE_WIDTH);
        startY = (int)(y/Tile.TILE_HEIGHT);
        
        bounds = new Rectangle(0,0, width, height);
    }
      
    public abstract void update();
    public abstract void render(Graphics graphics);
    public abstract void die();
     
    public void hurt(double damage){
        health -= damage;
        if(health <= 0){
            active = false;
            die();
        }
    }

    public void infoBar(Graphics graphics){
        healthPercent = getHealthPercentage();
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset() - 20), (int)(y + bounds.y - handler.getCamera().getyOffset() - 50), 80, 2);
        
        if(healthPercent > 50)
            graphics.setColor(Color.GREEN);
        else if(healthPercent > 25 && healthPercent <= 50)
            graphics.setColor(Color.ORANGE);
        else
            graphics.setColor(Color.RED);
        
        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset() - 20), (int)(y + bounds.y - handler.getCamera().getyOffset() - 50), (int)(80 *(healthPercent/100)), 2);
    }

    public float getHealthPercentage(){
        return ((health/fullHealth) * 100);
    }
    
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x + bounds.x + xOffset),(int)(y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
    
    
    
}
