/*******************************************************************************
 * File Name:     Printer.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines a printer.
 *******************************************************************************/

public class Printer {
	protected final double PRICE_BW = 0.15;         //Price of black & white paper per sheet
	protected final double PRICE_COLOUR = 0.50;     //Price colour paper per sheet
	protected long id;                              //ID of printer
	protected int maxPaper;                         //max amount of paper(s) allowed in the printer
	protected int numPaper;                         //amount of paper currently in the printer
  
	public Printer (long identification, int max, int num) {
		id = identification;
		maxPaper = max;
		numPaper = num;
	} //Printer constructor
  
	public String toString () {
		return "ID: " + id + "\n" +
			"Max Number of Paper(s): " + maxPaper + "\n" +
			"Current Number of Paper(s): " + numPaper;
	} //toString method
  
	//checks if two printer objects are equal
	public boolean equals (Printer other) {
		if (id == other.id) {
			return true;
		} //if structure
		
		return false;
	} //equals method
  
	public long getId () {
		return id;
	} //getID method
  
	public int getNumPaper () {
		return numPaper;
	} //getPaper method
  
	//Checks if printing is possible and adds to amount due
	public boolean print (boolean color, int needed, User account) {
		if(numPaper - needed >= 0){
			numPaper -= needed;
			
			if (color) {
				account.addAmountOwed(needed * PRICE_COLOUR);
			} else {
				account.addAmountOwed(needed * PRICE_BW);
			} //if structure
			
			return true;
		} //if structure
		
		return false;
	} //print method

	//Adds sheets of paper to the printer
	public boolean addPaper (int sheets) {
		if (maxPaper >= sheets + numPaper) {
			numPaper = numPaper + sheets;
			return true;
		} //if structure
		
		numPaper = maxPaper;
		return false;
	} //addPaper method
	
	//Sort a Printer array by ID
	public static void sortById (Printer[] printers) {
      Printer temp;
      boolean swapped = true;
      
      for (int i = 0; i < printers.length && swapped; i++) {
         swapped = false;
         
         for (int j = printers.length - 1; j > i; j--) {
            if (printers[j].id < printers[j - 1].id) {
               temp = printers[j];
               printers[j] = printers[j - 1];
               printers[j - 1] = temp;
               swapped = true;
            } //if structure
         } //for loop
      } //for loop
   } //sortById method
	
	//Search a Printer array by ID
	public static Printer searchById (Printer[] printers, long id) {
		sortById(printers);
	
		int bottom = 0, top = printers.length, middle;
		while (bottom <= top) {
			middle = (bottom + top) / 2;
			
			if (printers[middle].getId() == id) {
				return printers[middle];
			} else if (printers[middle].getId() > id) {
				top = middle - 1;
			} else {
				bottom = middle + 1;
			} //if structure
		} //while loop
		
		return null;
	} //searchById method
} //Printer class
