package DungeonEntity.Fighters;

import DungeonEntity.Fighters.Base.Attacker;
import DungeonEntity.Fighters.Base.Defender;
import DungeonEntity.Fighters.Base.LifeForm;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;


/**
 * The Enemy. This is the entity to fight as user.
 * The Enemy Class extends the LifeForm Class.
 * The Enemy Class implements the Attacker and Defender Interface.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Enemy extends LifeForm implements Attacker, Defender {

    @Getter @Setter
    private Weapon rightHandWeapon;
    @Getter @Setter
    private Shield leftHandShield;
    private Random rand = new Random();

    /**
     * Calls super and then Inits the private fields.
     * @param name Name of the Enemy.
     * @param isALife Is the Enemy a life.
     * @param lifePoints The lifePoints the Enemy has.
     * @param rightHandWeapon The Weapon the Enemy holds.
     */
    public Enemy(String name, boolean isALife, int lifePoints, Weapon rightHandWeapon, Shield leftHandShield) {
        super(name, isALife, lifePoints);
        this.rightHandWeapon = rightHandWeapon;
        this.leftHandShield = leftHandShield;
    }

    /**
     * This constructor is used to copy an Enemy.
     * @param enemy The Enemy to copy.
     */
    public Enemy(Enemy enemy) {
        this(enemy.getName(), enemy.isALife, enemy.getLifePoints(), enemy.getRightHandWeapon(), enemy.getLeftHandShield());
    }

    /**
     * toString gives you a beautified version of what the Enemy is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        return super.toString() + ", \nright hand: " + rightHandWeapon.toString() + "," +
                "\nleft hand: " + leftHandShield.toString();
    }

    /**
     * Attack the the the LifeForm.
     *
     * @param defender The LifeForm to attack.
     */
    @Override
    public void attack(LifeForm defender) {
        defender.loseLifePoints(rightHandWeapon.getAttackPoints());
    }

    /**
     * This method will be one of the main parts to the fighting in the game.
     *
     * @param attacker The attacker which to defend against.
     */
    @Override
    public void defend(LifeForm attacker) {
        try {
            Player player = (Player) attacker;
            if (rand.nextBoolean()) {
                if (hasShield() && leftHandShield.getDefensePoints() < player.getRightHandWeapon().getAttackPoints()) {
                    int lifePointsToLose = player.getRightHandWeapon().getAttackPoints() - leftHandShield.getDefensePoints();
                    this.loseLifePoints(lifePointsToLose);
                }
            } else {
                loseLifePoints(player.getRightHandWeapon().getAttackPoints());
            }
        } catch (ClassCastException e) {
            // Currently we don't care
        }

    }

    /**
     * Check if the enemy has a Shield.
     * @return True if enemy has a Shield false otherwise.
     */
    public boolean hasShield() {
        return leftHandShield != null;
    }
}
