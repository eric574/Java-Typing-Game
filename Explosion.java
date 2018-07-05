import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For the explosion animation when the player dies or the word/enemy is destroyed
 * 
 * @author Eric Liu
 * @version March 11, 2018
 */
public class Explosion extends Actor {
    
    private GreenfootImage [] arr;
    private int idx;
    private int timeloop = 1500; // 3 seconds
    
    public Explosion (int timeforloop) {
        this.timeloop = timeforloop;
        arr = new GreenfootImage[4];
        arr[0] = new GreenfootImage("explosion4.png");
        arr[1] = new GreenfootImage("explosion3.png");
        arr[2] = new GreenfootImage("explosion2.png");
        arr[3] = new GreenfootImage("explosion1.png");
        setImage(arr[idx]);
    }
    
    public void Animation () {
        idx = (idx + 1) % arr.length;
        setImage(arr[idx]);
    }
    
    private long lastTime = System.currentTimeMillis();
    
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
       // Make sure the animation doesn't loop forever
       long currTime = System.currentTimeMillis();
       Animation();
       if (currTime > lastTime + timeloop) {
           lastTime = currTime;
           getWorld().removeObject(this);
       }
    }    
}
