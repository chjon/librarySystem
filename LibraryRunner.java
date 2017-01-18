import java.util.Scanner;
import java.io.*;

public class LibraryRunner {
	public static void main (String[] args) {
		Library jurrLibrary = new Library();
		Scanner sc = new Scanner(System.in);

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
			} catch (InputMismatchException e) {
				System.out.println("Invalid input");
			} catch (IOException e) {
				System.out.println("Error using file");
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop
	} //displayMainMenu method

	public static void displayAccountMenu () {
		long accNum;
		int sel;
		boolean exit = false;

		System.out.print("Enter account number: ");
		accNum = sc.nextLong();

		while (!exit) {
			System.out.println("ACCOUNT MENU");
			System.out.println("1. Sign out item");
			System.out.println("2. View current items");
			System.out.println("3. Check overdue fees");
			System.out.println("4. Change account information");
			System.out.println("5. Change account information");
			System.out.println("6. Get suggested books");
			System.out.println("7. Get suggested movies");
			System.out.println("8. Return to main menu");

			try {
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
						
						break;
					case 6:
						
						break;
					case 7:
						
						break;
					case 8:
						exit = true;
						break;
				} //switch structure to display sub menus
			} catch (InputMismatchException e) {
				System.out.println("Invalid input");
			} catch (IOException e) {
				System.out.println("Error using file");
			} catch (Exception e) {
				System.out.println("Critical error");
			} //try and catch structure
		} //while loop

		displayMainMenu();
	}

	public static void displayLibraryInventoryMenu () {

	}

	public static void displayUserListMenu () {

	}

	public static void displayReturnItem () {

	}

	public static void displayRoomMenu () {

	}

	public static void displayComputerMenu () {

	}
}