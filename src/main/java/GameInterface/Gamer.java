package GameInterface;

import Commander.Command;
import UserInterface.UserCommandLineInterface;

/**
 * The abstract class Gamer provides a single way for the Games to be started by the DungeonController.
 * The Gamer extends the abstract Command class, witch is the basis for the Command Pattern.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Gamer{
	public abstract String playGame(UserCommandLineInterface userInterface, String playerName);
}
