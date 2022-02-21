package Game.shop;

import Game.Handler;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.items.Item;
import Game.tiles.Tile;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import Game.ui.UIObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Elan Fernandez
 */
public class Shop{
    private final Handler handler;
    private final UIManager uiManager;
    private final ShopWeapons weapon;
    private final ShopPotion potion;
    private Item item;

    public Item getItem() {
        return item;
    }
    private boolean active;
    
    public static int shopX, shopY, shopWidth, shopHeight;
    
    BufferedImage displayImage;
    private final int displayX, displayY, displayW, displayH;
    private final int textX, textY;
    
    private int  index, prevIndex, len;
    private ArrayList<UIObject> btns, obj;
    private final int max;
    
    private final float btnX, btnY;
    private final int btnW, btnH;
    private String str1;
    private int i, price,text =0;
    
    private final int invWidth , invHeight;
    private final int invX, invY;
    private String totalPageNo, pageNo;
    private int indexInv, prevIndexInv, lenInv;
    private String totalPageNoInv, pageNoInv;
    private ArrayList<Item> items;
    
    private final int invImageX, invImageY;
    private final int invImageWidth = Tile.TILE_WIDTH, invImageHeight = Tile.TILE_HEIGHT;
    private final double spaceW, spaceH;
    
    public Shop(Handler handler){
        this.handler = handler;
        uiManager = new UIManager(handler);
        
        active = false;
        
        shopX = 20;
        shopY = 20;
        shopWidth = handler.getWidth() - 40;
        shopHeight = handler.getHeight() - 40;
        
        displayW = Tile.TILE_WIDTH * 3;
        displayH = Tile.TILE_HEIGHT * 3;
        displayX = (shopWidth*7)/16;
        displayY = shopHeight/4;
        
        btnX = shopWidth / 8;
        btnY = shopHeight / 7;
        btnW = (int)(btnX / 2);
        btnH = (int)(btnY / 2);
        btns = new ArrayList<>();
        
        textX = displayX + displayW/2;
        textY = (int)(displayY*3.2);
        
        i=1;
        price = 0;
        max = 12;
        
        invX = 20;
        invY = 20;
        
        weapon = new ShopWeapons(handler);
        potion = new ShopPotion(handler);
        
        invWidth = handler.getWidth() - 40;
        invHeight = handler.getHeight() - 40;
        invImageX = (int)(handler.getWidth()*11/16);
        invImageY = (int)(invHeight/3.5);
        spaceW = 30;
        spaceH = 30;
        
        init();
        
        pageNo = "1";
        totalPageNo = "1";
        pageNoInv = "1";
        totalPageNoInv = "1";
      
        uiManager.setObjects(changeItemsToWeapons());
    }
    
