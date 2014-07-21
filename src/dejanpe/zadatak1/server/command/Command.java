package dejanpe.zadatak1.server.command;

import dejanpe.zadatak1.server.core.user.User;

public interface Command {
	void execute();

	String getResult();

	void setUserInvoker(User userInvoker);
}
