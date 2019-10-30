package Controller;

import Controller.Model.DungeonControllerModel;
import GameInterface.Gamer;
import UserInterface.UserCommandLineInterface;


/**
 * The DungeonController describes a way to play multiple text based Dungeon-Crawler-Games.
 * It can be given an argument over the CommandLine while starting the game or just start
 * the game with no argument and chose from a list of Games.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class DungeonController {

    private UserCommandLineInterface userInterface;
    private DungeonControllerModel controllerModel = new DungeonControllerModel();

    private String gameName;

    /**
     * The Constructor for the DungeonController will evaluate it there is an argument,
     * if yes it will check if it is a valid gameName, if the name is not valid it will
     * stop and give out a message to the user, else it will start up the game.
     *
     * @param args Takes one or zero arguments. It must be a name to a game.
     */
    public DungeonController(String[] args) {
        this.userInterface = new UserCommandLineInterface();
        if (args.length == 1) {
            gameName = args[0];
            if (checkGameName(gameName)) return;
            userInterface.println( controllerModel.noGameWithThatName(gameName));
            System.exit(2);
        } else if(args.length > 1) {
            userInterface.println(controllerModel.wrongInputParameters());
            System.exit(1);
        } else {
            gameName = null;
        }
    }

    /**
     * The start method should be called right after init of the DungeonController.
     */
    public void start() {

    }

    private boolean checkGameName(String gameName) {
        return false;
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

    /**
     * The chosenGame method calls the playGame method on the class that implements the GamesInterface.Gamer Interface.
     * @param game The gamer Interface that all Games have to implement in the Dungeon game Series.
     */
    private void chosenGame(Gamer game, UserCommandLineInterface userInterface) {
        game.playGame(userInterface);
    }

}
