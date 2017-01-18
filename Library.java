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
	
	public Library (String userFiletext, String userHolderText, String itemFileText, 
			String printerFileText){
		
		try{
			//Item file reader begins
			BufferedReader itemIn =  new BufferedReader(new FileReader("items.txt"));
			int itemAmount = Integer.parseInt(itemIn.readline());
			items = new Item[itemAmount];
			for(int i = 0;i < itemAmount; i++){
				in.readLine();
				String type = in.readLine();
				if(type.equals("Book")){
					String title = itemIn.readLine();
					boolean isOut = Boolean.parseBoolean(itemIn.readLine());
					long id = Long.parseLong(itemIn.readLine());
					String lineOfDate = itemIn.readLine();
					int [] date = Integer.parseInt(lineOfDate.split("/"));
					Date dayBorrowed = new Date(date[0],date[1],date[2],cal);
					String author =  itemIn.readLine();
					int pages =  Integer.parseInt(itemIn.readLine());
					double deweyDecNum = itemIn.readLine();
					items[i] = new Book(title,isOut,id,dayBorrowed,author,pages,deweyDecNum,deweySystem);
				}//Book reader
				else if(type.equals("Movie")){
					long id = Long.parseLong(itemIn.readLine());
					boolean isOut = Boolean.parseBoolean(itemIn.readLine());
					String title = itemIn.readLine();
					String lineOfDate = itemIn.readLine();
					int [] date =  Integer.parseInt(lineOfDate.split("/"));
					Date dayBorrowed = new Date(date[0],date[1],date[2],cal);
					String director = itemIn.readLine();
					String genre = itemIn.readLine();
					int length = Integer.parseInt(itemIn.readLine());
					int ageRating = Integer.parseInt(itemIn.readLine());
					items[i] = new Movie(id,isOut,title,dateBorrowed,director,genre,length,ageRating);
				}//Movie reader
				else if(type.equals("Video Game")){
					long id = Long.parseLong(itemIn.readLine());
					boolean isOut = Boolean.parseBoolean(itemIn.readLine());
					String title = itemIn.readLine();
					String lineOfDate = itemIn.readLine();
					int [] date = Integer.parseInt(lineOfDate.split("/"));
					Date dayBorrowed = new Date(date[0],date[1],date[2],cal);
					String developer =  itemIn.readLine();
					String genre = itemIn.readLine();
					int ageRating = Integer.parseInt(itemIn.readLine());
					items[i] =  new VideoGame(id,isOut,title,dayBorrowed,developer,genre,ageRating);
				}//Video Game reader
			}
			
			//User file reader begins
			BufferedReader userIn = new BufferedReader (new FileReader ("users.txt"));
			int userAmount = Integer.parseInt(in.readLine());
			users = new User[userAmount];
			for (int i = 0; i < userAmount; i ++){
				userIn.readLine();
				String name = userIn.readLine();
				long id = Long.parseLong(userIn.readLine());
				int age = Integer.parseInt(userIn.readLine());
				double amountOwed = Double.parseDouble(userIn.readLine());
				String itemList = userIn.readLine();
				long itemArray = itemList.split(",");
				Item [] itemList =  new Item[itemArray.length];
				for(int i = 0;i<itemArray.length;i++){
					
				}
				users[i] =  new User(name,id,age,amountOwed,itemList);
			}
			
			//UserHolder file reader begins
			BufferedReader holderIn =  new BufferedReader(new FileReader("userHolder.txt"));
			int userHolderAmount = Integer.parseInt(holderIn.readLine());
			computerList =  new Computer[userHolderAmount];
			roomList = new Room[userHolderAmount];
			for(int i = 0; i<userHolderAmount;i++){
				String type = holderIn.readLine();
				int computerCount = 0;
				int roomCount = 0;
				if(type.equals("Room")){
					long id = Long.parseLong(holderIn.readLine());
					int maxUser =  Integer.parseInt(holderIn.readLine());
					String lineOfUser = holderIn.readLine();
					long [] userId = Long.parseLong(lineOfUser.split(","));
					User [] arrayOfUser = new User[maxUser];
					for(int u=0;u<maxUser;u++){
						User found = searchById(users,userId[u]);
						if(found != null){
							arrayOfUser[roomCount] = found;
						}
					}
					roomList[roomCount] =  new Room(id,maxUser);
					for(int u=0;u<arrayOfUser.length;u++){
						roomList[roomCount].addUser(arrayOfUser[u]);
					}
					roomCount++;
				}
				else if(type.equals("Computer")){
					long id = Long.parseLong(holderIn.readLine());
					boolean occupied = Boolean.parseBoolean(holderIn.readLine());
					long userId =  Long.parseLong(holderIn.readLine());
					User foundUser =  searchById(users,userId);
					String lineOfPrinters= holderIn.readLine();
					long [] printerId = new Printer(Long.parseLong(lineOfPrinters.split(",")));
					computerList[computerCount] =  new Computer(id,occupied);
					if(occupied == true){
						computerList[computerCount].addUser(foundUser);
					}
					//add user
					computerCount++;
				}
			}
			
			//Printer file reader begins
			BufferedReader printerIn =  new BufferedReader(new FileReader("printer.txt"));
			int printerAmount = Integer.parseInt(printerIn.readLine());
			printers = new Printer[printerAmount];
			for(int i = 0;i<printerAmount;i++){
				long id = Long.parseLong(printerIn.readLine());
				int max = Integer.parseInt(printerIn.readLine());
				int num = Integer.parseInt(printerIn.readLine());
				printers [i] = new Printer(id,max,num);
			}
		} catch (Exception e) {
		
		}
						 
	}
	
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
	
	private static int totalLength (Movie[] movies) {
		int sum = 0;
		
		for (int i = 0; i < movies.length; i++) {
			sum += movies[i].getLength();
		} //for loop
		
		return sum;
	} //totalLength method
	
	public static Movie[] suggestMovies (Movie[] movies, int desired) {
		//Check if there are no more movies
		if (movies.length == 1) {
			return movies;
		} //if structure
		
		//Array of movies with the closest desired length
		Movie[] closestMovies = movies;
		//Closeness to length
		int closeness = Math.abs(desired - totalLength(movies));
		
		for (int movie = 0; movie < movies.length; movie++) {
			Movie current = movies[movie];
			
			//Update closest if current array is closer than the stored array
			if (closeness - Math.abs(desired - current.getLength()) > 0) {
				closeness = Math.abs(desired - current.getLength());
				closestMovies = new Movie[1];
				closestMovies[0] = current;
			} //if structure
			
			//Array of all movies after the current one
			Movie[] successiveMovies = new Movie[movies.length - movie - 1];
			
			//Copy all successive items to new list
			for (int i = 1; i <= successiveMovies.length; i++) {
				successiveMovies[i - 1] = movies[movie + i];
			} //for loop
			
			//Find array closest to new desired length
			for (int i = movie; i <= successiveMovies.length; i++) {
				Movie[] moviesToCheck = suggestMovies(successiveMovies, desired - current.getLength());
				
				int checkLength = totalLength(moviesToCheck);
				
				//Update closest if current array is closer than the stored array
				if (closeness - Math.abs(desired - checkLength - current.getLength()) > 0) {
					closeness = Math.abs(desired - checkLength - current.getLength());
					
					//Insert current movie into array
					closestMovies = new Movie[moviesToCheck.length + 1];
					closestMovies[0] = current;
					
					for (int j = 1; j < closestMovies.length; j++) {
						closestMovies[j] = moviesToCheck[j - 1];
					} //for loop
				} //if structure
			} //for loop
		} //for loop
		
		return closestMovies;
	} //suggestMovies method
	
	private static int totalPages (Book[] books) {
		int sum = 0;
		
		for (int i = 0; i < books.length; i++) {
			sum += books[i].getPages();
		} //for loop
		
		return sum;
	} //totalPages method
	
	public static Book[] suggestBooks (Book[] books, int desired) {
		//Check if there are no more books
		if (books.length == 1) {
			return books;
		} //if structure
		
		//Array of books with the closest desired length
		Book[] closestBooks = books;
		//Closeness to length
		int closeness = Math.abs(desired - totalPages(books));
		
		for (int book = 0; book < books.length; book++) {
			Book current = books[book];
			
			//Update closest if current array is closer than the stored array
			if (closeness - Math.abs(desired - current.getPages()) > 0) {
				closeness = Math.abs(desired - current.getPages());
				closestBooks = new Book[1];
				closestBooks[0] = current;
			} //if structure
			
			//Array of all books after the current one
			Book[] successiveBooks = new Book[books.length - book - 1];
			
			//Copy all successive items to new list
			for (int i = 1; i <= successiveBooks.length; i++) {
				successiveBooks[i - 1] = books[book + i];
			} //for loop
			
			//Find array closest to new desired length
			for (int i = book; i <= successiveBooks.length; i++) {
				Book[] booksToCheck = suggestBooks(successiveBooks, desired - current.getPages());
				
				int checkPages = totalPages(booksToCheck);
				
				//Update closest if current array is closer than the stored array
				if (closeness - Math.abs(desired - checkPages - current.getPages()) > 0) {
					closeness = Math.abs(desired - checkPages - current.getPages());
					
					//Insert current book into array
					closestBooks = new Book[booksToCheck.length + 1];
					closestBooks[0] = current;
					
					for (int j = 1; j < closestBooks.length; j++) {
						closestBooks[j] = booksToCheck[j - 1];
					} //for loop
				} //if structure
			} //for loop
		} //for loop
		
		return closestBooks;
	} //suggestBooks method
	public void writeToFile(){
		try{
  BufferedWriter out = new BufferedWriter (new FileWriter ("printers.txt", true));
  out.write(""+printers.length);
  for (int i =0; i < printers.length; i ++){
    out.newLine();
    out.write(""+printers[i].id);  
    out.newLine();
    out.write(""+printers[i].maxPaper);
    out.newLine();
    out.write(""+printers[i].numPaper);
    out.newLine();
    out.newLine():
  }
  printerOut.close();
}catch (IOException e){
  System.out.println("Problem with file" + e.getMessage());
  
}
try{
  BufferedWriter itemOut = new BufferedWriter (new FileWriter ("items.txt", true));
  out.write(""+items.length);
  for (int i = 0; i < items.length; i ++){
    out.newLine();
    if (items[i] instanceof Book){
      out.write("Book");
      out.newLine();
      out.write(items[i].title);
      out.newLine();
      if (items[i].getIsOut())
        out.write("true");
      else
        out.write("false");
      out.newLine();
      out.write(""+items[i].id);
      out.newLine();
      out.write(items[i].dayBorrowed.getDay()+"/");
      out.write(items[i].dayBorrowed.getMonth()+"/");
      out.write(items[i].dayBorrowed.getYear()+"");
      out.newLine();
      out.write(((Book)(items[i])).getAuthor());
      out.newLine();
      out.write(((Book)(items[i])).getPages()+"");
      out.newLine();
      out.write(((Book)(items[i])).getDeweyDecNum()+"");
      out.newLine();
    }
    else if (items[i] instanceof Movie){
      out.write("Movie");
      out.newLine();
      out.write(""+ items[i].id);
      out.newLine();
      if(items[i].getIsOut())
        out.write("true");
      else
        out.write("false");
      out.newLine();
      out.write(items[i].title);
      out.newLine();
      out.write(items[i].dayBorrowed.getDay()+"/");
      out.write(items[i].dayBorrowed.getMonth()+"/");
      out.write(items[i].dayBorrowed.getYear()+"");
      out.newLine();
      out.write(((Movie)(items[i])).getDirector());
      out.newLine();
      out.write(((Movie)(items[i])).getGenre());
      out.newLine();
      out.write(((Movie)(items[i])).getLength()+"");
      out.newLine();
      out.write(((Movie)(items[i])).getAgeRating()+"");
      out.newLine();
    }
    else if (items[i] instanceof VideoGame){
      out.write("Video Game");
      out.newLine();
      out.write(items[i].getId()+"");
      out.newLine();
      if (items[i].getIsOut())
        out.write("true");
      else
        out.write("false");
      out.newLine();
      out.write(items[i].getTitle());
      out.newLine();
      out.write(items[i].dayBorrowed.getDay()+"/");
      out.write(items[i].dayBorrowed.getMonth()+"/");
      out.write(items[i].dayBorrowed.getYear()+"");
      out.newLine();
      out.write(((VideoGame)(items[i])).getDeveloper());
      out.newLine();
      out.write(((VideoGame)(items[i])).getGenre());
      out.newLine();
      out.write(((VideoGame)(items[i])).getAgeRating()+"");
      out.newLine();
    }
      
    }
  out.close();
}catch (IOException e){
  System.out.println("Problem with using file" + e.getMessage());
  
}
	}
} //Library class
