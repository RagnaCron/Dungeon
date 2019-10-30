package Controller.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class Controller {

	protected Map<String, Supplier<String>> commands = new HashMap<>();

	protected Controller() {
		commands.put("help", this::help);
		commands.put("info", this::info);
		commands.put("commands", this::commands);
	}

	abstract public void start();

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
				"Enter '<name of a game>' to play a game.\n" +
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



}
