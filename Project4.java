//Justin Espinal Project3
import java.util.*;

/**
 * This project will take a text file filled with Roman Numerals, print them to a GUI, then print their sorted and unsorted Arabic counterparts
 * @author Justin Espinal
 */
public class Project4 { //start of project 3
    /**
     * Main is where the TextFileInput is initialized and RomanNumeralGUI is called
     * @param args command line arguments from the user
     */
    public static void main(String[] args) //start of main
    {
        RomanNumeralGUI myGUI = new RomanNumeralGUI(); //Initialize a new RomanNumeralGUI and pass the new linked list

        myGUI.setVisible(true);//set the GUI to visible so the user can select what they want to do
    }
}

