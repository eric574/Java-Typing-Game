import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.*;

/**
 * Class is used to display scoreboard/all-time leaderboard
 * 
 * @author Eric Liu
 * @version March 2, 2018
 */

public class Scoreboard extends World {

    private String filename = "scores.txt";
    private Reading_and_Writing_Text_Files reader = new Reading_and_Writing_Text_Files(filename);
    private ArrayList<Player_Info> scoreslist = new ArrayList<Player_Info>();
    private GreenfootImage background = new GreenfootImage("game_over.png");
    private Player_Info player;
    private Button back;
    private String difflevel, game_mode;
    private Sound_Info sound_info;
    private Ship_Sprite player_sprite;
    private Background_Info background_info;
    private Scanner sc = null; // Scanner object
    
    /**
     * Constructor for objects of class Scoreboard.
     * 
     */
    public Scoreboard (String level, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = level;
        this.game_mode = mode;
        this.sound_info = sound;
        this.player_sprite = user;
        this.background_info = info;
        background.scale(getWidth(), getHeight());
        getBackground().drawImage(background, 0, 0);
        back = new Button("back", new GreenfootImage("back_button.png"));
        addObject(back, 400, 600);
        // GetScores();
        GetScores1(); // Use the Scanner version
        Display(); // Display the scoreboard/leaderboard
    }
    
    /*
     * @ param filename: The name of the text file - make sure that this is correct
     * @ returns A Scanner object that you can use to read the content of the text file
     */
    public Scanner getScanner (String filename) {
        InputStream myFile = getClass().getResourceAsStream(filename);
        if (myFile != null) return new Scanner(myFile);
        return null;
    }
    
    /**
     * Second method to get the scores using Java's Scanner
     */
    private void GetScores1 () {
        int idx1 = 0, idx2 = 1, idx3 = 2; // Initialize variables
        String nextline = null;
        try {
            if (sc == null) sc = getScanner(filename);
            while (sc.hasNextLine()) {
                String tokens[] = sc.nextLine().split(", ");
                scoreslist.add(new Player_Info(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
            }
            // Always close the scanner object(s)
            if (sc != null) sc.close();
        }
        catch (InputMismatchException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Method gets the scores using Java's FileReader and BufferedReader
     */
    private void GetScores () {
        int idx1 = 0, idx2 = 1, idx3 = 2; //initialize variables
        String nextline = null;
        try {
            FileReader fileReader = new FileReader(filename); //readfiles
            BufferedReader br = new BufferedReader(fileReader);
            while ((nextline = br.readLine()) != null) {
                String tokens[] = nextline.split(", ");
                scoreslist.add(new Player_Info(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
            }
            br.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");
        }
        catch (IOException ex) {
            System.out.println("Error while reading file '" + filename + "'");
        }
    }
    
    private void Display () {
        addObject(new Text("All Time Leaderboard", 25, "white"), 400, 20); //display
        int idx = 1; // For numbering the ranks
        int xcoor = 150;
        int ycoor = 100;
        // Sort from greatest to least
        Collections.sort(scoreslist);
        // We can also generate only the top 10 scores alternatively
        for (Player_Info scores : scoreslist) {
            if (idx > 10) { // Break if it's greater than 10 scores
                break; 
            }
            String username = scores.getName();
            int scorenum = scores.getScore();
            int level = scores.getLevel();
            addObject(new Text("Player " + String.valueOf(idx) + ":", 20, "white"), xcoor, ycoor);
            addObject(new Text("Username: ", 20, "white"), xcoor + 120, ycoor);
            addObject(new Text(username, 20, "white"), xcoor + 240, ycoor);
            addObject(new Text("Level: ", 20, "white"), xcoor + 350, ycoor);
            addObject(new Text(String.valueOf(level), 20, "white"), xcoor + 380, ycoor);
            addObject(new Text("Score: ", 20, "white"), xcoor + 450, ycoor);
            addObject(new Text(String.valueOf(scorenum), 20, "white"), xcoor + 520, ycoor);
            ycoor += 50; // Shuold be 50 units apart
            idx++;
        }
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (Greenfoot.mouseClicked(back)) {
            Greenfoot.setWorld(new End_Screen(this.difflevel, this.game_mode, this.sound_info, this.player_sprite, this.background_info)); // Go back to the end screen
        }
   }
}
