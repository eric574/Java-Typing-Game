import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Allow the user to choose which character they want to play with
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Ex_Start_Screen extends World {
    
    private Button back, choose_character, difficulty_levels, game_modes;
    private GreenfootImage background;
    private Sound_Info sound_info;
    private Ship_Sprite player;
    private Background_Info background_info;
    int worldWidth = getWidth();
    int worldHeight = getHeight();
    private String difflevel, game_mode;
    
    /**
     * 1st Constructor for objects of class Ex_Start_Screen.
     * 
     */
    public Ex_Start_Screen (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = diff;
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(getWidth(), getHeight());
        int xcoor = 0, ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        back = new Button("back", new GreenfootImage("back_button.png"));
        game_modes = new Button("game_modes", new GreenfootImage("game_modes_button.png"));
        addObject(game_modes, 400, getHeight() / 2);
        addObject(back, 400, 550);
    }
    
    /**
     * 2nd Constructor for objects of class Ex_Start_Screen
     */
    public Ex_Start_Screen (Sound_Info sound, Ship_Sprite user, Background_Info info) {
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
        game_modes = new Button("game_modes", new GreenfootImage("game_modes_button.png"));
        addObject(game_modes, 400, getHeight() / 2);
        addObject(back, 400, 550);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (Greenfoot.mouseClicked(back)) {
            Greenfoot.setWorld(new Intro_Screen(difflevel, game_mode, sound_info, player, background_info));
        }
        else if (Greenfoot.mouseClicked(game_modes)) {
            Greenfoot.setWorld(new Game_Modes(difflevel, game_mode, sound_info, player, background_info));
        }
   }
}
