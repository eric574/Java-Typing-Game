import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.*;

/**
 * Display the settings screen
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Settings_Screen extends World {

    private GreenfootImage background;
    private Button back, music_settings, background_settings, choose_character, reset_scores;
    private Sound_Info sound_info;
    private Ship_Sprite player;
    private Background_Info background_info;
    private String difflevel, game_mode;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    
    /**
     * Constructor for objects of class Settings_Screen.
     * 
     */
    public Settings_Screen (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
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
        music_settings = new Button("music_settings", new GreenfootImage("music_settings_button.png"));
        back = new Button("back", new GreenfootImage("back_button.png"));
        background_settings = new Button("background_settings", new GreenfootImage("background_settings_button.png"));
        choose_character = new Button("choose_character", new GreenfootImage("choose-character_button.png"));
        reset_scores = new Button("reset_scores", new GreenfootImage("reset_scores_button.png"));
        addObject(background_settings, 412, 260);
        addObject(music_settings, 412, 360);
        addObject(reset_scores, 412, 460);
        addObject(back, 412, 570);
    }
    
    /**
     * Method is used to indicate that the user has resetted the highscores/scoreboard
     */
    public void Display_Reset_Scores () {
        removeObjects(getObjects(Text.class));
        addObject(new Text("You have cleared the scoreboard!", 20, "white"), 412, 200);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (Greenfoot.mouseClicked(background_settings)) Greenfoot.setWorld(new Background_Settings(difflevel, game_mode, sound_info, player, background_info));
        else if (Greenfoot.mouseClicked(music_settings)) Greenfoot.setWorld(new Music_Settings(difflevel, game_mode, sound_info, player, background_info));
        else if (Greenfoot.mouseClicked(reset_scores)) {
            Display_Reset_Scores();
            // Now reset the scores
            try {
                fileWriter = new FileWriter("scores.txt", false); // Overwrite and clear the data
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(""); // Clear everything
            }
            catch (IOException ex) {
                System.out.println("Error writing to file 'scores.txt'");
            }
        }
        else if (Greenfoot.mouseClicked(back)) Greenfoot.setWorld(new Intro_Screen(difflevel, game_mode, sound_info, player, background_info));
   }
}
