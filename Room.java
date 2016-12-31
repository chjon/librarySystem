public class Room extends UserHolder {
	private int maxUser;

	public Room (long id, int maxUser) {
		super(id);
		this.maxUser = maxUser;
	}

	public String toString () {
		return "Max Users: "+maxUser;
	}

	public boolean isOccupied () {

	}

	
}
