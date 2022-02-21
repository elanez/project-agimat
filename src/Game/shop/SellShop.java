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
import Game.tiles.Tile;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Lance
 */
public class SellShop {
    private final Handler handler;
    private UIManager uiManager;
    private ArrayList<Item> obj;
    
    private final float itemX, itemY;
    private final int itemWidth, itemHeight, ItemMax;
    private final double spaceW, spaceH;
    private int countW, countH;
    
    private final float btnX, btnY;
    BufferedImage displayImage;
    private final int btnW, btnH;
    
    private final int textX, textY;
    private final int displayX, displayY, displayW, displayH;
    
    private final int invX, invY ;
    private final int invImageX, invImageY;
    private final int invImageWidth = Tile.TILE_WIDTH, invImageHeight = Tile.TILE_HEIGHT;
    
    public int shopX, shopY, shopWidth, shopHeight;
    private boolean active = false;
    private int price = 0;
    private boolean textRender;
    
    private int i,count;
    private Item item;
    
    private final int max;
    private int len, index, prevIndex;
    private String pageNo, totalPageNo;
    
    public SellShop(Handler handler) {
        this.handler = handler;
               
        shopX = 20;
        shopY = 20;
        shopWidth = handler.getWidth() - 40;
        shopHeight = handler.getHeight() - 40;
        
        invX = 20;
        invY = 20;
        
        invImageX = (handler.getWidth() - 40)/13;
        invImageY = (int)((handler.getHeight() - 40)/3.5);
        
        itemX = Shop.shopWidth/13;
        itemY = (float)(Shop.shopHeight/3.5);
        itemWidth = Tile.TILE_WIDTH;
        itemHeight = Tile.TILE_HEIGHT;
        countW = 0;
        countH = 0;
        
        displayW = Tile.TILE_WIDTH * 3;
        displayH = Tile.TILE_HEIGHT * 3;
        displayX = (shopWidth*7)/16;
        displayY = shopHeight/4;
        
        btnX = Shop.shopWidth / 8;
        btnY = Shop.shopHeight / 7;
        btnW = (int)(btnX / 2);
        btnH = (int)(btnY / 2);
        
        textX = displayX + displayW/2;
        textY = (int)(displayY*3.2);
        
        pageNo = "1";
        totalPageNo = "1";
        
        spaceW = 30;
        spaceH = 30;
        ItemMax = 3;
        max = 12;
    }
    
    public void init(){
        uiManager = new UIManager(handler);
        obj = handler.getWorld().getPlayer().getInventory().getInventoryItems();
        uiManager.addObject(new UIImageButton(shopX + shopWidth - btnW/2,shopY,btnW/2,btnH/2,Assets.closeBtn, () -> {
            deactivate();
        }));
        
        uiManager.addObject(new UIImageButton(displayX,textY-20, 40, 50,Assets.minus, () -> {
            count--;
            if(count < 1){
                count = item.getCount();
            }
        }));
          //  uiManager.getObjects().get(1).setActive(false);
        
        uiManager.addObject(new UIImageButton(displayX + displayW - 40,textY-20, 40, 50,Assets.plus, () -> {
            count++;
            if(count > item.getCount()){
                count = 1;
            }
        }));
          //  uiManager.getObjects().get(2).setActive(false);
            
         uiManager.addObject(new UIImageButton(textX - btnW/2,textY + btnH, btnW, btnH,Assets.sellBtn, () -> {
            if(handler.getWorld().getPlayer().getInventory().getEquipID() != item.getID()){     
            handler.getWorld().getPlayer().setPlayerMoney(handler.getWorld().getPlayer().getPlayerMoney() + price*count);
            handler.getWorld().getPlayer().getInventory().remove(item, count);
            activate();
            }else{
                if(item.getCount() - count >= 1){
                  handler.getWorld().getPlayer().setPlayerMoney(handler.getWorld().getPlayer().getPlayerMoney() + price*count);
                  handler.getWorld().getPlayer().getInventory().remove(item, count);
                  activate();  
                }
            }
        }));
          //  uiManager.getObjects().get(3).setActive(false);
          
        clickerActive();
        sellButtonDeactivate();
    }
    
    public void update(){
       if(active){
            uiManager.update();
            handler.getWorld().getPlayer().getInventory().update();
            
            if(pageNo.equals("0") || Integer.parseInt(pageNo) > Integer.parseInt(totalPageNo))
                activate();
        }
    }
    
    


