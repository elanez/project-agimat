package Game.inventory;

import Game.Handler;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.items.Item;
import Game.items.interfaces.Potion;
import Game.items.interfaces.Weapon;
import Game.tiles.Tile;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import Game.ui.UIObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Elan Fernandez
 */
public final class Inventory{
    
    private Handler handler;
    private boolean active = false;
    private final ArrayList<Item> inventoryItems;
    private Item item;
    private BufferedImage itemEquip;

    private final int invX, invY ,invWidth , invHeight;
    private final int invImageX, invImageY;
    private final int invImageWidth = Tile.TILE_WIDTH, invImageHeight = Tile.TILE_HEIGHT;
    
    ///shop
    private final float btnX, btnY;
    private final int btnW, btnH;
    
    private int countW, countH;
    private int equipID;

    BufferedImage displayImage;
    private final int displayX, displayY, displayW, displayH;
    
    private int len, index, count, prevIndex;
    private String pageNo, totalPageNo;
    private final int max;
    private ArrayList<UIObject> obj;
    
    private final double spaceW, spaceH;
    
    private UIManager uiManager;
    
    public Inventory(Handler handler){
        this.handler = handler;
        
        inventoryItems = new ArrayList<>();
        
        invX = 20;
        invY = 20;
        invWidth = handler.getWidth() - 40;
        invHeight = handler.getHeight() - 40;
        
        invImageX = invWidth/13;
        invImageY = (int)(invHeight/3.5);
                
        spaceW = 30;
        spaceH = 30;
        
        btnX = invWidth / 8;
        btnY = invHeight / 7;
        btnW = (int)(btnX / 2);
        btnH = (int)(btnY / 2);
        
        displayImage = null;
        displayW = Tile.TILE_WIDTH * 3;
        displayH = Tile.TILE_HEIGHT * 3;
        displayX = (invWidth*7)/16;
        displayY = invHeight/4;
        
        max = 12;
        pageNo = "1";
        totalPageNo = "1";
    }
    
    public void loadItem(int id,int count){
       System.out.printf("ID: %d COUNT:%d", id, count);
               
       inventoryItems.add(Item.getItem(id).createNew(count));
    }
    
