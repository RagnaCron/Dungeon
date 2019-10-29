package controller.model;

public final class DungeonControllerModel {

	public String wrongInputParameters() {
		return "You can use this game with zero or 1 arguments.\n" +
				"With no argument you will enter the game menu.\n" +
				"With one arguments: The game name you want to play.";
	}

	public String noGameWithThatName(String gameName) {
		return "There is no game named: " + gameName;
	}

}