    public void render(Graphics graphics) {
        if(active){
            graphics.drawImage(Assets.ShopInventory, shopX, shopY,shopWidth,shopHeight, null);
            graphics.drawImage(Assets.iconGold,(shopX + shopWidth*10/11), 225,32,32,null);
            Text.drawString(graphics,Integer.toString(handler.getWorld().getPlayer().getPlayerMoney()),(shopX + shopWidth*9/11), 250, false, Color.YELLOW, Assets.font28);
            Text.drawString(graphics,"GOLD:",(shopX + shopWidth*7/10), 250, false, Color.YELLOW, Assets.font28);
            len = uiManager.getObjects().size();
        
            prevIndex = index;

            uiManager.getObjects().get(0).render(graphics);
            uiManager.getObjects().get(1).render(graphics);
            uiManager.getObjects().get(2).render(graphics);
            uiManager.getObjects().get(3).render(graphics);
            uiManager.getObjects().get(4).render(graphics);
            uiManager.getObjects().get(5).render(graphics);

            int temp = handler.getWorld().getPlayer().getInventory().getInventoryItems().size()/max;
            if(handler.getWorld().getPlayer().getInventory().getInventoryItems().size()%max > 0  || temp==0)
                temp++;

            totalPageNo = Integer.toString(temp);

            Text.drawString(graphics,pageNo + "/" + totalPageNo,(int)(invX + shopWidth*3/16),(int)(invY + shopHeight*27/32),true,Color.WHITE,Assets.font28);

            countW = 0;
            countH = 0;
            int counter = 0;

            while(index<len){
                uiManager.getObjects().get(index).render(graphics);

                index++;
                counter++;
                if(counter >= max)
                    break;
            }

            index = prevIndex;
            
            renderText(graphics);
            renderDisplay(graphics);
        }
       
    }
    private void renderDisplay(Graphics graphics){
        if(displayImage != null){
            graphics.drawImage(displayImage,displayX,displayY,displayW,displayH,null);
  
        }
    }
  public void renderText(Graphics graphics){
        if(textRender){
            Text.drawString(graphics, "Price: " + Integer.toString(price*count), textX, textY - 64, true,  Color.yellow, Assets.font35);
            Text.drawString(graphics, Integer.toString(count), textX, textY, true, Color.GREEN, Assets.font35);
            
        }
    }
    
    public void sellButtonActivate(){
        uiManager.getObjects().get(1).setActive(true);
        uiManager.getObjects().get(2).setActive(true);
        uiManager.getObjects().get(3).setActive(true);
        textRender = true;
    }
    
    public void sellButtonDeactivate(){
        uiManager.getObjects().get(1).setActive(false);
        uiManager.getObjects().get(2).setActive(false);
        uiManager.getObjects().get(3).setActive(false);
        textRender = false;
    }
    
    public void clickerActive(){
        
        uiManager.addObject(new UIImageButton((float)(invX + shopWidth/16),(float)(invY + shopHeight*53/64),32,32,Assets.left, () -> {
            len = uiManager.getObjects().size();
            if((index - max) >= 5){
                buttonSwitch(index, false);
                index-=max;
                buttonSwitch(index, true);
                pageNo = Integer.toString(Integer.parseInt(pageNo)-1);
            } 
        }));
        uiManager.addObject(new UIImageButton((float)(invX + shopWidth*18.5/64),(float)(invY + shopHeight*53/64),32,32,Assets.right, () -> {
            len = uiManager.getObjects().size();
            if((index + max) < len){
                buttonSwitch(index, false);
                index += max;
                buttonSwitch(index, true);
                pageNo = Integer.toString(Integer.parseInt(pageNo)+1);
            }
        }));
        index = 6;
        countW = 0;
        countH = 0;
        obj = handler.getWorld().getPlayer().getInventory().getInventoryItems();
        for(i = 0; i<obj.size();i++){
        uiManager.addObject(new UIImageButton((int)(itemX + (spaceW * (countW + 1)) + (itemWidth*countW)),(int)(itemY + (spaceH * (countH + 1)) + (itemHeight*countH)),itemWidth,itemHeight,obj.get(i).getImage(),obj.get(i).sell()));
         countW++;
        if(countW == 3){
            countW = 0;
            countH++;
            if(countH == 4)
                countH = 0;
        }
      }
        
        len = uiManager.getObjects().size();
        
        for(int i=1; i < len ; i++){
            if(i<=max+6)
                continue;
            
            uiManager.getObjects().get(i).setActive(false);
        }
        pageNo = "1";
        
    }
    
    private void buttonSwitch(int i, boolean bool){
        int j=0;
        while(i < len){
            uiManager.getObjects().get(i).setActive(bool);
            i++;
            j++;
            if(j > max)
                break;
         }
    }
    
    public boolean isActive(){
       return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public void activate(){
       init();
       displayImage = null;
       handler.getMouseManager().setUIManager(uiManager);
       active = true;
    }
    
    public void deactivate(){
       handler.getMouseManager().setUIManager(null);
       active = false;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public BufferedImage getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(BufferedImage displayImage) {
        this.displayImage = displayImage;
    }
    
}