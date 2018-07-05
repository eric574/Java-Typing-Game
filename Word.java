import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

/**
 * Used for the on-screen words
 * The actor class should display the words on-screen
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Word extends Actor {
    
    private Random rng = new Random();
    protected Ship_Sprite user_sprite;
    protected int speed = 4; // Adjust this as necessary
    private String word = "";
    private int idx = 0;
    protected Word_Display display = null;
    private boolean destroyed = false;
    protected boolean is_ship_1 = false, is_ship_2 = false, is_ship_3 = false, is_ship_4 = false, is_ship_5 = false;
    protected int word_type; // For identifying the enemy sprite
    private int prevx, prevy;
    private String colour = "";
    protected boolean word_finished = false, done = false;
    public boolean current = false; // To indicate which word in the array is current, in Word_Manager
    private boolean temp = false;
    protected boolean inputted = false; // For whether the user inputted something
    
    /**
     * Constructor for Word class
     */
    public Word (Ship_Sprite player_sprite, String type, int id, int speednum) {
        this.user_sprite = player_sprite;
        this.word = type;
        this.word_type = id;
        this.colour = "white"; // Set the default colour to white
        this.speed = speednum;
        if (user_sprite.getType() == 1) {
            is_ship_1 = true;
        }
        else if (user_sprite.getType() == 2) {
            is_ship_2 = true;
        }
        else if (user_sprite.getType() == 3) {
            is_ship_3 = true;
        }
        else if (user_sprite.getType() == 4) {
            is_ship_4 = true;
        }
        else if (user_sprite.getType() == 5) {
            is_ship_5 = true;
        }
    }
    
    /**
     * Get the colour as a string
     */
    protected String getColour () {
        return this.colour;
    }
    
    /**
     * Get the word type
     */
    protected int getType () {
        return this.word_type;
    }
    
    /**
     * Returns boolean for is word_finished
     */
    protected void setBoolFinished (boolean flag) {
        this.word_finished = flag;
    }
    
    /**
     * Return Word_Display object
     */
    public void setDisplay (Word_Display _display) {
        this.display = _display;
    }
    
    /**
     * Method is used to update whether or not the user has inputted a character
     */
    public void setBoolInputted (boolean flag) {
        this.inputted = flag;
    }
    
    /**
     * Use this to change the colour of the text word to be displayed on screen
     */
    public void setWord (String type, String color) {
        this.word = type;
        this.colour = color;
        // display.SetWord(this, color);
    }
    
    /**
     * Method sets the speed of the falling word
     */
    public void setSpeed (int speednum) {
        this.speed = speednum;
    }
    
    /**
     * Makes the word bounce off if it reaches past the bottom edge or the left and right edge of the screen
     */
    protected void checkBoundaries () {
        if (this.getY() > getWorld().getHeight() - 10 || this.getX() > getWorld().getWidth() - 10 || this.getX() < 10) {
            this.turn(180);
        }
    }
    
    /**
     * Returns the Euclidean distance as a double
     */
    protected double getDistance (Word actor1, Word actor2) {
        return Math.sqrt(Math.pow((actor2.getX() - actor1.getX()), 2) + Math.pow((actor2.getY() - actor1.getY()), 2));
    }
    
    /**
     * Method returns the nearest Word to the current word being highlighted in green
     * Time Complexity: O(N)
     * @param idx = current index of green highlighted in the words list
     */
    protected Word getNearestActor (int idx) {
        ArrayList<Word> words = (ArrayList<Word>) getWorld().getObjects(Word.class);
        Word nearestActor = null;
        if (words.size() > 1) {
            double nearestDistance = Double.MAX_VALUE;
            for (int i=0; i<words.size(); i++) {
                Word word2 = null;
                if (i != idx) word2 = words.get(i);
                double next_distance = 0;
                if (word2 != null) {
                    next_distance = getDistance(words.get(idx), word2);
                    if (nearestDistance > next_distance) {
                        nearestDistance = next_distance;
                        nearestActor = word2;
                    }
                }
            }
        }
        // If the size of the words list is 1, then it must be the first object in the list
        if (nearestActor == null) nearestActor = words.get(0);
        return nearestActor;
    }
    
    /**
     * Method checks to see if the word/enemy is overlapping with another word/enemy in the world and repositions the highlighted word
     * This is important since the user must be able to see the text of the highlighted word
     */
    protected void checkOverlapping () {
        boolean flag = false;
        int curridx = 0; // Current index of the green highlighted word in the world
        List<Word> words = getWorld().getObjects(Word.class);
        if (!words.isEmpty()) {
            for (int i=0; i<words.size(); i++) {
                Word word1 = getWorld().getObjects(Word.class).get(i);
                if (word1.colour.equals("green")) {
                    curridx = i;
                    Word word2 = getNearestActor(curridx); // Get nearest word object to the current word being highlighted in green
                    // getWorld().addObject(new Text(word1.getWord(), 20, "white"), 400, 350);
                    if (word2 != null && words.size() > 1) {
                        if (Math.abs(word1.getX() - word2.getX()) <= 20 && Math.abs(word1.getY() - word2.getY()) <= 20) {
                            word1.setLocation(word1.getX() + 30, word1.getY() - 10);
                            word2.setLocation(word2.getX() - 30, word2.getY() + 10);
                            break;
                        }
                    }
                }
            }
        }
        // return flag;
    }
    
    /**
     * Method removes the letter when the user presses the backspace key
     */
    protected void RemoveLetter () {
        this.word = this.word.substring(0, this.word.length() - 1);
        this.idx--; // Decrement index by 1
    }
    
    /**
     * Remove the text from the screen (not necessary)
     */
    protected void RemoveWord () {
        getWorld().removeObject(this);
    }
    
    /**
     * Used to remove any bullets on screen
     */
    public boolean isDestroyed () {
        return this.destroyed;
    }
    
    /**
     * Set the boolean destroyed to value of parameter
     */
    public void setBoolDestroyed (boolean flag) {
        this.destroyed = flag;
    }
    
    /**
     * Set the string text right beside the enemy sprite
     */
    public void Display_Word () {
        World world = (Main_Game) getWorld();
        if (this.word.length() > 0 && getWorld() != null) {
            if (getX() - 10 > 0 && getX() - 10 < 800 && getY() + 10 > 0 && getY() + 10 < 700) {
                world.addObject(new Text(this.word, 19, this.colour, "black"), getX() - 10, getY() + 10);
            }
        }
    }
    
    /**
     * Only is dealt damage if the user correctly types the character for the String word
     * @param wordTyped = has whole word been typed correctly?
     */
    protected void wordHit (boolean wordTyped) {
        if (this instanceof Enemy_1) {
            if (getWorld() != null && !getWorld().getObjects(Enemy_1.class).isEmpty()) {
                if (isTouching(Bullet.class)) {
                    prevx = getX();
                    prevy = getY();
                    Actor actor = getOneIntersectingObject(Bullet.class);
                    if (actor != null && WordTyped()) getWorld().removeObject(actor);
                    if (WordTyped()) {
                        dyingAnimation1();
                        if (word_finished) {
                            getWorld().removeObject(this);
                            destroyed = true;
                        }
                    }
                }
            }
        }
        
        else if (this instanceof Enemy_2) {
            if (getWorld() != null && !getWorld().getObjects(Enemy_2.class).isEmpty()) {
                if (isTouching(Bullet.class)) {
                    prevx = getX();
                    prevy = getY();
                    Actor actor = getOneIntersectingObject(Bullet.class);
                    if (actor != null && WordTyped()) getWorld().removeObject(actor);
                    if (WordTyped()) {
                        dyingAnimation1();
                        if (word_finished) {
                            getWorld().removeObject(this);
                            destroyed = true;
                        }
                    }
                }
            }
        }
        
        else if (this instanceof Enemy_3) {
            if (getWorld() != null && !getWorld().getObjects(Enemy_3.class).isEmpty()) {
                if (isTouching(Bullet.class)) {
                    prevx = getX();
                    prevy = getY();
                    Actor actor = getOneIntersectingObject(Bullet.class);
                    if (actor != null && WordTyped()) getWorld().removeObject(actor);
                    if (WordTyped()) {
                        dyingAnimation1();
                        if (word_finished) {
                            getWorld().removeObject(this);
                            destroyed = true;
                        }
                    }
                }
            }
        }
        
        else if (this instanceof Enemy_4) {
            if (getWorld() != null && !getWorld().getObjects(Enemy_4.class).isEmpty()) {
                if (isTouching(Bullet.class)) {
                    prevx = getX();
                    prevy = getY();
                    Actor actor = getOneIntersectingObject(Bullet.class);
                    if (actor != null && WordTyped()) getWorld().removeObject(actor);
                    if (WordTyped()) {
                        dyingAnimation1();
                        if (word_finished) {
                            getWorld().removeObject(this);
                            destroyed = true;
                        }
                    }
                }
            }
        }
        
        else if (this instanceof Enemy_5) {
            if (getWorld() != null && !getWorld().getObjects(Enemy_5.class).isEmpty()) {
                if (isTouching(Bullet.class)) {
                    prevx = getX();
                    prevy = getY();
                    Actor actor = getOneIntersectingObject(Bullet.class);
                    if (actor != null && WordTyped()) getWorld().removeObject(actor);
                    if (WordTyped()) {
                        dyingAnimation1();
                        if (word_finished) {
                            getWorld().removeObject(this);
                            destroyed = true;
                        }
                    }
                }
            }
        }
        
        else if (this instanceof Enemy_6) {
            if (getWorld() != null && !getWorld().getObjects(Enemy_6.class).isEmpty()) {
                if (isTouching(Bullet.class)) {
                    prevx = getX();
                    prevy = getY();
                    Actor actor = getOneIntersectingObject(Bullet.class);
                    if (actor != null && WordTyped()) getWorld().removeObject(actor);
                    if (WordTyped()) {
                        dyingAnimation1();
                        if (word_finished) {
                            getWorld().removeObject(this);
                            destroyed = true;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * First dying animation for the ship
     */
    protected void dyingAnimation1 () {
        World world = getWorld();
        word_finished = true;
        GreenfootSound explosion_sound1 = new GreenfootSound("explosion2.mp3"); // Explosion sound effect
        Explosion explosion = new Explosion(1000);
        explosion_sound1.setVolume(30);
        explosion_sound1.play();
        world.addObject(explosion, prevx, prevy);
    }
    
    /**
     * Method returns the word as a string
     */
    public String getWord () {
        return this.word;
    }
    
    /**
     * Method gets the next character in the string
     */
    public char GetNextLetter () {
        return this.word.charAt(idx);
    }
    
    /**
     * Use this whenever the user types in the word correctly
     * The method increments the index by 1 and updates the displayer (see Typed_Correctly() method)
     */
    public void TypeLetter () {
        // Make sure to keep index within the range
        if (this.idx < word.length()) this.idx++;
        Typed_Correctly();
    }
    
    /**
     * Use this method whenever the user types in a character correctly
     * Method removes the correctly typed letter and sets the current colour to red
     */
    protected void Typed_Correctly () {
        GreenfootSound type = new GreenfootSound("typing.mp3"); //Typing sound effect 
        type.setVolume(50);
        type.play();
        this.word = this.word.substring(1);
        this.colour = "red";
        done = true;
        temp = true;
        this.idx--; // Decrement index by 1
    }
    
    protected void Add_Space () {
        String left1 = word.substring(0, idx);
        String left2 = word.substring(idx + 1);
        word = left1 + " " + left2;
        this.idx++;
    }
    
    public boolean typedCorrectly () {
        return this.done;
    }
    
    /**
     * Method returns a boolean representing whether the user has successfully typed in the word
     */
    public boolean WordTyped () {
        return this.idx >= this.word.length();// || word_finished;
    }
    
    /**
     * Use this to get the speed of the word
     */
    public int getSpeed () {
        return this.speed;
    }
    
    /**
     * Act - do whatever the Word wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        getWorld().setPaintOrder(Text.class, Button.class, Explosion.class, Player.class, Word.class, Bullet.class);
        // Display_Word();
        if (done) done = false; // Always set the boolean for whether a character is typed correctly to false to reset
    }    
}
