/*******************************************************************************
 * File Name:     Library.java
 * Class:         ICS4U-01
 * Description:   This class defines a library.
 *******************************************************************************/

import java.io.*;

public class Library {
 private static final String DATA_FILE_DIRECTORY = "";
 private static final String ITEM_FILE = "items.txt";
 private static final String USER_FILE = "users.txt";
 private static final String USER_HOLDER_FILE = "userHolders.txt";
 private static final String PRINTER_FILE = "printers.txt";
 private static final long MAX_ID = 99999999;   //maximum allowed ID number
 private String name;       //name of library
 private User[] users;      //list of users
 private Computer[] computerList;        //list of computers
 private Room[] roomList;     //list of rooms
 private Printer[] printers;     //list of printers
 private Item[] items;      //list of items in library
 private Calendar cal;      //library calendar system
 private DeweyDecSystem deweySystem;    //library Dewey Decimal system
 
 //Parse a String array to integer values
 private int[] toIntFromString (String[] stringValues) {
  int[] values = new int[stringValues.length];
 
  for (int i = 0; i < stringValues.length; i++) {
   values[i] = Integer.parseInt(stringValues[i]);
  } //for loop
  
  return values;
 } //toIntFromString method
 
 //Parse a String array to long values
 private long[] toLongFromString (String[] stringValues) {
  long[] values = new long[stringValues.length];
 
  for (int i = 0; i < stringValues.length; i++) {
   values[i] = Long.parseLong(stringValues[i]);
  } //for loop
  
  return values;
 } //toLongFromString method
 
