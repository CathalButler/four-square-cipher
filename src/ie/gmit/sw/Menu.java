//Cathal Butler - Data Structures & Algorithms Project - 2018
//Rapid Encryption using the Four-Square Cipher

package ie.gmit.sw;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Menu extends Parser {

	// === M e m b e r V a r i a b l e s ============================
	private String inputResource;

	static Scanner console = new Scanner(System.in);

	public void menu() throws IOException {// Method display main heading of programs and then runs main menu.
		System.out.println("\n== \t\t  Cathal Butler\t\t\t ==\n"
				+ "==\t\tFour-Square Cipher\t\t ==\n==  \t   Data Structures & Algoritms   \t ==\n"); // Heading

		System.out.println("You can encrypt or decrypt files and URLs in this program."); // Heading

		cipherMenu(); // Calling main menu after displaying heading.
	}// end method

	public void fileInput(int userOption) throws IOException { // Method to allow the user to select a file from the
																// resourse dir - GUI.
		// Varaibles
		int option;
		String encInputHeading = "Please choose a file to encrypt:";
		String decInputHeadig = "Please choose a file to decrypt:";

		System.out.println("1: File\n2: URL\n3: Return to main menu"); // Options Heading
		option = console.nextInt();

		if (option == 1) {

			if (userOption == 1) { // Encrypt - User Option
				System.out.println("Please Choose the file you wish to encrypt from the on screen pop up:\n"); // Heading

				inputChooser(userOption, encInputHeading); // (userOption) Calling inputerChooser to allow the user
															// enter a file to Encrypt.
			} else { // Decrypt - User Option
				// Heading
				System.out.println("Please Choose the file you wish to decrypt from the on screen pop up:\n");

				inputChooser(userOption, decInputHeadig); // (userOption) Calling inputerChooser to allow the user enter
															// a file to Decrypt.
			} // end if else

		} else if (option == 2) {
			urlInput(userOption); // (option) Calling urlInput method to either encrypt or decrypt a URL of the
									// user choice.
		} else if (option == 3) {
			cipherMenu(); // (option) Calling main menu.
		} else {
			System.out.println("Not an option please choose from the options on screen");
			fileInput(userOption);// (option) not a option calling fileInput method again.
		} // end if else
	}// end method

	public void urlInput(int userOption) throws IOException { // Method allows user enter in a vaild URL link to encrypt
																// or decrypt a URL file. GUI.
		// Variables
		String urlLink;
		int choice;
		boolean checkURL = false;
		String inputHeading = "Choose a URL file to decrypt: ";

		System.out.println("For URL's would you like to\n1: Encrypt\n2: Decrypt\n3: Return to main menu");
		choice = console.nextInt();

		if (choice == 1) {
			urlLink = JOptionPane.showInputDialog(null, "Please enter a URL must include HTTP://", "URL Input",
					JOptionPane.INFORMATION_MESSAGE); // (choice) Displaying input box to input a URL
			try {
				while (!checkURL) {
					readUrl(userOption, urlLink); // Passing in URL if URL is valid and userOption to Parser
				} // end while
			} catch (MalformedURLException e) {
				urlLink = JOptionPane.showInputDialog(null, "Invalid URL, try again!", "URL Error",
						JOptionPane.WARNING_MESSAGE);// Displaying error box if URL is invalid
			} // end try catch
			
			cipherMenu(); //Return to menu

		} else if (choice == 2) {
			System.out.println("Please Choose the file you wish to decrypt from the on screen pop up:\n"); // Heading

			inputChooser(userOption, inputHeading); // (choice) calling inputChooser to allow user decrypt URL file
			readUrl(userOption, inputResource); // Calling realUrl method in parser to read in the file contents chosen
												// from inputChooser
		} else if(choice == 3) {
			System.out.println("Returning to main menu");
			cipherMenu();
		} else {
			System.out.println("Not an option. Please choose from the options on screen");
			urlInput(userOption); // (choice) not a option calling urlInput method again.
		}// end if else
	}// end method

	public void cipherMenu() throws IOException { // Method to allow the user to navigate the cipher.
		// Variables
		int userOption;

		// Heading
		System.out.println("\nWhat would you like to do:\n1: Encrypt\n2: Decrypt\n3: Exit\n ");
		userOption = console.nextInt();

		switch (userOption) {
		case 1:
			fileInput(userOption); // Run file input for encryption then run parser method with the file selected
									// passed into it.
			break;
		case 2:
			fileInput(userOption); // Run file input for decryption then run parser method with the file selected
									// passed into it.
			break;
		case 3:
			System.exit(userOption = 3); // EXIT PROGRAM
		default:
			System.out.println("Not an option please choose from one of the above\n");
			cipherMenu(); // Not an option calling cipher menu again
			break;
		}// end switch
	}// end method

	public void inputChooser(int userOption, String heading) throws IOException { // Method allows user choose a file on
																					// there pc and is then passed into
																					// parser. GUI.
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle(heading);
		jfc.setCurrentDirectory(new File("./Resource"));
		try {
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				inputResource = jfc.getSelectedFile().getAbsolutePath();
				System.out.printf("File you selected is : %s\n\n", jfc.getSelectedFile());
			} // end if

			readFile(userOption, inputResource); // Run Parser for the file chosen from user above

		} catch (Exception e) {
			System.out.println("Returning to Menu");
			fileInput(userOption);
		} // end try catch
	}// end method
}// end class
