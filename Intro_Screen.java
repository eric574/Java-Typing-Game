import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This World class is used as the intro/starting screen
 * 
 * @author Eric Liu, Justin C
 * @version March 2, 2018
 */
public class Intro_Screen extends World {

    private Button start, settings, controls, introduction, credits;
    private GreenfootImage background;
    private int xcoor, ycoor;
    private Sound_Info sound_info = new Sound_Info(1, 50);
    private Ship_Sprite player = new Ship_Sprite(1, new GreenfootImage("sprite1.png"));
    private Background_Info background_info = new Background_Info("background.png");
    GreenfootImage image = new GreenfootImage("title.png");
    int worldWidth = getWidth();
    int worldHeight = getHeight();
    private String difflevel, game_mode;
    
    /**
     * 1st Constructor for objects of class Intro_Screen.
     * 
     */
    public Intro_Screen (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = diff;
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(getWidth(), getHeight());
        // Adjust these coordinates accordingly
        xcoor = 0;
        ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        start = new Button("start", new GreenfootImage("start_button.png"));
        settings = new Button("settings", new GreenfootImage("settings_button.png"));
        controls = new Button("controls", new GreenfootImage("controls_button.png"));
        introduction = new Button("introduction", new GreenfootImage("introduction_button.png"));
        credits = new Button("credits", new GreenfootImage("credits_button.png")); // Add credits where credits is due
        
        addObject(start, worldWidth/2, 250);
        addObject(settings, worldWidth/2, 350);
        addObject(introduction, worldWidth/2, 450);
        addObject(controls, worldWidth/2, 550);
        addObject(credits, worldWidth / 2, 650); 
        addObject(new Text("Space Typists", 40, "green"), worldWidth/2, 100);
        addObject(new Text("By: Eric Liu, Benjamin Wen, Justin Chan, Willson Ye", 25, "yellow"), worldWidth/2, 175);
    }
    
    public Intro_Screen () {
        super(800, 700, 1);
        background = new GreenfootImage(background_info.getType());
        background.scale(getWidth(), getHeight());
        // Adjust these coordinates accordingly
        xcoor = 0;
        ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        start = new Button("start", new GreenfootImage("start_button.png"));
        settings = new Button("settings", new GreenfootImage("settings_button.png"));
        controls = new Button("controls", new GreenfootImage("controls_button.png"));
        introduction = new Button("introduction", new GreenfootImage("introduction_button.png"));
        credits = new Button("credits", new GreenfootImage("credits_button.png")); // Add credits where credits is due
        
        addObject(start, worldWidth/2, 250);
        addObject(settings, worldWidth/2, 350);
        addObject(introduction, worldWidth/2, 450);
        addObject(controls, worldWidth/2, 550);
        addObject(credits, worldWidth / 2, 650); 
        addObject(new Text("Space Typists", 40, "green"), worldWidth/2, 100);
        addObject(new Text("By: Eric Liu, Benjamin Wen, Justin Chan, Willson Ye", 25, "yellow"), worldWidth/2, 175);
    }
    
    public Intro_Screen (Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(getWidth(), getHeight());
        // Adjust these coordinates accordingly
        xcoor = 0;
        ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        start = new Button("start", new GreenfootImage("start_button.png"));
        settings = new Button("settings", new GreenfootImage("settings_button.png"));
        controls = new Button("controls", new GreenfootImage("controls_button.png"));
        introduction = new Button("introduction", new GreenfootImage("introduction_button.png"));
        credits = new Button("credits", new GreenfootImage("credits_button.png")); // Add credits where credits is due
        
        addObject(start, worldWidth/2, 250);
        addObject(settings, worldWidth/2, 350);
        addObject(introduction, worldWidth/2, 450);
        addObject(controls, worldWidth/2, 550);
        addObject(credits, worldWidth / 2, 650); 
        addObject(new Text("Space Typists", 40, "green"), worldWidth/2, 100);
        addObject(new Text("By: Eric Liu, Benjamin Wen, Justin Chan, Willson Ye", 25, "yellow"), worldWidth/2, 175);
    }

    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (Greenfoot.mouseClicked(start)) // If the start button is pressed
            Greenfoot.setWorld(new Ex_Start_Screen(difflevel, game_mode, sound_info, player, background_info)); // Start a new game (refers to the Game_Modes subclass
        else if (Greenfoot.mouseClicked(settings)) // If the settings button is pressed
            Greenfoot.setWorld(new Settings_Screen(difflevel, game_mode, sound_info, player, background_info)); // Start a new Settings screen
        else if (Greenfoot.mouseClicked(controls)) // Else if the controls button is pressed
            Greenfoot.setWorld(new Controls(difflevel, game_mode, sound_info, player, background_info)); // Start a new controls screen (refers to the Controls subclass)
        else if (Greenfoot.mouseClicked(introduction)) // Else if the introduction button is pressed
            Greenfoot.setWorld(new Introduction(difflevel, game_mode, sound_info, player, background_info));
        else if (Greenfoot.mouseClicked(credits)) // Else if credits button is pressed
            Greenfoot.setWorld(new Credits(difflevel, game_mode, sound_info, player, background_info));
    }
}
