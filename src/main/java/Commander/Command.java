package Commander;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
// * @param <T> Any Type that makes sens.

/**
 * The abstract class Command builds the basis for the Command Pattern.
 *
 * @param <R> Any Type that makes sens that you want to return.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Command<R> {
	// TODO: DOCUMENT COMMAND PATTERN
	// TODO: JAVADOC

	protected Map<String, R> controllerCommands; // TODO: DOCUMENT CHANGES
	protected Map<String, R> helpCommands;

	public Command() {
		controllerCommands = new HashMap<>();
		helpCommands = new HashMap<>();
	}

	/**
	 * With this Method we want to get a command out of an HashMap or
	 * what ever fits the needs.
	 *
	 * @param command The Command to look up, for example in a HashMap.
	 * @return A java Tuple
	 */
	abstract protected R getCommand(String command);

	/**
	 * With this Method we went to execute a given R Function.
	 *
	 * @param command Takes the R.
	 * @return Returns a String from the R Type execution.
	 */
	abstract protected String executeCommand(R command);

}
