import greenfoot.*;  // (World, Aleartor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane; // For input box

/**
 * The code for the main game, note that there are varying difficulty levels
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Main_Game extends World {

    private GreenfootImage background; // Background image
    private boolean paused = false; // Boolean for whether the game was paused(no longer needed)
    private boolean paused_pressed = false; // Boolean for whether the pause button was pressed (no longer needed)
    private GreenfootSound music; // Music for game
    private String difflevel = "", game_mode = ""; // Strings storing the value of the parameters
    private Player player_ship; // Main player object
    private int wordstyped = 0; // The number of words that were typed
    private int score = 0; // The score for the player
    private int level = 1; // Set to 1 initially
    private String username = ""; // Used to store the string username
    private Sound_Info sound; // Stores the sound information
    
    private ArrayList<String> adjectives = new ArrayList<String>(); // List of adjectives that are read from the file
    private ArrayList<String> nouns = new ArrayList<String>(); // List of nouns that are read from the file
    private ArrayList<String> verbs = new ArrayList<String>(); // List of verbs that are read from the file
    
    private Reading_and_Writing_Text_Files file1 = new Reading_and_Writing_Text_Files("scores.txt"); // Instantiate a new object for reading and writing scores
    private final String filename1 = "adjectives.txt"; // Source of adjectives file
    private final String filename2 = "nouns.txt"; // Source of nouns file
    private final String filename3 = "verbs.txt"; // Source of verbs file
    
    // These are for extracting the contents of the word .txt files (not actually used for processing the words!!!)
    private Reading_and_Writing_Text_Files file2 = new Reading_and_Writing_Text_Files(filename1); // Instantiate a new object for reading adjectives
    private Reading_and_Writing_Text_Files file3 = new Reading_and_Writing_Text_Files(filename2); // Instantiate a new object for reading nouns
    private Reading_and_Writing_Text_Files file4 = new Reading_and_Writing_Text_Files(filename3); // Instantiate a new object for reading verbs
    private Player_Info player_info; // Object to store player information
    private Ship_Sprite player_sprite; // Object for player sprite
    private Background_Info background_info; // Object to store the background sprite
    private Stack<String> sounds = new Stack<String>(); // Stack to store the upcoming songs
    private Queue<String> backgrounds = new Queue<String>(); // Stqck to store the upcoming backgrounds
    private Random rng = new Random(); // Instantiate a new rng object
    private boolean displayed_flag = false; // For displaying the count down
    
    // We can also use a HashSet to store the words just in case there's any duplicate words
    private HashSet<String> adjectives1 = new HashSet<String>(); // HashSet for storing a set of adjectives
    private HashSet<String> nouns1 = new HashSet<String>(); // HashSet for storing a set of nouns
    private HashSet<String> verbs1 = new HashSet<String>(); // HashSet for storing a set of verbs
    private HashSet<String> enemies_set = new HashSet<String>(); // HashSet for storing a set of enemies/words
    private HashSet<String> typed_set = new HashSet<String>(); // HashSet for storing all of the typed words
    
    // Alternatively, we can also use a queue/stack to store the words
    private Stack<Word> st = new Stack<Word>(); // Stack for storing words
    private Queue<String> q = new Queue<String>(); // Queue for storing strings
    private Queue<Word> enemies = new Queue<Word>(); // Queue for storing words/enemeis
    private Stack<Word> enemies_stack = new Stack<Word>(); // Can use a stack to store the word elements too
    
    private int timeElapsed = 1000; // Feel free to change this constant
    private boolean canFire = false; // For if the ship can fire a bullet or not, only true if the user has typed a character correctly
    private int word_speed = 1; // Speed for the movement of the ships and the words
    private int bullet_speed = 3; // Speed for the movement of the bullet of the ship
    private int num_enemies = 0; // Set the inital number of words to 5 to start off
    private int enemies_destroyed = 0; // Keep track of how many enemies/words were destroyed in total
    
    // Define all of the GreenfootSounds
    private GreenfootSound pause_sound = new GreenfootSound("pause.mp3"); // Pause sound
    private GreenfootSound level_up = new GreenfootSound("level_up.mp3"); // Levelling-up sound
    
    private boolean destroyed_enemies = false; // Has all the enemies been destroyed for the current level?
    private boolean word_finished = false; // Has the word been typed
    private boolean word_destroyed = false; // Has the word been destroyed
    private boolean typed_correctly = false; // Has the user typed correctly (from the word class)
    private boolean typed_correctly1 = false; // Has the user typed correctly (from the word_manager class)
    
    private Word_Display displayer = new Word_Display(); // Initialize the displayer class
    private Word_Manager word_manager; // Object for managing words
    private ArrayList<Word> words_list = new ArrayList<Word>(); // List to store words
    private ArrayList<Word> text_list = new ArrayList<Word>(); // Another list to store words
    
    private Button settings, restart, exit; // Settings, restart, and exit buttons
    private boolean easy = false, medium = false, hard = false; // Booleans for the difficulty levels
    private ArrayList<String> input = new ArrayList<String>(); // List for storing input as strings
    private int player_type = 0; // Keep track of player sprites
    private boolean enemy_destroyed = true; // Has all of the enemies been destroyed
    private boolean stop_firing = false; // Has the ship stopped firing
    private Word test = null; // Object used as a reference to Word class initially
    private int enemy_cnt = 0; // Keep track of how many enemies there are so far
    private boolean player_died = false; // Also boolean for whether the player has lost or not
    private String [] words_arr = new String[2400]; // There are at most 2400 elements -> used for toggling in between the words on screen
    private int idx = 0; // Index for the array
    private Button paused_button; // Pause button
    private final int typing_xcoor = 400, typing_ycoor = 350; // x and y coordinates for Write_Words method
    private int maxpausedelay = 20, currpausedelay = 20; // For controlling pause
    private boolean levelledup = false; // Has the user levelled-up
    private boolean inputted = false; // For if user has inputted a character
    private int curridx = 0; // For the current index in the array of Words
    private Word [] wordsarr; // The words array
    private boolean blitz = false, blitzkrieg = false; // Two booleans for which game mode was chosen
    private boolean colored = false; // Has the current word been colored green yet
    private boolean removed_bullets = false; // Has bullets been removed from the world
    private int score_deduct_num = 100; // Amount of points to deduct from the score, if the user types a character incorrectly
    private boolean deduct = false; // Boolean for whether or not the user's score should be deducted
    
    /**
     * Constructor for objects of class Main_Game.
     */
    public Main_Game (String level, String mode, Sound_Info sound_info, Ship_Sprite user, Background_Info info) {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = level;
        this.game_mode = mode;
        this.sound = sound_info;
        this.player_sprite = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        // Initialize the song
        if (sound.getSong() == 1) music = new GreenfootSound("Dark-Techno-City.mp3");
        else if (sound.getSong() == 2) music = new GreenfootSound("Factory-On-Mercury.mp3");
        else if (sound.getSong() == 3) music = new GreenfootSound("Night-Winds.mp3");
        else if (sound.getSong() == 4) music = new GreenfootSound("Trouble-On-Mercury.mp3");
        // Set sound volumes
        music.setVolume(sound.getVolume());
        level_up.setVolume(30); // Make sure it's quiet enough
        // Draw the background image
        background.scale(800, 700);
        int xcoor = 0;
        int ycoor = 0;
        setPaintOrder(Text.class, Button.class, Explosion.class, Player.class, Word.class, Bullet.class);
        getBackground().drawImage(background, xcoor, ycoor);
        // Push all of the Strings of sounds onto the stack
        GenerateSounds();
        // Enqueue all of the Strings of backgrounds into the queue (used for levelling)
        GenerateBackgrounds();
        
        // Set up all of the buttons
        settings = new Button("settings", new GreenfootImage("settings_button.png"));
        restart = new Button("restart", new GreenfootImage("restart_button.png"));
        exit = new Button("exit", new GreenfootImage("exit_button.png"));
        // paused_button = new Button("pause", new GreenfootImage("pause_button.png"));
        GreenfootImage pause_image = new GreenfootImage("pause_button.jpg");
        pause_image.scale(50, 50);
        paused_button = new Button("pause", pause_image);
        // Add the pause button to the right of the screen
        AskUsername(); // Ask player for username
        Set_Difficulty_Level(); // Set the difficulty level before starting
        Set_Game_Mode(); // Set the game mode before starting
        getReady(); // Get all of the words
        // Do preprocessing: generate 1000 enemies just in case
        /*for (int i=0; i<1000; i++) {
            generateEnemies();
        }*/
        addShip(); // Add the ship object initially
    }
    
    /**
     * Generate all of the backgrounds by enqueuing them into the queue
     */
    public void GenerateBackgrounds () {
        if (backgrounds.isEmpty()) {
            backgrounds.enqueue("space1.png");
            backgrounds.enqueue("space2.png");
            backgrounds.enqueue("space3.png");
            backgrounds.enqueue("space4.png");
        }
    }
    
    /**
     * Generate all of the sounds by pushing them into the stack
     */
    public void GenerateSounds () {
        if (sounds.isEmpty()) {
            sounds.push("Dark-Techno-City.mp3");
            sounds.push("Factory-On-Mercury.mp3");
            sounds.push("Night-Winds.mp3");
            sounds.push("Trouble-On-Mercury.mp3");
        }
    }
    
    /**
     * Method sets the difficulty level of the game, depending on the parameter given
     * The difficulty level should affect factors like speed and maybe even the difficulty of the words (if we have time)
     */
    public void Set_Difficulty_Level () {
        if (difflevel.equalsIgnoreCase("easy")) {
            easy = true;
            this.num_enemies = 3;
            this.timeElapsed = 3000; // Make the time interval between spawning enemies 5 seconds long
        }
        else if (difflevel.equalsIgnoreCase("medium")) {
            medium = true;
            this.num_enemies = 4;
            this.timeElapsed = 2000; // Make the time interval between spawning enemies 3 seconds long
        }
        else if (difflevel.equalsIgnoreCase("hard")) {
            hard = true;
            this.num_enemies = 5;
            this.timeElapsed = 1000; // Make the time interval between spawning enemies 1 second long
        }
    }
    
    /**
     * Method sets the game mode of the game, depending on the parameter given
     * There are two game modes: blitz and blitzkrieg
     * blitz is where enemies fall one by one, after a certain time interval
     * blitzkrieg is where enemies fall simultaneously, in fixed numbers
     */
    public void Set_Game_Mode () {
        if (game_mode.equalsIgnoreCase("1")) {
            blitz = true;
        }
        else {
            blitzkrieg = true;
        }
    }
    
    /**
     * Method is used to preprocess the enemies and add them to a list before starting the animations
     */
    public void Store_Enemies () {
        if (num_enemies > 0 && enemies.size() >= num_enemies && enemies.size() > 0) {
            test = enemies.dequeue();
            words_list.add(test); // Add the current word to the list - used for the word_manager class
            text_list.add(test); // Add the word text into a list to be displayed later
        }
        /*else { // Refill the queue with enemies
            for (int i=0; i<1000; i++) {
                generateEnemies();
            }
        }*/
    }
    
    private long lastTime = System.currentTimeMillis(); // Keep track of last time
    
    /**
     * Use this method for the blitz mode
     */
    public void moveObjects () {
        long currTime = System.currentTimeMillis();
        if (words_list.size() >= num_enemies) {
            // for (Word word : words_list) {
            for (int i=0; i<num_enemies; i++) {
                Word word = words_list.get(i);
                if (!destroyed_enemies && currTime > lastTime + timeElapsed) {
                    lastTime = currTime;
                    int xcoor = rng.nextInt(800) + 1;
                    int ycoor = rng.nextInt(100 - 50 + 1) + 50; // Make it between 50-100, inclusive
                    addObject(word, xcoor, ycoor);
                    word.wordHit(word_finished);
                    if (word != null) enemy_destroyed &= word.isDestroyed(); // Check to see if all of the enemies have been destroyed for that level
                    enemy_cnt++;
                    // If all enemies have been destroyed, set it to true to move to next level
                    if (enemy_cnt > num_enemies) {
                        destroyed_enemies = true;
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Use this method for the blitzkrieg mode
     * Need to ensure that the enemies are not added in the same spot to start
     */
    public void move_Objects () {
        int prevx = -1, prevy = -1; // Used to store previous x and y coordinates
        if (words_list.size() >= num_enemies) {
            for (int i=0; i<words_list.size(); i++) {
                Word word = words_list.get(i);
                if (!destroyed_enemies) {
                    int xcoor = rng.nextInt(800) + 1;
                    int ycoor = rng.nextInt(100 - 50 + 1) + 50; // Make it between 50-100, inclusive
                    // Add the word/enemy to the world, making sure that no two overlap
                    if (prevx > 0 && Math.abs(xcoor - prevx) > 15) { // 15 units apart should be good..
                        addObject(word, xcoor, ycoor);
                    }
                    word.wordHit(word_finished);
                    if (word != null) enemy_destroyed &= word.isDestroyed(); // Check to see if all of the enemies have been destroyed for that level
                    // enemy_cnt += (test.WordTyped() || test.isDestroyed() || word_finished) ? 1 : 0;
                    enemy_cnt++; // Get how many enemies still remain in worldfclac
                    // If all enemies have been destroyed, set it to true to move to next level
                    if (enemy_cnt > num_enemies) {
                        destroyed_enemies = true;
                        break;
                    }
                    // Now store previous x and y coordinates
                    prevx = xcoor;
                    prevy = ycoor;
                }
            }
        }
    }
    
    private SimpleTimer timer = new SimpleTimer(); // 
    /**
     * Method clears the screen of any remaining bullets left on-screen
     * Call on this after the enemies have been destroyed (since bullets stop moving)
     */
    public void Clear_Bullets () {
        boolean ref = word_finished; // Make a reference to that boolean value
        if (!getObjects(Bullet.class).isEmpty() && levelledup) removeObjects(getObjects(Bullet.class));  // Clear the screen of bullets if there are any
        // For blitz mode
        if (blitz && !blitzkrieg) {
            if (!getObjects(Bullet.class).isEmpty() && getObjects(Word.class).isEmpty()) {// && word_done) {
                removeObjects(getObjects(Bullet.class));
            }
        }
        else if (blitzkrieg && !blitz) {
            long currTime = System.currentTimeMillis();
            if (!getObjects(Bullet.class).isEmpty() && word_finished && num_enemies > 0 && getObjects(Word.class).size() < num_enemies && timer.millisElapsed() < 1500) {
                timer.mark();
                word_finished = false;
                removeObjects(getObjects(Bullet.class));
            }
        }
        if (levelledup) levelledup = false; // Reset the boolean
    }
  
    
    /**
     * Method prepares the incoming words and everything else needed to start
     */
    public void getReady () {
        // Add all of the words to their respective ArrayLists...
        getAdjectives();
        getNouns();
        getVerbs();
        // Now add all of the words into another set
        for (String adjectivess : adjectives1) {
            enemies_set.add(adjectivess);
        }
        for (String nounss : nouns1) {
            enemies_set.add(nounss);
        }
        for (String verbss : verbs1) {
            enemies_set.add(verbss);
        }
   }
    
    /**
     * Method adds the ship object to the centre of the screen
     * Is called upon in the setup method (note that the ship isn't to be static)
     */
    public void addShip () {
        if (player_sprite.getType() == 1) {
            Ship_1 player = new Ship_1(bullet_speed, 600);
            addObject(player, 400, 600); // Change coordinates later
            player_type = 1;
            player_ship = player;
        }
        else if (player_sprite.getType() == 2) {
            Ship_2 player = new Ship_2(bullet_speed, 600);
            addObject(player, 400, 600); // Change coordinates later
            player_type = 2;
            player_ship = player;
        }
        else if (player_sprite.getType() == 3) {
            Ship_3 player = new Ship_3(bullet_speed, 600);
            addObject(player, 400, 600); // Change coordinates later
            player_type = 3;
            player_ship = player;
        }
        else if (player_sprite.getType() == 4) {
            Ship_4 player = new Ship_4(bullet_speed, 600);
            addObject(player, 400, 600); // Change coordinates later
            player_type = 4;
            player_ship = player;
        }
        else if (player_sprite.getType() == 5) {
            Ship_5 player = new Ship_5(bullet_speed, 600);
            addObject(player, 400, 600); // Change coordinates later
            player_type = 5;
            player_ship = player;
        }
    }
    
    /**
     * Method generates the enemies and pushes them into the queue, using the rng object
     */
    public void generateEnemies () {
        // Push all of the enemies/words into the queue to be displayed later
        // The words should be stored in either the HashSet or the ArrayList
        int word_type = rng.nextInt(3) + 1;
        if (word_type == 1) {
            int word_idx = rng.nextInt(adjectives.size());
            int enemy_type = rng.nextInt(6) + 1;
            if (enemy_type == 1) {
                Enemy_1 enemy = new Enemy_1(player_sprite, adjectives.get(word_idx), 1, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 2) {
                Enemy_2 enemy = new Enemy_2(player_sprite, adjectives.get(word_idx), 2, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 3) {
                Enemy_3 enemy = new Enemy_3(player_sprite, adjectives.get(word_idx), 3, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 4) {
                Enemy_4 enemy = new Enemy_4(player_sprite, adjectives.get(word_idx), 4, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 5) {
                Enemy_5 enemy = new Enemy_5(player_sprite, adjectives.get(word_idx), 5, this.word_speed);
            }
            else if (enemy_type == 6) {
                Enemy_6 enemy = new Enemy_6(player_sprite, adjectives.get(word_idx), 6, this.word_speed);
                enemies.enqueue(enemy);
            }
        }
        else if (word_type == 2) {
            int word_idx = rng.nextInt(nouns.size());
            int enemy_type = rng.nextInt(6) + 1;
            if (enemy_type == 1) {
                Enemy_1 enemy = new Enemy_1(player_sprite, nouns.get(word_idx), 1, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 2) {
                Enemy_2 enemy = new Enemy_2(player_sprite, nouns.get(word_idx), 2, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 3) {
                Enemy_3 enemy = new Enemy_3(player_sprite, nouns.get(word_idx), 3, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 4) {
                Enemy_4 enemy = new Enemy_4(player_sprite, nouns.get(word_idx), 4, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 5) {
                Enemy_5 enemy = new Enemy_5(player_sprite, nouns.get(word_idx), 5, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 6) {
                Enemy_6 enemy = new Enemy_6(player_sprite, nouns.get(word_idx), 6, this.word_speed);
                enemies.enqueue(enemy);
            }
        }
        else if (word_type == 3) {
            int word_idx = rng.nextInt(verbs.size());
            int enemy_type = rng.nextInt(6) + 1;
            if (enemy_type == 1) {
                Enemy_1 enemy = new Enemy_1(player_sprite, verbs.get(word_idx), 1, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 2) {
                Enemy_2 enemy = new Enemy_2(player_sprite, verbs.get(word_idx), 2, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 3) {
                Enemy_3 enemy = new Enemy_3(player_sprite, verbs.get(word_idx), 3, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 4) {
                Enemy_4 enemy = new Enemy_4(player_sprite, verbs.get(word_idx), 4, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 5) {
                Enemy_5 enemy = new Enemy_5(player_sprite, verbs.get(word_idx), 5, this.word_speed);
                enemies.enqueue(enemy);
            }
            else if (enemy_type == 6) {
                Enemy_6 enemy = new Enemy_6(player_sprite, verbs.get(word_idx), 6, this.word_speed);
                enemies.enqueue(enemy);
            }
        }
    }
    
    /**
     * Method gets the user inputting on-screens
     * Use Greenfoot.isKeyDown() for handling the user inputting
     * Use Word_Display.addWord() method to add the word onto the world screen
     */
    public void getInput () {
        // First check to see if the user presses the pause button
        if (paused_button.isPressed()) {
            if (paused) {
                paused = false;
                paused_pressed = false;
                removeObjects(getObjects(Button.class));
            }
            else {
                paused = true;
                // Add all of the buttons for when it is paused
                settings = new Button("settings", new GreenfootImage("settings_button.png"));
                exit = new Button("exit", new GreenfootImage("exit_button.png"));
                restart = new Button("restart", new GreenfootImage("restart_button.png"));
                addObject(settings, 200, 230);
                addObject(restart, 200, 305);
                addObject(exit, 200, 380);
            }
        }
        if (!paused) {
            // Now get all of the inputting for the main controls
            String key = Greenfoot.getKey();
            String currword = ""; // The current word to be typed
            if (inputted = (key != null)) {
                if (key.length() > 1) {
                    if (key.equals("backspace")) { // Delete the character
                        test.RemoveLetter();
                        if (currword.length() > 1) currword = currword.substring(0, currword.length() - 1);
                    }
                    else if (key.equals("up")) { // User chose to switch between words 
                        if (wordsarr.length > 0) Switch_Word(true, false); // Switch words up
                    }
                    else if (key.equals("down")) {
                        if (wordsarr.length > 0) Switch_Word(false, true); // Switch words down
                    }
                    else if (key.equals("space")) { // Pressed spacebar
                        if (word_manager != null) {
                            word_manager.Space_Listener();
                            if (test != null && !test.WordTyped()) {
                                word_manager.TypeLetter(' ');
                                currword += " ";
                            }
                        }
                    }
                }
                else if (key.length() == 1) { // Else the word is one character long
                    if (!Character.isDigit(key.charAt(0))) { // The string is not a number -> user has inputted a character
                        if (test != null && (!test.WordTyped())) { //|| !test.isDestroyed())) {
                            if (word_manager != null) {
                                word_manager.TypeLetter(key.charAt(0)); // Call on the typing method
                                currword += key; // Add the current character to the string
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Method asks player for username before starting the game
     */
    public void AskUsername () {
        username = JOptionPane.showInputDialog("Please input your username"); // Get and store the value of the username as a String
    }
    
    /**
     * Method sets the background (makes it easier to change the background picture)
     */
    public void Set_Background (GreenfootImage image) {
        image.scale(800, 700);
        int xcoor = 0;
        int ycoor = 0;
        getBackground().drawImage(image, xcoor, ycoor);
    }
    
    /**
     * Method sets the music (makes it easier to change the background picture)
     */
    public void Set_Music (GreenfootSound song) {
        music.stop();
        music = song;
        music.playLoop();
    }
    
    private long lastTime1 = System.currentTimeMillis(); // Keep track of last time again
    /**
     * Method does most of the levelling up for the main game
     * This includes changing the music and background of the screen, as well as increasing number of words, and the speed
     */
    public void levelUp () {
        levelledup = true; // Set the boolean to true
        stop_firing = true; // Set it to true to stop ship from firing bullets
        destroyed_enemies = false; // Reset the boolean
        score += 1000; // Increment score by 1000
        enemy_cnt = 0; // Reset the cnt variable
        // Generate the next background
        if (!backgrounds.isEmpty()) {
            Set_Background(new GreenfootImage(backgrounds.dequeue()));
        }
        else GenerateBackgrounds(); // Else refill
        // Generate the next sound
        if (!sounds.isEmpty()) {
            Set_Music(new GreenfootSound(sounds.pop()));
        }
        else GenerateSounds(); // Else refill
        long currTime = System.currentTimeMillis();
        if (currTime > lastTime1 + 500) { // If greater than 0.5 seconds
            lastTime1 = currTime;
            addShip();
        }
        word_speed++; // Increment the value of the speed for words/enemies
        if (bullet_speed < 10) bullet_speed++; // Increment speed for bullet -> set cap to 10
        level++; // Increment the current level
        if (score_deduct_num >= 10) score_deduct_num -= 10; // Decrement this value since less points from the player's score should be deducted as the game progresses
        if (timeElapsed > 200) timeElapsed -= 100; // Reduce the time interval by 100 milliseconds to make it faster
        // Increment number of enemies depending on the difficulty level
        if (difflevel.length() > 0 && num_enemies > 0) {
            // Stop spawning more enemies for: level >= 11
            if (this.difflevel.equals("easy")) {
                if (level < 11) num_enemies += 1;
            }
            else if (this.difflevel.equals("medium")) {
                if (level < 11) num_enemies += 2;
            }
            else if (this.difflevel.equals("hard")) {
                if (level < 11) num_enemies += 3;
            }
        }
        level_up.play(); // Play the levelling-up sound
    }
    
    /**
     * Get a list of the adjectives
     */
    public void getAdjectives () {
        file2.ReadWords1(); // Read using the scanner
        adjectives = file2.getList();
        // Add all of the contents to the HashSet
        for (String next : adjectives) {
            adjectives1.add(next);
        }
    }
    
    /**
     * Get a list of the nouns
     */
    public void getNouns () {
        file3.ReadWords1(); // Read using the scanner
        nouns = file3.getList();
        // Add all of the contents to the HashSet
        for (String next : nouns) {
            nouns1.add(next);
        }
    }
    
    /**
     * Get a list of the verbs
     */
    public void getVerbs () {
        file4.ReadWords1(); // Read using the scanner
        verbs = file4.getList();
        // Add all of the contents to the HashSet
        for (String next : verbs) {
            verbs1.add(next);
        }
    }
    
    /**
     * Write the player_info to the scores.txt file
     * (Uses Java's BufferedWriter and FileWriter)
     */
    public void writeScores (Player_Info player) {
        file1.Write(player);
    }
    
    /**
     * Method stops all of the songs from playing
     */
    public void Stop_Songs () {
        music.stop();
        pause_sound.stop();
        level_up.stop();
    }
    
    /**
     * Always display current level on the screen
     */
    public void Display_Level () {
        addObject(new Text("Level " + String.valueOf(level), 23, "orange"), 400, 150);
    }
    
    /**
     * Always display current score on the screen
     */
    public void Display_Score () {
        addObject(new Text("Score: " + String.valueOf(score), 20, "orange"), 700, 100);
    }
    
    /**
     * Always display the current difficulty level
     */
    public void Display_Difficulty_Level () {
        if (difflevel.equalsIgnoreCase("easy")) {
            addObject(new Text("Difficulty: Easy", 20, "orange"), 400, 200);
        }
        else if (difflevel.equalsIgnoreCase("medium")) {
            addObject(new Text("Difficulty: Medium", 20, "orange"), 400, 200);
        }
        else if (difflevel.equalsIgnoreCase("hard")) {
            addObject(new Text("Difficulty: Hard", 20, "orange"), 400, 200);
        }
    }
    
    /**
     * Always display the chosen game mode
     */
    public void Display_Game_Mode () {
        if (blitz) {
            addObject(new Text("Mode: Blitz", 20, "orange"), 400, 250);
        }
        else {
            addObject(new Text("Mode: Blitzkrieg", 20, "orange"), 400, 250);
        }
    }
    
    /**
     * Calculate the score of the player
     * Score varies depending on the difficulty level chosen as well as how many words/enemies were destroyed
     */
    public void Calc_Score () {
        // Increment score first
        if (enemy_cnt > 0) {
            if (this.difflevel.equals("easy")) {
                score += enemy_cnt;
            }
            else if (this.difflevel.equals("medium")) {
                score += 2 * enemy_cnt;
            }
            else {
                score += 3 * enemy_cnt;
            }
        }
        // Now decrement score
        if (deduct) {
            score -= score_deduct_num; // Deduct points from the score if user typed a character incorrectly, depending on the level
            deduct = false;
        }
    }
    
    /**
     * For when the player dies
     */
    public void CheckLoss () {
        if (player_ship.isHit()) { // Allow 1500 milliseconds for explosion animation
            Calc_Score(); // Calculate user's score
            // Save the player info (make sure that string username isn't empty)
            if (this.username.length() > 0) player_info = new Player_Info(this.username, this.score, this.level);
            writeScores(player_info); // Now write the score to the text file
            Stop_Songs(); // Stop songs from playing
            Greenfoot.setWorld(new End_Screen(this.difflevel, this.game_mode, this.sound, this.player_sprite, this.background_info));
        }
    }
    
    /**
     * Check to see if the player won!
     * This occurs when the user has typed all of the words there is, assuming that they haven't died yet
     */
    public void CheckWin () {
        if (!player_ship.isHit()) { // If the player hasn't died yet
            if (typed_set.size() == enemies_set.size()) { // If it's equal, that means that the player has typed all of the words
                Calc_Score(); // Calculate user's score
                // Save the player info (make sure that string username isn't empty)
                if (this.username.length() > 0) player_info = new Player_Info(this.username, this.score, this.level);
                writeScores(player_info); // Now write the score to the text file
                Stop_Songs();
                Greenfoot.setWorld(new Win_Screen(this.difflevel, this.game_mode, this.sound, this.player_sprite, this.background_info));
            }
        }
    }
   
    /**
     * Method processes the words on the screen
     */
    public void Process_Words () {
        //for (Word test : words_list) {
        String word_string = ""; // For storing the text strings of the words that appear on screen
        if (!getObjects(Word.class).isEmpty()) {
            Word test = getObjects(Word.class).get(0);
            word_string = test.getWord(); // Set it equal to the word/enemy's text
            if (test != null) {
                if (inputted) { // Only if user has inputted a character
                    word_finished = test.WordTyped();
                    typed_correctly = test.typedCorrectly();
                    word_destroyed = test.isDestroyed();
                }
                if (!word_finished) { // Only if the word still isn't finished yet
                    test.setBoolFinished(false);
                    if (typed_correctly) { // If the character is typed correctly
                        player_ship.SetTargetWord(test);
                        stop_firing = false; // Continue firing
                        player_ship.setBoolFire(false);
                        player_ship.setBoolTyped(true);
                        if (player_sprite.getType() == 1) {
                            Ship_1 ship = (Ship_1) player_ship;
                            // Make ship fire only one bullet to simplify
                            if (!stop_firing && !ship.isHit()) {
                                if (getObjects(Bullet.class).isEmpty() && !word_destroyed) ship.shootBlasts(100);
                            }
                        }
                        else if (player_sprite.getType() == 2) {
                            Ship_2 ship = (Ship_2) player_ship;
                            // Make ship fire only one bullet to simplify
                            if (!stop_firing && !ship.isHit()) {
                                if (getObjects(Bullet.class).isEmpty() && !word_destroyed) ship.shootBlasts(100);
                            }
                        }
                        else if (player_sprite.getType() == 3) {
                            Ship_3 ship = (Ship_3) player_ship;
                            // Make ship fire only one bullet to simplify
                            if (!stop_firing && !ship.isHit()) {
                                if (getObjects(Bullet.class).isEmpty() && !word_destroyed) ship.shootBlasts(100);
                            }
                        }
                        else if (player_sprite.getType() == 4) {
                            Ship_4 ship = (Ship_4) player_ship;
                            // Make ship fire only one bullet to simplify
                            if (!stop_firing && !ship.isHit()) {
                                if (getObjects(Bullet.class).isEmpty() && !word_destroyed) ship.shootBlasts(100);
                            }
                        }
                        else if (player_sprite.getType() == 5) {
                            Ship_5 ship = (Ship_5) player_ship;
                            // Make ship fire only one bullet to simplify
                            if (!stop_firing && !ship.isHit()) {
                                if (getObjects(Bullet.class).isEmpty() && !word_destroyed) ship.shootBlasts(100);
                            }
                        }
                        stop_firing = true;
                        player_ship.setBoolFire(true); // Stop firing after every blast (wait for user to input character)
                        player_ship.setBoolTyped(false);
                        // typed_correctly = false;
                    }
                    else if (!typed_correctly) { // Else if typed incorrectly
                        stop_firing = true;
                        player_ship.setBoolFire(true);
                        player_ship.setBoolTyped(false);
                    }
                    // Deduct points from the score if the user types a character incorrectly
                    if (word_manager != null && !word_manager.typedCorrectly() && inputted) {
                        if (score > score_deduct_num) deduct = true; // score -= score_deduct_num; // Deduct points from the score if user typed a character incorrectly, depending on the level
                    }
                }
                else if (word_finished) { // If user has finished typing the word fully
                    typed_set.add(word_string); // Add it to the HashSet of all words that have been typed
                    stop_firing = true;
                    // removeObjects(getObjects(Bullet.class));
                    test.setBoolFinished(true);
                    player_ship.setBoolFire(true);
                    player_ship.setBoolTyped(false);
                }
                test.setBoolInputted(inputted); // Update the boolean for whether the user has inputted a character or not
            }
        //}
        }
    }
    
    /**
     * Method shifts every element, starting from the first element, to the right in the Word array
     */
    public void Shift_Elem_Left () {
        for (int i=0; i<curridx; i++) {
            wordsarr[i] = wordsarr[i + 1];
        }
    }
    
    /**
     * Method shifts every element, starting from the last element, to the left in the Word array
     */
    public void Shift_Elem_Right () {
        for (int i=curridx; i>0; i--) {
            wordsarr[i] = wordsarr[i - 1];
        }
    }
    
    /**
     * This method handles the processing of switching in between words, when the user presses the up or down keys
     * We use the Word array to toggle in between words
     * @param up_pressed = is up_key pressed
     * @param down_pressed = is down_key pressed
     */
    public void Switch_Word (boolean up_pressed, boolean down_pressed) {
        if (up_pressed && wordsarr[1] != null && wordsarr[0] != null) {
            wordsarr[0].current = false;
            wordsarr[0].setWord(wordsarr[0].getWord(), "white"); // Try white for now
            st.push(wordsarr[0]);
            Shift_Elem_Left(); // Shift words to the left in array
            curridx--;
        }
        if (down_pressed && !st.isEmpty() && wordsarr[0] != null) {
            wordsarr[0].current = false;
            Shift_Elem_Right(); // Shift words to the right in array
            curridx++;
        }
    }
    
    /**
     * Used to return a string in the array of words
     */
    public String replaceWord () {
        if (wordsarr[0] == null) return wordsarr[0].getWord();
        else return "";
    }
    
    /**
     * Method toggles the array of words
     */
    public void ToggleWords () {
        // For switch word function
        if (wordsarr != null) {
            if (wordsarr[0] == null && !st.isEmpty()) {
                if (!st.isEmpty()) wordsarr[0] = st.pop();
                curridx++;
            }
            if (wordsarr[0] != null) wordsarr[0].current = true;
        }
    }
   
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (!paused) { // Only if it's not paused
            music.playLoop(); // Play the music in a loop
            generateEnemies(); // Generate the words/enemies
            Store_Enemies(); // Store the words/enemies
            if (blitz) moveObjects(); // Move the words/enemies simultaneously
            else if (!blitz) move_Objects(); // Move the words/enemies one by one
            getInput(); // Get player input
            // Now initliaze the word_manager and wordsarr objects
            if (!this.words_list.isEmpty()) {
                word_manager = new Word_Manager(this.words_list, this.num_enemies);
                // Initialize the contents in the Word array
                wordsarr = new Word[words_list.size()];
                for (int i=0; i<words_list.size(); i++) {
                    wordsarr[i] = words_list.get(i);
                }
            }
            // Always update the Word list
            if (word_manager != null) {
                words_list = word_manager.Get_List();
            }
            ToggleWords();
            Process_Words(); // Process words in the words list
            Clear_Bullets(); // Clear the screen of bullets
            if (player_ship != null) {
                player_ship.SetNumEnemies(num_enemies); // Update the number of enemies in current level
                this.player_died = player_ship.isHit(); // Check if player ship is hit
            }
            CheckLoss(); // Check if player has lost
            CheckWin(); // Check if player has won
            removeObjects(getObjects(Text.class));
            // For highlighting the current word in green
            if (!getObjects(Word.class).isEmpty()) {
                colored = false;
                Word currword = getObjects(Word.class).get(0);
                if (!currword.WordTyped()) {
                    addObject(new Text("Word: " + currword.getWord(), 23, "yellow"), 400, 350); // Now add the text to the centre of the screen for the user to see
                }
                if (!currword.typedCorrectly()) { // Only highlight if it hasn't been typed correctly
                    currword.setWord(currword.getWord(), "green");
                    colored = true;
                }
            }
            // For displaying the words beside the enemies
            if (!getObjects(Player.class).isEmpty() && !text_list.isEmpty()) {
                for (Word text : text_list) {
                    if (text != null && !getObjects(Word.class).isEmpty() && !text.WordTyped()) {
                        text.Display_Word(); // Display the text word!
                    }
                    else if (text.WordTyped()) {
                        text.setWord(text.getWord(), "white");
                    }
                }
            }
            // Always display level, difficulty level, game mode, and score
            Display_Level();
            Display_Difficulty_Level();
            Display_Game_Mode();
            Display_Score();
            // Calculate score
            if (getObjects(Word.class).size() < num_enemies) Calc_Score();
            // Level up if all enemies have been destroyed for the current level
            if (destroyed_enemies && getObjects(Word.class).isEmpty() && !player_ship.isHit() && enemy_cnt > 0) {
                removeObjects(getObjects(Player.class));
                levelUp();
                enemy_cnt = 0;
            }
        }
        else {
            addObject(new Text("PAUSED", 27, "white"), 400, 300); // Display text
            // this.word_speed = 0; // Stop the words from moving
            // this.bullet_speed = 0; // Set the bullet speeds to zero
            // Stop songs from playing if paused and play pause sound
            if (!paused_pressed) {
                pause_sound.play();
                paused_pressed = true;
                Stop_Songs();
            }
            // If settings button is pressed
            if (settings.isPressed()) {
                // Stop all of the songs from playing
                Stop_Songs();
                Greenfoot.setWorld(new Settings_Screen(difflevel, game_mode, sound, player_sprite, background_info));
                Greenfoot.stop(); // Stop Greenfoot
            }
            // Else if exit button is pressed
            else if (exit.isPressed()) {
                Stop_Songs();
                Greenfoot.setWorld(new Intro_Screen(difflevel, game_mode, sound, player_sprite, background_info));
                Greenfoot.stop();
            }
            // Else if restart button is pressed
            else if (restart.isPressed()) {
                Stop_Songs();
                Greenfoot.setWorld(new Main_Game(difflevel, game_mode, sound, player_sprite, background_info));
            }
        }
   }
}