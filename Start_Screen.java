import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display the difficulty levels screen once the user presses the start button
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Start_Screen extends World {

    private Button easy, medium, hard, back;
    private GreenfootImage background;
    private Sound_Info sound_info;
    private Ship_Sprite player;
    private Background_Info background_info;
    int worldWidth = getWidth();
    int worldHeight = getHeight();
    private String difflevel, game_mode;
    
    /**
     * Constructor for objects of class Difficulty_Levels.
     */
    public Start_Screen (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
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
        easy = new Button("easy", new GreenfootImage("easy_mode.png"));
        medium = new Button("medium", new GreenfootImage("medium_mode.png"));
        hard = new Button("hard", new GreenfootImage("hard_mode.png"));
        back = new Button("back", new GreenfootImage("back_button.png"));
        addObject(easy, worldWidth/2, 310);
        addObject(medium, worldWidth/2, 390);
        addObject(hard, worldWidth/2, 470);
        addObject(back, worldWidth/2, 650);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (Greenfoot.mouseClicked(easy)) Greenfoot.setWorld(new Main_Game("easy", game_mode, sound_info, player, background_info));
        else if (Greenfoot.mouseClicked(medium)) Greenfoot.setWorld(new Main_Game("medium", game_mode, sound_info, player, background_info));
        else if (Greenfoot.mouseClicked(hard)) Greenfoot.setWorld(new Main_Game("hard", game_mode, sound_info, player, background_info));
        else if (Greenfoot.mouseClicked(back)) Greenfoot.setWorld(new Intro_Screen(difflevel, game_mode, sound_info, player, background_info));
   }
}
