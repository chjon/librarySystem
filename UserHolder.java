/*******************************************************************************
 * File Name:     UserHolder.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines an object that holds users.
 *******************************************************************************/

public abstract class UserHolder {
	protected long id;
	protected User [] users;
	
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
	
	public boolean addUser(User toAdd) {
		boolean added = false;
	
		for (int i = 0; !isOccupied() && i < users.length; i++) {
			if (users[i] == null) {
				users[i] = toAdd;
				added = true;
			} //if structure
		} //for loop
		
		return added;
	} //addUser method
} //UserHolder class