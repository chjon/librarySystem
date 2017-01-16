public class Printer{
  protected final double PRICE_BW = 0.15:                   //price of black&white paper per sheet
  protected final double PRICE_COLOUR = 0.50;               //price of color paper per sheet
  protected long id;                                        //id of printer
  protected int maxPaper;                                   //max amount of paper(s) allowed in the printer
  protected int numPaper;                                   //amount of paper currently in the printer
  
  public Printer(long identification, int max, int num){
    id = identification;
    maxPaper = max;
    numPaper = num;
  }//Printer constructor
  
  public String toString(){
    return "ID: "+id + " Max Number of Paper(s): " +  maxPaper + "Current Number of Paper(s): " +numPaper;
  }//toString method
  
  public boolean equals(Printer other){
    if(id == other.id){
      return true;
    }
    return false;
  }//equals method
  
  public long getID(){
    return id;
  }//getID method
  
  public int getNumPaper(){
    return numPaper;
  }//getPaper method
  
  public boolean print(boolean color,int needed){  //Checks if printing is possible and adds to amount due
    if(numPaper - needed >= 0){
      return true;
    }
    return false;
  }//print method
  
  public boolean addPaper(int sheets){  //Adds sheets of paper to the printer
    if(maxPaper >= sheets + numPaper){
      numPaper = numPaper + sheets;
      return true;
    }
    else{
      numPaper = maxPaper;
      return false;
    }
  }//addPaper method
  
}//Printer class
