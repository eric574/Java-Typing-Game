import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For the ship sprites that appear on the choose character screen
 * 
 * @author Eric Liu
 * @version March 11, 2018
 */
public class Ship_Sprite extends Actor {
    
    private int type; // Type of ship chosen
   
    public Ship_Sprite (int shiptype, GreenfootImage image) {
        this.type = shiptype;
        setImage(image);
    }
    
    public int getType () {
        return this.type;
    }
   
    /**
     * Act - do whatever the Ship_Sprite wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // Add your action code here.
    }    
}
