package application;

/**
 * Class to populate a HashMapread with prize key value pairs 
 * from a text file of prizes.
 * 
 * @author Ivan 
 * @version 3
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileMap {

	// attribute
	private HashMap<String, String> hm;
	
	/**
	 * Constructor
	 */
	public FileMap() {
	}

	/**
	 * Method to populate a HashMap from a text file
	 * 
	 * @return populated HashMap
	 */
	public HashMap<String, String> generateHashMap(int stars) {

		hm = new HashMap<String, String>();

		try {
			// new FileReader/ BufferedReader objects to read from file
			BufferedReader in = new BufferedReader(new FileReader("Prizes.txt"));

			// String to hold lines read from file
			String line = "";
			// loop until all lines processed
			while ((line = in.readLine()) != null) {
				// split lines at tab into pairs
				String words[] = line.split("\t");
				// add Key-Value pairs to HashMap
				// switch to ensure only prizes matching the player's prize
				// level are added
				switch (stars) {
				case 4:
					if (words[1].equals("4")) {
						hm.put(words[0], words[2]);
					}
					break;
				case 5:
					if (words[1].equals("5")) {
						hm.put(words[0], words[2]);
					}
					break;
				case 6:
					if (words[1].equals("6")) {
						hm.put(words[0], words[2]);
					}
					break;
				default:
					break;
				}
			}

			// close BufferedReader
			in.close();
			// System.out.println(hm.toString());//DEBUG

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");

		} catch (IOException e) {
			System.out.println("IO Exception");
		}

		// return populated HashMap
		return hm;
	}
}
