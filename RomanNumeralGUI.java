import java.awt.*; //import the necessary libraries so we can utilize them in the class RomanNumeralGUI
import java.util.*;
import javax.swing.*;

/**
 * This RomanNumeralGUI class extends JFrame, and sets up a grid layout of 1 row and 3 columns. It also traverses through two list and prints them to the GUI, along with a text file
 * @author Justin Espinal
 */
public class RomanNumeralGUI extends JFrame { //RomanNumeralGUI class. extend JFrame to our new class so it takes on all attributes of JFrame through inheritance

    /**
     * This constructor takes two lists as parameters and prints them to a GUI. It also prints the tokens of a text file
     */
    public RomanNumeralGUI() //now we make a constructor for our new class and pass the unsorted and sorted list!
    { //here we will recount the amount of tokens in our text file and append them to our GUI, while also appending the two lists
    	
       GridLayout grid = new GridLayout(1, 3); //set the GridLayout to (1,3) so we have 3 slots for the Roman Numerals, converted unsorted list, and converted sorted list
        setLayout(grid); //setting the layout of the GUI using the GridLayout we initialized above called grid
        setSize(800, 500); //we set a random size with good readability for the user
        setTitle("Roman Numeral to Arabic Numbering"); //set a title that talks about what we are doing
        setLocation(500, 100); //make the GUI appear at (500,100) on the screen
        createMenu();
    } //end of constructor

    //use setTextArea to clear screen
    
	private void createMenu() {//start of createMenu
		JMenuItem item;//creating a JMenuItem variable to hold our items
		JMenuBar menuBar = new JMenuBar();//creating a menuBar to display our menus
		
		JMenu fileMenu = new JMenu("File");//creating a new JMenu variable which displays as "File"
		FileMenuHandler fmh = new FileMenuHandler(this);
		
		item = new JMenuItem("Open");//creating a new item variable which displays as "Open"
		item.addActionListener(fmh);//add an action listener to "Open" that detects when it is clicked
		fileMenu.add(item);//add "Open" to the fileMenu drop down
		
		fileMenu.addSeparator();//separate our items
		
		item = new JMenuItem("Quit");//creating a new item variable which displays as "Quit"
		item.addActionListener(fmh);//add an action listener to "Quit" that detects when it is clicked
		fileMenu.add(item);//add "Quit" to the fileMenu drop down
		
		JMenu converterMenu = new JMenu("Converter");//creating a new JMenu variable which displays as "Converter"
		FileMenuHandler cfmh = new FileMenuHandler(this);//creating a new FileMenuHandler to handle converterMenu
		
		item = new JMenuItem("Roman to Arabic");//creating an item button that displays as "Roman to Arabic" 
		item.addActionListener(cfmh);//adding an action listener to detect when it is pressed by the user
		converterMenu.add(item);//adding "Roman to Arabic" to the converterMenu drop down
		
		
		setJMenuBar(menuBar);//set the JMenuBar to the current GUI
		
		
		menuBar.add(fileMenu);//add fileMenu drop down to the menu bar
		menuBar.add(converterMenu);//add convertedMenu drop down to the menu bar
	}
} //end of class RomanNumeralGUI