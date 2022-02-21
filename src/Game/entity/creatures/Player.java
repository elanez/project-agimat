package Game.entity.creatures;

import Game.Handler;
import Game.dialog.InteractDialog;
import Game.entity.Entity;
import Game.graphics.Animation;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.inventory.Inventory;
import Game.items.Item;
import Game.quest.Quest;
import Game.tiles.Tile;
import Game.util.Timer;
import Game.util.Utils;
import Game.Projectile.Projectile;
import Game.Projectile.ProjectileController;
import Game.audio.sfxPlayer;
import Game.datahelper.DBconnect;
import Game.items.interfaces.Potion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;

public class Player extends Creature{
    
    private final Quest quest;
    
    private Animation attack;
    private final Animation move;
    private final Animation roll;
    private Animation spin;
    private Animation rangedAnimation;
    private final Animation diwataAnim;
    
    private int face, staminaRegen,count;
    private boolean pots = true;
    private float healthPercent, stamina, fullStamina;
    private boolean bleeding = false;
    private boolean diwataActive = false;
    private boolean slowed = false;
    
    private int playerMoney,playerScore,playerAgimat = 0;

    private int healthCount,manaCount,debuffCount;
    private int element;
    private int playerWeapon;

    private final sfxPlayer soundEffects;
    
    private final Timer regenTimer, spinTimer, rollTimer, atkTimer, rangedTimer,slowTimer, bleedTimer,diwataHealTimer;
    
    private final Color color1;
    private final Color color2;
    
    private final int uiX, uiY, uiWidth, uiHeight;
    private final int cdX, cdY, cdWidth, cdHeight;
    
    private Rectangle test;
    private final ProjectileController projectileController;
    private Inventory inventory;
    private final InteractDialog dialog;
    private final int infobarWidth, infobarHeight;
    private final String loginID;
    private int healer = 0,healerActive = 0;
    
    private boolean isLoad;

    public Player(Handler handler, float x, float y, String loginID, boolean isLoad) {
        super(handler, x, y, (Tile.TILE_WIDTH)*2, (Tile.TILE_WIDTH)*2,0);
        this.loginID = loginID;
        id = 0;
        playerScore = 0;
        projectileController = new ProjectileController(handler);
        
        bounds.x = (width*3)/8;
        bounds.y = height/2;
        bounds.width = width/4;
        bounds.height = height/4;
        
        quest = new Quest(handler);
        soundEffects = new sfxPlayer();
        attack = new Animation(100,Assets.attack_left,Assets.attack_right,Assets.attack_up,Assets.attack_down);
        move = new Animation(200,Assets.player_left,Assets.player_right,Assets.player_up,Assets.player_down);
        roll = new Animation(100,Assets.roll_left,Assets.roll_right,Assets.roll_up,Assets.roll_down);
        count = 0;
        
        inventory = new Inventory(handler);
        dialog = new InteractDialog(handler);
        regenTimer = new Timer(1000);
        spinTimer = new Timer(1000);
        rollTimer = new Timer(1000);
        atkTimer = new Timer(1000);
        rangedTimer = new Timer(1500);
        slowTimer = new Timer(10000);
        bleedTimer = new Timer(20000);
        diwataHealTimer = new Timer(1000);
        
        diwataAnim = new Animation(80, Assets.diwataFlying);
        
        playerWeapon = 0;
        color1 = new Color (24, 89, 163);
        color2 = new Color (32, 54, 51);
        
        face=3;
        damage = 25;
        fullStamina = 100;
        stamina = fullStamina;
        staminaRegen = 1;
        playerMoney = 250;
        count = 0;
        
        infobarWidth = (handler.getWidth()*2)/5;
        infobarHeight = (Tile.TILE_HEIGHT/3);
        //loadPlayer("src\\res\\invent\\playerStats.txt");
        if(isLoad){
            loadPlayer();
        }
        loadWeaponElement();
        loadAttackAnim();
//        Item.setHandler(handler);
        uiWidth = 500;
        uiHeight=150;
        uiX = handler.getWidth()/2 - uiWidth/2;
        uiY = handler.getHeight() - uiHeight;
        
        cdWidth = 85;
        cdHeight = 80;
        cdX = (int)(uiX + cdWidth/1.65);
        cdY = uiY + cdHeight/2;
    }
    
