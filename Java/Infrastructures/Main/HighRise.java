package FX_W20PKG;

public class HighRise extends Building {
    private double highRise; // describes the height of the high-rise.


    public double getHighRise() {
        return highRise;
    }

    public void setHighRise(double highRise) {
        this.highRise = highRise;
    }


    public HighRise(int yearOfCreation, double cost, double landSpace, String material, double highRise) {
        super(yearOfCreation, cost, landSpace, material);
        this.highRise = highRise;
    }

    @Override
    public String toString() {
        return "This high rise measuring " + highRise + " meters was created in " + getYearOfCreation() + " and it costs $" + getCost() + ".\nIt has " +
                getLandSpace() + " land space and it's made of " + getMaterial();
    }
}
