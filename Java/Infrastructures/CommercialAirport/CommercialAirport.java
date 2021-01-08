package CommercialAirport;

import FX_W20PKG.Airport;

public class CommercialAirport extends Airport {
    private int numOfGates;

    public int getNumOfGates() {
        return numOfGates;
    }

    public void setNumOfGates(int numOfGates) {
        this.numOfGates = numOfGates;
    }

    public CommercialAirport(int yearOfCreation, double cost, int numOfRunways, String code, int numOfGates) {
        super(yearOfCreation, cost, numOfRunways, code);
        this.numOfGates = numOfGates;
    }
    @Override
    public String toString() {
        return "This airport was created in " + getYearOfCreation() + " and it costs $" + getCost() + ".\nIt has "+
                getNumOfRunways() + " runways and " + numOfGates+ " gates. Its code is: " + getCode();
    }
}
