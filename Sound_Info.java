import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Used to store which song the user chose in the music settings screen and the volume level
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Sound_Info extends Actor {
    
    public int songtype; // Which song did the user choose
    public int volume; // Adjust the volume of the chosen song
    
    public Sound_Info (int type, int level) {
        this.songtype = type;
        this.volume = level;
    }
    
    public int getVolume () {
        return this.volume;
    }
    
    public int getSong () {
        return this.songtype;
    }
    
    /**
     * Act - do whatever the Sound_Info wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // Add your action code here.
    }    
}
