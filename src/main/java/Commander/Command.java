package Commander;

import Controller.Model.ControllerState;
import javafx.util.Pair;

import java.util.function.Supplier;

/**
 * The abstract class Command builds the basis for the Command Pattern.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Command<T, R> {
	// TODO: DOCUMENT COMMAND PATTERN

	/**
	 * With this Method we want to get a command out of an HashMap or
	 * what ever fits the needs.
	 *
	 * @param command The Command to look up, for example in a HashMap.
	 * @return A java Tuple.
	 */
	abstract protected Pair<T, R> getCommand(String command);

	/**
	 * With this Method we went to execute a given function.
	 *
	 * @param command Takes the function with no arguments and a String as Return value.
	 * @return Returns a String from the function execution.
	 */
	abstract protected String executeCommand(R command);

}
