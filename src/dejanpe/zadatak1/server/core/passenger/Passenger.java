package dejanpe.zadatak1.server.core.passenger;

import java.io.Serializable;

public class Passenger implements Serializable {
	private String JMBG;
	private String name;
	private String surname;
	
	public Passenger(final String JMBG, final String name, final String surname) {
		this.JMBG = JMBG; 
		this.name = name;
		this.surname = surname;
	}

	public String getJMBG() {
		return JMBG;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
}
