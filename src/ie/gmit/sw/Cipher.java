//Cathal Butler - Data Structures & Algorithms Project - 2018
//Rapid Encryption using the Four-Square Cipher

package ie.gmit.sw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cipher {

	// === M e m b e r V a r i a b l e s ============================
	private char encCrypA;
	private char encCrypB;
	private char decCrypA;
	private char decCrypB;

	int rowA, colA;
	int rowB, colB;

	char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };
	char[] defaultKey1 = { 'Z', 'G', 'P', 'T', 'F', 'O', 'I', 'H', 'M', 'U', 'W', 'D', 'R', 'C', 'N', 'Y', 'K', 'E',
			'Q', 'A', 'X', 'V', 'S', 'B', 'L', }; // 1st half of default key
	char[] defaultKey2 = { 'M', 'F', 'N', 'B', 'D', 'C', 'R', 'H', 'S', 'A', 'X', 'Y', 'O', 'G', 'V', 'I', 'T', 'U',
			'E', 'W', 'L', 'Q', 'Z', 'K', 'P' };// 2st half of default key

	List<Character> storedEncChars = new ArrayList<>();
	List<Character> storedDecChars = new ArrayList<>();

	public void findIndexA(int userOption, char inputCharA) { // Method that find the index of char A from input file
																// and stores it to be used for encryption/decryption.
		// Variables
		int indexA = 0;
		rowA = 0;
		colA = 0;

		if (userOption == 1) { // Encrypt - User Option
			for (int i = 0; i < alphabet.length; i++) { // O(n)

				if (alphabet[i] == inputCharA) { // Search an Array O(n)
					indexA = i; // Index of the char in the alphabet array
					rowA = indexA / 5; // Find the rows for char A
					colA = indexA % 5; // Find the col for char A
				} // end if
			} // end for loop
		} else { // Decrypt - User Option
			for (int i = 0; i < defaultKey1.length; i++) { // O(n)

				if (defaultKey1[i] == inputCharA) { // Search an Array O(n)
					indexA = i; // Index of the char in the default key 1 array.
					rowA = indexA / 5; // Find the rows for char A
					colA = indexA % 5; // Find the col for char A
				} // end if
			} // end for
		} // end if else
	}// End method

	public void findIndexB(int userOption, char inputCharB) {// Method that find the index of char B from input file
																// and stores it to be used for encryption/decryption.
		// Variables
		int indexB = 0;
		rowB = 0;
		colB = 0;

		if (userOption == 1) { // Encrypt - User Option
			for (int i = 0; i < alphabet.length; i++) { // Loop for finding the index of each char in the alphabet. O(n)

				if (alphabet[i] == inputCharB) { // Search an Array O(n)
					indexB = i; // Index of the first char in the alphabet array
					rowB = indexB / 5; // Find the rows for char A
					colB = indexB % 5; // Find the col for char B

				} // end if
			} // end for
			encrypt(); //Calling encryption method once char A & char B index are found.
		} else { // Decrypt - User Option
			for (int i = 0; i < defaultKey2.length; i++) { // O(n)

				if (defaultKey2[i] == inputCharB) { // Search an Array O(n)
					indexB = i; // Index of the 2nd char in the default key 2 array.
					rowB = indexB / 5; // Find the rows for char B
					colB = indexB % 5; // Find the col for char B
				} // end if
			} // end for
		} // end if else
		decrypt(); //Calling decryption method once char A & char B index are found.
	}// End method

	public void encrypt() {// Method that will take the stored indexs from above methods and pass them into
							// the default 2D arrays to encrypt input.
		// Variables
		char[][] fourSquare1 = { { 'Z', 'G', 'P', 'T', 'F' }, // Default 1st half of key
				{ 'O', 'I', 'H', 'M', 'U' }, { 'W', 'D', 'R', 'C', 'N' }, { 'Y', 'K', 'E', 'Q', 'A' },
				{ 'X', 'V', 'S', 'B', 'L' }, };

		char[][] fourSquare2 = { { 'M', 'F', 'N', 'B', 'D' }, // Default 2st half of key
				{ 'C', 'R', 'H', 'S', 'A' }, { 'X', 'Y', 'O', 'G', 'V' }, { 'I', 'T', 'U', 'E', 'W' },
				{ 'L', 'Q', 'Z', 'K', 'P' }, };

		encCrypA = fourSquare1[rowA][colB]; // Encrypt char A. O(1) Accessing a value at an array index
		encCrypB = fourSquare2[rowB][colA]; // Encrypt char B. O(1) Accessing a value at an array index

		storedEncChars.add(encCrypA); // Passing encrypted chars into list. O(n)
		storedEncChars.add(encCrypB); // Passing encrypted chars into list. O(n)
	}// end method

	public void decrypt() {// Method that will take the stored indexs from above methods and pass them into
							// the default 2D arrays to decrypt input.
		// Variables
		char[][] fourSquare0 = { { 'A', 'B', 'C', 'D', 'E', }, { 'F', 'G', 'H', 'I', 'K', },
				{ 'L', 'M', 'N', 'O', 'P', }, { 'Q', 'R', 'S', 'T', 'U', }, { 'V', 'W', 'X', 'Y', 'Z' }, };

		decCrypA = fourSquare0[rowA][colB]; // Decrypt char A. O(1) Accessing a value at an array index
		decCrypB = fourSquare0[rowB][colA]; // Decrypt char B. O(1) Accessing a value at an array index

		storedDecChars.add(decCrypA); // Pass Decrypted chars into list. O(n)
		storedDecChars.add(decCrypB); // Pass Decrypted chars into list. O(n)
	}// End method

	public String getOutput(int userOption) throws IOException { // Method to get output for conslose or text file by
																	// building a string from the arrays lists the
																	// encrypted/decrypted chars are storeds.
		//Varaibles
		String output = null;

		if (userOption == 1) { // Encrypt - User Optionaa

			output = storedEncChars.toString().replace("[", "").replace("]", "").replace(", ", "").trim(); //Make output from encrypted chars with .toString()

		} else { // Decrypt - User Option

			output = storedDecChars.toString().replace("[", "").replace("]", "").replace(", ", "").trim(); //Make output from decrypted chars with .toString()
		} // end if else

		return output; // Return output
	}// end method
}// End class
