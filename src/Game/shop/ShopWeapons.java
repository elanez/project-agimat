/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.shop;

import Game.Handler;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.items.Item;
import Game.ui.ClickListener;
import Game.ui.UIImageButton;
import Game.tiles.Tile;
import Game.ui.UIObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class ShopWeapons{
    
    private final Handler handler;
    private ArrayList<UIObject> weaponsBtn;
    
    private final float itemX, itemY;
    private final int itemWidth, itemHeight, ItemMax;
    private final double spaceW, spaceH;
    private int countW, countH;
    
    private final float btnX, btnY;
    private final int btnW, btnH;
    
    
    public ShopWeapons(Handler handler){
        this.handler = handler;
        
        weaponsBtn = new ArrayList<>();
           
        itemX = Shop.shopWidth/13;
        itemY = (float)(Shop.shopHeight/3.5);
        itemWidth = Tile.TILE_WIDTH;
        itemHeight = Tile.TILE_HEIGHT;
        
        countW = 0;
        countH = 0;
        
        btnX = Shop.shopWidth / 8;
        btnY = Shop.shopHeight / 7;
        btnW = (int)(btnX / 2);
        btnH = (int)(btnY / 2);
        
        spaceW = 30;
        spaceH = 30;
        ItemMax = 3;
        
        init();
    }
    
    private void init(){
        
        addShopItem(Assets.goldSword, () -> {
            initButton(Assets.goldSword,Item.goldSword);
        });
        
        addShopItem(Assets.silverSword, () -> {
            initButton(Assets.silverSword,Item.silverSword);
            
        });
        
        addShopItem(Assets.woodSword, () -> {
            initButton(Assets.woodSword,Item.woodSword);
        });
        
    }
    
    private void addShopItem(BufferedImage image, ClickListener click){
        weaponsBtn.add(new UIImageButton((int)(itemX + (spaceW * (countW + 1)) + (itemWidth*countW)),(int)(itemY + (spaceH * (countH + 1)) + (itemHeight*countH)),itemWidth,itemHeight,image,click));
        countW++;
        if(countW == 3){
            countW = 0;
            countH++;
            if(countH == 4)
                countH = 0;
        }
    }
    
    private void initButton(BufferedImage image, Item item){
        handler.getWorld().getShop().setText(1);
        handler.getWorld().getShop().setDisplayImage(image);
        handler.getWorld().getShop().setItem(item);
        handler.getWorld().getShop().setI(1);
        handler.getWorld().getShop().setPrice(item.getPrice());
        handler.getWorld().getShop().getObj().get(3).setActive(true);
        handler.getWorld().getShop().getObj().get(4).setActive(true);
        handler.getWorld().getShop().getObj().get(5).setActive(true);
    }
    
    public ArrayList<UIObject> getWeapons() {
        return weaponsBtn;
    }
}

