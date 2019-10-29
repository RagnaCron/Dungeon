package Games.Base;

import UserInterface.UserCommandLineInterface;

/**
 * The Gamer Interface provides a single way for the Games to be started by the DungeonController.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Gamer {
	void playGame(UserCommandLineInterface userInterface);
}
