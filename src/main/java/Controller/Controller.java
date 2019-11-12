package Controller;

import Commander.Command;
import Gamer.Gamer;
import Menace.MenaceGame;
import UserInterface.UserCommandLineInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The abstract Controller Class is a basis for different kind of Controllers in the Dungeon Framework.
 * It extends the abstract Command class with the Type Supplier with the Type String.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Controller extends Command<Supplier<String>> {

	protected Map<String, Function<String, UserCommandLineInterface, Gamer>> games;
	protected String playerName;
	protected ControllerState state;

	/**
	 * Init the Controller. Calls Super() first and then loads the controllerCommands and the helpCommands,
	 * after that it inits games HashMap. and sets the ControllerState to PLAY_STATE.
	 */
	protected Controller() {
		super();

		controllerCommands.put("help",  this::help);
		controllerCommands.put("commands", this::commands);
		controllerCommands.put("exit", this::exit);
		controllerCommands.put("explore dungeon", this::playGame);

		helpCommands.put("quit", this::quit);
		helpCommands.put("menace description", MenaceGame::GAME_DESCRIPTION);

		games = new HashMap<>();
		state = ControllerState.PLAY_STATE;
	}

	/**
	 * This is the entry point to the Controller. It is the API to a Controller and
	 * should be used to start up all you need.
	 */
	abstract public void startController();

	/**
	 * This Method is here to start up a game the Player chooses from a list of games.
	 *
	 * @return Returns a String of what ever you see fit.
	 */
	abstract protected String playGame();

	/**
	 * Lets give out a nice Greeting to the Player.
	 *
	 * @param name The name of the Player.
	 * @return A nice intro to the Player and the to commands 'help' and 'commands'.
	 */
	protected String helloGamer(String name) {
		return "Hello " + name + ", welcome to a Text base Dungeon Crawler Gamer.\n" +
				"Enter the command 'help' to see some basic infos.\n" +
				name + " you are at the beginning of you journey enter 'commands' to see\n" +
				"what is possible at the Dungeon Portal.";
	}

	/**
	 * Make it clear that the user knows if a command is not valid.
	 *
	 * @param command The User input.
	 * @return Then nice and obvious "there is no command..."
	 */
	protected String wrongInput(String command) {
		return "Sorry there is no command named '" +
				command + "'.";
	}

	/**
	 * We want the User to know how to  use a command in this Controller.
	 * It sets the state of the Controller to HELP_STATE.
	 *
	 * @return Returns a nice help description.
	 */
	private String help() {
		state = ControllerState.HELP_STATE;
		return "This is the help text...Enter 'help' for this text.\n" +
				"Most commands that can be run have a simple syntax, \n" +
				"'<command name>', exchange  everything between the <> with a given command.\n" +
				"For example 'commands'. Enter 'explore dungeon' to play a game.\n" +
				"You will then have to chose from a list of games.\n" +
				"Note: While you are playing a game you can not use any of the commands that are\n" +
				"listed under 'commands'.\n" +
				"Enter 'quit' to leave the help section.\n" +
				"'menace description' - tells you all you ned to know about the Menace Dungeon";
	}

	/**
	 * A Method that changes the State of the controller back to PLAY_STATE and
	 * then Returns a String Message.
	 * @return A nice Message for the user so he knows that he left the help section.
	 */
	private String quit() {
		state = ControllerState.PLAY_STATE;
		return "You have left the help section.";
	}

	/**
	 * With this the User gets a nice overview what kind of simple commands the
	 * controller is capable of.
	 *
	 * @return Returns all Possible commands that a basic Controller should have.
	 */
	private String commands() {
		return "The Dungeon Portal has the following commands:\n" +
				"'help' - gives you a nice general help to the dungeon portal\n" +
				"'commands' - gives you this out what you are reading right know\n" +
				"'explore dungeon' - displays you a list of all possible games to play\n" +
				"'exit' - stops the game";
	}


	/**
	 * Calling this Functions kills the Program with exit code 0.
	 *
	 * @return String, but gives null back. This is done due to the command Pattern.
	 */
	private String exit() {
		System.exit(0);
		return null;
	}

}
