import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For displaying the word on-screen
 * 
 * @author Eric Liu
 * @version March 11, 2018
 */
public class Word_Display extends Actor {
    
    private Text text = null;
    private int size = 19; // 17 too small?
    private int fallSpeed;
    private String word_color = "";
    private Word word = null;
    
    public Word_Display () {
        
    }
    
    /**
     * This method is used to set the String word as well as to change the colour
     */
    public void SetWord (Word _word, String colour) {
        this.word = _word;
        this.word_color = colour;
        this.text = new Text(word.getWord(), 19, this.word_color);
    }
    
    /**
     * Method is called upon whenever the user types in the correct character
     */
    public void Typed_Correctly () {
        text = new Text(text.textstring.substring(1), size, "red");
    }
    
    /**
     * Method is called to remove the last letter from the word on the screen
     * We don't need to change the actual word object itself
     */
    public void Remove_Letter () {
        text = new Text(text.textstring.substring(0, text.textstring.length() - 1), size, this.word_color);
    }
    
    /**
     * Call on this method when the user has finished typing the word on the screen
     */
    public void RemoveWord () {
        getWorld().removeObject(this);
    }
    
    /**
     * First method for adding the word to the screen
     * Call on this method for displaying the words onto the screen
     */
    public void AddWord (Text text, int xcoor, int ycoor) { // Method adds the word onto the screen
        if (text != null) getWorld().addObject(text, xcoor, ycoor);
    }
    
    /**
     * Second method for adding the word to the screen
     * This method adds the current text onto the screen
     */
    public void AddWord (int xcoor, int ycoor) {
        if (this.text != null) getWorld().addObject(this.text, xcoor, ycoor);
    }
    
    /**
     * Act - do whatever the Word_Display wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // May decide to put the method of adding the object to the world screen here
    }    
}