public class Library {
	private String name;
	private User[] users;
	private Room[] rooms;
	private Computer[] computers;
	private Printer[] printers;
	private Item[] items;

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
		return rooms;
	}

	public Room getRoomById (long id) {
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].getId() == id) {
				return rooms[i];
			}
		}
	}

	public Computer[] getComputers () {
		return computers;
	}

	public Computer getComputerById (long id) {
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
}