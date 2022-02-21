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
 * @author Elan Fernandez
 */
public class PortalTile extends Tile{

    protected int worldID, x, y;
    
    public PortalTile(Handler handler) {
        super(handler);
        animation = new Animation(200, Assets.homePortal);
        
        worldID = 0;
        x = 25;
        y = 10;
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
        handler.setWorld(handler.getState().getWorldManager().getWorlds().get(worldID));
        handler.getWorld().getPlayer().setX(x * Tile.TILE_WIDTH);
        handler.getWorld().getPlayer().setY(y * Tile.TILE_HEIGHT);
        handler.getMouseManager().setWorld(handler.getWorld());
    }
}
