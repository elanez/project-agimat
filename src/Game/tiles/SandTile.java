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
public class SandTile extends Tile{

    public SandTile(Handler handler) {
        super(handler);
    }

    @Override
    public void render(Graphics graphics, int x, int y) {
       graphics.drawImage(Assets.sand,x,y,Tile.TILE_WIDTH,Tile.TILE_HEIGHT, null);
     
    }
    
    @Override
    public void event(){
//       handler.getWorld().getPlayer().setSpeed(1);
        
       
    }
    
}
