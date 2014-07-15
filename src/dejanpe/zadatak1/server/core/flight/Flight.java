package dejanpe.zadatak1.server.core.flight;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import dejanpe.zadatak1.server.core.passenger.Passenger;

public class Flight implements Serializable {
	private String flightId;
	private String departureTime;
	private String arrivalTime;
	private String source;
	private String destination;
	private int numberOfPassingers;

	private int numberOfReservations;
	private Map<String, Passenger> passengers = new HashMap<>();

	public Flight(final String flightId, final String departureTime, final String arrivalTime, final String source,
			final String destination, final int numberOfPassingers) {
		this.flightId = flightId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.source = source;
		this.destination = destination;
		this.numberOfPassingers = numberOfPassingers;
	}

	public boolean cancel(final Passenger passenger) {
		if (this.passengers.get(passenger.getJMBG()) != null) {
			// it exists so
			this.passengers.remove(passenger.getJMBG());
			this.numberOfReservations--;
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Flight)) {
			return false;
		}
		Flight f = (Flight) obj;
		return this.flightId.equals(f.flightId);
	}

	public String getFlightId() {
		return this.flightId;
	}

	@Override
	public int hashCode() {
		return this.flightId.hashCode();
	}

	public boolean reserve(final Passenger passenger) {
		int newNumberOfReservations = this.numberOfReservations + 1;
		if (newNumberOfReservations <= this.numberOfPassingers) {
			// success
			this.numberOfReservations = newNumberOfReservations;
			this.passengers.put(passenger.getJMBG(), passenger);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Flight(" + this.flightId + " from " + this.source + " to " + this.destination + " with capacity of "
				+ this.numberOfPassingers + " there is still " + (this.numberOfPassingers - this.numberOfReservations) + " of reservations available )";
	}
}
