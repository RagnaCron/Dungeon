package GameInterface;

import UserInterface.UserCommandLineInterface;

/**
 * The GamesInterface.Gamer Interface provides a single way for the Games to be started by the DungeonController.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Gamer {
	String playGame(UserCommandLineInterface userInterface);
}
