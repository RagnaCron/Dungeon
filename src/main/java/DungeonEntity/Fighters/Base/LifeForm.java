package DungeonEntity.Fighters.Base;

import lombok.Getter;
import lombok.Setter;

/**
 * The LifeForm abstract Baseclass will provide the basic API for all LifeForms in the Gamer.
 * All LifeForms in this Gamer have a name and lifePoints.
 *
 * @author Manuel Werder
 * @version 0.1
 */
@Getter
@Setter
public abstract class LifeForm {

    protected String name;
    protected boolean isALife;
    protected int lifePoints;

    /**
     * Init all protected fields.
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
        buildStats += lifePoints == 1 ? "point" : "points";
        return buildStats;
    }

    /**
     * Each LifeForm can lose life. It returns a boolean that is set if lifePoints is less or equals zero.
     * @param lifePoints The lifePoints to lose.
     * @return If LifeForm is still a life.
     */
    public boolean loseLifePoints(int lifePoints) {
        this.lifePoints -= lifePoints;
        isALife = this.lifePoints > 0;
        return isALife;
    }

    /**
     * Add some more life points to the LifeForm.
     * @param lifePoints The life points to add.
     */
    public void addLifePoints(int lifePoints) {
        this.lifePoints += lifePoints;
    }

}
