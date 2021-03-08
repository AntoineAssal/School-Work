package Infrastructures.Main;

public class Building extends Structure{
    private double landSpace; //amount of land space of the building
    private String material; // main material used ofr building (wood, concrete, glass, etc)

    // getters and setters
    public double getLandSpace() {
        return landSpace;
    }

    public void setLandSpace(double landSpace) {
        this.landSpace = landSpace;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    // parameterized constructor
    public Building(int yearOfCreation, double cost, double landSpace, String material) {
        super(yearOfCreation, cost);
        this.landSpace = landSpace;
        this.material = material;
    }

    @Override
    public String toString() {
        return "This building was created in " + getYearOfCreation() + " and it costs $" + getCost() + ".\nIt has "+
                 landSpace + ", land space and is made of " + material;
    }
}
