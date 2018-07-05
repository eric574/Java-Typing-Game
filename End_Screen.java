import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For the end/gameover screen
 * 
 * @author Eric Liu
 * @version March 14, 2018
 */
public class End_Screen extends World {

    private Button back, view_scoreboard, restart, exit;
    private GreenfootImage background;
    private GreenfootSound gameover;
    private boolean played = false; // For only playing the sound once
    private String difflevel, game_mode;
    private Sound_Info sound_info;
    private Ship_Sprite player;
    private Background_Info background_info;
    
    /**
     * Constructor for objects of class End_Screen.
     * 
     */
    public End_Screen (String level, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = level;
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage("game_over.png");
        gameover = new GreenfootSound("gameover.mp3");
        background.scale(background.getWidth() + 800, background.getHeight() + 700);
        getBackground().drawImage(background, 0, 0);
        restart = new Button("restart", new GreenfootImage("restart_button.png"));
        exit = new Button("exit", new GreenfootImage("exit_button.png"));
        view_scoreboard = new Button("scoreboard", new GreenfootImage("score_board.png"));
        addObject(new Text("GAME OVER", 30, "white"), 400, 150);
        addObject(view_scoreboard, 400, 300);
        addObject(restart, 400, 400);
        addObject(exit, 400, 500);
        gameover.setVolume(50);
        // ((Main_Game) getObjects(Main_Game.class).get(0)).Stop_Songs(); // Stop songs from playing from main game
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (!played) {
            gameover.play();
            played = true;
        }
        if (Greenfoot.mouseClicked(restart)) {
            gameover.stop();
            Greenfoot.setWorld(new Main_Game(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(view_scoreboard)) {
            gameover.stop();
            Greenfoot.setWorld(new Scoreboard(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(exit)) {
            gameover.stop();
            Greenfoot.setWorld(new Intro_Screen(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
            Greenfoot.stop();
        }
   }
}
