package FX_W20PKG;

public class CondoBuilding extends ResidentialBuilding {
    private int numOfUnits; // describes the number of condo units the building has.

    public int getNumOfUnits() {
        return numOfUnits;
    }

    public void setNumOfUnits(int numOfUnits) {
        this.numOfUnits = numOfUnits;
    }

    public CondoBuilding(int yearOfCreation, double cost, double landSpace, String material,
                         int maxNumberOfHabitants, int numOfUnits) {
        super(yearOfCreation, cost, landSpace, material, maxNumberOfHabitants);
        this.numOfUnits = numOfUnits;
    }
    @Override
    public String toString() {
        return "This " + numOfUnits + " units condo building with a max number of habitants of  " + getMaxNumberOfHabitants()
                + " was created in " + getYearOfCreation() + " and it costs $" + getCost() + ".\nIt has " +
                getLandSpace() + ", land space and material is " + getMaterial();
    }
}
