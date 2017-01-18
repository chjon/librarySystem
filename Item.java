/*******************************************************************************
 * File Name:     Item.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines an item.
 *******************************************************************************/

abstract class Item {
	public final static double OVERDUE_PRICE = 0.25;				//Price per day overdue
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
	
	public abstract String getType();
	public abstract String getGenre();
	public abstract int getMaxDaysOut();
	
	public long getId () {
		return id;
	} //getId accessor
	
	public boolean getIsOut () {
		return isOut;
	} //isOut accesssor
	
	public String getTitle () {
		return title;
	} //title accessor
	
	public Date getDayBorrowed () {
		return dayBorrowed;
	} //dayBorrowed accessor
	
	public void setIsOut (boolean isOut) {
		this.isOut = isOut;
	} //isOut mutator
	
	public void setDayBorrowed (Date dayBorrowed) {
		this.dayBorrowed = dayBorrowed;
	} //dayBorrowed mutator
	
	//Checks the items amount of days overdue
	public int getDaysOverdue (Date curDate) {
		return dayBorrowed.compareTo(curDate) - getMaxDaysOut();
	} //getDaysOverdue method
	
	//Checks if the item is overdue
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
	
	//Sort an item array by ID
	public static void sortById (Item[] list) {
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
   } //sortById method
	
	//Search an Item array by ID
	public static Item searchById (Item[] items, long id) {
		int bottom = 0, top = items.length, middle;
		while (bottom <= top) {
			middle = (bottom + top) / 2;
			
			if (items[middle].getId() == id) {
				return items[middle];
			} else if (items[middle].getId() > id) {
				top = middle - 1;
			} else {
				bottom = middle + 1;
			} //if structure
		} //while loop
		
		return null;
	} //searchById method
	
	//Sort an item array by title alphabetically
	public static void sortByTitle (Item[] items) {
		Item temp;
		boolean swapped = true;
      
		for (int i = 0; i < items.length && swapped; i++) {
			swapped = false;
         
			for (int j = items.length - 1; j > i; j--) {
				if (items[j].getTitle().compareTo(items[j - 1].getTitle()) > 1) {
					temp = items[j];
					items[j] = items[j - 1];
					items[j - 1] = temp;
					swapped = true;
				} //if structure
			} //for loop
		} //for loop
	} //sortByTitle method
	
	//Sort an item array by genre alphabetically
	public static void sortByGenre (Item[] items) {
		Item temp;
		boolean swapped = true;
      
		for (int i = 0; i < items.length && swapped; i++) {
			swapped = false;
         
			for (int j = items.length - 1; j > i; j--) {
				if (items[j].getGenre().compareTo(items[j - 1].getGenre()) > 1) {
					temp = items[j];
					items[j] = items[j - 1];
					items[j - 1] = temp;
					swapped = true;
				} //if structure
			} //for loop
		} //for loop
	} //sortByGenre method
	
	//Search an item array by title alphabetically
	public static Item[] searchByTitle (Item[] items, String title) {
		int bottom = 0, top = items.length, middle;
		int foundIndex = -1;
      
		//find the index of a matching item
		while (bottom <= top && foundIndex == -1) {
			middle = (bottom + top) / 2;
			String itemTitle = items[middle].getTitle();
         
			if (itemTitle.equals(title)) {
				foundIndex = middle;
			} else if (itemTitle.compareTo(title) > 0) {
				top = middle - 1;
			} else {
				bottom = middle + 1;
			} //if structure
		} //while loop
      
		//Check if a match has been found
		if (foundIndex != -1) {
			int endIndex = foundIndex;
			
			//find first index of a matching item
			while (foundIndex > 0 && items[foundIndex - 1].equals(title)) {
				foundIndex--;
			} //while loop
			
			//find last index of a matching item
			while (endIndex < items.length - 1 && items[endIndex + 1].equals(title)) {
				endIndex++;
			} //while loop
			
			Item[] foundItems = new Item[endIndex - foundIndex + 1];
			
			//Copy all matching items
			for (int i = foundIndex; i <= endIndex; i++) {
				foundItems[i - foundIndex] = items[i];
			} //for loop
			
			return foundItems;
		} //if structure
		
		//return null if no match is found
		return null;
	} //searchByTitle method
	
	//Search an item array by genre alphabetically
	public static Item[] searchByGenre (Item[] items, String genre) {
		int bottom = 0, top = items.length, middle;
		int foundIndex = -1;
      
		//find the index of a matching item
		while (bottom <= top && foundIndex == -1) {
			middle = (bottom + top) / 2;
			String itemGenre = items[middle].getGenre();
         
			if (itemGenre.equals(genre)) {
				foundIndex = middle;
			} else if (itemGenre.compareTo(genre) > 0) {
				top = middle - 1;
			} else {
				bottom = middle + 1;
			} //if structure
		} //while loop
      
		//Check if a match has been found
		if (foundIndex != -1) {
			int endIndex = foundIndex;
			
			//find first index of a matching item
			while (foundIndex > 0 && items[foundIndex - 1].equals(genre)) {
				foundIndex--;
			} //while loop
			
			//find last index of a matching item
			while (endIndex < items.length - 1 && items[endIndex + 1].equals(genre)) {
				endIndex++;
			} //while loop
			
			Item[] foundItems = new Item[endIndex - foundIndex + 1];
			
			//Copy all matching items
			for (int i = foundIndex; i <= endIndex; i++) {
				foundItems[i - foundIndex] = items[i];
			} //for loop
			
			return foundItems;
		} //if structure
		
		//return null if no match is found
		return null;
	} //searchByGenre method
	
	//Search for a type of item
	public static Item[] searchByType (Item[] items, String type) {
		final String BOOK = "book";
		final String VIDEO_GAME = "video game";
		final String MOVIE = "movie";
		
		int foundCount = 0;
		
		//Find number of a type of item
		if (type.equalsIgnoreCase(BOOK)) {
			for (int i = 0; i < items.length; i++) {
				if (items[i] instanceof Book) {
					foundCount++;
				} //if structure
			} //for loop
		} else if (type.equalsIgnoreCase(VIDEO_GAME)) {
			for (int i = 0; i < items.length; i++) {
				if (items[i] instanceof VideoGame) {
					foundCount++;
				} //if structure
			} //for loop
		} else if (type.equalsIgnoreCase(MOVIE)) {
			for (int i = 0; i < items.length; i++) {
				if (items[i] instanceof Movie) {
					foundCount++;
				} //if structure
			} //for loop
		} //if structure
		
		Item[] foundItems = new Item[foundCount];
		foundCount = 0;
		
		//Copy items of a matching type
		if (type.equalsIgnoreCase(BOOK)) {
			for (int i = 0; i < items.length && foundCount < foundItems.length; i++) {
				if (items[i] instanceof Book) {
					foundItems[foundCount] = items[i];
					foundCount++;
				} //if structure
			} //for loop
		} else if (type.equalsIgnoreCase(VIDEO_GAME)) {
			for (int i = 0; i < items.length && foundCount < foundItems.length; i++) {
				if (items[i] instanceof VideoGame) {
					foundItems[foundCount] = items[i];
					foundCount++;
				} //if structure
			} //for loop
		} else if (type.equalsIgnoreCase(MOVIE)) {
			for (int i = 0; i < items.length && foundCount < foundItems.length; i++) {
				if (items[i] instanceof Movie) {
					foundItems[foundCount] = items[i];
					foundCount++;
				} //if structure
			} //for loop
		} //if structure
		
		return foundItems;
	} //searchByType
	
	
} //Item class