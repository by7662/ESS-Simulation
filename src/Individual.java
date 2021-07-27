/*
 * Name: Benson Yan
 * Class: CSCI 331
 * Instructor: Prof.Borrelli
 */

public class Individual implements Comparable{
    private Strategy strategy;
    private boolean isDead;
    private int resources;

    public Individual(Strategy strategy, boolean isDead, int resources) {
        this.strategy = strategy;
        this.isDead = isDead;
        this.resources = resources;
    }

    /**
     * Getter for src.Strategy
     * @return src.Strategy
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Check if the src.Strategy of the src.Individual is dead.
     * @return true if src.Strategy's resource amount is below 0, false otherwise
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Return the resources amount
     * @return resources amount from the individual
     */
    public int getResources() {
        return resources;
    }

    /**
     * Setter whether the src.Strategy is dead
     * @param dead true if src.Strategy's resource amount is below 0, false otherwise
     */
    public void setDead(boolean dead) {
        isDead = dead;
        if (dead){
            setStrategy(Strategy.DEAD);
        }
    }

    /**
     * Setter the resources amount for the individual
     * @param resources the resources amount
     */
    public void setResources(int resources) {
        this.resources = resources;
    }

    /**
     * Setter for the src.Strategy for the src.Individual. Used for changing src.Strategy to DEAD when its resources amount is below 0
     * @param strategy Enum of the src.Strategy to change into
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public int compareTo(Object o) {
        Individual i = (Individual) o;
        if (this.resources > i.resources){
            return 1;
        }
        else if (this.resources < i.resources){
            return -1;
        }
        return 0;
    }
}
