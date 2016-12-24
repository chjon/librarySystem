/*******************************************************************************
 * File Name:     Book.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/23
 * Description:   This class defines a book.
 *******************************************************************************/

import java.io.*;

public class Book {
	private static final String DEWEY_DEC_CLASSES = "Dewey Decimal Classes";
	private static final int DEWEY_DEC_GENRE_CODE_LENGTH = 3;
	
	private String title;
	private String author;
	private int pages;
	private double deweyDecNum;
	
	public Book (String title, String author, int pages, double deweyDecNum) {
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.deweyDecNum = deweyDecNum;
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
		String deweyDecClass = "" + (int)deweyDecNum;
		String genre = "";
		
		while (deweyDecClass.length() < DEWEY_DEC_GENRE_CODE_LENGTH) {
			deweyDecClass = "0" + deweyDecClass;
		} //while loop
	
		try {
			BufferedReader in = new BufferedReader(new FileReader(DEWEY_DEC_CLASSES + ".txt"));
			String input;
			
			while ((input = in.readLine()) != null) {
				if (input.startsWith(deweyDecClass)) {
					genre = input.substring(deweyDecClass.length() + 1);
				} //if structure
			} //while loop
			
			in.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} //try-catch structure
		
		if (genre.isEmpty()) {
			System.out.println("The genre could not be found.");
		} //if structure
		
		return genre;
	} //getGenre method
} //Book class