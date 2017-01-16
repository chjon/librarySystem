public class Library {
	private String name;
	private User[] users;
	private UserHolder[] userHolders;
	private Printer[] printers;
	private Item[] items;
	private Calendar cal;

	public Library (String name) {
		this.name = name;
	}

	public String getName () {
		return name;
	}

	public User[] getUsers () {
		return users;
	}

	public User getUserById (long id) {
		for (int i = 0; i < users.length; i++) {
			if (users[i].getId() == id) {
				return user[i];
			}
		}
	}

	public User getUserByName (String name) {
		for (int i = 0; i < users.length; i++) {
			if (users[i].getName().equals(name)) {
				return user[i];
			}
		}
	}

	public Room[] getRooms () {
		Room[] roomList;
		int roomListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Room) {
				roomListSize ++;
			}
		}
		roomList = new Room[roomListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Room) {
				roomList[i] = items[i];
			}
		}
		return roomList;
	}

	public Room getRoomById (long id) {
		Room[] rooms = getRooms();
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].getId() == id) {
				return rooms[i];
			}
		}
	}

	public Computer[] getComputers () {
		Computer[] computerList;
		int computerListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Computer) {
				computerListSize ++;
			}
		}
		computerList = new Computer[computerListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Computer) {
				computerList[i] = items[i];
			}
		}
		return computerList;
	}

	public Computer getComputerById (long id) {
		Computer[] computers = getComputers();
		for (int i = 0; i < computers.length; i++) {
			if (computers[i].getId() == id) {
				return computers[i];
			}
		}
	}

	public Printer[] getPrinters () {
		return printers;
	}

	public Printer getPrinterById (long id) {
		for (int i = 0; i < printers.length; i++) {
			if (printers[i].getId() == id) {
				return printers[i];
			}
		}
	}

	public Item[] getItems () {
		return items;
	}

	public Item getItemById (long id) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].getId() == id) {
				return items[i];
			}
		}
	} 

	public Item[] getItemsByTitle (String title) {
		Item[] temp;
		int tempSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i].getTitle().equals(title)) {
				tempSize ++;
			}
		}
		temp = new Item[tempSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i].getTitle().equals(title)) {
				temp[i] = items[i];
			}
		}

		return temp;
	}

	public Book[] getBooks () {
		Book[] bookList;
		int bookListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Book) {
				bookListSize ++;
			}
		}
		bookList = new Book[bookListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Book) {
				bookList[i] = items[i];
			}
		}
		return bookList;
	}

	public Book[] getBooksByAuthor (String author) {
		Book[] bookList;
		int bookListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Book && items[i].getAuthor().equals(author)) {
				bookListSize ++;
			}
		}
		bookList = new Book[bookListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Book && items[i].getAuthor().equals(author)) {
				bookList[i] = items[i];
			}
		}
		return bookList;
	}

	public VideoGame[] getVideoGames () {
		VideoGame[] gameList;
		int gameListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame) {
				gameListSize ++;
			}
		}
		gameList = new VideoGame[gameListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame) {
				gameList[i] = items[i];
			}
		}
		return gameList;
	}

	public VideoGame[] getVideosGameByDev (String dev) {
		VideoGame[] gameList;
		int gameListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && items[i].getPublisher().equals(dev)) {
				gameListSize ++;
			}
		}
		gameList = new VideoGame[gameListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && items[i].getPublisher().equals(dev)) {
				gameList[i] = items[i];
			}
		}
		return gameList;
	}

	public VideoGame[] getVideoGamesByGenre (String genre) {
		VideoGame[] gameList;
		int gameListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && items[i].getGenre().equals(genre)) {
				gameListSize ++;
			}
		}
		gameList = new VideoGame[gameListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && items[i].getGenre().equals(genre)) {
				gameList[i] = items[i];
			}
		}
		return gameList;
	}

	public VideoGame[] getVideoGamesByRating (String rating) {
		VideoGame[] gameList;
		int gameListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && items[i].getAgeRating().equals(rating)) {
				gameListSize ++;
			}
		}
		gameList = new VideoGame[gameListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof VideoGame && items[i].getAgeRating().equals(rating)) {
				gameList[i] = items[i];
			}
		}
		return gameList;
	}

	public Movie[] getMoviesByDirector (String director) {
		Movie[] movieList;
		int movieListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Movie && items[i].getDirector().equals(director)) {
				movieListSize ++;
			}
		}
		movieList = new Movie[movieListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Movie && items[i].getDirector().equals(director)) {
				movieList[i] = items[i];
			}
		}
		return movieList;
	}

	public Movie[] getMovies () {
		Movie[] movieList;
		int movieListSize = 0;

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Movie) {
				movieListSize ++;
			}
		}
		movieList = new Movie[movieListSize];

		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof Movie) {
				movieList[i] = items[i];
			}
		}
		return movieList;
	}

	public Item[] getOverdue (Date day) {

	}
	 public static User[] userSortByName (){
    		boolean sorted = false;
    		User temp = list[0];
    		for (int i = list.length-1; i >= 1 &&!sorted; i --){
      			sorted = true;
      			for (int j = 0; j <= i-1; j++){
        			if (list[j].name.compareTo(list[j+1].name) > 0){
          			sorted = false;
          			temp = list[j];
          			list[j] = list[j+1];
          			list[j+1] = temp;
       				}
       			}
    		}
    		return list;
  	}
	public static User[] userSortById(){
	    boolean sorted = false;
	    User temp = list[0];
	    for (int i = list.length-1; i >= 1 &&!sorted; i --){
	      sorted = true;
	      for (int j = 0; j <= i-1; j++){
		if (list[j].id < list[j+1].id)
		  sorted = false;
		  temp = list[j];
		  list[j] = list[j+1];
		  list[j+1] = temp;

		}

	      }
	    }
	    return list;
  }
}
