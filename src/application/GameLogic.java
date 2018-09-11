package application;

/**
 * Class to provide functionality to Guessing Game.
 * @author Ivan 
 * @version 3
 *
 */

public class GameLogic {
	// generate random number between 1-100
	private int number = (int) (Math.random() * 100 + 1);

	// number of guesses
	private int numGuess = 1;
	private int maxGuesses = 6;
	private int stars = 0;

	/**
	 * Constructor
	 */
	public GameLogic() {

	}

	/**
	 * Method to check player guess and provide feedback
	 * @param guess player entry
	 * @return result of player entry
	 */
	public String guessResult(int guess) {

		String result = "";

		if (numGuess <= maxGuesses) {
			// String to display information to player
			result = "Guess " + numGuess + ": ";
			// advise player of guesses taken, if too high/low or correct guess
			if (guess < number && numGuess < 6) {
				result += guess + " is too low. Try again";

			} else if (guess > number && numGuess < 6) {
				result += guess + " is too high. Try again";

			} else if (guess != number && numGuess == 6) {
				result += guess + " incorrect - Out of guesses\n Number was " + number + "\nRESET to play again";

			} else {
				result += guess + " is correct. You win a 4 star prize!\nClick on Prizes button to claim your prize\nRESET to play again";
				this.stars = 4;
				numGuess = 7;
			}
			// increment number of guesses
			numGuess++;
		} else {
			result = "No guesses left - Claim your Prize or Press Reset to play again ";
		}
		// return information to player
		return result;
	}

	/**
	 * Method to get random number for testing purposes
	 * 
	 * @return randomly generated number 1-100
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Method to return number of stars for prize
	 * 
	 * @return returns 4 if prize won
	 */
	public int getStars() {
		return this.stars;
	}

}
