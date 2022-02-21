/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.items;

import Game.Handler;
import Game.graphics.Assets;
import Game.ui.ClickListener;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elan Fernandez
 */
public abstract class Item {
    
    public static Item[] items = new Item[256];
    public static Item woodSword;
    public static Item redPotion;
    public static Item goldSword;
    public static Item bluePotion;
    public static Item greenPotion;
    public static Item fireAgimat;
    public static Item waterAgimat;
    public static Item windAgimat;
    public static Item silverSword;
    public static Item ulo,baboy,dila,diwata;
    
    public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32;
    
    protected Handler handler;
    protected BufferedImage image;
    protected String name;
    protected final int id;
    
    protected Rectangle bounds;
    
    protected int x, y, count, price;
    protected String description;

    public String getDescription() {
        return description;
    }
    protected boolean pickedUp = false;
    
    public Item(Handler handler,BufferedImage image, String name, int id){
        this.handler = handler;
        this.image = image;
        this.name = name;
        this.id = id;
        
        count = 1;
        price = 0;
        
        bounds = new Rectangle(x,y, ITEM_WIDTH, ITEM_HEIGHT);
        
        items[id] = this;
    }
    
    public static void init(Handler handler){
        woodSword = new WoodSword(handler,0);
        silverSword = new SilverSword(handler,1);
        goldSword = new GoldSword(handler,2);
        redPotion = new HealthPotion(handler,3);
        bluePotion = new ManaPotion(handler,4);
        greenPotion = new DebuffPotion(handler,5);
        fireAgimat = new FireAgimat(handler,6);
        waterAgimat = new WaterAgimat(handler,7);
        windAgimat = new WindAgimat(handler,8);
        ulo = new Ulo(handler,9);
        baboy = new Baboy(handler,10);
        dila = new Dila(handler,11);
        diwata = new Diwata(handler,12);
    }
    
    public static Item getItem(int id){
        switch(id){
            case 0:
                return Item.woodSword;
            case 1:
                return Item.silverSword;
            case 2:
                return Item.goldSword;
            case 3:
                return Item.redPotion;
            case 4:
                return Item.bluePotion;
            case 5:
                return Item.greenPotion;
            case 6:
                return Item.fireAgimat;
            case 7:
                return Item.waterAgimat;
            case 8:
                return Item.windAgimat;
            case 9:
                return Item.ulo;
            case 10:
                return Item.baboy;
            case 11:
                return Item.dila;
            case 12:
                return Item.diwata;
        }
        return null;
    }
    
    public abstract Item createNew(int count);
    public abstract Item createNew(int x, int y);
    
    public ClickListener sell(){
        Item i = this;
        return ((new ClickListener() {
            @Override
            public void onClick(){  
                handler.getWorld().getSellShop().setDisplayImage(i.image);
                handler.getWorld().getSellShop().setPrice(price/2);
                handler.getWorld().getSellShop().setItem(i);
                handler.getWorld().getSellShop().setCount(count);
                handler.getWorld().getSellShop().sellButtonActivate();
            }
        }));
    }
    
    public ClickListener equip() {
        Item i = this;
         return ((new ClickListener() {
            @Override
            public void onClick(){  
                handler.getWorld().getPlayer().getInventory().setItem(i);
                handler.getWorld().getPlayer().getInventory().equipActivate();
                handler.getWorld().getPlayer().getInventory().setEquipID(id);
                handler.getWorld().getPlayer().getInventory().setItemEquip(i.image);
                handler.getWorld().getPlayer().getInventory().setDisplayImage(i.image);
            }
        }));
      
    }
    
    
    public void update(){
        if(handler.getWorld().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)){
            pickedUp = true;
            handler.getWorld().getPlayer().getInventory().addItem(this);
        }
    }
    
    public void render(Graphics graphics){
        if(handler == null)
            return;
        
        render(graphics, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()));
    }
    
    public void render(Graphics graphics, int x, int y){
        graphics.drawImage(image, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public int getID(){
        return id;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }
    
    public void setPickedUp(boolean pickedUp){
        this.pickedUp = pickedUp;
    }


    public int getPrice() {
        return price;
    }
}
