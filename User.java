class User{
  private String name;                                  //User name
  private long id;                                      //User id
  private int age;                                      //User age
  private double amountOwed;                            //Amount due by the user
  private final int MAX_ITEMS_OUT = 5;                  //Maximum amount of items allowed out at a single time
  private Item[] itemList = new Item[MAX_ITEMS_OUT];    //Array of items current signed out
  
  public User (String name, long id, int age){         
  	this.name = name;
   	this.id = id;
	this.age = age;
	amountOwed = 0;
  }//User constructor
  
  public void setAge(int age){
    	this.age = age;
  }//age mutatator
  
  public long getId(){
    	return id;
  }//id accessor 
  
  public int getAge(){
    	return age;
  }//age accessor
  
  public String getName(){
    	return name;
  }//name accessor
  
  public Item[] getItems(){
    	return itemList;
  }//item array accessor
  
  public double getAmountOwed(){
   	return amountOwed;
  }//amountOwed accessor
  
  public String toString(){
    	String s = name +"\n User number: " + id + "\nAge: " + age +
      		"\nAmount Owed: " + amountOwed+;
    	return s;
  }//toString method
  
  public boolean equalsId (User other){
  	if (id == other.id){
     	  	return true;
    	}
  	return false;
  }//equalsId method
  
  public boolean equalsName (User other){
    	if (name.equals(other.name)){
      		return true;
    	}  
    	return false;
  }//equalsName method
  
  public int currentItems(){
  	int count = 0;
	for (int i = 0; i < itemList.length; i ++){
      		if (itemList[i] != null){
        count ++;
      	}
    }
    return count;
  }//currentItems method
  
  public boolean canBorrow(Item libraryItem){
   	if (libraryItem.isOut()){
      		if (currentItems() < MAX_ITEMS_OUT){
        		return true;
      		}  
      	else{
        	return false;
      	}  
    	}
    return false;
  }//canBorrow method
  
  public boolean takeOutItem (Item checkOut){   //Adds item to the users item list
    	if (canBorrow(checkOut)){
      		itemList[currentItems()-1] = checkOut;
      	return true;
    	}
    return false;    
  }//takeOutItem method
  
  public void takeBack(){
    	int count = currentItems();
    	for (int i = 0; i < count; i ++){
      	itemList[i].isOut() = false;
      	itemList[i] = null;
    }
  }//takeBack method
  
  public void takeBack(Item returnItem){       //Takes back the item according to the provided item
    	returnItem.isOut = false;
    	int count = currentItems();
    	for (int i = 0; i < count; i ++){
      	if (itemList[i].id == returnItem.id)
        	itemList[i] = null;
    	}
  }//takeBack method
  
  public boolean payOverdue(){
    	for (int i = 0; i < itemList.length; i ++){  //Pay amount owed by the user
      		if (itemList[i].isOverdue(itemList[i]))
        	return false;
    	}
    	amountOwed = 0;
    	return true;
  }//payOverdue method
 
  public void setAmountOwed(double amount){       //Set amount owed by the user
    	amountOwed =  amountOwed + amount;
  }
}//setAmountOwed method
