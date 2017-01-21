/*******************************************************************************
 * File Name:     VideoGame.java
 * Class:         ICS4U-01
 * Description:   This class defines a video game.
 *******************************************************************************/

package librarySystem.items;

public class VideoGame extends Item {
	private static final String TYPE = "Video game";      		//Item type
	private static final int MAX_DAYS_OUT = 14;           		//Maximum number of days borrowed
	private String developer;                             		//Name of developer
	private String genre;                                 		//Genre of video game
	private int ageRating;                                		//Age rating of video game
	
	public VideoGame (long id, boolean isOut, String title, Date dayBorrowed,
		String developer, String genre, int ageRating) {
		super (id, isOut, title, dayBorrowed);
		this.developer = developer;
		this.genre = genre;
		this.ageRating = ageRating;
	} //VideoGame constructor
	
	public String getDeveloper () {
		return developer;
	} //getDeveloper method
	
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
			"Developer: " + developer + "\n" +
			"Genre: " + genre + "\n" +
			"Age Rating: " + ageRating;
	} //toString method
} //VideoGame class
