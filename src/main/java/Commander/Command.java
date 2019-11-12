package Commander;

import java.util.HashMap;
import java.util.Map;
// * @param <T> Any Type that makes sens.

/**
 * The abstract class Command builds the basis for the Command Pattern.
 *
 * @param <R> Any Type that makes sens that you want to return, wen you get a Value out of the HashMaps.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Command<R> {

	protected Map<String, R> controllerCommands;
	protected Map<String, R> helpCommands;

	/**
	 * Init the controllerCommands and the helpCommands.
	 */
	public Command() {
		controllerCommands = new HashMap<>();
		helpCommands = new HashMap<>();
	}

	/**
	 * With this Method we want to get a command out of an HashMap, you need
	 * to implement you own logic to handel to different HashMap access.
	 *
	 * @param command The Command to look up, for example in a HashMap.
	 * @return What ever R will be.
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
