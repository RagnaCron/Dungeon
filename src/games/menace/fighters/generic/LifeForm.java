package games.menace.fighters.generic;


import games.menace.items.Weapon;

/**
 * The LifeForm Baseclass will provide the basic API all LifeForms in the Game.
 * All LifeForms in this Game have a name, lifePoints and hold a Weapon in the
 * right hand.
 *
 * (It can be that at a later time the LifeForm may to hold a Shield in its leftHand.)
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class LifeForm {
    private String name;
    private boolean isALife;
    private int lifePoints;
    private Weapon rightHandWeapon;

    public LifeForm(String name, boolean isALife, int lifePoints, Weapon rightHandWeapon) {
        this.name = name;
        this.isALife = isALife;
        this.lifePoints = lifePoints;
        this.rightHandWeapon = rightHandWeapon;
    }

    /**
     * Stats gives you a beautified version of what this LifeForm is.
     * @return Type: String. The nice and beauty.
     */
    public String stats() {
        String buildStats = getName() + " has " + lifePoints + " life ";
        if (lifePoints == 1) {
            buildStats += "point,\n";
        } else {
            buildStats += "points,\n";
        }
         return buildStats + "right hand: " + getRightHandWeapon().getName();
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Each LifeForm can lose life. It returns a boolean that is set if lifePoints is less or equals zero.
     * @param lifePoints Type: int. The lifePoints to lose.
     * @return Type: boolean. If LifeForm is still a life.
     */
    public boolean loseLifePoints(int lifePoints) {
        this.lifePoints -= lifePoints;
        if (this.lifePoints <= 0) {
            isALife = false;
        }
        return isALife;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints += lifePoints;
    }

    public boolean isALife() {
        return isALife;
    }

    public Weapon getRightHandWeapon() {
        return rightHandWeapon;
    }
}
