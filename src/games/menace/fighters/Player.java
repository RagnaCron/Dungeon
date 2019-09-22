package games.menace.fighters;

import games.menace.fighters.generic.Attacker;
import games.menace.fighters.generic.Defender;
import games.menace.fighters.generic.Healer;

/**
 * The Player. This is the entity that the user gives command to.
 * The Player Class extends the LifeForm Class.
 * The Player Class implements the Healer, Attacker and Defender Interface.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Player implements Healer, Attacker, Defender {



    @Override
    public void attack(Attacker attacker) {

    }

    @Override
    public void defend(Attacker attacker) {

    }

    @Override
    public void heal() {

    }
}
