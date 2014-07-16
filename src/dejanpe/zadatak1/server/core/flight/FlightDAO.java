package dejanpe.zadatak1.server.core.flight;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import dejanpe.zadatak1.server.core.passenger.Passenger;
import dejanpe.zadatak1.server.core.user.User;

public class FlightDAO {
	
	private static final String FLIGHTS_PERSISTANCE_FILE = "flights.txt";

	public TreeMap<String, Flight> loadFlights() {
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FLIGHTS_PERSISTANCE_FILE)));
			return (TreeMap<String, Flight>) decoder.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TreeMap<String, Flight>();
	}

	private void persist() {
		try {
			XMLEncoder encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream(FLIGHTS_PERSISTANCE_FILE)));
			encoder.writeObject(this.registredFlights);
			encoder.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static final FlightDAO INSTANCE = new FlightDAO();
	
	private FlightDAO() {
		this.registredFlights = loadFlights();
	}
	public static FlightDAO get() {
		return INSTANCE;
	}

	private Map<String, Flight> registredFlights = new TreeMap<String, Flight>() ;

	public synchronized boolean insert(Flight flight) {
		if (this.registredFlights.get(flight.getFlightId()) != null) {
			return false;
		}
		this.registredFlights.put(flight.getFlightId(), flight);
		return true;
	}
	
	public synchronized Collection<Flight> getAllFlights() {
		return this.registredFlights.values();
	}

	public synchronized Flight getFlightById(String flightId) {
		return this.registredFlights.get(flightId);
	}

	public synchronized String reserveFlight(String flightId, Passenger passenger) {
		Flight flight = this.registredFlights.get(flightId);
		if (flight == null) {
			return "Flight doesn't exist";
		}
		// now that we have valid flight try to reserve on flight
		if (flight.reserve(passenger)) {
			return "Succesfully reserved flight!";
		} else {
			return "Flight is full";
		}
	}

	public synchronized String cancel(String flightId, Passenger passenger) {
		Flight flight = getFlightById(flightId);
		if (flight == null) {
			return "Flight doesn't exist";
		}
		if (flight.cancel(passenger)) {
			return "Canceled reservation succesfully";
		} else {
			return "Passinger is not on this flight!";
		}
	}
	

}
