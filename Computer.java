/*******************************************************************************
 * File Name:     Computer.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines a Computer.
 *******************************************************************************/

class Computer extends UserHolder {
	protected boolean occupied;
	protected Printer[] printers;
	private final static int MAXUSERS = 1;

	public Computer (long identification) {
		super(identification);
		occupied = false;
	} //Computer constructor
  
	public String toString () {
		return "ID:" + id + "Currently Available:" + id;
	} //toString method
  
	public boolean isOccupied () {
		return occupied;
	} //isOccupied method
  
	public boolean remUser () {
		users[0] = null;		// Computer only ever has 1 user
		occupied = false;
	} //remUser method
  
	public void addPrinter (Printer other) {
		Printer[] temp = new Printer[printers.length() + 1];

		for (int i = 0; i < temp.length(); i++) {
			temp[i] = printers[i];
		}

		temp[temp.length() - 1] = other;
	} //addPrinter method
  
	public void remPrinter (long id) {
		Printer[] temp = new Printer[printers.length() - 1];
		// one less than the original length for the removed printer

		for (int i = 0; i < temp.length(); i++) {
			// if the printer does dot have the id targeted for removal, it is kept
			if (printers[i].getId() != id) {
				temp[i] = printers[i];
			}
		}

		printers = temp;
	} //remPrinter method
  
	public boolean print (boolean colour, int amount, User account) {
		Printer mostPaper = printers[0];

		for (int i = 1; i < printers.length(); i++) {
			if (printers[i].getNumPaper() > mostPaper.getNumPaper()) {
				mostPaper = printers[i];
			}
		}

		mostPaper.print(colour, amount);
	} //print method
} //UserHolder class