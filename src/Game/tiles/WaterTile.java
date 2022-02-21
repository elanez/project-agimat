/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tiles;

import Game.Handler;
import Game.graphics.Assets;
import java.awt.Graphics;

/**
 *
 * @author Kevin
 */
public class WaterTile extends Tile{

    public WaterTile(Handler handler) {
        super(handler);
    }

    @Override
    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(Assets.water,x,y,Tile.TILE_WIDTH,Tile.TILE_HEIGHT, null);
    
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
    @Override
    public void event(){
        
    }
    
}
