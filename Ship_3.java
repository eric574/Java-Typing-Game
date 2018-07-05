import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

/**
 * For ship 3
 * 
 * @author Eric Liu
 * @version March 11, 2018
 */
public class Ship_3 extends Player {
    
    private Random rng = new Random();
    private int wordtype;
    private SimpleTimer timer = new SimpleTimer();
    private boolean temp = false;
    private int bullet_speed = 0;
    private Enemy_1 enemy_1;
    private Enemy_2 enemy_2;
    private Enemy_3 enemy_3;
    private Enemy_4 enemy_4;
    private Enemy_5 enemy_5;
    private Enemy_6 enemy_6;
    private int y = 0;
    private Word target_word = null;
    
    /**
     * @param word_type = type of word
     * @param speed = value of speed for the ship
     * @param ycoor = y coordinate of the ship on the screen
     */
    public Ship_3 (int speed, int ycoor) {
        this.bullet_speed = speed;
        this.y = ycoor;
    }
    
    /**
     * Use this public method to set the target word
     * This will be the word that the user chooses to type
     */
    public void SetTargetWord (Word target) {
        this.target_word = target;
    }
    
    private long lastTime = System.currentTimeMillis();
    
    public void shootBlasts (int timeloop) {
        if (!died) {
            if (!getWorld().getObjects(Word.class).isEmpty()) {
                long currTime = System.currentTimeMillis();
                if (currTime > lastTime + timeloop && user_typed) {
                    lastTime = currTime;
                    temp = true;
                    lastTime = currTime;
                    Blast_3 blast = new Blast_3(bullet_speed, this.target_word);
                    blast.setTyped(user_typed); // Update the boolean for character typed
                    blast.setFinished(word_finished); // Update the boolean for word typed
                    World world = getWorld();
                    blast1.setVolume(30);
                    blast1.play();
                    world.addObject(blast, getX() + 28, getY() - 11); // Modify the x and y coordinate arguments this later
                }
                else temp = false;
            }
        }
    }
    
    /**
     * Act - do whatever the Ship_1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        playerHit();
   }    
}
