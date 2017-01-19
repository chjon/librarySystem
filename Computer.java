/*******************************************************************************
 * File Name:     Computer.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines a Computer.
 *******************************************************************************/

public class Computer extends UserHolder {
	private final static int MAX_USERS = 1;			//max users on a computer is only 1
	private boolean occupied;				//whether or not a computer is occupied
	private Printer[] printers;				//array of printers to print to

	public Computer (long identification, boolean occupied) {
		super(MAX_USERS, identification);
		this.occupied = occupied;
	} //Computer constructor
  
	public String toString () {
		return "ID:" + id + "Currently Available:" + id;
	} //toString method
  
	public boolean isOccupied () {
		return occupied;
	} //isOccupied method
  
	public boolean remUser () {
		if (users[0] != null) {
			users[0] = null;		// Computer only ever has 1 user
			occupied = false;
			return true;
		} //if structure
		
		return false;
	} //remUser method
	
	//Add a printer
	public void addPrinter (Printer toAdd) {
		Printer[] temp = new Printer[printers.length + 1];

		for (int i = 0; i < temp.length; i++) {
			temp[i] = printers[i];
		} //for loop

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
  
	public boolean print (boolean colour, int amount, User account) {
		Printer mostPaper = printers[0];

		for (int i = 1; i < printers.length; i++) {
			if (printers[i].getNumPaper() > mostPaper.getNumPaper()) {
				mostPaper = printers[i];
			} //if structure
		} //for loop

		return mostPaper.print(colour, amount, account);
	} //print method
} //UserHolder class
