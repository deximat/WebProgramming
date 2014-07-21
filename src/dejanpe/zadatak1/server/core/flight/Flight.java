package dejanpe.zadatak1.server.core.flight;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dejanpe.zadatak1.server.core.passenger.Passenger;

public class Flight implements Serializable {

	private static final long serialVersionUID = 4181421455147017958L;
	private String flightId;
	private Date departureTime;
	private Date arrivalTime;
	private String source;
	private String destination;
	private int numberOfPassingers;

	private int numberOfReservations;
	private Map<String, Passenger> passengers = new HashMap<>();

	public Flight() {

	}

	public Flight(final String flightId, final Date departureTime, final Date arrivalTime, final String source,
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

	public Date getArrivalTime() {
		return this.arrivalTime;
	}

	public Date getDepartureTime() {
		return this.departureTime;
	}

	public String getDestination() {
		return this.destination;
	}

	public String getFlightId() {
		return this.flightId;
	}

	public int getNumberOfPassingers() {
		return this.numberOfPassingers;
	}

	public int getNumberOfReservations() {
		return this.numberOfReservations;
	}

	public Map<String, Passenger> getPassengers() {
		return this.passengers;
	}

	public String getSource() {
		return this.source;
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

	public void setArrivalTime(final Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setDepartureTime(final Date departureTime) {
		this.departureTime = departureTime;
	}

	public void setDestination(final String destination) {
		this.destination = destination;
	}

	public void setFlightId(final String flightId) {
		this.flightId = flightId;
	}

	public void setNumberOfPassingers(final int numberOfPassingers) {
		this.numberOfPassingers = numberOfPassingers;
	}

	public void setNumberOfReservations(final int numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}

	public void setPassengers(final Map<String, Passenger> passengers) {
		this.passengers = passengers;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Flight(" + this.flightId + " from " + this.source + " to " + this.destination + " with capacity of "
				+ this.numberOfPassingers + " there is still " + (this.numberOfPassingers - this.numberOfReservations)
				+ " of reservations available )";
	}
}
