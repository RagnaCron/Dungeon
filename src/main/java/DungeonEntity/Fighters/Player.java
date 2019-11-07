package DungeonEntity.Fighters;

import DungeonEntity.Fighters.Base.*;
import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * The Player. This is the entity that the user gives command to.
 * The Player Class extends the LifeForm Class.
 * The Player Class implements the Healer, Attacker and Defender Interface.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Player extends LifeForm implements Healer, Attacker, Defender {

    @Setter @Getter
    private Weapon rightHandWeapon;
    @Setter @Getter
    private Shield leftHandShield;
    @Setter @Getter
    private Potion potion;

    private Random rand = new Random();
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
        this(name, true, 100, new Weapon("Stick", 6),
                new Shield("Wooden plank", 6),
                new Potion("Water", 20));
    }

    /**
     * toString gives you a beautified version of what the Player is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        if (hasPotion()) builder.append(",\n").append(potion.toString());
        else builder.append(",\n").append("no potion");
        builder.append(",\nright hand: ")
                .append(rightHandWeapon.toString())
                .append(",\nleft hand: ")
                .append(leftHandShield.toString());
        return builder.toString();
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
            if (rand.nextBoolean()) {
                if (hasShield() && leftHandShield.getDefensePoints() < enemy.getRightHandWeapon().getAttackPoints()) {
                    int lifePointsToLose = enemy.getRightHandWeapon().getAttackPoints() - leftHandShield.getDefensePoints();
                    loseLifePoints(lifePointsToLose);
                }
            } else {
                loseLifePoints(enemy.getRightHandWeapon().getAttackPoints());
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
        if (hasPotion()) {
            addLifePoints(potion.getLifePoints());
            potion = null;
        }
    }

    /**
     * Check if the player has a Potion.
     * @return True if player has a Potion false otherwise.
     */
    public boolean hasPotion() {
        return potion != null;
    }

    /**
     * Check if the player has a Weapon.
     * @return True if player has a Weapon false otherwise.
     */
    public boolean hasWeapon() {
        return rightHandWeapon != null;
    }

    /**
     * Check if the player has a Shield.
     * @return True if player has a Shield false otherwise.
     */
    public boolean hasShield() {
        return leftHandShield != null;
    }
}
