/*******************************************************************************
 * File Name:     LibraryRunner.java
 * Class:         ICS4U-01
 * Date:          2017/01/17
 * Description:   This class runs the Library and interacts with the user.
 *******************************************************************************/

import java.util.Scanner;
import java.io.*;

public class LibraryRunner {
	private static Scanner sc = new Scanner(System.in);
	private static Library jurrLibrary = new Library();

	public static void main (String[] args) {

		displayMainMenu();
	} // main method

	public static void displayMainMenu () {
		int sel;
		boolean exit = false;

		while (!exit) {
			System.out.println("MAIN MENU");
			System.out.println("Welcome to the Library!\nPlease enter a selection:");
			System.out.println("1. Enter account credentials");
			System.out.println("2. View Library inventory");
			System.out.println("3. View user list");
			System.out.println("4. Return item");
			System.out.println("5. View rooms");
			System.out.println("6. View computers");
			System.out.println("7. Exit");

			try {
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						displayAccountMenu();
						break;

					case 2:
						displayLibraryInventoryMenu();
						break;

					case 3:
						displayUserListMenu();
						break;

					case 4:
						displayReturnItem();
						break;

					case 5:
						displayRoomMenu();
						break;

					case 6:
						displayComputerMenu();
						break;

					case 7:
						exit = true;
						break;
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop
	} //displayMainMenu method

	public static void displayAccountMenu () {
		//displayMainMenu selection 1

		long accNum; 
		int sel;
		int itemId;
		int newAge;
		int targetPages;
		int targetMinutes;
		boolean exit = false;
		String newName;
		User curUser;
		Item[] tempList;

		System.out.print("Enter account number: ");
		accNum = sc.nextLong();

		curUser = jurrLibrary.getUserById(accNum);

		while (!exit) {
			System.out.println("ACCOUNT MENU");
			System.out.println("1. Sign out item");
			System.out.println("2. View current items");
			System.out.println("3. Check overdue fees");
			System.out.println("4. Pay overdue fees");
			System.out.println("5. Change account information");
			System.out.println("6. Get suggested books");
			System.out.println("7. Get suggested movies");
			System.out.println("8. Return to main menu");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						System.out.print("Enter item ID: ");
						itemId = sc.nextInt();
						//current user takes out item
						curUser.takeOutItem(jurrLibrary.getItemById(itemId));
						break;

					case 2:
						viewCurrentItemsMenu();
						break;

					case 3:
						System.out.println("Amount owed: "+curUser.getAmountOwed());
						break;

					case 4:
						curUser.payFine();
						System.out.println("Transaction complete.  All fees have been paid");
						break;

					case 5:
						System.out.print("Enter name: ");
						newName = sc.nextLine();
						System.out.print("Enter age: ");
						newAge = sc.nextInt();
						curUser.editUser(newName, newAge);
						System.out.println("Account information changed");
						break;

					case 6:
						System.out.print("Enter a number of pages to read: ");
						targetPages = sc.nextInt();

						//returns a list of suggested books whose number of pages sum most closely to the target number of pages
						tempList = Library.suggestBooks(jurrLibrary.getBooks(), targetPages);

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}
						break;

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
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayMainMenu();
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
		Calendar cal = new Calendar();

		while (!exit) {
			System.out.println("VIEW CURRENT ITEMS MENU");
			System.out.println("1. Search for items by name");
			System.out.println("2. Search for item by expiry date");
			System.out.println("3. Search for item by ID");
			System.out.println("4. Return to ACCOUNT MENU");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						System.out.print("Enter a title: ");
						name = sc.nextLine();
						tempList = jurrLibrary.getItemsByTitle(name);

						//printing information for all items found with matching title
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}

						break;

					case 2:
						System.out.print("Enter an expiry date (YYYY/MM/DD): ");
						dateTemp = sc.nextLine().split("/");

						expDate = new Date(Integer.parseInt(dateTemp[0]), Integer.parseInt(dateTemp[1]), Integer.parseInt(dateTemp[2]), cal);

						tempList = jurrLibrary.getOverdue(expDate);
						
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}

						break;

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
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayAccountMenu();
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
			System.out.println("INVENTORY MENU");
			System.out.println("1. Display item list");
			System.out.println("2. Add item");
			System.out.println("3. Remove item");
			System.out.println("4. Check item status");
			System.out.println("5. Return to MAIN MENU");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						tempList = jurrLibrary.getItems();

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}

						break;

					case 2:
						System.out.print("Enter item ID: ");
						id = sc.nextLong();
						System.out.print("Enter item name: ");
						name = sc.nextLine();
						System.out.print("Enter item type (book, video game, movie): ");
						type = sc.nextLine();

						if (type.equalsIgnoreCase("book")) {
							parameters = new Object[BOOKPARAMETERS];

							System.out.print("Enter author name: ");
							parameters[0] = sc.nextLine();

							System.out.print("Enter the number of pages in the book: ");
							parameters[1] = (Integer)sc.nextInt();

							System.out.print("Enter the Dewey Decimal Number: ");
							parameters[2] = (Long)sc.nextLong();

							jurrLibrary.addItem(type, name, parameters);

						} else if (type.equalsIgnoreCase("video game")) {
							parameters = new Object[VIDEOGAMEPARAMETERS];

							System.out.print("Enter developer name: ");
							parameters[0] = sc.nextLine();

							System.out.print("Enter the genre: ");
							parameters[1] = sc.nextLine();

							System.out.print("Enter the age rating: ");
							parameters[2] = (Integer)sc.nextInt();

							jurrLibrary.addItem(type, name, parameters);

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

					case 3:
						System.out.print("Enter an ID: ");
						id = sc.nextLong();

						jurrLibrary.remItem(id);

						break;

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
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayMainMenu();
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
		Calendar cal = new Calendar();
		Item[] tempList;

		while (!exit) {
			System.out.println("DISPLAY ITEM MENU");
			System.out.println("1. Display by overdue");
			System.out.println("2. Display by type");
			System.out.println("3. Display by name");
			System.out.println("4. Display by ID");
			System.out.println("5. Display by genre");
			System.out.println("6. Return to INVENTORY MENU");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						System.out.print("Enter the overdue date (DD/MM/YYYY): ");
						dateHold = sc.nextLine().split("/");

						dueDate = new Date(Integer.parseInt(dateHold[0]), Integer.parseInt(dateHold[1]), Integer.parseInt(dateHold[2]), cal);

						tempList = jurrLibrary.getOverdue(dueDate);

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}

						break;

					case 2:
						System.out.print("Enter an item type (book / video game / movie): ");
						type = sc.nextLine();

						tempList = Item.searchByType(jurrLibrary.getItems(), type);

						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}
						break;

					case 3:
						System.out.print("Enter a title: ");
						name = sc.nextLine();
						tempList = jurrLibrary.getItemsByTitle(name);

						//printing information for all items found with matching title
						for (int i = 0; i < tempList.length; i++) {
							System.out.println(tempList[i]);
						}
						
						break;

					case 4:
						System.out.print("Enter item ID: ");
						id = sc.nextLong();
						System.out.println(jurrLibrary.getItemById(id));
						break;

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
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayLibraryInventoryMenu();

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
			System.out.println("USER LIST MENU");
			System.out.println("1. Display users");
			System.out.println("2. Add user");
			System.out.println("3. Remove user");
			System.out.println("4. Edit user");
			System.out.println("5. Return to MAIN MENU");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						displayUsersMenu();
						break;

					case 2:
						System.out.print("Enter a name: ");
						name = sc.nextLine();
						System.out.print("Enter an age: ");
						age = sc.nextInt();

						jurrLibrary.addUser(name, age);
						break;

					case 3:
						System.out.print("Enter an ID: ");
						id = sc.nextInt();

						jurrLibrary.remUser(id);
						break;

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
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayMainMenu();
	} //displayUserListMenu method

