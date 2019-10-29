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

    /**
     *
     * @param name Name of the Player.
     * @param isALife Is the Player a life.
     * @param lifePoints The lifePoints the Player has.
     * @param rightHandWeapon The Weapon the Player holds.
     * @param leftHandShield The Shield the Player holds.
     * @param potion The Potion that the Player can use.
     */
    public Player(String name, boolean isALife, int lifePoints,
                  Weapon rightHandWeapon, Shield leftHandShield, Potion potion)
    {
        super(name, isALife, lifePoints);
        this.rightHandWeapon = rightHandWeapon;
        this.leftHandShield = leftHandShield;
        this.potion = potion;
    }

    /**
     * toString gives you a beautified version of what the Player is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        String buildStats = super.toString() + ", \n" + potion.toString();
        buildStats += ", \nright hand: " + rightHandWeapon.toString();
        return buildStats + ", \nleft hand: " + leftHandShield.toString();
    }

    @Override
    public void attack(LifeForm defender) {
        defender.loseLifePoints(getLifePoints());
    }

    @Override
    public void defend(LifeForm attacker) {
        try {
            Enemy enemy = (Enemy) attacker;
            if (leftHandShield.getDefensePoints() < enemy.getRightHandWeapon().getAttackPoints()) {
                int lifePointsToLose = enemy.getRightHandWeapon().getAttackPoints() - leftHandShield.getDefensePoints();
                loseLifePoints(lifePointsToLose);
            }
        } catch (ClassCastException e) {
            // Currently we don't care
        }
    }

    /**
     * Heal consumes the potion.
     */
    @Override
    public void heal() {
        if (potion != null) {
            addLifePoints(potion.getLifePoints());
            potion = null;
        }
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }
}
