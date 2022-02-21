/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elan Fernandez
 */
public class UIImageButton extends UIObject {

    private BufferedImage image;
    private BufferedImage[] images;
    private ClickListener click;
    
    public UIImageButton(float x, float y, int width, int height, BufferedImage image, ClickListener click) {
        super(x, y, width, height);
        this.image = image;
        this.click = click;
    }
    
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener click) {
        super(x, y, width, height);
        this.images = images;
        this.click = click;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics graphics) {
        if(!active)
            return;
        
        if(images != null){
            if(hovering)
            graphics.drawImage(images[1], (int)x, (int)y, width, height, null);
        else
            graphics.drawImage(images[0], (int)x, (int)y, width, height, null);
        }
        
        if(image != null)
            graphics.drawImage(image, (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
        click.onClick();
    }
    
}
