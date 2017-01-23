/*******************************************************************************
 * File Name:     Book.java
 * Class:         ICS4U-01
 * Description:   This class defines a book.
 *******************************************************************************/

package librarySystem.items;

public class Book extends Item {
	private static final String TYPE = "Book";				//Item type
	private static final int MAX_DAYS_OUT = 21;				//Maximum number of days borrowed
	private String author;											//Name of author
	private int pages;												//Number of pages
	private double deweyDecNum;									//Dewey Decimal number
	private DeweyDecSystem deweySystem;							//Dewey Decimal system
	
	//constructor for Book
	public Book (long id, boolean isOut, String title, Date dayBorrowed,
		String author, int pages, double deweyDecNum, DeweyDecSystem deweySystem) {
		super (id, isOut, title, dayBorrowed);
		this.author = author;
		this.pages = pages;
		this.deweyDecNum = deweyDecNum;
		this.deweySystem = deweySystem;
	} //Book constructor
	
	public String getAuthor () {
		return author;
	} //getAuthor method
	
	public int getPages () {
		return pages;
	} //getPages method
	
	public double getDeweyDecNum () {
		return deweyDecNum;
	} //getDeweyDecNum method
	
	public String getGenre () {
		return deweySystem.getGenre(deweyDecNum);
	} //getGenre method
	
	public String getType () {
		return TYPE;
	} //getType method
	
	public int getMaxDaysOut () {
		return MAX_DAYS_OUT;
	} //daysOverdue method
	
	public String toString () {
		return super.toString() + "\n" +
			"Type: " + TYPE + "\n" +
			"Author: " + author + "\n" +
			"Pages: " + pages + "\n" +
			"Genre: " + getGenre() + "\n" +
			"Dewey Decimal number: " + deweyDecNum;
	} //toString method
} //Book class