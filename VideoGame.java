/*******************************************************************************
 * File Name:     VideoGame.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/26
 * Description:   This class defines a video game.
 *******************************************************************************/

public class VideoGame extends Item {
	private static final String TYPE = "Video game";
	private String title;
	private String developer;
	private String genre;
	private String ageRating;
	
	public VideoGame (long id, String status, double price,
		String title, String developer, String genre, String ageRating) {
		super (id, status, price);
		this.title = title;
		this.developer = developer;
		this.genre = genre;
		this.ageRating = ageRating;
	} //VideoGame constructor
	
	public String getTitle () {
		return title;
	} //getTitle method
	
	public String getDeveloper () {
		return developer;
	} //getDeveloper method
	
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
			"Developer: " + developer + "\n" +
			"Genre: " + genre + "\n" +
			"Age Rating: " + ageRating;
	} //toString method
} //VideoGame class