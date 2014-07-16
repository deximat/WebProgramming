package dejanpe.zadatak1.server.core.user;

import java.io.Serializable;

public class User implements Serializable {
	
	private String username;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
