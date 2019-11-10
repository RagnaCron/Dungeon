package Gamer;

import java.util.function.Supplier;

/**
 * The abstract class Gamer provides a single way for the Games to be started by the DungeonController.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Gamer {

	/**
	 * This is the soul Method to start a game.
	 * @return Gives a nice message about how the game ended.
	 */
	Supplier<String> playGame();

}
