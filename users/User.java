/*******************************************************************************
 * File Name:     User.java
 * Class:         ICS4U-01
 * Description:   This class defines a user.
 *******************************************************************************/

package librarySystem.users;

import librarySystem.items.*;

public class User {
	private String name;                                  //User name
	private long id;                                      //User id
	private int age;                                      //User age
	private double amountOwed;                            //Amount due by the user
	private final int MAX_ITEMS_OUT = 5;                  //Maximum amount of items allowed out at a single time
	private Item[] items = new Item[MAX_ITEMS_OUT];    //Array of items current signed out
	
	//Constructor for loading users
	public User (String name, long id, int age, double amountOwed, Item[] itemList) {         
		this.name = name;
		this.id = id;
		this.age = age;
		amountOwed = amountOwed;
		items = itemList;
	} //User constructor
	
	//Constructor for a new user
	public User (String name, long id, int age) {         
		this.name = name;
		this.id = id;
		this.age = age;
		amountOwed = 0;
	} //User constructor
 
	public void setAge (int age) {
	  this.age = age;
  	} //age mutator
	
	public void setName (String name) {
	  this.name = name;
  	}//name mutator
	
	public long getId () {
    		return id;
  	} //id accessor 

	public int getAge () {
		return age;
	} //age accessor
  
	public String getName () {
		return name;
	} //name accessor

	public Item[] getItems () {
		return items;
	} //item array accessor

	public double getAmountOwed () {
		return amountOwed;
	} //amountOwed accessor

	public String toString () {
		return
			"Name: " + name + "\n" +
			"User number: " + id + "\n" +
			"Age: " + age + "\n" +
			"Amount Owed: " + amountOwed;
	} //toString method
	
	//Edit user name and age by id
	public void editUser (String name, int age) {
		this.name = name;
		this.age = age;
	} //editUser method
  
	public boolean equalsId (User other) {
		return id == other.id;
	} //equalsId method
  
	public boolean equalsName (User other) {
		return name.equals(other.name);
	} //equalsName method

	//Returns number of items currently checked out by the user
	public int currentItems() {
		int count = 0;
		
		for (int i = 0; i < items.length; i ++) {
			if (items[i] != null) {
				count ++;
			} //if structure
		} //for loop
		
		return count;
	} //currentItems method

	//Checks if borrowing the item is possible
	public boolean canBorrow (Item libraryItem) {
  		if (libraryItem instanceof VideoGame) {
    		if (age >= (((VideoGame)(libraryItem)).getAgeRating())) {     
      		if (!libraryItem.getIsOut() && currentItems() < MAX_ITEMS_OUT) {
        			return true;
      		} else {
        			return false;
      		} //if structure
    		} else {
				return false;
			} //if structure
    	} else if (libraryItem instanceof Movie) {
      	if (age >= (((Movie)(libraryItem)).getAgeRating())) {
      		if (!libraryItem.getIsOut() && currentItems() < MAX_ITEMS_OUT) {
        			return true;
      		} else {
        			return false;
      		} //if structure
    		} else {
				return false;
			} //if structure
    	} //if structure
		
      return (!libraryItem.getIsOut()) && (currentItems() < MAX_ITEMS_OUT);
	}//canBorrow method

	//Adds item to the user's item list
	public boolean takeOutItem (Item checkOut) {   
		if (canBorrow(checkOut)) {
			items[currentItems() - 1] = checkOut;
			return true;
		} //if structure
		
		return false;    
	} //takeOutItem method

	//Takes back all items
	public void takeBack (Date curDate) {
		int count = currentItems();
		for (int i = 0; i < count; i ++) {
			//Add fine if item is overdue
			if (items[i].isOverdue(curDate)) {
				amountOwed += (-items[i].getDaysOverdue(curDate)) * Item.OVERDUE_PRICE;
			} //if structure
			
			//Reset item properties
			items[i].setDayBorrowed(null);
			items[i].setIsOut(false);
			items[i] = null;
		} //for loop
	} //takeBack method
  
