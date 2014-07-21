package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.command.AbstractCommand;

public class TopSecretCommand extends AbstractCommand {

	@Override
	protected void executeCommand() {
		this.result = "You have seen the secret now, since you are logged in, MASTER!";
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
