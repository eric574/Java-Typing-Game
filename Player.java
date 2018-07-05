import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.*;

/**
 * This is used for displaying the sprites on screen
 * 
 * @author Eric Liu
 * @version March 8, 2018
 */
public class Player extends Actor {
    
    private Random rng = new Random();
    protected GreenfootSound blast1 = new GreenfootSound("blast1.mp3");
    //protected GreenfootSound blast2 = new GreenfootSound("blast2.mp3");
    private String name = "";
    protected boolean died = false;
    protected boolean user_typed = false; // Protected field variable for whether the user has successfully typed the character or not
    protected boolean word_finished = false; // For whether or not the user finished typing the word
    protected boolean stop_firing = false; // For whether to fire blasts or not
    protected Word target_word = null; // For target word
    protected int ship_type = 0; // For ship type
    protected int num_enemies = 0; // For number of enemies in current level
    protected ArrayList<Word> nearActors;
    
    /**
     * Get the string name
     */
    public String getName () {
        return this.name;
    }
    
    /**
     * Method sets the number of enemies in the current level
     */
    public void SetNumEnemies (int num) {
        this.num_enemies = num;
    }
    
    /**
     * Method sets the boolean user_typed to the boolean value of the parameter
     */
    protected void setBoolTyped (boolean flag) {
        this.user_typed = flag;
    }
    
    /**
     * Method sets the boolean word_finished to the boolean value of the parameter
     */
    protected void setBoolFinished (boolean flag) {
        this.word_finished = flag;
    }
    
    /**
     * Method returns whether word is finished
     */
    public boolean getWordFinished () {
        return this.word_finished;
    }
    
    /**
     * Use this public method to set the target word
     * This will be the word that the user chooses to type
     */
    protected void SetTargetWord (Word target) {
        this.target_word = target;
    }
    
    /**
     * Method returns the target word
     */
    public Word Get_TargetWord () {
        return this.target_word;
    }
    
    /**
     * Method sets the boolean stop_firing to the boolean value of the parameter
     */
    protected void setBoolFire (boolean flag) {
        this.stop_firing = flag;
    }
    
    /**
     * Method returns true if the user has successfully typed in the word and vice-versa
     */
    protected boolean isTyped () {
        return this.user_typed;
    }
    
    protected void playerHit () {
        if (!getWorld().getObjects(Word.class).isEmpty()) {
            if (isTouching(Word.class)) {
                Main_Game world = (Main_Game) getWorld();
                died = true;
                Actor actor = getOneIntersectingObject(Word.class);
                if (actor != null) getWorld().removeObject(actor);
                dyingAnimation();
                world.removeObject(this);
            }
        }
    }
    
    /**
     * Used to set the type of ship
     */
    public void SetShip (int type) {
        this.ship_type = type;
    }
    
    protected void dyingAnimation () {
        World world = getWorld();
        Explosion explosion = new Explosion(1500);
        GreenfootSound explosion_sound1 = new GreenfootSound("explosion2.mp3");
        explosion_sound1.setVolume(30);
        explosion_sound1.play();
        world.addObject(explosion, getX(), getY());
    }
    
    protected double getDistance (Word actor) {
        return Math.hypot(actor.getX() - getX(), actor.getY() - getY());
    }
    
    /**
     * Method makes the Player turn towards the nearest Word/Enemy
     * Time Complexity: O(N)
     */
    protected void TurnTowardsEnemy() {
        nearActors = (ArrayList<Word>) getWorld().getObjects(Word.class);//here you can use the radius you want and maybe another class;
        if (!nearActors.isEmpty()) {
            Word closest = nearActors.get(0);
            double distance = Math.sqrt(Math.pow(closest.getX() - this.getX(), 2) + Math.pow(closest.getY() - this.getY(), 2));
            for (Word ref : nearActors) {
                double temp = Math.sqrt(Math.pow(ref.getX() - this.getX(), 2) + Math.pow(ref.getY() - this.getY(), 2));
                if (temp < distance) {
                    distance = temp;
                    closest = ref;
                }
            }
            turnTowards(closest.getX(), closest.getY());
        }
    }
    
    public boolean isHit () {
        return this.died;
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        getWorld().setPaintOrder(Text.class, Button.class, Explosion.class, Player.class, Word.class, Bullet.class);
    }    
}
