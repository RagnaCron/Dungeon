package games.generic;

import userInterface.UserCommandLineInterface;

/**
 * The Gamer Interface provides a single way for the games to be started by the DungeonController.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Gamer {
	void playGame(UserCommandLineInterface userInterface);
}
