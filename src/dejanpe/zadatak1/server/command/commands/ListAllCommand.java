package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.flight.FlightDAO;

public class ListAllCommand extends AbstractCommand {

	@Override
	protected void executeCommand() {
		this.result = FlightDAO.get().getAllFlights().toString();
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
