package dungeonEntitys.fighters;

import dungeonEntitys.fighters.generic.Attacker;
import dungeonEntitys.fighters.generic.LifeForm;
import dungeonEntitys.items.Weapon;
import lombok.Getter;


/**
 * The Enemy. This is the entity to fight as user.
 * The Enemy Class extends the LifeForm Class.
 * The Enemy Class implements the Attacker Interface.
 *
 * @author Manuel Werder
 * @version 0.1
 */
@Getter
public class Enemy extends LifeForm implements Attacker {
    private Weapon rightHandWeapon;

    /**
     *
     * @param name Name of the Enemy.
     * @param isALife Is the Enemy a life.
     * @param lifePoints The lifePoints the Enemy has.
     * @param rightHandWeapon The Weapon the Enemy holds.
     */
    public Enemy(String name, boolean isALife, int lifePoints, Weapon rightHandWeapon) {
        super(name, isALife, lifePoints);
        this.rightHandWeapon = rightHandWeapon;
    }


    /**
     * toString gives you a beautified version of what the Player is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        return super.toString() + ", \nright hand: " + rightHandWeapon.toString();
    }

    /**
     * toString gives you a beautified version of what the Enemy is.
     * @return The nice and beauty.
     */
    @Override
    public void attack(LifeForm defender) {
        defender.loseLifePoints(getLifePoints());
    }

}
