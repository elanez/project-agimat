/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.world;

import Game.Handler;
import Game.entity.Entity;
import Game.entity.EntityManager;
import Game.entity.creatures.Player;
import Game.items.ItemManager;
import Game.quest.Quest;
import Game.shop.SellShop;
import Game.shop.Shop;
import Game.tiles.Tile;
import Game.tiles.TileManager;
import Game.ui.MapEditorUI;
import Game.util.Timer;
import Game.util.Utils;
import java.awt.Graphics;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Elan Fernandez
 */
public class World {
    
    private Handler handler;
    private EntityManager entityManager;
    private ItemManager itemManager;
    private MapEditorUI mapEditorUI;
    private final Player player;
    private final TileManager tileManager;
    private final Timer timer, saveTimer;
    private final Shop shop;
    private final SellShop sellShop;
    private final Map map;
    
    private final int fill = 0;
    private int width, height;
    private int[][] worldTiles;
    private int tileFill = 0, entityID = 0;
    private boolean tileInput;
    private boolean entityInput;
    private final ArrayList<String> editedTiles;
//    private final ArrayList<String> placedEntities;
    private int xStart, yStart, xEnd, yEnd;
    private final String name, path, entityPath;
//    private int camX, camY;
    
    public World(Handler handler, String path, String entityPath, Player player, TileManager tileManager, String name){
        this.handler = handler;
        this.player = player;
        this.name = name;
        this.path = path;
        this.entityPath = entityPath;
        entityManager = new EntityManager(handler, player, entityPath);
        
        this.tileManager = tileManager;
        itemManager = new ItemManager(handler);
        
        if(player.getLoginID().equals("1"))
            mapEditorUI = new MapEditorUI(handler);
       
        shop = new Shop(handler);
        sellShop = new SellShop(handler);
        editedTiles = new ArrayList<>();
                
        loadWorld(path);
        
        map = new Map(handler, worldTiles,width,height);
        
        tileInput = true;
        entityInput = true;
        
        timer = new Timer(250);
        saveTimer = new Timer(250);
    }
    
