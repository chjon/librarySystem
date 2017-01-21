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
						exit = true;
						System.out.println("EXITING PROGRAM");
						break;
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
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

		curUser = jurrLibrary.getUserById(accNum);

		System.out.println();

		while (!exit) {
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

				switch (sel) {
					//Signing out an item
					case 1:
						System.out.print("Enter item ID: ");
						itemId = sc.nextInt();
						//current user takes out item
						curUser.takeOutItem(jurrLibrary.getItemById(itemId));
						break;

					//Viewing current items in the account
					case 2:
						viewCurrentItemsMenu();
						break;

					//Displaying the amount owed to the Library
					case 3:
						System.out.println("Amount owed: "+curUser.getAmountOwed());
						break;

					//Paying overdue fines
					case 4:
						curUser.payFine();
						System.out.println("Transaction complete.  All fees have been paid");
						break;

					//Change information in the account
					case 5:
						System.out.print("Enter name: ");
						newName = sc.nextLine();
						System.out.print("Enter age: ");
						newAge = sc.nextInt();
						curUser.editUser(newName, newAge);
						System.out.println("Account information changed");
						break;

					//Returning books suggested that are closest to the number of pages the user enters
					case 6:
						System.out.print("Enter a number of pages to read: ");
						targetPages = sc.nextInt();

						//returns a list of suggested books whose number of pages sum most closely to the target number of pages
						tempList = Library.suggestBooks(jurrLibrary.getBooks(), targetPages);

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}
						break;

					//Returning movies suggested that are closest to the number of minutes the user enters
					case 7:
						System.out.print("Enter number of minutes to watch: ");
						targetMinutes = sc.nextInt();

						//returns a list of suggested movies whose time sum most closely to the target time
						tempList = Library.suggestMovies(jurrLibrary.getMovies(), targetMinutes);
						break;

					case 8:
						exit = true;
						break;
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
			System.out.println("\nVIEW CURRENT ITEMS MENU\n");
			System.out.println("1. Search for items by name");
			System.out.println("2. Search for item by expiry date");
			System.out.println("3. Search for item by ID");
			System.out.println("4. Return to ACCOUNT MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					//Displays a list of items according to their title
					case 1:
						System.out.print("Enter a title: ");
						name = sc.nextLine();
						tempList = jurrLibrary.getItemsByTitle(name);

						//printing information for all items found with matching title
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}

						break;

					//Displays a list of items according to their expiry/overdue date
					case 2:
						System.out.print("Enter an expiry date (YYYY/MM/DD): ");
						dateTemp = sc.nextLine().split("/");

						expDate = new Date(Integer.parseInt(dateTemp[0]), Integer.parseInt(dateTemp[1]), Integer.parseInt(dateTemp[2]), jurrLibrary.getCal());

						tempList = jurrLibrary.getOverdue(expDate);
						
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}

						break;

					//Displays an item with the matching ID
					case 3:
						System.out.print("Enter item ID: ");
						id = sc.nextLong();
						System.out.println(jurrLibrary.getItemById(id));
						break;

					case 4:
						exit = true;
						break;
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
					//Displays the item list of the Library inventory
					case 1:
						tempList = jurrLibrary.getItems();
                  
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
                     System.out.println();
						}

						break;

					//Add item to the list of items
					case 2:
						System.out.print("Enter item name: ");
						name = sc.nextLine();
						System.out.print("Enter item type (book, video game, movie): ");
						type = sc.nextLine();

						//If the item is of type Book
						if (type.equalsIgnoreCase("book")) {
							parameters = new Object[BOOKPARAMETERS];

							System.out.print("Enter author name: ");
							parameters[0] = sc.nextLine();

							System.out.print("Enter the number of pages in the book: ");
							parameters[1] = (Integer)sc.nextInt();

							System.out.print("Enter the Dewey Decimal Number: ");
							parameters[2] = (Long)sc.nextLong();

							jurrLibrary.addItem(type, name, parameters);

						//If the item is of type VideoGame
						} else if (type.equalsIgnoreCase("video game")) {
							parameters = new Object[VIDEOGAMEPARAMETERS];

							System.out.print("Enter developer name: ");
							parameters[0] = sc.nextLine();

							System.out.print("Enter the genre: ");
							parameters[1] = sc.nextLine();

							System.out.print("Enter the age rating: ");
							parameters[2] = (Integer)sc.nextInt();

							jurrLibrary.addItem(type, name, parameters);

						//If the item is of type Movie
						} else if (type.equalsIgnoreCase("movie")) {
							parameters = new Object[MOVIEPARAMETERS];

							System.out.print("Enter director name: ");
							parameters[0] = sc.nextLine();

							System.out.print("Enter the genre: ");
							parameters[1] = sc.nextLine();

							System.out.print("Enter the length of the movie (in minutes): ");
							parameters[2] = (Integer)sc.nextInt();

							System.out.print("Enter the age rating: ");
							parameters[3] = (Integer)sc.nextInt();

							jurrLibrary.addItem(type, name, parameters);
						}

						break;

					//Removes an item from the list by ID
					case 3:
						System.out.print("Enter an ID: ");
						id = sc.nextLong();

						jurrLibrary.remItem(id);

						break;

					//Checks to see if an item is in the library or not
					case 4:
						System.out.print("Enter item ID: ");
						id = sc.nextLong();

						if (jurrLibrary.getItemById(id) != null) {
							System.out.println("Item is in the library");
						} else {
							System.out.println("Item is not in the library");
						}

						break;

					case 5:
						exit = true;
						break;
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
		String type;;
		String[] dateHold;
		int year, month, day;
		Date dueDate;
		Item[] tempList;

		while (!exit) {
			System.out.println("\nDISPLAY ITEM MENU\n");
			System.out.println("1. Display by overdue");
			System.out.println("2. Display by type");
			System.out.println("3. Display by name");
			System.out.println("4. Display by ID");
			System.out.println("5. Display by genre");
			System.out.println("6. Return to INVENTORY MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					//Displays items that are overdue
					case 1:
						System.out.print("Enter the overdue date (DD/MM/YYYY): ");
						dateHold = sc.nextLine().split("/");

						dueDate = new Date(Integer.parseInt(dateHold[0]), Integer.parseInt(dateHold[1]), Integer.parseInt(dateHold[2]), jurrLibrary.getCal());

						tempList = jurrLibrary.getOverdue(dueDate);

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}

						break;

					//Displays item by the selected type (Book, VideoGame, Movie)
					case 2:
						System.out.print("Enter an item type (book / video game / movie): ");
						type = sc.nextLine();

						tempList = Item.searchByType(jurrLibrary.getItems(), type);

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}
						break;

					//Displays items by their title
					case 3:
						System.out.print("Enter a title: ");
						name = sc.nextLine();
						tempList = jurrLibrary.getItemsByTitle(name);

						//printing information for all items found with matching title
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}
						
						break;

					//Displays an item with the entered ID
					case 4:
						System.out.print("Enter item ID: ");
						id = sc.nextLong();
						System.out.println(jurrLibrary.getItemById(id));
						break;

					//Displays items by genre
					case 5:
						System.out.print("Enter a genre: ");
						genre = sc.nextLine();

						tempList = Item.searchByGenre(jurrLibrary.getItems(), genre);

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}

						break;

					case 6:
						exit = true;
						break;
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
						break;

					//Removes a user from the library user list
					case 3:
						System.out.print("Enter an ID: ");
						id = sc.nextInt();

						jurrLibrary.remUser(id);
						break;

					//Changes user information
					case 4:
						System.out.print("Enter account number: ");
						accNum = sc.nextLong();

						curUser = jurrLibrary.getUserById(accNum);

						System.out.print("Enter name: ");
						newName = sc.nextLine();
						System.out.print("Enter age: ");
						newAge = sc.nextInt();

						curUser.editUser(newName, newAge);

						System.out.println("Account information changed");
						break;

					case 5:
						exit = true;
						break;
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

				switch (sel) {
					//Lists users by ID
					case 1:
						tempUsers = jurrLibrary.getUsers();

						for (int i = 0; i < tempUsers.length; i++) {
							System.out.println(tempUsers[i]);
							System.out.println();
						}

						break;

					//Lists users by name
					case 2:
						System.out.print("Enter a name: ");
						name = sc.nextLine();

						tempUsers = jurrLibrary.getUserByName(name);

						for (int i = 0; i < tempUsers.length; i++) {
							System.out.println(tempUsers[i]);
						}

						break;

					//Lists users by age
					case 3:
						System.out.print("Enter age: ");
						age = sc.nextInt();

						tempUsers = User.searchByAge(jurrLibrary.getUsers(), age);

						for (int i = 0; i < tempUsers.length; i++) {
							System.out.println(tempUsers[i]);
						}

						break;

					//Lists users by overdues
					case 4:
						System.out.println("Enter date (YYYY/MM/DD): ");
						dateInfo = sc.nextLine().split("/");

						curDate = new Date(Integer.parseInt(dateInfo[0]), Integer.parseInt(dateInfo[1]), Integer.parseInt(dateInfo[2]), jurrLibrary.getCal());

						tempUsers = User.searchByOverdue(jurrLibrary.getUsers(), curDate);

						for (int i = 0; i < tempUsers.length; i++) {
							System.out.println(tempUsers[i]);
						}

						break;

					//Lists users by the number of items
					case 5:
						System.out.print("Enter number of items: ");
						itemNum = sc.nextInt();

						tempUsers = User.searchByNumItemsOut(jurrLibrary.getUsers(), itemNum);

						for (int i = 0; i < tempUsers.length; i++) {
							System.out.println(tempUsers[i]);
						}

						break;
						
					case 6:
						exit = true;
						break;
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
		Date curDate;

		System.out.println("Enter user ID: ");
		id = sc.nextLong();

		curUser = jurrLibrary.getUserById(id);

		System.out.print("Enter today's date (YYYY/MM/DD): ");
		dateTemp = sc.nextLine().split("/");

		curDate = new Date(Integer.parseInt(dateTemp[0]), Integer.parseInt(dateTemp[1]), Integer.parseInt(dateTemp[2]), jurrLibrary.getCal());

		try {
			while (!exit) {
				System.out.print("Enter the ID of the item being returned (\"Exit\" to return to MAIN MENU): ");
				id = sc.nextLong();

				curUser.takeBack(jurrLibrary.getItemById(id), curDate);
			}
		} catch (java.util.InputMismatchException e) {
			exit = true;
		}
	} 

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

				switch (sel) {
					//Displays a listing of all rooms
					case 1:
						tempRooms = jurrLibrary.getRooms();

						for (int i = 0; i < tempRooms.length; i++) {
							System.out.println(tempRooms[i]);
						}

						break;

					//Displays a room with the matching entered ID
					case 2:
						System.out.println("Enter room ID: ");
						roomId = sc.nextLong();

						System.out.println(jurrLibrary.getRoomById(roomId));
						break;

					//Adds a user to a room
					case 3:
						System.out.print("Enter room ID: ");
						roomId = sc.nextLong();

						curRoom = jurrLibrary.getRoomById(roomId);

						System.out.print("Enter user ID: ");
						id = sc.nextLong();

						curUser = jurrLibrary.getUserById(id);

						if (curRoom.addUser(curUser)) {
							System.out.println("User entered room");
						} else {
							System.out.println("Room full");
						}

						break;

					//Enters a sub menu to remove users from a room
					case 4:
						System.out.print("Enter room ID: ");
						roomId = sc.nextLong();

						curRoom = jurrLibrary.getRoomById(roomId);

						displayRemoveFromRoomMenu(curRoom);
						break;

					case 5:
						exit = true;
						break;
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	}

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

						if (curRoom.remUser(id)) {
							System.out.println("User removed");
						} else {
							System.out.println("User not found");
						}

						break;

					case 3:
						exit = true;
						break;

				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	}

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
			System.out.println("5. Return to MAIN MENU");
			System.out.println();

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					//Displays a listing of all computers
					case 1:
						tempComs = jurrLibrary.getComputers();

						for (int i = 0; i < tempComs.length; i++) {
							System.out.println(tempComs[i]);
						}

						break;

					//Displays a computer with matching entered ID
					case 2:
						System.out.println("Enter Computer ID: ");
						comId = sc.nextLong();

						System.out.println(jurrLibrary.getComputerById(comId));
						break;

					//Adds a user to a computer
					case 3:
						System.out.print("Enter room ID: ");
						comId = sc.nextLong();

						curCom = jurrLibrary.getComputerById(comId);

						System.out.print("Enter user ID: ");
						id = sc.nextLong();

						curUser = jurrLibrary.getUserById(id);

						if (curCom.addUser(curUser)) {
							System.out.println("User on computer");
						} else {
							System.out.println("Adding to computer failed");
						}

						break;

					//Removes a user from a computer
					case 4:
						System.out.print("Enter Computer ID: ");
						comId = sc.nextLong();

						curCom = jurrLibrary.getComputerById(comId);

						//A computer will only ever have one user at max
						if (curCom.remUser()) {
							System.out.println("User removed");
						} else {
							System.out.println("Computer unoccupied");
						}

						break;

					case 5:
						exit = true;
						break;
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Critical error");
				System.out.println(e.getMessage());
			} //try and catch structure
		} //while loop
	}
}