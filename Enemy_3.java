import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For enemy 3
 * 
 * @author Eric Liu
 * @version March 11, 2018
 */
public class Enemy_3 extends Word {
    
    private Ship_1 ship1;
    private Ship_2 ship2;
    private Ship_3 ship3;
    private Ship_4 ship4;
    private Ship_5 ship5;
    private int xcoor, ycoor;
    private boolean word_finished = false;
    private int cnt = 0;
    
    public Enemy_3 (Ship_Sprite player_sprite, String type, int id, int speednum) {
        super(player_sprite, type, id, speednum);
    }
    
    /**
     * Method returns the type of enemy (classified by sprite) in integer form
     */
    public int getType () {
        return super.getType();
    }
    
    public void setBoolFinished (boolean flag) {
        this.word_finished = flag;
    }
    
    /**
     * Act - do whatever the Enemy_5 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (is_ship_1) {
            if (!getWorld().getObjects(Ship_1.class).isEmpty()) {
                ship1 = (Ship_1) getWorld().getObjects(Ship_1.class).get(0);
                xcoor = ship1.getX();
                ycoor = ship1.getY();
            }
        }
        else if (is_ship_2) {
            if (!getWorld().getObjects(Ship_2.class).isEmpty()) {
                ship2 = (Ship_2) getWorld().getObjects(Ship_2.class).get(0);
                xcoor = ship2.getX();
                ycoor = ship2.getY();
            }
        }
        else if (is_ship_3) {
            if (!getWorld().getObjects(Ship_3.class).isEmpty()) {
                ship3 = (Ship_3) getWorld().getObjects(Ship_3.class).get(0);
                xcoor = ship3.getX();
                ycoor = ship3.getY();
            }
        }
        else if (is_ship_4) {
            if (!getWorld().getObjects(Ship_4.class).isEmpty()) {
                ship4 = (Ship_4) getWorld().getObjects(Ship_4.class).get(0);
                xcoor = ship4.getX();
                ycoor = ship4.getY();
            }
        }
        else if (is_ship_5) {
            if (!getWorld().getObjects(Ship_5.class).isEmpty()) {
                ship5 = (Ship_5) getWorld().getObjects(Ship_5.class).get(0);
                xcoor = ship5.getX();
                ycoor = ship5.getY();
            }
        }
        checkOverlapping();
        turnTowards(xcoor, ycoor); // Now move towards the player ship
        move(speed);
        checkBoundaries();
        wordHit(this.word_finished);
    }     
}