    private void loadPlayer(){
       /*String file =  Utils.loadFile(path);
         Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.contains(":")){
                String [] token = line.split(":");
                setHealth(Integer.parseInt(token[0]));
                setStamina(Integer.parseInt(token[1]));
                setDamage(Integer.parseInt(token[2]));
                setPlayerMoney(Integer.parseInt(token[3]));
              
           }else if(line.contains(",")){
                String[] token = line.split(",");
                if(token.length >= 2){
                    System.out.print((Integer.parseInt(token[0])));
                    System.out.println((Integer.parseInt(token[1])));  
                }
            }
        }
        scanner.close();
       */
       try{
           DBconnect connect = new DBconnect();
           ResultSet rs = connect.getdata("SELECT * FROM playerinfo WHERE ID = " + loginID);
           System.out.println("Player: " + loginID);
           if(rs.next()){
               setPlayerMoney(Integer.parseInt(rs.getString("Gold"))); 
 //             inventory.equipped(Integer.parseInt(rs.getString("Equipped")));
               setHealth(Integer.parseInt(rs.getString("Health")));
               setStamina(Integer.parseInt(rs.getString("Stamina")));
               quest.setCurrentQuest(Integer.parseInt(rs.getString("Questlog")));
               setDamage(Integer.parseInt(rs.getString("Damage")));
               setPlayerAgimat(Integer.parseInt(rs.getString("Count")));
               setPlayerScore(Integer.parseInt(rs.getString("Score")));
               String[] arr1 = rs.getString("Equipped").split(",");
               String[] arr = rs.getString("Inventory").split(",");
               
               inventory.setEquipID(Integer.parseInt(arr1[0]));
               setCount(Integer.parseInt(arr1[1]));
               
               for (String iterate : arr){
                   String[] obj = iterate.split("-");
                   inventory.loadItem(Integer.parseInt(obj[0]), Integer.parseInt(obj[1]));
               }
           }
       }catch(Exception ex){
           
       }
    }
    public void checkDiwataHeal(){
        if(diwataHealTimer.start() && diwataActive){
            if((health+5) >= 100)
                health = 100;
            else{
                health+=5;
            }
            diwataHealTimer.restart();
        }
       
    }

    @Override
    public void update() {
        
        move.update();
        attack.singleUpdate();
        roll.singleUpdate();
        spin.singleUpdate();
        playerWeapon = handler.getWorld().getPlayer().getInventory().getEquipID();
        if(handler.getKeyManager().switchBtn && !spin.isAnimating() && !attack.isAnimating()){
            loadWeaponElement();
        }
        
        checkSpin();
        getInput();
        move();
        tileEvent();
        quest.update();
        
        handler.getCamera().centerOnEntity(this);
        
          if(healer == 1){
            checkDiwataHeal();
            diwataAnim.update();
        }
        
        checkSpeed();
        checkRoll();
        checkAttack();
        rangedAttack();
        regen();
        
        projectileController.update();
        inventory.update();
        dialog.update();
        heal();  
    }
    public void loadAttackAnim(){
        switch(inventory.getEquipID()){
            case 0:
                attack = new Animation(100, Assets.attack_left, Assets.attack_right, Assets.attack_up, Assets.attack_down);
                break;
            case 1:
                attack = new Animation(100, Assets.silverAttack_left, Assets.silverAttack_right, Assets.silverAttack_up, Assets.silverAttack_down);
                break;
            case 2:
                attack = new Animation(100, Assets.goldAttack_left, Assets.goldAttack_right, Assets.goldAttack_up, Assets.goldAttack_down);
                break;
        }
    }
    public void loadWeaponElement(){
            switch(count){
                case 0:
                    switch(inventory.getEquipID()){
                        case 0:
                            spin = new Animation(50,Assets.waterSpin);
                            break;
                        case 1:
                            spin = new Animation(50,Assets.silverWaterSpin);
                            break;
                        case 2:
                            spin = new Animation(50,Assets.goldWaterSpin);
                            break;
                    }
                    rangedAnimation = new Animation(50, Assets.waterProjectileLeft, Assets.waterProjectileRight, Assets.waterProjectileUp, Assets.waterProjectileDown);
                    break;
                case 1:
                    switch(inventory.getEquipID()){
                        case 0:
                            spin = new Animation(50,Assets.fireSpin);
                            break;
                        case 1:
                            spin = new Animation(50,Assets.silverFireSpin);
                            break;
                        case 2:
                            spin = new Animation(50,Assets.goldFireSpin);
                            break;
                    }
                    rangedAnimation = new Animation(50, Assets.fireProjectileRight, Assets.fireProjectileLeft, Assets.fireProjectileUp, Assets.fireProjectileDown);
                    break;
                case 2:
                    switch(inventory.getEquipID()){
                        case 0:
                            spin = new Animation(50,Assets.windSpin);
                            break;
                        case 1:
                            spin = new Animation(50,Assets.silverWindSpin);
                            break;
                        case 2:
                            spin = new Animation(50,Assets.goldWindSpin);
                            break;
                    }
                    rangedAnimation = new Animation(50, Assets.windProjectile);
                    break;
                case 3:
                    switch(inventory.getEquipID()){
                        case 0:
                            spin = new Animation(50,Assets.lightningSpin);
                            break;
                        case 1:
                            spin = new Animation(50,Assets.silverLightningSpin);
                            break;
                        case 2:
                            spin = new Animation(50,Assets.goldLightningSpin);
                            break;
                    }
                    rangedAnimation = new Animation(50, Assets.lightningProjectile);
                    break;
//                default:
//                    spin = new Animation(80,Assets.waterSpin);
//                    rangedAnimation = new Animation(50, Assets.waterProjectileLeft, Assets.waterProjectileRight, Assets.waterProjectileUp, Assets.waterProjectileDown);
//                    break;
            }
        
            
            spin.setIndex(0);
        
    }
    