    private void init(){
        btns.add(new UIImageButton(btnX,btnY, btnW, btnH,Assets.sandataButton, () -> {
            buttonSwitch(index, false);
            index = 10;
            buttonSwitch(index, true);
            uiManager.setObjects(changeItemsToWeapons());
        }));
        
        btns.add(new UIImageButton(btnX+btnW+20,btnY, btnW, btnH,Assets.salamankaButton, () -> {
            buttonSwitch(index, false);
            index = 10;
            buttonSwitch(index, true);
            uiManager.setObjects(changeItemsToPotions());
        }));
        
        btns.add(new UIImageButton(shopX + shopWidth - 32,shopY, 32, 32,Assets.closeBtn, () -> {
            deactivate();
        }));
        
        btns.add(new UIImageButton(displayX,textY-20, 40, 50,Assets.minus, () -> {
            i--;
            if(i < 1){
                i = 999;
            }
        }));
            btns.get(3).setActive(false);
        
        btns.add(new UIImageButton(displayX + displayW - 40,textY-20, 40, 50,Assets.plus, () -> {
            i++;
            if(i > 999){
                i = 1;
            }
            
        }));
            btns.get(4).setActive(false);
            
        btns.add(new UIImageButton(textX - btnW/2,textY + btnH, btnW, btnH,Assets.buyBtn, () -> {
            if(handler.getWorld().getPlayer().getPlayerMoney() < (price * i))
                System.out.println("Not enought money");
            else{
                Item x = item.createNew(i);
                handler.getWorld().getPlayer().getInventory().addItem(x);
                handler.getWorld().getItemManager().addItem(x);
                handler.getWorld().getPlayer().setPlayerMoney(handler.getWorld().getPlayer().getPlayerMoney() - (price * i));
                i = 1;
            }
        }));
            btns.get(5).setActive(false);
        
        btns.add(new UIImageButton((float)(invX + invWidth/16),(float)(invY + invHeight*53/64),32,32,Assets.left, () -> {
            len = uiManager.getObjects().size();
            if((index - max) >= 5){
                buttonSwitch(index, false);
                index-=max;
                buttonSwitch(index, true);
                pageNo = Integer.toString(Integer.parseInt(pageNo)-1);
            } 
        }));
        btns.add(new UIImageButton((float)(invX + invWidth*18.5/64),(float)(invY + invHeight*53/64),32,32,Assets.right, () -> {
            len = uiManager.getObjects().size();
            if((index + max) < len){
                buttonSwitch(index, false);
                index += max;
                buttonSwitch(index, true);
                pageNo = Integer.toString(Integer.parseInt(pageNo)+1);
            }
        })); 
        //inventory
        btns.add(new UIImageButton((float)(invX + invWidth*22/32),(float)(invY + invHeight*53/64),32,32,Assets.left, () -> {
            lenInv = handler.getWorld().getPlayer().getInventory().getInventoryItems().size();
            if((indexInv - max) >= 0){
                indexInv-=max;
                pageNoInv = Integer.toString(Integer.parseInt(pageNoInv)-1);
            } 
        }));
        btns.add(new UIImageButton((float)(invX + invWidth*58.5/64),(float)(invY + invHeight*53/64),32,32,Assets.right, () -> {
            lenInv = handler.getWorld().getPlayer().getInventory().getInventoryItems().size();
            if((indexInv + max) < lenInv){
                indexInv += max;
                pageNoInv = Integer.toString(Integer.parseInt(pageNoInv)+1);
            }
        }));
        indexInv = 0;
        pageNoInv = "1";
        
    }
    
    private void buttonSwitch(int i, boolean bool){
        int j=0;
        while(i < len){
            obj.get(i).setActive(bool);
            i++;
            j++;
            if(j > max)
                break;
         }
    }
    
    public void update(){
        if(active){
            uiManager.update();
            
            if(pageNo.equals("0") || Integer.parseInt(pageNo) > Integer.parseInt(totalPageNo)){
            init();
            }
            
            if(pageNoInv.equals("0") || Integer.parseInt(pageNoInv) > Integer.parseInt(totalPageNoInv)){
                init();
            }
        }
    }
    
    public void render(Graphics graphics) {
        if(active){
            graphics.drawImage(Assets.ShopInventory, shopX, shopY,shopWidth,shopHeight, null);
            graphics.drawImage(Assets.iconGold,(invX + invWidth*19/22), 150,32,32,null);
            
            Text.drawString(graphics,"SHOP",shopX+shopWidth/2, shopY+50, true, Color.WHITE, Assets.font35);
            Text.drawString(graphics,"INVENTORY",shopX+shopWidth-270, shopY+100, true, Color.WHITE, Assets.font35);    
            Text.drawString(graphics,Integer.toString(handler.getWorld().getPlayer().getPlayerMoney()),(invX + invWidth*8/10)-30, 175, false, Color.YELLOW, Assets.font28);
            
            int count = 0;
            
            uiManager.getObjects().get(0).render(graphics);
            uiManager.getObjects().get(1).render(graphics);
            uiManager.getObjects().get(2).render(graphics);
            uiManager.getObjects().get(3).render(graphics);
            uiManager.getObjects().get(4).render(graphics);
            uiManager.getObjects().get(5).render(graphics);
            uiManager.getObjects().get(6).render(graphics);
            uiManager.getObjects().get(7).render(graphics);
            uiManager.getObjects().get(8).render(graphics);
            uiManager.getObjects().get(9).render(graphics);
            
            Text.drawString(graphics,pageNo + "/" + totalPageNo,(int)(invX + invWidth*3/16),(int)(invY + invHeight*27/32),true,Color.WHITE,Assets.font28);
            if(text == 1){
            Text.drawString(graphics,"NAME:" +" "+ getItem().getName(),(int)(invX + invWidth*9.5/24), displayY + displayH + 30, false, Color.WHITE, Assets.font18);
            Text.drawString(graphics,"DESCRIPTION:"+ " " + getItem().getDescription(),(int)(invX + invWidth*9.5/24), displayY + displayH + 50, false, Color.WHITE, Assets.font18);
            Text.drawString(graphics,"PRICE:" +" " + Integer.toString(getItem().getPrice()*i),(int)(invX + invWidth*9.5/24), displayY + displayH + 70,false,Color.YELLOW, Assets.font18);
            
            }
            
            prevIndex = index;
            
            while(index < len){
                uiManager.getObjects().get(index).render(graphics);
                index++;
                count++;
                if(count >= max)
                    break;
                
            }
            
            index = prevIndex;
                        
//            uiManager.render(graphics);
            renderDisplay(graphics);
            renderInventory(graphics);
        }
    }

