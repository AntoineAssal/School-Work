package CargoAirport;

import FX_W20PKG.Airport;

public class CargoAirport extends Airport {
    private double landedWeight; // describes the weight of landed goods on the airport. for an airport to be classified
                                // as a cargo airport, the airport must have a landed weight of 100 million pounds.

    public double getLandedWeight() {
        return landedWeight;
    }

    public void setLandedWeight(double landedWeight) {
        this.landedWeight = landedWeight;
    }

    public CargoAirport(int yearOfCreation, double cost, int numOfRunways, String code, double landedWeight) {
        super(yearOfCreation, cost, numOfRunways, code);
        this.landedWeight = landedWeight;
    }
    @Override
    public String toString() {
        return "This airport was created in " + getYearOfCreation() + " and it costs $" + getCost() + ".\nIt has "+
                getNumOfRunways() + " runways. Its code is: " + getCode() + " and has a landing weight of " + landedWeight + " pounds.";
    }
}
