package Infrastructures.Main;

public class House extends ResidentialBuilding {
    private double numberOfRooms; // describes the number of rooms in the house
    private int numOfFloors; // describes the number of floors in the house.

    public double getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public void setNumOfFloors(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public House(int yearOfCreation, double cost, double landSpace, String material, int maxNumberOfHabitants, double numberOfRooms, int numOfFloors) {
        super(yearOfCreation, cost, landSpace, material, maxNumberOfHabitants);
        this.numberOfRooms = numberOfRooms;
        this.numOfFloors = numOfFloors;
    }

    @Override
    public String toString() {
        return "This house with a max number of habitants of  " + getMaxNumberOfHabitants() + ", " + numberOfRooms + " rooms and " + numOfFloors + " floor(s)"
                + " was created in " + getYearOfCreation() + " and it costs $" + getCost() + ".\nIt has " +
                getLandSpace() + " land space and made of " + getMaterial();
    }
}
