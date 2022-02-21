/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.world;

import Game.Handler;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class Map {
    private Handler handler;
    private int[][] worldTiles;
    private int tileSize;
    private int mapX, mapY, mapSize;
    private int width, height;
    private int id;
    private final Color grass;
    private final Color dirt;
    private final Color cliff;
    private final Color sand;
    private final Color water;
    private final Color deepWater;
    private final Color plank;
    private final Color portal;
    private final Color rock;

    public Map(Handler handler, int [][] worldTiles, int width, int height) {
        this.handler = handler;
        this.worldTiles = worldTiles;
        
        mapSize = 200;
        mapX = 0;
        mapY = handler.getHeight() - mapSize;
        
        this.width = width;
        this.height = height;
        tileSize = mapSize/width;
        grass = new Color( 73, 186, 51);
        dirt = new Color (153, 102, 51);
        cliff = new Color(88, 57, 25);
        sand = new Color(249, 200, 86);
        water = new Color (0, 235, 243);
        deepWater = new Color (37, 117, 146);
        portal = new Color (196, 52, 232);
        rock = new Color (108,108,108);
        plank = new Color (95, 48,1);
    }
    public void render(Graphics graphics){
        graphics.setColor(grass);
        graphics.fillRect(mapX, mapY, width*tileSize, height*tileSize);

        Text.drawString(graphics, handler.getWorld().getName(),mapX,mapY-8,false,Color.white, Assets.font20);
        for(int x = 0; x < width; x ++){
            for(int y = 0; y < height; y++){
                id = worldTiles[x][y];
                switch(id){
                    case 0:
                        break;
                    case 1:
                        graphics.setColor(dirt);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                    case 2:
                        graphics.setColor(cliff);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                    case 3:
                        graphics.setColor(sand);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                    case 4:
                        graphics.setColor(water);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                    case 6:
                        graphics.setColor(deepWater);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                    case 7: 
                        graphics.setColor(plank);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                    case 8:
                        graphics.setColor(rock);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                    case 9:
                        graphics.setColor(portal);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                    default:
                        graphics.setColor(water);
                        graphics.fillRect(mapX + (x * tileSize), mapY+(y * tileSize), tileSize, tileSize);
                        break;
                }
            }
        }
        graphics.setColor(Color.red);
        graphics.fillRect(mapX + (int)Math.floor((handler.getWorld().getPlayer().getX()/Tile.TILE_WIDTH+1)* tileSize)-5,
                          mapY + (int)Math.floor((handler.getWorld().getPlayer().getY()/Tile.TILE_HEIGHT+1)* tileSize)-5
                         , 10, 10);
    }
}
