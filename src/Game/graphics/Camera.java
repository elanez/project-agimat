/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.graphics;

import Game.Handler;
import Game.entity.Entity;
import Game.tiles.Tile;

/**
 *
 * @author Elan Fernandez
 */
public class Camera {
    
    private final Handler handler;
    private float xOffset, yOffset;
    
    public Camera(Handler handler,float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void checkBlankSpace(){
        if(xOffset < 0)
            xOffset = 0;
        else if(xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth())
            xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        
        if(yOffset < 0)
            yOffset = 0;
        else if(yOffset > handler.getWorld().getWidth() * Tile.TILE_HEIGHT - handler.getHeight())
            yOffset = handler.getWorld().getWidth() * Tile.TILE_HEIGHT - handler.getHeight();
        
    }
    
    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getWidth()/2 + e.getWidth()/2;
        yOffset = e.getY() - handler.getHeight()/2 + e.getHeight()/2;
        checkBlankSpace();
    }
    
    public void move(float xAmount, float yAmount){
        xOffset += xAmount;
        yOffset += yAmount;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
}
