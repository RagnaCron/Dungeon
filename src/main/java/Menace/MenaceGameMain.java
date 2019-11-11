package Menace;


import Controller.Controller;

/**
 * The entry point to the Dungeon game.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class MenaceGameMain {

    // TODO: JAVADOC

    public static void main(String[] args) {

        Controller controller = new DungeonController();
        controller.startController();

    }

}