    public void checkSpeed(){
        if(slowed){
            setSpeed(2);
            System.out.println("slowed");
            
            slowTimer.restart();
            slowed = false;
        }
        else if(slowTimer.start())
            setSpeed(speed);
    }
    
    @Override
    public void checkDebuff(){
        
        if(bleeding){
            
            bleedTimer.restart();
            bleeding = false;
            System.out.println("bleeding");
        }
        if(!bleedTimer.start())
                health -= 0.03;
//        else if(bleedTimer.start())
//            bleeding = false;
    }

    public void rangedAttack(){
        if(rangedTimer.start()){
            if(notMoveable()){
                return;
            }
            if(handler.getKeyManager().rangedBtn && stamina >= 15){
                rangedTimer.restart();
                attack.startAnimation();
                projectileController.addProjectile(new Projectile(handler, (int)x, (int)y, face, rangedAnimation));
                stamina -= 15;
            }
        }
    }
    
    public void heal(){
        healthCount = 0;
        manaCount = 0;
        debuffCount = 0;
        for(int i = 0 ; i < inventory.getInventoryItems().size();i++){
                     switch (inventory.getInventoryItems().get(i).getID()) {
                     case 3:
                         healthCount = inventory.getInventoryItems().get(i).getCount();
                         break;
                     case 4:
                         manaCount = inventory.getInventoryItems().get(i).getCount();
                         break;
                     case 5:
                         debuffCount = inventory.getInventoryItems().get(i).getCount();
                         break;
                     default:
                         break;
                 }
        }

        if(healthCount > 0 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_1) && getHealth() < 100){  
            ((Potion)(Item.redPotion)).buff();
            inventory.remove(Item.redPotion, 1);

             
        }

