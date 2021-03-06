/*******************************************************************************
 * File Name:     LibraryRunner.java
 * Class:         ICS4U-01
 * Description:   This class runs the Library and interacts with the user.
 *******************************************************************************/

package librarySystem.core;

import java.util.Scanner;
import java.io.*;
import librarySystem.items.*;
import librarySystem.userHolders.*;
import librarySystem.users.*;

public class LibraryRunner {
	private static Scanner sc = new Scanner(System.in);
	private static Library jurrLibrary = new Library();

	public static void main (String[] args) {
      //Enters the main menu
		displayMainMenu();
	} // main method

	public static void displayMainMenu () {
		//Base method for interaction

		int sel; // to choose an option
		boolean exit = false;

		while (!exit) {
			//Structure to select which function to execute or other menu to view
			System.out.println("\nMAIN MENU\n");
			System.out.println("Welcome to the Library!\nPlease enter a selection:\n");
			System.out.println("1. Enter account credentials");
			System.out.println("2. View Library inventory");
			System.out.println("3. View user list");
			System.out.println("4. Return item");
			System.out.println("5. View rooms");
			System.out.println("6. View computers");
			System.out.println("7. Exit");
			System.out.println();

			try {
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Entering account credentials
					case 1:
						displayAccountMenu();
						break;

					//Viewing Library inventory
					case 2:
						displayLibraryInventoryMenu();
						break;

					//Viewing list of users
					case 3:
						displayUserListMenu();
						break;

					//Returning an item
					case 4:
						displayReturnItem();
						break;

					//Viewing rooms
					case 5:
						displayRoomMenu();
						break;

					//Viewing computers
					case 6:
						displayComputerMenu();
						break;

					//Exit
					case 7:
						//Writes and saves information to text files immediately prior to program exit
						jurrLibrary.writeToFile();

						exit = true;
						System.out.println("EXITING PROGRAM");
						break;
						
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Runner Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayMainMenu method

	public static void displayAccountMenu () {
		//displayMainMenu selection 1
		//Shows options to interact with a user's account

		long accNum; 
		int sel;
		int itemId;
		int newAge;
		int targetPages;			//Number of pages a user wants for suggested books
		int targetMinutes;		//Number of minutes a user wants for suggested movies
		boolean exit = false;
		String newName;
		User curUser;				//Current user
		Item[] tempList;

		System.out.print("Enter account number: ");
		accNum = sc.nextLong();
		sc.nextLine();

		curUser = jurrLibrary.getUserById(accNum);
		
		if (curUser == null) {
			System.out.println("That user was not found.");
			exit = true;
		} //if structure
		
		System.out.println();

		while (!exit) {
         //Menu options for interacting with a User's information
			System.out.println("\nACCOUNT MENU\n");
			System.out.println("1. Sign out item");
			System.out.println("2. View current items");
			System.out.println("3. Check overdue fees");
			System.out.println("4. Pay overdue fees");
			System.out.println("5. Change account information");
			System.out.println("6. Get suggested books");
			System.out.println("7. Get suggested movies");
			System.out.println("8. Return to main menu");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Signing out an item
					case 1:
						System.out.print("Enter item ID: ");
						itemId = sc.nextInt();
						sc.nextLine();
						
						Item chosenItem = jurrLibrary.getItemById(itemId);
						
						if (chosenItem == null) {
							System.out.println("That item was not found.");
						} else {
							//current user takes out item
							if (curUser.takeOutItem(chosenItem)) {
								System.out.println("Item successfully signed out");
							} else {
								System.out.println("Item cannot be signed out");
							} //if structure
						} //if structure
						
						break;

					//Viewing current items in the account
					case 2:
						tempList = curUser.getItems();
						
						for (int i = 0; i < tempList.length; i++) {
							if (tempList[i] != null) {
								System.out.println(tempList[i]);
								System.out.println();
							} //if structure
						} //for loop
						
						viewCurrentItemsMenu();
						break;

					//Displaying the amount owed to the Library
					case 3:
						System.out.println("Amount owed: " + curUser.getAmountOwed());
						break;

					//Paying overdue fines
					case 4:
						curUser.payFine();
						System.out.println("Transaction complete. All fees have been paid");
						break;

					//Change information in the account
					case 5:
						System.out.print("Enter name: ");
						newName = sc.nextLine();
						System.out.print("Enter age: ");
						newAge = sc.nextInt();
						sc.nextLine();
						curUser.editUser(newName, newAge);
						System.out.println("Account information changed");
						break;

					//Returning books suggested that are closest to the number of pages the user enters
					case 6:
						System.out.print("Enter a number of pages to read: ");
						targetPages = sc.nextInt();
						sc.nextLine();

						//returns a list of suggested books whose number of pages sum most closely to the target number of pages
						tempList = Library.suggestBooks(jurrLibrary.getBooks(), targetPages);

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						} //for loop
						
						break;

					//Returning movies suggested that are closest to the number of minutes the user enters
					case 7:
						System.out.print("Enter number of minutes to watch: ");
						targetMinutes = sc.nextInt();
						sc.nextLine();

						//returns a list of suggested movies whose time sum most closely to the target time
						tempList = Library.suggestMovies(jurrLibrary.getMovies(), targetMinutes);
						
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						} //for loop
						
						break;

               //Returning to the MAIN MENU
					case 8:
						exit = true;
						break;
					
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayAccountMenu method

	public static void viewCurrentItemsMenu () {
		//displayAccountMenu selection 1

		int sel;
		long id;
		boolean exit = false;
		String name, date;
		String[] dateTemp;
		Item[] tempList;
		Date expDate;

		while (!exit) {
         //Display options to view options involving items
			System.out.println("\nVIEW CURRENT ITEMS MENU\n");
			System.out.println("1. Search for items by name");
			System.out.println("2. Search for item by expiry date");
			System.out.println("3. Search for item by ID");
			System.out.println("4. Return to ACCOUNT MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Displays a list of items according to their title
					case 1:
						System.out.print("Enter a title: ");
						name = sc.nextLine();
						tempList = jurrLibrary.getItemsByTitle(name);
						
						//printing information for all items found with matching title
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						} //for loop

						break;

					//Displays a list of items according to their expiry/overdue date
					case 2:
						System.out.print("Enter an expiry date (YYYY/MM/DD): ");
						dateTemp = sc.nextLine().split("/");
						
						try {
                     //Creating the Date object in which items may expire
							expDate = new Date(Integer.parseInt(dateTemp[0]), Integer.parseInt(dateTemp[1]), Integer.parseInt(dateTemp[2]), jurrLibrary.getCal());

                     //Retrieves an array of Items that expire on the given Date
							tempList = jurrLibrary.getOverdue(expDate);
							
							for (int i = 0; i < tempList.length; i++) {
								System.out.println(tempList[i]);
							} //for loop
						} catch (Exception e) {
							System.out.println("Invalid input");
						} //try-catch structure

						break;

					//Displays an item with the matching ID
					case 3:
						System.out.print("Enter item ID: ");
						id = sc.nextLong();
						
						Item chosenItem = jurrLibrary.getItemById(id);
						
						if (chosenItem == null) {
							System.out.println("That item was not found.");
						} else {
							System.out.println(chosenItem);
						} //if structure
						
						break;

					case 4:
						exit = true;
						break;
						
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //viewCurrentItemsMenu method

	public static void displayLibraryInventoryMenu () {
		//displayMainMenu selection 2
		final int BOOKPARAMETERS = 3;
		final int VIDEOGAMEPARAMETERS = 3;
		final int MOVIEPARAMETERS = 4;

		int sel;
		long id;
		boolean exit = false;
		String name, type;
		Object[] parameters;
		Item[] tempList;

		while (!exit) {
         //Display options to interact with the Library's lists of Item objects
			System.out.println("\nINVENTORY MENU\n");
			System.out.println("1. Display item list");
			System.out.println("2. Add item");
			System.out.println("3. Remove item");
			System.out.println("4. Check item status");
			System.out.println("5. Return to MAIN MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Displays the item list menu of the Library inventory
					case 1:
						displayItemMenu();

						break;

					//Add item to the list of items
					case 2:
						System.out.print("Enter item name: ");
						name = sc.nextLine();
						System.out.print("Enter item type (book, video game, movie): ");
						type = sc.nextLine();
						
						try {
							//If the item is of type Book
							if (type.equalsIgnoreCase(Item.BOOK)) {
                        //Creating an object array to store the parameters of a Book object
								parameters = new Object[BOOKPARAMETERS];
	
								System.out.print("Enter author name: ");
								parameters[0] = sc.nextLine();
	
								System.out.print("Enter the number of pages in the book: ");
								parameters[1] = (Integer)sc.nextInt();
	
								System.out.print("Enter the Dewey Decimal Number: ");
								parameters[2] = (Double)sc.nextDouble();
								sc.nextLine();
	
								jurrLibrary.addItem(type, name, parameters);
	
							//If the item is of type VideoGame
							} else if (type.equalsIgnoreCase(Item.VIDEO_GAME)) {
                        //Creating an object array to store the parameters of a VideoGame object
								parameters = new Object[VIDEOGAMEPARAMETERS];
	
								System.out.print("Enter developer name: ");
								parameters[0] = sc.nextLine();
	
								System.out.print("Enter the genre: ");
								parameters[1] = sc.nextLine();
	
								System.out.print("Enter the age rating: ");
								parameters[2] = (Integer)sc.nextInt();
								sc.nextLine();
	
								jurrLibrary.addItem(type, name, parameters);
							//If the item is of type Movie
							} else if (type.equalsIgnoreCase(Item.MOVIE)) {
                        //Creating an object array to store the parameters of a Movie object
								parameters = new Object[MOVIEPARAMETERS];
	
								System.out.print("Enter director name: ");
								parameters[0] = sc.nextLine();
	
								System.out.print("Enter the genre: ");
								parameters[1] = sc.nextLine();
	
								System.out.print("Enter the length of the movie (in minutes): ");
								parameters[2] = (Integer)sc.nextInt();
	
								System.out.print("Enter the age rating: ");
								parameters[3] = (Integer)sc.nextInt();
								sc.nextLine();
								
								jurrLibrary.addItem(type, name, parameters);
							} //if structure
							
							System.out.println("Item was successfully added.");
						} catch (Exception e) {
							System.out.println("Item addition failed.");
						} //try-catch structure

						break;

					//Removes an item from the list with the specified ID
					case 3:
						boolean canRemove;
						System.out.print("Enter an ID: ");
						id = sc.nextLong();
						sc.nextLine();

						if (jurrLibrary.remItem(id)) {
							System.out.println("Item removed successfully.");
						} else {
							System.out.println("Item removing failed.");
						} //if structure
						
						break;

					//Checks to see if an item is in the library or not
					case 4:
						System.out.print("Enter item ID: ");
						id = sc.nextLong();

						if (jurrLibrary.getItemById(id) != null) {
							System.out.println("Item is in the library");
						} else {
							System.out.println("Item is not in the library");
						} //if structure

						break;

					case 5:
						exit = true;
						break;
					
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayLibraryInventoryMenu method

	public static void displayItemMenu () {
		//displayLibraryInventoryMenu selection 1

		int sel;
		long id;
		boolean exit = false;
		String name;
		String genre;
		String type;
		String[] dateHold;
		int year, month, day;
		Date dueDate;
		Item[] tempList;

		while (!exit) {
			System.out.println("\nDISPLAY ITEM MENU\n");
			System.out.println("1. Display all items");
			System.out.println("2. Display by overdue");
			System.out.println("3. Display by type");
			System.out.println("4. Display by name");
			System.out.println("5. Display by ID");
			System.out.println("6. Display by genre");
			System.out.println("7. Return to INVENTORY MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					case 1:
						tempList = jurrLibrary.getItems();

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						} //for loop

						break;

					//Displays items that are overdue
					case 2:
						System.out.print("Enter the overdue date (YYYY/MM/DD): ");
						dateHold = sc.nextLine().split("/");
						
						try {
							dueDate = new Date(Integer.parseInt(dateHold[0]), Integer.parseInt(dateHold[1]), Integer.parseInt(dateHold[2]), jurrLibrary.getCal());
							
	                  //Retrieving an array of the items with the given overdue Date
							tempList = jurrLibrary.getOverdue(dueDate);
	
							if (tempList != null) {
								for (int i = 0; i < tempList.length; i++) {
									System.out.println(tempList[i]);
								} //for loop
							} else {
								System.out.println("No items found with given overdue date");
							} //if structure
						} catch (Exception e) {
							System.out.println("Invalid input.");
						} //try-catch structure

						break;

					//Displays item by the selected type (Book, VideoGame, Movie)
					case 3:
						System.out.print("Enter an item type (book / video game / movie): ");
						type = sc.nextLine();

                  //Retrieving a list of the items with the given type
						tempList = Item.searchByType(jurrLibrary.getItems(), type);

						if (tempList != null) {
							for (int i = 0; i < tempList.length; i++) {
								System.out.println(tempList[i]);
							} //for loop
						} else {
							System.out.println("No item found with specified type:");
						}
						
						break;

					//Displays items by their title
					case 4:
						System.out.print("Enter a title: ");
						name = sc.nextLine();
						tempList = jurrLibrary.getItemsByTitle(name);

						if (tempList != null) {
							//printing information for all items found with matching title
							for (int i = 0; i < tempList.length; i++) {
								System.out.println(tempList[i]);
							} //for loop
						} else {
							System.out.println("No item found with given title");
						}
						
						break;

					//Displays an item with the entered ID
					case 5:
						System.out.print("Enter item ID: ");
						id = sc.nextLong();
						sc.nextLine();
						
                  //Finds the item with the given ID
						Item chosenItem = jurrLibrary.getItemById(id);
						
						if (chosenItem == null) {
							System.out.println("That item was not found.");
						} else {
							System.out.println(chosenItem);
						} //if structure
						
						break;

					//Displays items by genre
					case 6:
						System.out.print("Enter a genre: ");
						genre = sc.nextLine();

						tempList = Item.searchByGenre(jurrLibrary.getItems(), genre);

						if (tempList != null) {
							for (int i = 0; i < tempList.length; i++) {
								System.out.println(tempList[i]);
							} //for loop
						} else {
							System.out.println("No item found with given genre");
						}

						break;

					case 7:
						exit = true;
						break;
						
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayItemMenu method

	public static void displayUserListMenu () {
		//displayMainMenu selection 3

		long accNum;
		int sel;
		boolean exit = false;
		String name, newName;
		int age, newAge;
		int id;
		User curUser;

		while (!exit) {
			System.out.println("\nUSER LIST MENU\n");
			System.out.println("1. Display users");
			System.out.println("2. Add user");
			System.out.println("3. Remove user");
			System.out.println("4. Edit user");
			System.out.println("5. Return to MAIN MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Enters menu to display users
					case 1:
						displayUsersMenu();
						break;

					//Adds user to the library user list
					case 2:
						System.out.print("Enter a name: ");
						name = sc.nextLine();
						System.out.print("Enter an age: ");
						age = sc.nextInt();

						jurrLibrary.addUser(name, age);
						
						System.out.println("User successfully added");
						break;

					//Removes a user from the library user list
					case 3:
						System.out.print("Enter an ID: ");
						id = sc.nextInt();

						if (jurrLibrary.remUser(id)) {
							System.out.println("User successfully removed");
						} else {
							System.out.println("That user was not found");
						} //if structure
						
						break;

					//Changes user information
					case 4:
						System.out.print("Enter account number: ");
						accNum = sc.nextLong();
						sc.nextLine();

						curUser = jurrLibrary.getUserById(accNum);
						
						if (curUser == null) {
							System.out.println("That user was not found.");
						} else {
							System.out.print("Enter name: ");
							newName = sc.nextLine();
							System.out.print("Enter age: ");
							newAge = sc.nextInt();
	
							curUser.editUser(newName, newAge);
	
							System.out.println("Account information changed");
						} //if structure
						
						break;

					case 5:
						exit = true;
						break;
						
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayUserListMenu method

	public static void displayUsersMenu () {
		//displayUserListMenu selection 1

		int sel, age, itemNum;
		boolean exit = false;
		String name;
		String[] dateInfo;
		User[] tempUsers;
		User curUser;
		Date curDate;

		while (!exit) {
			System.out.println("\nDISPLAY USERS MENU\n");
			System.out.println("1. List by ID");
			System.out.println("2. List by name");
			System.out.println("3. List by age");
			System.out.println("4. List by overdue");
			System.out.println("5. List by number of items");
			System.out.println("6. Return to USER LIST MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Lists users by ID
					case 1:
						tempUsers = jurrLibrary.getUsers();

						for (int i = 0; i < tempUsers.length; i++) {
							System.out.println(tempUsers[i]);
							System.out.println();
						} //for loop

						break;

					//Lists users by name
					case 2:
						System.out.print("Enter a name: ");
						name = sc.nextLine();

						tempUsers = jurrLibrary.getUserByName(name);

						if (tempUsers != null) {
							for (int i = 0; i < tempUsers.length; i++) {
								System.out.println(tempUsers[i]);
							} //for loop
						} else {
							System.out.println("No users found with given name");
						}						

						break;

					//Lists users by age
					case 3:
						System.out.print("Enter age: ");
						age = sc.nextInt();
						sc.nextLine();

						tempUsers = User.searchByAge(jurrLibrary.getUsers(), age);

						if (tempUsers != null) {
							for (int i = 0; i < tempUsers.length; i++) {
								System.out.println(tempUsers[i]);
							} //for loop
						} else {
							System.out.println("No users found with given age");
						}

						break;

					//Lists users by overdues
					case 4:
						System.out.println("Enter date (YYYY/MM/DD): ");
						dateInfo = sc.nextLine().split("/");

						try {
							curDate = new Date(Integer.parseInt(dateInfo[0]), Integer.parseInt(dateInfo[1]), Integer.parseInt(dateInfo[2]), jurrLibrary.getCal());

							tempUsers = User.searchByOverdue(jurrLibrary.getUsers(), curDate);

							if (tempUsers != null) {
								for (int i = 0; i < tempUsers.length; i++) {
									System.out.println(tempUsers[i]);
								} //for loop
							} else {
								System.out.println("No users found with given overdue date");
							}

						} catch (Exception e) {
							System.out.println("Invalid input.");
						} //try-catch structure

						break;

					//Lists users by the number of items
					case 5:
						System.out.print("Enter number of items: ");
						itemNum = sc.nextInt();
						sc.nextLine();

						tempUsers = User.searchByNumItemsOut(jurrLibrary.getUsers(), itemNum);

						if (tempUsers != null) {
							for (int i = 0; i < tempUsers.length; i++) {
								System.out.println(tempUsers[i]);
							} //for loop
						} else {
							System.out.println("No users found with the given number of items");
						}

						break;
						
					case 6:
						exit = true;
						break;
						
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayUsersMenu method

	public static void displayReturnItem () {
		//displayMainMenu selection 4

		long id;
		boolean exit = false;
		String[] dateTemp;
		User curUser;
		Date curDate = null;

		System.out.println("Enter user ID: ");
		id = sc.nextLong();
		sc.nextLine();
		
		curUser = jurrLibrary.getUserById(id);
		
		if (curUser == null) {
			System.out.println("That user was not found.");
			exit = true;
		} else {
			System.out.print("Enter today's date (YYYY/MM/DD): ");
			dateTemp = sc.nextLine().split("/");
			
			try {
				curDate = new Date(Integer.parseInt(dateTemp[0]), Integer.parseInt(dateTemp[1]), Integer.parseInt(dateTemp[2]), jurrLibrary.getCal());
				
				System.out.println("Return all? (y/n)");
				String input = sc.nextLine();
				
				if (input.equals("y")) {
					curUser.takeBack(curDate);
					exit = true;
					System.out.println("New amount owed: " + curUser.getAmountOwed());
				} else if (!input.equals("n")) {
					System.out.println("Invalid input");
					exit = true;
				} //if structure
				
				while (!exit) {
					System.out.print("Enter the ID of the item being returned (\"Exit\" to return to MAIN MENU): ");
					id = sc.nextLong();
					sc.nextLine();
	            
					Item chosenItem = Item.searchById(curUser.getItems(), id);
					
					if (chosenItem == null) {
						System.out.println("That item was not found.");
						exit = true;
					} else {
						curUser.takeBack(chosenItem, curDate);
					} //if structure      
				} //while loop
			} catch (java.util.InputMismatchException e) {
				if (sc.nextLine().equalsIgnoreCase("exit")) {
					System.out.println("New amount owed: " + curUser.getAmountOwed());
				} else {
					System.out.println("Invalid input");
				} //if structure
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid input");
			} //try-catch structure
		} //if structure   
	} //displayReturnItem method

	public static void displayRoomMenu () {
		//displayMainMenu selection 5
		
		int sel;
		long roomId;
		long id;
		boolean exit = false;
		Room[] tempRooms;
		Room curRoom;
		User curUser;

		while (!exit) {
			System.out.println("\nROOM MENU\n");
			System.out.println("1. View all rooms");
			System.out.println("2. Search for room by ID");
			System.out.println("3. Add user");
			System.out.println("4. Remove user");
			System.out.println("5. Return to MAIN MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Displays a listing of all rooms
					case 1:
						tempRooms = jurrLibrary.getRooms();
						
						for (int i = 0; i < tempRooms.length; i++) {
							if (tempRooms[i] != null) {
								System.out.println(tempRooms[i]);
								System.out.println();
							} //if structure
						} //for loop

						break;

					//Displays a room with the matching entered ID
					case 2:
						System.out.println("Enter room ID: ");
						roomId = sc.nextLong();
						sc.nextLine();
						
						Room chosenRoom = (Room)UserHolder.searchById(jurrLibrary.getRooms(), roomId);
						
						if (chosenRoom == null) {
							System.out.println("That room was not found.");
						} else {
							System.out.println(chosenRoom);
						} //if structure
						
						break;

					//Adds a user to a room
					case 3:
						System.out.print("Enter room ID: ");
						roomId = sc.nextLong();
						sc.nextLine();

						curRoom = (Room)UserHolder.searchById(jurrLibrary.getRooms(), roomId);
						
						if (curRoom == null) {
							System.out.println("That room was not found.");
						} else {
							System.out.print("Enter user ID: ");
							id = sc.nextLong();
							sc.nextLine();
	
							curUser = jurrLibrary.getUserById(id);
	
							if (curUser == null) {
								System.out.println("That user was not found.");
							} else if (curRoom.addUser(curUser)) {
								System.out.println("User entered room");
							} else {
								System.out.println("Could not add user to room");
							} //if structure
						} //if structure

						break;

					//Enters a sub menu to remove users from a room
					case 4:
						System.out.print("Enter room ID: ");
						roomId = sc.nextLong();
						sc.nextLine();

						curRoom = (Room)UserHolder.searchById(jurrLibrary.getRooms(), roomId);
						
						if (curRoom == null) {
							System.out.println("That room was not found.");
						} else {
							displayRemoveFromRoomMenu(curRoom);
						} //if structure
						
						break;

					case 5:	
						exit = true;
						break;
						
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayRoomMenu method

	public static void displayRemoveFromRoomMenu (Room curRoom) {
		//displayRoomMenu selection 4

		int sel;
		long id;
		long roomId;
		boolean exit = false;
		User curUser;

		while (!exit) {
			System.out.println("\nREMOVE FROM ROOM MENU\n");
			System.out.println("1. Remove all user(s)");
			System.out.println("2. Remove user by ID");
			System.out.println("3. Return to ROOM MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Removes all users from the room
					case 1:
						curRoom.remAllUsers();
						System.out.println("All users removed");

						break;

					//Removes a single user from the room specified by ID
					case 2:
						System.out.print("Enter a user ID: ");
						id = sc.nextLong();
						sc.nextLine();

						if (curRoom.remUser(id)) {
							System.out.println("User removed");
						} else {
							System.out.println("User not found");
						} //if structure

						break;

					case 3:
						exit = true;
						break;
					
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayRemoveFromRoomMenu method

	public static void displayComputerMenu () {
		//displayMainMenu selection 6

		int sel;
		long id;
		long comId;
		boolean exit = false;
		Computer[] tempComs;
		Computer curCom;
		User curUser;

		while (!exit) {
			System.out.println("\nCOMPUTER MENU\n");
			System.out.println("1. View all computers");
			System.out.println("2. Search for computer by ID");
			System.out.println("3. Add user");
			System.out.println("4. Remove user");
			System.out.println("5. View printer options");
			System.out.println("6. Return to MAIN MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					//Displays a listing of all computers
					case 1:
						tempComs = jurrLibrary.getComputers();
						
						for (int i = 0; i < tempComs.length; i++) {
							if (tempComs[i] != null) {
								System.out.println(tempComs[i]);
								System.out.println();
							} //if structure
						} //for loop

						break;
					//Displays a computer with matching entered ID
					case 2:
						System.out.println("Enter Computer ID: ");
						comId = sc.nextLong();
						sc.nextLine();
						
						Computer chosenCom = jurrLibrary.getComputerById(comId);
						
						if (chosenCom == null) {
							System.out.println("That computer was not found.");
						} else {
							System.out.println(chosenCom);
						} //if structure
						
						break;

					//Adds a user to a computer
					case 3:
						System.out.print("Enter Computer ID: ");
						comId = sc.nextLong();
						sc.nextLine();

						curCom = jurrLibrary.getComputerById(comId);
						
						if (curCom == null) {
							System.out.println("That Computer was not found.");
						} else {
							System.out.print("Enter user ID: ");
							id = sc.nextLong();
							sc.nextLine();
	
							curUser = jurrLibrary.getUserById(id);
	
							if (curUser == null) {
								System.out.println("That user was not found.");
							} else if (curCom.addUser(curUser)) {
								System.out.println("Successfully added user to Computer");
							} else {
								System.out.println("That computer is occupied.");
							} //if structure
						} //if structure

						break;

					//Removes a user from a computer
					case 4:
						System.out.print("Enter Computer ID: ");
						comId = sc.nextLong();
						sc.nextLine();

						curCom = jurrLibrary.getComputerById(comId);
						
						if (curCom == null) {
							System.out.println("That computer was not found.");
						} else {
							//A computer will only ever have one user at max
							if (curCom.remUser()) {
								System.out.println("User removed");
							} else {
								System.out.println("Computer unoccupied");
							} //if structure
						} //if structure

						break;

					case 5:
						displayPrinterMenu();
						break;

					case 6:
						exit = true;
                  break;
						
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayComputerMenu method

	public static void displayPrinterMenu () {
		int sel;
		int sheets;
		long id;
		long comId;
		long printerId;
		boolean exit = false;
		boolean colour = false;
		boolean valid;
		String colourOption;
		User curUser;
		Computer curCom;
		Printer curPrinter;

		while (!exit) {
			System.out.println("\nPRINTER MENU\n");
			System.out.println("1. Print");
			System.out.println("2. Add paper");
			System.out.println("3. Return to COMPUTER MENU");
			System.out.println();                  	
         
         

			try {				
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();
				sc.nextLine();

				switch (sel) {
					
					case 1:
						try {
							System.out.print("Enter a user ID: ");
							id = sc.nextLong();
							System.out.print("Enter a computer ID: ");
							comId = sc.nextLong();

                     //Finding the user and computer specified by their IDs
							curUser = jurrLibrary.getUserById(id);
							curCom = jurrLibrary.getComputerById(comId);
							System.out.println();

							if (curUser == null && curCom == null) {
								System.out.println("User and computer not found");
							} else if (curUser == null) {
								System.out.println("User not found");
							} else if (curCom == null) {
								System.out.println("Computer not found");
							} else {
								sc.nextLine();
								System.out.print("Colour print? (y/n) :");
								colourOption = sc.nextLine();

                        //determining whether colour printing variable is valid, yes, or no
								if (colourOption.equalsIgnoreCase("y")) {
									colour = true;
									valid = true;
								} else if (colourOption.equalsIgnoreCase("n")) {
									colour = false;
									valid = true;
								} else {
									System.out.println("Invalid input");
									valid = false;
								}

								if (valid) {
									System.out.print("Enter number of sheets to be printed: ");
									sheets = sc.nextInt();

									if (curCom.print(colour, sheets, curUser)) {
										System.out.println("Print successful");
									} else {
										System.out.println("Problem printing paper");
									}
								}
							} //if structure

						} catch (java.util.InputMismatchException e) {
							sc.nextLine();
							System.out.println("Invalid input");
						}
                  
                  break;

					case 2:
						try {
							System.out.print("Enter printer ID: ");
							printerId = sc.nextInt();
							System.out.print("Enter the number of sheets to add: ");
							sheets = sc.nextInt();

                     //finds the printer specified by ID
							curPrinter = jurrLibrary.getPrinterById(printerId);

							if (curPrinter == null) {
								System.out.println("Printer not found");
							} else {
								if (curPrinter.addPaper(sheets)) {
									System.out.println("Paper added");
								} else {
									System.out.println("Problem adding paper");
								}
							}

						} catch (java.util.InputMismatchException e) {
                     sc.nextLine();
							System.out.println("Invalid input");
						}
                  
                  break;

					case 3:
						exit = true;
						break;
						
					default:
						System.out.println("Invalid choice.");
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				sc.nextLine();
				System.out.println("Invalid input");
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	} //displayPrinterMenu method
} //LibraryRunner class