 //Library constructor - load from file
 public Library () {
  
  //Item file reader
  try {
   BufferedReader itemIn =  new BufferedReader(new FileReader(ITEM_FILE));
   int itemAmount = Integer.parseInt(itemIn.readLine());
   items = new Item[itemAmount];
   
   for (int i = 0; i < itemAmount; i++) {
    itemIn.readLine();
    String type = itemIn.readLine();
    
    //Book reader
    if (type.equalsIgnoreCase(Item.BOOK)) {
     //Parse object parameters
     String title = itemIn.readLine();
     long id = Long.parseLong(itemIn.readLine());
     boolean isOut = Boolean.parseBoolean(itemIn.readLine());
 
     String lineOfDate = itemIn.readLine();
     int [] date = toIntFromString(lineOfDate.split("/"));
     Date dayBorrowed = new Date(date[0],date[1],date[2],cal);
     String author = itemIn.readLine();
     int pages = Integer.parseInt(itemIn.readLine());
     double deweyDecNum = Double.parseDouble(itemIn.readLine());
     
     //Construct Book
     items[i] = new Book(id, isOut, title, dayBorrowed, author, pages, deweyDecNum, deweySystem);
    //Movie reader
    } else if (type.equalsIgnoreCase(Item.MOVIE)) {
     //Parse object parameters
               String title = itemIn.readLine();
     long id = Long.parseLong(itemIn.readLine());
     boolean isOut = Boolean.parseBoolean(itemIn.readLine());
               String lineOfDate = itemIn.readLine();
     int [] date = toIntFromString(lineOfDate.split("/"));
     Date dayBorrowed = new Date(date[0],date[1],date[2],cal);
     String director = itemIn.readLine();
     String genre = itemIn.readLine();
     int length = Integer.parseInt(itemIn.readLine());
     int ageRating = Integer.parseInt(itemIn.readLine());
     
     //Construct Movie
     items[i] = new Movie(id, isOut, title, dayBorrowed, director, genre, length, ageRating);
    //VideoGame reader
    } else if (type.equalsIgnoreCase(Item.VIDEO_GAME)){
     //Parse object parameters
     String title = itemIn.readLine();
     long id = Long.parseLong(itemIn.readLine());
     boolean isOut = Boolean.parseBoolean(itemIn.readLine());
     String lineOfDate = itemIn.readLine();
     int [] date = toIntFromString(lineOfDate.split("/"));
     Date dayBorrowed = new Date(date[0],date[1],date[2],cal);
     String developer =  itemIn.readLine();
     String genre = itemIn.readLine();
     int ageRating = Integer.parseInt(itemIn.readLine()); 
     
     //Construct VideoGame
     items[i] =  new VideoGame(id,isOut,title,dayBorrowed,developer,genre,ageRating);
    } //if structure
   } //for loop
         itemIn.close();
  } catch (Exception e) {
   System.err.println("There was a problem with the item file.\t" + e.getMessage());
  } //try-catch structure
  
  //User file reader
  try {
   BufferedReader userIn = new BufferedReader(new FileReader(USER_FILE));
   int userAmount = Integer.parseInt(userIn.readLine());
   users = new User[userAmount];
   
   for (int i = 0; i < userAmount; i++) {
    //Parse object parameters
    userIn.readLine();
    String name = userIn.readLine();
    long id = Long.parseLong(userIn.readLine());
    int age = Integer.parseInt(userIn.readLine());
    double amountOwed = Double.parseDouble(userIn.readLine());
    String unparsedItemIds = userIn.readLine();
    long[] itemIds = toLongFromString(unparsedItemIds.split(","));
    Item[] itemList =  new Item[itemIds.length];
    
    //Check if item exists
    for (int j = 0; j < itemIds.length; j++) {
     Item foundItem  = Item.searchById(items,itemIds[j]);
     int itemCount = 0;
     
     //Insert item into user inventory
     if (foundItem != null) {
      itemList[itemCount] = foundItem;
      itemCount++;
     } //if structure
    } //for loop
   } //for loop
         userIn.close();
  } catch (Exception e) {
   System.err.println("There was a problem with the user file.\t" + e.getMessage());
  } //try-catch structure
  
  //UserHolder file reader
  try {
   BufferedReader holderIn = new BufferedReader(new FileReader(USER_HOLDER_FILE));
   //Number of entries in the file
   int userHolderAmount = Integer.parseInt(holderIn.readLine());
   
   computerList = new Computer[userHolderAmount];
   roomList = new Room[userHolderAmount];
   
   for (int i = 0; i < userHolderAmount; i++) {
    String type = holderIn.readLine();
    int computerCount = 0;
    int roomCount = 0;
    
    //Determine UserHolder type
    if (type.equalsIgnoreCase(UserHolder.ROOM)) {
     //Parse object parameters
     long id = Long.parseLong(holderIn.readLine());
     int maxUser =  Integer.parseInt(holderIn.readLine());
     String lineOfUser = holderIn.readLine();
     long[] userId = toLongFromString(lineOfUser.split(","));
     User[] arrayOfUser = new User[maxUser];
     
     //Check if user exists
     for(int u = 0; u < maxUser; u++){
      User found = User.searchById(users,userId[u]);
      
      if (found != null){
       arrayOfUser[roomCount] = found;
      } //if structure
     } //for loop
     
     roomList[roomCount] =  new Room(id,maxUser);
     
     //Copy found users into array
     for(int u = 0; u < arrayOfUser.length; u++){
      roomList[roomCount].addUser(arrayOfUser[u]);
     } //for loop
     
     roomCount++;
    } else if (type.equalsIgnoreCase(UserHolder.COMPUTER)) {
     //Parse object parameters
     long id = Long.parseLong(holderIn.readLine());
     boolean occupied = Boolean.parseBoolean(holderIn.readLine());
     long userId =  Long.parseLong(holderIn.readLine());
     User foundUser = User.searchById(users, userId);
     String lineOfPrinters = holderIn.readLine();
     long[] printerId = toLongFromString(lineOfPrinters.split(","));
     Printer[] printers = new Printer[printerId.length];
     
     //Construct Computer
     computerList[computerCount] = new Computer(id, occupied);
     
     //Add user to Computer
     if (occupied == true) {
      computerList[computerCount].addUser(foundUser);
     } //if structure
     
     //Add printers to Computer
     for (int p = 0; p < printerId.length;p++) {
      Printer foundPrinter = Printer.searchById(printers,printerId[p]);
      
      if (foundPrinter != null) {
       computerList[computerCount].addPrinter(foundPrinter);
      } //if structure
      
      computerCount++;
     } //for loop
    } //if structure
   } //for loop
         holderIn.close();
  } catch (Exception e) {
   System.err.println("There was a problem with the UserHolder file.\t" + e.getMessage());
  } //try-catch structure
  
  //Printer file reader
  try {   
   BufferedReader printerIn = new BufferedReader(new FileReader(PRINTER_FILE));
   int printerAmount = Integer.parseInt(printerIn.readLine());
   printers = new Printer[printerAmount];
   
   for(int i = 0;i<printerAmount;i++){
    long id = Long.parseLong(printerIn.readLine());
    int max = Integer.parseInt(printerIn.readLine());
    int num = Integer.parseInt(printerIn.readLine());
    printers [i] = new Printer(id,max,num);
   } //for loop
         printerIn.close();
  } catch (Exception e) {
   System.err.println("There was a problem with the printer file.\t" + e.getMessage());
  } //try-catch structure
       
 } //Library constructor - loading from file
 
