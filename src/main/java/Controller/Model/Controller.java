package Controller.Model;

import Commander.Command;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Controller extends Command<String, Supplier<String>> {

	protected Controller() {
		commands = new HashMap<>();
		commands.put("help", this::help);
		commands.put("info", this::info);
		commands.put("commands", this::commands);
		commands.put("exit", this::exit);
	}

	abstract public void startController();

	protected String helloGamer(String name) {
		return "Hello " + name + ", welcome to a Text base Dungeon Crawler Game Library.\n" +
				"Enter the command 'help' to see some basic infos.";
	}

	protected String wrongInput(String command) {
		return "Sorry there is no command named: " + command;
	}

	private String help() {
		return "This is the help text...Enter 'help' for this text.\n" +
				"Most commands that can be run have a simple syntax, \n" +
				"'<command name>', exchange  everything\n" +
				"between the <> with a given command. For example 'info'.\n" +
				"Enter 'play' to play a game. You will then have to chose from a list of games.\n" +
				"Note: While you are playing a game you can not use 'help' or 'info'.\n" +
				"Enter 'commands' to see all possible commands to the Dungeon.";
	}

	private String info() {
		return "Those are the current games you can play:\n" +
				"'menace'.";
	}

	private String commands() {
		return "The Dungeon has the following commands:\n" +
				"'help', 'info', 'command', '<game name>',\n" +
				"'<game name> info'.";
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
