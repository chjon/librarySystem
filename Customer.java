import java.util.Scanner;
import java.io.*;

// Abstract class customer
abstract class Customer{
  protected String name; // customer name
  protected int id; // customer id
  protected int curAmountOfItems; // current amount of items checked out
  protected int itemsLate; // amount of items overdue
  
  // Constructor
  public Customer(String givenName, givenNumber, givenItems, givenItemsLate){
    name = givenName;
    id = givenNumber;
    curAmountOfItems = givenItems;
    itemsLate = givenItemsLate;
  }
  
  // Returns Customer info
  public String toString(){
    String s = "Name: " + name + "\nCustomer Number: " + id + 
      "\nAmount of items checked out " + curAmountOfItems + "\nItems Late " +
      itemsLate;
    return s;
  }
  
  public int compareToCustomerName (Customer other){
    if (name.compareTo(other.name) > 0)
      return 1;
    else if (name.compareTo(other.name) < 0)
      return -1;
    else
      return 0;
  }
  public int compareToId (Customer other){
    if (this.id > other.id){
      return 1;
    }
    else if (other.id > this.id){
      return -1;
    }
    return 0;
  }
  public int compareToItemsLate (Customer other){
    if (this.itemsLate > other.itemsLate){
      return 1;
    }
    else if (other.itemsLate > this.itemsLate){
      return -1;
    }
    return 0;
  }
  
  public int compareToCurItems (Customer other){
    if (this.curAmountOfItmes > other.curAmountOfItems){
      return 1;
    }
    else if (other.curAmountOfItems > this.curAmountOfItems){
      return -1;
    }
    return 0;
  }
  
}
