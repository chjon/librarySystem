/*******************************************************************************
 * File Name:     Library.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines a library.
 *******************************************************************************/

import java.io.*;

public class Library {
	private static final long MAX_ID = 99999999;			//maximum allowed ID number
	private String name; 						//name of library
	private User[] users;						//list of users
	private Computer[] computerList;    				//list of computers
	private Room[] roomList;					//list of rooms
	private Printer[] printers;					//list of printers
	private Item[] items;						//list of items in library
	private Calendar cal;						//library calendar system
	private DeweyDecSystem deweySystem;				//library Dewey Decimal system
	
	//New Library constructor
	public Library () {
		name = "JURR Library";
		users = new User[0];
		computerList = new Computer[0];
		roomList = new Room[0];
		printers = new Printer[0];
		items = new Item[0];
		cal = new Calendar();
		deweySystem = new DeweyDecSystem();
	} //Library constructor
	
	public void setName (String name) {
		this.name = name;
	} //setName method
	
	public String getName () {
		return name;
	} //getName method
	
	public User[] getUsers () {
		return users;
	} //getUsers method
	
	private long genId () {
		long id;
		boolean isUnused;
		do {
			id = (long)(Math.floor(Math.random() * (1 + MAX_ID)));
			
			isUnused = true;
			
			for (int i = 0; i < users.length && isUnused; i++) {
				if (users[i].getId() == id) {
					isUnused = false;
				} //if structure
			} //for loop
		} while (!isUnused);
		
		return id;
	} //genId method
	
	//Add new user
	public void addUser (String name, int age) {
		User[] temp = new User[users.length + 1];
		
		//Copy users to larger array
		for (int i = 0; i < users.length; i++) {
			temp[i] = users[i];
		} //for loop
		
		//Insert new user
		temp[users.length] = new User(name, genId(), age, 0);
		users = temp;
	} //addUser method
	
	//Remove a user
	public boolean remUser (long id) {
		boolean found = false;
		
		//Check if printer is in array
		for (int i = 0; i < users.length && !found; i++) {
			if (users[i].getId() == id) {
				found = true;
			} //if structure
		} //for loop
		
		if (found) {
			int currentIndex = 0;
			
			//one less than the original length for the removed user
			User[] temp = new User[users.length - 1];
			
			//Copy only the users that are not targeted for removal
			for (int i = 0; i < temp.length; i++) {
				if (users[i].getId() != id) {
					temp[currentIndex] = users[i];
					currentIndex++;
				} //if structure
			} //for loop
	
			users = temp;
		} //if structure
		
		return found;
	} //remUser method
	
	//finds user with specific id
	public User getUserById (long id) {
		User.sortById(users);
		return User.searchById(users, id);
	} //getUserById method

	//searches for user by name
	public User[] getUserByName (String name) {
		User.sortByName(users);
		return User.searchByName(users, name);
	} //getUserByName method

	public Room[] getRooms () {
		return roomList;
	} //getRooms method

