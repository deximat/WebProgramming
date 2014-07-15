package dejanpe.zadatak1.server.command;

import dejanpe.zadatak1.server.core.user.User;

public abstract class AbstractCommand implements Command {
	
	private User userInvoker;
	
	protected String result;
	
	public void execute() {
		if (!shouldAutorize() || this.userInvoker != null) {
			executeCommand();
		} else {
			this.result = "You must be logged in to run command: " + getClass().getSimpleName();
		}
	}

	protected abstract void executeCommand();
	
	@Override
	public String getResult() {
		return this.result;
	}
	
	public abstract boolean shouldAutorize();
	
	public void setUserInvoker(User userInvoker) {
		this.userInvoker = userInvoker;
	}
}