	//Takes back the item according to the provided item
	public boolean takeBack (Item returnItem, Date curDate) {
		//Add fine if item is overdue
		if (returnItem.isOverdue(curDate)) {
			amountOwed += -returnItem.getDaysOverdue(curDate) * Item.OVERDUE_PRICE;
		} //if structure
		
		//Reset item properties
    	returnItem.setIsOut(false);
		returnItem.setDayBorrowed(null);
		returnItem.setIsOut(false);
		
		//Remove the item from the user
		for (int i = 0; i < items.length; i ++) {
			if (items[i].getId() == returnItem.getId()) {
				items[i] = null;
				return true; // if successfuly returned
			} //if structure
		} //for loop
		return false; // if item isn't in users posession
	} //takeBack method

  
	//Pay amount owed by the user
	public void payFine () {
		amountOwed = 0;
	} //payOverdue method
 
	//Add to the amount owed by the user
	public void addAmountOwed (double amount) {
		amountOwed += amount;
	} //addAmountOwed method
	
	//Sort a User array by ID
	public static void sortById (User[] users) {
		User temp;
		boolean swapped = true;
		
		for (int i = 0; i < users.length && swapped; i++) {
			swapped = false;
         
			for (int j = users.length - 1; j > i; j--) {
				if (users[j].id < users[j - 1].id) {
					temp = users[j];
					users[j] = users[j - 1];
					users[j - 1] = temp;
					swapped = true;
				} //if structure
			} //for loop
		} //for loop
	} //sortById method
	
	//Sort a User array by age
	public static void sortByAge (User[] users) {
		User temp;
		boolean swapped = true;
		
		for (int i = 0; i < users.length && swapped; i++) {
			swapped = false;
         
			for (int j = users.length - 1; j > i; j--) {
				if (users[j].age < users[j - 1].age) {
					temp = users[j];
					users[j] = users[j - 1];
					users[j - 1] = temp;
					swapped = true;
				} //if structure
			} //for loop
		} //for loop
	} //sortByAge method
	
	//Sort an User array by name alphabetically
	public static void sortByName (User[] users) {
		User temp;
		boolean swapped = true;
		
		for (int i = 0; i < users.length && swapped; i++) {
			swapped = false;
			for (int j = users.length - 1; j > i; j--) {
				if (users[j].getName().compareTo(users[j - 1].getName()) > 1) {
					temp = users[j];
					users[j] = users[j - 1];
					users[j - 1] = temp;
					swapped = true;
				} //if structure
			} //for loop
		} //for loop
	} //sortByName method\

	//Sort an User array by the number of items signed out
	public static void sortByNumItemsOut (User[] users) {
		User temp;
		boolean swapped = true;
		
		for (int i = 0; i < users.length && swapped; i++) {
			swapped = false;
         
			for (int j = users.length - 1; j > i; j--) {
				if (users[j].items.length < users[j - 1].items.length) {
					temp = users[j];
					users[j] = users[j - 1];
					users[j - 1] = temp;
					swapped = true;
				} //if structure
			} //for loop
		} //for loop
	}
	
	//Search a User array by ID
	public static User searchById (User[] users, long id) {
		sortById(users);
	
		int bottom = 0, top = users.length - 1, middle;
      
		while (bottom <= top) {
			middle = (bottom + top) / 2;
			
			if (users[middle].getId() == id) {
				return users[middle];
			} else if (users[middle].getId() > id) {
				top = middle - 1;
			} else {
				bottom = middle + 1;
			} //if structure
		} //while loop
		
		return null;
	} //searchById method
	
	//Search a User array by name alphabetically
	public static User[] searchByName (User[] users, String name) {
		sortByName(users);
	
		int bottom = 0, top = users.length - 1, middle;
		int foundIndex = -1;
      
		//find the index of a matching user
		while (bottom <= top && foundIndex == -1) {
			middle = (bottom + top) / 2;
			String userName = users[middle].getName();
         
			if (userName.equals(name)) {
				foundIndex = middle;
			} else if (userName.compareTo(name) > 0) {
				top = middle - 1;
			} else {
				bottom = middle + 1;
			} //if structure
		} //while loop
      
		//Check if a match has been found
		if (foundIndex != -1) {
			int endIndex = foundIndex;
			
			//find first index of a matching user
			while (foundIndex > 0 && users[foundIndex - 1].equals(name)) {
				foundIndex--;
			} //while loop
			
			//find last index of a matching user
			while (endIndex < users.length - 1 && users[endIndex + 1].equals(name)) {
				endIndex++;
			} //while loop
			
			User[] foundUsers = new User[endIndex - foundIndex + 1];
			
			//Copy all matching users
			for (int i = foundIndex; i <= endIndex; i++) {
				foundUsers[i - foundIndex] = users[i];
			} //for loop
			
			return foundUsers;
		} //if structure
		
		//return null if no match is found
		return null;
	} //searchByName method
	