    public void update(){
        if(uiManager != null){
            uiManager.update();
        }
        
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_B) && !handler.getWorld().getPlayer().notMoveable()){
            activate();
        }else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_B) && active)
            deactivate();
        
        if((pageNo.equals("0") || Integer.parseInt(pageNo) > Integer.parseInt(totalPageNo)) && active)
            activate();
    }
    
    public void render(Graphics graphics){
        if(!active)
            return;
        
        graphics.drawImage(Assets.ShopInventory, invX,invY,invWidth,invHeight, null);
        graphics.drawImage(Assets.iconGold,(invX + invWidth*10/11), 225,32,32,null);
        Text.drawString(graphics,Integer.toString(handler.getWorld().getPlayer().getPlayerMoney()),(invX + invWidth*9/11), 250, false, Color.YELLOW, Assets.font28);
        Text.drawString(graphics,"GOLD:",(invX + invWidth*7/10), 250, false, Color.YELLOW, Assets.font28);
        graphics.drawImage(Assets.iconDmg,(invX + invWidth*10/11), 275,32,32,null);
        Text.drawString(graphics,Integer.toString(handler.getWorld().getPlayer().getDamage()),(invX + invWidth*9/11), 300, false, Color.RED, Assets.font28);
        Text.drawString(graphics,"DAMAGE: ",(invX + invWidth*7/10), 300, false, Color.RED, Assets.font28);
        graphics.drawImage(Assets.iconHealth,(invX + invWidth*10/11), 325,32,32,null);
        Text.drawString(graphics,Integer.toString((int) handler.getWorld().getPlayer().getHealth()),(invX + invWidth*9/11), 350, false, Color.GREEN, Assets.font28);
        Text.drawString(graphics,"HEALTH: ",(invX + invWidth*7/10), 350, false, Color.GREEN, Assets.font28);
        graphics.drawImage(Assets.iconStamina,(invX + invWidth*10/11), 375,32,32,null);
        Text.drawString(graphics,Integer.toString((int) handler.getWorld().getPlayer().getStamina()),(invX + invWidth*9/11), 400, false, Color.BLUE, Assets.font28);
        Text.drawString(graphics,"STAMINA: ",(invX + invWidth*7/10), 400, false, Color.BLUE, Assets.font28);
        graphics.drawImage(Assets.iconSpeed,(invX + invWidth*10/11), 425,32,32,null);
        Text.drawString(graphics,Integer.toString((int) handler.getWorld().getPlayer().getSpeed()),(invX + invWidth*9/11), 450, false, Color.MAGENTA, Assets.font28);
        Text.drawString(graphics,"SPEED: ",(invX + invWidth*7/10), 450, false, Color.MAGENTA, Assets.font28);
        Text.drawString(graphics,"SCORE: " + Integer.toString(handler.getWorld().getPlayer().getPlayerScore()),(invX + invWidth*7/10), 500, false, Color.CYAN, Assets.font28);
        len = obj.size();
        
        count = 0;
        prevIndex = index;
       
        obj.get(0).render(graphics);
        obj.get(1).render(graphics);
        obj.get(2).render(graphics);
        obj.get(3).render(graphics);
        
        Text.drawString(graphics,pageNo + "/" + totalPageNo,(int)(invX + invWidth*3/16),(int)(invY + invHeight*27/32),true,Color.WHITE,Assets.font28);
        
        countW = 0;
        countH = 0;
        
        if(displayImage != null)
            graphics.drawImage(displayImage, displayX, displayY,displayW,displayH,null);
        
        while(index<len){
            obj.get(index).render(graphics);
            Item it = inventoryItems.get(index - 4);
            
            Text.drawString(graphics, Integer.toString(it.getCount()),(int)(invImageX +  (invImageWidth * countW) +(spaceW * (countW+1))), (int)((invImageHeight * countH) + invImageY + (spaceH * (countH+1))), true, Color.white, Assets.font28);
            
            countW++;
            if(countW == 3){
                countW = 0;
                countH++;
                if(countH == 4)
                    countH = 0;
            }
            
            index++;
            count++;
            if(count > max)
                break;
        }
        
        index = prevIndex;
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
    
    public void addItem(Item item){
        for(Item i: inventoryItems){
            if(i.getID() == item.getID()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }
    
    public void remove(Item item){
        for(int i = 0; i < inventoryItems.size(); i++){
            if(inventoryItems.get(i).getID() == item.getID()){
                inventoryItems.get(i).setCount(inventoryItems.get(i).getCount() - item.getCount());
                if(inventoryItems.get(i).getCount()< 1)
                    inventoryItems.remove(i);
                return;
            }
        }
        inventoryItems.remove(item);
    }
    
    public void remove(Item item, int count){
        for(int i = 0; i < inventoryItems.size(); i++){
            if(inventoryItems.get(i).getID() == item.getID()){
                inventoryItems.get(i).setCount(inventoryItems.get(i).getCount() - count);
                if(inventoryItems.get(i).getCount()< 1)
                    inventoryItems.remove(i);
                return;
            }
        }
        inventoryItems.remove(item);
    } 
    
    public void activate(){
        handler.getWorld().getPlayer().setPots(false);
        active = true;
        countW = 0;
        countH = 0;
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.addObject(new UIImageButton(invX + invWidth/2 - 35,invY + invHeight - 128,70,64,Assets.equipButton, () -> {
        handler.getWorld().getPlayer().loadAttackAnim();
        handler.getWorld().getPlayer().loadWeaponElement();
        if(handler.getWorld().getPlayer().getHealerActive() == 1){
        handler.getWorld().getPlayer().checkDiwataHeal();
        handler.getWorld().getPlayer().setHealer(1);
        }
            if(item instanceof Weapon)
                ((Weapon)item).damageBuff();
            else if(item instanceof Potion)
                ((Potion)item).buff();
        }));

        equipDeactivate();
        
        int temp = handler.getWorld().getPlayer().getInventory().getInventoryItems().size()/max;
        if(handler.getWorld().getPlayer().getInventory().getInventoryItems().size()%max > 0 || temp==0)
            temp++;
        
        pageNo = "1";
        totalPageNo = Integer.toString(temp);
        
        uiManager.addObject(new UIImageButton((float)(invX + invWidth/16),(float)(invY + invHeight*53/64),32,32,Assets.left, () -> {
            len = uiManager.getObjects().size();
            if((index - max) >= 3){
                buttonSwitch(index, false);
                index-=max;
                buttonSwitch(index, true);
                pageNo = Integer.toString(Integer.parseInt(pageNo)-1);
            } 
        }));
        uiManager.addObject(new UIImageButton((float)(invX + invWidth*18.5/64),(float)(invY + invHeight*53/64),32,32,Assets.right, () -> {
            len = uiManager.getObjects().size();
            if((index + max) < len){
                buttonSwitch(index, false);
                index += max;
                buttonSwitch(index, true);
                pageNo = Integer.toString(Integer.parseInt(pageNo)+1);
            }
        }));
        
        uiManager.addObject(new UIImageButton((float)(invX + invWidth - 32),(float)(invY),32,32,Assets.closeBtn, () -> {
            deactivate();
        }));
        
        index = 4;

        for(int i = 0; i < inventoryItems.size();i++){
        uiManager.addObject(new UIImageButton( (int)(invImageX +  (invImageWidth * countW) +(spaceW * (countW+1))), (int)((invImageHeight * countH) + invImageY + (spaceH * (countH+1))), invImageWidth, invImageHeight,inventoryItems.get(i).getImage(),inventoryItems.get(i).equip()));
        countW++;
        if(countW == 3){
            countW = 0;
            countH++;
            if(countH == 4)
                countH = 0;
            }
        }
        
        obj = uiManager.getObjects();
        len = uiManager.getObjects().size();
        
        count = 0;
        for(int i=index; i < len ; i++){
            count++;
            if(count <= max)
                continue;
            
            uiManager.getObjects().get(i).setActive(false);
        }
    }
    
      public void equipped(int id){
     
    }
    public void equipActivate(){
       uiManager.getObjects().get(0).setActive(true);
    }
    
    public void equipDeactivate(){
        uiManager.getObjects().get(0).setActive(false);
    }

    public void deactivate(){
        displayImage = null;
        active = false;
        handler.getMouseManager().setUIManager(null);
        handler.getWorld().getPlayer().setPots(true);
    }
    
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

    public ArrayList<Item> getInventoryItems() {
        return inventoryItems;
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public int getEquipID() {
        return equipID;
    }

    public void setEquipID(int equipID) {
        this.equipID = equipID;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public BufferedImage getItemEquip() {
        return itemEquip;
    }

    public void setItemEquip(BufferedImage itemEquip) {
        this.itemEquip = itemEquip;
    }

    public void setDisplayImage(BufferedImage displayImage) {
        this.displayImage = displayImage;
    }

}
