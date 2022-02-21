/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.util;

import Game.entity.Entity;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Elan Fernandez
 */
public class Utils {
    
    public static String loadFile(String path){
        StringBuilder build = new StringBuilder();
        
        try{
            BufferedReader read = new BufferedReader(new FileReader(path));
            String line;
            while((line = read.readLine()) != null){
            build.append(line + "\n");
        }
            read.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return build.toString();
    }
    
    public static Rectangle generateHitbox(Entity e, int attackRangeSize, int face){
        Rectangle cb = e.getCollisionBounds(0, 0);
        Rectangle hitbox = new Rectangle();
        hitbox.width = hitbox.height = attackRangeSize;
        switch (face) {
            case 0://left
                hitbox.x = cb.x - attackRangeSize;
                hitbox.y = cb.y + cb.height/2 - attackRangeSize/2;
                break;
            case 1://right
                hitbox.x = cb.x + cb.width;
                hitbox.y = cb.y + cb.height/2 - attackRangeSize/2;
                break;
            case 2://up
                hitbox.x = cb.x + cb.width/2 - attackRangeSize/2;
                hitbox.y = cb.y - attackRangeSize;
                break;
            case 3://down
                hitbox.x = cb.x + cb.width/2 - attackRangeSize/2;
                hitbox.y = cb.y + cb.height;
                break;
        }
        
        return hitbox;
    }
    
}
