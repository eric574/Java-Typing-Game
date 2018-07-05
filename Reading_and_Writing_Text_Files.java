import java.io.*;
import java.util.*;
import java.net.*;

/*
 * This class is used to read and write text files using FileReader and BufferedReader!!!
 * This will be used to read and write from the scores.txt text file
 * @author Eric Liu
 * @version March 2, 2018
 */

public class Reading_and_Writing_Text_Files {
    
    // The name of the file to open.
    private String fileName;
    private String s = ""; // Save what to print to the file
    private String last = "";
    // We can also use an array to store the Strings and then convert it into an ArrayList of Strings
    private String [] wordsarr = new String[2400];
    private int idx = 0; // For adding the Strings into the array
    private ArrayList<String> wordslist = new ArrayList<String>(); // Store the list of words
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private Scanner sc = null; // Scanner for reading the words
    
    /*
     * Constructor for this class
     * @param s = the name of the file to be read and or extracted from
     */
    public Reading_and_Writing_Text_Files (String s) {
        this.fileName = s;
    }
    
    /*
     * @ param filename: The name of the text file - make sure that this is correct
     * @ returns A Scanner object that you can use to read the content of the text file
     */
    public Scanner getScanner (String filename) {
        InputStream myFile = getClass().getResourceAsStream(filename);
        if (myFile != null) return new Scanner(myFile);
        return null;
    }
    
    /**
     * Alternative method of reading words, to using BufferedReader
     */
    public void ReadWords1 () {
        String line = null;
        idx = 0;
        try {
            if (sc == null) sc = getScanner(fileName);
            while (sc.hasNextLine()) {
                wordsarr[idx++] = sc.nextLine();
                wordslist.add(sc.nextLine()); // Add the words that are separated by lines in the .txt files
            }
            // Always close the scanner object(s)
            if (sc != null) sc.close();
        }
        catch (InputMismatchException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * For the words text files, there is no need to split the contents since they're separated by a new line
     */
    public void ReadWords () {
        // This will reference one line at a time
        String line = null;
        idx = 0;
        try {
            // FileReader reads text files in the default encoding.
            fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                // System.out.println(line);
                wordsarr[idx++] = line;
                wordslist.add(line); // Add the words that are separated by lines in the .txt files
            }
            // Always close files.
            // bufferedReader.close();
            bufferedReader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
            // Or we could just do this: ex.printStackTrace();
        }
    }
    
    /**
     * Returns an ArrayList of Strings for generating the words
     */
    public ArrayList<String> getList () {
        //wordslist = new ArrayList<String>(Arrays.asList(wordsarr));
        return this.wordslist;
    }
    
    /**
     * Mainly used for writing to the score .txt file
     * Use FileWriter and BufferedWriter to write, since the Scanner class cannot write
     */
    public void Write (Player_Info player) {
        try {
            // Assume default encoding.
            fileWriter = new FileWriter(fileName, true); // true = append, false = overwrite
            // Always wrap FileWriter in BufferedWriter.
            bufferedWriter = new BufferedWriter(fileWriter);
            // Note that write() does not automatically append a newline character.
            last = player.getName() + ", " + String.valueOf(player.getScore()) + ", " + String.valueOf(player.getLevel());
            s += last + "\n";
            bufferedWriter.write(s);
            // Always close files.
            // bufferedWriter.close();
            bufferedWriter.flush();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
            // Or we could just do this: ex.printStackTrace();
        }
    }
    
    /**
     * Second method used for writing to the score .txt file
     * Use PrintWriter or FileOutputStream to do so
     */
    public void Write1 (Player_Info player) {
        // Note that write() does not automatically append a newline character.
        last = player.getName() + ", " + String.valueOf(player.getScore()) + ", " + String.valueOf(player.getLevel());
        s += last + "\n";
        try {
            FileOutputStream fOut = new FileOutputStream (System.getProperty("user.home") + "/Desktop/scores.txt", true); // For the home directory of Windows PCs
            fOut.write(s.getBytes()); // Output using bytes
            fOut.close(); // Always close
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