	public static void displayUsersMenu () {
		//displayUserListMenu selection 1

		long accNum;
		int sel;
		boolean exit = false;
		String name;
		User[] tempUsers;
		User curUser;

		System.out.print("Enter account number: ");
		accNum = sc.nextLong();

		curUser = jurrLibrary.getUserById(accNum);

		while (!exit) {
			System.out.println("DISPLAY USERS MENU");
			System.out.println("1. List by ID");
			System.out.println("2. List by name");
			System.out.println("3. List by age");
			System.out.println("4. List by overdue");
			System.out.println("5. List by number of items");
			System.out.println("6. Return to USER LIST MENU");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						tempUsers = jurrLibrary.getUsers();

						for (int i = 0; i < tempUsers.length; i++) {
							System.out.println(tempUsers[i]);
						}

						break;

					case 2:
						System.out.print("Enter a name: ");
						name = sc.nextLine();

						tempUsers = jurrLibrary.getUserByName(name);

						for (int i = 0; i < tempUsers.length; i++) {
							System.out.println(tempUsers[i]);
						}

						break;

					case 3:
						//NOTE getUsersByAge method not found															//
						break;

					case 4:
						//NOTE getUsersByOverdue method not found										//
						break;

					case 5:
						
						break;
						
					case 6:
						exit = true;
						break;
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayUserListMenu();
	} //displayUsersMenu method

	public static void displayReturnItem () {
		//displayMainMenu selection 4
		long id;
		boolean exit = false;
		String[] dateTemp;
		User curUser;
		Date curDate;
		Calendar cal = new Calendar();

		System.out.println("Enter user ID: ");
		id = sc.nextLong();

		curUser = jurrLibrary.getUserById(id);

		System.out.print("Enter today's date (YYYY/MM/DD");
		dateTemp = sc.nextLine().split("/");

		curDate = new Date(Integer.parseInt(dateTemp[0]), Integer.parseInt(dateTemp[1]), Integer.parseInt(dateTemp[2]), cal);

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
			System.out.println("ROOM MENU");
			System.out.println("1. View all rooms");
			System.out.println("2. Search for room by ID");
			System.out.println("3. Add user");
			System.out.println("4. Remove user");
			System.out.println("5. Return to MAIN MENU");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						tempRooms = jurrLibrary.getRooms();

						for (int i = 0; i < tempRooms.length; i++) {
							System.out.println(tempRooms[i]);
						}

						break;

					case 2:
						System.out.println("Enter room ID: ");
						roomId = sc.nextLong();

						System.out.println(jurrLibrary.getRoomById(roomId));
						break;

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
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayMainMenu();
	}

	public static void displayRemoveFromRoomMenu (Room curRoom) {
		//displayRoomMenu selection 4

		int sel;
		long id;
		long roomId;
		boolean exit = false;
		User curUser;

		while (!exit) {
			System.out.println("REMOVE FROM ROOM MENU");
			System.out.println("1. Remove all user(s)");
			System.out.println("2. Remove user by ID");
			System.out.println("3. Return to ROOM MENU");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						curRoom.remAllUsers();
						System.out.println("All users removed");

						break;

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
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayRoomMenu();
	}

	public static void displayComputerMenu () {
		//displayMainMenu selection 6

		int sel;
		long comId;
		boolean exit = false;
		Computer[] tempComs;
		Computer curCom;
		User curUser;

		while (!exit) {
			System.out.println("COMPUTER MENU");
			System.out.println("1. View all computers");
			System.out.println("2. Search for computer by ID");
			System.out.println("3. Add user");
			System.out.println("4. Remove user");
			System.out.println("5. Return to MAIN MENU");

			try {
				System.out.println("\nEnter a selection");
				sel = sc.nextInt();

				switch (sel) {
					case 1:
						tempComs = jurrLibrary.getComputers();

						for (int i = 0; i < tempComs.length; i++) {
							System.out.println(tempComs[i]);
						}

						break;

					case 2:
						System.out.println("Enter Computer ID: ");
						comId = sc.nextLong();

						System.out.println(jurrLibrary.getComputerById(comId));
						break;

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

					case 4:
						System.out.print("Enter Computer ID: ");
						comId = sc.nextLong();

						curCom = jurrLibrary.getComputerById(comId);

						if (curCom.remUser) {
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
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayMainMenu();
	}
}