package Commander;

import Controller.Model.ControllerState;
import javafx.util.Pair;

import java.util.function.Supplier;

/**
 *
 * @param <R> What ever you ned to implement the Command Patter.
 */
public abstract class Command<S> {
	// TODO: DOCUMENT COMMAND PATTERN

	abstract protected Pair<ControllerState, Supplier<String>> getCommand(String command);
	abstract protected String executeCommand(Supplier<String> command);

}