	//Search for a room by ID
	public Room getRoomById (long id) {
		
		Room[] rooms = getRooms();
		
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].getId() == id) {
				return rooms[i];
			} //if structure
		} //for loop
		
		//return null if room not found
		return null;
	} //getRoomById method

	public Computer[] getComputers () {
		return computerList;
	} //getComputers method
	
	//finds computer with given id
	public Computer getComputerById (long id) {
		Computer[] computers = getComputers();
		for (int i = 0; i < computers.length; i++) {
			if (computers[i].getId() == id) {
				return computers[i];
			} //if structure
		} //for loop
		
		return null;
	} //getComputerById method
	
	//accessor for printers
	public Printer[] getPrinters () {
		return printers;
	} //getPrinters method
	
	//returns printer with specific id
	public Printer getPrinterById (long id) {
		for (int i = 0; i < printers.length; i++) {
			if (printers[i].getId() == id) {
				return printers[i];
			} //if structure
		} //for loop
		
		return null;
	} //getPrinterById method

	public Item[] getItems () {
		return items;
	} //getItems method
	
	//searches for item with given id
	public Item getItemById (long id) {
		Item.sortById(items);
		return Item.searchById(items, id);
	} //getItemById method
	
	//Search for items by title
	public Item[] getItemsByTitle (String title) {
		Item.sortByTitle(items);
		return Item.searchByTitle(items, title);
	} //getItemsByTitle method
	
	//returns all books in the library
	public Book[] getBooks () {
		Book[] bookList;
		int bookListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Book) {
				bookListSize ++;
			} //if structure
		} //for loop
		
		bookList = new Book[bookListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Book) {
				bookList[i] = (Book)items[i];
			} //if structure
		} //for loop
		
		return bookList;
	} //getBooks method
	
	//returns all books with specific author
	public Book[] getBooksByAuthor (String author) {		
		Book[] bookList;
		int bookListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Book && ((Book)items[i]).getAuthor().equals(author)) {
				bookListSize ++;
			} //if structure
		} //for loop
		
		bookList = new Book[bookListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Book && ((Book)items[i]).getAuthor().equals(author)) {
				bookList[i] = (Book)items[i];
			} //if structure
		} //for loop
		
		return bookList;
	} //getBooksByAuthor method
	
	//returns all video games
	public VideoGame[] getVideoGames () { 			
		VideoGame[] gameList;
		int gameListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame) {
				gameListSize ++;
			} //if structure
		} //for loop
		
		gameList = new VideoGame[gameListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame) {
				gameList[i] = (VideoGame)items[i];
			} //if structure
		} //for loop
		
		return gameList;
	} //getVideoGames method
	
	//gets all video games with specific developer
	public VideoGame[] getVideoGamesByDev (String dev) {			
		VideoGame[] gameList;
		int gameListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && ((VideoGame)items[i]).getDeveloper().equals(dev)) {
				gameListSize ++;
			} //if structure
		} //for loop
		
		gameList = new VideoGame[gameListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && ((VideoGame)items[i]).getDeveloper().equals(dev)) {
				gameList[i] = (VideoGame)items[i];
			} //if structure
		} //for loop
		
		return gameList;
	} //getVideoGamesByDev method
	
	// return all video games with specific genre
	public VideoGame[] getVideoGamesByGenre (String genre) { 		
		VideoGame[] gameList;
		int gameListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && items[i].getGenre().equals(genre)) {
				gameListSize ++;
			} //if structure
		} //for loop
		
		gameList = new VideoGame[gameListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && items[i].getGenre().equals(genre)) {
				gameList[i] = (VideoGame)items[i];
			} //if structure
		} //for loop
		
		return gameList;
	} //getVideoGamesByGenre
	
	//returns all video games with specific age rating
	public VideoGame[] getVideoGamesByRating (int rating) {			
		VideoGame[] gameList;
		int gameListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && ((VideoGame)items[i]).getAgeRating() == rating) {
				gameListSize ++;
			} //if structure
		} //for loop
		
		gameList = new VideoGame[gameListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && ((VideoGame)items[i]).getAgeRating() == rating) {
				gameList[i] = (VideoGame)items[i];
			} //if structure
		} //for loop
		
		return gameList;
	} //getVideosByRating method
	
	// returns all movies with specific director
	public Movie[] getMoviesByDirector (String director) { 		
		Movie[] movieList;
		int movieListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Movie && ((Movie)items[i]).getDirector().equals(director)) {
				movieListSize ++;
			} //if structure
		} //for loop
		
		movieList = new Movie[movieListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Movie && ((Movie)items[i]).getDirector().equals(director)) {
				movieList[i] = (Movie)items[i];
			} //if structure
		} //for loop
		
		return movieList;
	} //getMoviesByDirector method
	
	// returns all the movies in the library
	public Movie[] getMovies () {							
		Movie[] movieList;
		int movieListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Movie) {
				movieListSize ++;
			} //if structure
		} //for loop
		
		movieList = new Movie[movieListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Movie) {
				movieList[i] = (Movie)items[i];
			} //if structure
		} //for loop
		
		return movieList;
	} //getMovies method
	
	// finds items that are overdue by the given Date object
	public Item[] getOverdue (Date curDate) {   		
		int numOverdue = 0, curIndex = 0;
	
		for (int i = 0; i < items.length; i++) {
			if (items[i].isOverdue(curDate)) {
				numOverdue++;
			} //if structure
		} //for loop
		
		Item[] overdueItems = new Item[numOverdue];
		
		for (int i = 0; i < items.length; i++) {
			if (items[i].isOverdue(curDate)) {
				overdueItems[curIndex] = items[i];
				curIndex++;
			} //if structure
		} //for loop
		
		return overdueItems;
	} //getOverdue method
	
	//searches for users with x items
	public User[] searchUsersByAmount(int amount){			
	  User[] amountArray = new User[users.length];
	  for (int i = 0; i < amountArray.length; i ++){
	    for (int r = 0; r < amount; r ++){
	      boolean theseItems = false;
	      if (users[i].itemsList[r] == null){
		r = amount;
		theseItems = false;
	      }
	      else
		theseItems = true;
	    }
	    if (theseItems)
	      amountArray[i] = users[i];

	  }
	  return amountArray;
	} //searchUsersByAmount method
} //Library class
