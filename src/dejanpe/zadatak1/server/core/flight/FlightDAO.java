package dejanpe.zadatak1.server.core.flight;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import dejanpe.zadatak1.server.core.passenger.Passenger;

public class FlightDAO {
	
	private static final FlightDAO INSTANCE = new FlightDAO();
	
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

	public Flight getFlightById(String flightId) {
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

	public String cancel(String flightId, Passenger passenger) {
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
