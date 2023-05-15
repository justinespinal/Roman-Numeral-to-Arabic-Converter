import java.util.HashMap;

/**
 * This class allows users to create a variable of type RomanNumeral, and also get its Arabic value
 * @author Justin Espinal
 */
public class RomanNumeral { //start of class RomanNumeral
    private String Roman; //initialize a private instance variable to hold the RomanNumeral
    private int Arabic; //Initialize a private instance variable to hold the converted Arabic number

    /**
     * 
     * @param r The String inputed by the user to be converter to a Roman Numeral
     */
    public RomanNumeral(String r) //constructor that takes in a String as a parameter to convert to type RomanNumeral
    {
        if (!r.matches("[MDCLXVI]+")) {//if the new Roman Numeral has characters besides MDCLXVI then it is incorrect
            throw new IllegalRomanNumeralException(r);//since it is invalid we throw a new IllegalRomanNumeralException
        }
        //if valid we continue creating the Roman Numeral
        Roman = r; //set Roman equal to parameter
        Arabic = romanToInt(Roman); //convert said Roman input to Arabic using the romanToInt method and store it in Arabic
    }

    /**
     * 
     * @return String In this method we return the value of Roman to the user for later use
     */
    public String getRoman() //get method for Roman
    {
        return Roman; //return the Roman value to the user
    }

    /**
     * 
     * @param r setting the current value of Roman to the new value passed by the user into the parameters
     */
    public void setRoman(String r) //set method for Roman
    {
        Roman = r; //set Roman equal to the parameter given by the user
    }

    /**
     * 
     * @return int returns the Arabic value to the user
     */
    public int getArabic() //get method for Arabic
    {
        return Arabic; //return the Arabic value to the user
    }

    /**
     * @param other new object passed into the equals method by the user to check if it is equal to the current object
     *@return returns true if both objects are equal, false if not
     */
    public boolean equals(Object other) { //equals method for RomanNumeral
        return (other != null &&
            getClass() == other.getClass() &&
            Roman.equals(((RomanNumeral) other).Roman)
        ); //this checks that the Object does not equal null, is of the same class, and if both hold the same value. if this is all true then hey are equal!
    } //end of equals method

    /**
     * 
     * @param other newly passed RomaNumeral that we would like to compare to our current Numeral
     * @return int we return a integer that corresponds to a less than, greater than, or equal to value. 1:greater, 0:equal, -1:less than
     */
    public int compareTo(RomanNumeral other) { //compareTo method for RomanNumeral
        if (Arabic > other.Arabic) //if the current Arabic value if greater then the Arabic value passed in the parameters return 1
        {
            return 1;
        } 
        else if (Arabic == other.Arabic) //else if the current arabic value is equal to the arabic value passed in the parameters return 0
        {
            return 0;
        } 
        else return -1; //if the current arabic value is less than the arabic value passed in the parameters return -1
    }

    /**
     * @return String here we just return the Roman Numeral value
     */
    public String toString() { //toString method for RomanNumeral
        return Roman; //here we simply just return the String value of Roman
    }

    /**
     * 
     * @param s pass a Roman Numeral of type string to the function and return its Arabic value
     * @return int Arabic value is returned to the user
     */
    public static int romanToInt(String s) //Method to convert Roman numerals to Arabic numerals given by Professor Lord
    {
    	if (s == null || s.length() == 0){
            return -1;
      }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length(), result = map.get(s.charAt(len - 1));
        for (int i = len - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
                result += map.get(s.charAt(i));
            else
                result -= map.get(s.charAt(i));
        }
        return result;
    }
}