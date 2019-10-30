package Controller;

import Controller.Model.Controller;
import GameInterface.Gamer;
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
public class DungeonController extends Controller {

	private UserCommandLineInterface userInterface;
	private Map<String, Gamer> games = new HashMap<>();
	private String playerName;
	private String gameName = "";

	/**
	 * The Constructor for the DungeonController initializes the DungeonController
	 * and creates a new instance of UserCommandLineInterface.
	 */
	public DungeonController() {
		super();
		commands.put("exit", this::exit);
		userInterface = new UserCommandLineInterface();
		this.playerName = userInterface.getInput("Hello, enter your name> ");
	}

	/**
	 * The start method should be called right after init of the DungeonController.
	 */
	@Override
	public void start() {
		userInterface.println(helloGamer(playerName));
		while (true) {
			String input = userInterface.getInput("dungeon portal> ");
			userInterface.println(runCommand(input));
		}
	}

	private String exit() {
		System.exit(0);
		return null;
	}


	private String runCommand(String command) {
		Supplier<String> func = commands.get(command);
		if (func == null)
			return wrongInput(command);
		return func.get();
	}

	/**
	 * Looks up the gameName and initializes the game with the roomCount.
	 * @param gameName The name of the game to play.
	 * @param roomCount The number of Rooms tho create.
	 * @return Returns a playable Game.
	 */
	private Gamer choseGame(String gameName, int roomCount) {
		// TODO: - chose a Game out of the available game list.
		return null;
	}

}
