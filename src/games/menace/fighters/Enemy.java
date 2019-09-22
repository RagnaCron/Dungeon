package games.menace.fighters;

import games.menace.fighters.generic.Attacker;
import games.menace.fighters.generic.LifeForm;
import games.menace.items.Weapon;


/**
 * The
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Enemy extends LifeForm implements Attacker {
    private Weapon rightHandWeapon;

    public Enemy(String name, boolean isALife, int lifePoints, Weapon rightHandWeapon) {
        super(name, isALife, lifePoints);
        this.rightHandWeapon = rightHandWeapon;
    }

    @Override
    public String toString() {
        return super.toString() + rightHandWeapon.stats();
    }

    @Override
    public void attack(LifeForm defender) {
        defender.loseLifePoints(getLifePoints());
    }

    public Weapon getRightHandWeapon() {
        return rightHandWeapon;
    }
}
