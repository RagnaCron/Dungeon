package games.generic;

import userInterface.UserCLIInterface;

/**
 * The Gamer Interface provides a single way for the games to be started by the DungeonController.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Gamer {
	void playGame(UserCLIInterface userInterface);
}
