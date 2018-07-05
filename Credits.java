import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen used to show the credits (each member's contributions)
 * 
 * @author Eric Liu
 * @version April 15, 2018
 */

public class Credits extends World {

    private Button back;
    private String difflevel, game_mode;
    private Sound_Info sound_info = new Sound_Info(1, 50);
    private Ship_Sprite player = new Ship_Sprite(1, new GreenfootImage("sprite1.png"));
    private Background_Info background_info = new Background_Info("background.png");
    private GreenfootImage background; // Background image
    
    /**
     * Constructor for objects of class Credits.
     * 
     */
    public Credits (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {
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
        int xcoor = 0;
        int ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        back = new Button("back_button", new GreenfootImage("back_button.png"));
        addObject(back, 400, 650);
        Display();
    }
    
    /**
     * Display the credits (each group member's contributions)
     */
    public void Display () {
        // Hardcode the text into place
        addObject(new Text("Purpose: To give credits where credits is due", 23, "white"), 400, 100);
        addObject(new Text("- Eric: ", 20, "white"), 100, 200);
        addObject(new Text("Programmed about 98% of the game, including", 17, "white"), 400, 200);
        addObject(new Text("the game logic, all of the classes and incorporated", 17, "white"), 400, 230);
        addObject(new Text("the sounds and sprites. Also fixed a lot of bugs.", 17, "white"), 400, 260);
        
        addObject(new Text("- Benjamin: ", 20, "white"), 110, 360);
        addObject(new Text("Incorporated the blast sprites to fix the blast orientation", 17, "white"), 410, 360);
        addObject(new Text("bug. Also fixed the button clicked bugs and tested for bugs.", 17, "white"), 420, 390);
        
        addObject(new Text("- Justin: ", 20, "white"), 100, 490);
        addObject(new Text("Added the background sprites, designed the game,", 17, "white"), 430, 490);
        addObject(new Text("and changed the instructions and introduction text.", 17, "white"), 415, 520);
        
        addObject(new Text("- Willson: ", 20, "white"), 100, 600);
        addObject(new Text("...................................................", 30, "white"), 400, 600);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (Greenfoot.mouseClicked(back)) Greenfoot.setWorld(new Intro_Screen(difflevel, game_mode, sound_info, player, background_info)); // Start a new game (refers to the Game_Modes subclass
        
    }
}
