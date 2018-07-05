import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * For the bullets that are fired from the player towards the word after the user correctly types a character
 * 
 * @author Eric Liu
 * @version March 11, 2018
 */
public class Bullet extends Actor {
    protected int speed = 2; // Adjust this as necessary
    protected boolean user_typed = false; // Has the user finished typing the character?
    protected boolean word_finished = false; // Has the user finished typing the whole word?

    protected void setSpeed (int bulletSpeed) {
        this.speed = bulletSpeed;
    }

    protected void setFinished (boolean flag) {
        this.word_finished = flag;
    }

    /**
     * Use to set the boolean to the value of the boolean parameter
     */
    protected void setTyped (boolean flag) {
        this.user_typed = flag;
    }

    protected boolean hasTyped () {
        return this.user_typed;
    }

    /**
     * Removes the bullet if it reaches past the top edge or the left and right edge of the screen
     */
    protected void checkBoundaries () {
        if (getY() < 10 || getX() > getWorld().getWidth() - 10 || getX() < 10) {
            if (getWorld() != null) getWorld().removeObject(this);
        }
    }

    /**
     * Method removes the bullet from the world once the enemy that it is locked on to, is typed
     */
    protected void CheckFinished () {
        if (getWorld() != null && !getWorld().getObjects(Bullet.class).isEmpty() && word_finished) getWorld().removeObject(this);
    }

    protected double getDistance (Word actor) {
        return Math.hypot(actor.getX() - getX(), actor.getY() - getY());
    }

    /**
     * Method returns the nearest Word to the player
     * Time Complexity: O(N)
     */
    protected Word getNearestActor() {
        List<Word> nearActors = getObjectsInRange(300, Word.class); // Here you can use radius of 300 for the range
        Word nearestActor = null;
        double nearestDistance = Double.MAX_VALUE;
        double distance = 0;
        for (int i = 0; i < nearActors.size(); i++) {
            distance = getDistance(nearActors.get(i));
            if (distance < nearestDistance) {
                nearestActor = nearActors.get(i);
                nearestDistance = distance;
            }
        }
        return nearestActor;
    }

    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {

    }
}
