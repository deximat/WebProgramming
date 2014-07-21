package dejanpe.zadatak1.server.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandBuilder {

	private static final Map<String, CommandType> COMMAND_TYPES;

	static {
		COMMAND_TYPES = new TreeMap<String, CommandType>();

		for (CommandType commandExecutor : CommandType.values()) {
			COMMAND_TYPES.put(commandExecutor.getIdentifier(), commandExecutor);
		}
	}

	public static Command build(final String command, final String[] params) {
		CommandType commandType = COMMAND_TYPES.get(command);
		if (commandType == null) {
			return null;
		} else {
			return commandType.buildCommand(params); 
		}
	}
}
