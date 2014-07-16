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
import java.util.Map;
import java.util.TreeMap;

public class UserDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2060867676962033377L;

	public static UserDAO get() {
		return INSTANCE;
	}

	private Map<String, User> registredUsers;

	private final static UserDAO INSTANCE = new UserDAO();

	private static final String USERS_PERSISTANCE_FILE = "users.txt";

	public UserDAO() {
		this.registredUsers = loadUsers();
	}

	public synchronized User findUserByUsername(final String username) {
		return this.registredUsers.get(username);
	}

	public Map<String, User> loadUsers() {
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(USERS_PERSISTANCE_FILE)));
			return (Map<String, User>) decoder.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TreeMap();
	}

	private void persist() {
		try {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(USERS_PERSISTANCE_FILE)));
			encoder.writeObject(this.registredUsers);
			encoder.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized boolean registerUser(final String username) {
		// check if it exists
		if (this.registredUsers.get(username) != null) {
			return false;
		}
		// add user to registred users
		this.registredUsers.put(username, new User(username));
		// persist to file
		persist();
		return true;
	}
}
