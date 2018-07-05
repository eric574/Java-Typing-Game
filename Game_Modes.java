import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen to display the two kinds of game modes - blitz and blitzkrieg
 * 
 * @author Eric Liu
 * @version April 15, 2018
 */

public class Game_Modes extends World {

    private Button back, blitz, blitzkrieg;
    private GreenfootImage background;
    private Sound_Info sound_info;
    private Ship_Sprite player;
    private Background_Info background_info;
    int worldWidth = getWidth();
    int worldHeight = getHeight();
    private String difflevel, game_mode;
    
    /**
     * 1st Constructor for objects of class Game_Modes.
     * 
     */
    public Game_Modes (String level, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1); 
        this.difflevel = level; //initialize variables
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(getWidth(), getHeight());
        int xcoor = 0, ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        back = new Button("back", new GreenfootImage("back_button.png"));
        blitz = new Button("blitz", new GreenfootImage("blitz-mode_button.png"));
        blitzkrieg = new Button("blitzkrieg", new GreenfootImage("blitzkrieg-mode_button.png"));
        addObject(blitz, 400, 280);
        addObject(blitzkrieg, 400, 400);
        addObject(back, 400, 550);
        addObject(blitz, worldWidth/2, 280);
        addObject(blitzkrieg, worldWidth/2, 400);
        addObject(back, worldWidth/2, 550);
    }
    
    /**
     * 2nd Constructor for objects of class Game_Modes.
     * 
     */
    public Game_Modes (Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1); 
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(getWidth(), getHeight());
        int xcoor = 0, ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        back = new Button("back", new GreenfootImage("back_button.png"));
        blitz = new Button("blitz", new GreenfootImage("blitz-mode_button.png"));
        blitzkrieg = new Button("blitzkrieg", new GreenfootImage("blitzkrieg-mode_button.png"));
        addObject(blitz, 412, 280);
        addObject(blitzkrieg, 412, 400);
        addObject(back, 400, 550);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () { //buttons
        if (Greenfoot.mouseClicked(back)) {
            Greenfoot.setWorld(new Ex_Start_Screen(difflevel, game_mode, sound_info, player, background_info));
        }
        else if (Greenfoot.mouseClicked(blitz)) {
            this.game_mode = "1";
            Greenfoot.setWorld(new Difficulty_Levels(difflevel, game_mode, sound_info, player, background_info));
        }
        else if (Greenfoot.mouseClicked(blitzkrieg)) {
            this.game_mode = "2";
            Greenfoot.setWorld(new Difficulty_Levels(difflevel, game_mode, sound_info, player, background_info));
        }
   }
}
