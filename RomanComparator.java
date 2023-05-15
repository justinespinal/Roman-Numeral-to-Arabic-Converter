import java.util.Comparator;
/**
 * 
 * @author Justin Espinal
 * This class is used so that our new TreeMap can be sorted in ascending order
 *
 */
public class RomanComparator implements Comparator <RomanNumeral>{//make a new comparator so we can compare the values of two roman numerals in our tree
	/**
	 * @param numeral1 first roman numeral being compared
	 * @param numerl2 second roman numeral being compared
	 * @return reutrns -1,0,1 depending on the compareTo method inside our Roman Numeral class
	 */
	public int compare(RomanNumeral numeral1, RomanNumeral numeral2){
		return numeral1.compareTo(numeral2);
	}

}
