import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display the difficulty levels screen once the user presses the start button
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Difficulty_Levels extends World {

    private Button easy, medium, hard, back;
    private GreenfootImage background;
    private Sound_Info sound_info;
    private Ship_Sprite player;
    private Background_Info background_info;
    private String difflevel, game_mode;
    
    /**
     * Constructor for objects of class Difficulty_Levels.
     * 
     */
    public Difficulty_Levels (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = diff;
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(800, 700);
        int xcoor = 0, ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        easy = new Button("easy", new GreenfootImage("easy_mode.png"));
        medium = new Button("medium", new GreenfootImage("medium_mode.png"));
        hard = new Button("hard", new GreenfootImage("hard_mode.png"));
        back = new Button("back", new GreenfootImage("back_button.png"));
        addObject(easy, 413, 310);
        addObject(medium, 413, 390);
        addObject(hard, 413, 470);
        addObject(back, 413, 650);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (Greenfoot.mouseClicked(easy)) {
            Greenfoot.setWorld(new Choose_Character("easy", this.game_mode, this.sound_info, this.player, this.background_info)); // Greenfoot.setWorld(new Main_Game("easy", sound_info, player, background_info));
            this.difflevel = "easy";
        }
        else if (Greenfoot.mouseClicked(medium)) {
            Greenfoot.setWorld(new Choose_Character("medium", this.game_mode, this.sound_info, this.player, this.background_info)); // Greenfoot.setWorld(new Main_Game("easy", sound_info, player, background_info));
            this.difflevel = "medium";
        }
        else if (Greenfoot.mouseClicked(hard)) {
            Greenfoot.setWorld(new Choose_Character("hard", this.game_mode, this.sound_info, this.player, this.background_info)); // Greenfoot.setWorld(new Main_Game("easy", sound_info, player, background_info));
            this.difflevel = "hard";
        }
        else if (Greenfoot.mouseClicked(back)) Greenfoot.setWorld(new Game_Modes(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info)); // Greenfoot.setWorld(new Ex_Start_Screen(sound_info, player, background_info));
   }
}
