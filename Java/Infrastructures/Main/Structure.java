package FX_W20PKG;

public class Structure {
    private int yearOfCreation;
    private double cost;

    // getters and setters
    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // parameterized constructor
    public Structure(int yearOfCreation, double cost) {
        this.yearOfCreation = yearOfCreation;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "This structure was created in " +
                yearOfCreation +
                ", and it costs $" + cost;

    }
}
