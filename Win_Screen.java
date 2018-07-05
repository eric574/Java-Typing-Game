import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen that is displayed if the player has won
 * 
 * @author Eric Liu
 * @version April 17, 2018
 */

public class Win_Screen extends World {

    private GreenfootImage background;
    private Button view_scoreboard, restart, exit;
    private GreenfootSound win; // Winning/victory sound
    private boolean played = false; // For only playing the sound once
    private String difflevel, game_mode;
    private Sound_Info sound_info;
    private Ship_Sprite player;
    private Background_Info background_info;
    
    /**
     * 
     * Constructor for objects of class Win_Screen.
     * 
     */
    public Win_Screen (String level, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        // NOTE: Same background as the Game-Over one
        background = new GreenfootImage("game_over.png");
        background.scale(background.getWidth() + 800, background.getHeight() + 700);
        getBackground().drawImage(background, 0, 0);
        addObject(new Text("YOU WIN!!!", 30, "white"), 400, 150);
        restart = new Button("restart", new GreenfootImage("restart_button.png"));
        exit = new Button("exit", new GreenfootImage("exit_button.png"));
        view_scoreboard = new Button("scoreboard", new GreenfootImage("score_board.png"));
        addObject(view_scoreboard, 400, 300);
        addObject(restart, 400, 400);
        addObject(exit, 400, 500);
        win = new GreenfootSound("victory.mp3");
        win.setVolume(50);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (!played) {
            win.play();
            played = true;
        }
        if (Greenfoot.mouseClicked(restart)) {
            win.stop();
            Greenfoot.setWorld(new Main_Game(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(view_scoreboard)) {
            win.stop();
            Greenfoot.setWorld(new Scoreboard(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
        }
        if (Greenfoot.mouseClicked(exit)) {
            win.stop();
            Greenfoot.setWorld(new Intro_Screen(this.difflevel, this.game_mode, this.sound_info, this.player, this.background_info));
            Greenfoot.stop();
        }
   }
}
