package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.flight.FlightDAO;
import dejanpe.zadatak1.server.core.passenger.Passenger;

public class ReserveCommand extends AbstractCommand {

	private String flightId;
	private Passenger passenger;

	public ReserveCommand(final String flightId, final Passenger passenger) {
		this.flightId = flightId;
		this.passenger = passenger;
	}

	@Override
	protected void executeCommand() {
		this.result =  FlightDAO.get().reserveFlight(this.flightId, this.passenger);
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
