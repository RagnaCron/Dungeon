package Menace;

import GameInterface.Gamer;
import UserInterface.UserCommandLineInterface;

public final class MenaceGame implements Gamer {

	@Override
	public void playGame(UserCommandLineInterface userInterface) {
		userInterface.getInput("Hello, world!");
	}


}
