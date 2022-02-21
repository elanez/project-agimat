/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.npc;

import Game.Handler;
import Game.graphics.Assets;
import Game.tiles.Tile;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public class ShopNPC extends NPC{

    private final UIManager uiManager;
    
    private boolean btnStatus;
    private final float btnX, btnY;
    private final int btnW, btnH;
    
    public ShopNPC(Handler handler, float x, float y, int id) {
        super(handler, x, y, id);
        
        bounds.x = width/2 - width/6;
        bounds.y = height/2;
        bounds.width = width/3;
        bounds.height = height/2;
        
        name = "Mariposa";
        message = "Hello There!";
        btnStatus = false;

        btnW = Tile.TILE_WIDTH;
        btnH = Tile.TILE_HEIGHT;
        btnX = ((handler.getWidth() - 20)*5)/6;
        btnY = ((handler.getHeight()*3)/4) - 20 - btnH;
        
        uiManager = new UIManager(handler);
        init();
    }

    @Override
    public void interact() {
        handler.getWorld().getPlayer().getDialog().setDialog(name, message);
        handler.getWorld().getPlayer().getDialog().activeSwitch();
        
        btnStatus = !btnStatus;
        if(btnStatus)
            handler.getMouseManager().setUIManager(uiManager);
        else
            handler.getMouseManager().setUIManager(null);
    }

    @Override
    public void update() {
        if(btnStatus)
         uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.shopNPC, (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);

        if(btnStatus)
            uiManager.render(graphics);
        
    }

    public void init(){
        uiManager.addObject(new UIImageButton(btnX,btnY, btnW, btnH,Assets.buyBtn, () -> {
            handler.getWorld().getPlayer().getDialog().setActive(false);
            btnStatus = false;
            handler.getWorld().getShop().activate();
        }));
        
        uiManager.addObject(new UIImageButton(btnX + btnW + 30,btnY, btnW, btnH,Assets.sellBtn, () -> {
            handler.getWorld().getPlayer().getDialog().setActive(false);
            btnStatus = false;
            handler.getWorld().getSellShop().activate();
        }));
    }
}
