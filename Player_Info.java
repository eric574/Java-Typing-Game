import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Stores info for the player, including the username
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Player_Info extends Actor implements Comparable<Player_Info> {
    
    private String name;
    private int score, level;
    
    public Player_Info (String username, int scorenum, int leveltype) {
        this.name = username;
        this.score = scorenum;
        this.level = leveltype;
    }
    
    public String getName () {
        return this.name;
    }
    
    public int getScore () {
        return this.score;
    }
    
    public int getLevel () {
        return this.level;
    }
    
    @Override
    public int compareTo (Player_Info s) { // Is used to sort the list in reverse order (greatest to least)
        return s.score - this.score;
    }
    
    /**
     * Act - do whatever the Player_Info wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // Add your action code here.
    }    
}
