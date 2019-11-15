package Menace;

import Controller.Controller;
import Controller.ControllerState;
import Controller.Function;
import Gamer.Gamer;
import UserInterface.UserCommandLineInterface;

import java.util.function.Supplier;


/**
 * The DungeonController describes a way to play multiple text based Dungeon-Crawler-Games.
 * After init, you should call startController.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class DungeonController extends Controller {

	private UserCommandLineInterface userInterface;

	/**
	 * The Constructor for the DungeonController initializes the DungeonController
	 * and creates a new instance of UserCommandLineInterface.
	 */
	public DungeonController() {
		super();

		userInterface = new UserCommandLineInterface();
		playerName = userInterface.getInput("Hello, enter your name> ");
		userInterface.println(helloGamer(playerName));

		games.put("menace", MenaceGame::new);
	}

	/**
	 * The start method should be called right after init of the DungeonController.
	 */
	@Override
	public void startController() {
		state = ControllerState.PLAY_STATE;
		//noinspection InfiniteLoopStatement
		while (true) {
			switch (state) {
				case PLAY_STATE:
					controlCommand("dungeon portal> ");
					break;
				case HELP_STATE:
					controlCommand("dungeon help section> ");
					break;
			}
		}
	}

	/**
	 * With this Method the intention is set, to getCommand and pass it to executeCommand,
	 * it then gives it out to the CommandLineInterface.
	 *
	 * @param prompt Where is the Player? So tell him.
	 */
	private void controlCommand(String prompt) {
		userInterface.println(executeCommand(getCommand(userInterface.getInput(prompt))));
	}

	/**
	 * Get a command out of controllerCommands or helpCommands depending on the state of the Controller.
	 * @param command The Command to look up.
	 * @return Gives a Supplier back with a return Type String.
	 */
	@Override
	protected Supplier<String> getCommand(String command) {
		Supplier<String> com = null;
		if (state == ControllerState.PLAY_STATE)
			com = controllerCommands.get(command);
		else if (state == ControllerState.HELP_STATE)
			com = helpCommands.get(command);
		return com != null ? com : () -> wrongInput(command);
	}

	/**
	 * We simple execute the given Function.
	 * @param command Takes the function with no arguments and a String as Return value.
	 * @return Returns the String that is returned by the function.
	 */
	@Override
	protected String executeCommand(Supplier<String> command) {
		return command.get();
	}

	/**
	 * Looks up the gameName and initializes the game and runs it.
	 * Internally we set the State of the DungeonController, depending on if
	 * the Gamer exists or not.
	 *
	 * @return A message of success or failure.
	 */
	@Override
	protected String playGame() {
		games.keySet().forEach( (t) -> System.out.print("'" + t + "' "));
		String gameName = userInterface.getInput("\nenter dungeon name> ");
		Function<String, UserCommandLineInterface, Gamer> g = games.get(gameName);
		if (g == null) {
			return "There is no dungeon '" + gameName + "' to explore...";
		}
		return g.execute(playerName, userInterface).playGame().get();
	}


}
