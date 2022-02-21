package Game.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
    
    private static final int WIDTH = 16, HEIGHT = 16;
    private static final int PLAYERWIDTH = 100, PLAYERHEIGHT = 100;
    private static final int ENEMYWIDTH = 64, ENEMYHEIGHT = 64;
    
    public static Font font35, font28, font20, font16,font18;
    
    //tiles
    public static BufferedImage grass, dirt, cliff, sand, water, plant, deepWater, plank, rock;
    public static BufferedImage torch, dust, kubo1, kubo2, kubo3, ship;
    public static BufferedImage coconut,banana, tree;
    public static BufferedImage[] waveLeft, waveRight, waveDown, waveUp;
    public static BufferedImage[] homePortal, portal1, portal2, portal3;
     
    //entities
    public static BufferedImage playerFace_down, playerFace_up, playerFace_left, playerFace_right;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] roll_left, roll_right, roll_up, roll_down;
    
    public static BufferedImage[] attack_left, attack_right, attack_up, attack_down;
    public static BufferedImage[] goldAttack_left, goldAttack_right, goldAttack_up, goldAttack_down;
    public static BufferedImage[] silverAttack_left, silverAttack_right, silverAttack_up, silverAttack_down;
    
    
    public static BufferedImage[] waterSpin, fireSpin, windSpin, lightningSpin;
    public static BufferedImage[] goldWaterSpin, goldFireSpin, goldWindSpin, goldLightningSpin;
    public static BufferedImage[] silverWaterSpin, silverFireSpin, silverWindSpin, silverLightningSpin,diwataFlying;
    
    
    public static BufferedImage[] fireProjectileLeft, fireProjectileRight, fireProjectileUp, fireProjectileDown;
    public static BufferedImage[] waterProjectileLeft, waterProjectileRight, waterProjectileUp, waterProjectileDown;
    public static BufferedImage[] windProjectile, lightningProjectile;
    
    public static BufferedImage[] spaniardLeft, spaniardRight, spaniardUp, spaniardDown;
    public static BufferedImage[] spaniardLeftAtk, spaniardRightAtk, spaniardUpAtk, spaniardDownAtk;
    
    public static BufferedImage[] enemy_left, enemy_right;
    public static BufferedImage[] enemyAttack_left, enemyAttack_right;
    public static BufferedImage[] enemySkillLeft, enemySkillRight, enemySkillUp, enemySkillDown;
    
    public static BufferedImage pigFace_left, pigFace_right;
    public static BufferedImage[] pigWalk_left, pigWalk_right;
    public static BufferedImage[] pigAttack_left, pigAttack_right;
    
    public static BufferedImage aswangFace_left, aswangFace_right;
    public static BufferedImage [] aswangWalk_left, aswangWalk_right;
    public static BufferedImage [] aswangAttack_left, aswangAttack_right;
    
    public static BufferedImage[] bossLeft, bossRight;
    public static BufferedImage[] bossAttackLeft, bossAttackRight;
    
    public static BufferedImage shopNPC;
    public static BufferedImage[] npc1_left, npc1_right, npc1_down, npc1_up; 
    public static BufferedImage[] npc2_left, npc2_right, npc2_down, npc2_up; 
    public static BufferedImage[] npc2_leftAtk, npc2_rightAtk, npc2_downAtk, npc2_upAtk; 
   
    //menu
    public static BufferedImage[] startBtn; 
    public static BufferedImage[] loadBtn; 
    public static BufferedImage[] logoutBtn; 
    public static BufferedImage[] playBtn; 
    public static BufferedImage[] tutorialsBtn; 
    public static BufferedImage[] backBtn; 
    public static BufferedImage[] resumeBtn;
    public static BufferedImage[] saveBtn;
    public static BufferedImage[] exitBtn;
    public static BufferedImage[] acceptBtn;
    public static BufferedImage[] cancelBtn;
    public static BufferedImage[] editBtn;
    public static BufferedImage[] leaderBoardBtn;
    public static BufferedImage loginBg;
    public static BufferedImage signupBg;
    public static BufferedImage cutSceneBg;
    public static BufferedImage menuStateBg;
    public static BufferedImage loginBtn;
    public static BufferedImage coolDownUI;
    public static BufferedImage btnW, btnA, btnS, btnD, btnK, btnShift, btnL, btnJ;
    public static BufferedImage lore;
    
    //shop items inventory
    public static BufferedImage inventoryScreen;
    public static BufferedImage closeBtn;
    public static BufferedImage ShopInventory;
    public static BufferedImage ShopInventoryP;
    public static BufferedImage equipScreen;
    public static BufferedImage left, right;
    public static BufferedImage iconDmg,iconHealth, iconGold, iconStamina, iconSpeed, iconUser,iconHealth2;
    public static BufferedImage goldSword,plus,minus,silverSword,woodSword;
    public static BufferedImage bRedPotion,bBluePotion,mRedPotion,mBluePotion,bGreenPotion;;
    public static BufferedImage kalakalButton;
    public static BufferedImage[] buyBtn;
    public static BufferedImage[] sellBtn;
    public static BufferedImage[] salamankaButton;
    public static BufferedImage[] sandataButton;
    public static BufferedImage[] equipButton;
    public static BufferedImage fAgimat,wAgimat,windAgimat,tAgimat,baboy,ulo,dila,diwata;
    
    //quest
    public static BufferedImage questAswang, questMananangal, questPig, questBossMananagal, questDayuhan;
    
    //infp
    public static BufferedImage[] stage1, stage2, stage3;
        
    public static void init(){
        font35 = FontLoader.loadFont("src/res/fonts/slkscr.ttf", 35);
        font28 = FontLoader.loadFont("src/res/fonts/slkscr.ttf", 28);
        font20 = FontLoader.loadFont("src/res/fonts/slkscr.ttf", 20);
        font16 = FontLoader.loadFont("src/res/fonts/slkscr.ttf",16);
        font18 = FontLoader.loadFont("src/res/fonts/slkscr.ttf", 18);
        
        SpriteSheet diwataSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/diwataSprite.png"));
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/images/SpriteSheet.png"));
        SpriteSheet structureSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/nightStructures.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/playerWalkingSpriteSheet.png"));
        SpriteSheet uiSheet = new SpriteSheet(ImageLoader.loadImage("/images/button.png"));
        SpriteSheet manananggalSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/manananggal.png"));
        SpriteSheet rollSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/rollSpriteSheet.png"));
        
        SpriteSheet attackSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/attackSpriteBig.png"));
        SpriteSheet goldAttackSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/attackGold.png"));
        SpriteSheet silverAttackSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/attackSilver.png"));
        
        SpriteSheet spinSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/powerSpinSprite.png"));
        SpriteSheet goldSpinSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/GOLDSPIN.png"));
        SpriteSheet silverSpinSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/silverSpin.png"));
        SpriteSheet waterProjectileSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/waterProjectile.png"));
        SpriteSheet windLightningProjectileSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/windLightningProjectile.png"));
        
        SpriteSheet treeSheet =  new SpriteSheet(ImageLoader.loadImage("/sprites/nightTreeSheet.png"));
        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/tiles/nightTileSheet.png"));
        SpriteSheet portalSheet = new SpriteSheet(ImageLoader.loadImage("/tiles/portals.png"));
        SpriteSheet projectileSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/projectileSheet.png"));
        SpriteSheet pigSheet= new SpriteSheet(ImageLoader.loadImage("/sprites/pig.png"));
        SpriteSheet aswangSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/aswangSpriteSheet.png"));
        SpriteSheet bossSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/boss.png"));
        SpriteSheet enemySkillSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/blood.png"));
        SpriteSheet spaniardSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/spaniardOldBigSpriteSheet.png"));
        SpriteSheet spaniardSheetAtk = new SpriteSheet(ImageLoader.loadImage("/sprites/spaniardAttackSpriteSheet.png"));
        
        SpriteSheet npcSheet1 = new SpriteSheet(ImageLoader.loadImage("/sprites/normie1.png"));
        SpriteSheet npcSheet2 = new SpriteSheet(ImageLoader.loadImage("/sprites/normie2.png"));
        SpriteSheet npcSheet2Atk = new SpriteSheet(ImageLoader.loadImage("/sprites/normiattack.png"));
        shopNPC =  ImageLoader.loadImage("/sprites/shopnpc.png");
        SpriteSheet equipS = new SpriteSheet(ImageLoader.loadImage("/images/equipScreen.png"));
        SpriteSheet equip = new SpriteSheet(ImageLoader.loadImage("/images/equip.png"));
        SpriteSheet buy = new SpriteSheet(ImageLoader.loadImage("/shop/buy.png"));
        SpriteSheet sell = new SpriteSheet(ImageLoader.loadImage("/shop/sell.png"));
        SpriteSheet salamanka = new SpriteSheet(ImageLoader.loadImage("/shop/salamanka.png"));
        SpriteSheet sandata = new SpriteSheet(ImageLoader.loadImage("/shop/sandata.png"));
        
        btnW = ImageLoader.loadImage("/shop/btnW.png");
        btnA = ImageLoader.loadImage("/shop/btnA.png");
        btnS = ImageLoader.loadImage("/shop/btnS.png");
        btnD = ImageLoader.loadImage("/shop/btnD.png");
        btnK = ImageLoader.loadImage("/shop/btnK.png");
        btnL = ImageLoader.loadImage("/shop/btnL.png");
        btnJ = ImageLoader.loadImage("/shop/btnJ.png");
        btnShift = ImageLoader.loadImage("/shop/btnShift.png");
        
        coolDownUI = ImageLoader.loadImage("/images/cdUI.png");
        kalakalButton = ImageLoader.loadImage("/shop/kalakal.png");
        ShopInventory = ImageLoader.loadImage("/shop/bgGame.png");
        ShopInventory = ImageLoader.loadImage("/shop/bgGame.png");
        ShopInventoryP = ImageLoader.loadImage("/shop/inventoryScreen.png");
        goldSword = ImageLoader.loadImage("/shop/swordGold.png");
        plus = ImageLoader.loadImage("/shop/plus.png");
        minus = ImageLoader.loadImage("/shop/minus.png");
        silverSword = ImageLoader.loadImage("/shop/swordSilver.png");
        bRedPotion = ImageLoader.loadImage("/shop/redPotionBig.png");
        mRedPotion = ImageLoader.loadImage("/shop/redPotionMedium.png");
        bBluePotion = ImageLoader.loadImage("/shop/bluePotionBig.png");
        bGreenPotion = ImageLoader.loadImage("/shop/greenPotionBig.png");
        mBluePotion = ImageLoader.loadImage("/shop/bluePotionMedium.png");
        woodSword = ImageLoader.loadImage("/shop/swordWood.png");
        left = ImageLoader.loadImage("/shop/left.png");
        right = ImageLoader.loadImage("/shop/right.png");
        iconHealth = ImageLoader.loadImage("/shop/iconHealth.png");
        iconHealth2 = ImageLoader.loadImage("/shop/iconHealth2.png");
        iconDmg = ImageLoader.loadImage("/shop/iconDamage.png");
        iconStamina = ImageLoader.loadImage("/shop/iconStamina.png");
        iconSpeed = ImageLoader.loadImage("/shop/iconSpeed.png");
        iconGold = ImageLoader.loadImage("/shop/iconGold.png");
        iconUser = ImageLoader.loadImage("/shop/iconUser.png");
        tAgimat = ImageLoader.loadImage(("/shop/yellowAgimat.png"));
        
        loginBtn = ImageLoader.loadImage("/shop/login.png");
        fAgimat = ImageLoader.loadImage("/shop/redAgimat.png");
        wAgimat = ImageLoader.loadImage("/shop/blueAgimat.png");
        windAgimat = ImageLoader.loadImage("/shop/greenAgimat.png");
        
        ulo = ImageLoader.loadImage("/sprites/ulo.png");
        dila = ImageLoader.loadImage("/sprites/dila.png");
        baboy = ImageLoader.loadImage("/sprites/baboy.png");
        diwata = ImageLoader.loadImage("/sprites/diwata.png");
        
        //quest
        questAswang = ImageLoader.loadImage("/quest/aswang.png");
        questMananangal = ImageLoader.loadImage("/quest/manananggal.png");
        questPig = ImageLoader.loadImage("/quest/pig.png");
        questBossMananagal = ImageLoader.loadImage("/quest/boss.png");
        questDayuhan = ImageLoader.loadImage("/quest/dayuhan.png");
        
        //menu 
         menuStateBg = ImageLoader.loadImage("/menu/bgLogin.png");
        loginBg = ImageLoader.loadImage("/menu/login.png");
        cutSceneBg = ImageLoader.loadImage("/menu/cutScene1.png");
        signupBg = ImageLoader.loadImage("/menu/bg.png");
        SpriteSheet start = new SpriteSheet(ImageLoader.loadImage("/menu/start.png"));
        SpriteSheet load = new SpriteSheet(ImageLoader.loadImage("/menu/load.png"));
        SpriteSheet logout = new SpriteSheet(ImageLoader.loadImage("/menu/logout.png"));
        SpriteSheet play = new SpriteSheet(ImageLoader.loadImage("/menu/play.png"));
        SpriteSheet tutorial = new SpriteSheet(ImageLoader.loadImage("/menu/tutorials.png"));
        SpriteSheet back = new SpriteSheet(ImageLoader.loadImage("/menu/back.png"));
        SpriteSheet resume = new SpriteSheet(ImageLoader.loadImage("/menu/resume.png"));
        SpriteSheet exit = new SpriteSheet(ImageLoader.loadImage("/menu/exit.png"));
        SpriteSheet save = new SpriteSheet(ImageLoader.loadImage("/menu/save.png"));
        SpriteSheet accept = new SpriteSheet(ImageLoader.loadImage("/images/accept.png"));
        SpriteSheet cancel = new SpriteSheet(ImageLoader.loadImage("/images/cancel.png"));
        SpriteSheet edit = new SpriteSheet(ImageLoader.loadImage("/images/EditAccount.png"));
        SpriteSheet leaderBoard = new SpriteSheet(ImageLoader.loadImage("/images/leaderBoardBtn.png"));
                
        startBtn = new BufferedImage[2];
        startBtn[0] = start.crop(0, 0, 61, 16);
        startBtn[1] = start.crop(0, 16, 61, 16);
        
        loadBtn = new BufferedImage[2];
        loadBtn[0] = load.crop(0, 0, 61, 16);
        loadBtn[1] = load.crop(0, 16, 61, 16);
        
        logoutBtn = new BufferedImage[2];
        logoutBtn[0] = logout.crop(0, 0, 61, 16);
        logoutBtn[1] = logout.crop(0, 16, 61, 16);    

        playBtn = new BufferedImage[2];
        playBtn[0] = play.crop(0, 0, 61, 16);
        playBtn[1] = play.crop(0, 16, 61, 16);
        
        tutorialsBtn = new BufferedImage[2];
        tutorialsBtn[0] = tutorial.crop(0, 0, 61, 16);
        tutorialsBtn[1] = tutorial.crop(0, 16, 61, 16);
        
        backBtn = new BufferedImage[2];
        backBtn[0] = back.crop(0, 0, 61, 16);
        backBtn[1] = back.crop(0, 16, 61, 16);
       
        resumeBtn = new BufferedImage[2];
        resumeBtn[0] = resume.crop(0, 0, 41, 16);
        resumeBtn[1] = resume.crop(0, 16, 41, 16);
        
        saveBtn = new BufferedImage[2];
        saveBtn[0] = save.crop(0, 0, 41, 16);
        saveBtn[1] = save.crop(0, 16, 41, 16);
        
        exitBtn = new BufferedImage[2];
        exitBtn[0] = exit.crop(0, 0, 41, 16);
        exitBtn[1] = exit.crop(0, 16, 41, 16);
        
        acceptBtn = new BufferedImage[2];
        acceptBtn[0] = accept.crop(0, 0, 47, 16);
        acceptBtn[1] = accept.crop(0, 16, 47, 16);
        
        cancelBtn = new BufferedImage[2];
        cancelBtn[0] = cancel.crop(0, 0, 47, 16);
        cancelBtn[1] = cancel.crop(0, 16, 47, 16);
        
        editBtn = new BufferedImage[2];
        editBtn[0] = edit.crop(0, 0, 63,16);
        editBtn[1] = edit.crop(0, 16, 63, 16);
        
        leaderBoardBtn = new BufferedImage[2];
        leaderBoardBtn[0] = leaderBoard.crop(0, 0, 61, 16);
        leaderBoardBtn[1] = leaderBoard.crop(0, 16, 61,16);
        
        //shop
        closeBtn = ImageLoader.loadImage("/shop/close2.png");
        
        equipScreen = equipS.crop(0, 0, 8, 18);
        lore = ImageLoader.loadImage("/images/lore.png");
        
        equipButton = new BufferedImage[2];
        equipButton[0] = equip.crop(0, 0, 38, 16);
        equipButton[1] = equip.crop(0, 16, 38, 16);
        
        buyBtn = new BufferedImage[2];
        buyBtn[0] = buy.crop(0, 0, 38, 16);
        buyBtn[1] = buy.crop(0, 16, 38, 16);
        
        sellBtn = new BufferedImage[2];
        sellBtn[0] = sell.crop(0, 0, 38, 16);
        sellBtn[1] = sell.crop(0, 16, 38, 16);
        
        salamankaButton = new BufferedImage[2];
        salamankaButton[0] = salamanka.crop(0, 0, 45, 16);
        salamankaButton[1] = salamanka.crop(0, 16, 45, 16);
        
        sandataButton = new BufferedImage[2];
        sandataButton[0] = sandata.crop(0, 0, 40, 16);
        sandataButton[1] = sandata.crop(0, 16, 40, 16);
                
        inventoryScreen = ImageLoader.loadImage("/shop/inventoryScreen.png");
        loginBg = ImageLoader.loadImage("/shop/bgLogin.png");
        
        //info
        stage1 = new BufferedImage[2];
        stage1[0] = ImageLoader.loadImage("/images/lvl_1.png");
        stage1[1] = ImageLoader.loadImage("/images/lvl1_hover.png");
        
        stage2 = new BufferedImage[2];
        stage2[0] = ImageLoader.loadImage("/images/lvl_2.png");
        stage2[1] = ImageLoader.loadImage("/images/lvl2_hover.png");
        
        stage3 = new BufferedImage[2];
        stage3[0] = ImageLoader.loadImage("/images/lvl_3.png");
        stage3[1] = ImageLoader.loadImage("/images/lvl3_hover.png");
        
        player_left = new BufferedImage[3];
        playerFace_left = playerSheet.crop(0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        player_left[0] = playerSheet.crop(PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        player_left[1] = playerSheet.crop(PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        player_left[2] = playerSheet.crop(PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        player_right = new BufferedImage[3];
        playerFace_right = playerSheet.crop(0, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        player_right[0] = playerSheet.crop(PLAYERWIDTH, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        player_right[1] = playerSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        player_right[2] = playerSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
     
        player_up = new BufferedImage[3];
        playerFace_up = playerSheet.crop(0, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        player_up[0] = playerSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        player_up[1] = playerSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        player_up[2] = playerSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        
        player_down = new BufferedImage[3];
        playerFace_down = playerSheet.crop(0, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        player_down[0] = playerSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        player_down[1] = playerSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        player_down[2] = playerSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        
        roll_left = new BufferedImage[5];
        roll_left[0] = rollSheet.crop (0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        roll_left[1] = rollSheet.crop(PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        roll_left[2] = rollSheet.crop(PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        roll_left[3] = rollSheet.crop(PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        roll_left[4] = rollSheet.crop(PLAYERWIDTH*4, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        roll_right = new BufferedImage[5];
        roll_right[0] = rollSheet.crop (0, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        roll_right[1] = rollSheet.crop(PLAYERWIDTH, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        roll_right[2] = rollSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        roll_right[3] = rollSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        roll_right[4] = rollSheet.crop(PLAYERWIDTH*4, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        
        roll_up = new BufferedImage[5];
        roll_up[0] = rollSheet.crop (0, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        roll_up[1] = rollSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        roll_up[2] = rollSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        roll_up[3] = rollSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        roll_up[4] = rollSheet.crop(PLAYERWIDTH*4, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        
        roll_down = new BufferedImage[5];
        roll_down[0] = rollSheet.crop (0, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        roll_down[1] = rollSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        roll_down[2] = rollSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        roll_down[3] = rollSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        roll_down[4] = rollSheet.crop(PLAYERWIDTH*4, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        
//ATTACK        
        attack_left = new BufferedImage[4];
        attack_left[0] = attackSheet.crop (0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        attack_left[1] = attackSheet.crop(PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        attack_left[2] = attackSheet.crop(PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        attack_left[3] = attackSheet.crop(PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        attack_right = new BufferedImage[4];
        attack_right[0] = attackSheet.crop (0, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        attack_right[1] = attackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        attack_right[2] = attackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        attack_right[3] = attackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        
        attack_up = new BufferedImage[4];
        attack_up[0] = attackSheet.crop (0, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        attack_up[1] = attackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        attack_up[2] = attackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        attack_up[3] = attackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        
        attack_down = new BufferedImage[4];
        attack_down[0] = attackSheet.crop (0, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        attack_down[1] = attackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        attack_down[2] = attackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        attack_down[3] = attackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        
                goldAttack_left = new BufferedImage[4];
        goldAttack_left[0] = goldAttackSheet.crop (0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_left[1] = goldAttackSheet.crop(PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_left[2] = goldAttackSheet.crop(PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_left[3] = goldAttackSheet.crop(PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        goldAttack_right = new BufferedImage[4];
        goldAttack_right[0] = goldAttackSheet.crop (0, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_right[1] = goldAttackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_right[2] = goldAttackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_right[3] = goldAttackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        
        goldAttack_up = new BufferedImage[4];
        goldAttack_up[0] = goldAttackSheet.crop (0, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_up[1] = goldAttackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_up[2] = goldAttackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_up[3] = goldAttackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        
        goldAttack_down = new BufferedImage[4];
        goldAttack_down[0] = goldAttackSheet.crop (0, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_down[1] = goldAttackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_down[2] = goldAttackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        goldAttack_down[3] = goldAttackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
       
        silverAttack_left = new BufferedImage[4];
        silverAttack_left[0] = silverAttackSheet.crop (0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_left[1] = silverAttackSheet.crop(PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_left[2] = silverAttackSheet.crop(PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_left[3] = silverAttackSheet.crop(PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        silverAttack_right = new BufferedImage[4];
        silverAttack_right[0] = silverAttackSheet.crop (0, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_right[1] = silverAttackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_right[2] = silverAttackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_right[3] = silverAttackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        
        silverAttack_up = new BufferedImage[4];
        silverAttack_up[0] = silverAttackSheet.crop (0, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_up[1] = silverAttackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_up[2] = silverAttackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_up[3] = silverAttackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        
        silverAttack_down = new BufferedImage[4];
        silverAttack_down[0] = silverAttackSheet.crop (0, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_down[1] = silverAttackSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_down[2] = silverAttackSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        silverAttack_down[3] = silverAttackSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        
        goldWaterSpin= new BufferedImage[4];
        goldWaterSpin[0] = goldSpinSheet.crop (0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        goldWaterSpin[1] = goldSpinSheet.crop (PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        goldWaterSpin[2] = goldSpinSheet.crop (PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        goldWaterSpin[3] = goldSpinSheet.crop (PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        silverWaterSpin= new BufferedImage[4];
        silverWaterSpin[0] = silverSpinSheet.crop (0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        silverWaterSpin[1] = silverSpinSheet.crop (PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        silverWaterSpin[2] = silverSpinSheet.crop (PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        silverWaterSpin[3] = silverSpinSheet.crop (PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        waterSpin= new BufferedImage[4];
        waterSpin[0] = spinSheet.crop (0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        waterSpin[1] = spinSheet.crop (PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        waterSpin[2] = spinSheet.crop (PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        waterSpin[3] = spinSheet.crop (PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        goldFireSpin= new BufferedImage[4];
        goldFireSpin[0] = goldSpinSheet.crop (0, 100, PLAYERWIDTH, PLAYERHEIGHT);
        goldFireSpin[1] = goldSpinSheet.crop (PLAYERWIDTH, 100, PLAYERWIDTH, PLAYERHEIGHT);
        goldFireSpin[2] = goldSpinSheet.crop (PLAYERWIDTH*2, 100, PLAYERWIDTH, PLAYERHEIGHT);
        goldFireSpin[3] = goldSpinSheet.crop (PLAYERWIDTH*3, 100, PLAYERWIDTH, PLAYERHEIGHT);
        
        silverFireSpin= new BufferedImage[4];
        silverFireSpin[0] = silverSpinSheet.crop (0, 100, PLAYERWIDTH, PLAYERHEIGHT);
        silverFireSpin[1] = silverSpinSheet.crop (PLAYERWIDTH, 100, PLAYERWIDTH, PLAYERHEIGHT);
        silverFireSpin[2] = silverSpinSheet.crop (PLAYERWIDTH*2, 100, PLAYERWIDTH, PLAYERHEIGHT);
        silverFireSpin[3] = silverSpinSheet.crop (PLAYERWIDTH*3, 100, PLAYERWIDTH, PLAYERHEIGHT);
        
        
        fireSpin= new BufferedImage[4];
        fireSpin[0] = spinSheet.crop (0, 100, PLAYERWIDTH, PLAYERHEIGHT);
        fireSpin[1] = spinSheet.crop (PLAYERWIDTH, 100, PLAYERWIDTH, PLAYERHEIGHT);
        fireSpin[2] = spinSheet.crop (PLAYERWIDTH*2, 100, PLAYERWIDTH, PLAYERHEIGHT);
        fireSpin[3] = spinSheet.crop (PLAYERWIDTH*3, 100, PLAYERWIDTH, PLAYERHEIGHT);
        
        goldWindSpin= new BufferedImage[4];
        goldWindSpin[0] = goldSpinSheet.crop (0, 300, PLAYERWIDTH, PLAYERHEIGHT);
        goldWindSpin[1] = goldSpinSheet.crop (PLAYERWIDTH, 300, PLAYERWIDTH, PLAYERHEIGHT);
        goldWindSpin[2] = goldSpinSheet.crop (PLAYERWIDTH*2, 300, PLAYERWIDTH, PLAYERHEIGHT);
        goldWindSpin[3] = goldSpinSheet.crop (PLAYERWIDTH*3, 300, PLAYERWIDTH, PLAYERHEIGHT);
        
        silverWindSpin= new BufferedImage[4];
        silverWindSpin[0] = silverSpinSheet.crop (0, 300, PLAYERWIDTH, PLAYERHEIGHT);
        silverWindSpin[1] = silverSpinSheet.crop (PLAYERWIDTH, 300, PLAYERWIDTH, PLAYERHEIGHT);
        silverWindSpin[2] = silverSpinSheet.crop (PLAYERWIDTH*2, 300, PLAYERWIDTH, PLAYERHEIGHT);
        silverWindSpin[3] = silverSpinSheet.crop (PLAYERWIDTH*3, 300, PLAYERWIDTH, PLAYERHEIGHT);
        
        windSpin= new BufferedImage[4];
        windSpin[0] = spinSheet.crop (0, 300, PLAYERWIDTH, PLAYERHEIGHT);
        windSpin[1] = spinSheet.crop (PLAYERWIDTH, 300, PLAYERWIDTH, PLAYERHEIGHT);
        windSpin[2] = spinSheet.crop (PLAYERWIDTH*2, 300, PLAYERWIDTH, PLAYERHEIGHT);
        windSpin[3] = spinSheet.crop (PLAYERWIDTH*3, 300, PLAYERWIDTH, PLAYERHEIGHT);
        
        goldLightningSpin= new BufferedImage[4];
        goldLightningSpin[0] = goldSpinSheet.crop (0, 200, PLAYERWIDTH, PLAYERHEIGHT);
        goldLightningSpin[1] = goldSpinSheet.crop (PLAYERWIDTH, 200, PLAYERWIDTH, PLAYERHEIGHT);
        goldLightningSpin[2] = goldSpinSheet.crop (PLAYERWIDTH*2, 200, PLAYERWIDTH, PLAYERHEIGHT);
        goldLightningSpin[3] = goldSpinSheet.crop (PLAYERWIDTH*3, 200, PLAYERWIDTH, PLAYERHEIGHT);
        
        silverLightningSpin= new BufferedImage[4];
        silverLightningSpin[0] = silverSpinSheet.crop (0, 200, PLAYERWIDTH, PLAYERHEIGHT);
        silverLightningSpin[1] = silverSpinSheet.crop (PLAYERWIDTH, 200, PLAYERWIDTH, PLAYERHEIGHT);
        silverLightningSpin[2] = silverSpinSheet.crop (PLAYERWIDTH*2, 200, PLAYERWIDTH, PLAYERHEIGHT);
        silverLightningSpin[3] = silverSpinSheet.crop (PLAYERWIDTH*3, 200, PLAYERWIDTH, PLAYERHEIGHT);
        
        lightningSpin= new BufferedImage[4];
        lightningSpin[0] = spinSheet.crop (0, 200, PLAYERWIDTH, PLAYERHEIGHT);
        lightningSpin[1] = spinSheet.crop (PLAYERWIDTH, 200, PLAYERWIDTH, PLAYERHEIGHT);
        lightningSpin[2] = spinSheet.crop (PLAYERWIDTH*2, 200, PLAYERWIDTH, PLAYERHEIGHT);
        lightningSpin[3] = spinSheet.crop (PLAYERWIDTH*3, 200, PLAYERWIDTH, PLAYERHEIGHT);
        
        diwataFlying = new BufferedImage[3];
        diwataFlying[0] = diwataSheet.crop(0, 0, 50, 50);
        diwataFlying[1] = diwataSheet.crop(50, 0, 50, 50);
        diwataFlying[2] = diwataSheet.crop(100, 0, 50, 50);
        
        npc1_left = new BufferedImage[4];
        npc1_left[0] = npcSheet1.crop(0, 0, 64, 64);
        npc1_left[1] = npcSheet1.crop(64, 0, 64, 64);
        npc1_left[2] = npcSheet1.crop(128, 0, 64, 64);
        npc1_left[3] = npcSheet1.crop(192,0,64,64);
        
        npc1_right = new BufferedImage[4];
        npc1_right[0] = npcSheet1.crop(0, 64, 64, 64);
        npc1_right[1] = npcSheet1.crop(64, 64, 64, 64);
        npc1_right[2] = npcSheet1.crop(128, 64, 64, 64);
        npc1_right[3] = npcSheet1.crop(192, 64, 64, 64);
        
        npc1_up = new BufferedImage[4];
        npc1_up[0] = npcSheet1.crop(0, 128, 64, 64);
        npc1_up[1] = npcSheet1.crop(64, 128, 64, 64);
        npc1_up[2] = npcSheet1.crop(128, 128, 64, 64);
        npc1_up[3] = npcSheet1.crop(192, 128, 64, 64);
        
        npc1_down = new BufferedImage[4];
        npc1_down[0] = npcSheet1.crop(0, 192, 64, 64);
        npc1_down[1] = npcSheet1.crop(64, 192, 64, 64);
        npc1_down[2] = npcSheet1.crop(128, 192, 64, 64);
        npc1_down[3] = npcSheet1.crop(192, 192, 64, 64);
        
        npc2_left = new BufferedImage[4];
        npc2_left[0] = npcSheet2.crop(0, 0, 64, 64);
        npc2_left[1] = npcSheet2.crop(64, 0, 64, 64);
        npc2_left[2] = npcSheet2.crop(128, 0, 64, 64);
        npc2_left[3] = npcSheet2.crop(192,0,64,64);
        
        npc2_right = new BufferedImage[4];
        npc2_right[0] = npcSheet2.crop(0, 64, 64, 64);
        npc2_right[1] = npcSheet2.crop(64, 64, 64, 64);
        npc2_right[2] = npcSheet2.crop(128, 64, 64, 64);
        npc2_right[3] = npcSheet2.crop(192, 64, 64, 64);
        
        npc2_up = new BufferedImage[4];
        npc2_up[0] = npcSheet2.crop(0, 128, 64, 64);
        npc2_up[1] = npcSheet2.crop(64, 128, 64, 64);
        npc2_up[2] = npcSheet2.crop(128, 128, 64, 64);
        npc2_up[3] = npcSheet2.crop(192, 128, 64, 64);
        
        npc2_down = new BufferedImage[4];
        npc2_down[0] = npcSheet2.crop(0, 192, 64, 64);
        npc2_down[1] = npcSheet2.crop(64, 192, 64, 64);
        npc2_down[2] = npcSheet2.crop(128, 192, 64, 64);
        npc2_down[3] = npcSheet2.crop(192, 192, 64, 64);
        
        npc2_leftAtk = new BufferedImage[4];
        npc2_leftAtk[0] = npcSheet2Atk.crop(0, 0, 64, 64);
        npc2_leftAtk[1] = npcSheet2Atk.crop(64, 0, 64, 64);
        npc2_leftAtk[2] = npcSheet2Atk.crop(128, 0, 64, 64);
        npc2_leftAtk[3] = npcSheet2Atk.crop(192,0,64,64);
        
        npc2_rightAtk = new BufferedImage[4];
        npc2_rightAtk[0] = npcSheet2Atk.crop(0, 64, 64, 64);
        npc2_rightAtk[1] = npcSheet2Atk.crop(64, 64, 64, 64);
        npc2_rightAtk[2] = npcSheet2Atk.crop(128, 64, 64, 64);
        npc2_rightAtk[3] = npcSheet2Atk.crop(192, 64, 64, 64);
        
        npc2_upAtk = new BufferedImage[4];
        npc2_upAtk[0] = npcSheet2Atk.crop(0, 128, 64, 64);
        npc2_upAtk[1] = npcSheet2Atk.crop(64, 128, 64, 64);
        npc2_upAtk[2] = npcSheet2Atk.crop(128, 128, 64, 64);
        npc2_upAtk[3] = npcSheet2Atk.crop(192, 128, 64, 64);
        
        npc2_downAtk = new BufferedImage[4];
        npc2_downAtk[0] = npcSheet2Atk.crop(0, 192, 64, 64);
        npc2_downAtk[1] = npcSheet2Atk.crop(64, 192, 64, 64);
        npc2_downAtk[2] = npcSheet2Atk.crop(128, 192, 64, 64);
        npc2_downAtk[3] = npcSheet2Atk.crop(192, 192, 64, 64);
        
        spaniardLeft = new BufferedImage[4];
        spaniardLeft[0] = spaniardSheet.crop(0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardLeft[1] = spaniardSheet.crop(PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardLeft[2] = spaniardSheet.crop(PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardLeft[3] = spaniardSheet.crop(PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        spaniardRight = new BufferedImage[4];
        spaniardRight[0] = spaniardSheet.crop(0, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardRight[1] = spaniardSheet.crop(PLAYERWIDTH, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardRight[2] = spaniardSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardRight[3] = spaniardSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
     
        spaniardUp = new BufferedImage[4];
        spaniardUp[0] = spaniardSheet.crop(0, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardUp[1] = spaniardSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardUp[2] = spaniardSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardUp[3] = spaniardSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        
        spaniardDown = new BufferedImage[4];
        spaniardDown[0] = spaniardSheet.crop(0, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardDown[1] = spaniardSheet.crop(PLAYERWIDTH, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardDown[2] = spaniardSheet.crop(PLAYERWIDTH*2, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardDown[3] = spaniardSheet.crop(PLAYERWIDTH*3, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        
        spaniardLeftAtk = new BufferedImage[4];
        spaniardLeftAtk[0] = spaniardSheetAtk.crop (0, 0, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardLeftAtk[1] = spaniardSheetAtk.crop(PLAYERWIDTH, 0, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardLeftAtk[2] = spaniardSheetAtk.crop(PLAYERWIDTH*2, 0, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardLeftAtk[3] = spaniardSheetAtk.crop(PLAYERWIDTH*3, 0, PLAYERWIDTH, PLAYERHEIGHT);
        
        spaniardRightAtk = new BufferedImage[4];
        spaniardRightAtk[0] = spaniardSheetAtk.crop (0, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardRightAtk[1] = spaniardSheetAtk.crop(PLAYERWIDTH, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardRightAtk[2] = spaniardSheetAtk.crop(PLAYERWIDTH*2, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardRightAtk[3] = spaniardSheetAtk.crop(PLAYERWIDTH*3, PLAYERHEIGHT, PLAYERWIDTH, PLAYERHEIGHT);
        
        spaniardUpAtk = new BufferedImage[4];
        spaniardUpAtk[0] = spaniardSheetAtk.crop (0, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardUpAtk[1] = spaniardSheetAtk.crop(PLAYERWIDTH, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardUpAtk[2] = spaniardSheetAtk.crop(PLAYERWIDTH*2, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardUpAtk[3] = spaniardSheetAtk.crop(PLAYERWIDTH*3, PLAYERHEIGHT*2, PLAYERWIDTH, PLAYERHEIGHT);
        
        spaniardDownAtk = new BufferedImage[4];
        spaniardDownAtk[0] = spaniardSheetAtk.crop (0, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardDownAtk[1] = spaniardSheetAtk.crop(PLAYERWIDTH, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardDownAtk[2] = spaniardSheetAtk.crop(PLAYERWIDTH*2, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
        spaniardDownAtk[3] = spaniardSheetAtk.crop(PLAYERWIDTH*3, PLAYERHEIGHT*3, PLAYERWIDTH, PLAYERHEIGHT);
                
        enemy_left = new BufferedImage[3];
        enemy_left[0] = manananggalSheet.crop(0,0,PLAYERWIDTH,PLAYERHEIGHT);
        enemy_left[1] = manananggalSheet.crop(PLAYERWIDTH,0,PLAYERWIDTH,PLAYERHEIGHT);
        enemy_left[2] = manananggalSheet.crop(PLAYERWIDTH*2,0,PLAYERWIDTH,PLAYERHEIGHT);
        
        enemy_right = new BufferedImage[3];
        enemy_right[0] = manananggalSheet.crop(0,PLAYERHEIGHT,PLAYERWIDTH,PLAYERHEIGHT);
        enemy_right[1] = manananggalSheet.crop(PLAYERWIDTH,PLAYERHEIGHT,PLAYERWIDTH,PLAYERHEIGHT);
        enemy_right[2] = manananggalSheet.crop(PLAYERWIDTH*2,PLAYERHEIGHT,PLAYERWIDTH,PLAYERHEIGHT);
        
        enemyAttack_left = new BufferedImage[3];
        enemyAttack_left[0] = manananggalSheet.crop(0,PLAYERHEIGHT*2,PLAYERWIDTH,PLAYERHEIGHT);
        enemyAttack_left[1] = manananggalSheet.crop(PLAYERWIDTH,PLAYERHEIGHT*2,PLAYERWIDTH,PLAYERHEIGHT);
        enemyAttack_left[2] = manananggalSheet.crop(PLAYERWIDTH*2,PLAYERHEIGHT*2,PLAYERWIDTH,PLAYERHEIGHT);
        
        enemyAttack_right = new BufferedImage[3];
        enemyAttack_right[0] = manananggalSheet.crop(0,PLAYERHEIGHT*3,PLAYERWIDTH,PLAYERHEIGHT);
        enemyAttack_right[1] = manananggalSheet.crop(PLAYERWIDTH,PLAYERHEIGHT*3,PLAYERWIDTH,PLAYERHEIGHT);
        enemyAttack_right[2] = manananggalSheet.crop(PLAYERWIDTH*2,PLAYERHEIGHT*3,PLAYERWIDTH,PLAYERHEIGHT);
        
        enemySkillRight = new BufferedImage[4];
        enemySkillRight [0] = enemySkillSheet.crop(0,0, 200, 100);
        enemySkillRight [1] = enemySkillSheet.crop(0,100, 200, 100);
        enemySkillRight [2] = enemySkillSheet.crop(400,100, 200, 100);
        enemySkillRight [3] = enemySkillSheet.crop(0,0, 200, 100);
        
        enemySkillLeft = new BufferedImage[4];
        enemySkillLeft [0] = enemySkillSheet.crop(200,0, 200, 100);
        enemySkillLeft [1] = enemySkillSheet.crop(200,100, 200, 100);
        enemySkillLeft [2] = enemySkillSheet.crop(400,0, 200, 100);
        enemySkillLeft [3] = enemySkillSheet.crop(200,0, 200, 100);
        
        enemySkillDown = new BufferedImage[4];
        enemySkillDown [0] = enemySkillSheet.crop(0,200, 100, 200);
        enemySkillDown [1] = enemySkillSheet.crop(100,200, 100, 200);
        enemySkillDown [2] = enemySkillSheet.crop(200,200, 100, 200);
        enemySkillDown [3] = enemySkillSheet.crop(0,200, 100, 200);
        
        enemySkillUp = new BufferedImage[4];
        enemySkillUp [1] = enemySkillSheet.crop(300,200, 100, 200);
        enemySkillUp [0] = enemySkillSheet.crop(400,200, 100, 100);
        enemySkillUp [2] = enemySkillSheet.crop(500,200, 100, 200);
        enemySkillUp [3] = enemySkillSheet.crop(300,200, 100, 200);
        
        pigWalk_left = new BufferedImage[4];
        pigFace_left = pigSheet.crop(0, 0, ENEMYWIDTH, ENEMYHEIGHT);
        pigWalk_left[0] = pigSheet.crop(0, 0, ENEMYWIDTH, ENEMYHEIGHT);
        pigWalk_left[1] = pigSheet.crop(ENEMYWIDTH, 0, ENEMYWIDTH, ENEMYHEIGHT);
        pigWalk_left[2] = pigSheet.crop(ENEMYWIDTH*2, 0, ENEMYWIDTH, ENEMYHEIGHT);
        pigWalk_left[3] = pigSheet.crop(ENEMYWIDTH*3, 0, ENEMYWIDTH, ENEMYHEIGHT);
        
        pigWalk_right = new BufferedImage[4];
        pigFace_right = pigSheet.crop(0, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        pigWalk_right[0] = pigSheet.crop(0, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        pigWalk_right[1] = pigSheet.crop(ENEMYWIDTH, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        pigWalk_right[2] = pigSheet.crop(ENEMYWIDTH*2, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        pigWalk_right[3] = pigSheet.crop(ENEMYWIDTH*3, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        
        pigAttack_left = new BufferedImage[4];
        pigAttack_left [0]= pigSheet.crop(0, ENEMYHEIGHT*2, ENEMYWIDTH, ENEMYWIDTH);
        pigAttack_left [1]= pigSheet.crop(ENEMYWIDTH, ENEMYHEIGHT*2, ENEMYWIDTH, ENEMYWIDTH);
        pigAttack_left [2]= pigSheet.crop(ENEMYWIDTH*2, ENEMYHEIGHT*2, ENEMYWIDTH, ENEMYWIDTH);
        pigAttack_left [3]= pigSheet.crop(ENEMYWIDTH*3, ENEMYHEIGHT*2, ENEMYWIDTH, ENEMYWIDTH);
        
        pigAttack_right = new BufferedImage[4];
        pigAttack_right [0]= pigSheet.crop(0, ENEMYHEIGHT*3, ENEMYWIDTH, ENEMYWIDTH);
        pigAttack_right [1]= pigSheet.crop(ENEMYWIDTH, ENEMYHEIGHT*3, ENEMYWIDTH, ENEMYWIDTH);
        pigAttack_right [2]= pigSheet.crop(ENEMYWIDTH*2, ENEMYHEIGHT*3, ENEMYWIDTH, ENEMYWIDTH);
        pigAttack_right [3]= pigSheet.crop(ENEMYWIDTH*3, ENEMYHEIGHT*3, ENEMYWIDTH, ENEMYWIDTH);
        
        aswangWalk_left = new BufferedImage [4];
        aswangFace_left = aswangSheet.crop(0, 0, ENEMYWIDTH, ENEMYHEIGHT);
        aswangWalk_left [0] = aswangSheet.crop (0, 0, ENEMYWIDTH, ENEMYHEIGHT);
        aswangWalk_left [1] = aswangSheet.crop (ENEMYWIDTH, 0, ENEMYWIDTH, ENEMYHEIGHT);
        aswangWalk_left [2] = aswangSheet.crop (ENEMYWIDTH*2, 0, ENEMYWIDTH, ENEMYHEIGHT);
        aswangWalk_left [3] = aswangSheet.crop (ENEMYWIDTH*3, 0, ENEMYWIDTH, ENEMYHEIGHT);
        
        aswangWalk_right = new BufferedImage [4];
        aswangFace_right = aswangSheet.crop(0, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        aswangWalk_right [0] = aswangSheet.crop(0, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        aswangWalk_right [1] = aswangSheet.crop(ENEMYWIDTH, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        aswangWalk_right [2] = aswangSheet.crop(ENEMYWIDTH*2, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        aswangWalk_right [3] = aswangSheet.crop(ENEMYWIDTH*3, ENEMYHEIGHT, ENEMYWIDTH, ENEMYHEIGHT);
        
        aswangAttack_left = new BufferedImage [4];
        aswangAttack_left [0] = aswangSheet.crop (0, ENEMYHEIGHT*2, ENEMYWIDTH, ENEMYHEIGHT);
        aswangAttack_left [1] = aswangSheet.crop (ENEMYWIDTH, ENEMYHEIGHT*2, ENEMYWIDTH, ENEMYHEIGHT);
        aswangAttack_left [2] = aswangSheet.crop (ENEMYWIDTH*2, ENEMYHEIGHT*2, ENEMYWIDTH, ENEMYHEIGHT);
        aswangAttack_left [3] = aswangSheet.crop (ENEMYWIDTH*3, ENEMYHEIGHT*2, ENEMYWIDTH, ENEMYHEIGHT);
        
        aswangAttack_right = new BufferedImage [4];
        aswangAttack_right[0] = aswangSheet.crop (0, ENEMYHEIGHT*3, ENEMYWIDTH, ENEMYHEIGHT);
        aswangAttack_right [1] = aswangSheet.crop (ENEMYWIDTH, ENEMYHEIGHT*3, ENEMYWIDTH, ENEMYHEIGHT);
        aswangAttack_right [2] = aswangSheet.crop (ENEMYWIDTH*2, ENEMYHEIGHT*3, ENEMYWIDTH, ENEMYHEIGHT);
        aswangAttack_right [3] = aswangSheet.crop (ENEMYWIDTH*3, ENEMYHEIGHT*3, ENEMYWIDTH, ENEMYHEIGHT);
        
        fireProjectileLeft = new BufferedImage[4];
        fireProjectileLeft[0] = projectileSheet.crop(0, 0, 64, 64);
        fireProjectileLeft[1] = projectileSheet.crop(64, 0, 64, 64);
        fireProjectileLeft[2] = projectileSheet.crop(128, 0, 64, 64);
        fireProjectileLeft[3] = projectileSheet.crop(192, 0, 64, 64);
        
        fireProjectileRight = new BufferedImage[4];
        fireProjectileRight[0] = projectileSheet.crop(0, 64, 64, 64);
        fireProjectileRight[1] = projectileSheet.crop(64, 64, 64, 64);
        fireProjectileRight[2] = projectileSheet.crop(128, 64, 64, 64);
        fireProjectileRight[3] = projectileSheet.crop(192, 64, 64, 64);
        
        fireProjectileUp = new BufferedImage[4];
        fireProjectileUp[0] = projectileSheet.crop(0, 128, 64, 64);
        fireProjectileUp[1] = projectileSheet.crop(64, 128, 64, 64);
        fireProjectileUp[2] = projectileSheet.crop(128, 128, 64, 64);
        fireProjectileUp[3] = projectileSheet.crop(192, 128, 64, 64);
        
        fireProjectileDown = new BufferedImage[4];
        fireProjectileDown[0] = projectileSheet.crop(0, 192, 64, 64);
        fireProjectileDown[1] = projectileSheet.crop(64, 192, 64, 64);
        fireProjectileDown[2] = projectileSheet.crop(128, 192, 64, 64);
        fireProjectileDown[3] = projectileSheet.crop(192, 192, 64, 64);
        
        
        waterProjectileLeft = new BufferedImage[4];
        waterProjectileLeft[0] = waterProjectileSheet.crop(0, 0, 64, 64);
        waterProjectileLeft[1] = waterProjectileSheet.crop(64, 0, 64, 64);
        waterProjectileLeft[2] = waterProjectileSheet.crop(128, 0, 64, 64);
        waterProjectileLeft[3] = waterProjectileSheet.crop(192, 0, 64, 64);
        
        waterProjectileRight = new BufferedImage[4];
        waterProjectileRight[0] = waterProjectileSheet.crop(0, 64, 64, 64);
        waterProjectileRight[1] = waterProjectileSheet.crop(64, 64, 64, 64);
        waterProjectileRight[2] = waterProjectileSheet.crop(128, 64, 64, 64);
        waterProjectileRight[3] = waterProjectileSheet.crop(192, 64, 64, 64);
        
        waterProjectileUp = new BufferedImage[4];
        waterProjectileUp[0] = waterProjectileSheet.crop(0, 128, 64, 64);
        waterProjectileUp[1] = waterProjectileSheet.crop(64, 128, 64, 64);
        waterProjectileUp[2] = waterProjectileSheet.crop(128, 128, 64, 64);
        waterProjectileUp[3] = waterProjectileSheet.crop(192, 128, 64, 64);
        
        waterProjectileDown = new BufferedImage[4];
        waterProjectileDown[0] = waterProjectileSheet.crop(0, 192, 64, 64);
        waterProjectileDown[1] = waterProjectileSheet.crop(64, 192, 64, 64);
        waterProjectileDown[2] = waterProjectileSheet.crop(128, 192, 64, 64);
        waterProjectileDown[3] = waterProjectileSheet.crop(192, 192, 64, 64);
        
        lightningProjectile = new BufferedImage[4];
        lightningProjectile[0]= windLightningProjectileSheet.crop(0, 0, 64, 64);
        lightningProjectile[1]= windLightningProjectileSheet.crop(64, 0, 64, 64);
        lightningProjectile[2]= windLightningProjectileSheet.crop(128, 0, 64, 64);
        lightningProjectile[3]= windLightningProjectileSheet.crop(192, 0, 64, 64);
        
        windProjectile = new BufferedImage[4];
        windProjectile[0]= windLightningProjectileSheet.crop(0, 64, 64, 64);
        windProjectile[1]= windLightningProjectileSheet.crop(64, 64, 64, 64);
        windProjectile[2]= windLightningProjectileSheet.crop(128, 64, 64, 64);
        windProjectile[3]= windLightningProjectileSheet.crop(192, 64, 64, 64);
        
        bossLeft = new BufferedImage[3];
        bossLeft[0] = bossSheet.crop(0,0,PLAYERWIDTH,PLAYERHEIGHT);
        bossLeft[1] = bossSheet.crop(PLAYERWIDTH,0,PLAYERWIDTH,PLAYERHEIGHT);
        bossLeft[2] = bossSheet.crop(PLAYERWIDTH*2,0,PLAYERWIDTH,PLAYERHEIGHT);
        
        bossRight = new BufferedImage[3];
        bossRight [0] = bossSheet.crop(0,PLAYERHEIGHT,PLAYERWIDTH,PLAYERHEIGHT);
        bossRight [1] = bossSheet.crop(PLAYERWIDTH,PLAYERHEIGHT,PLAYERWIDTH,PLAYERHEIGHT);
        bossRight [2] = bossSheet.crop(PLAYERWIDTH*2,PLAYERHEIGHT,PLAYERWIDTH,PLAYERHEIGHT);
        
        bossAttackLeft = new BufferedImage[3];
        bossAttackLeft [2] = bossSheet.crop(0,PLAYERHEIGHT*2,PLAYERWIDTH,PLAYERHEIGHT);
        bossAttackLeft [1] = bossSheet.crop(PLAYERWIDTH,PLAYERHEIGHT*2,PLAYERWIDTH,PLAYERHEIGHT);
        bossAttackLeft [0] = bossSheet.crop(PLAYERWIDTH*2,PLAYERHEIGHT*2,PLAYERWIDTH,PLAYERHEIGHT);
        
        bossAttackRight = new BufferedImage[3];
        bossAttackRight[2] = bossSheet.crop(0,PLAYERHEIGHT*3,PLAYERWIDTH,PLAYERHEIGHT);
        bossAttackRight[1] = bossSheet.crop(PLAYERWIDTH,PLAYERHEIGHT*3,PLAYERWIDTH,PLAYERHEIGHT);
        bossAttackRight[0] = bossSheet.crop(PLAYERWIDTH*2,PLAYERHEIGHT*3,PLAYERWIDTH,PLAYERHEIGHT);
        
        
//grass, dirt, cliff, sand, water, plant, deepWater, plank, rock
        grass  = tileSheet.crop(0, 0, WIDTH, HEIGHT);
        dirt  = tileSheet.crop(WIDTH,0, WIDTH, HEIGHT);
        cliff = tileSheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        sand = tileSheet.crop(WIDTH*3, 0 , WIDTH, HEIGHT);
        water = tileSheet.crop(WIDTH*4, 0, WIDTH, HEIGHT);
        plant = tileSheet.crop(WIDTH*3, HEIGHT, WIDTH, HEIGHT);
        deepWater = tileSheet.crop(WIDTH*4, HEIGHT, WIDTH, HEIGHT);
        plank = tileSheet.crop(WIDTH*2, HEIGHT*2, WIDTH, HEIGHT);
        rock = tileSheet.crop(WIDTH*3, HEIGHT*2, WIDTH, HEIGHT);
        
        homePortal = new BufferedImage[3];
        homePortal[0] = portalSheet.crop(0, 0, WIDTH, HEIGHT);
        homePortal[1] = portalSheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        homePortal[2] = portalSheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        
        portal1 = new BufferedImage[3];
        portal1[0] = portalSheet.crop(0, HEIGHT, WIDTH, HEIGHT);
        portal1[1] = portalSheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
        portal1[2] = portalSheet.crop(WIDTH*2, HEIGHT, WIDTH, HEIGHT);
        
        portal2 = new BufferedImage[3];
        portal2[0] = portalSheet.crop(0, HEIGHT*2, WIDTH, HEIGHT);
        portal2[1] = portalSheet.crop(WIDTH, HEIGHT*2, WIDTH, HEIGHT);
        portal2[2] = portalSheet.crop(WIDTH*2, HEIGHT*2, WIDTH, HEIGHT);
        
        portal3 = new BufferedImage[3];
        portal3[0] = portalSheet.crop(0, HEIGHT*3, WIDTH, HEIGHT);
        portal3[1] = portalSheet.crop(WIDTH, HEIGHT*3, WIDTH, HEIGHT);
        portal3[2] = portalSheet.crop(WIDTH*2, HEIGHT*3, WIDTH, HEIGHT);
        
        waveLeft = new BufferedImage[3];
        waveLeft[0] = tileSheet.crop(WIDTH, HEIGHT*2, WIDTH, HEIGHT);
        waveLeft[1] = tileSheet.crop(WIDTH, HEIGHT*3, WIDTH, HEIGHT);
        waveLeft[2] = tileSheet.crop(WIDTH, HEIGHT*4, WIDTH, HEIGHT);
        
        waveRight = new BufferedImage[3];
        waveRight[0] = tileSheet.crop(0, HEIGHT*2, WIDTH, HEIGHT);
        waveRight[1] = tileSheet.crop(0, HEIGHT*3, WIDTH, HEIGHT);
        waveRight[2] = tileSheet.crop(0, HEIGHT*4, WIDTH, HEIGHT);
        
        waveUp = new BufferedImage[3];
        waveUp[0] = tileSheet.crop(WIDTH*2, HEIGHT*4, WIDTH, HEIGHT);
        waveUp[1] = tileSheet.crop(WIDTH*3, HEIGHT*4, WIDTH, HEIGHT);
        waveUp[2] = tileSheet.crop(WIDTH*4, HEIGHT*4, WIDTH, HEIGHT);
        
        waveDown = new BufferedImage[3];
        waveDown[0] = tileSheet.crop(WIDTH*2, HEIGHT*3, WIDTH, HEIGHT);
        waveDown[1] = tileSheet.crop(WIDTH*3, HEIGHT*3, WIDTH, HEIGHT);
        waveDown[2] = tileSheet.crop(WIDTH*4, HEIGHT*3, WIDTH, HEIGHT);
        
        coconut = treeSheet.crop(64*2,0,64,64);
        banana = treeSheet.crop(0, 0, 64, 64);
        tree = treeSheet.crop(64, 0, 64, 64);
        torch = sheet.crop(0, HEIGHT*5,WIDTH,HEIGHT);
        dust = sheet.crop(WIDTH*7, HEIGHT*10, WIDTH, HEIGHT);
        
        kubo1 = structureSheet.crop(300,0,100,100);
        kubo2 = structureSheet.crop(200, 0, 100, 100);
        kubo3 = structureSheet.crop(0, 0, 100, 100);
        ship = structureSheet.crop(100, 0 , 100, 100);
        
    }
    
}