    public void setText(int text) {
        this.text = text;
    }
    
    private void renderDisplay(Graphics graphics){
        if(displayImage != null){
            graphics.drawImage(displayImage,displayX,displayY,displayW,displayH,null);
            str1 = Integer.toString(i);
            
            Text.drawString(graphics, str1, textX, textY, true, Color.GREEN, Assets.font35);
        }
    }
    
    private void renderInventory(Graphics graphics){
        items = handler.getWorld().getPlayer().getInventory().getInventoryItems();
        lenInv = items.size();
        
        int temp = items.size()/max;
        if(items.size()%max > 0 || temp == 0);
            temp++;
        
        totalPageNoInv = Integer.toString(temp);
        
        int countW = 0;
        int countH = 0;
        int count = 0;
        Text.drawString(graphics,pageNoInv + "/" + totalPageNoInv,(int)(invX + invWidth*26/32),(int)(invY + invHeight*27/32),true,Color.WHITE,Assets.font28);
        
        prevIndexInv = indexInv;
        
        while(indexInv < lenInv){
            graphics.drawImage(items.get(indexInv).getImage(), (int)(invImageX +  (invImageWidth * countW) +(spaceW * (countW+1))), (int)((invImageHeight * countH) + invImageY + (spaceH * (countH+1))), invImageWidth, invImageHeight,null);
            Text.drawString(graphics, Integer.toString(items.get(indexInv).getCount()),(int)(invImageX +  (invImageWidth * countW) +(spaceW * (countW+1))), (int)((invImageHeight * countH) + invImageY + (spaceH * (countH+1))), true, Color.white, Assets.font28);
            countW++;
            if(countW == 3){
                countW = 0;
                countH++;
                if(countH == 4)
                    countH = 0;
            }
            indexInv++;
            count++;
            if(count >= max)
                break;
        }
        
        indexInv = prevIndexInv;
        
    }
    
    public ArrayList<UIObject> changeItemsToWeapons(){
        obj = new ArrayList<>();
        obj.addAll(btns);
        obj.addAll(weapon.getWeapons());
        
        int temp = weapon.getWeapons().size()/max;
        if(weapon.getWeapons().size()%max > 0  || temp==0)
            temp++;
        
        totalPageNo = Integer.toString(temp);
        
        pageNo = "1";
        
        index = 10;
        len = obj.size();
        for(int i=index; i < len ; i++){
            if(i<=(max+index))
                continue;
            
            obj.get(i).setActive(false);
        }
        
        return obj;
    }
    
    public ArrayList<UIObject> changeItemsToPotions(){
        obj = new ArrayList<>();
        obj.addAll(btns);
        obj.addAll(potion.getPotionBtns());
        
        int temp = potion.getPotionBtns().size()/max;
        if(potion.getPotionBtns().size()%max > 0  || temp==0)
            temp++;
        
        totalPageNo = Integer.toString(temp);
        
        pageNo = "1";
        
        index = 10;
        len = obj.size();
        for(int i=index; i < len ; i++){
            if(i<=(max+index))
                continue;
            
            obj.get(i).setActive(false);
        }
        return obj;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void activate(){
       active = true;
       handler.getMouseManager().setUIManager(uiManager);
    }
    
    public void deactivate(){
        active = false;
        displayImage = null;
        btns.get(3).setActive(false);
        btns.get(4).setActive(false);
        btns.get(5).setActive(false);
        handler.getMouseManager().setUIManager(null);
    }

    public ArrayList<UIObject>  getWeapon() {
        return weapon.getWeapons();
    }

    public ArrayList<UIObject>  getPotion() {
        return potion.getPotionBtns();
    }

    public void setButtons(ArrayList<UIObject> items){
        uiManager.setObjects(items);
    }

    public void setDisplayImage(BufferedImage displayImage) {
        this.displayImage = displayImage;
    }

    public ArrayList<UIObject> getObj() {
        return obj;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}