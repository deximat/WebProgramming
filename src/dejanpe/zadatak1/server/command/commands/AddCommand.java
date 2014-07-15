package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.flight.Flight;
import dejanpe.zadatak1.server.core.flight.FlightDAO;

public class AddCommand extends AbstractCommand {

	private Flight flight;

	public AddCommand(final Flight flight) {
		this.flight = flight;
	}

	@Override
	protected void executeCommand() {
		if (FlightDAO.get().insert(this.flight)) {
			this.result = "Flight successfully added";
		} else {
			this.result = "Flight with given id already exists.";
		}

	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
