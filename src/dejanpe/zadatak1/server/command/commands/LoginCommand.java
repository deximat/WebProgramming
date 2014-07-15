package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.Worker;
import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.command.Command;
import dejanpe.zadatak1.server.core.user.User;
import dejanpe.zadatak1.server.core.user.UserDAO;

public class LoginCommand extends AbstractCommand implements Command {

	private String username;
	
	public LoginCommand(String username) {
		this.username = username;
	}
	
	@Override
	protected void executeCommand() {
		User userLoggedIn = UserDAO.get().findUserByUsername(this.username);
		if (userLoggedIn != null) {
			Worker.getCurrent().setUserInvoker(userLoggedIn);
			this.result = "User successfully logged in.";
		} else {
			this.result = "User doesn't exist!";
		}
	}

	@Override
	public boolean shouldAutorize() {
		// this is autorization handler it will create user
		return false;
	}

}
