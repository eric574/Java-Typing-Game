import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Used to check to see if the user input matches with the string and then updates the word(s) on screen
 * 
 * @author Eric Liu
 * @version March 11, 2018
 */
public class Word_Manager extends Actor {
    
    private boolean hasActiveWord = false;
    private Word activeWord = null;
    private ArrayList<Word> wordslist;
    private boolean space_pressed = false;
    private Stack<Word> st;
    private Word arr[]; // THe array to store the current Words in the world
    private int idx = 0; // This variable stores the current index of the word in the array
    private boolean backspace_pressed = false;
    private boolean done = false;
    private int num_enemies = 0; // The variable storing the number of enemies
    private int curridx = 0;
    private boolean up_pressed = false; // For whether the user pressed the up key or not
    private boolean down_pressed = false; // For whether the user pressed the down key or not
    
    /**
     * Constructors:
     * @param words = list of words to be processed
     * @param enemy_cnt = number of words in ucurrent level
     */
    public Word_Manager (ArrayList<Word> words, int enemy_cnt) {
        this.wordslist = words;
        this.num_enemies = enemy_cnt;
        arr = new Word[wordslist.size()]; // Re-instantiate the array every time
        st = new Stack<Word>();
        // Add the string values of the words from the list to the array
        for (Word word : wordslist) {
            arr[idx++] = word;
        }
        Object words_[] = wordslist.toArray();
        // Can also use: arr = Arrays.copyOf(wordslist, words_.length, Word[].class);
    }
    
    /**
     * Set the boolean value to true
     */
    public void Backspace_Listener () {
        this.backspace_pressed = true;
    }
    
    /**
     * Method sets the boolean value equal to true
     * Call on this method if the user pressed space
     */
    public void Space_Listener () {
        this.space_pressed = true;
    }
    
    /**
     * Method sets the boolean value of up_pressed to true
     */
    public void Up_Key_Pressed () {
        this.up_pressed = true;
    }
    
    /**
     * Method sets the boolean value of down_pressed to true
     */
    public void Down_Key_Pressed () {
        this.down_pressed = true;
    }
    
    /**
     * Method is used to process whenever the user types a character
     */
    public void TypeLetter (char letter) {
        if (hasActiveWord) { // If the word hasn't been fully typed yet/is active
            activeWord.setWord(activeWord.getWord(), "green"); // Set the currently active word to green
            if (activeWord.GetNextLetter() == letter) {
                activeWord.TypeLetter();
                done = true;
            }
        }
        // The word isn't fully typed
        else {
            if (num_enemies > 0) {
                for (int i=0; i<num_enemies; i++) {
                    Word word = wordslist.get(i);
                    if (word.GetNextLetter() == letter && (word.getColour().equals("green") || word.getColour().equals("red"))) {
                        activeWord = word;
                        hasActiveWord = true;
                        done = true;
                        word.TypeLetter();
                        break;
                    }
                    
                    else if (word.GetNextLetter() != letter && (word.getColour().equals("green") || word.getColour().equals("red"))) {
                        if (done) done = false;
                        GreenfootSound error = new GreenfootSound("error.aiff"); // Error sound
                        error.play(); // Play the error sound if character is typed incorrectly
                        break;
                    }
                }
            }
        }
        if (hasActiveWord && activeWord.WordTyped()) { // Remove the word from the list if the user has finished typing the word
            hasActiveWord = false;
            // done = false;
            wordslist.remove(activeWord);
        }
    }
    
    public boolean typedCorrectly () {
        return this.done;
    }
    
    public boolean isActive () {
        return this.hasActiveWord;
    }
    
    /**
     * Method returns the word that the user has chosen to type
     */
    public Word returnWord () {
        return this.activeWord;
    }
    
    public ArrayList<Word> Get_List () {
        return this.wordslist; // Useful method to return list of words
    }
    
    public Word [] getWords () {
        return this.arr;
    }
    
    /**
     * Act - do whatever the Word_Manager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // Add your action code here.
    }    
}
