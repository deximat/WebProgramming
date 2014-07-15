package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.user.UserDAO;

public class RegisterCommand extends AbstractCommand {

	private final String username;

	public RegisterCommand(final String username) {
		this.username = username;
	}

	@Override
	public void executeCommand() {
		if (UserDAO.get().registerUser(this.username)) {
			this.result = "User succesfully registred!";
		} else {
			this.result = "User already exists!";
		}
	}

	@Override
	public boolean shouldAutorize() {
		// no autorization is not needed for registration
		return false;
	}
}
