package games.menace.fighters.generic;

/**
 * The Defender Interface.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public interface Defender {
    /**
     * This method will be one of the main parts to the fighting in the game.
     * @param attacker The attacker which to defend against.
     */
    public void defend(LifeForm attacker);
}
