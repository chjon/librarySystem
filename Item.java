/*******************************************************************************
 * File Name:     Item.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/29
 * Description:   This class defines an item.
 *******************************************************************************/

abstract class Item {
	private long id;					//Item identification number
	private boolean isOut;			//Whether the item is out of the library or not
	private String title;			//Title of the item
	private Date dayBorrowed;		//Day item was borrowed
	
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
	
	public int getDaysOverdue (Date curDate) {
		return dayBorrowed.compareTo(curDate) - getMaxDaysOut();
	} //getDaysOverdue method
	
	public boolean isOverdue () {
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

	public Item[] sortItemsById (Item[] list){
	    for (int i = 0; i < list.length; i ++){
	      int j = i;
	      int itemId = list[j].id;
	      while (j>0 && itemId < list[j-1]){
		list[j] = list[j-1];
		j--;   
	      }
	      list[j] = itemId;

	    }
	    return list;
	}
	public Item[] sortItemsByTitle (Item[] list){
		for (int i = 0; i < list.length; i++){
			int j = i;
	      		String itemTitle = list[j].title;
	      		while (j > 0 && itemTitle.compareTo(list[j-1].title) < 0){
				list[j]= = list[j-1];
				j --;
	      		}
		   	list[j] = itemTitle;
		   }
	   return list;
	}
	
	
} //Item class
