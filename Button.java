import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is used for creating new Button instances and objects to be displayed
 * 
 * @author Eric Liu
 * @version March 2, 2018
 */
public class Button extends Actor {
    
    private boolean pressed = false;
    private String name = "";
    private GreenfootSound pressedsound = new GreenfootSound("select.mp3");
    
    /**
     * Main constructor for Button class
     * @param name = name of identifier for Button
     * @param image = GreenfootImage to be displayed
     */
    public Button (String name, GreenfootImage image) {
        this.name = name;
        setImage(image);
        pressedsound.setVolume(80);
    }
    
    public boolean isPressed () {
        return this.pressed;
    }
    
    public String getName () {
        return this.name;
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // Add your action code here.
        if (!pressed) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null && mouse.getActor() == this && mouse.getButton() == 1) {
                pressed = true;
                pressedsound.play();
            }
        }
    }    
}
