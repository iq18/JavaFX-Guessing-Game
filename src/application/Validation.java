package application;

/**
 * Class containing methods to validate user input.
 * @author Ivan 
 * @version 2
 */



public class Validation {
	
	/**
	 * Constructor of class | No parameters
	 */
	public Validation() {
		
	}
	
	/**
	 * Method to ensure user input is an integer
	 * @param userInput users input as String
	 * @return boolean result true/false
	 */
	public boolean isInteger(String userInput) {
		
		try{
			//attempt to parse String to Integer
			Integer.parseInt(userInput);
			//if valid return true
			return true;
		}
		catch(NumberFormatException e) {
			//false if input cannot be parsed | is not integer
			return false;
		}
	}
	
	/**
	 * Method to ensure user input is not empty
	 * @param userInput
	 * @return boolean true/false
	 */
	public boolean nonEmpty(String userInput) {
		//check length of user input | 0 length = empty input
		if(userInput.length() == 0) {
			return false;
		}
		//returns true for non empty input
		return true;
	}
	
	/**
	 * Method to ensure user entry is in specified number range
	 * @param min	minimum allowable value
	 * @param max	maximum allowable value
	 * @param userInput	user input as a String
	 * @return true/false
	 */
	public boolean numberRange(int min,int max,String userInput) {
		//check number is within allowable range
		if(Integer.parseInt(userInput) >= min && Integer.parseInt(userInput) <= max) {
			return true;
		} else {
			return false;
		}
	}
}
