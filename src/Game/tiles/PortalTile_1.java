/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tiles;

import Game.Handler;
import Game.graphics.Animation;
import Game.graphics.Assets;

/**
 *
 * @author Elan Fernandez
 */
public class PortalTile_1 extends PortalTile{
    
    public PortalTile_1(Handler handler) {
        super(handler);
        
        animation = new Animation(200, Assets.portal1);
        worldID = 1;
        x = 0;
        y = 25;
    }
    
}
