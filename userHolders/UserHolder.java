/*******************************************************************************
 * File Name:     UserHolder.java
 * Class:         ICS4U-01
 * Description:   This class defines an object that holds users.
 *******************************************************************************/

package librarySystem.userHolders;

import librarySystem.users.*;

public abstract class UserHolder {
	public static final String COMPUTER = "computer";
	public static final String ROOM = "room";
	protected long id;            //ID of the UserHolder
	protected User [] users;      //List of users in the UserHolder
	
	abstract boolean isOccupied();
	
	public UserHolder (int maxUsers, long id) {
		users = new User[maxUsers];
		this.id = id;
	} //if structure

	public boolean equals (UserHolder temp) {
		return id == temp.id;
	} //equals method

	public long getId () {
		return id;
	} //getId method
	
	public User[] getUsers () {
		return users;
	} //getUsers method
	
	public String toString () {
		String output =
				"ID:\t" + id + "\n" +
				"Users:\t";
		
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				output += users[i].getId() + ",";
			} //if structure
		} //for loop
		
		if (output.charAt(output.length() - 1) == ',') {
			output = output.substring(0, output.length() - 1);
		} //if structure
		
		return output;
	} //toString method
	
	//Adds user to the Userholder
	public boolean addUser(User toAdd) {
		for (int i = 0; !isOccupied() && i < users.length; i++) {
			if (users[i] == null) {
				users[i] = toAdd;

				if (this instanceof Computer) {
					((Computer)this).setIsOccupied(true);
				}

				return true;
			} //if structure
		} //for loop
		
		return false;
	} //addUser method
	
	//Sort a UserHolder array by ID
	public static void sortById (UserHolder[] userHolders) {
      UserHolder temp;
		boolean swapped = true;
      
		for (int i = 0; i < userHolders.length && swapped; i++) {
			swapped = false;
         
			for (int j = userHolders.length - 1; j > i; j--) {
				if ((userHolders[j - 1] == null && userHolders[j] != null) ||
						(userHolders[j - 1] != null && userHolders[j] != null && userHolders[j].id < userHolders[j - 1].id)) {
					temp = userHolders[j];
					userHolders[j] = userHolders[j - 1];
					userHolders[j - 1] = temp;
					swapped = true;
				} //if structure
			} //for loop
		} //for loop
	} //sortById method
	
	//Search an UserHolder array by ID
	public static UserHolder searchById (UserHolder[] userHolders, long id) {
		sortById(userHolders);
	
		for (int i = 0; i < userHolders.length && userHolders[i] != null; i++) {
			if (userHolders[i].getId() == id) {
				return userHolders[i];
			} //if structure
		} //for loop
		
		return null;
	} //searchById method
} //UserHolder class