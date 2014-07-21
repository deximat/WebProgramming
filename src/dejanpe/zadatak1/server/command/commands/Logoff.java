package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.Worker;
import dejanpe.zadatak1.server.command.AbstractCommand;

public class Logoff extends AbstractCommand {

	@Override
	protected void executeCommand() {
		Worker.getCurrent().setUserInvoker(null);
		this.result = "Succesfully logged out.";
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}
}
