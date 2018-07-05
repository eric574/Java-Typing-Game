import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

/**
 * This class is used to extract the contents of the .txt files found in the folder
 * The method getScanner() returns a new Scanner object of the contents of the file
 * 
 * @author Eric Liu
 * @version March 2, 2018
 */
public class Scanner_File {
  
    /*
     * @ param filename: The name of the text file - make sure that this is correct
     * @ returns A Scanner object that you can use to read the content of the text file
     */
    public Scanner getScanner (String filename) {
        InputStream myFile = getClass().getResourceAsStream(filename);
        if (myFile != null) return new Scanner(myFile);
        return null;
    }
}