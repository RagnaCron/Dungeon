package controller;

import userInterface.UserCLIInterface;


/**
 * The DungeonController describes a way to play multiple text based Dungeon-Crawler-Games.
 * It can be given an argument over the CommandLine while starting the game or just start
 * the game with no argument and chose from a list of games.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class DungeonController {

    private UserCLIInterface userInterface;

    /**
     * The Constructor for the DungeonController will call internally a private method,
     * witch will evaluate if there is a valid argument and set up the game, or will
     * show a list of games to play.
     *
     * When given an argument (one argument only) it will work
     * or it will tell the user that there is no game with the given name
     * and quit the application.
     *
     * @param args Takes one argument. It must be a name to a possible game.
     */
    public DungeonController(String[] args) {
        this.userInterface = new UserCLIInterface();

        userInterface.print("args from CLI are currently not used", "\n");
        String userInput = userInterface.getInput("dungeon@menace> ");
        userInterface.print(userInput, "\n");
    }


}
