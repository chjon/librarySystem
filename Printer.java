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
				account.setAmountOwed(needed * PRICE_COLOUR);
			} else {
				account.setAmountOwed(needed * PRICE_BW);
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
} //Printer class