/*******************************************************************************
 * File Name:     Book.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/23
 * Description:   This class defines a book.
 *******************************************************************************/

public class Book extends Item {
	private String title;
	private String author;
	private int pages;
	private double deweyDecNum;
	private DeweyDecSystem deweySystem;
	
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
	
	//getGenre method
	public String getGenre () {
		return deweySystem.getGenre(deweyDecNum);
	} //getGenre method
} //Book class