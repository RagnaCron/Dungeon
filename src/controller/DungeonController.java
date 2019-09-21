package controller;

import userInterface.UserCLIInterface;

public class DungeonController {

    private UserCLIInterface userInterface;

    public DungeonController(String[] args) {
        this.userInterface = new UserCLIInterface();

        userInterface.print("args from CLI are currently not used", "\n");
        String userInput = userInterface.getInput("dungeon@menace> ");
        userInterface.print(userInput, "\n");
    }
}
