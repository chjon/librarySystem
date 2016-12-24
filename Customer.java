import java.util.Scanner;
import java.io.*;

// Abstract class customer
abstract class Customer{
  protected String name; // customer name
  protected int customerNum; // customer id
  protected int curAmountOfItems; // current amount of items checked out
  protected int itemsLate; // amount of items overdue
  
  // Constructor
  public Customer(String givenName, givenNumber, givenItems, givenItemsLate){
    name = givenName;
    customerNum = givenNumber;
    curAmountOfItems = givenItems;
    itemsLate = givenItemsLate;
  }
  
  // Returns Customer info
  public String toString(){
    String s = "Name: " + name + "\nCustomer Number: " + customerNum + 
      "\nAmount of items checked out " + curAmountOfItems + "\nItems Late " +
      itemsLate;
    return s;
  }
  
  
}
