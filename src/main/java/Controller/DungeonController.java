package Controller;

import Controller.Model.Controller;
import GameInterface.Gamer;
import Menace.MenaceGame;
import UserInterface.UserCommandLineInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


/**
 * The DungeonController describes a way to play multiple text based Dungeon-Crawler-Games.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class DungeonController extends Controller {

	private UserCommandLineInterface userInterface;
	private Map<String, Gamer> games;
	private String playerName;

	/**
	 * The Constructor for the DungeonController initializes the DungeonController
	 * and creates a new instance of UserCommandLineInterface.
	 */
	public DungeonController() {
		super();
		userInterface = new UserCommandLineInterface();
		this.playerName = "";
		this.games = new HashMap<>();

		commands.put("play", this::playGame);
		this.games.put("menace", new MenaceGame());
	}

	/**
	 * The start method should be called right after init of the DungeonController.
	 */
	@Override
	public void startCommandLineInterface() {
		this.playerName = userInterface.getInput("Hello, enter your name> ");
		userInterface.println(helloGamer(playerName));
		while (true) {
			String input = userInterface.getInput("dungeon portal> ");
			userInterface.println(runCommand(input));
		}
	}

	/**
	 * In this Method we evaluate a given text command form the user and give feedback to the user.
	 *
	 * @param command The command to evaluate on.
	 * @return The massage of the evaluation, success of failure.
	 */
	private String runCommand(String command) {
		Supplier<String> func = commands.get(command);
		if (func == null)
			return wrongInput(command);
		return func.get();
	}

	/**
	 * Looks up the gameName and initializes the game and runs it.
	 *
	 * @return A message of success or failure.
	 */
	private String playGame() {
		String gameName = userInterface.getInput("dungeon portal play> ");
		Gamer game = games.get(gameName);
		if (game == null)
			return wrongInput(gameName);
		return game.playGame(userInterface);
	}

}