        if(manaCount > 0 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_2) && getStamina() < 100){
           ((Potion)(Item.bluePotion)).buff();
           inventory.remove(Item.bluePotion,1);
               
       }
       if(debuffCount > 0 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)){
           ((Potion)(Item.greenPotion)).buff();
            inventory.remove(Item.greenPotion, 1);
            
               
        }
       if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R) && !spin.isAnimating()  && !attack.isAnimating()){
             switch (playerAgimat) {
                 case 0:
                     count = 0;
                     break;
                 case 1:
                     count++;
                     if(count == 2){
                         count = 0;
                     }         break;
                 case 2:
                     count++;
                     if(count == 3){
                         count = 0;
                     }break;
                 case 3:
                     count++;
                     if(count == 4){
                         count = 0;
                     }break;
                 default:
                     break;
             }
        }
    }
    
    public void regen(){
        if(regenTimer.start()){
            if(stamina < fullStamina){
                stamina+=staminaRegen;
                regenTimer.restart();
            }
            
        }
    }
    
    @Override
    public void infoBar(Graphics graphics){
        healthPercent = getHealthPercentage();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, infobarWidth, infobarHeight);
        graphics.fillRect(0, infobarHeight, infobarWidth, infobarHeight);
        if(healthPercent > 50)
            graphics.setColor(Color.GREEN);
        else if(healthPercent > 25 && healthPercent <= 50)
            graphics.setColor(Color.ORANGE);
        else
            graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, (int)(infobarWidth* (healthPercent/100)), infobarHeight);
        
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0,infobarHeight,(int)(infobarWidth * (stamina/fullStamina)), infobarHeight);
        Text.drawString(graphics, Integer.toString((int) healthPercent),infobarWidth+25, infobarHeight, false, Color.GREEN, Assets.font28);
        Text.drawString(graphics, "%",infobarWidth+80, infobarHeight, false, Color.GREEN, Assets.font28);
        Text.drawString(graphics,"%",infobarWidth+80, infobarHeight*2, false, Color.BLUE, Assets.font28);
        Text.drawString(graphics, Integer.toString((int) getStamina()),infobarWidth+25, infobarHeight*2, false, Color.BLUE, Assets.font28);
        
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0,infobarHeight,(int)(infobarWidth * (stamina/fullStamina)), infobarHeight);
        Text.drawString(graphics, Integer.toString(playerMoney),0, infobarHeight*3, false, Color.YELLOW, Assets.font28);
        
        graphics.setColor(color2);
        graphics.fillRect(cdX, cdY, cdWidth, cdHeight);
        
        graphics.setColor(color1);
        int atkCoolDown = (int)atkTimer.getTime()/5;
        if(atkCoolDown > cdHeight)
            atkCoolDown = cdHeight;
        graphics.fillRect(cdX,cdY,cdWidth, atkCoolDown);
        
        if(atkCoolDown <= 1)
            graphics.fillRect(cdX, cdY, cdWidth, cdHeight);
        
        graphics.setColor(color2);
        graphics.fillRect((int)(cdX + cdWidth *1.25), cdY, cdWidth, cdHeight);
        
        graphics.setColor(color1);
        int rollCoolDown = (int)rollTimer.getTime()/10;
        if(rollCoolDown > cdHeight)
            rollCoolDown = cdHeight;
        graphics.fillRect((int)(cdX + cdWidth *1.25),cdY,cdWidth, rollCoolDown);
        
        if(rollCoolDown <= 1)
            graphics.fillRect((int)(cdX + cdWidth *1.25), cdY, cdWidth, cdHeight);
        if(stamina < 10){
            graphics.setColor(color2);
            graphics.fillRect((int)(cdX + cdWidth *1.25), cdY, cdWidth, cdHeight);
        }
            
        graphics.setColor(color2);
        graphics.fillRect((int)(cdX + cdWidth *2.50), cdY, cdWidth, cdHeight);
        
        graphics.setColor(color1);
        int rangedCoolDown = (int)rangedTimer.getTime()/20;
        if(rangedCoolDown > cdHeight)
            rangedCoolDown = cdHeight;
        graphics.fillRect((int)(cdX + cdWidth *2.50),cdY,cdWidth, rangedCoolDown);
        
        if(rangedCoolDown <= 5)
            graphics.fillRect((int)(cdX + cdWidth *2.50), cdY, cdWidth, cdHeight);
        if(stamina < 15){
            graphics.setColor(color2);
            graphics.fillRect((int)(cdX + cdWidth *2.50), cdY, cdWidth, cdHeight);
        }
        
        graphics.setColor(color2);
        graphics.fillRect((int)(cdX + cdWidth *3.65), cdY, cdWidth, cdHeight);
        
        graphics.setColor(color1);
        int spinCoolDown = (int)spinTimer.getTime()/10;
        if(spinCoolDown > cdHeight)
            spinCoolDown = cdHeight;
        graphics.fillRect((int)(cdX + cdWidth *3.65),cdY,cdWidth, spinCoolDown);
        
        if(spinCoolDown <= 1)
            graphics.fillRect((int)(cdX + cdWidth *3.65), cdY, cdWidth, cdHeight);
        if(stamina < 10){
            graphics.setColor(color2);
            graphics.fillRect((int)(cdX + cdWidth *3.65), cdY, cdWidth, cdHeight);
        }
        
