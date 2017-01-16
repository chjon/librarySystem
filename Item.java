/*******************************************************************************
 * File Name:     Item.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines an item.
 *******************************************************************************/

abstract class Item {
	public final static double OVERDUE_PRICE = 0.25;	//Price per day overdue
	private long id;              							//Item identification number
	private boolean isOut;        							//Whether the item is out of the library or not
	private String title;         							//Title of the item
	private Date dayBorrowed;     							//Day item was borrowed
	
	public Item (long id, boolean isOut, String title, Date dayBorrowed) {
		this.id = id;
		this.isOut = isOut;
		this.title = title;
		this.dayBorrowed = dayBorrowed;
	} //Item constructor
	
	public abstract String getType ();
	public abstract int getMaxDaysOut ();
	
	public long getId () {
		return id;
	} //getId method
	
	public boolean isOut () {
		return isOut;
	} //isOut method
	
	public String getTitle () {
		return title;
	} //getTitle method
	
	public Date getDayBorrowed () {
		return dayBorrowed;
	} //getDayBorrowed method
	
	public void setIsOut (boolean isOut) {
		this.isOut = isOut;
	} //setIsOut method
	
	public void setDayBorrowed (Date dayBorrowed) {
		this.dayBorrowed = dayBorrowed;
	} //setDayBorrowed method
	
	public int getDaysOverdue (Date curDate) {
		return dayBorrowed.compareTo(curDate) - getMaxDaysOut();
	} //getDaysOverdue method
	
	public boolean isOverdue (Date curDate) {
		return dayBorrowed.compareTo(curDate) > getMaxDaysOut();
	} //isOverdue method
	
	public String toString () {
		String output =
			"ID: " + id + "\n" +
			"Status: ";
		
		if (isOut) {
			output += "Out";
		} else {
			output += "In";
		} //if structure
		
		output +=
			"\n" +
			"Title: " + title + "\n" +
			"Day borrowed: " + dayBorrowed;
			
		return output;
	} //toString method
	
	public boolean equals (Item other) {
		return
			this.getType().equals(other.getType()) &&
			this.id == other.id;
	} //equals method
	
	//Sort an item array by ID alphabetically
	public static void sortItemsById (Item[] list) {
      Item temp;
      boolean swapped = true;
      
      for (int i = 0; i < list.length && swapped; i++) {
         swapped = false;
         
         for (int j = list.length - 1; j > i; j--) {
            if (list[j].id < list[j - 1].id) {
               temp = list[j];
               list[j] = list[j - 1];
               list[j - 1] = temp;
               swapped = true;
            } //if structure
         } //for loop
      } //for loop
   } //sortItemsById method
	
	//Sort an item array by title alphabetically
	public static void sortItemsByTitle (Item[] list) {
      Item temp;
      boolean swapped = true;
      
      for (int i = 0; i < list.length && swapped; i++) {
         swapped = false;
         
         for (int j = list.length - 1; j > i; j--) {
            if (list[j].getTitle().compareTo(list[j - 1].getTitle()) > 1) {
               temp = list[j];
               list[j] = list[j - 1];
               list[j - 1] = temp;
               swapped = true;
            } //if structure
         } //for loop
      } //for loop
   } //sortItemsByTitle method
} //Item class