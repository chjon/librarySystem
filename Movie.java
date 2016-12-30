/*******************************************************************************
 * File Name:     Movie.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/29
 * Description:   This class defines a movie.
 *******************************************************************************/

public class Movie extends Item {
	private static final String TYPE = "Movie";	//Item type
	private static final int MAX_DAYS_OUT = 14;	//Maximum number of days borrowed
	private String director;							//Name of director
	private String genre;								//Genre of movie
	private int length;									//Length of movie in minutes
	private int ageRating;								//Age rating of movie
	
	public Movie (long id, boolean isOut, String title, Date dayBorrowed,
		String director, String genre, int length, int ageRating) {
		super (id, isOut, title, dayBorrowed);
		this.director = director;
		this.length = length;
		this.genre = genre;
		this.ageRating = ageRating;
	} //Movie constructor
	
	public String getDirector () {
		return director;
	} //getDirector method
	
	public int getLength () {
		return length;
	} //getLength method
	
	public String getGenre () {
		return genre;
	} //getGenre method

	public int getAgeRating () {
		return ageRating;
	} //getAgeRating method
	
	public String getType () {
		return TYPE;
	} //getType method
	
	public int getMaxDaysOut () {
		return MAX_DAYS_OUT;
	} //daysOverdue method
	
	public String toString () {
		return super.toString() + "\n" +
			"Type: " + TYPE + "\n" +
			"Director: " + director + "\n" +
			"Length: " + length + " minutes\n" +
			"Genre: " + genre + "\n" +
			"Age Rating: " + ageRating;
	} //toString method
} //Movie class