import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display the controls screen
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Controls extends World {

    private Sound_Info sound_info;
    private GreenfootImage background;
    private Button back;
    private Ship_Sprite player;
    private Background_Info background_info;
    int worldWidth = getWidth();
    int worldHeight = getHeight();
    private String difflevel, game_mode;
    
    /**
     * Constructor for objects of class Controls.
     * 
     */
    public Controls (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = diff;
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(getWidth(), getHeight());
        int worldWidth = getWidth();
        int worldHeight = getHeight();
        int xcoor = 0, ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        back = new Button("back", new GreenfootImage("back_button.png"));
        addObject(back, 412, 650);
        Display();
    }
    
    private void Display () {
        int size = 25;
        addObject(new Text("Instructions", 40, "white"), worldWidth/2, (worldHeight/3)-100);
        addObject(new Text("Type the words displayed on the enemies, by using the keyboard.", 23, "white"), worldWidth/2, 200);
        addObject(new Text("You can't use the backspace key, so type carefully!", 23, "white"), worldWidth/2, 300);
        addObject(new Text("Finish typing before the enemies touch you.", 23, "white"), worldWidth/2, 400);
        addObject(new Text("If you get touched you die, so stay alive!", 23, "white"), worldWidth/2, 500);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
    */
    public void act () {
        if (Greenfoot.mouseClicked(back)) Greenfoot.setWorld(new Intro_Screen(difflevel, game_mode, sound_info, player, background_info)); // Set the Menu screen when the back button is pressed
    }
}
