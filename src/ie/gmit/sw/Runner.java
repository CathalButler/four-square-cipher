//Cathal Butler - Data Structures & Algorithms Project - 2018
//Rapid Encryption using the Four-Square Cipher

package ie.gmit.sw;

public class Runner {

	public static void main(String[] args) throws Exception {
		
	//== Run Application ======================================================================
		Menu go = new Menu();
		go.menu();	//Run main menu for random key or user entered key.
		
	//== TESTING =============================================================================== 
			
		/*
		//Testing Speed
		long startTime = System.nanoTime(); //Timer START
		System.out.println("Testing Encryption of DebelloGallic.txt");
		Parser testingParser = new Parser();
		testingParser.read(1, "./Resource/input/DeBelloGallico.txt");
		
		long endTime = System.nanoTime(); //Timer END
		long totalTime = endTime - startTime; //Total Time
		System.out.println("\nNanoTime: " + totalTime);	
		*/
		
		/*
		//Testing Speed in MS
		long startTime = System.nanoTime(); // Timer START
		System.out.println("Testing WarAndPeace.txt");
		Parser parserAndEncrytion = new Parser();
		parserAndEncrytion.read(1, "./Resource/input/WarAndPeace-LeoTolstoy.txt");
		
		final long end = System.nanoTime();		
		System.out.println("Micro Seconds: " + ((end - startTime) / 1000000) + "ms");
		*/
	}// End main;

}
