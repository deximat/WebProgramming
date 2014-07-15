package dejanpe.zadatak1.server.core.user;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2060867676962033377L;

	public static UserDAO get() {
		return INSTANCE;
	}

	private List<User> registredUsers = new ArrayList<User>();

	private final static UserDAO INSTANCE = new UserDAO();

	private static final String USERS_PERSISTANCE_FILE = "users.txt";

	public UserDAO() {
		loadUsers();
	}

	public synchronized User findUserByUsername(final String username) {
		for (User user : this.registredUsers) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public ArrayList<User> loadUsers() {
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("USERS_PERSISTANCE_FILE")));
			return (ArrayList<User>) decoder.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList();
	}

	private void persist() {
		try {
			XMLEncoder encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream("USERS_PERSISTANCE_FILE")));
			encoder.writeObject(this.registredUsers);
			encoder.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized boolean registerUser(final String username) {
		// check if it exists
		// TODO: username is not type USER
		if (this.registredUsers.contains(username)) {
			return false;
		}
		// add user to registred users
		this.registredUsers.add(new User(username));
		// persist to file
		persist();
		return true;
	}
}
