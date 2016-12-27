/*******************************************************************************
 * File Name:     Movie.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/26
 * Description:   This class defines a movie.
 *******************************************************************************/

public class Movie extends Item {
	private static final String TYPE = "Movie";	//Item type
	private String title;								//Title of movie
	private String director;							//Name of director
	private int length;									//Length of movie in minutes
	private String genre;								//Genre of movie
	private String ageRating;							//Age rating of movie
	
	public Movie (long id, String status, double price,
		String title, String director, int length, String genre, String ageRating) {
		super (id, status, price);
		this.title = title;
		this.director = director;
		this.length = length;
		this.genre = genre;
		this.ageRating = ageRating;
	} //Movie constructor
	
	public String getTitle () {
		return title;
	} //getTitle method
	
	public String getDirector () {
		return director;
	} //getDirector method
	
	public int getLength () {
		return length;
	} //getLength method
	
	public String getGenre () {
		return genre;
	} //getGenre method

	public String getAgeRating () {
		return ageRating;
	} //getAgeRating method
	
	public String toString () {
		return super.toString() + "\n" +
			"Type: " + TYPE + "\n" +
			"Title: " + title + "\n" +
			"Director: " + director + "\n" +
			"Length: " + length + " minutes\n" +
			"Genre: " + genre + "\n" +
			"Age Rating: " + ageRating;
	} //toString method
} //Movie class