public class Room extends UserHolder {
	private int maxUser;

 public Room (long id, int maxUser) {
  	super(id);
  	this.maxUser = maxUser;
 }//Room constructor

 public String toString () {
  	return "Max Users: "+maxUser;
 }//toString method

 public boolean isOccupied () {
  	if (users != null) {
   		return true;
  	}
  	return false;
 }//isOccupied method

 public boolean remUser (long) {
   
 }//remUser method
}//Room class
