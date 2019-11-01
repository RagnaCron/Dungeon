package Commander;

import java.util.Map;

/**
 *
 * @param <T> What ever you ned to implement the Command Patter.
 * @param <R> What ever you ned to implement the Command Patter.
 */
public abstract class Command<T, R> {
	// TODO: DOCUMENT COMMAND PATTERN

	protected Map<T, R> commands;

	abstract protected R getCommand(String Command);
	abstract protected String executeCommand(R command);
	abstract protected String runCommand(String command);

}
