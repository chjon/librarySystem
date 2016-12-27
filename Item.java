/*******************************************************************************
 * File Name:     Item.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/24
 * Description:   This class defines an item.
 *******************************************************************************/

abstract class Item {
	private long id;
	private String status;
	private double price;

	public Item (long id, String status, double price) {
		this.id = id;
		this.status = status;
		this.price = price;
	} //Item constructor
	
	public long getId () {
		return id;
	} //getId method
	
	public String getStatus () {
		return status;
	} //getStatus method
	
	public double getPrice () {
		return price;
	} //getPrice method
	
	public String toString () {
		return
			"ID: " + id + "\n" +
			"Status: " + status + "\n" +
			"Price: " + price;
	} //toString method
} //Item class