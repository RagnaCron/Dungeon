package Controller.Model;

import Commander.Command;
import GameInterface.Gamer;
import Menace.MenaceGame;
import UserInterface.UserCommandLineInterface;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The abstract Controller Class is a basis for different kind of Controllers in the Dungeon Framework.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Controller extends Command<ControllerState, Supplier<String>> {

	// TODO: JAVADOC

	protected Map<String, Pair<ControllerState, Supplier<String>>> controllerCommands;
	protected Map<String, Pair<ControllerState, Supplier<String>>> helpCommands;
	protected Map<String, Function<String, UserCommandLineInterface, Gamer>> games;
	protected Gamer game;
	protected String playerName;
	protected ControllerState state;

	protected Controller() {
		controllerCommands = new HashMap<>();
		controllerCommands.put("help",  new Pair<>(ControllerState.HELP_STATE, this::help));
		controllerCommands.put("commands", new Pair<>(ControllerState.CHOOSING_STATE, this::commands));
		controllerCommands.put("exit", new Pair<>(ControllerState.CHOOSING_STATE, this::exit));
		controllerCommands.put("explore dungeon", new Pair<>(ControllerState.GAMING_STATE, this::playGame));

		helpCommands = new HashMap<>();
		helpCommands.put("quit", new Pair<>(ControllerState.CHOOSING_STATE, this::quit));
		helpCommands.put("menace description", new Pair<>(ControllerState.HELP_STATE, MenaceGame::GAME_DESCRIPTION));

		games = new HashMap<>();
	}

	/**
	 * This is the entry point to the Controller. It is the API to a Controller and
	 * should be used to start up all you need.
	 */
	abstract public void startController();

	/**
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
		return "Hello " + name + ", welcome to a Text base Dungeon Crawler Game.\n" +
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
	 *
	 * @return Returns a nice help description.
	 */
	protected String help() {
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

	protected String quit() {
		return "You have left the help section.";
	}

	/**
	 *
	 * @return Returns all Possible commands that a basic Controller should have.
	 */
	protected String commands() {
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
	protected String exit() {
		System.exit(0);
		return null;
	}

}
