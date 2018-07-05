import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Allow the user to change the background used for the main game
 * 
 * @author Eric Liu
 * @version March 19, 2018
 */
public class Background_Settings extends World {

    private Button back, background_pic1, background_pic2, background_pic3;
    private Background_Info background_info;
    private GreenfootImage background;
    private Sound_Info sound_info;
    private Ship_Sprite player;
    int worldWidth = getWidth(); //grab width & height
    int worldHeight = getHeight();
    private String difflevel, game_mode;
    
    /**
     * Constructor for objects of class Background_Settings.
     * 
     */
    public Background_Settings (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = diff;
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(getWidth(), getHeight());
        getBackground().drawImage(background, 0, 0);
        back = new Button("back", new GreenfootImage("back_button.png"));
        background_pic1 = new Button("pic1", new GreenfootImage("background_pic1_button.png"));
        background_pic2 = new Button("pic2", new GreenfootImage("background_pic2_button.png"));
        background_pic3 = new Button("pic3", new GreenfootImage("background_pic3_button.png"));
        addObject(background_pic1, 410, 290);
        addObject(background_pic2, 410, 390);
        addObject(background_pic3, 410, 490);
        addObject(back, 410, 610);
        
        // Display the text
        addObject(new Text("Choose your background", 25, "white"), 400, 100);
    }
    
    private void Display_Background1 () { //buttons for changing background
        removeObjects(getObjects(Text.class));
        addObject(new Text("Choose your background", 25, "white"), 400, 100);
        addObject(new Text("You have chosen Background 1!", 20, "white"), 400, 200);
    }
    
    private void Display_Background2 () {
        removeObjects(getObjects(Text.class));
        addObject(new Text("Choose your background", 25, "white"), 400, 100);
        addObject(new Text("You have chosen Background 2!", 20, "white"), 400, 200);
    }
    
    private void Display_Background3 () {
        removeObjects(getObjects(Text.class));
        addObject(new Text("Choose your background", 25, "white"), 400, 100);
        addObject(new Text("You have chosen Background 3!", 20, "white"), 400, 200);
    }
    
    /**
     * Act - do whatever the Word_Manager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
       if (Greenfoot.mouseClicked(background_pic1)) {
           Display_Background1();
           background_info = new Background_Info("background.png");           
       }
       if (Greenfoot.mouseClicked(background_pic2)) {
           Display_Background2();
           background_info = new Background_Info("space1.png");           
       }
       if (Greenfoot.mouseClicked(background_pic3)) {
           Display_Background3();
           background_info = new Background_Info("space2.png");           
       }
       if (Greenfoot.mouseClicked(back)) {
           Greenfoot.setWorld(new Settings_Screen(difflevel, game_mode, sound_info, player, background_info));
       }
    }
}
