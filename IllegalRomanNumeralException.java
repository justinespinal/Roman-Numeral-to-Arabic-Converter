//creating a new error Exception for Roman Numerals and extending IllegalArgumentException, so that it inherits all
//attributes of IllegalArgument Exception

/**
 * 
 * This class allows us to create a new ArgumentException that detects when an Illegal Roman Numeral is entered
 * @author Justin Espinal
 * 
 */
public class IllegalRomanNumeralException extends IllegalArgumentException { //we extend IllegalArgumentException so IllegalRomanNumeralException takes on all of its attributes

    /**
     * Here our constructor will have our parameter as the message that should be thrown to the user through a super();
     * @param message This is the message that should be passed to the console and display as an error
     */
    public IllegalRomanNumeralException(String message) {
        super(message); //pass the error message given to super
    } //end of constructor

} //IllegalRomanNumeralException