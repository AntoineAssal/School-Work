package FX_W20PKG;

public class ResidentialBuilding extends Building {
    private int maxNumberOfHabitants; // the maximum number of expected habitants.


    public int getMaxNumberOfHabitants() {
        return maxNumberOfHabitants;
    }

    public void setMaxNumberOfHabitants(int maxNumberOfHabitants) {
        this.maxNumberOfHabitants = maxNumberOfHabitants;
    }
    public ResidentialBuilding(int yearOfCreation, double cost, double landSpace, String material, int maxNumberOfHabitants) {
        super(yearOfCreation, cost, landSpace, material);
        this.maxNumberOfHabitants = maxNumberOfHabitants;
    }

    @Override
    public String toString() {
        return "This residential building with a max number of habitants of  " + maxNumberOfHabitants + " was created in " + getYearOfCreation() + " and it costs $" + getCost() + ".\nIt has " +
                getLandSpace() + " land space and made of " + getMaterial();
    }
}