 //New Library constructor
 public Library (String name) {
  this.name = name;
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
 
 private long genUserId () {
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
 } //genUserId method
 
 private long genItemId () {
  long id;
  boolean isUnused;
  
  do {
   id = (long)(Math.floor(Math.random() * (1 + MAX_ID)));
   
   isUnused = true;
   
   for (int i = 0; i < items.length && isUnused; i++) {
    if (items[i].getId() == id) {
     isUnused = false;
    } //if structure
   } //for loop
  } while (!isUnused);
  
  return id;
 } //genItemId method
 
 //Add new user
 public void addUser (String name, int age) {
  User[] temp = new User[users.length + 1];
  
  //Copy users to larger array
  for (int i = 0; i < users.length; i++) {
   temp[i] = users[i];
  } //for loop
  
  //Insert new user
  temp[users.length] = new User(name, genUserId(), age);
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
 
 //Add new item
 public void addItem (String type, String title, Object[] objectParameters) throws Exception {
  Item newItem;
  
  //Create new item
  if (title.equalsIgnoreCase(Item.BOOK)) {
   newItem = new Book(
    genItemId(),                        //Item ID
    false,                              //Whether the Item is out
    title,                              //Title of Book
    null,                               //Date Book was borrowed
    (String)objectParameters[0],        //Name of author
    (int)objectParameters[1],           //Number of pages
    (double)objectParameters[2],        //Dewey Decimal number
    deweySystem);          //The Library's Dewey Decimal system
  } else if (title.equalsIgnoreCase(Item.VIDEO_GAME)) {
   newItem = new VideoGame(genItemId(),   //Item ID
    false,          //Whether the Item is out
    title,                              //Title of VideoGame
    null,                               //Date VideoGame was borrowed
    (String)objectParameters[0],        //Name of developer
    (String)objectParameters[1],        //Genre
    (int)objectParameters[2]);          //Age rating
  } else if (title.equalsIgnoreCase(Item.MOVIE)) {
   newItem = new Movie(genItemId(),       //Item ID
    false,                              //Whether the Item is out
    title,                              //Title of Movie
    null,                               //Date VideoGame was borrowed
    (String)objectParameters[0],        //Name of director
    (String)objectParameters[1],        //Genre
    (int)objectParameters[2],           //Length of Movie in minutes
    (int)objectParameters[3]);          //Age rating
  } else {
   throw new Exception("Invalid item type: " + type);
  } //if structure
  
  //Copy items to larger array
  Item[] temp = new Item[items.length + 1];
  
  for (int i = 0; i < items.length; i++) {
   temp[i] = items[i];
  } //for loop
  
  temp[items.length] = newItem;
  items = temp;
 } //addUser method
 
 public boolean remItem (long id) {
  Item.sortById(items);
  
  int index = Item.indexOfId(items, id);
  
  if (index != -1) {
   Item[] temp = new Item[items.length - 1];
   int curIndex = 0;
   
   for (int i = 0; i < items.length; i++) {
    if (i != index) {
     temp[curIndex] = items[i];
     curIndex++;
    } //if structure
   } //for loop
   
   items = temp;
  } //if structure
  
  return index != -1;
 } //remItem method
 
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
 
 //finds items that are overdue by the given Date object
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
 
 //saves all items to different files.
 public void writeToFile () {
  //Printer file writer
  try {
   BufferedWriter out = new BufferedWriter (new FileWriter (PRINTER_FILE, true));
   
   out.write("" + printers.length);
   
   for (int i = 0; i < printers.length; i ++) {
    out.newLine();
    out.write("" + printers[i].id);  
    out.newLine();
    out.write("" + printers[i].maxPaper);
    out.newLine();
    out.write("" + printers[i].numPaper);
    out.newLine();
    out.newLine();
   } //for loop
   
   out.close();
  } catch (IOException e) {
   System.err.println("There was a problem with the printers file.\t" + e.getMessage());
  } //try-catch structure
  
  //Item file writer
  try {
   BufferedWriter itemOut = new BufferedWriter (new FileWriter (ITEM_FILE, true));
   
   itemOut.write("" + items.length);
   
   for (int i = 0; i < items.length; i ++){
    itemOut.newLine();
       
    //Book writer
    if (items[i] instanceof Book){
     itemOut.write(Item.BOOK);
     itemOut.newLine();
     itemOut.write(items[i].getTitle());
     itemOut.newLine();
               itemOut.write("" + items[i].getId());
     itemOut.newLine();
               if (items[i].getIsOut()) {
      itemOut.write("true");
     } else {
      itemOut.write("false");
     } //if structure
     
     itemOut.newLine();
     itemOut.write(items[i].getDayBorrowed().getDay() + "/");
     itemOut.write(items[i].getDayBorrowed().getMonth() + "/");
     itemOut.write(items[i].getDayBorrowed().getYear() + "");
     itemOut.newLine();
     itemOut.write(((Book)(items[i])).getAuthor());
     itemOut.newLine();
     itemOut.write(((Book)(items[i])).getPages() + "");
     itemOut.newLine();
     itemOut.write(((Book)(items[i])).getDeweyDecNum() + "");
     itemOut.newLine();
    //Movie writer
    } else if (items[i] instanceof Movie) {
     itemOut.write(Item.MOVIE);
     itemOut.newLine();
               itemOut.write(items[i].getTitle());
     itemOut.newLine();

     itemOut.write("" + items[i].getId());
     itemOut.newLine();
     
     if (items[i].getIsOut()) {
      itemOut.write("true");
     } else {
      itemOut.write("false");
     } //if structure
     
     itemOut.newLine();
     itemOut.write(items[i].getDayBorrowed().getDay() + "/");
     itemOut.write(items[i].getDayBorrowed().getMonth() + "/");
     itemOut.write(items[i].getDayBorrowed().getYear() + "");
     itemOut.newLine();
     itemOut.write(((Movie)(items[i])).getDirector());
     itemOut.newLine();
     itemOut.write(((Movie)(items[i])).getGenre());
     itemOut.newLine();
     itemOut.write(((Movie)(items[i])).getLength()+"");
     itemOut.newLine();
     itemOut.write(((Movie)(items[i])).getAgeRating()+"");
     itemOut.newLine();
    //VideoGame writer
    } else if (items[i] instanceof VideoGame) {
     itemOut.write(Item.VIDEO_GAME);
     itemOut.newLine();
               itemOut.write(items[i].getTitle());
     itemOut.newLine();
               itemOut.write(items[i].getId()+"");
     itemOut.newLine();
     
     if (items[i].getIsOut()) {
      itemOut.write("true");
     } else {
      itemOut.write("false");
     } //if structure
     itemOut.newLine();
     itemOut.write(items[i].getDayBorrowed().getDay() + "/");
     itemOut.write(items[i].getDayBorrowed().getMonth() + "/");
     itemOut.write(items[i].getDayBorrowed().getYear() + "");
     itemOut.newLine();
     itemOut.write(((VideoGame)(items[i])).getDeveloper());
     itemOut.newLine();
     itemOut.write(((VideoGame)(items[i])).getGenre());
     itemOut.newLine();
     itemOut.write(((VideoGame)(items[i])).getAgeRating() + "");
     itemOut.newLine();
    } //if structure
   } //for loop
   
   itemOut.close();
  } catch (IOException e){
   System.err.println("There was a problem with the items file./t" + e.getMessage());
  } //try-catch structure
  
  //User file writer
  try {
   BufferedWriter out = new BufferedWriter (new FileWriter (USER_FILE, true));
   out.write(users.length + "");
   out.newLine();
   
   for (int i = 0; i < users.length; i ++){
    out.newLine();
    out.write(users[i].getName());
    out.newLine();
    out.write(users[i].getId() + "");
    out.newLine();
    out.write(users[i].getAge() + "");
    out.newLine();
    out.write(users[i].getAmountOwed() + "");
    out.newLine();
       
    boolean noMoreItems = false;
    
    //Write the items in the user's inventory
    for (int r = 0; r < users[i].getItems().length && !noMoreItems; r++){
     if (users[i].getItems()[r] != null) {
      out.write(users[i].getItems()[r].getId() + ",");
     } else {
      noMoreItems = true;
     } //if structure
     
     out.newLine();
    } //for loop
   } //for loop
   
   out.close();
  } catch (IOException e){
   System.out.println("There was a problem with the users file.\t" + e.getMessage());
  } //try-catch structure
  
  try {
   BufferedWriter out = new BufferedWriter (new FileWriter (USER_HOLDER_FILE, true));
   
   out.write((computerList.length + roomList.length) + "");
   
   boolean found = false;
   
   for (int i = 0; i < roomList.length; i ++){
    out.write(UserHolder.ROOM);
    out.newLine();
    out.write(roomList[i].getId() + "");
    out.newLine();
    out.write(roomList[i].getMaxUsers() + "");
    out.newLine();
    
    //Write the users in the room
    for (int r = 0; r < roomList[i].getUsers().length && !found; i ++){
     if (roomList[i].getUsers()[r] != null){
      out.write(roomList[i].getUsers()[r].getId() + ",");
      found = false;
     } else {
      found = true;
      out.newLine();
     } //if structure
     
     out.newLine();
    } //for loop
   } //for loop
    
   for (int i = 0; i < computerList.length; i ++){
    out.write(UserHolder.COMPUTER);
    out.newLine();
    out.write(computerList[i].id+"");
    out.newLine();
     
    if (computerList[i].isOccupied()) {
     out.write("true");
    } else {
     out.write("false");
    } //if structure
    
    out.newLine();
    
    for (int r = 0; r < computerList[i].getUsers().length && !found; r ++){
     if (computerList[i].getUsers()[r] != null) {
      out.write(computerList[i].getUsers()[r].getId() + "");
      found = false;
     } else {
      found = true;
      out.newLine();
     } //if structure
     
     out.newLine();
    } //for loop
    
    boolean printFound = false;
    
    for (int r = 0; r < computerList[i].getPrinters().length && !printFound; r ++){
     if (computerList[i].getUsers()[r] !=null){
      out.write(computerList[i].getPrinters()[r].getId() + "");
      printFound = false;
     } else {
      printFound = true;
      out.newLine();
     } //if structure
     
     out.newLine();
    } //for loop
   } //for loop
   
   out.close();
  } catch (IOException e) {
   System.err.println("The was a problem with the userHolders file.\t" + e.getMessage());
  } //try-catch structure
 } //writeToFile method
} //Library class