//        graphics.
        graphics.drawImage(Assets.coolDownUI, uiX, uiY, uiWidth, uiHeight, null);
    }
    
     public void checkRoll(){
        if(rollTimer.start()){
            if(handler.getKeyManager().shftBtn && stamina >= 10){
                roll.startAnimation();
                stamina -= 10;
                rollTimer.restart();
            } 
        }
    }
    
    public void checkAttack(){  
        
        if(atkTimer.start()){
            
            if(inventory.isActive() || dialog.isActive())
                return;

            Rectangle attackRange = new Rectangle();

            if(handler.getKeyManager().atkBtn){
                if(!roll.isAnimating()){
                    atkTimer.restart();
                    attack.startAnimation();
                    attackRange = Utils.generateHitbox(this, 50, face);
                }
                soundEffects.run();
                test = attackRange;

                for(Entity e : handler.getWorld().getEntityManager().getMobs()){
                    if(e instanceof Normie)
                        continue;
                    if(e.getCollisionBounds(0,0).intersects(attackRange)){
                        e.hurt(damage);
                        if(e instanceof Creature)
                            ((Creature)e).damaged(damage);
                    }
                }
            }
        }
    }
    
    public void checkSpin(){  
        
        if(spinTimer.start()){
            
            if(notMoveable())
                return;

            Rectangle cb = getCollisionBounds(0,0);
            Rectangle ar = new Rectangle();
            
            if(handler.getKeyManager().spinBtn && stamina>=5){
                if(!roll.isAnimating()||!attack.isAnimating()){
                    spinTimer.restart();
                    spin.startAnimation();
                    int arSize = 50;
                    ar.width = arSize*3;
                    ar.height = arSize*3;

                    ar.x = cb.x - arSize;
                    ar.y = cb.y - arSize;

                    stamina -= 5;
                }

                test = ar;

                for (Entity e : handler.getWorld().getEntityManager().getMobs()) {
                    if(!(e instanceof Creature) || e instanceof Normie || e.equals(this))
                        continue;
                    if(e.getCollisionBounds(0,0).intersects(ar)){
                        switch(count){
                            case 0:
                                ((Creature)e).setSlowed(true);
                                break;
                            case 1:
                                ((Creature)e).setBurning(true);
                                break;
                            case 2:
                                ((Creature)e).spinKnockback(e);
                                break;
                            case 3:
                                ((Creature)e).setStunned(true);
                                break;
                        }
                        e.hurt(damage*2);
                        if(e instanceof Creature)
                            ((Creature)e).damaged(damage*2);
                    }
                }
            }
        }
    }
    

    @Override
    public void die(){
        dialog.setDialog(" ", "You died!");
        changeScore(-1000);
        handler.getWorld().getEntityManager().respawnPlayer();
    }
    
    @Override
    public void getInput(){
        xMove = 0;
        yMove = 0;
        
        if(notMoveable())
            return;
        
        if(handler.getKeyManager().up)
            yMove = -speed;
         if(handler.getKeyManager().up&& handler.getKeyManager().left){
             yMove = -speed/2;
             xMove = -speed/2;
         }
         if(handler.getKeyManager().up&& handler.getKeyManager().rigth){
             yMove = -speed/2;
             xMove = speed/2;
         }
            
        if(handler.getKeyManager().down)
            yMove = speed;
         if(handler.getKeyManager().down&& handler.getKeyManager().left){
             yMove = speed/2;
             xMove = -speed/2;
         }
         if(handler.getKeyManager().down&& handler.getKeyManager().rigth){
             yMove = speed/2;
             xMove = speed/2;
         }
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().rigth)
            xMove = speed;
        if(roll.isAnimating()){
            switch (face) {
                case 0:
                    xMove = -speed*2;
                    break;
                case 1:
                    xMove = speed*2;
                    break;
                case 2:
                    yMove = -speed*2;
                    break;
                case 3:
                    yMove = speed*2;
                    break;
                default:
                    break;
            }
        }      
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
        projectileController.render(graphics);
        renderText(graphics);
         if(healer == 1)
            graphics.drawImage(diwataAnim.getCurrentFrame(), (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()), null);
