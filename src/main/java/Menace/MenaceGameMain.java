package Menace;

import Controller.DungeonController;

import java.util.Random;


/**
 * The entry point to the Dungeon game.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class MenaceGameMain {

    public static void main(String[] args) {

        DungeonController controller = new DungeonController();
        controller.start();

    }

}
