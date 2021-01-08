package FX_W20PKG;

public class Overpass extends Bridge {
    private double maxLoad; // indicate the maximum load/weight accommodated by the overpass

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public Overpass(int yearOfCreation, double cost, double length, double height, double maxLoad) {
        super(yearOfCreation, cost, length, height);
        this.maxLoad = maxLoad;
    }

    @Override
    public String toString() {
        return "This overpass was created in " + getYearOfCreation() + " and it's cost is $" + getCost() + ".\nIt has a length of " +
                getLength() + " meters and it's height is " + getHeight() + " meters, it's max load is " + maxLoad;
    }
}