//graphics.setColor(Color.YELLOW);
//        if(test != null)
//            graphics.fillRect((int)(test.x - handler.getCamera().getxOffset()), (int)(test.y -  handler.getCamera().getyOffset()), test.width, test.height);
//        graphics.fillRect((int)(x + bounds.x - handler.getCamera().getxOffset()), (int)(y +bounds.y -  handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }
    
    public void postRender(Graphics graphics){
        infoBar(graphics);
        quest.render(graphics);
        
        if(pots){
            potsRender(graphics);
        }
        
        inventory.render(graphics);
        dialog.render(graphics);
    }
    
    public void potsRender(Graphics graphics){
        //graphics.drawImage(Assets.equipScreen,handler.getWidth() - 200, 64,200,400, null);
         graphics.drawImage(Assets.bRedPotion,handler.getWidth() - 50, 128,50,50, null);
         Text.drawString(graphics, Integer.toString(healthCount), handler.getWidth() - 50, 128, true, Color.white, Assets.font16);
          graphics.drawImage(Assets.bBluePotion,handler.getWidth() - 50, 178,50,50, null);
         Text.drawString(graphics, Integer.toString(manaCount), handler.getWidth() - 50, 178, true, Color.white, Assets.font16);
         graphics.drawImage(Assets.bGreenPotion,handler.getWidth() - 50, 228,50,50, null);
         Text.drawString(graphics, Integer.toString(debuffCount), handler.getWidth() - 50, 228, true, Color.white, Assets.font16);
        switch (count) {
            case 0:
                graphics.drawImage(Assets.wAgimat,handler.getWidth() - 50, 328,50,50, null);
                break;
            case 1:
                graphics.drawImage(Assets.fAgimat,handler.getWidth() - 50, 328,50,50, null);
                break;
            case 2:
                graphics.drawImage(Assets.windAgimat,handler.getWidth() - 50, 328,50,50, null);
                break;
            case 3:
                graphics.drawImage(Assets.tAgimat,handler.getWidth() - 50, 328,50,50, null);
                break;
            default:
                break;
        }
        Text.drawString(graphics, "R", handler.getWidth() - 50, 328, active, Color.white, Assets.font16);
         graphics.drawImage(Item.getItem(inventory.getEquipID()).getImage(),handler.getWidth() - 50, 378,50,50, null);
    }
    
    @Override
    public BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){//left
            face = 0;
        }else if(xMove > 0){//right
            face = 1;
        }else if(yMove < 0){//up
            face = 2;
        }else if(yMove > 0){//down
            face = 3;
        }else{//idle
            if(attack.isAnimating())
                return attack.getCurrentFrame(face);
            else if(spin.isAnimating())
                return spin.getCurrentFrame(face);
            else{
                switch (face) {
                 case 0:
                    return Assets.playerFace_left;
                case 1:
                    return Assets.playerFace_right;
                case 2:
                    return Assets.playerFace_up;
                default:
                    return Assets.playerFace_down;
                }
            }
        }
        if(attack.isAnimating()){
            return attack.getCurrentFrame(face);
        }else if(roll.isAnimating()){
            return roll.getCurrentFrame(face);
        }else if (spin.isAnimating()){
            return spin.getCurrentFrame(face);
        }else
            return move.getCurrentFrame(face);
    }
    
    public void changeScore(int amount){
        if(playerScore+amount <= 0)
            playerScore = 0;
        else
            playerScore += amount;
        
    }
    
    public void addGold(int amount){
        playerMoney  += amount;
    }
    
    public boolean notMoveable(){
        return(inventory.isActive() || dialog.isActive() || handler.getWorld().getShop().isActive() ||
                handler.getWorld().getSellShop().isActive() || quest.isActive());
    }
    
    public void tileEvent(){
        handler.getWorld().getTile((int)((x + bounds.x)/Tile.TILE_WIDTH), (int)((y +bounds.y) /Tile.TILE_HEIGHT)).event();
    }
    
    
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getFace() {
        return face;
    }

    public InteractDialog getDialog() {
        return dialog;
    }

    public Quest getQuest() {
        return quest;
    }
    
    
    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }
    
     public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPlayerAgimat() {
        return playerAgimat;
    }

    public void setPlayerAgimat(int playerAgimat) {
        this.playerAgimat = playerAgimat;
    }
    
     public boolean isPots() {
        return pots;
    }

    public void setPots(boolean pots) {
        this.pots = pots;
    }
    
    public void setSlow(boolean slowed){
        this.slowed = slowed;
    }
    
    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    
    public float getStamina() {
        return stamina;
    }

    public void setStamina(float stamina) {
        this.stamina = stamina;
    }

    public String getLoginID() {
        return loginID;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
     public int getHealer() {
        return healer;
    }

    public void setHealer(int healer) {
        this.healer = healer;
    }

    public int getHealerActive() {
        return healerActive;
    }

    public void setHealerActive(int healerActive) {
        this.healerActive = healerActive;
    }

    public void setDiwataActive(boolean diwataActive) {
        this.diwataActive = diwataActive;
    }

    
}
