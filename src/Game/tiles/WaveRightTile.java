/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tiles;

import Game.Handler;
import Game.graphics.Animation;
import Game.graphics.Assets;
import java.awt.Graphics;

/**
 *
 * @author Kevin
 */
public class WaveRightTile extends Tile{

    public WaveRightTile(Handler handler) {
        super(handler);
        animation = new Animation(200, Assets.waveRight);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
    @Override
    public void update(){
        animation.update();
    }

    @Override
    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(animation.getCurrentFrame(),x,y,Tile.TILE_WIDTH,Tile.TILE_HEIGHT, null);
   
    }
    @Override
    public void event(){
       // handler.getWorld().getPlayer().setSpeed(1);
    }
}
    

