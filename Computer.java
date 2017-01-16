/*******************************************************************************
 * File Name:     Computer.java
 * Class:         ICS4U-01
 * Date:          2017/01/16
 * Description:   This class defines a Computer.
 *******************************************************************************/

class Computer extends UserHolder{
	protected boolean occupied;
  
	public Computer(long identification){
		super(identification);
		occupied = false;
	} //Computer constructor

	public String toString(){
		return "ID:" + id + "Currently Available:" + id;
	} //toString method
  
	public boolean isOccupied(){
		return occupied;
	} //isOccupied method
  
	public boolean remUser(){
		
	} //remUser method
  
	public void addPrinter(Printer other){
		
	} //addPrinter method
	
	public void remPrinter(long id){
		
	} //remPrinter method

	public boolean print(int amount,User account){
		
	} //print method
} //UserHolder class