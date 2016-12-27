import java.util.Scanner;
import java.io.*;

// Abstract class customer
abstract class Person{
  protected String name; // customer name
  protected int id; // customer i
  
  // Constructor
  public Person(String givenName, givenNumber){
    name = givenName;
    id = givenNumber;
  }
  
  // Returns Customer info
  public String toString(){
    String s = "Name: " + name + "\nNumber: " + id;
    return s;
  }
  
  public int compareToPersonName (Person other){
    if (name.compareTo(other.name) > 0)
      return 1;
    else if (name.compareTo(other.name) < 0)
      return -1;
    else
      return 0;
  }
  public int compareToId (Person other){
    if (this.id > other.id){
      return 1;
    }
    else if (other.id > this.id){
      return -1;
    }
    return 0;
  }
  
}
