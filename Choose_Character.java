import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This world class is used to allow the user to choose the character to play with
 * 
 * @author Eric Liu
 * @version March 8, 2018
 */
public class Choose_Character extends World {

    private Button player1, player2, player3, player4, player5, back;
    private Ship_Sprite sprite1, sprite2, sprite3, sprite4, sprite5;
    private Ship_Sprite player;
    private Sound_Info sound_info;
    private GreenfootImage background;
    private Background_Info background_info;
    int x = getWidth(), y = getHeight();
    private String difflevel = "", game_mode = "";
    
    /**
     * Constructor for objects of class Choose_Character.
     * 
     */
    public Choose_Character (String level, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.sound_info = sound;
        this.player = user;
        this.difflevel = level;
        this.game_mode = mode;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(800, 700);
        int xcoor = 0, ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        player1 = new Button("Player 1", new GreenfootImage("sprite1_button.png"));
        player2 = new Button("Player 2", new GreenfootImage("sprite2_button.png"));
        player3 = new Button("Player 3", new GreenfootImage("sprite3_button.png"));
        player4 = new Button("Player 4", new GreenfootImage("sprite4_button.png"));
        player5 = new Button("Player 5", new GreenfootImage("sprite5_button.png"));
        back = new Button("back", new GreenfootImage("back_button.png"));
        
        sprite1 = new Ship_Sprite(1, new GreenfootImage("sprite1.png"));
        sprite2 = new Ship_Sprite(2, new GreenfootImage("sprite2.png"));
        sprite3 = new Ship_Sprite(3, new GreenfootImage("sprite3.png"));
        sprite4 = new Ship_Sprite(4, new GreenfootImage("sprite4.png"));
        sprite5 = new Ship_Sprite(5, new GreenfootImage("sprite5.png"));
        addObject(new Text("Choose your character", 25, "white"), 400, 100);
        addObject(player1, 90, 420); //hard coded locations
        addObject(player2, 240, 420);
        addObject(player3, 390, 420);
        addObject(player4, 540, 420);
        addObject(player5, 690, 420);
 
        addObject(sprite1, 90, 370);
        addObject(sprite2, 240, 370);
        addObject(sprite3, 390, 370);
        addObject(sprite4, 540, 370);
        addObject(sprite5, 690, 370);
        
        addObject(back, 413, 650);
        // Add the instructions
        addObject(new Text("Choose a player by pressing the button.", 20, "white"), 400, 250);
    }
    
    private void Display_Player1 () {
        removeObjects(getObjects(Text.class)); //call remove objects
        addObject(new Text("Choose your character", 25, "white"), 400, 100);
        addObject(new Text("You have chosen Player 1!", 20, "white"), 400, 250);
        addObject(new Text("Now starting the game!!!", 20, "white"), 400, 300);
    }
    
    private void Display_Player2 () {
        removeObjects(getObjects(Text.class));
        addObject(new Text("Choose your character", 25, "white"), 400, 100);
        addObject(new Text("You have chosen Player 2!", 20, "white"), 400, 250);
        addObject(new Text("Now starting the game!!!", 20, "white"), 400, 300);
    }
    
    private void Display_Player3 () {
        removeObjects(getObjects(Text.class));
        addObject(new Text("Choose your character", 25, "white"), 400, 100);
        addObject(new Text("You have chosen Player 3!", 20, "white"), 400, 250);
        addObject(new Text("Now starting the game!!!", 20, "white"), 400, 300);
    }
    
    private void Display_Player4 () {
        removeObjects(getObjects(Text.class));
        addObject(new Text("Choose your character", 25, "white"), 400, 100);
        addObject(new Text("You have chosen Player 4!", 20, "white"), 400, 250);
        addObject(new Text("Now starting the game!!!", 20, "white"), 400, 300);
    }
    
    private void Display_Player5 () {
        removeObjects(getObjects(Text.class));
        addObject(new Text("Choose your character", 25, "white"), 400, 100);
        addObject(new Text("You have chosen Player 5!", 20, "white"), 400, 250);
        addObject(new Text("Now starting the game!!!", 20, "white"), 400, 300);
    }
    
    private long lastTime = System.currentTimeMillis();
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        long currTime = System.currentTimeMillis();
        if (Greenfoot.mouseClicked(player1)) {
            player = new Ship_Sprite(1, new GreenfootImage("sprite1.png"));
            Display_Player1();
            Greenfoot.setWorld(new Main_Game(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(player2)) {
            player = new Ship_Sprite(2, new GreenfootImage("sprite2.png"));
            Display_Player2();
            Greenfoot.setWorld(new Main_Game(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(player3)) {
            player = new Ship_Sprite(3, new GreenfootImage("sprite3.png"));
            Display_Player3();
            Greenfoot.setWorld(new Main_Game(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(player4)) {
            player = new Ship_Sprite(4, new GreenfootImage("sprite4.png"));
            Display_Player4();
            Greenfoot.setWorld(new Main_Game(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(player5)) {
            player = new Ship_Sprite(5, new GreenfootImage("sprite5.png"));
            Display_Player5();
            Greenfoot.setWorld(new Main_Game(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(back)) {
            Greenfoot.setWorld(new Difficulty_Levels(difflevel, game_mode, sound_info, player, background_info));
        }
   }
}
