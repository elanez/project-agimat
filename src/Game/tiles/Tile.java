/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tiles;

import Game.Handler;
import Game.graphics.Animation;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elan Fernandez
 */
public abstract class Tile {
    
    protected Handler handler;
    protected Animation animation;
        
    public static int TILE_WIDTH, TILE_HEIGHT;
    
    protected BufferedImage[] tileSet;
    protected BufferedImage tile;
    
    public Tile(Handler handler){
        this.handler = handler;
        
        TILE_WIDTH = handler.getWidth()/20;
        TILE_HEIGHT = TILE_WIDTH;
    }
    
    public abstract void render(Graphics graphics, int x, int y);
    
    public void update(){
        
    }

    public void event(){
//        handler.getWorld().getPlayer().setSpeed(5);
    }

    public boolean isSolid(){
        return false;
    }
   
}
