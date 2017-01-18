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
			} catch (IOException e) {
				System.out.println("Problem with using file");
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
			} catch (IOException e) {
				System.out.println("Problem with using file");
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
		Item[] tempList;

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
						System.out.print("Enter an expiry date (YYYY,MM,DD): ");
						date = sc.nextLine();
						//NOTE no method found for finding items by expiry date 									//
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
			} catch (IOException e) {
				System.out.println("Problem with using file");
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayAccountMenu();
	} //viewCurrentItemsMenu method

	public static void displayLibraryInventoryMenu () {
		//displayMainMenu selection 2

		int sel;
		long id;
		boolean exit = false;
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
						//NOTE no addItem method found													//
						break;
					case 3:
						//NOTE no removeItem method found												//
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
			} catch (IOException e) {
				System.out.println("Problem with using file");
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
						//NOTE no getItemsByType method found												//
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
						//NOTE no getItemsByGenre method found													//
						break;
					case 6:
						exit = true;
						break;
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
			} catch (IOException e) {
				System.out.println("Problem with using file");
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
		String name;
		int age;
		int id;

		System.out.print("Enter account number: ");
		accNum = sc.nextLong();

		curUser = jurrLibrary.getUserById(accNum);

		while (!exit) {
			System.out.println("DISPLAY USERS MENU");
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

						break;
					case 5:
						exit = true;
						break;
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
			} catch (IOException e) {
				System.out.println("Problem with using file");
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

		System.out.print("Enter account number: ");
		accNum = sc.nextLong();

		curUser = jurrLibrary.getUserById(accNum);

		while (!exit) {
			System.out.println("DISPLAY USERS MENU");
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

						break;
					case 2:

						break;
					case 3:

						break;
					case 4:

						break;
					case 5:
						exit = true;
						break;
				} //switch structure to display sub menus
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input");
			} catch (IOException e) {
				System.out.println("Problem with using file");
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayUserListMenu();
	} //displayUsersMenu method

	public static void displayReturnItem () {
		//displayMainMenu selection 4
	} 

	public static void displayRoomMenu () {
		//displayMainMenu selection 5
	}

	public static void displayComputerMenu () {
		//displayMainMenu selection 6
	}
}