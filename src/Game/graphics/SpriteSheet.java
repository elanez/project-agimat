/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author Elan Fernandez
 */
public class SpriteSheet {
    private BufferedImage sheet;
    
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int x, int y, int w, int h){
        return sheet.getSubimage(x, y, w, h);
    }
}
