package dejanpe.zadatak1.server.core.passenger;

import java.io.Serializable;

public class Passenger implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4100224177712466654L;
	private String JMBG;
	private String name;
	private String surname;

	public Passenger() {
		
	}
	
	public Passenger(final String JMBG, final String name, final String surname) {
		this.JMBG = JMBG;
		this.name = name;
		this.surname = surname;
	}

	public String getJMBG() {
		return this.JMBG;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setJMBG(final String jMBG) {
		this.JMBG = jMBG;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}
}
