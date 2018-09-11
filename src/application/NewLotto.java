package application;

/**
 * Class to work with data in Lotto Cure game.
 * @author Ivan 
 * @version 4
 */
import java.util.ArrayList;

public class NewLotto {
	
	/**
	 * Attributes
	 */
	private int numberGuesses;
	private ArrayList<Integer> playerNums;
	private int stars;
	private ArrayList<Integer> matchingNumbers;
	
	/**
	 * Constructor
	 */
	public NewLotto(){
		this.numberGuesses=1;
		this.playerNums = new ArrayList<Integer>();
		this.stars =0;
		this.matchingNumbers = new ArrayList<Integer>();
	}
	
	/**
	 * Method to generate lottery numbers
	 * @return ArrayList of 6 random numbers in range 1-40
	 */
	public ArrayList<Integer> generateNumbers() {

		// array list to hold lotto numbers
		ArrayList<Integer> lottoNumbers = new ArrayList<Integer>();

		// amount of lotto numbers
		int totalNumbers = 0;

		// loop until lotto numbers ArrayList is full
		while (totalNumbers < 6) {
			// generate random number 1-40
			int number = (int) (Math.random() * 40 + 1);
			// ensure no duplicate numbers
			if (!lottoNumbers.contains(number)) {
				// add non duplicate number
				lottoNumbers.add(number);
				// increment total numbers
				totalNumbers++;
			}
		}

		// return numbers
		return lottoNumbers;
	}
	
	 /**
	 * Method to compare player numbers with lottery numbers and count matches
	 * @param lottoNums		ArrayList of 6 random numbers
	 * @param playerNums	ArrayList of players chosen numbers
	 * @return	numberof matching numbers
	 */
	public int compareNumbers(ArrayList<Integer> lottoNums, ArrayList<Integer> playerNums) {

		for (int a : lottoNums) {
			if (playerNums.contains(a)) {
				this.stars++;
			}
		}

		return this.stars;

	}
	
	/**
	 * Method to return a list of matching nums to add to message string
	 * @param lottoNums		ArrayList of 6 random numbers
	 * @param playerNums	ArrayList of players chosen numbers
	 * return String representation of matching numbers
	 */
	public String matchedNumbers(ArrayList<Integer> lottoNums, ArrayList<Integer> playerNums) {
		String correctNumbers = " ";
		// loop through numbers add matches to String
		for (int a : lottoNums) {
			if (playerNums.contains(a)) {
				this.matchingNumbers.add(a);
				correctNumbers += " " + a;
			}
		}
		// return list of matching numbers
		return correctNumbers;
	}
	
	/**
	 * Method to create player feedback messages
	 * @param lottoNums		ArrayList of 6 random numbers
	 * @param playerNums	ArrayList of players chosen numbers
	 * @return	message to player
	 */
	
	public String playerMessage(ArrayList<Integer> lottoNums, ArrayList<Integer> playerNums, String correctNums,
			int matched) {

		// inform player of prize won
		String prizeMessage = (stars >= 4 && stars < 7) ? "You win a " + matched + " star prize!\nClick on Prizes button to claim your prize"
				: "No prize won -" + " matching numbers: " + correctNums + "\nPress Reset to try again";
		// return message to player
		return prizeMessage;
	}

}
