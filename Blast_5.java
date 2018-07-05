import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fifth type of blast
 * 
 * @author Eric Liu
 * @version March 11, 2018
 */
public class Blast_5 extends Bullet {
    
    private int xcoor, ycoor;
    private SimpleTimer timer = new SimpleTimer(); // Used as the timer
    private int wordtype;
    private Enemy_1 word1;
    private Enemy_2 word2;
    private Enemy_3 word3;
    private Enemy_4 word4;
    private Enemy_5 word5;
    private Enemy_6 word6;
    private boolean temp = false;
    private Word targetWord = null;
    private int rotation_val = 0; // Value of rotation argument for setRotation()
    
    /**
     * @param word_type = type of word/enemy chosen for tracking on-screen
     * @param speed = speed of blast/bullet
     * @param tar = target Word to track
     */
     public Blast_5 (int speed, Word tar) {
         this.targetWord = tar;
         setSpeed(speed); // Adjust this as necessary
    }
    
    /**
     * Main method for tracking the enemies on screen
     * Change this later to the actual target word
     */
    public void goTo () {
        if (!getWorld().getObjects(Word.class).isEmpty()) {
            Word word = getNearestActor();
            if (word instanceof Enemy_1) {
                word1 = (Enemy_1) getWorld().getObjects(Enemy_1.class).get(0);
                xcoor = word1.getX();
                ycoor = word1.getY();
            }
            else if (word instanceof Enemy_2) {
                word2 = (Enemy_2) getWorld().getObjects(Enemy_2.class).get(0);
                xcoor = word2.getX();
                ycoor = word2.getY();
            }
            else if (word instanceof Enemy_3) {
                word3 = (Enemy_3) getWorld().getObjects(Enemy_3.class).get(0);
                xcoor = word3.getX();
                ycoor = word3.getY();
            }
            else if (word instanceof Enemy_4) {
                word4 = (Enemy_4) getWorld().getObjects(Enemy_4.class).get(0);
                xcoor = word4.getX();
                ycoor = word4.getY();
            }
            else if (word instanceof Enemy_5) {
                word5 = (Enemy_5) getWorld().getObjects(Enemy_5.class).get(0);
                xcoor = word5.getX();
                ycoor = word5.getY();
            }
            else if (word instanceof Enemy_6) {
                word6 = (Enemy_6) getWorld().getObjects(Enemy_6.class).get(0);
                xcoor = word6.getX();
                ycoor = word6.getY();
            }
            turnTowards(xcoor, ycoor);
            move(speed);
        }
    }
    
    /**
     * Second method for the tracking method
     * @param target = Word target for the user to track
     */
    public void goTo (Word target) {
        if (!getWorld().getObjects(Word.class).isEmpty()) {
            if (target instanceof Enemy_1) {
                if (!getWorld().getObjects(Enemy_1.class).isEmpty()) {
                    word1 = (Enemy_1) getWorld().getObjects(Enemy_1.class).get(0);
                    xcoor = word1.getX();
                    ycoor = word1.getY();
                }
            }
            else if (target instanceof Enemy_2) {
                if (!getWorld().getObjects(Enemy_2.class).isEmpty()) {
                    word2 = (Enemy_2) getWorld().getObjects(Enemy_2.class).get(0);
                    xcoor = word2.getX();
                    ycoor = word2.getY();
                }
            }
            else if (target instanceof Enemy_3) {
                if (!getWorld().getObjects(Enemy_3.class).isEmpty()) {
                    word3 = (Enemy_3) getWorld().getObjects(Enemy_3.class).get(0);
                    xcoor = word3.getX();
                    ycoor = word3.getY();
                }
            }
            else if (target instanceof Enemy_4) {
                if (!getWorld().getObjects(Enemy_4.class).isEmpty()) {
                    word4 = (Enemy_4) getWorld().getObjects(Enemy_4.class).get(0);
                    // rotation_val = word4.getRotation();
                    xcoor = word4.getX();
                    ycoor = word4.getY();
                }
            }
            else if (target instanceof Enemy_5) {
                if (!getWorld().getObjects(Enemy_5.class).isEmpty()) {
                    word5 = (Enemy_5) getWorld().getObjects(Enemy_5.class).get(0);
                    xcoor = word5.getX();
                    ycoor = word5.getY();
                }
            }
            else if (target instanceof Enemy_6) {
                if (!getWorld().getObjects(Enemy_6.class).isEmpty()) {
                    word6 = (Enemy_6) getWorld().getObjects(Enemy_6.class).get(0);
                    xcoor = word6.getX();
                    ycoor = word6.getY();
                }
            }
            if (!word_finished) { // Only go if the word being typed is still active
                turnTowards(xcoor, ycoor);
                move(speed);
            }
        }
    }
    
    private long lastTime = System.currentTimeMillis();
    
    /**
     * Act - do whatever the Blast_1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        long currTime = System.currentTimeMillis();
        if (user_typed) {
            goTo(this.targetWord);
            checkBoundaries();
            CheckFinished();
        }
    }     
}
