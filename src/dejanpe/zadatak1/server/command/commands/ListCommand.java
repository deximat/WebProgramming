package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.flight.Flight;
import dejanpe.zadatak1.server.core.flight.FlightDAO;

public class ListCommand extends AbstractCommand {

	private String flightId;

	public ListCommand(String flightId) {
		this.flightId = flightId;
	}
	
	@Override
	protected void executeCommand() {
		Flight flight = FlightDAO.get().getFlightById(this.flightId);
		if (flight != null) {
			this.result = flight.toString();
		} else {
			this.result = "Flight with given id is not found!";
		}
	}

	@Override
	public boolean shouldAutorize() {
		// TODO Auto-generated method stub
		return false;
	}

}
