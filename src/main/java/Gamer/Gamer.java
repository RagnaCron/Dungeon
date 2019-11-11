package Gamer;

import java.util.function.Supplier;

/**
 * The Gamer Interface provides a single way for the Games to be started by a Controller.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Gamer {

	/**
	 * This is the soul Method to start a game.
	 * @return Gives a nice Supplier with the return Type String back.
	 */
	Supplier<String> playGame();

}
