/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public class Text {
    
    public static void drawString(Graphics graphics, String text, int xPos, int yPos, boolean center, Color c, Font font){
        graphics.setColor(c);
        graphics.setFont(font);
        int x = xPos;
        int y = yPos;
        if(center){
            FontMetrics fm = graphics.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        graphics.drawString(text, x, y);
    }
    
}