	//Search a User array by age
	public static User[] searchByAge (User[] users, int age) {
		sortByAge(users);
	
		int bottom = 0, top = users.length - 1, middle;
		int foundIndex = -1;
      
		//find the index of a matching user
		while (bottom <= top && foundIndex == -1) {
			middle = (bottom + top) / 2;
			int userAge = users[middle].getAge();
         
			if (userAge == age) {
				foundIndex = middle;
			} else if (userAge > age) {
				top = middle - 1;
			} else {
				bottom = middle + 1;
			} //if structure
		} //while loop
      
		//Check if a match has been found
		if (foundIndex != -1) {
			int endIndex = foundIndex;
			
			//find first index of a matching user
			while (foundIndex > 0 && users[foundIndex - 1].getAge() == age) {
				foundIndex--;
			} //while loop
			
			//find last index of a matching user
			while (endIndex < users.length - 1 && users[endIndex + 1].getAge() == age) {
				endIndex++;
			} //while loop
			
			User[] foundUsers = new User[endIndex - foundIndex + 1];
			
			//Copy all matching users
			for (int i = foundIndex; i <= endIndex; i++) {
				foundUsers[i - foundIndex] = users[i];
			} //for loop
			
			return foundUsers;
		} //if structure
		
		//return null if no match is found
		return null;
	} //searchByAge method
	
	//Check whether a User has an overdue Item
	public boolean hasOverdue (Date curDate) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].isOverdue(curDate)) {
				return true;
			} //if structure
		} //for loop
		
		return false;
	} //hasOverdue method
	
	//Search a User array for Users with overdue Items 
	public static User[] searchByOverdue (User[] users, Date curDate) {
		int count = 0;
		
		//Count number of Users with overdue Items
		for (int i = 0; i < users.length; i++) {
			if (users[i].hasOverdue(curDate)) {
				count++;
			} //if structure
		} //for loop
		
		User[] areOverdue = new User[count];
		count = 0;
		
		//Copy all Users with overdue Items
		for (int i = 0; i < users.length; i++) {
			if (users[i].hasOverdue(curDate)) {
				areOverdue[count] = users[i];
				count++;
			} //if structure
		} //for loop
		
		return areOverdue;
	} //searchByOverdue method
	
	//searches for users with x items
	public static User[] searchByAmount(User[] users, int amount) {			
		int count = 0;
		
		//Count number of Users with x items
		for (int i = 0; i < users.length; i++) {
			if (users[i].currentItems() == amount) {
				count++;
			} //if structure
		} //for loop
		
		User[] foundUsers = new User[count];
		count = 0;
		
		//Copy Users with x items
		for (int i = 0; i < users.length; i++) {
			if (users[i].currentItems() == amount) {
				foundUsers[count] = users[i];
				count++;
			} //if structure
		} //for loop
		
		return foundUsers;
	} //searchByAmount method

	public int findNumItemsOut () {
		int count = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				count++;
			}
		}
		return count;
	}

	public static User[] searchByNumItemsOut (User[] users, int target) {
		sortByNumItemsOut(users);

		int bottom = 0, top = users.length - 1, middle;
		int foundIndex = -1;
      
		//find the index of a matching user
		while (bottom <= top && foundIndex == -1) {
			middle = (bottom + top) / 2;
			int numItem = users[middle].findNumItemsOut();
         
			if (numItem == target) {
				foundIndex = middle;
			} else if (numItem > target) {
				top = middle - 1;
			} else {
				bottom = middle + 1;
			} //if structure
		} //while loop
      
		//Check if a match has been found
		if (foundIndex != -1) {
			int endIndex = foundIndex;
			
			//find first index of a matching user
			while (foundIndex > 0 && users[foundIndex - 1].findNumItemsOut() == target) {
				foundIndex--;
			} //while loop
			
			//find last index of a matching user
			while (endIndex < users.length - 1 && users[endIndex + 1].findNumItemsOut() == target) {
				endIndex++;
			} //while loop
			
			User[] foundUsers = new User[endIndex - foundIndex + 1];
			
			//Copy all matching users
			for (int i = foundIndex; i <= endIndex; i++) {
				foundUsers[i - foundIndex] = users[i];
			} //for loop
			
			return foundUsers;
		} //if structure
		
		//return null if no match is found
		return null;
	} //searchByNumItemsOut method
} //User class
