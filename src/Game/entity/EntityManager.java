/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity;

import Game.Handler;
import Game.entity.creatures.Aswang;
import Game.entity.creatures.Manananggal;
import Game.entity.creatures.Manananggal2;
import Game.entity.creatures.Normie;
import Game.entity.creatures.Pig;
import Game.entity.creatures.Player;
import Game.entity.creatures.Spaniard;
import Game.entity.npc.NPC;
import Game.entity.npc.NPC1;
import Game.entity.npc.NPC2;
import Game.entity.statics.CoconutTree;
import Game.entity.npc.ShopNPC;
import Game.entity.statics.BananaTree;
import Game.entity.statics.Kubo1;
import Game.entity.statics.Kubo2;
import Game.entity.statics.Kubo3;
import Game.entity.statics.Ship;
import Game.entity.statics.Tree;
import Game.tiles.Tile;
import Game.util.Timer;
import Game.util.Utils;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Elan Fernandez
 */
public class EntityManager {
    
    private final Handler handler;
    private Player player;
    private final ArrayList<Entity> entities;
    private ArrayList<Entity>  mobs;
    private ArrayList<NPC> npcs;
    private final int width, height;
    private int entityID, entityX, entityY;
    private int xStart, yStart, xEnd, yEnd;
    private int deathCount;
    private final Timer respawnTimer;
    private ArrayList<String> spawner;
    private boolean playerDead, pause;
    
    private final Comparator<Entity> renderSorter = (Entity a, Entity b) -> {
        if((a.getY()+ a.height) < (b.getY() + b.height))
            return -1;
        return 1;
    };
        
    public EntityManager(Handler handler, Player player, String path){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        npcs = new ArrayList<>();
        mobs = new ArrayList<>();
        mobs.add(player);
        spawner = new ArrayList<>();
        respawnTimer = new Timer(8000);
        deathCount = 0;
        
        pause = false;
        playerDead = false;
        
        width = handler.getWidth();
        height = handler.getHeight();
        
        entities.add(player);
        
        loadEntity(path);
    }
    
    private void loadEntity(String path){
        String file = Utils.loadFile(path);
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.contains(",")){
                    String[] token = line.split(",");
                    entityID = Integer.parseInt(token[0]);
                    entityX = Integer.parseInt(token[1]);
                    entityY = Integer.parseInt(token[2]);
                    addEntity(entityID, entityX, entityY);
                }
            }
        }
    }
    
    public void update(){
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            
            if(playerDead || pause){
                player.update();
            }else
                e.update();
            
            if(!e.isActive() && !(e instanceof Player)){
                it.remove();
                Iterator<Entity> x = mobs.iterator();
                while(x.hasNext()){
                    Entity ex = x.next();
                    if(!ex.isActive())
                        x.remove();
                }
            }
            
            if(playerDead || pause)
                break;
               
        }
        entities.sort(renderSorter);
        respawn();
    }
    
    public void render(Graphics graphics){
        
//        xStart = (int) Math.max(0,handler.getCamera().getxOffset()/Tile.TILE_WIDTH);
//        xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
//        yStart = (int) Math.max(0,handler.getCamera().getyOffset()/Tile.TILE_HEIGHT);
//        yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
        
        xStart = (int) (handler.getCamera().getxOffset()/Tile.TILE_WIDTH - 5);
        xEnd = (int) ((handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        yStart = (int)(handler.getCamera().getyOffset()/Tile.TILE_HEIGHT - 5);
        yEnd = (int) ((handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
        
        entities.forEach((e) -> {
            int x = (int)(e.x / Tile.TILE_WIDTH);
            int y = (int)(e.y / Tile.TILE_HEIGHT);
            
            if (x >= xStart && x <= xEnd && y >= yStart && y <= yEnd) {
                if(playerDead){
                  if(!(e instanceof Player))
                        e.render(graphics);  
                }else
                    e.render(graphics);
            }
        });
    }
    
    public void addEntity(int entityID, int entityX, int entityY){
        switch (entityID) {
            case 1:
                entities.add(new Tree(handler, entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 1));
                break;
            case 2:
                entities.add(new BananaTree(handler, (entityX * Tile.TILE_WIDTH), (entityY * Tile.TILE_WIDTH), 2));
                break;
            case 3:
                entities.add(new CoconutTree(handler, (entityX * Tile.TILE_WIDTH), (entityY * Tile.TILE_WIDTH), 3));
                break;
            case 4:
                entities.add(new Kubo1(handler, (entityX * Tile.TILE_WIDTH), (entityY * Tile.TILE_WIDTH), 4));
                break;
            case 5:
                entities.add(new Kubo2(handler, entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 5));
                break;
            case 6:
                entities.add(new Kubo3(handler, entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 6));
                break;
            case 7:
                entities.add(new Pig(handler,  entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 7));
                mobs.add(entities.get(entities.size() - 1));
                break;
            case 8:
                entities.add(new Manananggal(handler,  entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 8));
                mobs.add(entities.get(entities.size() - 1));
                break;
            case 9:
                entities.add(new Aswang(handler,  entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 9));
                mobs.add(entities.get(entities.size() - 1));
                break;
            case 10:
                entities.add(new Manananggal2(handler,  entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH,10));
                mobs.add(entities.get(entities.size() - 1));
                break;
            case 11:
                entities.add(new ShopNPC(handler, entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 11));
                npcs.add((NPC)entities.get(entities.size() - 1));
                break;
            case 12:
                entities.add(new NPC1(handler, entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 12));
                npcs.add((NPC)entities.get(entities.size() - 1));
                break;
            case 13:
                entities.add(new NPC2(handler, entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH, 13));
                npcs.add((NPC)entities.get(entities.size() - 1));
                break;
            case 14:
                entities.add(new Ship(handler,  entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH,14));
                break;
            case 15:
                entities.add(new Spaniard(handler,  entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH,15));
                mobs.add(entities.get(entities.size() - 1));
                break;
            case 16:
                entities.add(new Normie(handler,  entityX * Tile.TILE_WIDTH, entityY * Tile.TILE_WIDTH,16));
                mobs.add(entities.get(entities.size() - 1));
                break;
            default:
                break;
        }
    }
    
    public void respawn(){
        if(respawnTimer.start() && deathCount > 0){
            Scanner scan = new Scanner(spawner.get(0));
            String line = scan.nextLine();
            String[] tokens = line.split(",");  
            System.out.println(tokens[0] + " " + tokens[1] + " " + tokens[2]);
            addEntity(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
            deathCount--;
            spawner.remove(0);
            if(deathCount > 0)
                respawnTimer.restart();
        }
    }
    
    public void respawnPlayer(){
        playerDead = true;
        player.setActive(true);
        player.getDialog().setDialog("", "You died");
        player.getDialog().activeSwitch();
    }
    
    public void setRespawn(int id, int x, int y){
        spawner.add(Integer.toString(id) + "," + Integer.toString(x) + "," + Integer.toString(y));
        if(deathCount == 0)
            respawnTimer.restart();
        deathCount++;
        
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public ArrayList<Entity> getMobs() {
        return mobs;
    }

    public boolean isPlayerDead() {
        return playerDead;
    }

    public void setPlayerDead(boolean playerDead) {
        this.playerDead = playerDead;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
    
    public boolean deads(){
        return(!(respawnTimer.start()));
    }
    
    
}