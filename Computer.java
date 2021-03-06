/*******************************************************************************
 * File Name:     Computer.java
 * Class:         ICS4U-01
 * Description:   This class defines a Computer.
 *******************************************************************************/

package librarySystem.userHolders;

import librarySystem.users.*;

public class Computer extends UserHolder {
	private final static int MAX_USERS = 1;			//max users on a computer is only 1
	private boolean occupied;				//whether or not a computer is occupied
	private Printer[] printers;				//array of printers to print to

	public Computer (long identification, boolean occupied) {
		super(MAX_USERS, identification);
		this.occupied = occupied;
		printers = new Printer[0];
	} //Computer constructor
  
	public String toString () {
		String output =
			super.toString() + "\n" +
			"Printers:\t";
		
		for (int i = 0; i < printers.length; i++) {
			if (printers[i] != null) {
				output += printers[i].getId() + ",";
			} //if structure
		} //for loop
		
		if (output.charAt(output.length() - 1) == ',') {
			output = output.substring(0, output.length() - 1);
		} //if structure
		
		return output;
	} //toString method
	
	public Printer[] getPrinters () {
		return printers;
	} //getPrinters method
  
	public boolean isOccupied () {
		return occupied;
	} //isOccupied method
	
	public void setIsOccupied (boolean occupied) {
		this.occupied = occupied;
	} //isOccupied method
	
	//Removes a user from the computer
	public boolean remUser () {
		//Check if the user exists
		if (users[0] != null) {
			users[0] = null;		//Computer only ever has 1 user
			occupied = false;
			return true;
		} //if structure
		
		return false;
	} //remUser method
	
	//Add a printer
	public void addPrinter (Printer toAdd) {
		//Make a larger array to fit a new printer
		Printer[] temp = new Printer[printers.length + 1];
		
		//Copy existing printers to array
		for (int i = 0; i < printers.length; i++) {
			temp[i] = printers[i];
		} //for loop
		
		//Adds new printer to array
		temp[temp.length - 1] = toAdd;
		printers = temp;
	} //addPrinter method
	
	//Remove a printer
	public boolean remPrinter (long id) {
		boolean found = false;
		
		//Check if printer is in array
		for (int i = 0; i < printers.length && !found; i++) {
			if (printers[i].getId() == id) {
				found = true;
			} //if structure
		} //for loop
		
		if (found) {
			int currentIndex = 0;
			
			//one less than the original length for the removed printer
			Printer[] temp = new Printer[printers.length - 1];
			
			//Copy only the printers that are not targeted for removal
			for (int i = 0; i < temp.length; i++) {
				if (printers[i].getId() != id) {
					temp[currentIndex] = printers[i];
					currentIndex++;
				} //if structure
			} //for loop
	
			printers = temp;
		} //if structure
		
		return found;
	} //remPrinter method

	//Method to print paper
	public boolean print (boolean colour, int amount, User account) {
		Printer mostPaper = printers[0];
		
		//Find the printer with the most paper
		for (int i = 1; i < printers.length; i++) {
			if (printers[i].getNumPaper() > mostPaper.getNumPaper()) {
				mostPaper = printers[i];
			} //if structure
		} //for loop
		
		//Print the paper
		return mostPaper.print(colour, amount, account);
	} //print method
} //UserHolder class
