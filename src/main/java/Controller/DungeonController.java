package Controller;

import Controller.Model.Controller;
import Controller.Model.ControllerState;
import Menace.MenaceGame;
import UserInterface.UserCommandLineInterface;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.function.Supplier;


/**
 * The DungeonController describes a way to play multiple text based Dungeon-Crawler-Games.
 * After init, you should call startController.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class DungeonController extends Controller {

	// TODO: JAVADOC

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

		games.put("menace", MenaceGame.CreateNewMenaceGame(playerName));
	}

	/**
	 * The start method should be called right after init of the DungeonController.
	 */
	@Override
	public void startController() {
		state = ControllerState.CHOOSING_STATE;
		while (true) {
			switch (state) {
				case CHOOSING_STATE:
					state = controlCommand("dungeon portal> ");
					break;
				case GAMING_STATE:
					state = controlCommand("exploring dungeon> ");
					break;
			}
		}
	}

	private ControllerState controlCommand(String prompt) {
		String input;
		Pair<ControllerState, Supplier<String>> command;
		String output;
		input = userInterface.getInput(prompt);
		command = getCommand(input);
		state = command.getKey();
		output = executeCommand(command.getValue());
		userInterface.println(output);
		return state;
	}

	@Override
	protected Pair<ControllerState, Supplier<String>> getCommand(String command) {
		Pair<ControllerState, Supplier<String>> com = null;
		switch (state) {
			case CHOOSING_STATE:
				com = commands.get(command);
				if (com == null)
					com =  new Pair<>(state, () -> wrongInput(command));
				return com;
			case GAMING_STATE:
				com = new Pair<>(state, game.playGame(command));
				return com;
		}
		return com;
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
	 * the Game exists or not.
	 *
	 * @return A message of success or failure.
	 */
	@Override
	protected String playGame() {
		games.keySet().forEach( (t) ->System.out.print("'" + t + "' "));
		String gameName = userInterface.getInput("enter dungeon name> ");
		game = games.get(gameName);
		if (game == null) {
			state = ControllerState.CHOOSING_STATE;
			return "There is no dungeon '" + gameName + "' to explore...";
		}
		return playerName + " you have entered the " + gameName + " dungeon...good luck!";
	}


}
