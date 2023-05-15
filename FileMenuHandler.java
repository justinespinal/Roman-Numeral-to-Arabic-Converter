
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;
/**
 * This class allows us to control user "input" from the File Menu through various different methods
 * @author Justin Espinal
 */

public class FileMenuHandler extends JFrame implements ActionListener { //start of FileMenuHandler

    JFrame jframe; //creating a new JFrame variable to store our passed JFrame
    StringTokenizer myTokens; //creating a token variable to iterate through our newly selected file
    
    //create our text areas that will later be filed
    TextArea RomanNumerals;
    TextArea Sorted;
    
    int countText = 0; //this will be used to check if a text file has previously been entered

    /**
     * Constructor for FileMenuHandler that will initialize our new JFrame
     * @param jf The jframe passed in by the user that we can later manipulate
     */
    public FileMenuHandler(JFrame jf) //constructor of FileMenuHandler
    {
        jframe = jf; //initialize the previous jframe variable as the parameter passed by the user
    } //end of constructor

    /**
     * This method allows us to detect which event was selected by the user
     * @param event passed an event through an ActionEventListener that will decided what our program will do for the user
     */
    public void actionPerformed(ActionEvent event) //actionPeformed
    {
        String menuName;
        menuName = event.getActionCommand(); //this will detect which option is selected in the File Menu by the user

        if (menuName.equals("Open")) {
            openFile(); //If "Open" is selected by the user, call the openFile method in FileMenuHandler
        } 
        else if (menuName.equals("Quit")) {
            System.exit(0); //If "Quit" is selected by the user, call System.exit(0) to end the program
        } 
        else if (menuName.equals("Roman to Arabic")) {
            convert(); //If "Roman to Arabic" is selected by the user, call the convert method in FileMenuHandler
        }
    } //end of actionPerformed

    /**
     * When Roman to Arabic is selected, this method is called to ask the user to enter a Roman Numeral
     */
    private void convert() { //convert method

        //request user input by creating a string and calling JOptionPane.showInputDialog
        String numeral = JOptionPane.showInputDialog(null, "Enter a Roman Numeral, Enter STOP to end the program!");
        RomanNumeral newNumeral; //create a new Roman Numeral variable to store the newly inputed numeral

        if (numeral.equalsIgnoreCase("STOP")) System.exit(0); //if user enters STOP end the program fully

        try { //create a try catch case so that if the user enters an invalid Roman numeral, a IllgealRomanNumeralException
            //will be thrown through the RomanNumeral constructor and the user will be told that it is not a Roman Numeral
            newNumeral = new RomanNumeral(numeral);
            JOptionPane.showMessageDialog(null, newNumeral + " is: " + newNumeral.getArabic());
            //if it passes, tell the user the numerals arabic value
        } catch (IllegalRomanNumeralException e) { //catch the IllegalRomanNumeralException
            System.out.println("Invalid Roman Numeral: " + numeral); //if invalid print to console
            JOptionPane.showMessageDialog(null, "This is not a numeral!"); //tell the user it is invalid
        }
    } //end of convert

