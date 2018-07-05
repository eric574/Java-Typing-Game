import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background_Info here.
 * 
 * @author (Eric, Justin Benji) 
 * @version (a version number or a date)
 */
public class Background_Info extends Actor {
    
    private String background_type;
    
    public Background_Info (String type) {
        this.background_type = type;
    }
    
    public String getType () {
        return this.background_type;
    }
    
    /**
     * Act - do whatever the Background_Info wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // Add your action code here.
    }    
}
