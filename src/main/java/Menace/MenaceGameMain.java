package Menace;


import Controller.Controller;

/**
 * The entry point to the Dungeon game. This class is not meant to be subclassed.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class MenaceGameMain {

    /**
     * The main static Method of the DungeonGame. It does not use the arguments from the CLI.
     * @param args CLI commands.
     */
    public static void main(String[] args) {
        Controller controller = new DungeonController();
        controller.startController();
    }

}