    /**
     * This method opens our file selected by the user
     */
    private void openFile() { //start of openFile method
        JFileChooser chooser; //create a new chooser variable that will hold the file chosen by the user
        int status;
        chooser = new JFileChooser(); //initialize our variable
        status = chooser.showOpenDialog(null); //show the dialog to the user to enter a file address
        if (status == JFileChooser.APPROVE_OPTION) //if it is a valid address
        {
            readSource(chooser.getSelectedFile()); //call readSource to tokenize the selected file
        } else JOptionPane.showMessageDialog(null, "Open File Dialog canceled");
    } //openFile

    
    /**
     * This method will allow us to read our text file, tokenize it, and print it to the user
     * @param selectedFile passes our selected file that was given by the user, and displays its contents in a GUI
     * @exception IllegalRomanNumeralException thrown when the user inputs a Roman Numeral that is invalid
     */
    private void readSource(File selectedFile) { //start of readSource

    	//creating a treemap that takes romannumerals as keys and integers as values so we can store and sort out data
        TreeMap <RomanNumeral, Integer> numerals = new TreeMap<RomanNumeral, Integer>(new RomanComparator());
        
        String selectedFileName = selectedFile.getAbsolutePath(); //store the path of the selected file
        TextFileInput inFile = new TextFileInput(selectedFileName); //pass input from the file through a TextFileInput variable

        Container myContentPane = jframe.getContentPane(); //create a new container to hold our new text areas
        if (countText == 0) //this makes it so the program only creates a text area when we are entering out first text file
        {
            RomanNumerals = new TextArea(); //creating a text area to store out Roman Numerals
            Sorted = new TextArea(); //creating a text area to store our sorted converted Roman Numerals

            RomanNumerals.setBackground(Color.pink);
            Sorted.setBackground(Color.cyan);

            //add our new text areas to our content pane with .add
            myContentPane.add(RomanNumerals);
            myContentPane.add(Sorted);
            countText++;
        }

        //sets each text area to have no contents, so that whenever a new text file is entered, it will start from scratch
        RomanNumerals.setText("");
        Sorted.setText("");

        String line = inFile.readLine(); //create a string variable to store a line from the text file and later tokenize
        myTokens = new StringTokenizer(line, ","); //set the StringTokenizer to tokenize our previously initialized String

        //creating tile line for each of our text areas for better user readability
        RomanNumerals.append("Roman Numerals: \n");
        Sorted.append("Sorted Arabic Values: \n");

        int count = 0; //creating a count variable to count the amount of numerals in our new file
        String curr; //creating a curr string variable to hold our current token

        while (line != null) //we continue the while loop until we hit a line with no tokens/characters on it
        {
            if (myTokens.hasMoreTokens()) //if my current line contains tokens we enter that token into the current index(i)
            {
                curr = myTokens.nextToken(); //iterating to the next token
                try {
                    RomanNumeral rom = new RomanNumeral(curr); //Create a RomanNumeral variable and set it equal to the current token
                    //if it is invalid Roman Numeral, an IllegalRomanNumeralException will be thrown
                    
                    numerals.put(rom,rom.getArabic());//put our new roman numeral into our treemap, with the numeral as a key and the arabic value as the value
                    count++; //increment count which indicates we have counted a numeral
                    
                } catch (IllegalRomanNumeralException e) //catch any IllegalRomanNumeralException
                {
                    System.out.println("Invalid Roman Numeral: " + curr); //print to the console that this numeral is invalid
                }
            }
            if (!myTokens.hasMoreTokens()) //if after entering a numeral and we are now out of tokens, go to the next line!
            {
                line = inFile.readLine(); //reinitialize line to the next line, this must be done each time we go to another line
                //if we do this and hit a line thats null then we are done, but if we don't, we recall the myTokens and enter the new line
                if (line != null) myTokens = new StringTokenizer(line, ",");
                //this is done to avoid an error occurring! When line == null, then there are no more tokens in the text file and we can stop
            }
        } //end of while loop

        //create an entrySet so that we can iterate through our list
        Set<Entry<RomanNumeral, Integer>> entry = numerals.entrySet();
        Iterator i = entry.iterator();
        Map.Entry <RomanNumeral, Integer> value; //store the key and value of the current branch
        
        while(i.hasNext()) {//while there are remaining keys
        	value = (Map.Entry)i.next();
        	RomanNumerals.append((value.getKey()).toString() + "\n");//append our key to our Roman Numeral Text Area
        	Sorted.append(Integer.toString(value.getValue())+"\n");//append our key's value to our Sorted Text Area
        }
        
        
        jframe.setVisible(true); //finally set the jframe to visible so the user can see the output
    } //end of readSource
} //FileMenuHandler