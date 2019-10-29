package games.menace.fighters.generic;

/**
 * The Attacker Interface.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Attacker {
    /**
     * This method will be one of the main parts of attacking an opponent in the game.
     * @param attacker The lifeForm to be attacked.
     */
    void attack(LifeForm attacker);
}
