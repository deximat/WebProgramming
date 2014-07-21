package dejanpe.zadatak1.server.core.user;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1996045147220552274L;
	private String username;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(final String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}
