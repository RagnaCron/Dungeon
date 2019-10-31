package Menace;

import GameInterface.Gamer;
import UserInterface.UserCommandLineInterface;

public final class MenaceGame implements Gamer {

	@Override
	public String playGame(UserCommandLineInterface userInterface) {
		return userInterface.getInput("Hello, world!");
	}


}
