package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.passenger.Passenger;
import dejanpe.zadatak1.server.core.passenger.PassengerDAO;

public class ListPassengerCommand extends AbstractCommand {

	private String JMBG;
	
	public ListPassengerCommand(String JMBG) {
		this.JMBG = JMBG;
	}
	@Override
	protected void executeCommand() {
		Passenger passenger = PassengerDAO.get().getPassengerByJMBG(this.JMBG);
		if (passenger != null) {
			this.result = passenger.toString();
		} else {
			this.result = "There is no such passenger!";
		}
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
