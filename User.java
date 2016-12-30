class User{
  private String name;
  private long id;
  private int age;
  private double amountOwed;
  private final int MAX_ITEMS_OUT = 5;
  private Item[] itemList = new Item[MAX_ITEMS_OUT];
  
  public User (String name, long id, int age){
    this.name = name;
    this.id = id;
    this.age = age;
    amountOwed = 0;
  }
  public long getId(){
    return id;
  }
  public int getAge(){
    return age;
  }
  public String getName(){
    return name;
  }
  public Item[] getItems(){
    return itemList;
  }
  public double getAmountOverdue(){
    return amountOverdue;
  }
  
  public String toString(){
    String s = name +"\n User number: " + id + "\nAge: " + age +
      "\nAmount Owed: " + amountOwed+;
    return s;
  }
  
  public boolean equals (User other){
    if (id == other.id){
      return true;
    }
    
    return false;
  }
  public int currentItems(){
    int count = 0;
    for (int i = 0; i < itemList.length; i ++){
      if (itemList[i] != null){
        count ++;
      }
    }
    return count;
  }
  public boolean canBorrow(Item libraryItem){
    if (libraryItem.isOut){
      if (currentItems() < MAX_ITEMS_OUT)
        return true;
      else
        return false;
    }
    return false;
  }
  public boolean takeOutItem (Item checkOut){
    if (canBorrow(checkOut)){
      itemList[currentItems()-1] = checkOut;
      return true;
    }
    return false;
        
  }
  
  public void takeBack(){
    int count = currentItems();
    for (int i = 0; i < count; i ++){
      itemList[i].isOut = false;
      itemList[i] = null;
    }
  }
  public void takeBack(Item returnItem){
    returnItem.isOut = false;
    int count = currentItems();
    for (int i = 0; i < count; i ++){
      if (itemList[i].id == returnItem.id)
        itemList[i] = null;
    }
  }
  public boolean payOverdue(){
    for (int i = 0; i < itemList.length; i ++){
      if (itemList[i].isOverdue(itemList[i]))
        return false;
    }
    amountOverdue = 0;
    return true;
  }
 
  
  
  
}
