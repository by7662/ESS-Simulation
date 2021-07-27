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
     * Getter for Strategy
     * @return Strategy
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Check if the Strategy of the Individual is dead.
     * @return true if Strategy's resource amount is below 0, false otherwise
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
     * Setter whether the Strategy is dead
     * @param dead true if Strategy's resource amount is below 0, false otherwise
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
     * Setter for the Strategy for the Individual. Used for changing Strategy to DEAD when its resources amount is below 0
     * @param strategy Enum of the Strategy to change into
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