    public void update(){
        itemManager.update();
        entityManager.update();
        
        if(shop.isActive())
            shop.update();
        if(sellShop.isActive())
            sellShop.update();
        
//        xStart = (int) Math.max(0,handler.getCamera().getxOffset()/Tile.TILE_WIDTH);
//        xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
//        yStart = (int) Math.max(0,handler.getCamera().getyOffset()/Tile.TILE_HEIGHT);
//        yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
        
        xStart = (int) (handler.getCamera().getxOffset()/Tile.TILE_WIDTH - 1);
        xEnd = (int) ((handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        yStart = (int) (handler.getCamera().getyOffset()/Tile.TILE_HEIGHT - 1);
        yEnd = (int) ((handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
        
        
        for(int x = xStart; x < xEnd; x++){
            for(int y = yStart; y< yEnd; y++){
                getTile(x, y).update();
          }
        }
        
        if(mapEditorUI != null){
            mapEditorUI.update();
            
            if(!mapEditorUI.isActive())
                return;
            
            if(handler.getMouseManager().isRightPressed() && timer.start()){
                removeEntity();
                timer.restart();
            }
            
            if(handler.getMouseManager().isLeftPressed() && timer.start()){
            setTile(tileFill);
            setEntity();
            timer.restart();
            }
        
            if(saveTimer.start() && handler.getKeyManager().ctrlBtn && handler.getKeyManager().saveBtn){
            saveWorld(path);
            saveEntities(entityPath);
            saveTimer.restart();
            }
        }
    }
    
    public void render(Graphics graphics){       
        for(int x = xStart; x < xEnd; x++){
            for(int y = yStart; y< yEnd; y++){
                getTile(x,y).render(graphics, (int)(x * Tile.TILE_WIDTH - handler.getCamera().getxOffset()), (int)(y * Tile.TILE_HEIGHT - handler.getCamera().getyOffset()));
          }
        }
        itemManager.render(graphics);
        entityManager.render(graphics);
        map.render(graphics);
        
        player.postRender(graphics);
        
        if(shop.isActive())
            shop.render(graphics);
        if(sellShop.isActive())
            sellShop.render(graphics);
        
        if(mapEditorUI != null)
            mapEditorUI.render(graphics);
    }

    public Tile getTile(int x, int y){
        if(y >= height)
            return tileManager.getTile(6);
        else if(x < 0 || y < 0 || x >= width)
            return tileManager.getTile(1);
        
        if(worldTiles[x][y] == 0){
            worldTiles[x][y] = fill;
        }
        
        return tileManager.getTile(worldTiles[x][y]);
    }
    
    public void setTile(int tileFill){
        if(!tileInput || (mapEditorUI.getRect().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())))
            return;
        
        int tileX = (int)Math.floor((handler.getMouseManager().getMouseX() + handler.getCamera().getxOffset())/Tile.TILE_WIDTH);
        int tileY = (int)Math.floor((handler.getMouseManager().getMouseY() + handler.getCamera().getyOffset())/Tile.TILE_HEIGHT);

        if(tileX < 0 || tileY < 0 || tileX >= handler.getWorld().getWidth() || tileY >= handler.getWorld().getHeight())
            return;
        
        worldTiles[tileX][tileY] = tileFill;
        
        String str;
        str = tileFill + "," + tileX + "," + tileY;
        if(tileFill != 0){
            for(String temp: editedTiles){
                String[] tokens = temp.split(",");
                if(Integer.parseInt(tokens[1]) == tileX && Integer.parseInt(tokens[2]) ==tileY){
                    editedTiles.set(editedTiles.indexOf(temp), str);
                    return;
                }     
            }
            editedTiles.add(str);
        }else{
            for(String temp: editedTiles){
                String[] tokens = temp.split(",");
                if(Integer.parseInt(tokens[1]) == tileX && Integer.parseInt(tokens[2]) ==tileY){
                    editedTiles.remove(temp);
                    return;
                } 
            }
        }
    }
    
    public void setEntity(){
        if(tileInput || (mapEditorUI.getRect().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())))
            return;
        
        int entityX = (int)Math.floor((handler.getMouseManager().getMouseX() + handler.getCamera().getxOffset())/Tile.TILE_WIDTH);
        int entityY = (int)Math.floor((handler.getMouseManager().getMouseY() + handler.getCamera().getyOffset())/Tile.TILE_HEIGHT);
        entityManager.addEntity(entityID, entityX, entityY);
        
//        String str = entityID+"," + entityX + ","+entityY;
        
//        for(String temp: placedEntities){
//            String[] tokens = temp.split(",");
//            if(Integer.parseInt(tokens[1]) == entityX && Integer.parseInt(tokens[2]) == entityY)
//            {
//                placedEntities.set(placedEntities.indexOf(temp), str);
//                return;
//            }
//        }
//        placedEntities.add(str);
    }
    
    public void removeEntity(){
        if(mapEditorUI.getRect().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()))
            return;
        
        int entityX = (int)Math.floor((handler.getMouseManager().getMouseX() + handler.getCamera().getxOffset()));
        int entityY = (int)Math.floor((handler.getMouseManager().getMouseY() + handler.getCamera().getyOffset()));
        for(int i = 0; i < entityManager.getEntities().size();i++){
            if(entityManager.getEntities().get(i).getCollisionBounds(0, 0).contains(entityX, entityY)){
                entityManager.getEntities().remove(i);
                return;
            }
                   
        }
        
    }
 
    private void loadWorld(String path){
        String file = Utils.loadFile(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.contains(":")){
                String [] token = line.split(":");
                width = Integer.parseInt(token[0]);
                height = Integer.parseInt(token[1]);                
                worldTiles = new int [width][height];
            }else if(line.contains(",")){
                editedTiles.add(line);
                String[] token = line.split(",");
                if(token.length >= 3){
                    worldTiles[Integer.parseInt(token[1])][Integer.parseInt(token[2])] = Integer.parseInt(token[0]);
                }
            }
        }
        scanner.close();
       
    }
    
    private void saveWorld(String path){
        try {
            PrintWriter out = new PrintWriter(path);
            out.println("50:50");
            for(String temp: editedTiles){
                out.println(temp);
            }
            out.close();
            
            System.out.println("SAVE");
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
      private void saveEntities(String path){
        try {
            PrintWriter out = new PrintWriter(path);
            String temp;
            ArrayList<Entity> e = getEntityManager().getEntities();
            for (Entity e1 : e) {
                temp = Integer.toString(e1.getId()) + "," + Integer.toString(e1.getStartX()) + "," + Integer.toString(e1.getStartY());
                out.println(temp);
            }
            out.close();
            
            System.out.println("SAVE");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    public int getTileFill() {
        return tileFill;
    }

    public void setTileFill(int tileFill) {
        this.tileFill = tileFill;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isTileInput() {
        return tileInput;
    }

    public void setTileInput(boolean tileInput) {
        this.tileInput = tileInput;
    }
    
    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public Quest getQuest() {
        return handler.getWorld().getPlayer().getQuest();
    }

    public Shop getShop() {
        return shop;
    }

    public String getName() {
        return name;
    }
    
    public SellShop getSellShop() {
        return sellShop;
    }
    
}
