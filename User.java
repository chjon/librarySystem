/*******************************************************************************
 * File Name:     User.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines a user.
 *******************************************************************************/

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
		this.amountOwed = amountOwed;
	} //User constructor
	
	//Constructor for a new user
	public User (String name, long id, int age) {         
		this.name = name;
		this.id = id;
		this.age = age;
		amountOwed = 0;
	} //User constructor
 
	public void setAge(int age){
	  this.age = age;
  	} //age mutator
	
	public void setName(String name){
	  this.name = name;
  	}//name mutator
	
	public long getId(){
    		return id;
  	} //id accessor 

	public int getAge(){
		return age;
	} //age accessor
  
	public String getName(){
		return name;
	} //name accessor

	public Item[] getItems(){
		return itemList;
	} //item array accessor

	public double getAmountOwed(){
		return amountOwed;
	} //amountOwed accessor

	public String toString(){
		return
			"Name: " + name + "\n" +
			"User number: " + id + "\n" +
			"Age: " + age + "\n" +
			"Amount Owed: " + amountOwed;
	} //toString method
	
	//Edit user name and age by id
	public boolean editUser(long id, String name, int age){
		if(searchById(id) != null){
			this.name = name;
			this.age = age;
			return true;
		}
		return false;
	}//editUser method
  
	public boolean equalsId (User other) {
		return id == other.id;
	} //equalsId method
  
	public boolean equalsName (User other){
		return name.equals(other.name);
	} //equalsName method

	//Returns number of items currently checked out by the user
	public int currentItems () {
		int count = 0;
		
		for (int i = 0; i < itemList.length; i ++) {
			if (itemList[i] != null) {
				count ++;
			} //if structure
		} //for loop
		
		return count;
	} //currentItems method

	//Checks if borrowing the item is possible
	public boolean canBorrow(Item libraryItem) {
		return
			!libraryItem.isOut() &&
			currentItems() < MAX_ITEMS_OUT;
	} //canBorrow method

	//Adds item to the user's item list
	public boolean takeOutItem (Item checkOut) {   
		if (canBorrow(checkOut)) {
			itemList[currentItems()-1] = checkOut;
			return true;
		} //if structure
		
		return false;    
	} //takeOutItem method

	//Takes back all items
	public void takeBack(Date curDate) {
		int count = currentItems();
		for (int i = 0; i < count; i ++) {
			//Add fine if item is overdue
			if (itemList[i].isOverdue(curDate)) {
				amountOwed += itemList[i].getDaysOverdue(curDate) * Item.OVERDUE_PRICE;
			} //if structure
			
			//Reset item properties
			itemList[i].setDayBorrowed(null);
			itemList[i].setIsOut(false);
			itemList[i] = null;
		} //for loop
	} //takeBack method
  
	//Takes back the item according to the provided item
	public void takeBack(Item returnItem, Date curDate) {
		//Add fine if item is overdue
		if (returnItem.isOverdue(curDate)) {
			amountOwed += returnItem.getDaysOverdue(curDate) * Item.OVERDUE_PRICE;
		} //if structure
		
		//Reset item properties
    	returnItem.setIsOut(false);
		returnItem.setDayBorrowed(null);
		returnItem.setIsOut(false);
		
		//Remove the item from the user
		for (int i = 0; i < itemList.length; i ++) {
			if (itemList[i].getId() == returnItem.getId()) {
				itemList[i] = null;
			} //if structure
		} //for loop
	} //takeBack method
  
	//Pay amount owed by the user
	public void payFine () {
		amountOwed = 0;
	} //payOverdue method
 
	//Add to the amount owed by the user
	public void addAmountOwed(double amount) {
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
	} //sortByName method
	
	//Search a User array by ID
	public static User searchById (User[] users, long id) {
		int bottom = 0, top = users.length, middle;
      
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
		int bottom = 0, top = users.length, middle;
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
} //User class
