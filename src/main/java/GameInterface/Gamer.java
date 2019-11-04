package GameInterface;

import Commander.Command;
import Controller.Model.ControllerState;
import UserInterface.UserCommandLineInterface;
import javafx.util.Pair;

import java.util.function.Supplier;

/**
 * The abstract class Gamer provides a single way for the Games to be started by the DungeonController.
 * The Gamer extends the abstract Command class, witch is the basis for the Command Pattern.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Gamer {
	/**
	 * This is the soul Method to get a game command.
	 * @param command The command to find.
	 * @return Returns a controller state and a Method to be run be the Command Pattern.
	 */
	Pair<ControllerState, Supplier<String>> playGame(String command);
}
