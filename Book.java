/*******************************************************************************
 * File Name:     Book.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/26
 * Description:   This class defines a book.
 *******************************************************************************/

public class Book extends Item {
	private static final String TYPE = "Book";	//Item type
	private String title;								//Title of book
	private String author;								//Name of author
	private int pages;									//Number of pages
	private double deweyDecNum;						//Dewey Decimal number
	private DeweyDecSystem deweySystem;				//Dewey Decimal system
	
	public Book (long id, String status, double price,
		String title, String author, int pages, double deweyDecNum, DeweyDecSystem deweySystem) {
		super (id, status, price);
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.deweyDecNum = deweyDecNum;
		this.deweySystem = deweySystem;
	} //Book constructor
	
	public String getTitle () {
		return title;
	} //getTitle method
	
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
	
	public String toString () {
		return super.toString() + "\n" +
			"Type: " + TYPE + "\n" +
			"Title: " + title + "\n" +
			"Author: " + author + "\n" +
			"Pages: " + pages + "\n" +
			"Dewey Decimal number: " + deweyDecNum;
	} //toString method
} //Book class