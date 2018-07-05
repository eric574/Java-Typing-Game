import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This actor class is used to create text objects that can be displayed on buttons and so forth
 * 
 * @author Eric Liu
 * @version March 2, 2018
 */
public class Text extends Actor {
    
    public String textstring, colour_string = "";
    private Color colour = null, colour1 = null; // Used for the Greenfoot color class
    
    /**
     * The 1st constructor for the class
     * Use RGB for Color Class
     */
    Text (String text, int size, String color) {
        this.textstring = text;
        this.colour_string = color;
        // Set the type of color, from the given argument
        // For the foreground colour
        if (color.equalsIgnoreCase("white")) {
            colour = new Color(255, 255, 255);
        }
        else if (color.equalsIgnoreCase("black")) {
            colour = new Color(0, 0, 0);
        }
        else if (color.equalsIgnoreCase("red")) {
            colour = new Color(255, 0, 0);
        }
        else if (color.equalsIgnoreCase("blue")) {
            colour = new Color(0, 0, 255);
        }
        else if (color.equalsIgnoreCase("green")) {
            colour = new Color(0, 255, 0);
        }
        else if (color.equalsIgnoreCase("pink")) {
            colour = new Color(210, 144, 212);
        }
        else if (color.equalsIgnoreCase("orange")) {
            colour = new Color(230, 145, 0);
        }
        else if (color.equalsIgnoreCase("gray")) {
            colour = new Color(201, 194, 181);
        }
        else if (color.equalsIgnoreCase("magenta")) {
            colour = new Color(142, 79, 201);
        }
        else if (color.equalsIgnoreCase("yellow")) {
            colour = new Color(222, 218, 9);
        }
        else if (color.equalsIgnoreCase("cyan")) {
            colour = new Color(9, 222, 222);
        }
        setImage(new GreenfootImage(text, size, colour, new Color(0, 0, 0, 0)));
    }
    
    /**
     * The 2nd constructor for the class
     * Use RGB for Color Class
     */
    Text (String text, int size, String color, String color1) {
        this.textstring = text;
        this.colour_string = color;
        // Set the type of color, from the given argument
        // For the foreground colour
        if (color.equalsIgnoreCase("white")) {
            colour = new Color(255, 255, 255);
        }
        else if (color.equalsIgnoreCase("black")) {
            colour = new Color(0, 0, 0);
        }
        else if (color.equalsIgnoreCase("red")) {
            colour = new Color(255, 0, 0);
        }
        else if (color.equalsIgnoreCase("blue")) {
            colour = new Color(0, 0, 255);
        }
        else if (color.equalsIgnoreCase("green")) {
            colour = new Color(0, 255, 0);
        }
        else if (color.equalsIgnoreCase("pink")) {
            colour = new Color(210, 144, 212);
        }
        else if (color.equalsIgnoreCase("orange")) {
            colour = new Color(230, 145, 0);
        }
        else if (color.equalsIgnoreCase("gray")) {
            colour = new Color(201, 194, 181);
        }
        else if (color.equalsIgnoreCase("magenta")) {
            colour = new Color(142, 79, 201);
        }
        else if (color.equalsIgnoreCase("yellow")) {
            colour = new Color(222, 218, 9);
        }
        else if (color.equalsIgnoreCase("cyan")) {
            colour = new Color(9, 222, 222);
        }
        
        // For the background colour
        if (color1.equalsIgnoreCase("white")) {
            colour1 = new Color(255, 255, 255);
        }
        else if (color1.equalsIgnoreCase("black")) {
            colour1 = new Color(0, 0, 0);
        }
        else if (color1.equalsIgnoreCase("red")) {
            colour1 = new Color(255, 0, 0);
        }
        else if (color1.equalsIgnoreCase("blue")) {
            colour1 = new Color(0, 0, 255);
        }
        else if (color1.equalsIgnoreCase("green")) {
            colour1 = new Color(0, 255, 0);
        }
        else if (color1.equalsIgnoreCase("pink")) {
            colour1 = new Color(210, 144, 212);
        }
        else if (color1.equalsIgnoreCase("orange")) {
            colour1 = new Color(230, 145, 0);
        }
        else if (color1.equalsIgnoreCase("gray")) {
            colour1 = new Color(201, 194, 181);
        }
        else if (color1.equalsIgnoreCase("magenta")) {
            colour1 = new Color(142, 79, 201);
        }
        else if (color1.equalsIgnoreCase("yellow")) {
            colour1 = new Color(222, 218, 9);
        }
        else if (color1.equalsIgnoreCase("cyan")) {
            colour1 = new Color(9, 222, 222);
        }
        else {
            colour1 = new Color(0, 0, 0, 0);
        }
        setImage(new GreenfootImage(text, size, colour, colour1));
    }
    
    public String getColor () {
        return this.colour_string;
    }
    
    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // Add your action code here
        
    }    
}
