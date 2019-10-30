package Controller;

import Controller.Model.DungeonControllerModel;
import GameInterface.Gamer;
import Menace.MenaceGame;
import UserInterface.UserCommandLineInterface;
import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;


/**
 * The DungeonController describes a way to play multiple text based Dungeon-Crawler-Games.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class DungeonController {

    private UserCommandLineInterface userInterface;
    private DungeonControllerModel controllerModel = new DungeonControllerModel();

    private HashMap<String, Gamer> games = new HashMap<>();

    @Setter
    @Getter
    private String gameName = "";

    /**
     * The Constructor for the DungeonController initializes the DungeonController
     * and creates a new instanz of UserCommandLineInterface.
     */
    public DungeonController() {
        this.userInterface = new UserCommandLineInterface();
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
