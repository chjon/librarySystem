abstract class UserHolder {
	protected long id;
	protected User [] users;
	protected int recentUser;

	public UserHolder (long id) {
		this.id = id;
	}

	abstract boolean isOccupied ();

	abstract String toString ();

	public boolean equals (UserHolder temp) {
		if (this.id == temp.getId()) {
			return true;
		}
		return false;
	}

	public long getId () {
		return id;
	}
        public boolean addUser(User temp){
		users [recentUser]  =  temp.getId();
		recentUser ++;
	}
	
}
