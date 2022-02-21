/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.items;

import Game.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Elan Fernandez
 */
public class ItemManager {
	
	private Handler handler;
	private final ArrayList<Item> items;
	
	public ItemManager(Handler handler){
            this.handler = handler;
            items = new ArrayList<>();
	}
	
	public void update(){
            Iterator<Item> it = items.iterator();
            while(it.hasNext()){
            Item i = it.next();
            i.update();
            if(i.isPickedUp())
                it.remove();
            }
	}
	
	public void render(Graphics graphics){
            for(Item i : items)
		i.render(graphics);
	}
	
	public void addItem(Item i){
            i.setHandler(handler);
            items.add(i);
	}
        
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}

