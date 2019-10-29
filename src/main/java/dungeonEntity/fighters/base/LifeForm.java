package dungeonEntity.fighters.base;


import lombok.Getter;
import lombok.Setter;

/**
 * The LifeForm abstract Baseclass will provide the basic API for all LifeForms in the Game.
 * All LifeForms in this Game have a name and lifePoints.
 *
 * (It can be that at a later time the LifeForm may to hold a Shield in its leftHand.)
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class LifeForm {
    @Getter
    protected final String name;
    @Getter
    @Setter
    protected boolean isALife;
    @Getter
    @Setter
    protected int lifePoints;

    /**
     *
     * @param name The name of the LifeForm.
     * @param isALife Is the LifeForm a life.
     * @param lifePoints The lifePoints of the LifeForm.
     */
    public LifeForm(String name, boolean isALife, int lifePoints) {
        this.name = name;
        this.isALife = isALife;
        this.lifePoints = lifePoints;
    }

    /**
     * toString gives you a beautified version of what this LifeForm is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        String buildStats = name + " has " + lifePoints + " life ";
        if (lifePoints == 1) {
            buildStats += "point";
        } else {
            buildStats += "points";
        }
         return buildStats;
    }

    /**
     * Each LifeForm can lose life. It returns a boolean that is set if lifePoints is less or equals zero.
     * @param lifePoints The lifePoints to lose.
     * @return If LifeForm is still a life.
     */
    public boolean loseLifePoints(int lifePoints) {
        this.lifePoints -= lifePoints;
        if (this.lifePoints <= 0) {
            isALife = false;
        }
        return isALife;
    }

    public void addLifePoints(int lifePoints) {
        this.lifePoints += lifePoints;
    }

}
