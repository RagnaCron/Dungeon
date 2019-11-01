package DungeonEntity.Fighters;

import DungeonEntity.Fighters.Base.*;
import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import lombok.Getter;
import lombok.Setter;

/**
 * The Player. This is the entity that the user gives command to.
 * The Player Class extends the LifeForm Class.
 * The Player Class implements the Healer, Attacker and Defender Interface.
 *
 * @author Manuel Werder
 * @version 0.1
 */
@Setter
@Getter
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

    public Player(String name) {
        this(name, true, 5, new Weapon("Stick", 3),
                new Shield("Wooden plank", 3),
                new Potion("Water", 3));
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

    /**
     * Attack an opponent. It just subtracts the a attack points from the player weapon
     * of the life points form an opponent.
     *
     * @param defender The life form that is defending.
     */
    @Override
    public void attack(LifeForm defender) {
        defender.loseLifePoints(rightHandWeapon.getAttackPoints());
    }

    /**
     * Defend against an attacking opponent.
     *
     * @param attacker The attacker which to defend against.
     */
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
     * Heal consumes the potion and adds the life points of the potion to the player life points.
     */
    @Override
    public void heal() {
        if (potion != null) {
            addLifePoints(potion.getLifePoints());
            potion = null;
        }
    }
}
