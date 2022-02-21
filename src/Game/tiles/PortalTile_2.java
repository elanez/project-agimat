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
public class PortalTile_2 extends PortalTile{
    
    public PortalTile_2(Handler handler) {
        super(handler);
        
        animation = new Animation(200,Assets.portal2);
        worldID = 2;
        x = 45;
        y = 23;
    }
    
}
