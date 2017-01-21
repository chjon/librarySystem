/*******************************************************************************
 * File Name:     Room.java
 * Class:         ICS4U-01
 * Description:   This class defines a room.
 *******************************************************************************/

package librarySystem.userHolders;

public class Room extends UserHolder {
	private static final String TYPE = "Room";
	private int maxUsers;		//maximum amount of users allowed

	public Room (long id, int maxUsers) {
		super(maxUsers, id);
		this.maxUsers = maxUsers;
	} //Room constructor

	public String toString () {
		return
			super.toString() + "\n" +
			"Type: " + TYPE + "\n" +
			"Max Users: " + maxUsers;
	} //toString method
	
	public int getMaxUsers () {
		return maxUsers;
	} //getMaxUsers method

	//Checks if the room is full
	public boolean isOccupied () {
		int count = 0;
	
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				count++;
			} //if structure
		} //for loop
		
		return count == maxUsers;
	} //isOccupied method
	
	//Remove user from room
	public boolean remUser (long id) {
		boolean found = false;
		
		for (int i = 0; i < users.length && !found; i++) {
			if (users[i] != null && users[i].getId() == id) {
				users[i] = null;
				found = true;
			} //if structure
		} //for loop
		
		return found;
	} //remUser method
	
	public void remAllUsers () {
		for (int i = 0; i < users.length; i++) {
			users[i] = null;
		}
	}
} //Room class
