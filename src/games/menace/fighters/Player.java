package games.menace.fighters;

import games.menace.fighters.generic.Attacker;
import games.menace.fighters.generic.Defender;
import games.menace.fighters.generic.Healer;
import games.menace.fighters.generic.LifeForm;
import games.menace.items.Potion;
import games.menace.items.Shield;
import games.menace.items.Weapon;

/**
 * The Player. This is the entity that the user gives command to.
 * The Player Class extends the LifeForm Class.
 * The Player Class implements the Healer, Attacker and Defender Interface.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Player extends LifeForm implements Healer, Attacker, Defender {
    private Weapon rightHandWeapon;
    private Shield leftHandShield;
    private Potion potion;

    public Player(String name, boolean isALife, int lifePoints,
                  Weapon rightHandWeapon, Shield leftHandShield, Potion potion)
    {
        super(name, isALife, lifePoints);
        this.rightHandWeapon = rightHandWeapon;
        this.leftHandShield = leftHandShield;
        this.potion = potion;
    }


    @Override
    public String toString() {
        String buildStats = super.toString() + ",\n" + potion.stats();
        buildStats += ",\nright hand: " + rightHandWeapon.stats();
        return buildStats + ",\nleft hand: " + leftHandShield.stats();
    }

    @Override
    public void attack(LifeForm defender) {
        defender.loseLifePoints(getLifePoints());
    }

    @Override
    public void defend(LifeForm attacker) {
        try {
            Enemy enemy = (Enemy) attacker;

        } catch (Exception e) {
            // we dont care
        }
    }

    /**
     * Heal consumes the potion.
     */
    @Override
    public void heal() {
        setLifePoints(potion.getLifePoints());
        potion = null;
    }

}
