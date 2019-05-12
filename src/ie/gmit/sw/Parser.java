//Cathal Butler - Data Structures & Algorithms Project - 2018
//Rapid Encryption using the Four-Square Cipher

package ie.gmit.sw;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Parser {

	// === M e m b e r V a r i a b l e s ============================
	private char charA;
	private char charB;

	Cipher cipher = new Cipher();
	ArrayList<Character> parseredOutput = new ArrayList<Character>();

	static Scanner console = new Scanner(System.in);

	public void readFile(int userOption, String res) {// Method to read a text file and pass the characters into a
														// arraylist.

		try {
			// Variables
			BufferedReader br = new BufferedReader(new FileReader(res));
			String line;

			long startTime = System.nanoTime(); // START TIMER
			while ((line = br.readLine()) != null) { // Reading in each line from file. O(N^2):
				line = line.toUpperCase().replaceAll("[^A-Z ]", "").replace("j", "i"); // Making all text uppercase and
																						// replacing j with i to fit the
																						// cipher requirements.

				for (char ch : line.toCharArray()) { // Loop through each char of each line. O(N):
					if (Character.isLetter(ch)) {
						parseredOutput.add(ch); // Passing chars from file into a Array list. O(n)

					} // end if
				} // end for loop for char
			} // end while
			br.close(); // close file

			bigrams(userOption); // Calling method bigrams.

			final long end = System.nanoTime(); // STOP TIMER
			System.out.println("Took: " + (end - startTime) + " Nano Seconds"); // Print time
			outputToFile(userOption); // Calling method output to file after a file has either encryption or
										// decrytion.

		} catch (IOException e) {
			System.out.println("Could not access file!\n" + e);
		}
	}// end parser

	public void readUrl(int userOption, String urlRes) throws IOException { // Method to read in URL data and pass it
																			// into an array list
		// Variables
		URL oracle = new URL(urlRes);
		BufferedReader br = new BufferedReader(new InputStreamReader(oracle.openStream()));
		String line;

		long startTime = System.nanoTime(); // Timer START
		while ((line = br.readLine()) != null) { // Reading in each line from file. O(N^2):
			line = line.toUpperCase().replaceAll("[^A-Z ]", "").replace('j', 'i');// Making all text uppercase and
																					// replacing j with i to fit the
																					// cipher requirements.

			for (char ch : line.toCharArray()) { // Loop through each char of each line. O(n)
				if (Character.isLetter(ch)) {
					parseredOutput.add(ch); // Passing chars from file into list. O(n)
				} // end if
			} // end for loop for char
		} // end while
		br.close(); // close file

		bigrams(userOption);

		final long end = System.nanoTime(); // STOP TIMER
		System.out.println("Took: " + (end - startTime) + " Nano Seconds"); // Print time
		outputToFile(userOption);
	}

	public void bigrams(int userOption) { // Method to get char A & char B from the Array list to be then passed into
											// the index methods in the cipher class.

		for (int i = 0; i < ((parseredOutput.size() % 2 == 0) ? parseredOutput.size()
				: parseredOutput.size() - 1); i += 2) { // A loop that will pair 2 chars from the array list. O(n)

			charA = parseredOutput.get(i); // Get char A. O(1)
			charB = parseredOutput.get(i + 1); // Get char B. O(1)

			// Passing in chars to find index in arrays
			cipher.findIndexA(userOption, charA); // first find charA indexs then
			cipher.findIndexB(userOption, charB); // Char b`s
		} // end loop
	}// end method

	public void outputToFile(int userOption) throws IOException { // Method that output the encrypted/decrypted text to
																	// either the console or a text file of choice by
																	// the user.

		int userChoice;
		Menu m = new Menu();
		FileWriter output = null;

		System.out.println(
				"\nWould you like to output the encrypted/decrypted text to console or a text file\n 1: Console\n 2: Text File\n");
		userChoice = console.nextInt();

		if (userChoice == 1) { // Print to console
			System.out.println(cipher.getOutput(userOption));
			m.cipherMenu();
		} else { // Print to file
			try {
				long startTime = System.nanoTime(); // Timer START
				output = new FileWriter(outputChooser());
				BufferedWriter writer = new BufferedWriter(output);
				writer.append(cipher.getOutput(userOption)); // Output to text file chosen by user
				writer.flush();
				writer.close(); // Close file
				final long end = System.nanoTime(); // STOP TIMER
				System.out.println("Outputed to File\nTook: " + (end - startTime) + " Nano Seconds");
			} catch (Exception e) {
				System.out.println("Returning to main menu");
				m.cipherMenu();
			} // end try catch
		} // end if else
		m.cipherMenu();
	}// end method

	public String outputChooser() {

		// Variables
		String dir = null;

		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Choose a save location: ");
		jfc.setCurrentDirectory(new File("./Resource/output/"));

		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			dir = jfc.getSelectedFile().getAbsolutePath();
			System.out.printf("File you selected is : %s\n\n", jfc.getSelectedFile());
		}//end if
		return dir;
	}// end method
}// end class
