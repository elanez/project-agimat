/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tiles;

import Game.Handler;
import Game.graphics.Assets;
import Game.util.Timer;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public class DirtTile extends Tile{
    
    private Timer timer;
    
    public DirtTile(Handler handler) {
        super(handler);
        
        timer = new Timer(250);
    }
    
    @Override
    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(Assets.dirt,x,y,Tile.TILE_WIDTH,Tile.TILE_HEIGHT, null);
    }
    
    @Override
    public void event(){
//        handler.getWorld().getPlayer().setSpeed(1);
        
//        if(timer.start()){
//            handler.getWorld().getPlayer().hurt(1);
//            timer.restart();
//        }
        
    }
}
